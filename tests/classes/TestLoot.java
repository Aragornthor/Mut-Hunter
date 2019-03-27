package classes;

import static org.junit.Assert.*;

import org.junit.Test;

import competences.Competences;
import competences.Missile;

public class TestLoot {

	public Personnage p = new Personnage("chasseur",new Position(0,0));
	public Loot l = new Loot();
	
	@Test
	public void testChangeCompetence() {
		Competences[] comp = p.getCompetences();
		assertTrue(comp[1] == null);
		System.out.println("Avant");
		System.out.println(p);
		l.changeCompetence(p);
		System.out.println("Apres");
		System.out.println(p);
		assertTrue(comp[1] != null);
		int id1 = comp[0].getId();
		int id2 = comp[1].getId();
		l.changeCompetence(p, 2);
		System.out.println("Enfin");
		System.out.println(p);
		assertTrue(comp[1].getId() != id1 && comp[1].getId() != id2);
	}

}
