package se.chalmers.tda367.std.core.events;

import se.chalmers.tda367.std.core.IPlayer;

/**
 * This event is fired when the player is dead and the game is over.
 * @author Emil Edholm
 * @date   May 13, 2012
 */
public final class PlayerDeadEvent {
	private final IPlayer player;
	
	public PlayerDeadEvent(IPlayer player) {
		this.player = player;
	}

	/**
	 * @return the dead player.
	 */
	public IPlayer getPlayer() {
		return player;
	}
}
