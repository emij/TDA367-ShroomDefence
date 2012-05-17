package se.chalmers.tda367.std.core.effects;

/**
 * Represents a slowing effect.
 * <p>Duration: <b>5 sec</b>
 * <p>SpeedModifier: <b>10% * level</b>
 * @author Johan Gustafsson
 * @modified Emil Edholm (May 16, 2012)
 * @date   23 Apr 2012
 */
public class SlowEffect extends NoEffect {
	
	private final int level;
	/**
	 * Constructor able to scale the speed modifier based on given integer.
	 * @param level must be a positive integer above 0.
	 */
	public SlowEffect(int level) {
		super(5000, 1); // Always ready means always slowing during whole duration.
		this.level = level;
	}
	
	@Override
	public float modifySpeed(float baseSpeed) {
		if(!ready())
			return baseSpeed; // Do nothing.
		
		return (float)(baseSpeed * (1 - (0.5 * level)));
	}
	
	@Override
	public SlowEffect clone() {
		return (SlowEffect)super.clone();
	}
}
