package se.chalmers.tda367.std.core.effects;

/**
 * Represents the standard effect; on that does nothing at all.
 * Any sub-classes should Override {@code clone()} and always use {@code ready()} 
 * when overriding one of the modify-methods.
 * 
 * @see {@link se.chalmers.tda367.std.core.effects.NoEffect.ready()}
 * @author Emil Edholm
 * @date   May 16, 2012
 */
public class NoEffect implements IEffect, Cloneable {
	private static NoEffect instance;
	
	private int duration, timePassed;
	private final int initialDuration, interval;
	
	private final boolean alwaysReady;
	
	/** Create a new {@code NoEffect} with no duration and no interval*/
	public NoEffect() {
		this(0, 0);
	}
	
	/**
	 * Creates a new effect with specified duration and interval. The number of times
	 * the effect will be applied is roughly {@code duration / interval}
	 * @param duration - the duration in milliseconds.
	 * @param interval - the interval between readiness, in milliseconds. A interval of 0 means always ready
	 */
	protected NoEffect(int duration, int interval) {
		this.initialDuration = duration;
		this.duration        = duration;
		
		// Remove the possibility of NaN and negative numbers.
		this.interval    = (interval > 0) ? interval : 0;
		this.alwaysReady = (interval == 0) ? true : false;
		this.timePassed  = 0;
	}
	
	/** Returns a shared instance of the {@code NoEffect} with no interval and no duration. */
	public static NoEffect getInstance() {
		if(instance == null) 
			instance = new NoEffect();
		
		// This class can share instances since in it's default
		// state it is not mutable.
		return instance;
	}
	
	@Override
	public double getDuration() { return duration; }

	@Override
	public void resetDuration() {
		duration = initialDuration;
	}
	
	/**
	 * Whether or not the effect is ready to be applied.
	 * This is essentially a check to see if the effect has reached the interval.
	 * <p>This should <b>always</b> be used when overriding one of the modify methods, since this also
	 * assures that the effect never is ready when the duration has reached zero.</p>
	 * @return true if ready to be applied, else false.
	 */
	protected boolean ready() {
		if(duration == 0 || timePassed < interval )
			return false;
		
		if(alwaysReady)
			return true;
		
		if(timePassed >= interval) {
			timePassed = 0;
		}
		return true;
	}
	
	@Override
	public void decrementDuration(int millisec) {
		duration -= millisec;
		timePassed += millisec;
		
		if(duration < 0)
			duration = 0;
	}

	@Override
	public float modifySpeed(float baseSpeed) { return baseSpeed; }

	@Override
	public int modifyHealth(int baseHealth) { return baseHealth; }

	@Override
	public int modifyArmor(int baseArmor) { return baseArmor;}
	
	@Override
	public NoEffect clone() {
		try {
			return (NoEffect) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new AssertionError();  // Can't happen
		}
	}
}
