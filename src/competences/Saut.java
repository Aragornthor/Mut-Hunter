package competences;

import java.util.Scanner;

import classes.Personnage;
import classes.Plateau;
import classes.Position;

/**
 * 
 * La classe Saut est une competence du monstre permettant au monstre d'assomer le chasseur pendant 2 tour ce qui l'immobilise
 * @author Quentin Delmarre, Robin Gallifa
 *
 */
public class Saut extends Competences {

	/**
	 * Constructeur de la compétence Saut
	 */
	public Saut() {
		this.setId(4);
		this.setElement("deplacement");
		this.setEffet(0); //Immobilisation -> 2 tours
		this.setDuree(2);
		this.cout = 30;
	}

	@Override
	public void utilisation(Plateau p, Personnage perso, Personnage cible) {
		Position tmp = this.demanderPosition();
		while(tmp.equals(perso.getPosition()) || tmp.getX() > perso.getPosition().getX()+2 ||
				tmp.getX() < perso.getPosition().getX()-2 ||tmp.getY() > perso.getPosition().getY()+2 ||
				tmp.getY() < perso.getPosition().getY()-2 ) {
			this.utilisation(p, perso,cible);
		}
		
		perso.setPosition(tmp);
		
		Position zone = new Position(perso.getPosition().getX()-1, perso.getPosition().getY()-1);
		
		int i = zone.getX();
		int j = zone.getY();
		boolean flag = false;
		while(i<zone.getX()+3 && !flag) {
			while (j<zone.getY()+3 && !flag){
				if(zone.equals(cible.getPosition())) {
					if(cible.getStatut() != Statut.Shield) {
						if(cible.getStatut().equals(Statut.Stun)) {
							cible.getStatut().setTour(cible.getStatut().getNbTour()+this.getDuree());
						}else {
							cible.setStatut(Statut.Stun);
							cible.getStatut().setTour(this.getDuree());
						}
					}
					flag = true;
				}
				j++;
			}
			j=zone.getX();
			i++;
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
