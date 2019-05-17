package competences;

import classes.Personnage;
import classes.Plateau;
import classes.Position;
import classes.TypeCase;

/**
 * 
 * La classe Piege est une competence chasseur permettant de creer un piege
 * @author Quentin Delmarre, Robin Gallifa
 *
 */
public class Piege extends Competences {

	/**
	 * Constructeur de la compétence Piege
	 */
	public Piege() {
		this.setId(3);
		this.setNom("Piege");
		this.setDuree(0);
		this.cout = 10;
	}

	@Override
	public void utilisation(Plateau p, Personnage perso, Personnage cible, Position tmp) {
		if(!p.getCase(perso.getPosition()).getEstPortail() && !p.getCase(perso.getPosition()).getEstPiege()) p.getCase(perso.getPosition()).setTypeCase(TypeCase.PIEGE);
	}


}
