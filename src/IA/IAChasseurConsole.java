package IA;

import java.util.Random;

import classes.Personnage;
import classes.Plateau;
import classes.Position;
import competences.Competences;
import competences.IEM;
import javafx.scene.image.Image;

/**
 * 
 * La classe IAChasseur permet d'instancier un chasseur joué par l'ordinateur
 * @author Robin Gallifa
 *
 */
public class IAChasseurConsole extends Personnage {
	String direction = "z";
	
	/**
	 * Constructeur du Chasseur avec une  position donnée
	 * @param p Prend en paramètre la position initiale du chasseur
	 */
	public IAChasseurConsole(Position p) {
		super(p);
		this.setType("chasseur");
		this.setTableauCompetences(new Competences[] {new IEM(), null});		
	}
	
	/**
	 * Permet de redemander un deplacement tant que celles données ne sont pas bonnes
	 * @param p Prend un plateau en paramètre
	 */
	public void seDeplace(Plateau p) {
		while(!estDeplace(p, this.dirAlea()));
	}
	
	/**
	 * Renvoie une direction aléatoire
	 */
	protected String dirAlea() {
		Random rand = new Random();
		int i = rand.nextInt(4);
		if(i==0) direction = "z";
		else if(i==1) direction = "q";
		else if(i==2) direction = "s";
		else direction = "d";
		
		return direction;	
	}

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean changeCase(Plateau p) {
		p.getCase(this.getPosition()).setEstChasseur(true);
		return false;
	}

	@Override
	public void supprimePersonnage(Plateau p) {
		p.getCase(this.getPosition()).setEstChasseur(false);
	}
}
