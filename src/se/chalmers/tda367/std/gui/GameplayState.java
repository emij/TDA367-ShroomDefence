package se.chalmers.tda367.std.gui;

import java.io.IOException;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import com.google.common.eventbus.Subscribe;

import se.chalmers.tda367.std.core.*;
import se.chalmers.tda367.std.core.anno.Tower;
import se.chalmers.tda367.std.core.events.*;
import se.chalmers.tda367.std.core.tiles.towers.*;
import se.chalmers.tda367.std.utilities.BoardPosition;
import se.chalmers.tda367.std.utilities.EventBus;
import se.chalmers.tda367.std.utilities.IO;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.NiftyEventSubscriber;
import de.lessvoid.nifty.controls.CheckBoxStateChangedEvent;
import de.lessvoid.nifty.controls.SliderChangedEvent;
import de.lessvoid.nifty.controls.TextField;
import de.lessvoid.nifty.controls.button.builder.ButtonBuilder;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.events.NiftyMousePrimaryClickedEvent;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
import de.lessvoid.nifty.slick2d.NiftyOverlayBasicGameState;

/**
 * This class represents the gameplay state. This state let's the player interact with and
 * play the game via a graphical user interface.
 * Will be initiated by {@code STDGame} and called from {@code MainMenuState}.
 * @author Johan Gustafsson
 * @date 2012-04-25
 */

public class GameplayState extends NiftyOverlayBasicGameState implements ScreenController {
	private int stateID, tileScale;
	private boolean towerIsChoosen, optionsScreenIsOpen, gameOver;
	private Properties properties = Properties.INSTANCE;
	
	private IGame gameControl;
	private ITower choosenTower;
	private IAttackTower selectedTower;
	private BoardPosition towerPos;
	
	private Nifty nifty;
	private Element optionsPopup, gameOverPopup, towerPopup;
	private Music backgroundMusic;
	private StateBasedGame state;
	private GameplayRenderer gameRenderer;
	private GameplayGUIRenderer guiRenderer;
	private Input input;
	

	public GameplayState(int stateID, IGame gameControl) {
		this.stateID = stateID;
		this.gameControl = gameControl;
		tileScale = properties.getTileScale();
	}
	
	@Override
	protected void enterState(GameContainer container, StateBasedGame state)
			throws SlickException {
		this.state = state;
		
		//Reset state from last play.
		towerIsChoosen = false;
		optionsScreenIsOpen = false;
		gameOver = false;
		
		gameRenderer = new GameplayRenderer(gameControl, input);
		guiRenderer = new GameplayGUIRenderer(gameControl, nifty);
		
		backgroundMusic.loop(1, 1);
	}
	
	@Override
	protected void leaveState(GameContainer container, StateBasedGame state)
			throws SlickException {
		backgroundMusic.stop();
		gameControl.resetGame();
	}

