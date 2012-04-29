package se.chalmers.tda367.std.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import se.chalmers.tda367.std.core.EnemyItem;
import se.chalmers.tda367.std.core.GameBoard;
import se.chalmers.tda367.std.core.GameController;
import se.chalmers.tda367.std.core.Player;
import se.chalmers.tda367.std.core.Properties;
import se.chalmers.tda367.std.core.exported.BasicAttackTower;
import se.chalmers.tda367.std.core.maps.LevelMap;
import se.chalmers.tda367.std.core.maps.MapLoader;
import se.chalmers.tda367.std.core.tiles.IBoardTile;
import se.chalmers.tda367.std.core.tiles.IBuildableTile;
import se.chalmers.tda367.std.utilities.NativeSprite;
import se.chalmers.tda367.std.utilities.Position;

public class GameplayState extends BasicGameState {
	private int stateID;
	private Image startButton;
	private Image towerThumbnail;
	private Image background;
	
	private int tileScale;
	private int startX, startY, startGridX, startGridY;
	private boolean overStart = false, towerChoosed = false;
	private GameBoard board;
	private Properties properties = Properties.INSTANCE;
	private Player player;
	private GameController gameControl;
	
	// TODO: Handle events: player has died.
	
	public GameplayState(int stateID) {
		this.stateID = stateID;
	}
	
	private String getResourcePath(String path){
		return getClass().getResource(path).getPath();
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame state)
			throws SlickException {
		startButton = new Image(getResourcePath("/images/gameplay/button_template.png"));
		towerThumbnail = new Image(getResourcePath("/images/gameplay/tower_thumbnail1.png"));
		background = new Image(getResourcePath("/images/gameplay/background.png"));
		
		tileScale = properties.getTileScale();
		
		MapLoader mLoader = new MapLoader(1);
		board = new GameBoard(mLoader.getLoadedMap());
		player = new Player("GustenTestar");
		gameControl = new GameController(player, board);
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame state, Graphics g)
			throws SlickException {
        background.draw(0, 0);
        g.setColor(Color.black);
        
		int w = board.getWidth();
        int h = board.getHeight();
        for(int y = 0; y < h; y++){
        	for(int x = 0; x < w; x++){
        		IBoardTile tile = board.getTileAt(x, y);
        		int nX = x * tileScale;
        		int nY = y * tileScale;
        		tile.getSprite().getNativeSprite().draw(nX, nY, tileScale, tileScale);
        	}
        }
        
        
        for(EnemyItem ei : board.getEnemies() ) {
        	Position p = ei.getEnemyPos();
        	int health = ei.getEnemy().getHealth();
        	
        	NativeSprite image = ei.getEnemy().getSprite().getNativeSprite();
        	image.draw(p.getX(), p.getY(), tileScale, tileScale);
        	g.drawString(""+health, p.getX(), p.getY()-tileScale);
        }
        startButton.draw(startX, startY);
        towerThumbnail.draw(startGridX, startGridY);
        if(overStart) {
        	startButton.draw(startX, startY, Color.green);
        }
	}

	
	@Override
	public void update(GameContainer container, StateBasedGame state, int delta)
			throws SlickException {
		gameControl.updateGameState(delta);
		
		startX = (int)(container.getWidth()*0.796);
		startY = (int)(container.getHeight()*0.895);
		startGridX = (int)(container.getWidth()*0.797);
		startGridY = (int)(container.getHeight()*0.211);
		
		Input input = container.getInput();
		int mouseX = input.getMouseX();
		int mouseY = input.getMouseY();
		
		if((mouseX >= startX && mouseX <= startX+startButton.getWidth()) && 
				(mouseY >= startY && mouseY <= startY+startButton.getHeight())) {
			overStart = true;
			if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				gameControl.nextWave();
			}
		}
		else if((mouseX >= startGridX && mouseX <= startGridX+towerThumbnail.getWidth()) && 
				(mouseY >= startGridY && mouseY <= startGridY+towerThumbnail.getHeight())) {
			if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				towerChoosed = true;
			}
		}
		else if(mouseX < tileScale*board.getWidth() && mouseY < tileScale*board.getHeight()) {
			if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
		    	int x = mouseX / tileScale;
				int y = mouseY / tileScale;
				GameBoard.BoardPosition p = GameBoard.BoardPosition.valueOf(x, y);
				if(board.getTileAt(p) instanceof IBuildableTile && towerChoosed) {
					board.placeTile(new BasicAttackTower(), p);
					towerChoosed = false;
				}
			}
    	}
		else{
			overStart = false;
		}
	}

	@Override
	public int getID() {
		return stateID;
	}

}
