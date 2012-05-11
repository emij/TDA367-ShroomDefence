package se.chalmers.tda367.std.mapeditor;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import se.chalmers.tda367.std.core.Properties;
import se.chalmers.tda367.std.mapeditor.events.NewMapEvent;
import se.chalmers.tda367.std.utilities.EventBus;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

@SuppressWarnings("serial")
public class NewMapWizard extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final JSpinner widthSpinner;
	private final JSpinner heightSpinner;
	private final JSpinner levelSpinner;
	private final JComboBox<DefaultTile> defaultTileComboBox;
	private JLabel lblHeight;
	private JLabel lblDefaultTile;
	private JLabel lblMapWidth;
	private JLabel lblMapLevel;
	private JPanel buttonPane;
	private JButton okButton;
	private JButton cancelButton;
	private JPanel panel;
	private JLabel lblPixelSize;

	/**
	 * Create the dialog.
	 */
	public NewMapWizard() {
		setType(Type.UTILITY);
		setResizable(false);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setTitle("New map");
		setBounds(100, 100, 275, 185);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			lblMapWidth = new JLabel("Width");
			lblMapWidth.setFont(new Font("Dialog", Font.PLAIN, 13));
			lblMapWidth.setToolTipText("The number of tiles used in the width");
			GridBagConstraints gbc_lblMapWidth = new GridBagConstraints();
			gbc_lblMapWidth.anchor = GridBagConstraints.EAST;
			gbc_lblMapWidth.insets = new Insets(0, 0, 5, 15);
			gbc_lblMapWidth.gridx = 0;
			gbc_lblMapWidth.gridy = 0;
			contentPanel.add(lblMapWidth, gbc_lblMapWidth);
		}
		{
			widthSpinner = new JSpinner();
			widthSpinner.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					updatePixelSize();
				}
			});
			widthSpinner.setModel(new SpinnerNumberModel(25, 10, 500, 1));
			GridBagConstraints gbc_spinner = new GridBagConstraints();
			gbc_spinner.fill = GridBagConstraints.HORIZONTAL;
			gbc_spinner.insets = new Insets(0, 0, 5, 5);
			gbc_spinner.gridx = 1;
			gbc_spinner.gridy = 0;
			contentPanel.add(widthSpinner, gbc_spinner);
		}
		{
			panel = new JPanel();
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.gridheight = 2;
			gbc_panel.insets = new Insets(0, 0, 5, 0);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 2;
			gbc_panel.gridy = 0;
			contentPanel.add(panel, gbc_panel);
			panel.setLayout(new BorderLayout(0, 0));
			{
				lblPixelSize = new JLabel("640x480 px");
				lblPixelSize.setHorizontalAlignment(SwingConstants.CENTER);
				panel.add(lblPixelSize);
			}
		}
		{
			lblHeight = new JLabel("Height");
			lblHeight.setFont(new Font("Dialog", Font.PLAIN, 13));
			GridBagConstraints gbc_lblHeight = new GridBagConstraints();
			gbc_lblHeight.insets = new Insets(0, 0, 5, 15);
			gbc_lblHeight.anchor = GridBagConstraints.EAST;
			gbc_lblHeight.gridx = 0;
			gbc_lblHeight.gridy = 1;
			contentPanel.add(lblHeight, gbc_lblHeight);
		}
		{
			heightSpinner = new JSpinner();
			heightSpinner.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					updatePixelSize();
				}
			});
			heightSpinner.setModel(new SpinnerNumberModel(20, 10, 500, 1));
			GridBagConstraints gbc_spinner = new GridBagConstraints();
			gbc_spinner.fill = GridBagConstraints.HORIZONTAL;
			gbc_spinner.insets = new Insets(0, 0, 5, 5);
			gbc_spinner.gridx = 1;
			gbc_spinner.gridy = 1;
			contentPanel.add(heightSpinner, gbc_spinner);
		}
		{
			lblDefaultTile = new JLabel("Default tile");
			lblDefaultTile.setFont(new Font("Dialog", Font.PLAIN, 13));
			GridBagConstraints gbc_lblDefaultTile = new GridBagConstraints();
			gbc_lblDefaultTile.anchor = GridBagConstraints.EAST;
			gbc_lblDefaultTile.insets = new Insets(0, 0, 5, 15);
			gbc_lblDefaultTile.gridx = 0;
			gbc_lblDefaultTile.gridy = 2;
			contentPanel.add(lblDefaultTile, gbc_lblDefaultTile);
		}
		{
			defaultTileComboBox = new JComboBox<DefaultTile>();
			defaultTileComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			defaultTileComboBox.setToolTipText("What tile to fill the \"background\" with by default");
			defaultTileComboBox.setModel(new DefaultComboBoxModel<DefaultTile>(DefaultTile.values()));
			GridBagConstraints gbc_comboBox = new GridBagConstraints();
			gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBox.insets = new Insets(0, 0, 5, 5);
			gbc_comboBox.gridx = 1;
			gbc_comboBox.gridy = 2;
			contentPanel.add(defaultTileComboBox, gbc_comboBox);
		}
		{
			lblMapLevel = new JLabel("Map level");
			lblMapLevel.setToolTipText("What level the new map shall represent.");
			lblMapLevel.setFont(new Font("Dialog", Font.PLAIN, 13));
			GridBagConstraints gbc_lblMapLevel = new GridBagConstraints();
			gbc_lblMapLevel.anchor = GridBagConstraints.EAST;
			gbc_lblMapLevel.insets = new Insets(0, 0, 0, 15);
			gbc_lblMapLevel.gridx = 0;
			gbc_lblMapLevel.gridy = 3;
			contentPanel.add(lblMapLevel, gbc_lblMapLevel);
		}
		{
			levelSpinner = new JSpinner();
			levelSpinner.setToolTipText("What level the new map shall represent.");
			levelSpinner.setModel(new SpinnerNumberModel(1, 1, 1000, 1));
			GridBagConstraints gbc_spinner = new GridBagConstraints();
			gbc_spinner.fill = GridBagConstraints.HORIZONTAL;
			gbc_spinner.insets = new Insets(0, 0, 0, 5);
			gbc_spinner.gridx = 1;
			gbc_spinner.gridy = 3;
			contentPanel.add(levelSpinner, gbc_spinner);
		}
		contentPanel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{lblHeight, lblDefaultTile, heightSpinner, defaultTileComboBox, widthSpinner, lblMapWidth, lblMapLevel, levelSpinner}));
		{
			buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// What happens on the OK button click.
						int width = (int)NewMapWizard.this.widthSpinner.getValue();
						int height = (int)NewMapWizard.this.heightSpinner.getValue();
						DefaultTile defTile = (DefaultTile)NewMapWizard.this.defaultTileComboBox.getSelectedItem();
						int level = (int)NewMapWizard.this.levelSpinner.getValue();
								
						NewMapEvent event = new NewMapEvent(width, height, defTile, level);
						EventBus.INSTANCE.post(event);
						NewMapWizard.this.setVisible(false);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// Hide form on Cancel click.
						NewMapWizard.this.setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		updatePixelSize();
	}
	
	private void updatePixelSize() {
		int width = (int) widthSpinner.getValue();
		int height = (int) heightSpinner.getValue();
		int scale = Properties.INSTANCE.getTileScale();
		
		lblPixelSize.setText(width*scale + "x" + height*scale);
	}

}
