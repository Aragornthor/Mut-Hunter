package competences;


/**
 * 
 * La classe Acide est une competence du monstre faisant perdre de l'energie au chasseur pendant 3 tours
 * @author Quentin Delmarre, Robin Gallifa
 *
 */
public class Acide implements Competences {
	private int id = 0;
	private String element;
	private int effet;
	private int duree;
	
	public Acide() {
		this.element = "energie";
		this.effet = -10; //Perte d'énergie (nettoyage) -> 3 tours
		this.duree = 3;
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
