package se.chalmers.tda367.std.gui;

import java.util.LinkedList;
import java.util.List;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.state.StateBasedGame;

import com.google.common.eventbus.Subscribe;

import se.chalmers.tda367.std.core.*;
import se.chalmers.tda367.std.core.anno.Tower;
import se.chalmers.tda367.std.core.enemies.IEnemy;
import se.chalmers.tda367.std.core.events.*;
import se.chalmers.tda367.std.core.tiles.*;
import se.chalmers.tda367.std.core.tiles.towers.*;
import se.chalmers.tda367.std.utilities.BoardPosition;
import se.chalmers.tda367.std.utilities.EventBus;
import se.chalmers.tda367.std.utilities.NativeSprite;
import se.chalmers.tda367.std.utilities.Position;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.NiftyEventSubscriber;
import de.lessvoid.nifty.controls.Button;
import de.lessvoid.nifty.controls.CheckBoxStateChangedEvent;
import de.lessvoid.nifty.controls.Label;
import de.lessvoid.nifty.controls.SliderChangedEvent;
import de.lessvoid.nifty.controls.button.builder.ButtonBuilder;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.events.NiftyMousePrimaryClickedEvent;
import de.lessvoid.nifty.input.NiftyInputEvent;
import de.lessvoid.nifty.input.mapping.DefaultInputMapping;
import de.lessvoid.nifty.screen.KeyInputHandler;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
import de.lessvoid.nifty.slick2d.NiftyOverlayBasicGameState;


public class GameplayState extends NiftyOverlayBasicGameState implements ScreenController {
	private int stateID, tileScale, mouseX, mouseY, delta;
	private boolean towerIsChoosen, optionsScreenIsOpen, gameOver;
	private Properties properties = Properties.INSTANCE;
	
	private Player player;
	private GameController gameControl;
	private ITower choosenTower;
	private IAttackTower selectedTower;
	private BoardPosition towerPos;
	
	private Nifty nifty;
	private Element lifeLabel, scoreLabel, levelLabel, playerMoneyLabel, startWaveButton,
					optionsPopup, gameOverPopup, towerPopup;
	private List<AttackAnimationDuration> attacksList;
	private Image[] explosion;
	private Animation explosionAnimation;
	private Music backgroundMusic;
	private StateBasedGame state;
	private Input input;
	

	public GameplayState(int stateID) {
		this.stateID = stateID;
		tileScale = properties.getTileScale();
	}
	
	@Override
	protected void enterState(GameContainer container, StateBasedGame state)
			throws SlickException {
		this.state = state;
		towerIsChoosen = false;
		optionsScreenIsOpen = false;
		gameOver = false;
		attacksList = new LinkedList<AttackAnimationDuration>();

		player = new Player("Player1");
		player.setMoney(500);
		gameControl = new GameController(player);
		
		backgroundMusic.loop(1, 1);
	}
	
	@Override
	protected void leaveState(GameContainer container, StateBasedGame state)
			throws SlickException {
		backgroundMusic.stop();
	}

	@Override
	protected void initGameAndGUI(GameContainer container, StateBasedGame state)
			throws SlickException {		
		initSound();
		initAnimations();
		initNifty(container, state);
		nifty = this.getNifty();
		initGUIButtons();
		initElements();
		
		input = container.getInput();
		
		container.getGraphics().setLineWidth(3);
		
		EventBus.INSTANCE.register(this);
	}
	
	/**This will call {@code DynamicLoader} and receive a list of all towers currently implemented.
	 * It will then dynamically create a button in the button panel to the right in the GUI.
	 * Every button will have it's representing tower's annotation saved which will be used to identify the button when pressed.
	 */
	private void initGUIButtons() {
		boolean equal = true;
		Element leftBtnPanel = nifty.getCurrentScreen().findElementByName("leftButtonPanel");
		Element rightBtnPanel = nifty.getCurrentScreen().findElementByName("rightButtonPanel");
		ButtonBuilder bb = new ButtonBuilder("icons");
		bb.width("85%");
		bb.height("15%");
		bb.name("button");
		bb.font("verdana-smallregular.fnt");
		//Load all the towers.
		List<Class<ITower>> exportedTowers = DynamicLoader.getTowers();
		
		for(Class<ITower> towerClass : exportedTowers) {
			Element panel = (equal) ? leftBtnPanel : rightBtnPanel;
			Tower anno = towerClass.getAnnotation(Tower.class);
			if(anno != null) {
				bb.id(anno.name());
				bb.label(anno.name());
				//This will cause the button when pressed to call the buildTower() method with it's annotation as argument.
				bb.interactOnClick("buildTower("+ anno.name() + ")");
			}
			bb.build(nifty, nifty.getCurrentScreen(), panel);
			equal = !equal;
		}
	}
	
