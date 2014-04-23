package edu.clemson.cs.wmohr_pkeyser_eparham.assignment3;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

public class InboxDlg extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8110294489970065443L;

	private JPanel contentPane = new JPanel();
	private MainFrame mainWindow = RelativePosition.getMainWindow();
	private InboxTableModel inboxTm = RelativePosition.getInboxWindow();
	private JTable table;
	private static InboxDlg instance;
	
	static {
		instance = new InboxDlg();
	}
	
	private InboxDlg() {
		setTitle("Simple Mail -- Inbox");
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
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] {614, 0};
		gbl_contentPane.rowHeights = new int[]{378, 33, 0};
		gbl_contentPane.columnWeights = new double[]{0.0};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		table = new JTable(inboxTm);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(0, 0, 5, 0);
		gbc.gridx = 0;
		gbc.gridy = 0;
		JScrollPane scrollPane = new JScrollPane(table);
		contentPane.add(scrollPane, gbc);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.anchor = GridBagConstraints.NORTH;
		gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		contentPane.add(panel, gbc_panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnDeleteEmail = new JButton("Delete Email");
		btnDeleteEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() != -1) {
					DataStore.getInstance().DeleteEmail(table.getSelectedRow(), 
							DataStore.getInstance().getSentEmails());
					inboxTm.removeEmail(table.getSelectedRow());
				}
			}
		});
		panel.add(btnDeleteEmail);
	}
	
	public static InboxDlg getInstance() {
		return instance;
	}
}
