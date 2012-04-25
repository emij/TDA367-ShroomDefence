package se.chalmers.tda367.std.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import se.chalmers.tda367.std.core.EnemyItem;
import se.chalmers.tda367.std.core.GameBoard;
import se.chalmers.tda367.std.core.GameController;
import se.chalmers.tda367.std.core.Player;
import se.chalmers.tda367.std.core.Properties;
import se.chalmers.tda367.std.core.exported.BasicAttackTower;
import se.chalmers.tda367.std.core.tiles.BuildableTile;
import se.chalmers.tda367.std.core.tiles.IBoardTile;
import se.chalmers.tda367.std.core.tiles.IBuildableTile;
import se.chalmers.tda367.std.core.tiles.PathTile;
import se.chalmers.tda367.std.core.tiles.towers.ITower;
import se.chalmers.tda367.std.utilities.Position;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
import de.lessvoid.nifty.slick2d.NiftyOverlayBasicGameState;
import de.lessvoid.nifty.slick2d.input.PlainSlickInputSystem;
import de.lessvoid.nifty.slick2d.render.SlickRenderDevice;
import de.lessvoid.nifty.slick2d.sound.SlickSoundDevice;
import de.lessvoid.nifty.slick2d.time.LWJGLTimeProvider;
import de.lessvoid.nifty.spi.input.InputSystem;


public class GameplayOverlay extends NiftyOverlayBasicGameState implements ScreenController {
	private int stateID;
	private Image pathTile;
	private Image buildableTile;
	private Image towerTile;
	private Image enemyImage;
	private int tileScale;
	private boolean towerChoosed;
	private GameBoard board;
	private Properties properties = Properties.INSTANCE;
	private Player player;
	private GameController gameControl;
	private Nifty nifty;
	private InputSystem input;
	
	public GameplayOverlay(int stateID) {
		this.stateID = stateID;
	}
	
	private String getResourcePath(String path){
		return getClass().getResource(path).getPath();
	}
	
	@Override
	protected void enterState(GameContainer container, StateBasedGame state)
			throws SlickException {
	}

	@Override
	protected void initGameAndGUI(GameContainer container, StateBasedGame state)
			throws SlickException {
		pathTile = new Image(getResourcePath("/images/gameplay/path_tile.jpg"));
		buildableTile = new Image(getResourcePath("/images/gameplay/buildable_tile.png"));
		towerTile = new Image(getResourcePath("/images/gameplay/tower_tile1.png"));
		enemyImage = new Image(getResourcePath("/images/gameplay/enemy.png"));
		
		towerChoosed = false;
		tileScale = properties.getTileScale();
		input = new PlainSlickInputSystem();
		nifty = new Nifty(new SlickRenderDevice(container), new SlickSoundDevice(), input, new LWJGLTimeProvider());
		prepareNifty(nifty, state);
		initNifty(container, state);
		
		board = new GameBoard(25,20, new Position(0,12), new Position (19,12));
		player = new Player("GustenTestar");
		gameControl = new GameController(player, board);
	}

	@Override
	protected void leaveState(GameContainer container, StateBasedGame state)
			throws SlickException {
	}

	@Override
	protected void prepareNifty(Nifty nifty, StateBasedGame state) {
		nifty.fromXml(getResourcePath("/XMLFile1.xml"), "start", this);
	}

	@Override
	protected void renderGame(GameContainer container, StateBasedGame state,
			Graphics g) throws SlickException {
        g.setColor(Color.black);
        
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
        
        
        for(EnemyItem ei : board.getEnemies() ) {
        	Position p = ei.getEnemyPos();
        	int health = ei.getEnemy().getHealth();
        	enemyImage.draw(p.getX(), p.getY(), tileScale, tileScale);
        	g.drawString(""+health, p.getX(), p.getY()-tileScale);
        }
        nifty.render(false);
	}

	@Override
	protected void updateGame(GameContainer container, StateBasedGame state, int delta)
			throws SlickException {
		nifty.update();
		
		Input input = container.getInput();
		int mouseX = input.getMouseX();
		int mouseY = input.getMouseY();

		if(mouseX < tileScale*board.getWidth() && mouseY < tileScale*board.getHeight()) {
			if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
		    	int x = mouseX / tileScale;
				int y = mouseY / tileScale;
				Position p = Position.valueOf(x, y);
				if(board.getTileAt(p) instanceof IBuildableTile && towerChoosed) {
					board.placeTile(new BasicAttackTower(), p);
					towerChoosed = false;
				}
			}
    	}
	}

	@Override
	public int getID() {
		return stateID;
	}

	@Override
	public void bind(Nifty nifty, Screen screen) {
	}

	@Override
	public void onEndScreen() {
	}

	@Override
	public void onStartScreen() {
	}
	
	public void startWave() {
		gameControl.nextWave();
	}
	
	public void buildTower(String tower) {
		towerChoosed = true;
		if(tower.equals("basic")) {
			
		}
		else if(tower.equals("poison")) {
			
		}
	}
}
