# TP2
## Contenu :

* 2ème séance (4h) : Spring Web application
    * Construction d'une application Web
    * Implémentation du MVC
    * Retour sur la partie métier et le wiring
    * Spring Webflow

## Objectifs de l'application Web :

* Un formulaire permettant de télécharger un fichier
    * Choix du type de fichier : csv ou txt
    * Choix du fichier à télécharger
    * Bouton pour démarrer le téléchargement
* Les fichiers téléchargés sont stockés dans un répertoire spécifique
    * A chaque fichier est ajouté un numéro d'ordre en début de fichier sur 4 chiffres
    * Par exemple, si l'on télécharge un fichier test.csv le fichier est renommé en NNNNtest.csv
* A la fin du téléchargement, une page confirmant que le téléchargement s'est bien passé s'affiche
    * La page affiche également les informations concernant le fichier (cf. TP1)
    * La page comporte des liens vers
        * La page permettant de télécharger un nouveau fichier
        * La page permettant de voir la liste des fichiers déjà téléchargés
* Une page permet de visualiser la liste des fichiers déjà téléchargés
    * La page affiche la liste des fichiers sous forme d'un tableau
    * Pour chaque fichier s'affiche les informations concernant le fichier
    
## Réalisation :

Une trame pour démarrer les développements est fournie dans le module TP2Web. Ce module est extrait directement des exemples fournis par spring sur le site https://github.com/mdautrey/TPEnssat2016 .
