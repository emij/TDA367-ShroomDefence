package se.chalmers.tda367.towerdefence;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.AppGameContainer;

/**
 * The main class containing the main method.
 * @author Emil Edholm
 * @date 2012-03-13
 */
public final class Main extends BasicGame {
	public Main(){
        super("Bepa");
    }
	
	@Override
    public void init(GameContainer container) throws SlickException {}

    @Override
    public void update(GameContainer container, int delta)
            throws SlickException {}

    @Override
    public void render(GameContainer container, Graphics g)
            throws SlickException {
        g.drawString("Hello, Tower Defence", 0, 100);
    }
    /**
	 * @param args the command line arguments.
	 */
    public static void main(String[] args) {
        try {
            AppGameContainer app = new AppGameContainer(new Main(), 640, 480, false);
            app.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

}