package UI;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class EnergyBar extends Pane {
	private Rectangle energy;
	
	public EnergyBar() {
		this.energy = new Rectangle();
	}
	
	public Pane getEnergyBar() {
		Pane root = new Pane();
		this.energy.setFill(Color.LIGHTBLUE);
		this.energy.setX(10);
		this.energy.setY(1);
		this.energy.setWidth(200);
		this.energy.setHeight(25);

		Rectangle r2 = new Rectangle();
		r2.setFill(Color.BLACK);
		r2.setX(9);
		r2.setY(0);
		r2.setWidth(202);
		r2.setHeight(27);

		Rectangle r3 = new Rectangle();
		r3.setFill(Color.WHITE);
		r3.setX(10);
		r3.setY(1);
		r3.setWidth(200);
		r3.setHeight(25);
		
		root.getChildren().addAll(r2,r3,this.energy);
		
		return root;
	}
	
	public void setEnergyWidth(double d) {
		this.energy.setWidth(d);
	}
	
	public double getEnergyWidth() {
		return this.energy.getWidth();
	}
	
}
