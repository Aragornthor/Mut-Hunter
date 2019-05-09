package competences;

import classes.Personnage;
import classes.Plateau;
import classes.Position;

/**
 * 
 * La classe Missile est une competence chasseur permettant de tuer instantanement le monstre le faisant gagner la partie
 * @author Quentin Delmarre, Robin Gallifa
 *
 */
public class Missile extends Competences {

	/**
	 * Constructeur de la compétence Missile
	 */
	public Missile() {
		this.setId(2);
		this.setElement("vie");
		this.setEffet(-1); //Mort instantanée
		this.setDuree(0);
		this.cout = 10;
	}

	@Override
	public void utilisation(Plateau p, Personnage perso, Personnage cible) {
		// TODO Auto-generated method stub
		
	}


}