	/**Creates {@code Animation} which will be used by game renderer*/
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
	
	private void initElements() {
		Screen tmpScreen = nifty.getCurrentScreen();
		lifeLabel = tmpScreen.findElementByName("lifeLabel");
		scoreLabel = tmpScreen.findElementByName("scoreLabel");
		levelLabel = tmpScreen.findElementByName("levelLabel");
		playerMoneyLabel = tmpScreen.findElementByName("playerMoneyLabel");
		startWaveButton = tmpScreen.findElementByName("startWaveButton");
		optionsPopup = nifty.createPopup("optionsPopup");
		gameOverPopup = nifty.createPopup("gameOverPopup");
		towerPopup = nifty.createPopup("towerSelectedPopup");
	}

	@Override
	protected void prepareNifty(Nifty nifty, StateBasedGame state) {
		nifty.fromXml(getResourcePath("/gameplay_gui.xml"), "start", this);
		nifty.getSoundSystem().addMusic("backgroundmusic", getResourcePath("/audio/main_menu/music.wav"));
	}

	@Override
	protected void renderGame(GameContainer container, StateBasedGame state,
			Graphics g) throws SlickException {
		g.setColor(Color.black);
        renderStats();
        renderTiles();
        renderEnemies(g);
        renderPlayerCharacter(g);
        
        //If a tower has been selected it will show a small rectangle where you hold the
        //mouse to indicate if one can build on that tile or not.
        if(towerIsChoosen) {
        	renderBuildingFeedback(g);
        }
        if(attacksList != null && !attacksList.isEmpty()) {
        	renderAttacks(g);
        }
	}
	
	@Override
	protected void updateGame(GameContainer container, StateBasedGame state, int delta)
			throws SlickException {
		GameBoard board = gameControl.getGameBoard();
		
		this.delta = delta;
		if(!gameOver && !optionsScreenIsOpen) {
			gameControl.updateGameState(delta);
			
			mouseX = input.getMouseX();
			mouseY = input.getMouseY();
			
			checkForMovement(input, delta);
			
			if(input.isMousePressed(Input.MOUSE_RIGHT_BUTTON)) {
				towerIsChoosen = false;
				startWaveButton.setFocus();
			}
			
			if(mouseX < tileScale*board.getWidth() && mouseY < tileScale*board.getHeight()
					 && input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				int x = mouseX / tileScale;
				int y = mouseY / tileScale;
				BoardPosition p = BoardPosition.valueOf(x, y);
				if(towerIsChoosen && board.getTileAt(p) instanceof IBuildableTile) {
					gameControl.buildTower(choosenTower, p);
					if(!input.isKeyDown(Input.KEY_LSHIFT)) {
						towerIsChoosen = false;
						startWaveButton.setFocus();
					}
				}
				else if(board.getTileAt(p) instanceof IAttackTower && !towerIsChoosen) {
					selectedTower = (IAttackTower)board.getTileAt(p);
					towerPos = p;
					updateTowerPopup();
					nifty.showPopup(nifty.getCurrentScreen(), towerPopup.getId(), null);
				}
	    	}
		}
	}

	@Override
	public int getID() {
		return stateID;
	}
	
	/**Helper method to get correct path to files.*/
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
	
	/**
	 * Will add create a new {@code AttackAnimationDuration} with provided {@code TowerShootingEvent} and add
	 * it to the list of attacks that will be render by the game.
	 * @param event instance of {@code TowerShootingEvent} that will be used in the wrapper.
	 */
	@Subscribe
	public void renderTowerShooting(TowerShootingEvent event){
		AttackAnimationDuration attack = new AttackAnimationDuration(event, 1000);
		attacksList.add(attack);
	}
	
	@Subscribe
	public void enemyHasEnteredBase(EnemyEnteredBaseEvent e) {
		// TODO: Implement
	}
	
