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
	Image image = new Image("file:ressources/images/mech.png");
	
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

	/**
	 * @return Renvoie l'image associée à l'entité 
	 */
	public Image getImage() {
		return this.image;
	}

	/**
	 * Modifie la case de présence du chasseur
	 */
	@Override
	public boolean changeCase(Plateau p) {
		p.getCase(this.getPosition()).setEstChasseur(true);
		return false;
	}
	
	
}
