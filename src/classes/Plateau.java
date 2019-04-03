package classes;

import java.util.Random;
/**
 * Cette classe a pour but de gérer et manipuler un terrain, représenter par un tableau à deux dimensions.
 * @author Bankaert Benoit
 * @version 1.0
 */
public class Plateau {
	
	/**
	 * Rand permet de générer des nombres aléatoires.
	 */
	private static Random rand = new Random();
	/**
	 * plateau est un tableau à deux dimensions de Case qui représente le terrain de jeu.
	 */
	private Case[][] plateau;
	/**
	 * tours représente le nombre de tours dans la partie en cours.
	 */
	private int tours;
	/**
	 * PORTAIL1 représente la position dans le terrain du premier portail.
	 */
	private final Position PORTAIL1 = new Position(9,0);
	/**
	 * PORTAIL2 représente la position dans le terrain du second portail.
	 */
	private final Position PORTAIL2 = new Position(0,9);
	/**
	 * tourChasseur indique si c'est au chasseur de jouer.
	 */
	private boolean tourChasseur;
	/**
	 * tourMonstre indique si c'est au monstre de jouer.
	 */
	private boolean tourMonstre;
	/**
	 * Permet de donner au joueur different bonus quand il recupère un loot
	 */
	private Loot loot;
	
	/**
	 * On instancie un terrain de 10 par 10 et on règle le nombre de tour sur 1.
	 */
	public Plateau() {
		this.plateau = new Case[10][10];
		this.tours = 1;
		this.loot = new Loot();
	}
	
	/**
	 * Permet d'initialise les cases de tout le terrain et on place les deux joueurs et les deux portails.
	 */
	public void initPlateau() {
		for(int i = 0; i < this.plateau.length; i++) {
			for(int j = 0; j < this.plateau[i].length; j++) {
				this.plateau[i][j] = new Case();
			}
		}
		this.generePortail();
		
	}
	public void startPersonnage(Personnage chasseur, Personnage monstre) {
		
		this.plateau[chasseur.getPosition().getX()][chasseur.getPosition().getY()].setEstChasseur(true);
		this.plateau[monstre.getPosition().getX()][monstre.getPosition().getY()].setEstMonstre(true);
	}
	
	private void generePortail() {
		this.plateau[this.PORTAIL1.getX()][this.PORTAIL1.getY()].setEstPortail(true);
		this.plateau[this.PORTAIL2.getX()][this.PORTAIL2.getY()].setEstPortail(true);
	}
	
	/**
	 * Permet d'afficher le terrain.
	 * @param p prend un personnage en paramètre
	 */
	public void affichePlateau(Personnage p) {
		clearScreen();
		System.out.print("═════════════════════════════════════════\nTour n°"+this.tours+"\n╔═══╦═══╦═══╦═══╦═══╦═══╦═══╦═══╦═══╦═══╗\n║");
		for(int i = 0; i < this.plateau.length; i++) {
			for(int j = 0; j < this.plateau[i].length; j++) {
				System.out.print(" "+this.plateau[i][j].getIcon()+" ║");
			}
			if(i+1 == this.plateau.length) System.out.print("\n╚═══╩═══╩═══╩═══╩═══╩═══╩═══╩═══╩═══╩═══╝\n");
			else System.out.print("\n╠═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╣\n║");
		}
		System.out.println(p.toString());
		
		System.out.println("\n═════════════════════════════════════════");
	}
	
	/**
	 * 
	 * @return le terrain.
	 */
	public Case[][] getPlateau(){
		return this.plateau;
	}
	 /**
	  * Remplace le terrain par le terrain passé en parametre.
	  * @param tab est le nouveau terrain.
	  */
	public void setPlateau(Case[][] tab) {
		this.plateau = tab;
	}
	
	private int nbLoot() {
		int nb = 0;
		for(int i = 0; i < this.plateau.length; i++)
			for(int j = 0; j < this.plateau[i].length; j++)
				if(this.plateau[i][j].getLoot())
					nb++;
		return nb;
	}
	
	/**
	 * Ajoute de manière aleatoire un nombre de loot passé en parametre sur le terrain.
	 * @param loot est le nombre de loot que l'on souhaite ajouter.
	 */
	public void ajoutLoot(int loot) {
		if(loot+this.nbLoot() > 2) loot = 2 - this.nbLoot();
		for(int i = 0; i < loot ; i++) {
			if(this.nbLoot() < 2) {
				int x,y;
				do {
					x = rand.nextInt(this.plateau.length);
					y = rand.nextInt(this.plateau[0].length);
				}while(this.plateau[x][y].getLoot() && this.plateau[x][y].getEstPortail() && this.plateau[x][y].getEstMonstre() && this.plateau[x][y].getEstChasseur());
				
				this.plateau[x][y].changeLoot();
			}
		}
	}
	
