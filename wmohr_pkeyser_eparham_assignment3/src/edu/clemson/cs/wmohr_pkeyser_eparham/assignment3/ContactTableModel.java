package edu.clemson.cs.wmohr_pkeyser_eparham.assignment3;

import javax.swing.table.AbstractTableModel;

public class ContactTableModel extends AbstractTableModel {

	private DataStore data = DataStore.getInstance();
	private String[] columnNames = new String[] { "Full Name", "Home Address",
			"Email", "Phone Number" };
	private ErrorDlg emailErr;

	public int getColumnCount() {
		return 4;
	}

	public int getRowCount() {
		return data.getRowCount();
	}

	public String getColumnName(int columnIndex) {
		return columnNames[columnIndex];
	}

	public Object getValueAt(int r, int c) {
		Object retObj = null;
		switch (c) {
		case 0:
			retObj = data.getContact(r).getName();
			break;
		case 1:
			retObj = data.getContact(r).getPostal();
			break;
		case 2:
			retObj = data.getContact(r).getEmail();
			break;
		case 3:
			retObj = data.getContact(r).getPhone();
			break;
		default:
			break;
		}
		return retObj;
	}

	public void addContact(Contact p) {
		data.addToContacts(p);
		fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
	}

	public void removeContact(int i) {
		try {
			data.removeContact(i);
		} catch (ArrayIndexOutOfBoundsException e) {
			ErrorDlg error = new ErrorDlg("No Contact Selected");
			error.setVisible(true);
		}
		fireTableRowsDeleted(i, i);
	}

	public Contact getContact(int index) {
		return data.getContact(index);
	}

	public boolean isCellEditable(int row, int column) {
		return false;
	}

	public void setName(String name, int row) {
		data.getContact(row).setName(name);
	}

	public void setPostal(String postal, int row) {
		data.getContact(row).setPostal(postal);
	}

	public boolean setEmail(String email, int row) {
		boolean retVal = false;
		if(FieldValidator.getInstance().validateEmail(email)) {
			data.getContact(row).setEmail(email);
			retVal = true;
		}
		else {
			emailErr = new ErrorDlg("Invalid email address.");
			emailErr.setVisible(true);
		}
		return retVal;
	}

	public void setPhone(String phone, int row) {
		data.getContact(row).setPhone(phone);
	}
}
