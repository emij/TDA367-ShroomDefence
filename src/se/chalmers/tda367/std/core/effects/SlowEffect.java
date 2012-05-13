package se.chalmers.tda367.std.core.effects;

/**
 * Represents a slowing effect.
 * <p>Duration: <b>5 sec</b>
 * <p>SpeedModifier: <b>10% * level</b>
 * <p>HealthModifier: <b>1.0</b>
 * <p>ArmorModifier: <b>1.0</b>
 * @author Johan Gustafsson
 * @date   23 Apr 2012
 */
public class SlowEffect extends AbstractEffect {
	
	/**
	 * Constructor able to scale the speed modifier based on given integer.
	 * @param level must be a positive integer above 0.
	 */
	public SlowEffect(int level) {
		super(5000, 1 - (0.5*Math.abs(level)), 1.0, 1.0);
		super.level = level;
	}
	
	@Override
	public SlowEffect getCopy() {
		return new SlowEffect(super.level);
	}
}
