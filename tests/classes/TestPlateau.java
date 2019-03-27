package classes;

import java.util.Scanner;

public class TestPlateau {
	
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		Position p1 = new Position(0,0);
		Position p2 = new Position(9,9);
		
		Plateau pl = new Plateau();
		pl.initPlateau();
		pl.ajoutLoot(3);
		pl.showChasseur(p1);
		pl.hideMonstre(p2);
		pl.affichePlateau();
		System.out.println("Début de la partie !!");
		do {
			//System.out.println(p1.toString()+"\n"+p2.toString());
			deplacementChasseur(pl,p1);
			pl.affichePlateau();
			pl.addTours();
		}while(!pl.victoireChasseur(p1,p2));
		System.out.println("Fin de la partie !!");
	}
	
	public static void deplacementChasseur(Plateau pl,Position p) {
		String choix = "";
		do {
			System.out.print("Direction :");
			choix = sc.nextLine();
		}while(!choix.equals("up") && !choix.equals("down") && !choix.equals("left") && !choix.equals("right"));
		switch(choix) {
		case "up":
			pl.setCaseType(p, TypeCase.NORMAL);
			p.setX(p.getX()-1);
			if(p.getX() < 0) {
				p.setX(p.getX()+1);
				System.out.println("Sortie du terrain !");
				deplacementChasseur(pl,p);
				break;
			}
			if(pl.getType(p) == TypeCase.PORTAIL) p = pl.teleportation(p);
			pl.setCaseType(p, TypeCase.CHASSEUR);
			break;
		case "down":
			pl.setCaseType(p, TypeCase.NORMAL);
			p.setX(p.getX()+1);
			if(p.getX() >= pl.getHauteur()) {
				p.setX(p.getX()-1);
				System.out.println("Sortie du terrain !");
				deplacementChasseur(pl,p);
				break;
			}
			if(pl.getType(p) == TypeCase.PORTAIL) p = pl.teleportation(p);
			pl.setCaseType(p, TypeCase.CHASSEUR);
			break;
		case "left":
			pl.setCaseType(p, TypeCase.NORMAL);
			p.setY(p.getY()-1);
			if(p.getY() < 0) {
				p.setY(p.getY()+1);
				System.out.println("Sortie du terrain !");
				deplacementChasseur(pl,p);
				break;
			}
			if(pl.getType(p) == TypeCase.PORTAIL) pl.teleportation(p);
			pl.setCaseType(p, TypeCase.CHASSEUR);
			break;
		case "right":
			pl.setCaseType(p, TypeCase.NORMAL);
			p.setY(p.getY()+1);
			if(p.getY() >= pl.getLargeur()) {
				p.setY(p.getY()-1);
				System.out.println("Sortie du terrain !");
				deplacementChasseur(pl,p);
				break;
			}
			if(pl.getType(p) == TypeCase.PORTAIL) p = pl.teleportation(p);
			pl.setCaseType(p, TypeCase.CHASSEUR);
			break;		
		}
	}
}
