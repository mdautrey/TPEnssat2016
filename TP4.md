# TP4 : portage du code de l'example spring.io de Java 8 vers Java 7

Le code proposé sur le site spring.io est prévu pour Java 8. (https://spring.io/guides/gs/uploading-files/).
Les machines de l'ENSSAT sont installées avec Java 7. Il y a 3 parties de code qui ne fonctionnent pas sous Java 7 et qui
constituent finalement 2 problèmes à régler.

## 1er problème : initialisation de l'application spring boot
Au démarrage de l'application, le code actuel fait appel aux lambdas :

    @SpringBootApplication
    @EnableConfigurationProperties(StorageProperties.class)
    public class Application {

        public static void main(String[] args) {
            SpringApplication.run(Application.class, args);
        }

        @Bean
        CommandLineRunner init(StorageService storageService) {
            return (args) -> {
                storageService.deleteAll();
                storageService.init();
            };
        }
    }

### Que fait exactement ce code ?
#### @SpringBootApplication
En très résumé, @SpringBootApplication indique que l'on instancie un container IOC/Spring en le peuplant avec les beans
découverts à travers le mécanisme d'auto configuration

#### @EnableConfigurationProperties(StorageProperties.class)
Cette deuxième commande charge les éléments de configuration contenus dans StorageProperties
Ces éléments sont ensuite utilisés dans la classe FileSystemStorageService qui implémente l'interface StorageService via
le mécanisme d'autowire :
    @Autowired
    public FileSystemStorageService(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
    }

#### @Bean CommandLineRunner

Détails ici http://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-command-line-runner

C'est un bean qui contient du code qui n'est exécuté qu'une fois au moment du démarrage de l'application, juste avant que
SpringApplication.run() ne termine son exécution.

Plus de détails ici http://docs.spring.io/spring-boot/docs/current/api/org/springframework/boot/SpringApplication.html
Le CommandLineRunner :
* Crée un ApplicationContext (~ le container applicatif)
* Récupère et convertit en properties envoyés en ligne de commande par l'utilisateur pour lancer l'application (CommandLinePropertySource)
* Met à jour le contexte applicatif en chargeant tous les beans singleton
* Lancer les beans CommandLineRunner (dont celui défini dans le code ci-dessus)

Dans ce code, on initialise un bean commandLineRunner qui sera donc lancé à la fin du processus d'initialisation. Pour initialiser ce bean,
on lui passe deux morceaux de code à exécuter dans le commandLineRunner à savoir supprimer tout le contenu du storageService et l'initialiser.
Ces deux actions correspondent au code suivant :

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    @Override
    public void init() {
        try {
            Files.createDirectory(rootLocation);
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }

#### Solution pour supprimer les lambdas dans le code principal de l'application
Nous créons un bean qui implémente ApplicationRunner (qui sera donc lancé au démarrage de l'application) en ajoutant le
code suivant :

    package upload.storage;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.boot.ApplicationArguments;
    import org.springframework.boot.ApplicationRunner;
    import org.springframework.stereotype.Component;

    /**
    * Created by mdautrey on 14/10/16.
    */
    @Component
    public class StorageServiceInit implements ApplicationRunner {
        @Autowired StorageService storageService;

        @Override
        public void run(ApplicationArguments applicationArguments) throws Exception {
            storageService.deleteAll();
            storageService.init();
        }
    }

Comme ce bean est lancé après l'instanciation des singletons, il a accès au storageService et peut donc ensuite
lancer la suppression / réinitialisation du répertoire upload-dir à chaque démarrage de l'application.

Il est sans doute possible de réintégrer ce code à l'objet Application mais nous ne l'avons pas fait (on trouve une
solution simple qui fonctionne d'abord, on refactorise ensuite...).

On voit bien la logique différente en terme de conception de code entre cette version et la version précédente qui utilise
les lambdas.

#### Test de cette première modification
Nous avons ensuite commenté tous les autres problèmes. L'application fonctionne déjà en l'état : elle permet
de télécharger des fichiers mais elle ne permet pas de visualiser la liste des fichiers téléchargés.

## 2ème problème : la méthode listUploadedFiles du contrôleur FileUploadController fait appel à un lambda
Si nous souhaitons être compatible Java7, il nous faut revoir le code suivant :
    model.addAttribute("files", storageService
                .loadAll()
                .map(path ->
                        MvcUriComponentsBuilder
                                .fromMethodName(FileUploadController.class, "serveFile", path.getFileName().toString())
                                .build().toString())
                .collect(Collectors.toList()));

Et ce code fait de plus appel à la librairie java.util.stream.Collectors qui n'est supporté que dans Java 1.8.

### Premier niveau d'analyse
On repère tout de suite 3 éléments classiques dans ce code :
* model.addAttribute => on passe une structure de données nommée files au modèle du MVC
* .map => on mappe une structure de données via un lambda (~ une itération + transformation)
* .collect => on rassemble les éléments dans une liste
* Le map et le collect sont appliqués à une structure de données sorties de storageService
On retrouve une structure de traitement classique documentée ici :
https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html

### Deuxième niveau d'analyse
Quelle est la fonction appliquée dans le map ? C'est une méthode de MvcUriComponentsBuilder qui permet de
construire des URIs

Comment la structure de données est-elle utilisée (si nous ne savons pas comment elle est utilisée, il sera difficile de la remplacer...)
Elle est utilisée dans le template de page uploadForm.html
    <ul>
        <li th:each="file : ${files}">
            <a th:href="${file}" th:text="${file}" />
        </li>
    </ul>

On s'aperçoit que l'on a juste un champ file que l'on répète deux fois.

### Organisation des travaux
On constate alors qu'il faut d'abord traiter la question du stream dans la couche persistance avant de traiter ce problème
dans le contrôleur.

### Problème de stream dans la couche de persistance
Il apparait au niveau de l'interface StorageService :

    Stream<Path> loadAll();

La notion de Stream est définie ici dans la doc Java 8 : https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html

comme une liste d'éléments pouvant faire l'objet d'un traitement séquentiel ou parallèle.

On va se ramener à une liste basique qui devrait suffire pour ce que l'on a à faire :

      List<Path> loadAll();

Il faut aussi que l'on adapte l'implémentation en conséquence dans FileSystemStorageService en revoyant la méthode suivante :
    public List<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(path -> this.rootLocation.relativize(path));
        } catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }
    }


On trouve la solution à ce problème directement ici :
http://www.adam-bien.com/roller/abien/entry/listing_directory_contents_with_jdk

qui après quelques modifications donne ceci :

    @Override
    public List<Path> loadAll() {
        List<Path> filePaths = new ArrayList<>();
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(this.rootLocation)) {
            for (Path path : directoryStream) {
                if(!path.equals(this.rootLocation))
                filePaths.add(path);
            }
        } catch (IOException ex) {
            throw new StorageException("Failed to read stored files", e);
        }
        return filePaths;
    }


Et l'on a réglé le problème d'utilisation du stream dans la couche de persistance.

### Problème d'utilisation du lambda pour générer l'attribut passé au modèle dans le contrôleur
La transformation à réaliser pour régler ce deuxième problème est à peu près équivalente à celle utilisée dans la couche
persistance, à savoir remplacer le pattern map + collect par une itération.

Ce qui donne ceci :

    @GetMapping("/")
    public String listUploadedFiles(Model model) throws IOException {
        List<String> files = new ArrayList<>();
        for(Path path : storageService.loadAll()){
            files.add(MvcUriComponentsBuilder
                    .fromMethodName(FileUploadController.class, "serveFile", path.getFileName().toString())
                    .build().toString());
        }
        model.addAttribute("files", files);
        return "uploadForm";
    }

Autant, au niveau la méthode loadAll, le code est plus complexe en java7, autant, ici, on ne voit pas trop de différence.


