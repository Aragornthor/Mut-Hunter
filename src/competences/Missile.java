package competences;

import java.util.Scanner;

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
		this.setNom("Missile");
		this.setDuree(0);
		this.cout = 100;
	}

	public void utilisation(Plateau p, Personnage perso, Personnage cible, Position tmp) {
		
		tmp = new Position(tmp.getX()-1, tmp.getY()-1);
		
		int i = tmp.getX();
		int j = tmp.getY();
		boolean flag = false;
		while(i<tmp.getX()+3 && !flag) {
			while (j<tmp.getY()+3 && !flag){
				if((new Position(i,j)).equals(cible.getPosition())) {
					if(cible.getStatut() != Statut.Shield) cible.setStatut(Statut.Mort);
					flag = true;
				}
				j++;
			}
			j=tmp.getY();
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
