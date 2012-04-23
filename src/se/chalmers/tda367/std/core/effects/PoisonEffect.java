package se.chalmers.tda367.std.core.effects;

/**
 * Represents a poison effect.
 * <p>Duration: <b>5</b>
 * <p>SpeedModifier: <b>0</b>
 * <p>HealthModifier: <b>-10*level</b>
 * <p>ArmorModifier: <b>0</b>
 * @author Johan Gustafsson
 * @date   23 Apr 2012
 */
public class PoisonEffect extends AbstractEffect {
	
	/**
	 * Constructor able to scale the health modifier based on given integer.
	 * @param level must be a positive integer above 0.
	 */
	public PoisonEffect(int level) {
		super(5, 0, -10*Math.abs(level), 0);
	}
}
