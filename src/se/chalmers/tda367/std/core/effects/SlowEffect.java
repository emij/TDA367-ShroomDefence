package se.chalmers.tda367.std.core.effects;

/**
 * Represents a slowing effect.
 * <p>Duration: <b>5</b>
 * <p>SpeedModifier: <b>-1*level</b>
 * <p>HealthModifier: <b>0</b>
 * <p>ArmorModifier: <b>0</b>
 * @author Johan Gustafsson
 * @date   23 Apr 2012
 */
public class SlowEffect extends AbstractEffect {
	
	/**
	 * Constructor able to scale the speed modifier based on given integer.
	 * @param level must be a positive integer above 0.
	 */
	public SlowEffect(int level) {
		super(5, -1*Math.abs(level), 0, 0);
	}
}
