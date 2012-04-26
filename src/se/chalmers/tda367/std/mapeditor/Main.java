package se.chalmers.tda367.std.mapeditor;

import javax.swing.SwingUtilities;

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
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				MainFrame mf = new MainFrame();
				mf.setVisible(true);
			}
		});
	}

}
