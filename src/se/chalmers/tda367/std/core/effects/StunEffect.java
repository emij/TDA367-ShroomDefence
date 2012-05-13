package se.chalmers.tda367.std.core.effects;

/**
 * Represents a slowing effect.
 * <p>Duration: <b>level * sec</b>
 * <p>SpeedModifier: <b>0</b>
 * <p>HealthModifier: <b>1.0</b>
 * <p>ArmorModifier: <b>1.0</b>
 * @author Johan Andersson
 * @date   13 maj 2012
 */
public class StunEffect extends AbstractEffect {
	
	/**
	 * Constructor able to scale the speed modifier based on given integer.
	 * @param level must be a positive integer above 0.
	 */
	public StunEffect(int level) {
		super(level * 1000, 0, 1.0, 1.0);
	}
	
	@Override
	public String toString() {
		return "StunEffect";
	}
}
