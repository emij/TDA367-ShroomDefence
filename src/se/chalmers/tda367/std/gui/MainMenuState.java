package se.chalmers.tda367.std.gui;

import java.awt.Panel;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.Layer;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.NiftyEventSubscriber;
import de.lessvoid.nifty.builder.LayerBuilder;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.elements.events.NiftyMousePrimaryClickedEvent;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
import de.lessvoid.nifty.slick2d.NiftyBasicGameState;

public class MainMenuState extends NiftyBasicGameState implements ScreenController {
	private int stateID;
	private StateBasedGame state;
	private GameContainer container;
	private Image background;
	private Image startGameButton;
	private Image exitGameButton;
	private float startButtonScale;
	private float exitButtonScale;
	private int menuX;
	private int menuY;
	
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
	
	public void startGame() {
		state.enterState(STDGame.GAMEPLAYSTATE);
	}

	public void showHighscores() {
		state.enterState(STDGame.HIGHSCORESTATE);
	}
	
	public void exitGame() {
		container.exit();
	}
	
	@Override
	public int getID() {
		return stateID;
	}
	
	@NiftyEventSubscriber(pattern="button.*")
	public void onClick(String id, NiftyMousePrimaryClickedEvent event) {
		state.enterState(STDGame.GAMEPLAYSTATE);
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
