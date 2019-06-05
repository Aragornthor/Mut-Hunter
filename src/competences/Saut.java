package competences;

import classes.Personnage;
import classes.Plateau;
import classes.Position;
import javafx.scene.image.Image;

/**
 * 
 * La classe Saut est une competence du monstre permettant au monstre d'assomer le chasseur pendant 2 tour ce qui l'immobilise
 * @author Quentin Delmarre, Robin Gallifa
 *
 */
public class Saut extends Competences {

	/**
	 * Constructeur de la compétence Saut
	 */
	public Saut() {
		this.setId(4);
		this.setNom("Saut");
		this.setDuree(2);
		this.cout = 30;
		this.setIcon(new Image("file:ressources/images/boutonSaut.png"));
	}

	@Override
	public void utilisation(Plateau p, Personnage perso, Personnage cible, Position tmp) {
		
		perso.setPosition(tmp);
		
		Position zone = new Position(perso.getPosition().getX()-1, perso.getPosition().getY()-1);
		
		int i = zone.getX();
		int j = zone.getY();
		boolean flag = false;
		while(i<zone.getX()+3 && !flag) {
			while (j<zone.getY()+3 && !flag){
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
			j=zone.getY();
			i++;
		}
		perso.rechargeEnergie(-(this.cout));
	}
}
