package edu.clemson.cs.wmohr_pkeyser_eparham.assignment3;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
//Can read?
public class ErrorDlg extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final JLabel errorMsg = new JLabel();
	private MainFrame mainWindow = RelativePosition.getMainWindow();
	private ImageIcon error = new ImageIcon("icons/error.png");

	//TODO: Make error icon and relative position parameters
	public ErrorDlg(String errorMessage) {
		System.out.println(error.getIconHeight());
		errorMsg.setIcon(error);
		errorMsg.setText(errorMessage);
		contentPanel.add(errorMsg);
		setModal(true);
		setSize(200, 120);
		setLocationRelativeTo(mainWindow);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
}
