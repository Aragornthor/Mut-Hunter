package classes;

public class TestMain {
	public static void main(String[] args) {
		Plateau jeu = new Plateau();
		jeu.initPlateau();
		jeu.ajoutLoot(2);
		Personnage monstre = new Personnage("monstre");
		Personnage chasseur = new Personnage("chasseur");

		while(!jeu.victoireChasseur(chasseur.getPosition(), monstre.getPosition())) {
			tourMonstre();
			tourChasseur();
			jeu.addTours();
		}
	}

	public static void tourMonstre() {
	}
	
	public static void tourChasseur() {
		
	}
}