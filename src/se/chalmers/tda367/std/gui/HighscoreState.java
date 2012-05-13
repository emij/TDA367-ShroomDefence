package se.chalmers.tda367.std.gui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
import de.lessvoid.nifty.slick2d.NiftyBasicGameState;

public class HighscoreState extends NiftyBasicGameState implements ScreenController {
	private int stateID;
	private StateBasedGame state;
	
	public HighscoreState(int stateID) {
		this.stateID = stateID;
	}

	@Override
	protected void prepareNifty(Nifty nifty, StateBasedGame state) {
		nifty.fromXml(getResourcePath("/highscore_gui.xml"), "start", this);
	}
	
	@Override
	public void enterState(GameContainer container, StateBasedGame state) throws SlickException {
		super.enterState(container, state);
		this.state = state;
	}
	
	private String getResourcePath(String path) {
		return getClass().getResource(path).getPath();
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
	
	public void closeHighscore() {
		state.enterState(STDGame.MAINMENUSTATE);
	}
}
