package competences;

import classes.Personnage;
import classes.Plateau;

/**
 * 
 * L'interface Competences donne toutes les methodes propres aux competences
 * @author Quentin Delmarre, Robin Gallifa
 *
 */

/**
 * 
 * La classe Compétence est une classe abstraite regroupant les attributs et les méthodes communs aux compétences
 * @author Quentin Delmarre, Robin Gallifa
 *
 */
public abstract class Competences {
	
	private int id;
	private String nom;
	private int duree;
	protected int cout;
	
	/**
	 * 
	 * @return Retourne l'element de la competence
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * 
	 * @return Retourne la duree de la competence
	 */
	public int getDuree() {
		return duree;
	}
	
	public int getCout() {
		return this.cout;
	}
	
	/**
	 * 
	 * @return Retourne l'id de la competence
	 */
	public int getId() {
		return id;
	}
	
	
	
	public void setId(int id) {
		this.id = id;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public void setDuree(int duree) {
		this.duree = duree;
	}
	

	/**
	 * 
	 * @return Retourne sous forme textuelle la competence 
	 */
	@Override
	public String toString() {
		return "["+this.id+":"+this.nom+":"+this.duree+"]";
	}
	
	/**
	 * 
	 * @return Retourne vrai si la competence donnee en parametre est la meme que celle de la classe actuelle, sinon faux
	 */
	public boolean equals(Competences c) {
		return this.id == c.getId();
	}
	
	public abstract void utilisation(Plateau p, Personnage perso, Personnage cible);
}