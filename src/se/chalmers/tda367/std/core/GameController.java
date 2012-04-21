package se.chalmers.tda367.std.core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.swing.Timer;
import se.chalmers.tda367.std.core.GameController.EnemyOnBoard;
import se.chalmers.tda367.std.core.tiles.IWalkableTile;
import se.chalmers.tda367.std.core.tiles.PathTile;
import se.chalmers.tda367.std.core.tiles.PlayerBase;
import se.chalmers.tda367.std.core.tiles.TerrainTile;
import se.chalmers.tda367.std.core.tiles.enemies.BasicEnemy;
import se.chalmers.tda367.std.core.tiles.enemies.IEnemy;
import se.chalmers.tda367.std.core.tiles.towers.AbstractAttackTower;
import se.chalmers.tda367.std.core.tiles.towers.ITower;
import se.chalmers.tda367.std.utilities.Position;
import se.chalmers.tda367.std.utilities.Sprite;



/**
 * The class that contains the game logic and controls the game.
 * @author Johan Andersson
 * @date Mar 22, 2012
 */
public class GameController {
	
	private Player player;
	private Wave wave;
	private GameBoard board;
	private PlayerBase base;
	private Timer gameLoop;
	private Timer releaseTimer;
	private WaveItem nextEnemy;
	private ArrayList<EnemyOnBoard> enemiesOnBoard = new ArrayList<EnemyOnBoard>();
	private ArrayList<TowerOnBoard> towersOnBoard = new ArrayList<TowerOnBoard>();
	
	
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
		gameLoop = new Timer(1000, new GameLoopListener());
		releaseTimer = new Timer(1500, new ReleaseTimerListener());
		//dummywave
		this.wave = createBasicWave(5);
		base = new PlayerBase(2);
	}
	
	//Method to create a dummywave
	private Wave createBasicWave(int n){
		ConcurrentLinkedQueue<WaveItem> q = new ConcurrentLinkedQueue<WaveItem>();
		for(int i = 0; i<n; i++){
			q.add(new WaveItem(new BasicEnemy(board.getWaypoints(), new Position(board.getStartPos().getX()*16+8, board.getStartPos().getY()*16+8)), i*0));
		}
		return new Wave(q);
	}
	
	
	
	
	
	/**
	 * Starts a new game
	 */
	public void startGame(){
		gameLoop.start();
		releaseTimer.start();
	}
	
	/**
	 * Ends the running game
	 */
	public void endGame(){
		gameLoop.stop();
		releaseTimer.stop();
	}
	
	/**
	 * Releases the next enemy in queue from the wave
	 */
	public void releaseEnemy(){
		
		if(nextEnemy == null){
			nextEnemy = wave.getNext();
		}
		
		if(board.getTileAt(board.getStartPos()) instanceof IWalkableTile && nextEnemy != null){
			//TODO Look over the if-statement above, cleaner solution?
			placeEnemy(nextEnemy);
			nextEnemy =  wave.getNext();
			paint();
			if(nextEnemy != null){
				releaseTimer.setInitialDelay(nextEnemy.getDelay());
				releaseTimer.restart();
			}
		}
	}
	
	private void placeEnemy(WaveItem wi){
		enemiesOnBoard.add(new EnemyOnBoard(wi, board.getStartPos()));
	}
	
	/**
	 * 
	 * @param tower - Tower to be placed.
	 * @param pos - Position to place tower on.
	 */
	public void buildTower(ITower tower, Position pos){
		board.placeTile(tower, pos);
		if(tower instanceof AbstractAttackTower){
			towersOnBoard.add(new TowerOnBoard((AbstractAttackTower)tower, pos));
		}
	}
	
	/**
	 * Moves all the enemies on the GameBoard, towards the base.	
	 */
	public void moveEnemies(){
		Collections.sort(enemiesOnBoard);
		for (EnemyOnBoard eob : enemiesOnBoard) {
			eob.getEnemy().moveEnemy();
			//moveEnemy(eob);
		}
	}
	
	private void moveEnemy(EnemyOnBoard eob) {
		Position tmp = (eob.getPos()).move(1, 0);
		if(board.posOnBoard(tmp)){
			if(board.getTileAt(tmp) instanceof IWalkableTile){
				placeEnemyOnBoard(eob, tmp);
				eob.setPos(tmp);
			} else if(board.getTileAt(tmp = eob.getPos().move(1, 1)) instanceof IWalkableTile){
				placeEnemyOnBoard(eob, tmp);
				eob.setPos(tmp);
			} else if(board.getTileAt(tmp = eob.getPos().move(1, -1)) instanceof IWalkableTile){
				placeEnemyOnBoard(eob, tmp);
				eob.setPos(tmp);
			}
		} else {
			enemyEnteredBase(eob);
		}
	}
	
	//Moves the enemy a step.
	private void placeEnemyOnBoard(EnemyOnBoard eob, Position pos){
	}
	
	//Method for enemies entering a base, playerbase losing health.
	private void enemyEnteredBase(EnemyOnBoard eob){
		//TODO, different loss in health?
		base.decreaseHealth();
		board.placeTile(new PathTile(new Sprite()), eob.getPos());
	}

	/**
	 * Towers fires at enemies in range.
	 */
	public void shootAtEnemiesInRange(){
		for(TowerOnBoard tob : towersOnBoard){
			shootAtEnemyClosestToBase(tob, board.getEnemiesInRadius(tob.getPos(), tob.getTower().getRadius()));
		}
	}
	
	
	private void shootAtEnemyClosestToBase(TowerOnBoard tob, List<IEnemy> list){
		if(list.size() > 0){
		IEnemy enemy = list.get(0);
		enemy.decreaseHealth(tob.getTower().getDmg());
			if(enemy.getHealth() <= 0){
				player.setMoney(player.getMoney() + enemy.getLootValue());
			}
		}
	}
	
	
	/**
	 * The loop that updates the whole game
	 */
	public void updateGame(){
		moveEnemies();
		shootAtEnemiesInRange();
		removeDeadEnemies();
		paint();
		checkIfPlayerAlive();
	}
	
	private void checkIfPlayerAlive(){
		if(base.getHealth() <= 0){
			playerDead();
		}
	}
	
	private void playerDead(){
		System.out.println("Player dead, game over");
		endGame();
	}
	
	private void paint(){
		System.out.println(board);
		System.out.println(player.getName() +": "+player.getMoney());
	}
	
	private void removeDeadEnemies(){
		for(EnemyOnBoard eob:enemiesOnBoard){
			if(eob.getEnemy().getHealth() <= 0){
				board.placeTile(new PathTile(new Sprite()), eob.getPos());
			}
		}
	}
	
	//Inner class representing an enemy placed on the board.
	class EnemyOnBoard implements Comparable<EnemyOnBoard> {
		IEnemy enemy;
		Position pos;
		
		public EnemyOnBoard(WaveItem nextEnemy, Position p) {
			enemy = nextEnemy.getEnemy();
			pos = p;
		}

		public Position getPos() {
			return pos;
		}
		public void setPos(Position pos) {
			this.pos = pos;
		}
		
		public IEnemy getEnemy() {
			return enemy;
		}

		@Override
		public int compareTo(EnemyOnBoard o) {
			return Integer.compare(pos.getX(), o.pos.getX());
		}
	}
	
	//Inner class representing an tower placed on the board.
	class TowerOnBoard {
		AbstractAttackTower tower;
		Position pos;
		
		public TowerOnBoard(AbstractAttackTower abt, Position p) {
			tower = abt;
			pos = p;
		}
		
		public AbstractAttackTower getTower() {
			return tower;
		}
		
		public Position getPos() {
			return pos;
		}
	}
	
	
	
	class ReleaseTimerListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			releaseEnemy();
		}
	}
	
	
	class GameLoopListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			updateGame();
		}
	}

}
