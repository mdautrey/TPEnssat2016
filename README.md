# TPEnssat2016
TP spring ENSSAT 2016
## Plan général du TP

* 1ère séance (2h) : Injection de dépendance
    * Présentation du framework
    * Design pattern : exemple
    * Injection de dépendance avec Spring
    * Différents modules du framework
* 2ème séance (4h) : Spring Web application
    * Construction d'une application Web
    * Implémentation du MVC
    * Retour sur la partie métier et le wiring
* 3ème séance (2h) : Spring security + Spring boot
    * Le modèle AAAA
    * Authentification
    * Autorisation
    * Mise en oeuvre de Oauth
    * Présentation de Spring boot
    * Fonctionnement et configuration
* 4ème séance (2h) : Persistance
    * Accès à des solutions de persistance
    * Spring + JDBC
    * Spring + Hibernate
    * Spring + MongoDB

## Objectifs du TP
### Développer un outil de gestion de la qualité des données
Pour cette année, j'ai décidé de vous proposer un sujet autour de la gestion de la qualité des données et de leur visualisation :
* Parser un fichier CSV
* Vérifier son format
* Afficher les méta données du fichier : nombre de colonnes, nombre de lignes, type des lignes, nombre de rangs
* Calculer des indicateurs génériques sur les lignes :
    * liste des valeurs possibles pour les champs catégoriels (ou nombre de valeurs possibles)
    * min-max-mediane, moyenne, variance... pour les valeurs numériques

Ce sujet vous permet de voir que l'on peut, dans certains cas, travailler sur des données sans base de données,
d'aborder les problématiques de persistance en partant de la solution la plus simple (le fichier texte).

### Travailler dans un environnement et méthodes de développement "industriels"
Nous nous efforcerons d'utiliser un environnement et des méthodes de développement "industriels" :
* IDE : eclipse, jetbrain intellij ou ,netbean
* Gestion de versions : git + github
* Test : intégration de Junit
* Build : Maven
* Déploiement en tant que microservice docker
* Suivi de projet / spring pivotal tracker
* Pair programming
* Code review

## Bénéfices attendus pour les participants

* Comprendre spring et acquérir un premier niveau de compétence en développement sur cette technologie
* Prendre de la distance, améliorer sa technique de développement en Java
* Coder!

## Prérequis
* Java
* Compétences minimales en Linux
* Culture générale informatique (http, html, manipulation de données...)

## Evaluation
* Assiduité
* Quizz(s) d'évaluation

## Références
* O'Reilly, "Head first design patterns" (http://shop.oreilly.com/product/9780596007126.do)
* Manning, "Spring in action" (https://www.manning.com/books/spring-in-action-fourth-edition)
* Manning, "Junit in action" (https://www.manning.com/books/junit-in-action-second-edition)
*
* Eyrolles, "Java et Spring - Concevoir, construire et développer une application Java/J2EE avec Spring", (https://www.amazon.fr/Java-Spring-construire-d%C3%A9velopper-application/dp/2746055147)
