package competences;

import java.util.Scanner;

import classes.Personnage;
import classes.Plateau;
import classes.Position;

/**
 * 
 * La classe Acide est une competence du monstre faisant perdre de l'energie au chasseur pendant 3 tours
 * @author Quentin Delmarre, Robin Gallifa
 *
 */
public class Acide extends Competences {
	
	/**
	 * Constructeur de la compétence Acide
	 */
	public Acide() {
		this.setId(0);
		this.setNom("Acide");
		this.setDuree(3);
		this.cout = 20;
	}

	@Override
	public void utilisation(Plateau p, Personnage perso, Personnage cible, Position tmp) {
		while(tmp.equals(perso.getPosition()) || tmp.getX() > perso.getPosition().getX()+2 ||
				tmp.getX() < perso.getPosition().getX()-2 ||tmp.getY() > perso.getPosition().getY()+2 ||
				tmp.getY() < perso.getPosition().getY()-2 ) {
		}
		if(tmp.equals(cible.getPosition())) {
			if(cible.getStatut().equals(Statut.Acide)) {
				cible.getStatut().setTour(cible.getStatut().getNbTour()+this.getDuree());
			}else {
				cible.setStatut(Statut.Acide);
			}
		}
		perso.rechargeEnergie(-(this.cout));
	}
}
