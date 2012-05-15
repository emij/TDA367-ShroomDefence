package se.chalmers.tda367.std.tests;

import se.chalmers.tda367.std.core.effects.AbstractEffect;
import se.chalmers.tda367.std.core.effects.IEffect;

class DummyEffect extends AbstractEffect {
	
	/**
	 * 
	 * duration 1000
	 * speedModifier 1.0
	 * healthModifier 1.0
	 * armorModifier 1.0
	 */
	public DummyEffect() {
		super(1000, 1.0, 1.0, 1.0);
	}

	@Override
	public IEffect getCopy() {
		return new DummyEffect();
	}

}
