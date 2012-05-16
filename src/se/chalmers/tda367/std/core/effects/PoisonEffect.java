package se.chalmers.tda367.std.core.effects;

/**
 * Represents a poison effect.
 * <p>Duration: <b>5 sec</b>
 * <p>HealthModifier: <b>-10 * level</b>
 * @author Johan Gustafsson
 * @modified Emil Edholm (May 16, 2012)
 * @date   23 Apr 2012
 */
public class PoisonEffect extends NoEffect {
	
	private final int level;
	
	/**
	 * Constructor able to scale the health modifier based on given integer.
	 * @param level must be a positive integer > 0.
	 */
	public PoisonEffect(int level) {
		super(5000, 500);
		this.level = level;
	}
	
	@Override
	public int modifyHealth(int baseHealth) {
		if(!ready())
			return baseHealth; // Do nothing...
		
		return baseHealth - (10 * level);
	}
	
	@Override
	public PoisonEffect clone() {
		return (PoisonEffect) super.clone();
	}
}
