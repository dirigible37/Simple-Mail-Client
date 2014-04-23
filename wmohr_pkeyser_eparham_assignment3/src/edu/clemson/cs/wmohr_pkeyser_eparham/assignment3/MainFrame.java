package edu.clemson.cs.wmohr_pkeyser_eparham.assignment3;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;
import javax.swing.JButton;

import java.awt.FlowLayout;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private ContactTableModel ctm = new ContactTableModel();
	private SentEmailsTableModel emailTm = new SentEmailsTableModel();
	private InboxTableModel inboxTm = new InboxTableModel();
	private Email emailModel = new Email();
	private MainFrame mainWindow = RelativePosition.getMainWindow();
	private SentEmailsTableModel emailWindow = RelativePosition
			.getSentEmailsWindow();
	private InboxTableModel inboxWindow = RelativePosition.getInboxWindow();

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		RelativePosition.setContactTable(ctm);
		RelativePosition.setSentEmailsWindow(emailTm);
		RelativePosition.setInboxWindow(inboxTm);
		setTitle("Simple Mail");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		mnFile.add(mntmExit);

		JMenu mnConfiguration = new JMenu("Configuration");
		menuBar.add(mnConfiguration);

		JMenuItem mntmConfigure = new JMenuItem("Configure");
		mntmConfigure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConfigurationDlg con = new ConfigurationDlg();
				con.setVisible(true);
			}
		});
		mnConfiguration.add(mntmConfigure);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SystemInformationDlg info = new SystemInformationDlg();
				info.setVisible(true);
			}
		});
		mnHelp.add(mntmAbout);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		// Create the table
		table = new JTable(ctm);
		
		// Check for double-click
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					JTable target = (JTable) e.getSource();
					int row = target.getSelectedRow();
					EmailTransmissionDlg newEmail = new EmailTransmissionDlg(
							row, emailModel, true, "Send Email");
					newEmail.showDialog();
					newEmail.setVisible(true);
				}
			}
		});

		contentPane.add(new JScrollPane(table));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnNewButton = new JButton("Add Contact");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Contact a = new Contact();
				ctm.addContact(a);
				int contactPos = DataStore.getInstance().getRowCount() - 1;
				// Try to clear selection to start a new contact
				ContactEditingDlg add = new ContactEditingDlg(contactPos, ctm,
						true);
				add.setVisible(true);
			}
		});
		panel.add(btnNewButton);

		JButton btnDeleteContacts = new JButton("Delete Contact(s)");
		btnDeleteContacts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() != -1) {
					DataStore.getInstance().DeleteContact(
							table.getSelectedRow());
					ctm.removeContact(table.getSelectedRow());
				}
				else {
					ErrorDlg error = new ErrorDlg("No Contact Selected");
					error.setVisible(true);
				}
			}
		});
		panel.add(btnDeleteContacts);

		JButton btnEditContact = new JButton("Edit Contact");
		btnEditContact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean editCond = true;
				ContactEditingDlg edit = new ContactEditingDlg(table
						.getSelectedRow(), ctm, false);
				try {
					ctm.getContact(table.getSelectedRow());
				} catch (ArrayIndexOutOfBoundsException e1) {
					ErrorDlg error = new ErrorDlg("No Contact Selected");
					error.setVisible(true);
					editCond = false;
				}
				edit.setVisible(editCond);
			}
		});
		panel.add(btnEditContact);

		JButton btnSent = new JButton("Sent Emails");
		btnSent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SentDlg sent = SentDlg.getInstance();
				sent.setVisible(true);
			}
		});
		panel.add(btnSent);

		JButton btnInbox = new JButton("Inbox");
		btnInbox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InboxDlg inbx = InboxDlg.getInstance();
				inbx.setVisible(true);
			}
		});
		panel.add(btnInbox);
	} // end constructor

	public JTable getTable() {
		return table;
	}
}
