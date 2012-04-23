package se.chalmers.tda367.std.gui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import se.chalmers.tda367.std.core.EnemyItem;
import se.chalmers.tda367.std.core.GameBoard;
import se.chalmers.tda367.std.core.GameController;
import se.chalmers.tda367.std.core.Player;
import se.chalmers.tda367.std.core.Properties;
import se.chalmers.tda367.std.core.enemies.IEnemy;
import se.chalmers.tda367.std.core.exported.BasicAttackTower;
import se.chalmers.tda367.std.core.tiles.BuildableTile;
import se.chalmers.tda367.std.core.tiles.IBoardTile;
import se.chalmers.tda367.std.core.tiles.IBuildableTile;
import se.chalmers.tda367.std.core.tiles.PathTile;
import se.chalmers.tda367.std.core.tiles.WaypointTile;
import se.chalmers.tda367.std.core.tiles.towers.ITower;
import se.chalmers.tda367.std.utilities.Position;
import se.chalmers.tda367.std.utilities.Sprite;
import sun.swing.BakedArrayList;

public class GameplayState extends BasicGameState {
	private int stateID;
	private Image background;
	private Image pathTile;
	private Image buildableTile;
	private Image towerTile;
	private Image enemyImage;
	private int tileScale;
	private GameBoard board;
	private Properties properties = Properties.INSTANCE;
	private Player player;
	private GameController gameControl;
	
	public GameplayState(int stateID) {
		this.stateID = stateID;
	}
	
	private String getResourcePath(String path){
		return getClass().getResource(path).getPath();
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame state)
			throws SlickException {
		background = new Image(getResourcePath("/gameplay_background.png"));
		pathTile = new Image(getResourcePath("/path_tile.jpg"));
		buildableTile = new Image(getResourcePath("/buildable_tile.png"));
		towerTile = new Image(getResourcePath("/tower_tile2.png"));
		enemyImage = new Image(getResourcePath("/enemy.png"));
		
		tileScale = properties.getTileScale();
		
		board = new GameBoard(25,20, new Position(0,12), new Position (19,12));
		player = new Player("GustenTestar");
		gameControl = new GameController(player, board);
		gameControl.nextWave();
	}
	
	//TODO: Remake this system
    @Override
    public void mouseClicked(int button,
            int x,
            int y,
            int clickCount){
    	if(x < tileScale*board.getWidth() && y < tileScale*board.getHeight()) {
	    	x = x / tileScale;
			y = y / tileScale;
			Position p = Position.valueOf(x, y);
			
			if(board.getTileAt(p) instanceof IBuildableTile)
				board.placeTile(new BasicAttackTower(), p);
    	}
    }
	
	@Override
	public void render(GameContainer container, StateBasedGame state, Graphics g)
			throws SlickException {
        background.draw(0, 0);
        
		int w = board.getWidth();
        int h = board.getHeight();
        for(int y = 0; y < h; y++){
        	for(int x = 0; x < w; x++){
        		IBoardTile tile = board.getTileAt(x, y);
        		int nX = x * tileScale;
        		int nY = y * tileScale;
        		if(tile instanceof PathTile){
        			pathTile.draw(nX, nY, tileScale, tileScale);
        		}
        		else if(tile instanceof BuildableTile){
        			buildableTile.draw(nX, nY, tileScale, tileScale);
        		}
        		else if(tile instanceof ITower){
        			towerTile.draw(nX, nY, tileScale, tileScale);
        		}
        	}
        }
        
        for(EnemyItem ei : gameControl.getEnemies() ) {
        	Position p = ei.getEnemyPos();
        	enemyImage.draw(p.getX(), p.getY(), tileScale, tileScale);
        }
	}

	@Override
	public void update(GameContainer container, StateBasedGame state, int arg2)
			throws SlickException {
		
	}

	@Override
	public int getID() {
		return stateID;
	}

}
