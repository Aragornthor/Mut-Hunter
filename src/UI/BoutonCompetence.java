package UI;

import competences.Competences;
import javafx.scene.control.Button;

/**
 * 
 * @author Benoit Bankaert
 *
 */
public class BoutonCompetence extends Button {

	private Competences comp;
	
	/**
	 * Instancie BoutonCompetence
	 * @param txt Le texte à renseigner dans le bouton
	 */
	public BoutonCompetence(String txt) {
		super(txt);
		this.comp = null;
	}
	
	/**
	 * 
	 * @return Renvoie la compétence associée au bouton
	 */
	public Competences getComp() {
		return this.comp;
	}
	
	/**
	 * Permet de changer la compétence associée au bouton
	 * @param c La nouvelle compétence à associer
	 */
	public void setComp(Competences c) {
		this.comp = c;
	}
}
