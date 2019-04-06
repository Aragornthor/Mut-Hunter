package competences;

/**
 * 
 * La classe Piege est une competence chasseur permettant de creer un piege
 * @author Quentin Delmarre, Robin Gallifa
 *
 */
public class Piege extends Competences {

	/**
	 * Constructeur de la compétence Piege
	 */
	public Piege() {
		this.setId(3);
		this.setElement("deplacement");
		this.setEffet(0);
		this.setDuree(0);
	}
}
