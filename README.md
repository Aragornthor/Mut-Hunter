# Projet du Second Semestre du DUT Informatique de l'IUT A de Lille : Mut-Hunter

> # Sujet
>
> Pour le projet du second semestre, vous allez développer un ludogiciel appelé la chasse au monstre (CaM), dont les
règles sont les suivantes :
Le monstre et le chasseur sont sur un espace identique découpé en cases. Dans un premier temps, on peut
supposer que c’est une grille carrée (mais ce n’est pas très important, ça pourrait être une grille
rectangulaire, une île aux formes mal définies, un tore, on pourrait y ajouter des obstacles, pourquoi pas des
étages, des pièges, des passages secrets...).
Le but du jeu pour le chasseur est de trouver le monstre, pour le monstre d’avoir exploré chaque case de
l’espace de jeu sans avoir été trouvé. Un tour de jeu consiste en un coup du monstre suivi d’un coup du
chasseur.
Le monstre débute la partie : il choisit une case dans l’espace de jeu, et marque la case avec un 1 (qui
signifie : j’étais sur cette case lors du premier tour de jeu). Ensuite, à chaque tour de jeu, le monstre a
l’obligation de se déplacer sur une case adjacente non explorée, et de marquer cette case avec l’entier
correspondant au tour de jeu. Une case adjacente est une case située autour de la case du monstre
(au-dessus, en-dessous, à droite, gauche, ou en diagonale). Si le monstre ne peut se déplacer lors de son
tour de jeu, il a perdu la partie.
Le chasseur propose une des cases de l’espace de jeu. Il apprend l’état de la case : jamais explorée, explorée
lors du nième tour de jeu, occupée par le monstre. Le chasseur peut ainsi, peu à peu, retracer l’itinéraire du
monstre et le débusquer. Le chasseur a gagné s’il trouve le monstre. Il perd si le monstre a exploré tout
l’espace de jeu sans avoir été trouvé.
Il vous est demandé de réaliser une version électronique de ce jeu. Les fonctionnalités minimales requises pour le
logiciel sont :
— une ergonomie qui dépend des joueurs (IA, humains) ;
— une IHM pour contrôler les différents personnages (chasseur, monstre) ;
— de multiples versions des règles (déplacements, plateau de jeu, retour d’information) ;
— gérer une IA pour chaque personnage (chasseur, monstre).
Les travaux se font par équipes de 4 étudiants (ou 3 ou 5, selon la taille des groupes TD) avec cette contrainte que
chaque équipe se constitue à l’intérieur d’un unique groupe TD. Ce projet contribuera à vos notes de Projet et
Gestion de Projets.
>
> ***Sujet produit par l'IUT A de Lille.***

## Notre projet

Dans un premier temps, nous avons réalisé un « Brain-Storming » afin de progresser rapidement sur le développemnt à venir !
Nous sommes arrivés à une répartition des rôles pour réaliser la réponse suivante à l'énoncé.

Nous avons prévu de créer un **Type** *Personnage* caractérisé par son type, son niveau actuel et maximum d'énergie, ses deux compétences, sa *Position*, sa vitesse et son statut (vivant ou mort).

Ce *Personnage* se déplaçant, ainsi sur un (**Type**) *Plateau* peut être soit un Monstre devant dévcouvrir toutes les *Case*s du *Plateau* sans repasser sur des cases déjà découvertes, le cas échéant, le Chasseur remporte. Ce dernier peut, par ailleurs, trouver le monstre en suivant sa trace. Une fois qu'il a découvert le Monstre, la partie est terminée, sur la ***VICTOIRE*** du Chasseur