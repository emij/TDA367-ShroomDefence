package se.chalmers.tda367.std.gui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
import de.lessvoid.nifty.slick2d.NiftyBasicGameState;

public class MainMenuState extends NiftyBasicGameState implements ScreenController {
	private int stateID;
	private StateBasedGame state;
	private GameContainer container;
	
	public MainMenuState(int stateID) {
		this.stateID = stateID;
	}
	
	@Override
	protected void prepareNifty(Nifty nifty, StateBasedGame state) {
		nifty.fromXml(getResourcePath("/mainmenu_gui.xml"), "start", this);
	}
	
	@Override
	public void enterState(GameContainer container, StateBasedGame state) throws SlickException {
		super.enterState(container, state);
		this.container = container;
		this.state = state;
	}
	
	private String getResourcePath(String path) {
		return getClass().getResource(path).getPath();
	}
	
	/** 
	 * When called will cause state to enter state {@code STDGame.GAMEPLAYSTATE}.
	 */
	public void startGame() {
		state.enterState(STDGame.GAMEPLAYSTATE);
	}

	/** 
	 * When called will cause state to enter state {@code STDGame.HIGHSCORESTATE}.
	 */
	public void showHighscores() {
		state.enterState(STDGame.HIGHSCORESTATE);
	}
	
	/** 
	 * When called will exit the game.
	 */
	public void exitGame() {
		container.exit();
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
}
