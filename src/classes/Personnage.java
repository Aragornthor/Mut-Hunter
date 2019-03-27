package classes;

import java.util.Scanner;

import competences.Competences;
import competences.Shield;

/**
 * 
 * La classe Personnage permet d'instancier des personnages telle qu'un monstre ou un chasseur
 * @author Quentin Delmarre, Xavier Lezzoche
 *
 */
public class Personnage {
	private String type;
	private int energie;
	private int maxEnergie;
	private Position position;
	private Competences[] competences;
	private int deplacement;
	private int vie;
	
	public Personnage() {
		this.energie = 75;
		this.position = new Position(9,9);
		this.competences = new Competences[] {new Shield(), null};
		this.deplacement = 1;
		this.vie = 1;
	}
	
	/**
	 * 
	 * @return Retourne le niveau d'énergie du personnage
	 */
	public int getEnergie() {
		return energie;
	}

	/**
	 * 
	 * @return Retourne le niveau maximum du personnage
	 */
	public int getMaxEnergie() {
		return maxEnergie;
	}

	/**
	 * 
	 * @return Retourne la position du personnage sur le Plateau
	 */
	public Position getPosition() {
		return position;
	}

	/**
	 * 
	 * @return Retourne la liste de compétences du personnage
	 */
	public Competences[] getCompetences() {
		return competences;
	}

	/**
	 * 
	 * @return Retourne la vitesse de déplacement du personnage
	 */
	public int getDeplacement() {
		return deplacement;
	}

	/**
	 * 
	 * @return Retourne le statut vitale du personnage
	 */
	public int getVie() {
		return vie;
	}
	
	/**
	 * 
	 * @param p Prend un plateau en paramètre
	 */
	public void seDeplace(Plateau p) {
		while(!estDeplace(p)) {
			System.out.println("Vous ne pouvez pas vous d�placer ici.");
		}
	}
	
	/**
	 * 
	 * @param p Prend un plateau en paramètre
	 * @return Retourne VRAI si le mouvement est effectué, sinon FAUX
	 */
	private boolean estDeplace(Plateau p) {
		Scanner reader = new Scanner(System.in);
		String entree = reader.next();
		reader.close();
		if(entree.equals("z")) { //SE DEPLACE VERS LE HAUT
			if(this.position.getY()-1<0) return false;
			else {
				this.position.setY(this.position.getY()-1);
				return true;
			}
		}
		if(entree.equals("s")) { //SE DEPLACE VERS LE BAS
			if(this.position.getY()+1>= p.getHauteur()) return false;
			else {
				this.position.setY(this.position.getY()+1);
				return true;
			}
		}
		if(entree.equals("q")) { //SE DEPLACE VERS LA GAUCHE
			if(this.position.getX()-1<0) return false;
			else {
				this.position.setX(this.position.getX()-1);
				return true;
			}
		}
		if(entree.equals("d")) { //SE DEPLACE VERS LA DROITE
			if(this.position.getX()+1>=p.getLargeur()) return false;
			else {
				this.position.setX(this.position.getX()+1);
				return true;
			}
		}
		return false;
	}

	public void rechargeEnergie() {
		rechargeEnergie(10);
	}
	
	/**
	 * 
	 * @param i Prend en paramètre la quantité d'énergie à ajouter
	 */
	public void rechargeEnergie(int i) {
		if((this.energie + i) > maxEnergie) this.energie = maxEnergie;
		else this.energie += i;
	}

	/**
	 * 
	 * @return Retourne le type du personnage
	 */
	public String getType() {
		return type;
	}
}
