package UI;

import competences.Competences;
import javafx.scene.control.Button;

public class BoutonCompetence extends Button{

	private Competences comp;
	
	public BoutonCompetence(String txt) {
		super(txt);
		this.comp = null;
	}
	
	public Competences getComp() {
		return this.comp;
	}
	
	public void setComp(Competences c) {
		this.comp = c;
	}
}
