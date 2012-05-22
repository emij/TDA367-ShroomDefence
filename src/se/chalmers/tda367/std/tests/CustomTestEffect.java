package se.chalmers.tda367.std.tests;

import se.chalmers.tda367.std.core.effects.NoEffect;

public class CustomTestEffect extends NoEffect {

	private final float speedMod, healthMod, armorMod;
	
	public CustomTestEffect(int duration, int interval, float speedMod, float healthMod, float armorMod) {
		super(duration, interval);
		this.speedMod = speedMod;
		this.healthMod = healthMod;
		this.armorMod = armorMod;
	}

	@Override
	public float modifySpeed(float baseSpeed) {
		return baseSpeed * speedMod;
	}

	@Override
	public int modifyHealth(int baseHealth) {
		return (int)(baseHealth * healthMod);
	}

	@Override
	public int modifyArmor(int baseArmor) {
		return (int)(baseArmor * armorMod);
	}
	
	
	
}
