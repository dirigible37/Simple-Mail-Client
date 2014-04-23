package edu.clemson.cs.wmohr_pkeyser_eparham.assignment3;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public abstract class EmailInterface extends JDialog {
	private static final long serialVersionUID = 7308725574668035205L;
	private final JPanel contentPanel = new JPanel();
	private JPanel buttonPanel = new JPanel();
	private JTextField fromField, toField, subField;
	private JTextArea bodyField;
	private DataStore myData = DataStore.getInstance();
	private MainFrame mainWindow = RelativePosition.getMainWindow();
	private JTable mainTable = mainWindow.getTable();
	private int selectedRow = mainTable.getSelectedRow(), row;
	private SentEmailsTableModel sentEmails = RelativePosition.getSentEmailsWindow();
	private String currentName, currentTo, title, currentMsg, currentSub;
	private Email email;
	private boolean isSent;

	public EmailInterface(int r, Email e, boolean edit, String windowTitle) {
		this.email = e;
		this.row = r;
		this.isSent = edit;
		this.title = windowTitle;
	}

	public void showDialog() {
		setTitle(title);

		setModal(true);
		setSize(550, 413);
		setLocationRelativeTo(mainWindow);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		
		addFrom();
		addTo();
		addSubject();
		addContent();
		addSendButton();
		addCancelButton();

	}

	// Create "From" Field
	public void addFrom() {
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		JLabel lblFrom = new JLabel("From:");
		lblFrom.setLocation(10, 5);
		lblFrom.setSize(40, 15);
		contentPanel.add(lblFrom);
		try {
			currentName = myData.getConfig().getEmail();
		} catch (ArrayIndexOutOfBoundsException e) {
			currentName = "";
		}
		fromField = new JTextField(currentName);
		fromField.setBounds(5, 20, 280, 19);
		fromField.setHorizontalAlignment(SwingConstants.LEFT);
		fromField.setMaximumSize(new Dimension(550, 50));
		fromField.setEditable(false);
		contentPanel.add(fromField);
	}

	// Create "To" Field
	public void addTo() {
		JLabel lblTo = new JLabel("To: (Seperated by commas)");
		lblTo.setBounds(10, 39, 193, 15);
		contentPanel.add(lblTo);
		try {
			if(!isSent)
				currentTo = myData.getSentEmails().get(row).getTo();
			else
				currentTo = myData.getContact(selectedRow).getEmail();
		} catch (ArrayIndexOutOfBoundsException e) {
			currentTo = "";
		}
		toField = new JTextField(currentTo);
		toField.setBounds(5, 54, 280, 19);
		toField.setMaximumSize(new Dimension(550, 50));
		toField.setEditable(isSent);
		contentPanel.add(toField);
	}

	// Create "Subject" Field
	public void addSubject() {
		JLabel lblEmailAddress = new JLabel("Subject:");
		lblEmailAddress.setBounds(10, 73, 58, 15);
		contentPanel.add(lblEmailAddress);
		if(!isSent) {
			try {
				currentSub = myData.getSentEmails().get(row).getSubject();
			} catch (ArrayIndexOutOfBoundsException e) {
				currentSub = "";
			}
		}
		subField = new JTextField(currentSub);
		subField.setBounds(5, 88, 280, 19);
		subField.setMaximumSize(new Dimension(550, 50));
		subField.setEditable(isSent);
		contentPanel.add(subField);
	}

	// Create "Content" Field
	public void addContent() {
		JLabel lblPhoneNumber = new JLabel("Message:");
		lblPhoneNumber.setBounds(10, 107, 62, 15);
		contentPanel.add(lblPhoneNumber);
		if(!isSent) {
			try {
				currentMsg = myData.getSentEmails().get(row).getMessage();
			} catch (ArrayIndexOutOfBoundsException e) {
				currentMsg = "";
			}
		}
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 122, 525, 225);
		bodyField = new JTextArea(currentMsg);
		scrollPane.setViewportView(bodyField);
		bodyField.setWrapStyleWord(true);
		bodyField.setLineWrap(true);
		bodyField.setEditable(isSent);
		contentPanel.add(scrollPane);
	}

	// Create "Send" Button
	public void addSendButton() {
		JButton sendButton = new JButton("Send");
		sendButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int colCount = RelativePosition.getContactTable()
						.getColumnCount();
				email.setFrom(fromField.getText());
				email.addRecipient(toField.getText());
				email.setSubject(subField.getText());
				email.setMessage(bodyField.getText());
				email.setSmtpHost(myData.getConfig().getSmtp());
				email.setPort(myData.getConfig().getPort());
				email.setPassword(myData.getConfig().getPassword());
				email.setTls(myData.getConfig().getTsl());
				sentEmails.addEmail(email);
				myData.serializeSentEmails();
				email.sendEmail();
				for (int i = 0; i < colCount; i++) {
					RelativePosition.getContactTable().fireTableCellUpdated(
							row, i);
				}
				dispose();
			}
		});
		sendButton.setActionCommand("Send");
		buttonPanel.add(sendButton);
		getRootPane().setDefaultButton(sendButton);
	}

	// Create "Cancel" Button
	public void addCancelButton() {
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancelButton.setActionCommand("Cancel");
		buttonPanel.add(cancelButton);
	}
	
	public void setCurrentMessage(String currentMessage) {
		this.currentMsg = currentMessage;
	}
}
