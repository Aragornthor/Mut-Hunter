package IA;

import classes.Monstre;
import classes.Plateau;
import classes.Position;
import competences.Competences;
import competences.Shield;

public class IAMonstreGUI extends Monstre {
	private Direction dir;
	
	/**
	 * 
	 * @param p Instancie l'IAMonstre à la Position p
	 */
	public IAMonstreGUI(Position p) {
		super(p);
		super.setType("monstre");
		super.setTableauCompetences(new Competences[] {new Shield(), null});
		this.dir = Direction.HAUT;
	}

	/**
	 * 
	 * @param p Prend en paramètre le Plateau de jeu p
	 * @return Renvoie le statut du déplacement : VRAI si réalisé FAUX sinon
	 */
	public boolean jouer(Plateau p) {
		do {
			int ranDir = ((int) Math.random()*4);
			switch(ranDir) {
				case 0: dir = Direction.HAUT;
				case 1: dir = Direction.BAS;
				case 2: dir = Direction.GAUCHE;
				case 3: dir = Direction.DROITE;
			}
		} while(dirPossible(p, super.getPosition()));
		
		return super.estDeplace(p, Character.toString(dir.getDirection()));
	}
	
	/**
	 * 
	 * @param p Prend en paramètre le Plateau de jeu p
	 * @param pos Prend en paramètre la Position de l'IAMonstre pos
	 * @return Renvoie la possibilité de la direction : VRAI si possible FAUX sinon
	 */
	private boolean dirPossible(Plateau p, Position pos) {
		if(dir == Direction.HAUT && pos.getY() - 1 < 0) return false;
		if(dir == Direction.BAS && pos.getY() + 1 >= p.getHauteur()) return false;
		if(dir == Direction.GAUCHE && pos.getX() - 1 < 0) return false;
		if(dir == Direction.DROITE && pos.getX() + 1 >= p.getLargeur()) return false;
		return true;
	}
}
