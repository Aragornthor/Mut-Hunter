package classes;

public class TestMain {
	
	static Plateau jeu = new Plateau();
	static Personnage monstre = new Personnage("monstre", new Position(9,9));
	static Personnage chasseur = new Personnage("chasseur", new Position(0,0));
	
	public static void main(String[] args) {
		
		jeu.initPlateau();
		jeu.startPersonnage(chasseur, monstre);
		jeu.ajoutLoot(2);
		
		boolean fini = false;
		
		while(!fini) {
			fini = tourMonstre();
			if(fini) break;
			fini = tourChasseur();
			jeu.addTours();
		}
		
		System.out.println("Partie Finie !");

	}

	public static boolean tourChasseur() {
		jeu.getCase(chasseur.getPosition()).show();
		jeu.getCase(monstre.getPosition()).hide();
		jeu.affichePlateau(chasseur);
		int deplacementsRestant = chasseur.getDeplacement();
		
		while(deplacementsRestant>0) {
			if(jeu.victoireChasseur(chasseur.getPosition(), monstre.getPosition())) return true;
			chasseur.seDeplace(jeu);
			chasseur.changeCase(jeu);
			jeu.affichePlateau(chasseur);
			if(jeu.getTours()-jeu.getDernierLoot()>5) {
				jeu.ajoutLoot(1);			
			}
			deplacementsRestant--;
		}
		return false;
	}
	
	public static boolean tourMonstre() {
		jeu.getCase(monstre.getPosition()).show();
		jeu.getCase(chasseur.getPosition()).hide();
		jeu.affichePlateau(monstre);
		boolean perdu = false;
		
		int deplacementsRestant = monstre.getDeplacement();
		
		while(deplacementsRestant>0) {
			if(jeu.victoireChasseur(chasseur.getPosition(), monstre.getPosition())) return true;
			if(jeu.victoireMonstre()) return true;
			monstre.seDeplace(jeu);
			jeu.affichePlateau(monstre);
			if(jeu.getTours()-jeu.getDernierLoot()>5) {
				jeu.ajoutLoot(1);			
			}
			deplacementsRestant--;
			if(! jeu.getCase(monstre.getPosition()).getEstPortail() && jeu.getCase(monstre.getPosition()).getEstDecouvert()) jeu.setCompteurCasesDecouvertes();
			perdu = jeu.defaiteMonstre(monstre.getPosition());
			monstre.changeCase(jeu);
			if(perdu) return perdu;
		}
		return false;
	}
}