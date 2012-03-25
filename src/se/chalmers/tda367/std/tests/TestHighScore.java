package se.chalmers.tda367.std.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import se.chalmers.tda367.std.core.Highscore;
import se.chalmers.tda367.std.core.Score;

public class TestHighScore {

	@Test
	public void testAddScore() {
		Highscore hs = new Highscore();
		Score[] scores = new Score[] {
			new Score("Apa", 10),
			new Score("Bepa", 5),
			new Score("Bepa", 15),
			new Score("Cepa", 1),
			new Score("Cepa", 1), // Should not be added
		};
		
		int added = 0;
		for(Score s : scores){
			if(hs.addScore(s))
				added++;
		}
		
		// The count should be the same as added and separated from 
		// the length of 'scores'.
		assertTrue(hs.size() == added && hs.size() == (scores.length -1));
		
		// The order is dependent of a correct implementation of 'Score'.
		// Not tested here.
	}

	@Test
	public void testGetScore() {
		Highscore hs = new Highscore();
		Score s1 = new Score("Apa", 1);
		Score s2 = new Score("Bepa", 2);
		hs.addScore(s1);
		hs.addScore(s2);
		
		assertEquals(hs.getScore("Apa"), s1);
		assertEquals(hs.getScore("Bepa"), s2);
		assertNull(hs.getScore("Does not exist"));
	}
	
	@Test
	public void testGetHighestScore(){
		Highscore hs = new Highscore();
		Score[] scores = new Score[] {
			new Score("Apa", 10),
			new Score("Bepa", 5),
			new Score("Bepa", 15),
			new Score("Cepa", 1),
		};
		for(Score s : scores)
			hs.addScore(s);
		
		assertTrue(hs.getHighestScore() == scores[2]); // == Bepa, 15.
	}
	
	@Test
	public void testGetLowestScore(){
		Highscore hs = new Highscore();
		Score[] scores = new Score[] {
			new Score("Apa", 10),
			new Score("Bepa", 5),
			new Score("Bepa", 15),
			new Score("Cepa", 1),
		};
		for(Score s : scores)
			hs.addScore(s);
		
		assertTrue(hs.getLowestScore() == scores[3]); // == Cepa, 1.
	}

	@Test
	public void testResetHighscore() {
		Highscore hs = new Highscore();
		hs.addScore(new Score("Apa", 1));
		hs.addScore(new Score("Bepa", 2));
		
		assertTrue(hs.size() == 2);
		hs.resetHighscore();
		assertTrue(hs.size() == 0);
	}

}
