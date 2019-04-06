package classes;

import competences.Competences;
import competences.Shield;

/**
 * 
 * La classe Monstre permet d'instancier un chasseur
 * @author Robin Gallifa
 *
 */
public class Chasseur extends Personnage{

	public Chasseur(Position p) {
		super(p);
		this.setType("chasseur");
		this.setTableauCompetences(new Competences[] {new Shield(), null});
	}
}
