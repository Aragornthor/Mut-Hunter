package Event;

import java.util.List;

import UI.EnergyBar;
import UI.GameUI;
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
	private GameUI gUI;
	
	public CompetenceEvent(EnergyBar eb, GameUI gUI,Pane pane,List<Node> error,List<Node> success,Plateau jeu,Chasseur chass,Monstre mons,boolean b,TextField posX,TextField posY) {
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
		this.gUI = gUI;
	}
	
	@Override
	public void handle(MouseEvent arg0) {
		if(gUI.getNbComp() == 0) this.comp = gUI.getpI().getComp1().getComp();
		else this.comp = gUI.getpI().getComp2().getComp();
		System.out.println("La comp3 :"+comp);
		System.out.println("Le chasseur :"+chass);
		System.out.println();
		int x = 0;
		int y = 0;
		if(!this.posX.getText().isEmpty() && !this.posY.getText().isEmpty()) {
			if(this.estUnInt(this.posX.getText()) && this.estUnInt(this.posY.getText())) {
				x = Integer.parseInt(this.posX.getText());
				y = Integer.parseInt(this.posY.getText());
			}
			System.out.println(x+"  "+y);
			System.out.println(this.chass.getPosition().getX()+"  "+this.chass.getPosition().getY());
			if(this.comp.equals(new Missile())){
				System.out.println("Start verif Missile");
				if(x < this.chass.getPosition().getX()-2 ||
					x > this.chass.getPosition().getX()+2 ||
					y < this.chass.getPosition().getY()-2 ||
					y > this.chass.getPosition().getY()+2 || 
					!this.estUnInt(this.posX.getText()) || 
					!this.estUnInt(this.posY.getText())){
						System.out.println("failed");
						pane.getChildren().clear();
						pane.getChildren().addAll(this.error);
				}else {
					this.energy.perdreEnergy(this.comp.getCout());
					this.comp.utilisation(this.terrain, this.chass, this.mons, new Position(x,y));
					System.out.println("success");
					pane.getChildren().clear();
					pane.getChildren().addAll(this.success);
				}	
			}else if(comp.equals(new Saut()) || comp.equals(new Acide())) {
				System.out.println("Start verif Saut ou Acide");
				if(x < this.mons.getPosition().getX()-2 ||
					x > this.mons.getPosition().getX()+2 ||
					y < this.mons.getPosition().getY()-2 ||
					y > this.mons.getPosition().getY()+2 || 
					!this.estUnInt(this.posX.getText()) || 
					!this.estUnInt(this.posY.getText())){	
						System.out.println("failed");
						pane.getChildren().clear();
						pane.getChildren().addAll(this.error);
				}else {
					this.energy.perdreEnergy(this.comp.getCout());
					this.comp.utilisation(this.terrain, this.mons, this.chass, new Position(x,y));
					System.out.println("success");
					pane.getChildren().clear();
					pane.getChildren().addAll(this.success);

				}
			System.out.println("Fin verif");
			}
		}

	}
	
	private boolean estUnInt(String chaine) {
		try {
			Double.parseDouble(chaine);
		} catch (NumberFormatException e){
			return false;
		}
 
		return true;
	}
}
