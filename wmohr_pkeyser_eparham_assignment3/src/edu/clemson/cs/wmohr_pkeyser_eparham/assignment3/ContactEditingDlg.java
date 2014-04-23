package edu.clemson.cs.wmohr_pkeyser_eparham.assignment3;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ContactEditingDlg extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField nameField;
	private JTextField postField;
	private JTextField emailField;
	private JTextField phoneField;
	private DataStore myData = DataStore.getInstance();
	private MainFrame mainWindow = RelativePosition.getMainWindow();
	private JTable mainTable = mainWindow.getTable();
	private int selectedRow = mainTable.getSelectedRow();

	public ContactEditingDlg(final int row, final ContactTableModel ctm, boolean newContact) {
		String currentName, currentPostal, currentEmail, currentPhone;
		setTitle("Edit Contact");

		if(newContact == true)
			selectedRow = -1;
		
		setModal(true);
		setSize(500, 375);
		setLocationRelativeTo(mainWindow);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

		// Add Label and text box for "Name"
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JLabel lblName = new JLabel("Name:");
			contentPanel.add(lblName, "2, 2, right, default");
		}
		{
			try{
				currentName = myData.getContact(selectedRow).getName();
			} catch (ArrayIndexOutOfBoundsException e){
				currentName = "";
			}
			nameField = new JTextField(currentName);
			nameField.setMaximumSize(new Dimension(500, 20));
			contentPanel.add(nameField);
		}
		
		// Add Label and text box for "Postal Address"
		{
			JLabel lblPostalAddress = new JLabel("Postal Address");
			contentPanel.add(lblPostalAddress, "2, 4, right, default");
		}
		{
			try{
				currentPostal = myData.getContact(selectedRow).getPostal();
			} catch (ArrayIndexOutOfBoundsException e){
				currentPostal = "";
			}
			postField = new JTextField(currentPostal);
			postField.setMaximumSize(new Dimension(500, 20));
			contentPanel.add(postField);
		}
		
		// Add Label and text box for "Email"
		{
			JLabel lblEmailAddress = new JLabel("Email Address:");
			contentPanel.add(lblEmailAddress, "2, 6, right, default");
		}
		{
			try{
				currentEmail = myData.getContact(selectedRow).getEmail();
			} catch (ArrayIndexOutOfBoundsException e){
				currentEmail = "";
			}
			emailField = new JTextField(currentEmail);
			emailField.setMaximumSize(new Dimension(500, 20));
			contentPanel.add(emailField);
		}
		
		// Add Label and text box for "Phone Number"
		{
			JLabel lblPhoneNumber = new JLabel("Phone Number:");
			contentPanel.add(lblPhoneNumber, "2, 8, right, default");
		}
		{
			try{
				currentPhone = myData.getContact(selectedRow).getPhone();
			} catch (ArrayIndexOutOfBoundsException e){
				currentPhone = "";
			}
			phoneField = new JTextField(currentPhone);
			phoneField.setMaximumSize(new Dimension(500, 20));
			contentPanel.add(phoneField);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int colCount = RelativePosition.getContactTable().getColumnCount();
						ctm.setName(nameField.getText(), row);
						ctm.setPostal(postField.getText(), row);
						boolean valid = ctm.setEmail(emailField.getText(), row);
						ctm.setPhone(phoneField.getText(), row);
						myData.serializeContacts();
						for(int i = 0; i < colCount; i++) {
							RelativePosition.getContactTable().fireTableCellUpdated(row, i);
						}
						if(valid)
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
