package classes;

import java.util.Scanner;

import competences.Competences;
import competences.Statut;
import javafx.scene.image.Image;

/**
 * 
 * La classe Personnage est une classe abstraite regroupant les attributs et les méthodes communs aux personnages
 * @author Quentin Delmarre, Xavier Lezzoche, Robin Gallifa
 *
 */
public abstract class Personnage {
	static Scanner reader = new Scanner(System.in);
	private String type;
	private int energie;
	private int maxEnergie;
	private Position position;
	private Competences[] competences;
	private int deplacement;
	private int vie;
	private Statut statut;
	
	/**
	 * Constructeur du Personnage avec des paramètres prédéfinis
	 * @param type Prend en paramètre le type du personnage
	 * @param p Prend en paramètre la Position du personnage
	 */
	public Personnage(Position p) {
		this.energie = 75;
		this.position = p;
		this.deplacement = 1;
		this.vie = 1;
		this.maxEnergie = 100;
		this.statut = Statut.Vivant;
	}
	
	/**
	 * 
	 * @return Retourne le niveau d'énergie du personnage
	 */
	public int getEnergie() {
		return energie;
	}
	
	abstract public Image getImage();

	/**
	 * 
	 * @return Retourne le niveau maximum du personnage
	 */
	public int getMaxEnergie() {
		return maxEnergie;
	}

	/**
	 * 
	 * @return Retourne la position du personnage sur le Plateau
	 */
	public Position getPosition() {
		return position;
	}
	
	/**
	 * Remplace la position du joueur par la position passé en parametre.
	 * @param p est la nouvelle position
	 */
	public void setPosition(Position p) {
		this.position.setX(p.getX());
		this.position.setY(p.getY());
	}

	/**
	 * 
	 * @return Retourne la liste de compétences du personnage
	 */
	public Competences[] getCompetences() {
		return competences;
	}

	/**
	 * 
	 * @return Retourne la vitesse de déplacement du personnage
	 */
	public int getDeplacement() {
		return deplacement;
	}

	/**
	 * 
	 * @return Retourne le statut vitale du personnage
	 */
	public int getVie() {
		return vie;
	}
	
	/**
	 * Permet de redemander un deplacement tant que ce que donne le joueur n'est pas bon
	 * @param p Prend un plateau en paramètre
	 */
	public abstract void seDeplace(Plateau p);
	
	/**
	 * Permet au joueur d'entrer son déplacement(zqsd)
	 * @param p Prend un plateau en paramètre
	 * @return Retourne VRAI si le mouvement est effectué, sinon FAUX
	 */
	protected boolean estDeplaceJoueur(Plateau p) {
		String entree = reader.nextLine();
		return estDeplace(p, entree);
	}
	
