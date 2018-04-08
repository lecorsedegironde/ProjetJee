# ProjetJee
Le repo projetJee du groupe 404
## Composition de l'équipe : 
- Valentin Vasseur
- Damien Hamoudi
- Mickaël de Azévédo
- Ewen Baron
## Création de la base de données
Avant de lancer l'application, exécuter ce code sur la base de données MySql

    CREATE DATABASE IF NOT EXISTS jee;  
    CREATE USER 'jee'@'%' IDENTIFIED BY 'projetjee';  
    GRANT USAGE ON *.* TO  'jee'@'%' IDENTIFIED BY  'projetjee' WITH GRANT OPTION;  
    GRANT SELECT , INSERT , UPDATE , CREATE , ALTER , DELETE , DROP ON  `jee`.* TO  'jee'@'%';  
    FLUSH PRIVILEGES;