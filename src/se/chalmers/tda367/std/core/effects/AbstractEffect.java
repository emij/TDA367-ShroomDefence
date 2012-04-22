package se.chalmers.tda367.std.core.effects;

/**
 * A skeleton implementation of IEffect.
 * @author Johan Gustafsson
 * @date Apr 23, 2012
 */
public abstract class AbstractEffect implements IEffect {
	
	private int duration;
	private int speedMod;
	private int healthMod;
	private int armorMod;
	
	public AbstractEffect(int duration, int speedModifier, int healthModifier, int armorModifier) {
		this.duration 	= duration;
		this.speedMod 	= speedModifier;
		this.healthMod 	= healthModifier;
		this.armorMod	= armorModifier;
	}
	
	@Override
	public int getDuration() {
		return duration;
	}
	
	@Override
	public int getSpeedModifier() {
		return speedMod;
	}
	
	@Override
	public int getHealthModifier() {
		return healthMod;
	}
	
	@Override
	public int getArmorModifier() {
		return armorMod;
	}
	
}
