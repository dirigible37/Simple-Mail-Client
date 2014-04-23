package edu.clemson.cs.wmohr_pkeyser_eparham.assignment3;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.awt.GridLayout;
import javax.swing.ButtonGroup;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class ConfigurationDlg extends JDialog {

	private final JPanel contentPanel = new JPanel();
	//TODO: make text field an arrayList?
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private DataStore myData = DataStore.getInstance();
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private ActionListeners listener = new ActionListeners();

	public ConfigurationDlg() {
		String currentEmail, currentSmtp, currentPassword, currentPort;
		boolean currentTsl;
		setModal(true);
		setSize(450, 300);
		setLocationRelativeTo(RelativePosition.getMainWindow());
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] {150, 30, 217, 0};
		gbl_contentPanel.rowHeights = new int[]{22, 22, 22, 22, 22, 22, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		
		//Initialize current data
		try{
			currentEmail = myData.getConfig().getEmail();
			currentPassword = myData.getConfig().getPassword();
			currentSmtp = myData.getConfig().getSmtp();
			currentPort = myData.getConfig().getPort();
			currentTsl = myData.getConfig().getTsl();
		} catch (ArrayIndexOutOfBoundsException e){
			currentEmail = "";
			currentPassword = "";
			currentSmtp = "";
			currentPort = "";
			currentTsl = false;
		}
		
		// Add Label and text box for "Email"
		{
			JLabel lblEmail = new JLabel("Email:");
			GridBagConstraints gbc_lblEmail = new GridBagConstraints();
			gbc_lblEmail.fill = GridBagConstraints.BOTH;
			gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
			gbc_lblEmail.gridx = 0;
			gbc_lblEmail.gridy = 0;
			contentPanel.add(lblEmail, gbc_lblEmail);
		}
		textField = new JTextField(currentEmail);
		textField.setMaximumSize(new Dimension(500, 20));
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 2;
		gbc_textField.fill = GridBagConstraints.BOTH;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 0;
		contentPanel.add(textField, gbc_textField);
		
		// Add Label and text box for "Password"
		{
			JLabel lblPassword = new JLabel("Password:");
			GridBagConstraints gbc_lblPassword = new GridBagConstraints();
			gbc_lblPassword.fill = GridBagConstraints.BOTH;
			gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
			gbc_lblPassword.gridx = 0;
			gbc_lblPassword.gridy = 1;
			contentPanel.add(lblPassword, gbc_lblPassword);
		}
		textField_1 = new JPasswordField(currentPassword);
		textField_1.setMaximumSize(new Dimension(500, 20));
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.gridwidth = 2;
		gbc_textField_1.fill = GridBagConstraints.BOTH;
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 1;
		contentPanel.add(textField_1, gbc_textField_1);
		
		// Add Label and text box for "SMTP Server"
		{
			JLabel lblSmtp = new JLabel("SMTP Server:");
			GridBagConstraints gbc_lblSmtp = new GridBagConstraints();
			gbc_lblSmtp.fill = GridBagConstraints.BOTH;
			gbc_lblSmtp.insets = new Insets(0, 0, 5, 5);
			gbc_lblSmtp.gridx = 0;
			gbc_lblSmtp.gridy = 2;
			contentPanel.add(lblSmtp, gbc_lblSmtp);
		}
		textField_2 = new JTextField(currentSmtp);
		textField_2.setMaximumSize(new Dimension(500, 20));
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.gridwidth = 2;
		gbc_textField_2.fill = GridBagConstraints.BOTH;
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.gridx = 1;
		gbc_textField_2.gridy = 2;
		contentPanel.add(textField_2, gbc_textField_2);
		
		// Add Label and text box for "SMTP Port"
		{
			JLabel lblPassword = new JLabel("SMTP Port:");
			GridBagConstraints gbc_lblPassword = new GridBagConstraints();
			gbc_lblPassword.fill = GridBagConstraints.BOTH;
			gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
			gbc_lblPassword.gridx = 0;
			gbc_lblPassword.gridy = 3;
			contentPanel.add(lblPassword, gbc_lblPassword);
		}
		textField_3 = new JTextField(currentPort);
		textField_3.setMaximumSize(new Dimension(500, 20));
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.gridwidth = 2;
		gbc_textField_3.fill = GridBagConstraints.BOTH;
		gbc_textField_3.insets = new Insets(0, 0, 5, 5);
		gbc_textField_3.gridx = 1;
		gbc_textField_3.gridy = 3;
		contentPanel.add(textField_3, gbc_textField_3);
		
		//Add Label and radio buttons for "TLS/SSL"
		{
			JLabel lblTlsSsl = new JLabel("TLS/SSL Required:");
			GridBagConstraints gbc_lblTlsSsl = new GridBagConstraints();
			gbc_lblTlsSsl.fill = GridBagConstraints.BOTH;
			gbc_lblTlsSsl.insets = new Insets(0, 0, 5, 5);
			gbc_lblTlsSsl.gridx = 0;
			gbc_lblTlsSsl.gridy = 4;
			contentPanel.add(lblTlsSsl, gbc_lblTlsSsl);
		}
		{
			JRadioButton rdbtnYes = new JRadioButton("Yes", currentTsl);
			buttonGroup.add(rdbtnYes);
			GridBagConstraints gbc_rdbtnYes = new GridBagConstraints();
			gbc_rdbtnYes.fill = GridBagConstraints.BOTH;
			gbc_rdbtnYes.insets = new Insets(0, 0, 5, 5);
			gbc_rdbtnYes.gridx = 1;
			gbc_rdbtnYes.gridy = 4;
			rdbtnYes.addActionListener(listener.getRadioAction(myData, true));
			rdbtnYes.setActionCommand("SELECTED_KEY");
			contentPanel.add(rdbtnYes, gbc_rdbtnYes);
		}
		{
			JRadioButton rdbtnNo = new JRadioButton("No", currentTsl);
			buttonGroup.add(rdbtnNo);
			GridBagConstraints gbc_rdbtnNo = new GridBagConstraints();
			gbc_rdbtnNo.insets = new Insets(0, 0, 5, 0);
			gbc_rdbtnNo.fill = GridBagConstraints.BOTH;
			gbc_rdbtnNo.gridx = 2;
			gbc_rdbtnNo.gridy = 4;
			rdbtnNo.addActionListener(listener.getRadioAction(myData, false));
			rdbtnNo.setActionCommand("SELECTED_KEY");
			contentPanel.add(rdbtnNo, gbc_rdbtnNo);
		}
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						myData.getConfig().setEmail(textField.getText());
						myData.getConfig().setPassword(textField_1.getText());
						myData.getConfig().setSmtp(textField_2.getText());
						myData.getConfig().setPort(textField_3.getText());
						myData.serializeConfig();
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
