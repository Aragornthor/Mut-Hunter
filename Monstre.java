package classes;

import java.util.Scanner;

import competences.Competences;
import competences.Shield;

public class Monstre implements Personnage {
	
	private int energie;
	private int maxEnergie;
	private Position position;
	private Competences[] competences;
	private int deplacement;
	private int vie;
	
	public Monstre() {
		this.energie = 75;
		this.position = new Position(9,9);
		this.competences = new Competences[] {new Shield(), null};
		this.deplacement = 1;
		this.vie = 1;
	}
	
	public int getEnergie() {
		return energie;
	}

	public int getMaxEnergie() {
		return maxEnergie;
	}

	public Position getPosition() {
		return position;
	}

	public Competences[] getCompetences() {
		return competences;
	}

	public int getDeplacement() {
		return deplacement;
	}

	public int getVie() {
		return vie;
	}
	
	@Override
	public void seDeplace(Plateau p) {
		while(!estDeplace(p)) {
			System.out.println("Vous ne pouvez pas vous déplacer ici.");
		}
	}
	
	private boolean estDeplace(Plateau p) {
		Scanner reader = new Scanner(System.in);
		String entree = reader.next();
		reader.close();
		if(entree.equals("z")) { //SE DEPLACE VERS LE HAUT
			if(this.position.getY()-1<0) return false;
			else {
				this.position.setY(this.position.getY()-1);
				return true;
			}
		}
		if(entree.equals("s")) { //SE DEPLACE VERS LE BAS
			if(this.position.getY()+1>= p.getHauteur()) return false;
			else {
				this.position.setY(this.position.getY()+1);
				return true;
			}
		}
		if(entree.equals("q")) { //SE DEPLACE VERS LA GAUCHE
			if(this.position.getX()-1<0) return false;
			else {
				this.position.setX(this.position.getX()-1);
				return true;
			}
		}
		if(entree.equals("d")) { //SE DEPLACE VERS LA DROITE
			if(this.position.getX()+1>=p.getLargeur()) return false;
			else {
				this.position.setX(this.position.getX()+1);
				return true;
			}
		}
		return false;
	}

	@Override
	public void rechargeEnergie() {
		rechargeEnergie(10);
	}

	@Override
	public void rechargeEnergie(int i) {
		if((this.energie + i) > maxEnergie) this.energie = maxEnergie;
		else this.energie += i;
	}
}
