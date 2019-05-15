package competences;

import classes.Personnage;
import classes.Plateau;
import classes.Position;

/**
 * 
 * La classe IEM est une competence du chasseur immobilisant le monstre pendant 1 tour
 * @author Quentin Delmarre, Robin Gallifa
 *
 */
public class IEM  extends Competences {
	
	/**
	 * Constructeur de la compétence IEM
	 */
	public IEM() {
		this.setId(1);
		this.setElement("deplacement");
		this.setEffet(0);
		this.setDuree(1);
		this.cout = 40;
	}

	@Override
	public void utilisation(Plateau p, Personnage perso, Personnage cible) {
		Position tmp = new Position(perso.getPosition().getX()-1, perso.getPosition().getY()-1);
		
		int i = tmp.getX();
		int j = tmp.getY();
		boolean flag = false;
		while(i<tmp.getX()+3 && !flag) {
			while (j<tmp.getY()+3 && !flag){
				if((new Position(i,j)).equals(cible.getPosition())) {
					if(cible.getStatut() != Statut.Shield) {
						if(cible.getStatut().equals(Statut.Stun)) {
							cible.getStatut().setTour(cible.getStatut().getNbTour()+this.getDuree());
						}else {
							cible.setStatut(Statut.Stun);
							cible.getStatut().setTour(this.getDuree());
						}
					}
					flag = true;
				}
				j++;
			}
			j=tmp.getY();
			i++;
		}
		perso.rechargeEnergie(-(this.cout));
	}
}
