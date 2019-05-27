package UI;

import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;

public class InfoDisplay extends TitledPane{

	private TextArea txt;
	private String txtBase;
	
	/**
	 * Instancie InfoDisplay
	 */
	public InfoDisplay() {
		super();
		super.setText("Info");
		this.setTxtBase(0, false, 0);
		this.txt = new TextArea(this.txtBase);
		this.txt.setEditable(false);
		this.txt.setWrapText(true);
		super.setContent(this.txt);
	}
	
	/**
	 * Changer le contenu de description
	 * @param txt Le nouveau contenu
	 */
	public void changeText(String txt) {
		this.txt.setText(txt);
	}
	
	/**
	 * Initialiser le contenu de description par défaut
	 * @param nbTours Le nombre de tours joués par l'entité en jeu
	 * @param estMonstre TRUE si c'est le tour du Monstre sinon FALSE
	 * @param nbCaseDecouvert Le nombre de cases découvertes par le Monstre
	 */
	public void setTxtBase(int nbTours, boolean estMonstre, int nbCaseDecouvert) {
		this.txtBase = "Tour n°"+nbTours;
		if(estMonstre) {
			this.txtBase+="\nNombre de case découverte : "+nbCaseDecouvert;
		}
	}
	
	/**
	 * 
	 * @return Renvoie le texte de Base
	 */
	public String getTxtBase() {
		return this.txtBase;
	}
}
