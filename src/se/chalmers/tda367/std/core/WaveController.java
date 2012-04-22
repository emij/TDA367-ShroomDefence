package se.chalmers.tda367.std.core;

import javax.swing.Timer;

/**
 * The class that contains the game logic for wave phase of the game.
 * @author Johan Andersson
 * @modified 
 * @date Apr 22, 2012
 */

public class WaveController {

	private GameBoard board;
	private Player player;
	private Timer gameLoop;
	private Timer releaseTimer;
	private WaveItem nextEnemy;

	public WaveController(GameBoard board, Player player) {
		this.board = board;
		this.player = player;
	}
	
	
	
	
}
