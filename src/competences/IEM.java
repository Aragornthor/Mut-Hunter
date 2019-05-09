package competences;

import classes.Personnage;
import classes.Plateau;
import classes.Position;

/**
 * 
 * La classe IEM est une competence du chasseur immobilisant le monstre pendant 1 tour
 * @author Quentin Delmarre, Robin Gallifa
 *
 */
public class IEM  extends Competences {
	
	/**
	 * Constructeur de la compétence IEM
	 */
	public IEM() {
		this.setId(1);
		this.setElement("deplacement");
		this.setEffet(0);
		this.setDuree(1);
		this.cout = 10;
	}

	@Override
	public void utilisation(Plateau p, Personnage perso, Personnage cible) {
		// TODO Auto-generated method stub
		
	}


}
