package se.chalmers.tda367.std.core.effects;

/**
 * Represents a poison effect.
 * <p>Duration: <b>5 sec</b>
 * <p>SpeedModifier: <b>1.0</b>
 * <p>HealthModifier: <b>-10*level</b>
 * <p>ArmorModifier: <b>1.0</b>
 * @author Johan Gustafsson
 * @date   23 Apr 2012
 */
public class PoisonEffect extends AbstractEffect {
	
	/**
	 * Constructor able to scale the health modifier based on given integer.
	 * @param level must be a positive integer above 0.
	 */
	public PoisonEffect(int level) {
		super(5000, 1.0, -10*Math.abs(level), 1.0);
		super.level = level;
	}
	
	@Override
	public PoisonEffect getCopy() {
		return new PoisonEffect(super.level);
	}
}
