package classes;

import static org.junit.Assert.*;

import org.junit.Test;

import competences.Competences;

public class TestLoot {

	public Personnage p = new Personnage("chasseur",new Position(0,0));
	public Loot l = new Loot();
	
	@Test
	public void testChangeCompetence() {
		Competences[] comp = p.getCompetences();
		assertTrue(comp[1] == null);
		comp = p.getCompetences();
		System.out.println("Avant");
		l.changeCompetence(p);
		System.out.println("Apres");
		//assertTrue(comp[1] != null);
	}

}
