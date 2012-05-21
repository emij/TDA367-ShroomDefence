package se.chalmers.tda367.std.gui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import com.google.inject.Guice;

import se.chalmers.tda367.std.core.IGame;

/**
 * This will initiate all current gui states and start {@code MainMenuState}.
 * Will be called by {@code Main}.
 * @author Johan Gustafsson
 * @date 2012-03-28
 */

public class STDGame extends StateBasedGame {
	
    static final int MAINMENUSTATE = 0;
    static final int GAMEPLAYSTATE = 1;
    static final int HIGHSCORESTATE = 2;
    
    private IGame gameControl;
	
	public STDGame() {
		super("STD - Shroom Tower Defense");
		
	}

	@Override
	public void initStatesList(GameContainer gameCon) throws SlickException {
		gameControl = Guice.createInjector().getInstance(IGame.class);
		
		
		this.addState(new MainMenuState(MAINMENUSTATE));
        this.addState(new GameplayState(GAMEPLAYSTATE, gameControl));
        this.addState(new HighscoreState(HIGHSCORESTATE));
        this.enterState(MAINMENUSTATE);
	}
}
