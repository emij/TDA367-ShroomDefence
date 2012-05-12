package se.chalmers.tda367.std.core;

import se.chalmers.tda367.std.core.tiles.BuildableTile;
import se.chalmers.tda367.std.core.tiles.towers.ITower;

/**
 * The class that contains the game logic for build phase of the game.
 * @author Johan Andersson
 * @modified Emil Edholm (Apr 27, 2012)
 * @modified Johan Gustafsson (May 12, 2012)
 * @date Apr 22, 2012
 */

class BuildController {
	private static final double SELL_MODIFER = 0.75; 
	private GameBoard board;
	private Player player;
	
	
	public BuildController(GameBoard board, Player player){
		this.board = board;
		this.player = player;
	}
	
	/** 
	 * Builds a tower on the board.
	 * 
	 * @param tower - Tower to be built.
	 * @param pos - Position to build upon.
	 * @return - True if tower was built otherwise false
	 */
	public boolean buildTower(ITower tower, GameBoard.BoardPosition pos){
		if(isBuildableSpot(pos) && playerCanAffordTower(tower)){
			board.placeTile(tower, pos);
			player.removeMoney(tower.getBaseCost());
			return true;
		} else {
			return false;
		}
	}

	/** Tells if a spot is buildable.
	 * 
	 * @param pos - Position to test buildability on.
	 * @return - True if position is buildable on board.
	 */
	public boolean isBuildableSpot(GameBoard.BoardPosition pos) {
		return board.canBuildAt(pos);
	}

	/** Tells if a player can afford a tower.
	 * 
	 * @param tower - Tower to test affordability on.
	 * @return - True if player can afford upgrade.
	 */
	public boolean playerCanAffordTower(ITower tower) {
		return player.getMoney() >= tower.getBaseCost();
	}
	
	/** Upgrades a tower if possible.
	 * 
	 * @param tower - Tower to be upgraded.
	 * @return - True if tower was upgraded.
	 */
	public boolean upgradeTower(ITower tower){
		if(playerCanAffordUpgrade(tower)){
			tower.upgrade();
			player.removeMoney(tower.getUpgradeCost());
			return true;
		} else {
			return false;
		}
	}

	/** Tells if a player can afford to upgrade a tower.
	 * 
	 * @param tower - Tower considered to upgrade.
	 * @return - True if player can afford upgrade.
	 */
	public boolean playerCanAffordUpgrade(ITower tower) {
		return player.getMoney() >= tower.getUpgradeCost();
	}
	
	/** Sells a tower if possible.
	 * 
	 * @param tower - Tower to be sold.
	 * @param pos - Position on which the tower is built.
	 * @return - True if tower is sold.
	 */
	public boolean sellTower(ITower tower, GameBoard.BoardPosition pos){
		if(isTowerAt(tower,pos)){
			player.setMoney(player.getMoney() + tower.refund());
			board.placeTile(new BuildableTile(), pos);
			return true;
		} else {
			return false;
		}
	}

	private boolean isTowerAt(ITower tower, GameBoard.BoardPosition pos) {
		return tower == board.getTileAt(pos);
	}
		
	
}
