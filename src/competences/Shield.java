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
		this.setNom("Shield");
		this.setDuree(2);
		this.cout = 50;
	}

	@Override
	public void utilisation(Plateau p, Personnage perso, Personnage cible, Position tmp) {
		if(perso.getStatut() == Statut.Shield) perso.getStatut().setTour(perso.getStatut().getNbTour()+this.getDuree());
		else {
			perso.setStatut(Statut.Shield);
			perso.getStatut().setTour(this.getDuree());
		}
		perso.rechargeEnergie(-(this.cout));
	}


}
