package UI;

import competences.Competences;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

/**
 * 
 * @author Benoit Bankaert
 *
 */
public class BoutonCompetence extends Pane/*Button*/ {

	private Competences comp;
	private Image img;
	private Canvas c;
	
	/**
	 * Instancie BoutonCompetence
	 * @param txt Le texte à renseigner dans le bouton
	 */
	public BoutonCompetence(String txt,Image img) {
		super();
		super.setPrefSize(150, 25);
		super.setMaxSize(150, 25);
		this.img = img;
		this.comp = null;
		this.c = new Canvas(200,25);
		this.c.getGraphicsContext2D().drawImage(this.img, 0, 0);
		super.getChildren().add(this.c);
	}
	
	public Canvas getCanvas() {
		return this.c;
	}
	public Image getImage() {
		return this.img;
	}
	
	public void setNewImage(Image img) {
		this.img = img;
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