	@Subscribe
	public void playerIsDead(PlayerDeadEvent e) {
		gameOverPopup.findNiftyControl("gameOverScoreLabel", Label.class).setText("" + player.getCurrentScore());
		nifty.showPopup(nifty.getCurrentScreen(), gameOverPopup.getId(), null);
		gameOver = true;
	}
	
	@Subscribe
	public void waveHasEnded(WaveEndedEvent e) {
		// TODO: Implement
		startWaveButton.getNiftyControl(Button.class).enable();
	}
	
	@Subscribe
	public void waveHasStarted(WaveStartedEvent e) {
		// TODO: Implement
		startWaveButton.getNiftyControl(Button.class).disable();
	}
	
	private void renderTiles() {
		GameBoard board = gameControl.getGameBoard();
		
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
		GameBoard board = gameControl.getGameBoard();
		EnemyList enemies = board.getEnemies();
		
        for(IEnemy enemy : enemies) {
        	Position p = enemy.getPosition();
        	int health = enemy.getHealth();
        	NativeSprite image = enemy.getSprite().getNativeSprite();
        	
        	image.draw(p.getX(), p.getY(), tileScale, tileScale);
        	g.drawString(""+health, p.getX(), p.getY()-tileScale);
        }
	}
	
	private void renderPlayerCharacter(Graphics g) {
		IPlayerCharacter character = player.getCharacter();
		NativeSprite image = character.getSprite().getNativeSprite();
		
		image.draw(character.getPos().getX() - tileScale/2, 
				character.getPos().getY() - tileScale/2, tileScale, tileScale);
	}
	
	private void renderStats() {
	    lifeLabel.getNiftyControl(Label.class).setText("" + gameControl.getGameBoard().getPlayerBaseHealth());
	    scoreLabel.getNiftyControl(Label.class).setText("" + player.getCurrentScore());
	    levelLabel.getNiftyControl(Label.class).setText("" + gameControl.getWavesReleased());
	    playerMoneyLabel.getNiftyControl(Label.class).setText("" + player.getMoney());
	}
	
	/**Renders the small square and the big circle around the mouse location that gives the player visual feedback
	   showing if he can build on that tile or not and how far the tower can fire.*/
	private void renderBuildingFeedback(Graphics g) {
		GameBoard board = gameControl.getGameBoard();
		int x = mouseX/tileScale;
		int y = mouseY/tileScale;
		
		if(board.canBuildAt(BoardPosition.valueOf(x, y))) {
			g.setColor(Color.green);
			g.drawRect(x * tileScale, y * tileScale, tileScale, tileScale);
			g.setColor(Color.black);
			g.draw(new Circle(x * tileScale+tileScale/2, y * tileScale+tileScale/2, choosenTower.getRadius()*tileScale));
			
		} else {
			g.setColor(Color.red);
			g.drawRect(x * tileScale, y * tileScale, tileScale, tileScale);
			g.setColor(Color.black);
		}
	}
	
