package UI;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
/**
 * 
 * @author Bankaert Benoit
 * @version 1.0
 */
public class EnergyBar extends HBox {
	
	private final double MAX_ENERGY = 100;
	private final double MAX_LENGTH = 200;
	private final double MULTIPLICATEUR = this.MAX_LENGTH / this.MAX_ENERGY;
	private final Rectangle BLANK;
	private final Rectangle BORDER_REC;
	private Label energyValue;
	private Rectangle energy;
	
	/**
	 * Instancie les differents rectangles qui composent la barre d'énergie
	 */
	public EnergyBar() {
		this.energy = new Rectangle();
		this.BLANK = new Rectangle();
		this.BORDER_REC = new Rectangle();
	}
	
	/**
	 * Créer et parametres les rectangles pour former la barre d'énergie
	 * @return une HBox qui comporte la barre d'énergie et un label qui corespond a la valeur d'énergie
	 */
	public HBox getEnergyBar() {
		Pane energyBar = new Pane();
		this.energy.setFill(Color.DEEPSKYBLUE);
		this.energy.setX(10);
		this.energy.setY(1);
		this.energy.setWidth(this.MAX_LENGTH);
		this.energy.setHeight(25);

		this.BORDER_REC.setFill(Color.BLACK);
		this.BORDER_REC.setX(this.energy.getX()-1);
		this.BORDER_REC.setY(this.energy.getY()-1);
		this.BORDER_REC.setWidth(this.MAX_LENGTH+2);
		this.BORDER_REC.setHeight(this.energy.getHeight()+2);

		this.BLANK.setFill(Color.WHITE);
		this.BLANK.setX(this.energy.getX());
		this.BLANK.setY(this.energy.getY());
		this.BLANK.setWidth(this.MAX_LENGTH);
		this.BLANK.setHeight(this.energy.getHeight());
		
		this.energyValue = new Label(""+this.energy.getWidth()/this.MULTIPLICATEUR);
		this.energyValue.setTextFill(Color.WHITE);
		
		energyBar.getChildren().addAll(this.BORDER_REC,this.BLANK,this.energy);
		
		HBox root = new HBox();
		
		root.getChildren().addAll(energyBar,this.energyValue);
		
		HBox.setMargin(this.energyValue, new Insets(5));		
		
		
		return root;
	}
	
	/**
	 * Change la taille du rectangle d'énergie
	 * @param d est la valeur de l'énergie
	 */
	public void setEnergyWidth(double d) {
		this.energy.setWidth(d*this.MULTIPLICATEUR);
		this.energyValue.setText(""+d);
		this.colorRec();
	}
	
	/**
	 * 
	 * @return le largeur du rectangle d'énergie
	 */
	public double getEnergyWidth() {
		return this.energy.getWidth();
	}
	
	/**
	 * 
	 * @return le rectangle d'énergie
	 */
	public Rectangle getRectangle() {
		return this.energy;
	}
	
	/**
	 * Dimminue le niveau d'énergie et change la couleur d'énergie si les seuils sont atteint :
	 *  vert pour le niveau max, bleu pur 75% restant, orange pour 50% restant et rouge pour 20% restant. 
	 * @param perte est la perte d'énergie subit
 	 */
	public void perdreEnergy(double perte) {
		if(this.energy.getWidth() - perte*this.MULTIPLICATEUR > 0) {
			this.energy.setWidth(this.energy.getWidth()-perte*this.MULTIPLICATEUR);
		}else {
			this.energy.setWidth(0);
		}
		this.energyValue.setText(""+this.energy.getWidth()/this.MULTIPLICATEUR);
		this.colorRec();
	}
	
	/**
	 * Augement le niveau d'énergie et change la couleur d'énergie si les seuils sont atteint :
	 *  vert pour le niveau max, bleu pur 75% restant, orange pour 50% restant et rouge pour 20% restant. 
	 * @param gain est le gain d'énergie subit
 	 */
	public void gainEnergy(double gain) {
		if(this.energy.getWidth()+gain*this.MULTIPLICATEUR < this.MAX_LENGTH) {
			this.energy.setWidth(this.energy.getWidth()+gain*this.MULTIPLICATEUR);
		}else {
			this.energy.setWidth(MAX_LENGTH);
		}
		this.energyValue.setText(""+this.energy.getWidth()/this.MULTIPLICATEUR);
		this.colorRec();
	}
	
	/**
	 * Changer la couleur de la barre d'énergie en fonction de sa valeur
	 */
	private void colorRec() {
		if(this.energy.getWidth()/this.MULTIPLICATEUR <= this.MAX_ENERGY*0.2) {
			this.energy.setFill(Color.RED);
		}else if(this.energy.getWidth()/this.MULTIPLICATEUR <= this.MAX_ENERGY*0.5) {
			this.energy.setFill(Color.ORANGE);
		}else if(this.energy.getWidth()/this.MULTIPLICATEUR <= this.MAX_ENERGY*0.75) {
			this.energy.setFill(Color.DEEPSKYBLUE);
		}else {
			this.energy.setFill(Color.LIMEGREEN);
		}
	}
	
}
