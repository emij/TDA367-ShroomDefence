package se.chalmers.tda367.std.tests;

import se.chalmers.tda367.std.core.effects.AbstractEffect;
import se.chalmers.tda367.std.core.effects.IEffect;

class DummyArmorIncEffect extends AbstractEffect {
	
	/**
	 * 
	 * duration 1000
	 * speedModifier 0.0
	 * healthModifier 0.0
	 * armorModifier 2.0
	 */
	public DummyArmorIncEffect() {
		super(1000, 0, 0, 2.0);
	}

	@Override
	public IEffect getCopy() {
		return new DummyEffect();
	}

}
