package se.chalmers.tda367.std.core.effects;

/**
 * Represents an effect that reduces armor.
 * <p>Duration: <b>5 sec</b>
 * <p>SpeedModifier: <b>1.0</b>
 * <p>HealthModifier: <b>1.0</b>
 * <p>ArmorModifier: <b>-10% * level</b>
 * @author Johan Gustafsson
 * @date   23 Apr 2012
 */
public class ReduceArmorEffect extends AbstractEffect {
	
	/**
	 * Constructor able to scale the armor modifier based on given integer.
	 * @param level must be a positive integer above 0.
	 */
	public ReduceArmorEffect(int level) {
		super(5000, 1.0, 1.0, 1 - (0.1*Math.abs(level)));
	}
	
	@Override
	public ReduceArmorEffect getCopy() {
		return new ReduceArmorEffect(super.level);
	}
}

