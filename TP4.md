# TP4 : portage du code de l'example spring.io de Java 8 vers Java 7

Le code proposé sur le site spring.io est prévu pour Java 8. (https://spring.io/guides/gs/uploading-files/).
Les machines de l'ENSSAT sont installées avec Java 7. Il y a 3 parties de code qui ne fonctionnent pas sous Java 7.

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
solution simple qui fonctionne d'abord, on refactorise ensuite...)

#### Test de cette première modification
Nous avons ensuite commenté tous les autres problèmes. L'application fonctionne déjà en l'état : elle permet
de télécharger des fichiers mais elle ne permet pas de visualiser la liste des fichiers téléchargés.
