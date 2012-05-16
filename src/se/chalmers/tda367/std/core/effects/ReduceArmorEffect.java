package se.chalmers.tda367.std.core.effects;

/**
 * Represents an effect that reduces armor.
 * <p>Duration: <b>5 sec</b>
 * <p>ArmorModifier: <b>-10% * level</b>
 * @author Johan Gustafsson
 * @modified Emil Edholm (May 16, 2012)
 * @date   23 Apr 2012
 */
public class ReduceArmorEffect extends NoEffect {
	
	private final int level;
	
	/**
	 * Constructor able to scale the armor based on given integer.
	 * @param level must be a positive integer > 0.
	 */
	public ReduceArmorEffect(int level) {
		super(5000, 1); // Always ready
		this.level = level;
	}
	
	@Override
	public int modifyArmor(int baseArmor) {
		if(!ready()) 
			return baseArmor; // Do nothing...
		
		return (int)(baseArmor * (1 - (0.1 * level)));
	}
	
	@Override
	public ReduceArmorEffect clone() {
		return (ReduceArmorEffect) super.clone();
	}
}

