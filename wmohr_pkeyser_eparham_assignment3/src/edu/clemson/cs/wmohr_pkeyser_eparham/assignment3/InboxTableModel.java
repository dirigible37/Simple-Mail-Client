package edu.clemson.cs.wmohr_pkeyser_eparham.assignment3;

import javax.swing.table.AbstractTableModel;

public class InboxTableModel extends AbstractTableModel {

	DataStore myData = DataStore.getInstance();
	private String[] columnNames = new String[] {"From",
			"Subject"};
	
	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public int getRowCount() {
		return myData.getInEmails().size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		Object retObj = null;
		switch (col) {
		case 0:
			retObj = myData.getInEmails().get(row).getFrom();
			break;
		case 1:
			retObj = myData.getInEmails().get(row).getSubject();
		}
		return retObj;
	}
	
	public void removeEmail(int i) {
		try {
			myData.removeInEmail(i);
		} catch(ArrayIndexOutOfBoundsException e){
			ErrorDlg error = new ErrorDlg("No Email Selected");
			error.setVisible(true);
		}
		fireTableRowsDeleted(i, i);
	}

}
