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
		Position pos = super.getPosition();
		
	}
	
	private boolean dirPossible() {
		
	}
	
	@Override
	public boolean estMort() {
		return false;
	}
	
}