	@Override
	protected void initGameAndGUI(GameContainer container, StateBasedGame state)
			throws SlickException {		
		initSound();
		initNifty(container, state);
		nifty = this.getNifty();
		initGUIButtons();
		
		input = container.getInput();
		
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
		bb.inputMapping("se.chalmers.tda367.std.gui.GameInputMapping");
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
			//Alternating between true and false will make the buttons appear on both panels and in right order.
			equal = !equal;
		}
	}
	
	private void initSound() throws SlickException {
		backgroundMusic = new Music(getResourcePath("/audio/main_menu/music.wav"));
	}
	
	@Override
	protected void prepareNifty(Nifty nifty, StateBasedGame state) {
		nifty.fromXml(getResourcePath("/gameplay_gui.xml"), "start", this);
		nifty.getSoundSystem().addMusic("backgroundmusic", getResourcePath("/audio/main_menu/music.wav"));
		optionsPopup = nifty.createPopup("optionsPopup");
		gameOverPopup = nifty.createPopup("gameOverPopup");
		towerPopup = nifty.createPopup("towerSelectedPopup");
	}

	@Override
	protected void renderGame(GameContainer container, StateBasedGame state,
			Graphics g) throws SlickException {
		gameRenderer.renderGame(g);
        guiRenderer.renderGUI();
        
        if(towerIsChoosen) {
        	gameRenderer.renderBuildingFeedback(g, choosenTower);
        }
	}
	
	@Override
	protected void updateGame(GameContainer container, StateBasedGame state, int delta)
			throws SlickException {
		
		if(!gameOver && !optionsScreenIsOpen) {
			gameControl.updateGameState(delta);
			gameRenderer.updateAttackTimers(delta);
			
			checkForMovement(delta);
			checkForInput();
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
	
	@Subscribe
	/** 
	 * Listener for PlayerDeadEvents, will halt the game and show the endgame popup if PlayerDeadEvent is found.
	 * @param e
	 */
	public void playerIsDead(PlayerDeadEvent e) {
		guiRenderer.showEndGamePopup(gameOverPopup);
		gameOver = true;
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
	 * Listener to all primary mouse click events from elements which has an id that starts with "closePopup".
	 * It will then depending on what element which caused the event act accordingly.
	 * @param id of the element that has published the event.
	 * @param event instance of {@code NiftyMousePrimaryClickedEvent} class provided by nifty.
	 */
	@NiftyEventSubscriber(pattern="closePopup.*")
	public void onClosePopup(String id, NiftyMousePrimaryClickedEvent event) {
		guiRenderer.closePopup();
		if(optionsPopup.findElementByName(id) != null) {
			optionsScreenIsOpen = false;
		}
	}
	
	/**
	 * Opens the options menu and set the optionsScreenIsOpen to true.
	 */
	public void openOptionsMenu() {
		guiRenderer.showPopup(optionsPopup.getId());
		optionsScreenIsOpen = true;
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
		guiRenderer.closePopup();
		gameControl.sellTower(selectedTower, towerPos);
	}
	
	
	/** 
	 * When called the last selected tower will be upgraded if player has enough money.
	 */
	public void upgradeTower() {
		gameControl.upgradeTower(selectedTower);
		guiRenderer.updateTowerPopup(selectedTower, towerPopup);
	}
	
	
	/** 
	 * When called will start the next enemy wave.
	 */
	public void startWave() {
		gameControl.nextWave();
	}
	
	/**
	 * 
	 */
	public void endGame() {
		state.enterState(STDGame.MAINMENUSTATE);
	}
	
	public void saveHighscore() {
		String playerName = gameOverPopup.findNiftyControl("playerNameField", TextField.class).getText();
		//If no name is entered the game will set the name to Unnamed.
		if(playerName.equals("")) {
			playerName = "Unnamed";
		}
		int score = gameControl.getPlayer().getCurrentScore();
		try {
			Highscore hs = IO.loadObject(Highscore.class, properties.getHighscoreFile());
			hs.addScore(new Score(playerName, score));
			IO.saveObject(hs, properties.getHighscoreFile());
		} catch (ClassNotFoundException | IOException e1) {
			System.out.println(e1.getMessage());
		}
		guiRenderer.closePopup();
		state.enterState(STDGame.MAINMENUSTATE);
	}

	
	/**
	 * This will check for player movement from the arrow keys and space key. 
	 * If there's movement it will tell the {@code GameController}.
	 */
	private void checkForMovement(int delta) {
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
	
	/** Check for input that should interact with the game in some way */
	private void checkForInput() {
		IGameBoard board = gameControl.getGameBoard();
		int mouseX = input.getMouseX();
		int mouseY = input.getMouseY();
		
		if(input.isMousePressed(Input.MOUSE_RIGHT_BUTTON)) {
			towerIsChoosen = false;
			guiRenderer.setDefaultFocus();
		}
		
		if(mouseX < tileScale*board.getWidth() && mouseY < tileScale*board.getHeight()
				 && input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			
			int x = mouseX / tileScale;
			int y = mouseY / tileScale;
			BoardPosition p = BoardPosition.valueOf(x, y);
			if(towerIsChoosen && gameControl.isBuildableSpot(p)) {
				gameControl.buildTower(choosenTower, p);
				towerIsChoosen = false;
				guiRenderer.setDefaultFocus();
			}
			else if(!towerIsChoosen && gameControl.isTowerPosition(p)) {
				if(nifty.getTopMostPopup() == null) {
					selectedTower = (IAttackTower)board.getTileAt(p);
					towerPos = p;
					guiRenderer.updateTowerPopup(selectedTower, towerPopup);
					guiRenderer.showPopup(towerPopup.getId());
				}
			}
    	}
	}
}