	/**
	 * 
	 * @return le bonus actuel
	 */
	public Loot getLoot() {
		return this.loot;
	}
	
	/**
	 * Remplace le loot par celui passÃ© en parametre.
	 * @param l  est le loot que l'on souhait avoir
	 */
	public void setLoot(Loot l) {
		this.loot = l;
	}
	
	public void ajoutCompetence(Personnage p) {
		this.loot.changeCompetence(p);
	}
	
	/**
	 * 
	 * @return le nombre de tour Ã©couler depuis le debut de la partie.
	 */
	public int getTours() {
		return this.tours;
	}
	
	/**
	 * Augement le nombre de tour d'un.
	 */
	public void addTours() {
		this.tours++;
	}
	
	/**
	 * Rend la case dont la position lui est donnée en paramètre normale.
	 * @param p est la position dans le terrain que l'on souhaite éditer
	 */
	public void setCaseNormal(Position p) {
		this.plateau[p.getX()][p.getY()].setEstChasseur(false);
		this.plateau[p.getX()][p.getY()].setEstMonstre(false);
		this.plateau[p.getX()][p.getY()].setEstPortail(false);
		this.plateau[p.getX()][p.getY()].setLoot(false);
	}
	
	/**
	 * Rend la case dont la position lui est donnée en paramètre normale sauf le fait que ce soit un portail.
	 * @param p est la position dans le terrain que l'on souhaite éditer
	 */
	public void setCaseNormalPortail(Position p) {
		this.plateau[p.getX()][p.getY()].setEstChasseur(false);
		this.plateau[p.getX()][p.getY()].setEstMonstre(false);
		this.plateau[p.getX()][p.getY()].setEstPortail(true);
		this.plateau[p.getX()][p.getY()].setLoot(false);
	}
	
	/**
	 * @return la hauteur du terrain
	 */
	public int getHauteur() {
		return this.plateau.length;
	}
	
	/**
	 * @return la largeur du terrain
	 */
	public int getLargeur() {
		return this.plateau[0].length;
	}
	
	/**
	 * Verifie si le chasseur a gagné la partie.
	 * @param c est la position du chasseur
	 * @param m est la position du monstre
	 * @return true si le chasseur se trouve à la même position que le monstre
	 */
	public boolean victoireChasseur(Position c, Position m) {
		if(c.equals(m)) return true;
		return false;
	}
	
	/**
	 * @param p est la position de la case voulue
	 * @return la case passé en parametre
	 */
	public Case getCase(Position p) {
		return this.plateau[p.getX()][p.getY()];
	}
	
	/**
	 * Permet au joueur de se téléporter d'un portail à un autre si la position passée en paramètre est la position d'un portail.
	 * @param p est la position du joueur
	 * @return la position d'un portail
	 */
	public Position teleportation(Position p) {
		if(p.equals(this.PORTAIL1)) return this.PORTAIL2;
		return this.PORTAIL1;
	}
	
	/**
	 * @return true si c'est au chasseur de jouer, false sinon
	 */
	public boolean getTourChasseur() {
		return this.tourChasseur;
	}
	
	/**
	 * Change le statut de tourChasseur avec le booléen passé en paramètre.
	 * @param b est le nouveau statut de tourChasseur
	 */
	public void setTourChasseur(boolean b){
		this.tourChasseur = b;
	}
	
	/**
	 * @return true si c'est au monstre de jouer, false sinon
	 */
	public boolean getTourMonstre() {
		return this.tourMonstre;
	}
	
	/**
	 * Change le statut de tourMonstre avec le booléen passé en paramètre. 
	 * @param b est le nouveau statut de tourMonstre
	 */
	public void setTourMonstre(boolean b){
		this.tourMonstre = b;
	}
	
	private static void clearScreen() {
		try {
			if(System.getProperty("os.name" ).startsWith("Windows")) {
				Runtime.getRuntime().exec("cls" );
			} else {
			    Runtime.getRuntime().exec("clear" );
			}
		} catch(Exception e) {
			for(int i = 0; i < 100; i++) {
				System.out.println();
			}
		}
	}
}