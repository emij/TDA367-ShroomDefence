package se.chalmers.tda367.std.gui;

import java.io.IOException;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import se.chalmers.tda367.std.core.Highscore;
import se.chalmers.tda367.std.core.Properties;
import se.chalmers.tda367.std.core.Score;
import se.chalmers.tda367.std.utilities.IO;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.controls.label.builder.LabelBuilder;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
import de.lessvoid.nifty.slick2d.NiftyBasicGameState;

public class HighscoreState extends NiftyBasicGameState implements ScreenController {
	private int stateID;
	private StateBasedGame state;
	private Nifty nifty;
	private Element playerPanel, scorePanel;
	private Properties properties = Properties.INSTANCE;
	
	public HighscoreState(int stateID) {
		this.stateID = stateID;
	}

	@Override
	protected void prepareNifty(Nifty nifty, StateBasedGame state) {
		nifty.fromXml(getResourcePath("/highscore_gui.xml"), "start", this);
		playerPanel = nifty.getCurrentScreen().findElementByName("playerPanel");
		scorePanel = nifty.getCurrentScreen().findElementByName("scorePanel");
	}
	
	@Override
	public void enterState(GameContainer container, StateBasedGame state) throws SlickException {
		super.enterState(container, state);
		this.state = state;
		nifty = this.getNifty();
		try {
			Highscore hs = IO.loadObject(Highscore.class, properties.getHighscore());
			initHighscore(hs);
		} catch (ClassNotFoundException | IOException e1) {
			System.out.println(e1.getMessage());
		}
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
	
	private void initHighscore(Highscore highscore) {
		LabelBuilder lb = new LabelBuilder("highscores");
		lb.width("100%");
		lb.height("5%");
		lb.name("label");
		lb.textHAlignCenter();
		lb.textVAlignCenter();
		lb.font("verdana-smallregular.fnt");
		
		for(Score score : highscore.getScores()) {
			lb.id(score.getName());
			lb.label(score.getName());
			lb.build(nifty, nifty.getCurrentScreen(), playerPanel);
			lb.id(score.getName() + ".score");
			lb.label("" + score.getScore());
			lb.build(nifty, nifty.getCurrentScreen(), scorePanel);
		}

	}
	
	public void closeHighscore() {
		state.enterState(STDGame.MAINMENUSTATE);
	}
}
