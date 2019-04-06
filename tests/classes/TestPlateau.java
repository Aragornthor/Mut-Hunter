package classes;

import java.util.Scanner;

public class TestPlateau {
	
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		//System.out.println("HI");
		Personnage p = new Chasseur(new Position(9,9));
		Personnage p1 = new Monstre(new Position(0,0));
		
		
		Plateau pl = new Plateau();
		pl.initPlateau();
		pl.startPersonnage(p, p1);
		pl.ajoutLoot(3);
		pl.affichePlateau(p);
	}
	
}
