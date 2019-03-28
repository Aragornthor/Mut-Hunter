package classes;

import java.util.Scanner;

public class TestPlateau {
	
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		//System.out.println("HI");
		Personnage p = new Personnage("chasseur",new Position(9,9));
		Personnage p1 = new Personnage("monstre",new Position(0,0));
		Position p2 = new Position(9,9);
		
		
		Plateau pl = new Plateau();
		pl.initPlateau();
		pl.startPersonnage(p, p1);
		pl.ajoutLoot(3);
		pl.affichePlateau(p);
		System.out.println("Début de la partie !!");
		do {
			//System.out.println(p1.toString()+"\n"+p2.toString());
			deplacementChasseur(pl,p);
			pl.affichePlateau(p);
			pl.addTours();
		}while(!pl.victoireChasseur(p.getPosition(),p2));
		System.out.println("Fin de la partie !!");
	}
	
	public static void deplacementChasseur(Plateau pl,Personnage p) {
		String choix = "";
		do {
			System.out.print("Direction :");
			choix = sc.nextLine();
		}while(!choix.equals("up") && !choix.equals("down") && !choix.equals("left") && !choix.equals("right"));
		switch(choix) {
		case "up":
			pl.setCaseType(p.getPosition(), TypeCase.NORMAL);
			p.getPosition().setX(p.getPosition().getX()-1);
			if(p.getPosition().getX() < 0) {
				p.getPosition().setX(p.getPosition().getX()+1);
				System.out.println("Sortie du terrain !");
				deplacementChasseur(pl,p);
				break;
			}
			if(pl.getType(p.getPosition()) == TypeCase.PORTAIL) p.setPosition(pl.teleportation(p.getPosition())); 
			if(pl.getCase(p.getPosition()).getLoot()) pl.ajoutCompetence(p);
			pl.setCaseType(p.getPosition(), TypeCase.CHASSEUR);
			break;
		case "down":
			pl.setCaseType(p.getPosition(), TypeCase.NORMAL);
			p.getPosition().setX(p.getPosition().getX()+1);
			if(p.getPosition().getX() >= pl.getHauteur()) {
				p.getPosition().setX(p.getPosition().getX()-1);
				System.out.println("Sortie du terrain !");
				deplacementChasseur(pl,p);
				break;
			}
			if(pl.getType(p.getPosition()) == TypeCase.PORTAIL) p.setPosition(pl.teleportation(p.getPosition()));
			if(pl.getCase(p.getPosition()).getLoot()) pl.ajoutCompetence(p);
			pl.setCaseType(p.getPosition(), TypeCase.CHASSEUR);
			break;
		case "left":
			pl.setCaseType(p.getPosition(), TypeCase.NORMAL);
			p.getPosition().setY(p.getPosition().getY()-1);
			if(p.getPosition().getY() < 0) {
				p.getPosition().setY(p.getPosition().getY()+1);
				System.out.println("Sortie du terrain !");
				deplacementChasseur(pl,p);
				break;
			}
			if(pl.getType(p.getPosition()) == TypeCase.PORTAIL) p.setPosition(pl.teleportation(p.getPosition()));
			if(pl.getCase(p.getPosition()).getLoot()) pl.ajoutCompetence(p);
			pl.setCaseType(p.getPosition(), TypeCase.CHASSEUR);
			break;
		case "right":
			pl.setCaseType(p.getPosition(), TypeCase.NORMAL);
			p.getPosition().setY(p.getPosition().getY()+1);
			if(p.getPosition().getY() >= pl.getLargeur()) {
				p.getPosition().setY(p.getPosition().getY()-1);
				System.out.println("Sortie du terrain !");
				deplacementChasseur(pl,p);
				break;
			}
			if(pl.getCase(p.getPosition()).getLoot()) p.setPosition(pl.teleportation(p.getPosition())); 
			pl.setCaseType(p.getPosition(), TypeCase.CHASSEUR);
			break;		
		}
	}
}
