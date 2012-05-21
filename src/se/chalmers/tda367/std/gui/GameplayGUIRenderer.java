package se.chalmers.tda367.std.gui;

import com.google.common.eventbus.Subscribe;

import se.chalmers.tda367.std.core.GameController;
import se.chalmers.tda367.std.core.events.WaveEndedEvent;
import se.chalmers.tda367.std.core.events.WaveStartedEvent;
import se.chalmers.tda367.std.core.tiles.towers.IAttackTower;
import se.chalmers.tda367.std.utilities.EventBus;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.controls.Button;
import de.lessvoid.nifty.controls.Label;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.screen.Screen;

/**
 * Class for rendering and showing the gameplay gui.
 * Will be used by {@code GameplayState} as renderer.
 * @author Johan Gustafsson
 * @date 2012-05-15
 */
class GameplayGUIRenderer {
	private Nifty nifty;
	private Element lifeLabel, scoreLabel, levelLabel, playerMoneyLabel, startWaveButton;
	private GameController gameControl;
	
	public GameplayGUIRenderer(GameController gameControl, Nifty nifty) {
		this.nifty = nifty;
		this.gameControl = gameControl;
		init();
		
		EventBus.INSTANCE.register(this);
	}
	
	private void init() {
		initElements();
	}
	
	private void initElements() {
		Screen tmpScreen = nifty.getCurrentScreen();
		lifeLabel = tmpScreen.findElementByName("lifeLabel");
		scoreLabel = tmpScreen.findElementByName("scoreLabel");
		levelLabel = tmpScreen.findElementByName("levelLabel");
		playerMoneyLabel = tmpScreen.findElementByName("playerMoneyLabel");
		startWaveButton = tmpScreen.findElementByName("startWaveButton");
	}
	
	
	/**
	 * Renders the game's mutable gui elements.
	 */
	public void renderGUI() {
		renderStats();
	}

	
	private void renderStats() {
	    lifeLabel.getNiftyControl(Label.class).setText("" + gameControl.getGameBoard().getPlayerBaseHealth());
	    scoreLabel.getNiftyControl(Label.class).setText("" + gameControl.getPlayer().getCurrentScore());
	    levelLabel.getNiftyControl(Label.class).setText("" + gameControl.getWavesReleased());
	    playerMoneyLabel.getNiftyControl(Label.class).setText("" + gameControl.getPlayer().getMoney());
	}
	
	
	/**
	 * This will cause the labels in the tower properties popup to re-render with updated values
	 * @param tower tower to get values from.
	 * @param popup popup to get labels from.
	 */
	public void updateTowerPopup(IAttackTower tower, Element popup) {
		popup.findNiftyControl("towerDMGLabel", Label.class).setText("" + 
				tower.getDmg());
		popup.findNiftyControl("towerSPDLabel", Label.class).setText("" + 
				(double)tower.getAttackDelay()/1000);
		popup.findNiftyControl("towerUpgradeCostLabel", Label.class).setText("" + 
				tower.getUpgradeCost());
		popup.findNiftyControl("towerLVLLabel", Label.class).setText("" + 
				tower.getCurrentLevel());
	}
	
	/**
	 * If provided with a id representing an popup this will show the popup.
	 * Will show a warning in console if id is not found and will not show any popup.
	 * @param id - element id of the popup.
	 */
	public void showPopup(String id) {
		nifty.showPopup(nifty.getCurrentScreen(), id, null);
	}
	
	/**
	 * If provided with a id representing an popup this will close the popup.
	 * Will show a warning in console if id is not found.
	 * @param id - element id of the popup.
	 */
	public void closePopup(String id) {
		nifty.closePopup(id);
	}
	
	/**
	 * Will show an updated game over popup.
	 * @param popup
	 */
	public void showEndGamePopup(Element popup) {
		popup.findNiftyControl("gameOverScoreLabel", Label.class).setText("" +
				gameControl.getPlayer().getCurrentScore());
		showPopup(popup.getId());
	}
	
	
	@Subscribe
	public void waveHasEnded(WaveEndedEvent e) {
		startWaveButton.getNiftyControl(Button.class).enable();
	}
	
	@Subscribe
	public void waveHasStarted(WaveStartedEvent e) {
		startWaveButton.getNiftyControl(Button.class).disable();
	}
	
	/**
	 * Will set the focus of the screen to the default element.
	 */
	public void setDefaultFocus() {
		startWaveButton.setFocus();
	}
}
