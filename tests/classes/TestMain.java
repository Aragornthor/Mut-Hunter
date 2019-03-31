package classes;

public class TestMain {
	
	static Plateau jeu = new Plateau();
	static Personnage monstre = new Personnage("monstre", new Position(9,9));
	static Personnage chasseur = new Personnage("chasseur", new Position(0,0));
	
	public static void main(String[] args) {
		
		jeu.initPlateau();
		jeu.startPersonnage(chasseur, monstre);
		jeu.ajoutLoot(2);
		while(!jeu.victoireChasseur(chasseur.getPosition(), monstre.getPosition())) {
			tourMonstre();
			tourChasseur();
			jeu.addTours();
		}

	}

	public static void tourChasseur() {
		jeu.getCase(chasseur.getPosition()).show();
		jeu.getCase(monstre.getPosition()).hide();
		jeu.affichePlateau(chasseur);
		int deplacementsRestant = chasseur.getDeplacement();
		boolean perdu = false;
		
		while(!jeu.victoireChasseur(chasseur.getPosition(), monstre.getPosition()) && deplacementsRestant>0) {
			chasseur.seDeplace(jeu);
			jeu.affichePlateau(chasseur);
			jeu.ajoutLoot(1);			
			deplacementsRestant--;
		}
	}
	
	public static void tourMonstre() {
		jeu.getCase(monstre.getPosition()).show();
		jeu.getCase(chasseur.getPosition()).hide();
		jeu.affichePlateau(monstre);
		
		int deplacementsRestant = monstre.getDeplacement();
		
		while(!jeu.victoireChasseur(chasseur.getPosition(), monstre.getPosition()) && deplacementsRestant>0) {
			monstre.seDeplace(jeu);
			jeu.affichePlateau(monstre);
			jeu.ajoutLoot(1);
			deplacementsRestant--;
		}
	}
}