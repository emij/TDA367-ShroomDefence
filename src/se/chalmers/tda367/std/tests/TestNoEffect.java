package se.chalmers.tda367.std.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import se.chalmers.tda367.std.core.effects.NoEffect;

public class TestNoEffect {

	@Before
	public void setUp() {
		
	}
	
	@Test
	public void testDecrementDuration() {
		NoEffectTest effect = new NoEffectTest(500, 100);
		assertFalse(effect.ready());
		
		effect.decrementDuration(100);
		assertTrue(effect.ready());
		
		effect.decrementDuration(99);
		assertFalse(effect.ready());
		
		effect.decrementDuration(1);
		assertTrue(effect.ready());
		
		effect.decrementDuration(100);
		assertTrue(effect.ready());
		
		// Duration should never be lower than zero.
		effect.decrementDuration(10000);
		assertTrue(effect.getDuration() == 0);
	}

	@Test
	public void testReady() {
		// Defaults to never ready
		NoEffectTest effect = new NoEffectTest();
		assertFalse(effect.ready());
		
		// Interval = 0 equals always ready.
		effect = new NoEffectTest(10, 0);
		assertTrue(effect.ready());
		
		// No time passed means not ready.
		effect = new NoEffectTest(20, 10);
		assertFalse(effect.ready());
		
		// Reached interval
		effect.decrementDuration(10);
		assertTrue(effect.ready());
		
		// Reached end of effect also 2nd interval.
		effect.decrementDuration(10);
		assertFalse(effect.ready());
	}
	
	/* Used to get access to the protected methods. */
	private static class NoEffectTest extends NoEffect {
		
		public NoEffectTest() {
			super();
		}
		
		public NoEffectTest(int duration, int interval) {
			super(duration, interval);
		}
		
		@Override
		public boolean ready() {
			return super.ready();
		}
	}
}
