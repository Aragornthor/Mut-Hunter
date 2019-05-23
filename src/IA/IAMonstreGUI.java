package IA;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import classes.Case;
import classes.Monstre;
import classes.Plateau;
import classes.Position;
import competences.Acide;
import competences.Competences;

/**
 * 
 * Classe présentant le comportement adopté par l'IA du Monstre
 * @author Quentin DELMARRE
 *
 */
public class IAMonstreGUI extends Monstre {
	private Direction dir;
	private List<Position> histPos;
	
	/**
	 * 
	 * @param p Instancie l'IAMonstre à la Position p
	 */
	public IAMonstreGUI(Position p) {
		super(p);
		super.setType("monstre");
		super.setTableauCompetences(new Competences[] {new Acide(), null});
		this.dir = Direction.HAUT;
		histPos = new ArrayList<>();
	}
	
	/**
	 * 
	 * @param p Prend en paramètre le Plateau de jeu p
	 * @return Renvoie le statut du déplacement : VRAI si réalisé FAUX sinon
	 */
	public boolean jouer(Plateau p) {
		System.out.println("Move IA");
		Direction tmp = null;
		do {
			Random r = new Random();
			int ranDir = r.nextInt(4);
			switch(ranDir) {
				case 0: tmp = Direction.HAUT; break;
				case 1: tmp = Direction.BAS; break;
				case 2: tmp = Direction.GAUCHE; break;
				case 3: tmp = Direction.DROITE; break;
			}
			System.out.println("\tDirPossible : " + dirPossible(p, super.getPosition(), tmp));
			System.out.println("\tDirection : " + dir);
			System.out.println("\tPositions : " + super.getPosition());
		} while(!dirPossible(p, super.getPosition(), tmp) && iaStupide(p, tmp));
		dir = tmp;
		histPos.add(getPosition());
		if(p.getCase(getPosition()).getEstPortail()) super.estDeplace(p, Character.toString(dir.getDirection()));
		return super.estDeplace(p, Character.toString(dir.getDirection()));
	}
	
	/**
	 * 
	 * @param p Prend en paramètre le Plateau de jeu p
	 * @param pos Prend en paramètre la Position de l'IAMonstre pos
	 * @return Renvoie la possibilité de la direction : VRAI si possible FAUX sinon
	 */
	private boolean dirPossible(Plateau p, Position pos, Direction tmp) {
		if(tmp == Direction.HAUT && pos.getY() - 1 < 0) return false;
		if(tmp == Direction.BAS && pos.getY() + 1 > p.getHauteur()) return false;
		if(tmp == Direction.GAUCHE && pos.getX() - 1 < 0) return false;
		if(tmp == Direction.DROITE && pos.getX() + 1 > p.getLargeur()) return false;
		return true;
	}
	
	/**
	 * 
	 * @param tmp Prend en paramètre une nouvelle valeur TEMPORAIRE de dir
	 * @return Renvoie VRAI est en cas d'échec
	 */
	private boolean iaStupide(Plateau p, Direction tmp) {
		Position tmpPos = null;
		Position pos = getPosition();
		switch(tmp) {
			case HAUT: if(pos.getY() - 1 >= 0) tmpPos = new Position(pos.getX(), pos.getY() - 1); break;
			case BAS: if(pos.getY() + 1 < p.getHauteur()) tmpPos = new Position(pos.getX(), pos.getY() + 1); break;
			case GAUCHE: if(pos.getX() - 1 >= 0) tmpPos = new Position(pos.getX() - 1, pos.getY()); break;
			case DROITE: if(pos.getX() + 1 < p.getLargeur()) tmpPos = new Position(pos.getX() + 1, pos.getY()); break;
		}
		if(pos != null) tmpPos = pos;
		return histPos.contains(tmpPos);
	}
}
