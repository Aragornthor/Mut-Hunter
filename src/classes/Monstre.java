package classes;

import competences.Competences;
import competences.IEM;

/**
 * 
 * La classe Monstre permet d'instancier un monstre
 * @author Robin Gallifa
 *
 */
public class Monstre extends Personnage {

	public Monstre(Position p) {
		super(p);
		this.setType("monstre");
		this.setTableauCompetences(new Competences[] {new IEM(), null});
	}
}
