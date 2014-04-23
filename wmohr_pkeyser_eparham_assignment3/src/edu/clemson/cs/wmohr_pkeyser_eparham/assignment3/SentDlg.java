package edu.clemson.cs.wmohr_pkeyser_eparham.assignment3;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class SentDlg extends JDialog {
	private JPanel contentPane = new JPanel();
	private MainFrame mainWindow = RelativePosition.getMainWindow();
	private SentEmailsTableModel emailTm = RelativePosition.getSentEmailsWindow();
	private JTable table;
	private static SentDlg sentDialog;
	
	static {
		sentDialog = new SentDlg();
	}
	
	public static SentDlg getInstance() {
		return sentDialog;
	}
	
	private SentDlg() {
		setTitle("Simple Mail -- Outbox");
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
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] {614, 0};
		gbl_contentPane.rowHeights = new int[]{378, 33, 0};
		gbl_contentPane.columnWeights = new double[]{0.0};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		//Create the table
		table = new JTable(emailTm);
		
		// Check for double-click
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					JTable target = (JTable) e.getSource();
					int row = target.getSelectedRow();
					SentEmailDlg newEmail = new SentEmailDlg(
							row, null, false, "Sent Mail");
					newEmail.showDialog();
					newEmail.setVisible(true);
				}
			}
		});
		
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
					emailTm.removeEmail(table.getSelectedRow());
				}
			}
		});
		panel.add(btnDeleteEmail);
	}
}
