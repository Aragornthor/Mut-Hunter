package UI;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class EnergyBar extends HBox {
	
	private final double MAX_ENERGY = 100;
	private final double MAX_LENGTH = 200;
	private final double MULTIPICATEUR = this.MAX_LENGTH / this.MAX_ENERGY;
	private Label energyValue;
	private Rectangle energy;
	
	public EnergyBar() {
		this.energy = new Rectangle();
	}
	
	public HBox getEnergyBar() {
		Pane energyBar = new Pane();
		this.energy.setFill(Color.DEEPSKYBLUE);
		this.energy.setX(10);
		this.energy.setY(1);
		this.energy.setWidth(this.MAX_LENGTH);
		this.energy.setHeight(25);

		Rectangle r2 = new Rectangle();
		r2.setFill(Color.BLACK);
		r2.setX(this.energy.getX()-1);
		r2.setY(this.energy.getY()-1);
		r2.setWidth(this.MAX_LENGTH+2);
		r2.setHeight(this.energy.getHeight()+2);

		Rectangle r3 = new Rectangle();
		r3.setFill(Color.WHITE);
		r3.setX(this.energy.getX());
		r3.setY(this.energy.getY());
		r3.setWidth(this.MAX_LENGTH);
		r3.setHeight(this.energy.getHeight());
		
		this.energyValue = new Label(""+this.energy.getWidth()/this.MULTIPICATEUR);
		
		energyBar.getChildren().addAll(r2,r3,this.energy);
		
		HBox root = new HBox();
		
		root.getChildren().addAll(energyBar,this.energyValue);
		
		HBox.setMargin(this.energyValue, new Insets(5));		
		
		return root;
	}
	
	public void setEnergyWidth(double d) {
		this.energy.setWidth(d);
		this.energyValue.setText(""+d/this.MULTIPICATEUR);
	}
	
	public double getEnergyWidth() {
		return this.energy.getWidth();
	}
	
	public Rectangle getRectangle() {
		return this.energy;
	}
	
	public void perdreEnergy(double perte) {
		if(this.energy.getWidth() - perte*this.MULTIPICATEUR > 0) {
			this.energy.setWidth(this.energy.getWidth()-perte*this.MULTIPICATEUR);
		}else {
			this.energy.setWidth(0);
		}
		this.energyValue.setText(""+this.energy.getWidth()/this.MULTIPICATEUR);
		this.colorRec();
	}
	
	public void gainEnergy(double gain) {
		if(this.energy.getWidth()+gain*this.MULTIPICATEUR < this.MAX_LENGTH) {
			this.energy.setWidth(this.energy.getWidth()+gain*this.MULTIPICATEUR);
		}else {
			this.energy.setWidth(MAX_LENGTH);
		}
		this.energyValue.setText(""+this.energy.getWidth()/this.MULTIPICATEUR);
		this.colorRec();
	}
	
	private void colorRec() {
		if(this.energy.getWidth()/this.MULTIPICATEUR < 20) {
			this.energy.setFill(Color.RED);
		}else if(this.energy.getWidth()/this.MULTIPICATEUR < 50) {
			this.energy.setFill(Color.ORANGE);
		}else if(this.energy.getWidth()/this.MULTIPICATEUR < 70) {
			this.energy.setFill(Color.DEEPSKYBLUE);
		}else {
			this.energy.setFill(Color.LIMEGREEN);
		}
	}
	
}
