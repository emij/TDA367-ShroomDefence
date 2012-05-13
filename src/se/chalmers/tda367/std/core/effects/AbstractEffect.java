package se.chalmers.tda367.std.core.effects;

/**
 * A skeleton implementation of IEffect.
 * @author Johan Gustafsson
 * @modifed Johan Andersson
 * @date Apr 23, 2012
 */
public abstract class AbstractEffect implements IEffect {

	private double duration,
					 speedMod,
					 healthMod,
					 armorMod;
	protected int level;

	public AbstractEffect(double duration, double speedModifier, double healthModifier, double armorModifier) {
		this.duration 	= duration;
		this.speedMod 	= speedModifier;
		this.healthMod 	= healthModifier;
		this.armorMod	= armorModifier;
	}
	
	@Override
	public double getDuration() {
		return duration;
	}

	@Override 
	public void decrementDuration(double milisec) {
		duration -= milisec;
	}

	@Override
	public double getSpeedModifier() {
		return speedMod;
	}

	@Override
	public double getHealthModifier() {
		return healthMod;
	}

	@Override
	public double getArmorModifier() {
		return armorMod;
	}
	
	@Override
	public void setDuration(double duration) {
		this.duration = duration;
	}
}
