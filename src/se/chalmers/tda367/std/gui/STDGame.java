package se.chalmers.tda367.std.gui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class STDGame extends StateBasedGame {

    public static final int MAINMENUSTATE = 0;
    public static final int GAMEPLAYSTATE = 1;
    public static final int HIGHSCORESTATE = 2;
	
	public STDGame() {
		super("STD - Shroom Tower Defense");
		
		
        this.addState(new MainMenuState(MAINMENUSTATE));
        this.addState(new GameplayState(GAMEPLAYSTATE));
        this.addState(new HighscoreState(HIGHSCORESTATE));
        this.enterState(MAINMENUSTATE);
	}

	@Override
	public void initStatesList(GameContainer gameCon) throws SlickException {
	}
}
