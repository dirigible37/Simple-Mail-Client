package edu.clemson.cs.wmohr_pkeyser_eparham.assignment3;

import javax.swing.table.AbstractTableModel;

public class SentEmailsTableModel extends AbstractTableModel{
	
	DataStore myData = DataStore.getInstance();
	private String[] columnNames = new String[] {"To",
			"Subject"};
	
	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public int getRowCount() {
		return myData.getSentEmails().size();
	}
	
	public String getColumnName(int columnIndex) {
		return columnNames[columnIndex];
	}

	@Override
	public Object getValueAt(int row, int col) {
		Object retObj = null;
		switch (col) {
		case 0:
			retObj = myData.getSentEmails().get(row).getTo();
			break;
		case 1:
			retObj = myData.getSentEmails().get(row).getSubject();
			break;
		}
		return retObj;
	}

	public void addEmail(Email mail) {
		myData.addToSentEmails(mail);
		fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
	}
	
	public void removeEmail(int i) {
		try {
			myData.removeSentEmail(i);
		} catch (ArrayIndexOutOfBoundsException e) {
			ErrorDlg error = new ErrorDlg("No Email Selected");
			error.setVisible(true);
		}
		fireTableRowsDeleted(i, i);
	}
	
}
