package competences;

/**
 * 
 * La classe Shield est une competence du monstre permettant d'etre invulnerable pendant 1 tour
 * @author Quentin Delmarre, Robin Gallifa
 *
 */
public class Shield implements Competences {
	private int id = 5;
	private String element;
	private int effet;
	private int duree;
	
	public Shield() {
		this.element = "protection";
		this.effet = 1; //Protection totale -> 1 tour
		this.duree = 1;
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
