package competences;

/**
 * 
 * La classe Shield est une competence du monstre permettant d'etre invulnerable pendant 1 tour
 * @author Quentin Delmarre, Robin Gallifa
 *
 */
public class Shield extends Competences {

	public Shield() {
		this.setId(5);
		this.setElement("protection");
		this.setEffet(1); //Protection totale -> 1 tour
		this.setDuree(1);
	}
}
