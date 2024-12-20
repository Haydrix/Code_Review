#AwesomePasswordChecker

Description

    AwesomePasswordChecker est une application Java conçue pour vérifier la validité des mots de passe en utilisant des centres de clusters. Ce modèle utilise des caractéristiques spécifiques des caractères (majuscule, minuscule, chiffres, symboles) et calcule la distance Euclidienne par rapport à des centres de clusters pour évaluer la similarité avec des mots de passe précédemment validés.

Fonctionnalités

    Lecture des centres de clusters : La classe lit les centres de clusters à partir d'un fichier ou d'une ressource par défaut.
    Masquage des caractères : Génération d'un masque pour un mot de passe en fonction de ses caractères.
    Calcul de distance : Calcul de la distance minimale entre le mot de passe masqué et les centres de clusters pour évaluer la similarité.

Prérequis

    Java Development Kit (JDK) 8 ou supérieur.
    Une connexion internet pour télécharger le fichier de centre de clusters par défaut.

Utilisation

    Initialisation de l'instance
    AwesomePasswordChecker checker = AwesomePasswordChecker.getInstance();
    Vérification de la validité d'un mot de passe
    double distance = checker.getDistance("VotreMotDePasseIci");
    Calculer le hash MD5
    String md5Hash = AwesomePasswordChecker.computeMD5("VotreChaineIci");

Documentation additionnelle

    Security.md : Fichier contenant les politiques de sécurité pour l'utilisation du projet, les pratiques recommandées pour le stockage sécurisé des données, etc.
    Modèles de problèmes : Fichiers préconfigurés pour l'ouverture et la gestion des problèmes sur GitHub.
    Politiques de branches protégées : Paramétrages pour la gestion des branches sur le dépôt GitHub (ex : accès en lecture seule, fusion approuvée par les contributeurs).
    Définir la politique de demande de pull : Étapes pour définir le processus d'approbation des demandes de pull, les exigences de qualité de code, etc.

