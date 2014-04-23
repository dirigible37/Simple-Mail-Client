package edu.clemson.cs.wmohr_pkeyser_eparham.assignment3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;

public class DataStore implements Serializable {

	private final long serialVersionUID = 6703969164666078760L;
	private Configuration config;
	private ArrayList<Contact> contacts;
	private ArrayList<Email> sentEmails;
	private ArrayList<Email> inEmails;

	private static DataStore data;

	static {
		data = new DataStore();
	}

	private DataStore() {
		sentEmails = new ArrayList<Email>();
		inEmails = new ArrayList<Email>();
		contacts = new ArrayList<Contact>();
		config = new Configuration();
	}

	public void serializeContacts() {
		ObjectOutputStream out = null;
		try {
			for (int i = 0; i < contacts.size(); i++) {
				out = new ObjectOutputStream(
						new FileOutputStream(new File("data/contacts/"
								+ contacts.get(i).getEmail() + ".ser")));
				out.writeObject(contacts.get(i));
				out.close();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void serializeConfig() {
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(new FileOutputStream(new File(
					"data/config/" + "config.ser")));
			out.writeObject(config);
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void serializeSentEmails() {
		ObjectOutputStream out = null;
		try {
			for (int i = 0; i < sentEmails.size(); i++) {
				out = new ObjectOutputStream(new FileOutputStream(new File(
						"data/sent/" + sentEmails.get(i).getTo() + "-"
								+ sentEmails.get(i).getSubject() + ".ser")));
				out.writeObject(sentEmails.get(i));
				out.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// perhaps give this arguments to not repeat code
	public void Deserialize() {
		ObjectInputStream in = null;
		File contactsFolder = new File("data/contacts/");
		File[] contactsList = contactsFolder.listFiles();
		for (int i = 0; i < contactsList.length; i++) {
			try {
				in = new ObjectInputStream(new FileInputStream(contactsList[i]));
				Contact tempContact = (Contact) in.readObject();
				contacts.add(tempContact);
				in.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}

		File configFolder = new File("data/config/config.ser");
		try {
			in = new ObjectInputStream(new FileInputStream(configFolder));
			config = (Configuration) in.readObject();
			in.close();
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		} catch (ClassNotFoundException e) {
		}

		// De-serialize sent items
		File sentFolder = new File("data/sent/");
		File[] sentList = sentFolder.listFiles();
		for (int i = 0; i < sentList.length; i++) {
			try {
				in = new ObjectInputStream(new FileInputStream(sentList[i]));
				Email tempEmail = (Email) in.readObject();
				sentEmails.add(tempEmail);
				in.close();
			} catch (FileNotFoundException e) {
				System.out.println("File not Found");
			} catch (IOException e) {
				System.out.println("I/O Error");
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				System.out.println("Class Not Found");
			}
		}
	}

	// Should probably not be in DataStore?
	public void DeleteContact(int row) {
		File toBeDeleted = null;
		if (System.getProperty("os.name") == "Windows")
			toBeDeleted = new File("data\\contacts\\"
					+ contacts.get(row).getEmail() + ".ser");
		else
			toBeDeleted = new File("data/contacts/"
					+ contacts.get(row).getEmail() + ".ser");
		toBeDeleted.delete();
	}

	public void DeleteEmail(int row, ArrayList<Email> emails) {
		File toBeDeleted = null;
		if (System.getProperty("os.name") == "Windows")
			toBeDeleted = new File("data\\sent\\" + emails.get(row).getTo()
					+ "-" + emails.get(row).getSubject() + ".ser");
		else
			toBeDeleted = new File("data/sent/" + emails.get(row).getTo() + "-"
					+ emails.get(row).getSubject() + ".ser");
		toBeDeleted.delete();
	}

	public static DataStore getInstance() {
		return data;
	}

	public Configuration getConfig() {
		return config;
	}

	public ArrayList<Email> getSentEmails() {
		return sentEmails;
	}
	
	public ArrayList<Email> getInEmails() {
		return inEmails;
	}

	public void addToSentEmails(Email a) {
		sentEmails.add(a);
	}
	
	public void addToInEmails(Email a) {
		inEmails.add(a);
	}

	public void removeSentEmail(int i) {
		sentEmails.remove(i);
	}
	
	public void removeInEmail(int i) {
		inEmails.remove(i);
	}

	public int getColumnCount() {
		return 4;
	}

	public int getRowCount() {
		return contacts.size();
	}

	public Contact getContact(int index) {
		return contacts.get(index);
	}

	public void addToContacts(Contact c) {
		contacts.add(c);
	}

	public void removeContact(int i) {
		contacts.remove(i);
	}
}
