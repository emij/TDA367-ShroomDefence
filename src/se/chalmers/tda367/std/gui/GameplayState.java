package se.chalmers.tda367.std.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import com.google.common.eventbus.Subscribe;

import se.chalmers.tda367.std.core.EnemyItem;
import se.chalmers.tda367.std.core.GameBoard;
import se.chalmers.tda367.std.core.GameBoard.BoardPosition;
import se.chalmers.tda367.std.core.GameController;
import se.chalmers.tda367.std.core.Player;
import se.chalmers.tda367.std.core.Properties;
import se.chalmers.tda367.std.core.exported.BasicAttackTower;
import se.chalmers.tda367.std.core.tiles.IBoardTile;
import se.chalmers.tda367.std.core.tiles.IBuildableTile;
import se.chalmers.tda367.std.events.TowerShootingEvent;
import se.chalmers.tda367.std.utilities.EventBus;
import se.chalmers.tda367.std.utilities.NativeSprite;
import se.chalmers.tda367.std.utilities.Position;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.controls.Label;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
import de.lessvoid.nifty.slick2d.NiftyOverlayBasicGameState;


public class GameplayState extends NiftyOverlayBasicGameState implements ScreenController {
	private int stateID;
	private int tileScale;
	private int mouseX, mouseY;
	private boolean towerChoosed;
	private GameBoard board;
	private Properties properties = Properties.INSTANCE;
	private Player player;
	private GameController gameControl;
	private Nifty nifty;
	private Element lifeLabel, scoreLabel;

	public GameplayState(int stateID) {
		this.stateID = stateID;
		towerChoosed = false;
		tileScale = properties.getTileScale();
	}
	
	@Override
	protected void enterState(GameContainer container, StateBasedGame state)
			throws SlickException {
	}

	@Override
	protected void initGameAndGUI(GameContainer container, StateBasedGame state)
			throws SlickException {
		board = new GameBoard(25,20, GameBoard.BoardPosition.valueOf(0,12), GameBoard.BoardPosition.valueOf(19,12));
		player = new Player("GustenTestar");
		gameControl = new GameController(player, board);
		
		initNifty(container, state);
		nifty = this.getNifty();
		prepareNifty(nifty, state);
		
		container.getGraphics().setLineWidth(3);
		
		EventBus.INSTANCE.register(this);
	}

	@Override
	protected void leaveState(GameContainer container, StateBasedGame state)
			throws SlickException {
	}

	@Override
	protected void prepareNifty(Nifty nifty, StateBasedGame state) {
		nifty.fromXml(getResourcePath("/XMLFile1.xml"), "start", this);
		
		lifeLabel = nifty.getCurrentScreen().findElementByName("lifeLabel");
		scoreLabel = nifty.getCurrentScreen().findElementByName("scoreLabel");
	}

	@Override
	protected void renderGame(GameContainer container, StateBasedGame state,
			Graphics g) throws SlickException {
		g.setColor(Color.black);
        renderStats();
        renderTiles();
        renderEnemies(g);
        
        if(towerChoosed) {
        	renderBuildingFeedback(g);
        }
	}
	
	@Subscribe
	public void renderTowerShooting(TowerShootingEvent event){
		Position from = event.getFromPosition();
		Position to = event.getToPosition();
		// TODO: logic goes here.
	}
	
	@Override
	protected void updateGame(GameContainer container, StateBasedGame state, int delta)
			throws SlickException {
		gameControl.updateGameState(delta);
		nifty.update();
		
		
		Input input = container.getInput();
		mouseX = input.getMouseX();
		mouseY = input.getMouseY();
		
		if(input.isMousePressed(Input.MOUSE_RIGHT_BUTTON)) {
			towerChoosed = false;
			nifty.getCurrentScreen().findElementByName("startWaveButton").setFocus();
		}
		
		if(mouseX < tileScale*board.getWidth() && mouseY < tileScale*board.getHeight()
				 && towerChoosed && input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			int x = mouseX / tileScale;
			int y = mouseY / tileScale;
			BoardPosition p = BoardPosition.valueOf(x, y);
			if(board.getTileAt(p) instanceof IBuildableTile) {
				board.placeTile(new BasicAttackTower(), p);
				towerChoosed = false;
				nifty.getCurrentScreen().findElementByName("startWaveButton").setFocus();
			}
    	}
	}

	@Override
	public int getID() {
		return stateID;
	}
	
	private String getResourcePath(String path){
		return getClass().getResource(path).getPath();
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
	
	private void renderTiles() {
        for(int y = 0; y < board.getHeight(); y++){
        	for(int x = 0; x < board.getWidth(); x++){
        		IBoardTile tile = board.getTileAt(x, y);
        		int nX = x * tileScale;
        		int nY = y * tileScale;
        		tile.getSprite().getNativeSprite().draw(nX, nY, tileScale, tileScale);
        	}
        }
	}
	
	private void renderEnemies(Graphics g) {
        for(EnemyItem ei : board.getEnemies() ) {
        	Position p = ei.getEnemyPos();
        	int health = ei.getEnemy().getHealth();
        	NativeSprite image = ei.getEnemy().getSprite().getNativeSprite();
        	
        	image.draw(p.getX(), p.getY(), tileScale, tileScale);
        	g.drawString(""+health, p.getX(), p.getY()-tileScale);
        }
	}
	
	private void renderStats() {
	    lifeLabel.getNiftyControl(Label.class).setText("" + board.getPlayerBase().getHealth());
	    scoreLabel.getNiftyControl(Label.class).setText("" + player.getCurrentScore());
	}
	
	private void renderBuildingFeedback(Graphics g) {
		int x = mouseX/tileScale;
		int y = mouseY/tileScale;
		if(board.canBuildAt(BoardPosition.valueOf(x, y))) {
			g.setColor(Color.green);
			g.drawRect(x * tileScale, y * tileScale, tileScale, tileScale);
			g.setColor(Color.black);
		}
		else {
			g.setColor(Color.red);
			g.drawRect(x * tileScale, y * tileScale, tileScale, tileScale);
			g.setColor(Color.black);
		}
	}
	
	public void buildTower(String tower) {
		towerChoosed = true;
		if(tower.equals("basic")) {
			
		}
		else if(tower.equals("poison")) {
			
		}
	}
}
