package se.chalmers.tda367.std.tests;

import se.chalmers.tda367.std.core.effects.AbstractEffect;
import se.chalmers.tda367.std.core.effects.IEffect;

class DummyEffectSecond extends AbstractEffect {
	
	/**
	 * 
	 * duration 1000
	 * speedModifier 0
	 * healthModifier 0
	 * armorModifier 0
	 */
	public DummyEffectSecond() {
		super(1000, 0, 0, 0);
	}

	@Override
	public IEffect getCopy() {
		return new DummyEffect();
	}

}
