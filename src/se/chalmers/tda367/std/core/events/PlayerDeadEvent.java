package se.chalmers.tda367.std.core.events;

import se.chalmers.tda367.std.core.Player;

/**
 * This event is fired when the player is dead and the game is over.
 * @author Emil Edholm
 * @date   May 13, 2012
 */
public final class PlayerDeadEvent {
	private final Player player;
	
	public PlayerDeadEvent(Player player) {
		this.player = player;
	}

	/**
	 * @return the dead player.
	 */
	public Player getPlayer() {
		return player;
	}
}
