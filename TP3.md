#TP3
## Corrigé du TP1
Pour ceux qui ne sont pas parvenus à faire fonctionner le premier TP :

* Faire un git clone (ou un git pull si vous avez déjà les répertoires en local) du répertoire github des TPs
* mvn clean compile assembly:single
* Se positionner dans le répertoire target
* Vérifier la version de java (>=8)
* Exécuter le main qui vous intéresse (il y en trois, raw, design et spring). Par exemple : java -cp InjectionDependance-0.0-jar-with-dependencies.jar TP1.spring.app

## TP2

Deux versions de base de l'application, l'une avec Thymeleaf et spring boot, reprise des tutoriels du site spring.io https://spring.io/guides , l'autre en jsp/tomcat, 
reprise dans les exemples du livre "Spring in action" (https://www.manning.com/books/spring-in-action-fourth-edition ).

Travail à réaliser :
* Etudier la structure de ces deux applications
* Choisir d'adapter l'une ou l'autre pour répondre au besoin exprimé dans le TP2

## TP3
On souhaite ajouter un service de sécurité à l'application.

Dans un premier temps, il s'agit simplement d'authentifier les utilisateurs et de ne donner accès au service et à la liste des fichiers téléchargés qu'aux utilisateurs authentifiés.

Dans un deuxième temps, il s'agit de fournir à chaque utilisateur un espace privé dans lequel il télécharge ses fichiers et auquel il peut accéder pour visualiser la liste des fichiers qu'il a téléchargé.

Enfin, vous pouvez compléter ces deux fonctions par la possibilité de s'authentifier avec un Id externe (linkedin, facebook, ...) via oauth.