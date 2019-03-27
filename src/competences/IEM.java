package competences;

/**
 * 
 * La classe IEM est une competence du chasseur immobilisant le monstre pendant 1 tour
 * @author Quentin Delmarre, Robin Gallifa
 *
 */
public class IEM  implements Competences {
	private int id = 1;
	private String element;
	private int effet;
	private int duree;
	
	public IEM() {
		this.element = "deplacement";
		this.effet = 0;
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
