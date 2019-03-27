package classes;

import java.util.Scanner;

import competences.Competences;
import competences.IEM;
import competences.Shield;

/**
 * 
 * La classe Personnage permet d'instancier des personnages telle qu'un monstre ou un chasseur
 * @author Quentin Delmarre, Xavier Lezzoche
 *
 */
public class Personnage {
	static Scanner reader = new Scanner(System.in);
	private String type;
	private int energie;
	private int maxEnergie;
	private Position position;
	private Competences[] competences;
	private int deplacement;
	private int vie;
	
	/**
	 * Constructeur du Personnage avec des paramètres prédéfinis
	 * @param type Prend en paramètre le type du personnage
	 */
	public Personnage(String type,Position p) {
		this.type = type;
		this.energie = 75;
		this.position = p;
		if(type == "chasseur") this.competences = new Competences[] {new IEM(), null};
		else this.competences = new Competences[] {new Shield(), null};
		this.deplacement = 1;
		this.vie = 1;
		this.maxEnergie = 100;
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
	 * Remplace la position du joueur par la position passé en parametre.
	 * @param p est la nouvelle position
	 */
	public void setPosition(Position p) {
		this.position = p;
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
		String entree = reader.nextLine();
		if(entree.equals("q")) { //SE DEPLACE VERS LA GAUCHE
			if(this.position.getY()-1<0) return false;
			else {
				p.setCaseType(this.position, TypeCase.NORMAL);
				this.position.setY(this.position.getY()-1);
				changeCase(p);
				return true;
			}
		}
		if(entree.equals("d")) { //SE DEPLACE VERS LA DROITE
			if(this.position.getY()+1>= p.getHauteur()) return false;
			else {
				p.setCaseType(this.position, TypeCase.NORMAL);
				this.position.setY(this.position.getY()+1);
				changeCase(p);
				return true;
			}
		}
		if(entree.equals("z")) { //SE DEPLACE VERS LE HAUT
			if(this.position.getX()-1<0) return false;
			else {
				p.setCaseType(this.position, TypeCase.NORMAL);
				this.position.setX(this.position.getX()-1);
				changeCase(p);
				return true;
			}
		}
		if(entree.equals("s")) { //SE DEPLACE VERS LE BAS
			if(this.position.getX()+1>=p.getLargeur()) return false;
			else {
				p.setCaseType(this.position, TypeCase.NORMAL);
				this.position.setX(this.position.getX()+1);
				changeCase(p);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Change la case où se trouve le personnage
	 * @param p Prend un plateau en paramètre
	 */
	private void changeCase(Plateau p) {
		if(this.type.equals("monstre")) p.setCaseType(this.position, TypeCase.MONSTRE);
		else if(this.type.equals("chasseur")) p.setCaseType(this.position, TypeCase.CHASSEUR);
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
	
	/**
	 * Permet de changer l'une des deux competences du personnage avec la competence passee en parametre
	 * @param comp Competence que l'on veut mettre pour le personnage
	 * @param nb Nombre de la competence dans le tableau (soit 0 ou 1)
	 */
	public void setCompetence(Competences comp, int nb) {
		this.competences[nb] = comp;
	}
	
	/**
	 * @return le joueur en version texte
	 */
	public String toString() {
		String res = this.type+" : "+this.energie+"/"+this.maxEnergie+"\nCompetences : \n";
		for(Competences c:this.competences) {
			if(c != null) res += c.toString()+"\n";
		}
		return res;
	}
}
