package se.chalmers.tda367.std.mapeditor;

import java.util.logging.Logger;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * The class that contains the Main method for the STD Map Editor.
 * @author Emil Edholm
 * @date   Apr 26, 2012
 */
public final class Main {

	/**
	 * Launches the map editor GUI.
	 * @param args the command line arguments.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch(Exception e) {
			Logger.getLogger("se.chalmers.tda367.std.mapeditor").severe("Unable to set look and feel of the map editor");
			e.printStackTrace();
		}
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				MainFrame mf = new MainFrame();
				mf.setVisible(true);
			}
		});
	}

}
