package se.chalmers.tda367.std.core;

import se.chalmers.tda367.std.core.tiles.towers.ITower;
import se.chalmers.tda367.std.factories.WaveFactory;
import se.chalmers.tda367.std.utilities.Position;



/**
 * The class that contains the game logic and controls the game.
 * @author Johan Andersson
 * @modified 
 * @date Mar 22, 2012
 */
public class GameController {
	
	private Player player;
	private GameBoard board;
	private BuildController buildControl;
	private WaveController waveControl;
	
	
	/** Constructor for the GameController, requires a player and a board to work.
	 * 
	 * @param player - player playing the game.
	 * @param board - board to play the game on.
	 */
	public GameController(Player player, GameBoard board){
		this.player = player;
		this.board = board;
		init();
	}
	
	private void init(){
		buildControl = new BuildController(board, player);
		waveControl = new WaveController(board, player);
	}
	
	/** Starts the next wave of enemies.
	 * 
	 */
	public void nextWave(){
		//TODO Check the sent level parameter to the create method.
		Wave wave = new WaveFactory().create(1);
		waveControl.startWave(wave);
	}
	
	/** Builds a tower on the board.
	 * 
	 * @param tower - Tower to be built.
	 * @param pos - Position to build upon.
	 * @return - True if tower was build otherwise false
	 */
	public boolean buildTower(ITower tower, Position pos){
		return buildControl.buildTower(tower, pos);
	}
	
	/** Sells a tower if possible.
	 * 
	 * @param tower - Tower to be sold.
	 * @param pos - Position on which the tower is built.
	 * @return - True if tower is sold.
	 */
	public boolean sellTower(ITower tower, Position pos){
		return buildControl.sellTower(tower, pos);
	}
	
	/** Upgrades a tower if possible.
	 * 
	 * @param tower - Tower to be upgraded.
	 * @return - True if tower was upgraded.
	 */
	public boolean upgradeTower(ITower tower){
		return buildControl.upgradeTower(tower);
	}

	/** Tells if a player can afford to upgrade a tower.
	 * 
	 * @param tower - Tower considered to upgrade.
	 * @return - True if player can afford upgrade.
	 */
	public boolean playerCanAffordUpgrade(ITower tower) {
		return buildControl.playerCanAffordUpgrade(tower);
	}
	
	/** Tells if a spot is buildable or not.
	 * 
	 * @param pos - Position to test buildability on.
	 * @return - True if position is buildable on board.
	 */
	public boolean isBuildableSpot(Position pos) {
		return buildControl.isBuildableSpot(pos);
	}
	
	/** Tells if a player can afford a tower.
	 * 
	 * @param tower - Tower to test affordability on.
	 * @return - True if player can afford upgrade.
	 */
	public boolean playerCanAffordTower(ITower tower) {
		return buildControl.playerCanAffordTower(tower);
	}
	
	/** Return enemies that is currently active.
	 * 
	 * @return - List of enemies.
	 */
//	public ArrayList<EnemyItem> getEnemies() {
//		return waveControl.getEnemies();
//	}
}
