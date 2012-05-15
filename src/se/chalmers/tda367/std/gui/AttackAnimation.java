package se.chalmers.tda367.std.gui;


import se.chalmers.tda367.std.utilities.Position;


/**
 * Helper class for representing a towers attack.
 * @author Johan Gustafsson
 * @date   May 08, 2012
 */
class AttackAnimation {
	private int duration;
	private Position from, to;
	
	public AttackAnimation(Position from, Position to, int duration) {
		this.duration = duration;
		this.from = from;
		this.to = to;
	}
	
	public void decreaseDuration(int delta) {
		if(duration > 0) {
			//TODO: add correct implementation for relative timespan on attacks.
			duration -= delta*10;
		}
		else {
			duration = 0;
		}
	}
	
	public int getRemainigDuration() {
		return duration;
	}
	
	public Position getFromPos() { 
		return from;
	}
	
	public Position getToPos() {
		return to;
	}
}
