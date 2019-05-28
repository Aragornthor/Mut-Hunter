package Event;

import java.util.List;

import UI.EnergyBar;
import classes.Chasseur;
import classes.Monstre;
import classes.Plateau;
import classes.Position;
import competences.Acide;
import competences.Competences;
import competences.Missile;
import competences.Saut;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class CompetenceEvent implements EventHandler<MouseEvent>{

	private Competences comp;
	private Pane pane;
	private List<Node> error;
	private List<Node> success;
	private Plateau terrain;
	private Chasseur chass;
	private Monstre mons;
	private boolean tourChasseur;
	private TextField posX, posY;
	private EnergyBar energy;
	
	public CompetenceEvent(EnergyBar eb,Competences c,Pane pane,List<Node> error,List<Node> success,Plateau jeu,Chasseur chass,Monstre mons,boolean b,TextField posX,TextField posY) {
		this.comp = c;
		this.pane = pane;
		this.error = error;
		this.success = success;
		this.terrain = jeu;
		this.chass = chass;
		this.mons = mons;
		this.tourChasseur = b;
		this.posX = posX;
		this.posY = posY;
		this.energy = eb;
	}
	
	@Override
	public void handle(MouseEvent arg0) {
		if(!this.posX.getText().isEmpty() && !this.posY.getText().isEmpty()) {
			System.out.println(Integer.parseInt(this.posX.getText())+"  "+Integer.parseInt(this.posY.getText()));
			System.out.println(this.chass.getPosition().getX()+"  "+this.chass.getPosition().getY());
			if(this.comp.equals(new Missile())){
				System.out.println("Start verif Missile");
				if(tourChasseur) {
					if(Integer.parseInt(this.posX.getText()) < this.chass.getPosition().getX()-2 &&
							Integer.parseInt(this.posX.getText()) > this.chass.getPosition().getX()+2 &&
							Integer.parseInt(this.posY.getText()) < this.chass.getPosition().getY()-2 &&
							Integer.parseInt(this.posY.getText()) > this.chass.getPosition().getY()+2) {
						System.out.println("failed");
						pane.getChildren().clear();
						pane.getChildren().addAll(this.error);
					}else {
						this.energy.perdreEnergy(this.comp.getCout());
						this.comp.utilisation(this.terrain, this.chass, this.mons, new Position(Integer.parseInt(this.posX.getText()),Integer.parseInt(this.posY.getText())));
						System.out.println("success");
						pane.getChildren().clear();
						pane.getChildren().addAll(this.success);
					}
				}else {
					if(Integer.parseInt(this.posX.getText()) < this.mons.getPosition().getX()-2 &&
							Integer.parseInt(this.posX.getText()) > this.mons.getPosition().getX()+2 &&
							Integer.parseInt(this.posY.getText()) < this.mons.getPosition().getY()-2 &&
							Integer.parseInt(this.posY.getText()) > this.mons.getPosition().getY()+2) {
						System.out.println("failed");
						pane.getChildren().clear();
						pane.getChildren().addAll(this.error);
					}else {
						this.energy.perdreEnergy(this.comp.getCout());
						this.comp.utilisation(this.terrain, this.mons, this.chass, new Position(Integer.parseInt(this.posX.getText()),Integer.parseInt(this.posY.getText())));
						System.out.println("success");
						pane.getChildren().clear();
						pane.getChildren().addAll(this.success);
					}
				}
				
			}else if(comp.equals(new Saut()) || comp.equals(new Acide())) {
				System.out.println("Start verif Saut");
				if(tourChasseur) {
					if(Integer.parseInt(this.posX.getText()) < this.chass.getPosition().getX()-2 &&
							Integer.parseInt(this.posX.getText()) > this.chass.getPosition().getX()+2 &&
							Integer.parseInt(this.posY.getText()) < this.chass.getPosition().getY()-2 &&
							Integer.parseInt(this.posY.getText()) > this.chass.getPosition().getY()+2) {
						
						System.out.println("failed");
						pane.getChildren().clear();
						pane.getChildren().addAll(this.error);
					}else {
						this.energy.perdreEnergy(this.comp.getCout());
						this.comp.utilisation(this.terrain, this.chass, this.mons, new Position(Integer.parseInt(this.posX.getText()),Integer.parseInt(this.posY.getText())));
						System.out.println("success");
						pane.getChildren().clear();
						pane.getChildren().addAll(this.success);
					}
				}else {
					if(Integer.parseInt(this.posX.getText()) < this.mons.getPosition().getX()-2 &&
							Integer.parseInt(this.posX.getText()) > this.mons.getPosition().getX()+2 &&
							Integer.parseInt(this.posY.getText()) < this.mons.getPosition().getY()-2 &&
							Integer.parseInt(this.posY.getText()) > this.mons.getPosition().getY()+2) {
						
						System.out.println("failed");
						pane.getChildren().clear();
						pane.getChildren().addAll(this.error);
					}else {
						this.energy.perdreEnergy(this.comp.getCout());
						this.comp.utilisation(this.terrain, this.mons, this.chass, new Position(Integer.parseInt(this.posX.getText()),Integer.parseInt(this.posY.getText())));
						System.out.println("success");
						pane.getChildren().clear();
						pane.getChildren().addAll(this.success);
					}
				}
			System.out.println("Fin verif");
			}
		}
			

	}
	
}
