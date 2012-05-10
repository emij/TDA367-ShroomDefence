package se.chalmers.tda367.std.gui;

import java.util.LinkedList;
import java.util.List;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import com.google.common.eventbus.Subscribe;

import se.chalmers.tda367.std.core.DynamicLoader;
import se.chalmers.tda367.std.core.EnemyItem;
import se.chalmers.tda367.std.core.GameBoard;
import se.chalmers.tda367.std.core.GameBoard.BoardPosition;
import se.chalmers.tda367.std.core.GameController;
import se.chalmers.tda367.std.core.Player;
import se.chalmers.tda367.std.core.Properties;
import se.chalmers.tda367.std.core.exported.BasicAttackTower;
import se.chalmers.tda367.std.core.tiles.IBoardTile;
import se.chalmers.tda367.std.core.tiles.IBuildableTile;
import se.chalmers.tda367.std.core.tiles.towers.ITower;
import se.chalmers.tda367.std.events.TowerShootingEvent;
import se.chalmers.tda367.std.utilities.EventBus;
import se.chalmers.tda367.std.utilities.NativeSprite;
import se.chalmers.tda367.std.utilities.Position;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.NiftyEventSubscriber;
import de.lessvoid.nifty.builder.ControlBuilder;
import de.lessvoid.nifty.controls.CheckBoxStateChangedEvent;
import de.lessvoid.nifty.controls.Label;
import de.lessvoid.nifty.controls.SliderChangedEvent;
import de.lessvoid.nifty.controls.label.LabelControl;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
import de.lessvoid.nifty.slick2d.NiftyOverlayBasicGameState;


public class GameplayState extends NiftyOverlayBasicGameState implements ScreenController {
	private int stateID, tileScale, mouseX, mouseY, delta;
	private boolean towerIsChoosen, optionsScreenIsOpen, gameOver;
	private GameBoard board;
	private Properties properties = Properties.INSTANCE;
	private Player player;
	private GameController gameControl;
	private Nifty nifty;
	private Element lifeLabel, scoreLabel, levelLabel, defaultFocusElement,
					optionsPopup, gameOverPopup;
	private List<AttackAnimation> attacksList;
	private Image[] explosion;
	private Animation explosionAnimation;
	private ITower choosenTower;
	private Music backgroundMusic;
	private StateBasedGame state;

	public GameplayState(int stateID) {
		this.stateID = stateID;
		tileScale = properties.getTileScale();
	}
	
	@Override
	protected void enterState(GameContainer container, StateBasedGame state)
			throws SlickException {
		towerIsChoosen = false;
		optionsScreenIsOpen = false;
		gameOver = false;
		attacksList = new LinkedList<AttackAnimation>();
		
		board = new GameBoard(25,20, GameBoard.BoardPosition.valueOf(0,12), GameBoard.BoardPosition.valueOf(19,12));
		player = new Player("GustenTestar");
		gameControl = new GameController(player, board);
		
		backgroundMusic.loop(1, 1);
	}
	
	@Override
	protected void leaveState(GameContainer container, StateBasedGame state)
			throws SlickException {
		backgroundMusic.stop();
		nifty.closePopup(gameOverPopup.getId());
	}

	@Override
	protected void initGameAndGUI(GameContainer container, StateBasedGame state)
			throws SlickException {
		this.state = state;
		
		initSound();
		initAnimations();
		initNifty(container, state);
		nifty = this.getNifty();
		initGUIButtons();
		initElements();
				
		container.getGraphics().setLineWidth(3);
		
		EventBus.INSTANCE.register(this);
	}

	private void initGUIButtons() {
		//TODO: Fix the implemenation of this so it shows a name of the tower and also so it adds them in right order.
		Element leftBtnPanel = nifty.getCurrentScreen().findElementByName("leftButtonPanel");
		//Element rightBtnPanel = nifty.getCurrentScreen().findElementByName("rightButtonPanel");
		ControlBuilder cb = new ControlBuilder("icons");
		cb.width("85%");
		cb.height("15%");
		cb.name("button");
		cb.font("verdana-smallregular.fnt");
		List<Class<ITower>> exportedTowers = DynamicLoader.getTowers();
		
		for(Class<ITower> towerClass : exportedTowers) {
			ITower tmpInst = DynamicLoader.createInstance(towerClass);
			cb.interactOnClick("buildTower("+ tmpInst.getName() + ")");
			cb.build(nifty, nifty.getCurrentScreen(), leftBtnPanel);
		}
	}

	private void initAnimations() throws SlickException {
		Image exp_1 = new Image(getResourcePath("/animations/explosion_1.png"));
		Image exp_2 = new Image(getResourcePath("/animations/explosion_2.png"));
		explosion = new Image[2];
		explosion[0] = exp_1;
		explosion[1] = exp_2;
		explosionAnimation = new Animation(explosion, 500);
	}
	
	private void initSound() throws SlickException {
		backgroundMusic = new Music(getResourcePath("/audio/main_menu/music.wav"));
	}
	
	public void initElements() {
		Screen tmpScreen = nifty.getCurrentScreen();
		lifeLabel = tmpScreen.findElementByName("lifeLabel");
		scoreLabel = tmpScreen.findElementByName("scoreLabel");
		levelLabel = tmpScreen.findElementByName("levelLabel");
		defaultFocusElement = tmpScreen.findElementByName("startWaveButton");
		optionsPopup = nifty.createPopup("optionsPopup");
		gameOverPopup = nifty.createPopup("gameOverPopup");
	}

