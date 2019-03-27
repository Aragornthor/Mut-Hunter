package classes;

import java.util.Random;
/**
 * Cette classe a pour but de gérer et manipuler un terrain, représenter par un tableau à deux dimension.
 * @author Bankaert Benoit
 * @version 1.0
 */
public class Plateau {
	
	/**
	 * Rand permet de génerer des nombres aléatoire.
	 */
	private static Random rand = new Random();
	/**
	 * plateau est un tableau à deux dimension de Case qui représente le terrain de jeu.
	 */
	private Case[][] plateau;
	/**
	 * tours représente le nombre de tours dans la partie en cours.
	 */
	private int tours;
	/**
	 * PORTAIL1 repréente la position dans le terrain du premier portail.
	 */
	private final Position PORTAIL1 = new Position(9,0);
	/**
	 * PORTAIL2 représente la position dans le terrain du second portail.
	 */
	private final Position PORTAIL2 = new Position(0,9);
	/*
	private boolean tourChasseur;
	private boolean tourMonstre;
	*/
	public Plateau() {
		this.plateau = new Case[10][10];
		this.tours = 1;
	}
	
	public void initPlateau() {
		for(int i = 0; i < this.plateau.length; i++) {
			for(int j = 0; j < this.plateau[i].length; j++) {
				this.plateau[i][j] = new Case();
			}
		}
		this.generePortail();
		this.startPersonnage();
		
	}
	private void startPersonnage() {
		this.plateau[0][0].setType(TypeCase.CHASSEUR);
		this.plateau[0][0].setEstChasseur(true);
		this.plateau[this.plateau.length-1][this.plateau[1].length-1].setType(TypeCase.MONSTRE);
		this.plateau[this.plateau.length-1][this.plateau[1].length-1].setEstMonstre(true);
	}
	
	private void generePortail() {
		this.plateau[this.PORTAIL1.getX()][this.PORTAIL1.getY()].setType(TypeCase.PORTAIL);
		this.plateau[this.PORTAIL1.getX()][this.PORTAIL1.getY()].setEstPortail(true);
		this.plateau[this.PORTAIL2.getX()][this.PORTAIL2.getY()].setType(TypeCase.PORTAIL);
		this.plateau[this.PORTAIL2.getX()][this.PORTAIL2.getY()].setEstPortail(true);
	}
	
	public void affichePlateau() {
		System.out.print("═════════════════════════════════════════\nTour n°"+this.tours+"\n╔═══╦═══╦═══╦═══╦═══╦═══╦═══╦═══╦═══╦═══╗\n║");
		for(int i = 0; i < this.plateau.length; i++) {
			for(int j = 0; j < this.plateau[i].length; j++) {
				System.out.print(" "+this.plateau[i][j].getIcon()+" ║");
			}
			if(i+1 == this.plateau.length) System.out.print("\n╚═══╩═══╩═══╩═══╩═══╩═══╩═══╩═══╩═══╩═══╝\n");
			else System.out.print("\n╠═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╣\n║");
		}
		System.out.println("\n═════════════════════════════════════════");
	}
	
	public Case[][] getPlateau(){
		return this.plateau;
	}
	
	public void setPlateau(Case[][] tab) {
		this.plateau = tab;
	}
	
	private int nbLoot() {
		int nb = 0;
		for(int i = 0; i < this.plateau.length; i++)
			for(int j = 0; j < this.plateau[i].length; j++)
				if(this.plateau[i][j].getType() == TypeCase.LOOT)
					nb++;
		return nb;
	}
	
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
	
	public void hideChasseur(Position p) {
		this.plateau[p.getX()][p.getY()].setType(TypeCase.CHASSEUR_HIDE);
	}
	
	public void showChasseur(Position p) {
		this.plateau[p.getX()][p.getY()].setType(TypeCase.CHASSEUR);
	}

	public void hideMonstre(Position p) {
		this.plateau[p.getX()][p.getY()].setType(TypeCase.MONSTRE_HIDE);
	}
	
	public void showMonstre(Position p) {
		this.plateau[p.getX()][p.getY()].setType(TypeCase.MONSTRE);
	}
	
	public int getTours() {
		return this.tours;
	}
	
	public void addTours() {
		this.tours++;
	}
	
	public void setCaseType(Position p,TypeCase type) {
		this.plateau[p.getX()][p.getY()].setType(type);
	}
	
<<<<<<< HEAD
	public int getHauteur() {
=======
	public int geHauteur() {
>>>>>>> 2308c39608dd7cac52ce9252bedc666b7b3b8e49
		return this.plateau.length;
	}
	
	public int getLargeur() {
		return this.plateau[0].length;
	}
	
	public boolean victoireChasseur(Position c, Position m) {
		if(c.equals(m)) return true;
		return false;
	}
	
	public TypeCase getType(Position p) {
		return this.plateau[p.getX()][p.getY()].getType();
	}
	
	public Position teleprtation(Position p) {
		if(p.equals(this.PORTAIL1)) return this.PORTAIL2;
		return this.PORTAIL1;
	}
	
	/*
	public boolean getTourChasseur() {
		return this.tourChasseur;
	}
	
	public void setTourChasseur(boolean b){
		this.tourChasseur = b;
	}
	
	public boolean getTourMonstre() {
		return this.tourMonstre;
	}
	
	public void setTourMonstre(boolean b){
		this.tourMonstre = b;
	}	
	*/
}
