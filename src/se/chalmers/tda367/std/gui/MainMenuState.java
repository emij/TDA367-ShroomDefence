package se.chalmers.tda367.std.gui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MainMenuState extends BasicGameState {
	private int stateID;
	private Image background;
	private Image startGameButton;
	private Image exitGameButton;
	private float startButtonScale;
	private float exitButtonScale;
	private int menuX;
	private int menuY;
	private Sound music;
	
	public MainMenuState(int stateID) {
		this.stateID = stateID;
	}
	
	private String getResourcePath(String path) {
		return getClass().getResource(path).getPath();
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame state)
			throws SlickException {
		background = new Image(getResourcePath("/images/main_menu/background.png"));
		startGameButton = new Image(getResourcePath("/images/main_menu/start_button.png"));
		startButtonScale = 1;
		exitGameButton = new Image(getResourcePath("/images/main_menu/exit_button.png"));
		exitButtonScale = 1;
		
		menuX = container.getWidth()/35;
		menuY = container.getHeight()/2;
		
		music = new Sound(getResourcePath("/audio/main_menu/music.wav"));
		music.loop();
	}

	@Override
	public void render(GameContainer container, StateBasedGame state, Graphics g)
			throws SlickException {
		background.draw(0, 0, container.getWidth(), container.getHeight());
	
		startGameButton.draw(menuX, menuY, startButtonScale);
		exitGameButton.draw(menuX, menuY+startGameButton.getHeight(), exitButtonScale);
	}

	@Override
	public void update(GameContainer container, StateBasedGame state, int arg2)
			throws SlickException {
		Input input = container.getInput();
		int mouseX = input.getMouseX();
		int mouseY = input.getMouseY();
		
		boolean overStartButton = false;
		boolean overExitButton = false;
		
		if((mouseX >= menuX && mouseX <= menuX+startGameButton.getWidth()) && 
				(mouseY >= menuY && mouseY <= menuY+startGameButton.getHeight())) {
			overStartButton = true;
		}
		else if((mouseX >= menuX && mouseX <= menuX+exitGameButton.getWidth()) && 
				(mouseY >= menuY+startGameButton.getHeight() && 
				 mouseY <= menuY+startGameButton.getHeight()+exitGameButton.getHeight())) {
			overExitButton = true;
		}
		if(overStartButton) {
			startButtonScale = 1.05f;
			exitButtonScale = 1;
			if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				state.enterState(STDGame.GAMEPLAYSTATE);
			}
		}
		else if(overExitButton){
			exitButtonScale = 1.05f;
			startButtonScale = 1;
			if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				container.exit();
			}
		}
		else {
			startButtonScale = 1;
			exitButtonScale = 1;
		}
		

	}

	@Override
	public int getID() {
		return stateID;
	}


}
