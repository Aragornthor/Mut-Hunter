package competences;

/**
 * 
 * La classe Saut est une competence du monstre permettant au monstre d'assomer le chasseur pendant 2 tour ce qui l'immobilise
 * @author Quentin Delmarre, Robin Gallifa
 *
 */
public class Saut implements Competences {
	private int id = 4;
	private String element;
	private int effet;
	private int duree;
	
	public Saut() {
		this.element = "deplacement";
		this.effet = 0; //Immobilisation -> 2 tours
		this.duree = 2;
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
		return "["+this.element+":"+this.effet+":"+this.duree+"]";
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