	/**Render attacks from the tower attacking to the enemy being attacked. Uses inner class {@code AttackAnimationDuration}.*/
	private void renderAttacks(Graphics g) {
		List<AttackAnimationDuration> tmpList = new LinkedList<AttackAnimationDuration>(attacksList);
		
		for(AttackAnimationDuration attack : tmpList) {
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
	
	/** This will cause the labels in the tower properties popup to re-render with updated values */
	private void updateTowerPopup() {
		towerPopup.findNiftyControl("towerDMGLabel", Label.class).setText("" + 
						selectedTower.getDmg());
		towerPopup.findNiftyControl("towerSPDLabel", Label.class).setText("" + 
						selectedTower.getAttackSpeed());
		towerPopup.findNiftyControl("towerUpgradeCostLabel", Label.class).setText("" + 
						selectedTower.getUpgradeCost());
		towerPopup.findNiftyControl("towerLVLLabel", Label.class).setText("" + 
						selectedTower.getCurrentLevel());
	}
	
	/**
	 * Listener to musicCheckbox element in the gui that will pause or resume background music. 
	 * @param id of the element that has published the event.
	 * @param event instance of {@code CheckBoxStateChangedEvent} class provided by nifty.
	 */
	@NiftyEventSubscriber(id="musicCheckbox")
	public void onMusicCheckboxEvent(String id, CheckBoxStateChangedEvent event) {
		if(backgroundMusic.playing()) {
			backgroundMusic.pause();
		}
		else {
			backgroundMusic.resume();
		}
	}

	/**
	 * Listener to musicVolumeSlider element in the gui that will pause or resume background music. 
	 * @param id of the element that has published the event.
	 * @param event instance of {@code SliderChangedEvent} class provided by nifty.
	 */
	@NiftyEventSubscriber(id="musicVolumeSlider")
	public void onSliderEvent(String id, SliderChangedEvent event) {
		backgroundMusic.setVolume(event.getValue()/100);
	}
	
	/**
	 * Listener to all primary mouse click events from elements which has an id that starts with "close".
	 * It will then depending on what element which caused the event act accordingly.
	 * @param id of the element that has published the event.
	 * @param event instance of {@code NiftyMousePrimaryClickedEvent} class provided by nifty.
	 */
	@NiftyEventSubscriber(pattern="close.*")
	public void onClick(String id, NiftyMousePrimaryClickedEvent event) {
		if(optionsPopup.findElementByName(id) != null) {
			if(optionsScreenIsOpen) {
				nifty.closePopup(optionsPopup.getId());
			}
			else {
				nifty.showPopup(nifty.getCurrentScreen(), optionsPopup.getId(), null);
			}
			optionsScreenIsOpen = !optionsScreenIsOpen;
		}
		else if(towerPopup.findElementByName(id) != null) {
			nifty.closePopup(towerPopup.getId());
		}
		else if(gameOverPopup.findElementByName(id) != null) {
			nifty.closePopup(gameOverPopup.getId());
			state.enterState(STDGame.MAINMENUSTATE);
		}
	}
	
	/**
	 * If given string matches any annotation of dynamic loaded towers,
	 * this will create a new instance of that tower and save the reference for further use in {@code GameplayState}.
	 * @param anno is the string that will be matched against towers annotations.
	 */
	public void buildTower(String anno) {
		towerIsChoosen = true;
		choosenTower = DynamicLoader.createTowerInstance(anno);
	}
	
	/** 
	 * When called the last selected tower will be sold.
	 */
	public void sellTower() {
		nifty.closePopup(towerPopup.getId());
		gameControl.sellTower(selectedTower, towerPos);
	}
	
	/** 
	 * When called the last selected tower will be upgraded if player has enough money.
	 */
	public void upgradeTower() {
		gameControl.upgradeTower(selectedTower);
		updateTowerPopup();
	}
	
	/** 
	 * When called will start the next enemy wave.
	 */
	public void startWave() {
		gameControl.nextWave();
	}
	
	/**
	 * This will check for player movement from the arrow keys and space key. 
	 * If there's movement it will tell the {@code GameController}.
	 */
	private void checkForMovement(Input input, int delta) {
		//Using multiple if statements because I want the game to check for all movement keys that are currently down.
		//If you were to break when you find a key that is down, the game wont be able to handle diagonal movement since that
		//relies on two keys being pressed at the same time.
		if(input.isKeyDown(Input.KEY_UP) ) {
			if(input.isKeyPressed(Input.KEY_SPACE)) {
				gameControl.tryToJump(MovementEnum.MOVE_UP);
			}
			else {
				gameControl.moveChar(MovementEnum.MOVE_UP, delta);
			}
		}
		if(input.isKeyDown(Input.KEY_RIGHT)) {
			if(input.isKeyPressed(Input.KEY_SPACE)) {
				gameControl.tryToJump(MovementEnum.MOVE_RIGHT);
			}
			else {
				gameControl.moveChar(MovementEnum.MOVE_RIGHT, delta);
			}
		}
		if(input.isKeyDown(Input.KEY_DOWN)) {
			if(input.isKeyPressed(Input.KEY_SPACE)) {
				gameControl.tryToJump(MovementEnum.MOVE_DOWN);
			}
			else {
				gameControl.moveChar(MovementEnum.MOVE_DOWN, delta);
			}
		}
		if(input.isKeyDown(Input.KEY_LEFT)) {
			if(input.isKeyPressed(Input.KEY_SPACE)) {
				gameControl.tryToJump(MovementEnum.MOVE_LEFT);
			}
			else {
				gameControl.moveChar(MovementEnum.MOVE_LEFT, delta);
			}
		}
	}
	
	/**
	 * Private helper class for representing a towers attack.
	 * @author Johan Gustafsson
	 * @date   May 08, 2012
	 */
	private class AttackAnimationDuration {
		private int duration;
		private TowerShootingEvent event;
		
		public AttackAnimationDuration(TowerShootingEvent event, int duration) {
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
