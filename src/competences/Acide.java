package competences;


/**
 * 
 * La classe Acide est une competence du monstre faisant perdre de l'energie au chasseur pendant 3 tours
 * @author Quentin Delmarre, Robin Gallifa
 *
 */
public class Acide extends Competences {
	
	/**
	 * Constructeur de la compétence Acide
	 */
	public Acide() {
		this.setId(0);
		this.setElement("énergie");
		this.setEffet(-10); //Perte d'énergie (nettoyage) -> 3 tours
		this.setDuree(3);
	}
}
