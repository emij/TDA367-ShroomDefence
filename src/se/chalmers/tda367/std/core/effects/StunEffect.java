package se.chalmers.tda367.std.core.effects;

/**
 * Represents a Stun effect (Enemies will be immovable)
 * <p>Duration: <b>level * sec</b>
 * <p>SpeedModifier: <b>0</b>
 * @author Johan Andersson
 * @modified Emil Edholm (May 16, 2012)
 * @date   13 maj 2012
 */
public class StunEffect extends NoEffect {
	
	/**
	 * Constructor able to scale the duration based on given integer.
	 * @param level - must be a positive integer above 0.
	 */
	public StunEffect(int level) {
		super(level * 1000, 1); // Always slow during the whole duration.
	}
	
	@Override
	public float modifySpeed(float baseSpeed) {
		if(!ready())
			return baseSpeed;
		
		return 0;
	}
	
	@Override
	public StunEffect clone() {
		return (StunEffect)super.clone();
	}
}
