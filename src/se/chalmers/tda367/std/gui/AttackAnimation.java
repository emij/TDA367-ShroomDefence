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
	
	public AttackAnimation(Position from, Position to, int duration) {
		this.duration = duration;
		this.from = from;
		this.to = to;
		timeSinceLastUpdate = 0;
		isAttackOver = false;
	}
	
	public void updateDuration(int delta) {
		timeSinceLastUpdate += delta;
		if(timeSinceLastUpdate > duration) {
			isAttackOver = true;
		}
	}
	
	public boolean isAttackOver() {
		return isAttackOver;
	}
	
	public Position getFromPos() { 
		return from;
	}
	
	public Position getToPos() {
		return to;
	}
}