	/**
	 * Permet un déplacement
	 * @param p Prend un plateau en paramètre
	 * @return Retourne VRAI si le mouvement est effectué, sinon FAUX
	 */
	public boolean estDeplace(Plateau p, String entree) {
		
		//System.out.println("Debug estDeplace(...)");
		
		if(entree.equalsIgnoreCase("z")) { //SE DEPLACE VERS LE HAUT
			if(this.position.getY()-1<0) {
				return false;
			} else {
				//System.out.println("Z detect");
				p.setCaseNormal(this.position);
				this.position.setY(this.position.getY()-1);
				if(p.getCase(this.position).getEstPortail()) this.setPosition(p.teleportation(this.position));
				if(p.getCase(this.getPosition()).getLoot()) {
					p.ajoutCompetence(this);
					p.setDernierLoot();
				}
				changeCase(p);
				return true;
			}
		}
		
		if(entree.equalsIgnoreCase("s")) { //SE DEPLACE VERS LE BAS
			if(this.position.getY()+1>= p.getHauteur()) {
				return false;
			} else {
				//System.out.println("S detect");
				p.setCaseNormal(this.position);
				this.position.setY(this.position.getY()+1);
				if(p.getCase(this.position).getEstPortail()) this.setPosition(p.teleportation(this.position));
				if(p.getCase(this.getPosition()).getLoot()) {
					p.ajoutCompetence(this);
					p.setDernierLoot();
				}
				changeCase(p);
				return true;
			}
		}
		
		if(entree.equalsIgnoreCase("q")) { //SE DEPLACE VERS LA GAUCHE
			if(this.position.getX()-1<0) {
				return false;
			} else {
				//System.out.println("Q detect");
				p.setCaseNormal(this.position);
				this.position.setX(this.position.getX()-1);
				if(p.getCase(this.position).getEstPortail()) this.setPosition(p.teleportation(this.position));
				if(p.getCase(this.getPosition()).getLoot()) {
					p.ajoutCompetence(this);
					p.setDernierLoot();
				}
				changeCase(p);
				return true;
			}
		}
		
		if(entree.equalsIgnoreCase("d")) { //SE DEPLACE VERS LA DROITE
			if(this.position.getX()+1>=p.getLargeur()) {
				return false;
			} else {
				//System.out.println("D detect");
				p.setCaseNormal(this.position);
				this.position.setX(this.position.getX()+1);
				if(p.getCase(this.position).getEstPortail()) this.setPosition(p.teleportation(this.position));
				if(p.getCase(this.getPosition()).getLoot()) {
					p.ajoutCompetence(this);
					p.setDernierLoot();
				}
				changeCase(p);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Change la case où se trouve le personnage
	 * @param p Prend un plateau en paramètre
	 */
	public abstract boolean changeCase(Plateau p);

	public void rechargeEnergie() {
		rechargeEnergie(10);
	}
	
	/**
	 * 
	 * @param i Prend en paramètre la quantité d'énergie à ajouter
	 */
	public void rechargeEnergie(int i) {
		if((this.energie + i) > maxEnergie) this.energie = maxEnergie;
		else this.energie += i;
	}

	/**
	 * 
	 * @return Retourne le type du personnage
	 */
	public String getType() {
		return type;
	}
	
	public void setTableauCompetences(Competences[] comps) {
		this.competences = comps;
	}
	
	/**
	 * Permet de changer l'une des deux competences du personnage avec la competence passee en parametre
	 * @param comp Competence que l'on veut mettre pour le personnage
	 * @param nb Nombre de la competence dans le tableau (soit 0 ou 1)
	 */
	public void setCompetence(Competences comp, int nb) {
		this.competences[nb] = comp;
	}
	
	/**
	 * @return le joueur en version texte
	 */
	public String toString() {
		String res = this.type+" : "+this.energie+"/"+this.maxEnergie+"\nCompetences : \n";
		for(Competences c:this.competences) {
			if(c != null) res += c.toString()+"\n";
		}
		res+="Statut : "+this.statut.toString();
		return res;
	}
	
	/**
	 * @return le type du joueur soit monstre soit chasseur
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	public Statut getStatut() {
		return this.statut;	
	}
	
	public void setStatut(Statut s) {
		this.statut = s;
	}
	
	public void setDeplacement(int d) {
		this.deplacement = d;
	}
	
	public boolean gestionStatuts() {
		if(this.getStatut() == Statut.Stun) {
			this.getStatut().setTour(this.getStatut().getNbTour()-1);
			if(this.getStatut().getNbTour() == -1) {
				this.setStatut(Statut.Vivant);
				this.getStatut().setTour(0);
			}
			return false;
		}
		else if(this.getStatut() == Statut.Acide) {
			this.rechargeEnergie(-10);
			this.getStatut().setTour(this.getStatut().getNbTour()-1);
			if(this.getStatut().getNbTour() == -1) {
				this.setStatut(Statut.Vivant);
				this.getStatut().setTour(0);
			}
		}
		else if(this.getStatut() == Statut.Shield) {
			this.getStatut().setTour(this.getStatut().getNbTour()-1);
			if(this.getStatut().getNbTour() == -1) {
				this.setStatut(Statut.Vivant);
				this.getStatut().setTour(0);
			}
		}
		return true;
	}
	
}
