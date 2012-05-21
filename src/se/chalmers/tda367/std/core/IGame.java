package se.chalmers.tda367.std.core;

import com.google.inject.ImplementedBy;

import se.chalmers.tda367.std.core.tiles.towers.ITower;
import se.chalmers.tda367.std.utilities.BoardPosition;
import se.chalmers.tda367.std.utilities.Position;

/**
 * The interface the exposes the game functionality.
 * @author Emil Edholm
 * @date   May 21, 2012
 */
@ImplementedBy(GameController.class)
public interface IGame {

	/**
	 * Update the game to the next state.
	 * @param delta - the amount of time in milliseconds from the previous update.
	 */
	public abstract void updateGameState(final int delta);

	/** 
	 * Starts the next wave of enemies.
	 */
	public abstract void nextWave();

	/**
	 * Resets the game to the initial state. Level 1, no released waves etc.
	 */
	public abstract void resetGame();

	/** Builds a tower on the board.
	 * 
	 * @param tower - Tower to be built.
	 * @param pos - Position to build upon.
	 * @return - True if tower was build otherwise false
	 */
	public abstract boolean buildTower(ITower tower, BoardPosition pos);

	/** Sells a tower if possible.
	 * 
	 * @param tower - Tower to be sold.
	 * @param pos - Position on which the tower is built.
	 * @return - True if tower is sold.
	 */
	public abstract boolean sellTower(ITower tower, BoardPosition pos);

	/** Upgrades a tower if possible.
	 * 
	 * @param tower - Tower to be upgraded.
	 * @return - True if tower was upgraded.
	 */
	public abstract boolean upgradeTower(ITower tower);

	/** Tells if a player can afford to upgrade a tower.
	 * 
	 * @param tower - Tower considered to upgrade.
	 * @return - True if player can afford upgrade.
	 */
	public abstract boolean playerCanAffordUpgrade(ITower tower);

	/** Tells if a spot is buildable or not.
	 * 
	 * @param pos - Position to test buildability on.
	 * @return - True if position is buildable on board.
	 */
	public abstract boolean isBuildableSpot(BoardPosition pos);

	/** Tells if a player can afford a tower.
	 * 
	 * @param tower - Tower to test affordability on.
	 * @return - True if player can afford upgrade.
	 */
	public abstract boolean playerCanAffordTower(ITower tower);

	/**
	 *  Returns how many waves that have been released
	 * 
	 * @return number of waves released
	 */
	public abstract int getWavesReleased();

	/**
	 * Returns the current level.
	 * @return the current level.
	 */
	public abstract int getLevel();

	/**
	 * @return the player.
	 */
	public abstract IPlayer getPlayer();

	/**
	 * @return the game board
	 */
	public abstract IGameBoard getGameBoard();

	/**
	 * Causes the player to move depending on the {@code MovementEnum} provided.
	 * @param direction Enum to use for calculation.
	 * @param delta time in milliseconds since last update.
	 */
	public abstract void moveChar(MovementEnum direction, int delta);

	/**
	 * Causes the player to "jump" and move a tile forward if that tile is a buildable or terrain tile.
	 * @param direction enum representing the direction, will be used to calculate where the jump will land.
	 */
	public abstract void tryToJump(MovementEnum direction);

	/**
	 * Method to check if a given position is a buildable or terrain tile.
	 * This determines if player character can move to this position or not.
	 * @param p position to check.
	 * @return true if it's a {@code IBuildableTile} or {@code TerrainTile}.
	 */
	public abstract boolean isAbleToWalkTo(Position p);

}