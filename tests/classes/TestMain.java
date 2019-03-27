package classes;

public class TestMain {
	
	static Plateau jeu = new Plateau();
	static Personnage monstre = new Personnage("monstre", new Position(9,9));
	static Personnage chasseur = new Personnage("chasseur", new Position(0,0));
	
	public static void main(String[] args) {
		
		jeu.initPlateau();
		jeu.ajoutLoot(2);
		while(!jeu.victoireChasseur(chasseur.getPosition(), monstre.getPosition())) {
			tourMonstre();
			tourChasseur();
			jeu.addTours();
		}

	}

	public static void tourChasseur() {
		jeu.showChasseur(chasseur.getPosition());
		jeu.hideMonstre(monstre.getPosition());
		jeu.affichePlateau(chasseur);
		int deplacementsRestant = chasseur.getDeplacement();
		boolean perdu = false;
		
		while(!jeu.victoireChasseur(chasseur.getPosition(), monstre.getPosition()) && !perdu && deplacementsRestant>0) {
			chasseur.seDeplace(jeu);
			jeu.affichePlateau(chasseur);
			deplacementsRestant--;
		}
	}
	
	public static void tourMonstre() {
		jeu.hideChasseur(chasseur.getPosition());
		jeu.showMonstre(monstre.getPosition());
		
		int deplacementsRestant = monstre.getDeplacement();
		
		while(!jeu.victoireChasseur(chasseur.getPosition(), monstre.getPosition()) && deplacementsRestant>0) {
			monstre.seDeplace(jeu);
			jeu.affichePlateau(monstre);
			deplacementsRestant--;
		}
	}
}