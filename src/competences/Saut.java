package competences;

/**
 * 
 * La classe Saut est une competence du monstre permettant au monstre d'assomer le chasseur pendant 2 tour ce qui l'immobilise
 * @author Quentin Delmarre, Robin Gallifa
 *
 */
public class Saut extends Competences {

	/**
	 * Constructeur de la compétence Saut
	 */
	public Saut() {
		this.setId(4);
		this.setElement("deplacement");
		this.setEffet(0); //Immobilisation -> 2 tours
		this.setDuree(2);
	}
}
