package IA;

import classes.Monstre;
import classes.Plateau;
import classes.Position;

public class IAMonstreGUI extends Monstre implements IA {
	
	private Direction dir;
	
	public IAMonstreGUI(Position p) {
		super(p);
		this.dir = Direction.HAUT;
	}

	@Override
	public void jouer(Plateau p) {
				
	}
	
	private boolean dirPossible(Plateau p, Position pos) {
		if(dir == Direction.HAUT && pos.getY() - 1 < 0) return false;
		if(dir == Direction.BAS && pos.getY() + 1 >= p.getHauteur()) return false;
		if(dir == Direction.GAUCHE && pos.getX() - 1 < 0) return false;
		if(dir == Direction.DROITE && pos.getX() + 1 >= p.getLargeur()) return false;
		
		return true;
	}
	
	@Override
	public boolean estMort() {
		return false;
	}
	
}
