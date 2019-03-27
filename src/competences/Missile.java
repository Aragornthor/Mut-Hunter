package competences;

/**
 * 
 * La classe Missile est une competence chasseur permettant de tuer instantanement le monstre le faisant gagner la partie
 * @author Quentin Delmarre, Robin Gallifa
 *
 */
public class Missile implements Competences {
	private int id = 2;
	private String element;
	private int effet;
	private int duree;
	
	public Missile() {
		this.element = "vie";
		this.effet = -1; //Mort instantanée
		this.duree = 0;
	}

	/**
	 * 
	 * @return Retourne l'element de la competence
	 */
	public String getElement() {
		return element;
	}

	/**
	 * 
	 * @return Retourne l'effet de la competence
	 */
	public int getEffet() {
		return effet;
	}

	/**
	 * 
	 * @return Retourne la duree de la competence
	 */
	public int getDuree() {
		return duree;
	}
	
	/**
	 * 
	 * @return Retourne l'id de la competence
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * 
	 * @return Retourne sous forme textuelle la competence 
	 */
	@Override
	public String toString() {
		return "["+this.id+":"+this.element+":"+this.effet+":"+this.duree+"]";
	}
	
	/**
	 * 
	 * @return Retourne vrai si la competence donnee en parametre est la meme que celle de la classe actuelle, sinon faux
	 */
	@Override
	public boolean equals(Competences c) {
		return this.id == c.getId();
	}
}
