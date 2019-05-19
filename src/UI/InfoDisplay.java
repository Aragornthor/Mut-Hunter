package UI;

import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;

public class InfoDisplay extends TitledPane{

	private TextArea txt;
	
	public InfoDisplay() {
		super();
		super.setText("Info");
		this.txt = new TextArea("Texte de base");
		this.txt.setEditable(false);
		this.txt.setWrapText(true);
		super.setContent(this.txt);
	}
	
	public void changeText(String txt) {
		this.txt.setText(txt);
	}
}
