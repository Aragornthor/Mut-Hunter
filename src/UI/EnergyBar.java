package UI;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class EnergyBar extends HBox {
	private Rectangle energy;
	private final double MAX_LENGTH = 200;
	private Label energyValue;
	
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
		r2.setX(9);
		r2.setY(0);
		r2.setWidth(this.MAX_LENGTH+2);
		r2.setHeight(27);

		Rectangle r3 = new Rectangle();
		r3.setFill(Color.WHITE);
		r3.setX(10);
		r3.setY(1);
		r3.setWidth(this.MAX_LENGTH);
		r3.setHeight(25);
		
		this.energyValue = new Label(""+this.energy.getWidth()/2);
		
		energyBar.getChildren().addAll(r2,r3,this.energy);
		
		HBox root = new HBox();
		
		root.getChildren().addAll(energyBar,this.energyValue);
		
		HBox.setMargin(this.energyValue, new Insets(5));		
		
		return root;
	}
	
	public void setEnergyWidth(double d) {
		this.energy.setWidth(d);
		this.energyValue.setText(""+d/2);
	}
	
	public double getEnergyWidth() {
		return this.energy.getWidth();
	}
	
	public Rectangle getRectangle() {
		return this.energy;
	}
	
	public void perdreEnergy(double perte) {
		if(this.energy.getWidth() - perte*2 > 0) {
			this.energy.setWidth(this.energy.getWidth()-perte*2);
		}else {
			this.energy.setWidth(0);
		}
		this.energyValue.setText(""+this.energy.getWidth()/2);
		this.colorRec();
	}
	
	public void gainEnergy(double gain) {
		if(this.energy.getWidth()+gain*2 < this.MAX_LENGTH) {
			this.energy.setWidth(this.energy.getWidth()+gain*2);
		}else {
			this.energy.setWidth(MAX_LENGTH);
		}
		this.energyValue.setText(""+this.energy.getWidth()/2);
		this.colorRec();
	}
	
	private void colorRec() {
		if(this.energy.getWidth()/2 < 20) {
			this.energy.setFill(Color.RED);
		}else if(this.energy.getWidth()/2 < 50) {
			this.energy.setFill(Color.ORANGE);
		}else {
			this.energy.setFill(Color.DEEPSKYBLUE);
		}
	}
	
}
