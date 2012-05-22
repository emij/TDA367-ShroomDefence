package se.chalmers.tda367.std.gui;


import se.chalmers.tda367.std.utilities.Position;


/**
 * Helper class for representing a towers attack and duration.
 * This will be used by {@code GameplayRenderer} to render attacks during a given duration.
 * @author Johan Gustafsson
 * @date   May 08, 2012
 */
class AttackAnimation {
	private int duration;
	private int timeSinceLastUpdate;
	private boolean isAttackOver;
	private Position from, to;
	
	/**
	 * Create a new attack animation holder from the given from position,
	 * to position and from position.
	 * @param from Position from where the attack was fired.
	 * @param to Position the attack was fired upon.
	 * @param duration - the duration of the attack.
	 */
	public AttackAnimation(Position from, Position to, int duration) {
		this.duration = duration;
		this.from = from;
		this.to = to;
		timeSinceLastUpdate = 0;
		isAttackOver = false;
	}
	
	/**
	 * Updates the attacks duration with the time since last update.
	 * If the accumulated duration is longer than the duration of the attack it will signal the attack as over.
	 * @param delta the time since last update in milliseconds.
	 */
	public void updateDuration(int delta) {
		timeSinceLastUpdate += delta;
		if(timeSinceLastUpdate > duration) {
			isAttackOver = true;
		}
	}
	
	/**
	 * Checks to see if the attack is over or still going.
	 * @return true if the attack is over, false otherwise.
	 */
	public boolean isAttackOver() {
		return isAttackOver;
	}
	
	/**
	 * Get the position from where the attack was fired.
	 * @return position from where the attack was fired.
	 */
	public Position getFromPos() { 
		return from;
	}
	
	/**
	 * Get the position from where the attack landed.
	 * @return position from where the attack landed.
	 */
	public Position getToPos() {
		return to;
	}
}
