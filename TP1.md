# TP1
## Présentation
### Contenu :
* 1ère séance (2h) : Injection de dépendance
    * Présentation du framework
    * Design pattern : exemple
    * Injection de dépendance avec Spring
    * Différents modules du framework

### Initialisation des outils
* Environnement de développement + maven + git + github + pivotal tracker
* Compilation d'un premier code simple (sans spring)
* Compilation d'un premier code simple (avec spring)
* Présentation de Spring 
* Mise en place de l'environnement de test et écriture des premiers tests

## Présentation du framework
Voir support + notes au tableau

## Exemple de design pattern
Voir le code joint à compléter dans le module HeadFirst
### Version simpliste
Trois objets :
* FileInfo
* CSVInfo
* TxtInfo

FileInfo est une classe abstraite avec une méthode getNRows et un attribut, fileName.

Les deux classes CSV et Txt implémentent une méthode getNRows qui renvoie sous forme d'entier le nombre de lignes du fichier.

A faire :
* Mettre en place un POM
* Mettre en place l'arborescence du projet
* Mettre en place la gestion de version avec git
* Implémenter les tests unitaires des classes CSVInfo et TxtInfo
* Développer l'ensemble
* Ajouter une classe App avec une méthode main pour tester = setter les deux objets + faire un ou deux print

### Un peu de design

Question : comment fait-on si l'on a besoin d'ajouter une classe JpegInfo pour laquelle le nombre de rangs n'a pas
de sens ?

Nous souhaitons appliquer les règles de design de Java (voir le livre Head First Design Patterns) :
* OO Basics
    * Abstraction
    * Encapsulation
    * Polymorphisme
    * Inheritance
* OO Principles
    * Encapsulate what varies
    * Favor composition over inheritance
    * Program to interfaces, not implementations
    
On décide donc d'introduire une interface FileSize qui contient la méthode getNRows et de factoriser le code 
à partir de cette idée.

A faire :
* Mettre en place l'interface
* Revoir l'organisation du code en conséquence : quel est le contrat entre les objets dérivés de FileInfo et FileInfo....?
* Finaliser et tester
* Quelle est la limite de la solution actuelle ?

### Injection de dépendance
Pour aller plus loin dans le sens de la composition et de la flexibilité, nous décidons d'utiliser le mécanisme d'injection de 
dépendance proposé par Spring
A faire :
* Modifier le code main pour créer un contexte d'exécution Spring
* Ajouter un fichier de configuration spring pour réaliser la déclaration des beans et l'injection de dépendance
* Construire et tester l'ensemble

