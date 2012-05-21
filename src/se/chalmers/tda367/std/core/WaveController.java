package se.chalmers.tda367.std.core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.Timer;

import se.chalmers.tda367.std.core.enemies.IEnemy;
import se.chalmers.tda367.std.core.events.PlayerDeadEvent;
import se.chalmers.tda367.std.core.events.WaveEndedEvent;
import se.chalmers.tda367.std.core.tiles.IBoardTile;
import se.chalmers.tda367.std.core.tiles.towers.IAttackTower;
import se.chalmers.tda367.std.utilities.EventBus;
import se.chalmers.tda367.std.utilities.Position;


/**
 * The class that contains the game logic for wave phase of the game.
 * @author Johan Andersson
 * @modified Emil Edholm (May 13, 2012)
 * @modified Johan Gustafsson (May 12, 2012)
 * @date Apr 22, 2012
 */
class WaveController {

	/** The delay (in milliseconds) before the first enemy is placed on the game board */
	private static final int INITIAL_WAVE_DELAY = 100;
	
	private final IGameBoard board;
	private final IPlayer player;
	private final Timer releaseTimer;
	private Wave wave;
	private boolean waveHasBeenCompleted;
	

	public WaveController(IGameBoard board, IPlayer player) {
		this.board = board;
		this.player = player;
		waveHasBeenCompleted = false;
		releaseTimer = new Timer(INITIAL_WAVE_DELAY, new WaveReleaseTimerListener());
	}

	/**
	 * Starts a new wave
	 */
	public void startWave(Wave wave){
		this.wave = wave;
		releaseTimer.start();
	}

	/**
	 * Stops the release of enemies from the current wave
	 */
	public void endWaveRelease(){
		releaseTimer.stop();
		waveHasBeenCompleted = true;
	}
	
	/**
	 * The loop that updates the the wave related bits of the game.
	 * Does things like moving the enemies, making the towers shoot at the enemies etc.
	 * @param delta - the amount of time (in milliseconds) since the last update.
	 */
	public void updateWaveRelated(final int delta){
		if(!isPlayerDead()) {
			moveEnemies(delta);
			shootAtEnemiesInRange(delta);
		}
	}

	/**
	 * Releases the next enemy in queue from the wave
	 */
	private void releaseEnemy(){
		WaveItem next = wave.getNext();
		if(next != null) {
			releaseTimer.setDelay(next.getDelay());
			addEnemy(next);
		} else {
			endWaveRelease();
		}
	}
 
	/** Add a enemy to the game board from a {@code WaveItem} */
	private void addEnemy(WaveItem wi){
		if(wi == null) {
			return;
		}
		
		EnemyList enemies = board.getEnemies();
		enemies.add(wi.getEnemy());
	}

	/**
	 * Moves all the enemies on the GameBoard, towards the base.
	 * @param delta - the amount of time (in milliseconds) since the last update.	
	 */
	private void moveEnemies(final int delta){
		EnemyList enemies = board.getEnemies();
		if(enemies.isEmpty() && waveHasBeenCompleted) {
			EventBus.INSTANCE.post(new WaveEndedEvent());
			waveHasBeenCompleted = false;
			return;
		}
		
		for(IEnemy enemy : enemies) {
			enemy.moveTowardsWaypoint(delta);
		}
	}
	
	/**
	 * Towers fires at enemies in range.
	 * @param delta - the amount of time (in milliseconds) since the last update.
	 */
	private void shootAtEnemiesInRange(final int delta){ // TODO: Use AttackEntity instead
		int tileScale = Properties.INSTANCE.getTileScale();
		IPlayerCharacter character = player.getCharacter();
		for(int x = 0; x < board.getWidth(); x++){
			for(int y = 0; y <board.getHeight(); y++){
				IBoardTile tile = board.getTileAt(x, y);
				if(tile instanceof IAttackTower){
					IAttackTower attackTower = (IAttackTower) tile;
					if(attackTower.isAttackReady(delta)) {
						shoot(attackTower, new Position(x*tileScale, y*tileScale));
					}
				}
			}
		}
		if(character.isAttackReady(delta)) {
			character.shoot(board.getAttackables(character.getPos(), 50), character.getPos());
		}
	}
	
	
	/**
	 * Handles the individual shots.
	 */
	private void shoot(IAttackTower tile, Position pos) {
		int radius = tile.getRadius() * Properties.INSTANCE.getTileScale();
		List<Attackable> enemies = board.getAttackables(pos, radius);
		tile.shoot(enemies, pos);
	}

	/** Whether or not the player has died */
	private boolean isPlayerDead(){
		if(board.getPlayerBaseHealth() <= 0){
			playerDead();
			return true;
		}
		return false;
	}

	/** What to do when the player is dead. */
	private void playerDead(){
		Logger.getLogger("se.chalmers.tda367.std.core").info("Player dead, game over");
		EventBus.INSTANCE.post(new PlayerDeadEvent(player));
		EventBus.INSTANCE.post(new WaveEndedEvent());
	}
	
	private class WaveReleaseTimerListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			releaseEnemy();
		}

	}
}
