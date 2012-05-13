package se.chalmers.tda367.std.core.events;

import se.chalmers.tda367.std.utilities.Position;

/**
 * @author Emil Edholm
 * @date   Apr 27, 2012
 */
public final class TowerShootingEvent {
	private final Position from;
	private final Position to;
	
	public TowerShootingEvent(Position from, Position to){
		this.from = from;
		this.to = to;
	}
	
	/**
	 * The origin position of the projectile.
	 */
	public Position getFromPosition() {
		return new Position(from);
	}
	
	/**
	 * The destination position of the projectile.
	 */
	public Position getToPosition() {
		return new Position(to);
	}
}