	@Override
	protected void prepareNifty(Nifty nifty, StateBasedGame state) {
		nifty.fromXml(getResourcePath("/XMLFile1.xml"), "start", this);
		nifty.getSoundSystem().addMusic("backgroundmusic", getResourcePath("/audio/main_menu/music.wav"));
	}

	@Override
	protected void renderGame(GameContainer container, StateBasedGame state,
			Graphics g) throws SlickException {
		g.setColor(Color.black);
        renderStats();
        renderTiles();
        renderEnemies(g);
        
        //If a tower has been selected it will show a small rectangle where you hold the
        //mouse to indicate if one can build on that tile or not.
        if(towerIsChoosen) {
        	renderBuildingFeedback(g);
        }
        if(attacksList != null && !attacksList.isEmpty()) {
        	renderAttacks(g);
        }
	}
	
	@Subscribe
	public void renderTowerShooting(TowerShootingEvent event){
		AttackAnimation attack = new AttackAnimation(event, 1000);
		attacksList.add(attack);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	protected void updateGame(GameContainer container, StateBasedGame state, int delta)
			throws SlickException {
		this.delta = delta;
		if(!gameOver) {
			gameControl.updateGameState(delta);
			
			Input input = container.getInput();
			mouseX = input.getMouseX();
			mouseY = input.getMouseY();
			
			if(input.isMousePressed(Input.MOUSE_RIGHT_BUTTON)) {
				towerIsChoosen = false;
				defaultFocusElement.setFocus();
			}
			
			if(mouseX < tileScale*board.getWidth() && mouseY < tileScale*board.getHeight()
					 && towerIsChoosen && input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				int x = mouseX / tileScale;
				int y = mouseY / tileScale;
				BoardPosition p = BoardPosition.valueOf(x, y);
				if(board.getTileAt(p) instanceof IBuildableTile) {
					board.placeTile(choosenTower, p);
					towerIsChoosen = false;
					defaultFocusElement.setFocus();
				}
	    	}
			if(board.getPlayerBase().getHealth() == 0) {
				if(optionsScreenIsOpen) {
					nifty.closePopup(optionsPopup.getId());
				}
				gameOverPopup.findControl("gameOverScoreLabel", LabelControl.class).setText("" + player.getCurrentScore());
				nifty.showPopup(nifty.getCurrentScreen(), gameOverPopup.getId(), null);
				gameOver=true;
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
	    levelLabel.getNiftyControl(Label.class).setText("" + gameControl.getWavesReleased());
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
	
	private void renderAttacks(Graphics g) {
		List<AttackAnimation> tmpList = new LinkedList<AttackAnimation>(attacksList);
		for(AttackAnimation attack : tmpList) {
			TowerShootingEvent event = attack.getAttackEvent();
			Position from = event.getFromPosition();
			Position to = event.getToPosition();
			g.drawLine(from.getX()+tileScale/2, from.getY()+tileScale/2,
					to.getX()+tileScale/2, to.getY()+tileScale/2);
			g.drawAnimation(explosionAnimation, to.getX(), to.getY());
			attack.decreaseDuration(delta);
			if(attack.getRemainigDuration() == 0) {
				attacksList.remove(attack);
			}
		}
	}
	
	public void buildTower(String tower) {
		towerIsChoosen = true;
		if(tower.equals("BasicTower")) {
			choosenTower = new BasicAttackTower();
		}
		else if(tower.equals("PoisonTower")) {
			choosenTower = new BasicAttackTower();
		}
	}
	
	public void toggleOptions() {
		if(optionsScreenIsOpen) {
			nifty.closePopup(optionsPopup.getId());
		}
		else {
			nifty.showPopup(nifty.getCurrentScreen(), optionsPopup.getId(), null);
		}
		optionsScreenIsOpen = !optionsScreenIsOpen;
	}
	
	public void endGame() {
		state.enterState(STDGame.MAINMENUSTATE);
	}
	
	@NiftyEventSubscriber(id="musicCheckbox")
	public void onTextfieldChange(final String id, final CheckBoxStateChangedEvent event) {
		if(backgroundMusic.playing()) {
			backgroundMusic.pause();
		}
		else {
			backgroundMusic.resume();
		}
	}
	
	@NiftyEventSubscriber(id="musicVolumeSlider")
	public void onSliderEvent(String id, SliderChangedEvent event) {
		backgroundMusic.setVolume(event.getValue()/100);
	}
	
	/**
	 * Private helper class for representing a towers attack.
	 * @author Johan Gustafsson
	 * @date   May 08, 2012
	 */
	private class AttackAnimation {
		private int duration;
		private TowerShootingEvent event;
		
		public AttackAnimation(TowerShootingEvent event, int duration) {
			this.duration = duration;
			this.event = event;
		}
		
		public void decreaseDuration(int delta) {
			if(duration > 0) {
				duration -= delta*500;
			}
			else {
				duration = 0;
			}
		}
		
		public int getRemainigDuration() {
			return duration;
		}
		
		public TowerShootingEvent getAttackEvent() {
			return event;
		}
	}
}
