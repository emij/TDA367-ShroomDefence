package se.chalmers.tda367.std.core;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

import com.google.common.eventbus.Subscribe;
import com.google.inject.Guice;

import se.chalmers.tda367.std.core.events.WaveEndedEvent;
import se.chalmers.tda367.std.core.events.WaveStartedEvent;
import se.chalmers.tda367.std.core.factories.GameBoardFactory;
import se.chalmers.tda367.std.core.factories.IFactory;
import se.chalmers.tda367.std.core.factories.WaveFactory;
import se.chalmers.tda367.std.core.tiles.IBuildableTile;
import se.chalmers.tda367.std.core.tiles.TerrainTile;
import se.chalmers.tda367.std.core.tiles.towers.ITower;
import se.chalmers.tda367.std.utilities.BoardPosition;
import se.chalmers.tda367.std.utilities.EventBus;
import se.chalmers.tda367.std.utilities.FileScanner;
import se.chalmers.tda367.std.utilities.Position;
import se.chalmers.tda367.std.utilities.Prime;



/**
 * The class that contains the game logic and controls the game.
 * @author Johan Andersson
 * @modified Johan Gustafsson
 * @date Mar 22, 2012
 */
public class GameController implements IGame {
	
	private IPlayer player;
	private IGameBoard board;
	private BuildController buildControl;
	private WaveController waveControl;
	private Properties prop = Properties.INSTANCE;
	private int tileScale;
	
	private final IFactory<IGameBoard, Integer> boardFactory;
	
	private int releasedWaves = 0; // Number of released waves at the current level.
	private int level;
	private int maxWaveCount = 2;  // The number of waves that should be released before level up.
	
	public GameController() {
		this(Guice.createInjector().getInstance(IPlayer.class));
	}
	
	/** 
	 * Constructor for the GameController, requires a player to work.
	 * @param player - player playing the game.
	 */
	public GameController(IPlayer player){
		EventBus.INSTANCE.register(this);
		
		this.player = player;
		this.level = 1;
		tileScale = prop.getTileScale();
		boardFactory = new GameBoardFactory();
		
		init();
	}
	
	private void init(){
		this.board = boardFactory.create(level);
		buildControl = new BuildController(board, player);
		waveControl = new WaveController(board, player);
	}
	
	@Override
	public void updateGameState(final int delta) {
		waveControl.updateWaveRelated(delta);
	}
	
	@Override
	public void nextWave(){
		Wave wave = new WaveFactory().create(level);
		waveControl.startWave(wave);
		EventBus.INSTANCE.post(new WaveStartedEvent(releasedWaves));
		releasedWaves++;
	}
	
	@Subscribe
	public void waveEnded(WaveEndedEvent e) {
		if(releasedWaves > maxWaveCount) {
			releasedWaves = 0;
			level++;
			
			// Use the next prime number as the next wave count.
			maxWaveCount = Prime.nextPrime(maxWaveCount); 
			
			tryChangeMap();
		}
	}
	
	/** Tries to load the map corresponding to the next level */
	private void tryChangeMap() {
		List<File> maps = FileScanner.getFiles(Paths.get("maps"));
		if(maps.size() < level) // TODO: Assumes only maps resides in the folder. Add extension filter to getFiles(...)
			return;
		
		init();
	}

	@Override
	public void resetGame() {
		player = Guice.createInjector().getInstance(IPlayer.class);
		releasedWaves = 0;
		level = 1;
		
		init();
	}
	
	@Override
	public boolean buildTower(ITower tower, BoardPosition pos){
		return buildControl.buildTower(tower, pos);
	}
	
	@Override
	public boolean sellTower(ITower tower, BoardPosition pos){
		return buildControl.sellTower(tower, pos);
	}
	
	@Override
	public boolean upgradeTower(ITower tower){
		return buildControl.upgradeTower(tower);
	}

	@Override
	public boolean playerCanAffordUpgrade(ITower tower) {
		return buildControl.playerCanAffordUpgrade(tower);
	}
	
	@Override
	public boolean isBuildableSpot(BoardPosition pos) {
		return buildControl.isBuildableSpot(pos);
	}
	
	@Override
	public boolean isTowerPosition(BoardPosition pos) {
		return buildControl.isTowerPosition(pos);
	}
	
	@Override
	public boolean playerCanAffordTower(ITower tower) {
		return buildControl.playerCanAffordTower(tower);
	}
	
	@Override
	public int getWavesReleased() {
		return releasedWaves;
	}
	
	@Override
	public int getLevel() {
		return this.level;
	}
	
	@Override
	public IPlayer getPlayer() {
		return player;
	}

	@Override
	public IGameBoard getGameBoard() {
		return board;
	}
	
	@Override
	public void moveChar(MovementEnum direction, int delta) {
		float moveSpd = player.getCharacter().getSpeed();
		Position playerPos = player.getCharacter().getPos();
		Position newPos = direction.newPosition(playerPos, delta, moveSpd);
		
		if(isAbleToWalkTo(newPos)) {
			player.getCharacter().moveTo(newPos);
		}
	}
	
	@Override
	public void tryToJump(MovementEnum direction) {
		Position playerPos = player.getCharacter().getPos();
		Position newPos = direction.newJumpPosition(playerPos);
		
		if(isAbleToWalkTo(newPos)) {
			player.getCharacter().moveTo(newPos);
		}
	}
	
	@Override
	public boolean isAbleToWalkTo(Position p) {
		//Calculate on which tile the position given is on.
		int x = (int)(p.getX()/tileScale);
		int y = (int)(p.getY()/tileScale);
		
		if(board.posOnBoard(x, y)) {
			if(board.getTileAt(x, y) instanceof IBuildableTile
					|| board.getTileAt(x, y) instanceof TerrainTile) {
				return true;
			}
		}
		return false;
	}
}
