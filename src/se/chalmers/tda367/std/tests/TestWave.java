package se.chalmers.tda367.std.tests;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.ConcurrentLinkedQueue;

import org.junit.Test;

import se.chalmers.tda367.std.core.Wave;
import se.chalmers.tda367.std.core.WaveItem;


/**
 * Unit test for {@link se.chalmers.tda367.std.core.Wave}.
 * @author Johan Andersson
 * @date   27 mar 2012
 */
public class TestWave {
	
	private Wave createBasicWave(int n){
		ConcurrentLinkedQueue<WaveItem> q = new ConcurrentLinkedQueue<WaveItem>();
		for(int i = 0; i<n; i++){
			//q.add(new WaveItem(new BasicEnemy(), i+1));
		}
		return new Wave(q);
	}


	@Test
	public void testGetNext() throws Exception {

		
		Wave w = createBasicWave(3);
		

		WaveItem	wi1 = w.getNext();
		WaveItem	wi2 = w.getNext();
		WaveItem    wi3 = w.getNext();

		assertTrue(wi1.getDelay() == 1);
		assertTrue(wi2.getDelay() == 2);
		assertTrue(wi3.getDelay() == 3);

	}

	@Test(expected = Exception.class)
	public void testGetNextException() throws Exception {
		Wave w = createBasicWave(1);
		w.getNext();
		w.getNext(); // Exception!

	}
	
	@Test
	public void testSize(){
		Wave w = createBasicWave(3);
		assertTrue(w.size() == 3);
		
		w = createBasicWave(0);
		assertTrue(w.size() == 0);
	}
}
