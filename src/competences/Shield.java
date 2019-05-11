package competences;

import classes.Personnage;
import classes.Plateau;
import classes.Position;

/**
 * 
 * La classe Shield est une competence du monstre permettant d'etre invulnerable pendant 1 tour
 * @author Quentin Delmarre, Robin Gallifa
 *
 */
public class Shield extends Competences {

	/**
	 * Constructeur de la compétence Shield
	 */
	public Shield() {
		this.setId(5);
		this.setElement("protection");
		this.setEffet(1); //Protection totale -> 1 tour
		this.setDuree(2);
		this.cout = 50;
	}

	@Override
	public void utilisation(Plateau p, Personnage perso, Personnage cible) {
		perso.setStatut(Statut.Shield);
		perso.rechargeEnergie(-(this.cout));
	}


}
