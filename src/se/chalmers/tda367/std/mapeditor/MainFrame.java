package se.chalmers.tda367.std.mapeditor;

import java.awt.BorderLayout;
import javax.swing.*;

/**
 * The main frame of the Map Editor.
 * @author Emil Edholm
 * @date   Apr 26, 2012
 */
@SuppressWarnings("serial")
public final class MainFrame extends JFrame {
	public MainFrame(){
		initializeFrame();
	}
	
	private void initializeFrame(){
	    this.setLayout(new BorderLayout());
	    this.setTitle("STD Map Editor");
	    this.setLocationRelativeTo(null); // Center screen
	    this.setSize(600,500);
	}
}
