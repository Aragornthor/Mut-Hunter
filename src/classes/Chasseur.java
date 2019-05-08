package classes;

import competences.Competences;
import competences.IEM;
import javafx.scene.image.Image;

/**
 * 
 * La classe Monstre permet d'instancier un chasseur
 * @author Robin Gallifa
 *
 */
public class Chasseur extends Personnage{

	/**
	 * Constructeur du Chasseur avec une  position donnée
	 * @param p Prend en paramètre la position initiale du chasseur
	 */
	Image image = new Image("file:../../ressources/images/ceciEstUneGrosseBoule.png");
	
	public Chasseur(Position p) {
		super(p);
		this.setType("chasseur");
		this.setTableauCompetences(new Competences[] {new IEM(), null});
	}
	
	/**
	 * Permet de redemander un deplacement tant que ce que donne le joueur n'est pas bon
	 * @param p Prend un plateau en paramètre
	 */
	public void seDeplace(Plateau p) {
		while(!estDeplaceJoueur(p)) {
			System.out.println("Vous ne pouvez pas vous d�placer ici.");
		}
	}

	public Image getImage() {
		return this.image;
	}
	
	
}
