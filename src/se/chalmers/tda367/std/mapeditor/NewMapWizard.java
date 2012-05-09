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
import java.awt.Dialog.ModalityType;
import java.awt.Window.Type;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NewMapWizard extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public NewMapWizard() {
		setType(Type.UTILITY);
		setResizable(false);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setTitle("New map");
		setBounds(100, 100, 199, 151);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblMapWidth = new JLabel("Width");
			lblMapWidth.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblMapWidth.setToolTipText("The number of tiles used in the width");
			GridBagConstraints gbc_lblMapWidth = new GridBagConstraints();
			gbc_lblMapWidth.anchor = GridBagConstraints.EAST;
			gbc_lblMapWidth.insets = new Insets(0, 0, 5, 5);
			gbc_lblMapWidth.gridx = 0;
			gbc_lblMapWidth.gridy = 0;
			contentPanel.add(lblMapWidth, gbc_lblMapWidth);
		}
		{
			JSpinner spinner = new JSpinner();
			spinner.setModel(new SpinnerNumberModel(25, 10, 500, 1));
			GridBagConstraints gbc_spinner = new GridBagConstraints();
			gbc_spinner.insets = new Insets(0, 0, 5, 0);
			gbc_spinner.gridx = 1;
			gbc_spinner.gridy = 0;
			contentPanel.add(spinner, gbc_spinner);
		}
		{
			JLabel lblHeight = new JLabel("Height");
			lblHeight.setFont(new Font("Tahoma", Font.BOLD, 14));
			GridBagConstraints gbc_lblHeight = new GridBagConstraints();
			gbc_lblHeight.insets = new Insets(0, 0, 5, 5);
			gbc_lblHeight.anchor = GridBagConstraints.EAST;
			gbc_lblHeight.gridx = 0;
			gbc_lblHeight.gridy = 1;
			contentPanel.add(lblHeight, gbc_lblHeight);
		}
		{
			JSpinner spinner = new JSpinner();
			spinner.setModel(new SpinnerNumberModel(20, 10, 500, 1));
			GridBagConstraints gbc_spinner = new GridBagConstraints();
			gbc_spinner.insets = new Insets(0, 0, 5, 0);
			gbc_spinner.gridx = 1;
			gbc_spinner.gridy = 1;
			contentPanel.add(spinner, gbc_spinner);
		}
		{
			JLabel lblDefaultTile = new JLabel("Default tile");
			lblDefaultTile.setFont(new Font("Segoe UI", Font.BOLD, 14));
			GridBagConstraints gbc_lblDefaultTile = new GridBagConstraints();
			gbc_lblDefaultTile.anchor = GridBagConstraints.EAST;
			gbc_lblDefaultTile.insets = new Insets(0, 0, 0, 5);
			gbc_lblDefaultTile.gridx = 0;
			gbc_lblDefaultTile.gridy = 2;
			contentPanel.add(lblDefaultTile, gbc_lblDefaultTile);
		}
		{
			JComboBox<DefaultTile> comboBox = new JComboBox<DefaultTile>();
			comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			comboBox.setToolTipText("What tile to fill the \"background\" by default");
			comboBox.setModel(new DefaultComboBoxModel<DefaultTile>(DefaultTile.values()));
			GridBagConstraints gbc_comboBox = new GridBagConstraints();
			gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBox.gridx = 1;
			gbc_comboBox.gridy = 2;
			contentPanel.add(comboBox, gbc_comboBox);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						NewMapWizard.this.setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
