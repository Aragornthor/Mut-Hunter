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
		this.setElement("énergie");
		this.setEffet(-10); //Perte d'énergie (nettoyage) -> 3 tours
		this.setDuree(3);
		this.cout = 20;
	}

	@Override
	public void utilisation(Plateau p, Personnage perso, Personnage cible) {
		Position tmp = this.demanderPosition();
		while(tmp.equals(perso.getPosition()) || tmp.getX() > perso.getPosition().getX()+2 ||
				tmp.getX() < perso.getPosition().getX()-2 ||tmp.getY() > perso.getPosition().getY()+2 ||
				tmp.getY() < perso.getPosition().getY()-2 ) {
			tmp = this.demanderPosition();
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
	
	private Position demanderPosition() {
		System.out.println("Où voulez vous tirer ?\n en x :");
		Scanner sc = new Scanner(System.in);
		int x = sc.nextInt();
		System.out.println("en y : ");
		int y = sc.nextInt();
		return new Position(x,y);
	}



	

}
