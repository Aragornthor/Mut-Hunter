package IA;

import java.util.Random;

import classes.Case;
import classes.Chasseur;
import classes.Plateau;
import classes.Position;
import competences.Competences;
import competences.IEM;

/**
 * 
 * Classe présentant le comportement adopté par l'IA du Chasseur
 * @author Quentin DELMARRE
 *
 */
public class IAChasseurGUI extends Chasseur {
	private Direction dir;
	
	/**
	 * 
	 * @param p Instancie l'IAChasseur à la Position p
	 */
	public IAChasseurGUI(Position p) {
		super(p);
		super.setType("chasseur");
		super.setTableauCompetences(new Competences[] {new IEM(), null});
		this.dir = Direction.HAUT;
	}
	
	/**
	 * 
	 * @param p Prend en paramètre le Plateau de jeu p
	 * @return Renvoie le statut du déplacement : VRAI si réalisé FAUX sinon
	 */
	public boolean jouer(Plateau p) {
		System.out.println("Move IA");
		do {
			Random r = new Random();
			int ranDir = r.nextInt(4);
			switch(ranDir) {
				case 0: dir = Direction.HAUT; break;
				case 1: dir = Direction.BAS; break;
				case 2: dir = Direction.GAUCHE; break;
				case 3: dir = Direction.DROITE; break;
			}
			System.out.println("\tDirPossible : " + dirPossible(p, super.getPosition()));
			System.out.println("\tDirection : " + dir);
			System.out.println("\tPositions : " + super.getPosition());
		} while(!dirPossible(p, super.getPosition()));
		
		boolean res = super.estDeplace(p, Character.toString(dir.getDirection()));
		super.resetMouvement();
		return res;
	}
	
	/**
	 * 
	 * @param p Prend en paramètre le Plateau de jeu p
	 * @param pos Prend en paramètre la Position de l'IAChasseur pos
	 * @return Renvoie la possibilité de la direction : VRAI si possible FAUX sinon
	 */
	private boolean dirPossible(Plateau p, Position pos) {
		if(dir == Direction.HAUT && pos.getY() - 1 < 0) return false;
		if(dir == Direction.BAS && pos.getY() + 1 > p.getHauteur()) return false;
		if(dir == Direction.GAUCHE && pos.getX() - 1 < 0) return false;
		if(dir == Direction.DROITE && pos.getX() + 1 > p.getLargeur()) return false;
		return true;
	}
	
	/**
	 * 
	 * @param tmp Prend en paramètre une nouvelle valeur TEMPORAIRE de dir
	 * @return Renvoie VRAI est en cas d'échec
	 */
	private boolean iaStupide(Plateau p, Direction tmp) {
		Case tmpCase = null;
		Position pos = getPosition();
		switch(tmp) {
			case HAUT: if(pos.getY() - 1 >= 0) tmpCase = p.getCase(new Position(pos.getX(), pos.getY() - 1)); break;
			case BAS: if(pos.getY() + 1 < p.getHauteur()) tmpCase = p.getCase(new Position(pos.getX(), pos.getY() + 1)); break;
			case GAUCHE: if(pos.getX() - 1 >= 0) tmpCase = p.getCase(new Position(pos.getX() - 1, pos.getY())); break;
			case DROITE: if(pos.getX() + 1 < p.getLargeur()) tmpCase = p.getCase(new Position(pos.getX() + 1, pos.getY())); break;
		}
		if(pos != null) tmpCase = p.getCase(pos);
		return tmpCase.getEstDecouvert();
	}
}
