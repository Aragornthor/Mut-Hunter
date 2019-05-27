package classes;

import competences.Competences;
import competences.Shield;
import javafx.scene.image.Image;

/**
 * 
 * La classe Monstre permet d'instancier un monstre
 * @author Robin Gallifa
 *
 */
public class Monstre extends Personnage {

	Image image = new Image("file:ressources/images/monstre.png");
	
	/**
	 * Constructeur du Monstre avec une  position donnée
	 * @param p Prend en paramètre la position initiale du monstre
	 */
	public Monstre(Position p) {
		super(p);
		this.setType("monstre");
		this.setTableauCompetences(new Competences[] {new Shield(), null});
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
	@Override
	public Image getImage() {
		return this.image;
	}

	/**
	 * Modifie la case de présence du monstre
	 */
	@Override
	public boolean changeCase(Plateau p) {
		boolean passeParLa = p.defaiteMonstre(this.getPosition());
		p.getCase(this.getPosition()).setEstMonstre(true);
		p.getCase(this.getPosition()).decouvrirCase();
		p.getCase(this.getPosition()).setTempsDecouvert(p.getTours());
		return passeParLa;
	}
}
