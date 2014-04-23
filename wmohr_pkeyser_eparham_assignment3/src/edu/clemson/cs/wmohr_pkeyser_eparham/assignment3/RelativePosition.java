package edu.clemson.cs.wmohr_pkeyser_eparham.assignment3;

public class RelativePosition {
	private static MainFrame mainWindow;
	private static ContactTableModel contactWindow;
	private static SentEmailsTableModel sentEmailsWindow;
	private static InboxTableModel inboxWindow;

	public static MainFrame getMainWindow() {
		return mainWindow;
	}

	public static void setMainWindow(MainFrame mainWindow) {
		RelativePosition.mainWindow = mainWindow;
	}
	
	public static ContactTableModel getContactTable() {
		return contactWindow;
	}
	
	public static void setContactTable(ContactTableModel contactModel) {
		RelativePosition.contactWindow = contactModel;
	}

	public static SentEmailsTableModel getSentEmailsWindow() {
		return sentEmailsWindow;
	}

	public static void setSentEmailsWindow(SentEmailsTableModel sentEmailsWindow) {
		RelativePosition.sentEmailsWindow = sentEmailsWindow;
	}
	
	public static InboxTableModel getInboxWindow() {
		return inboxWindow;
	}
	
	public static void setInboxWindow(InboxTableModel inboxWindow) {
		RelativePosition.inboxWindow = inboxWindow;
	}
}
