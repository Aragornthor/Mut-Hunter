package UI;

import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;

public class InfoDisplay extends TitledPane{

	private TextArea txt;
	private String txtBase;
	
	public InfoDisplay() {
		super();
		super.setText("Info");
		this.setTxtBase(0, false, 0);
		this.txt = new TextArea(this.txtBase);
		this.txt.setEditable(false);
		this.txt.setWrapText(true);
		super.setContent(this.txt);
	}
	
	public void changeText(String txt) {
		this.txt.setText(txt);
	}
	
	public void setTxtBase(int nbTours, boolean estMonstre, int nbCaseDecouvert) {
		this.txtBase = "Tour n°"+nbTours;
		if(estMonstre) {
			this.txtBase+="\nNombre de case découverte : "+nbCaseDecouvert;
		}
	}
	
	public String getTxtBase() {
		return this.txtBase;
	}
}
