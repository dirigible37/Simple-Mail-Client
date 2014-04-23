package edu.clemson.cs.wmohr_pkeyser_eparham.assignment3;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;

public class getEmails {
	
	private String password;
	private String username;
	private String server;
	private Store store;
	private Folder folder;
	private Properties props;
	private Session session;
	private Message[] messages;
	
	public getEmails() {
		props = System.getProperties();
		session = Session.getDefaultInstance(props, null);
		try {
			store = session.getStore("pop3");
		} catch (NoSuchProviderException e) {
			System.out.println("Failed to create message store");
		}
		try {
			store.connect(server, username, password);
		} catch (MessagingException e) {
			System.out.println("Failed to connect to server");
		}
		
		try {
			folder = store.getDefaultFolder();
		} catch (MessagingException e) {
			System.out.println("No default folder located");
		}
		
		try {
			folder = folder.getFolder("INBOX");
		} catch (MessagingException e) {
			System.out.println("No pop3 inbox located");
		}
		
		try {
			folder.open(Folder.READ_ONLY);
		} catch (MessagingException e) {
			System.out.println("Folder could not be opened");
		}
		
		try {
			messages = folder.getMessages();
		} catch (MessagingException e) {
			System.out.println("Messages could not be accessed");
		}
		
		for(int i = 0; i < messages.length; i++ ) {
			readMessage(messages[i]);
		}
	}
	
	public void setPassword(String pw) {
		password = pw;
	}
	
	public void setUsername(String user) {
		username = user;
	}
	
	public void setServer(String s) {
		server = s;
	}
	
	public void receive() {
		
	}
	
	public void readMessage(Message msg) {
		try {
			String from = ((InternetAddress)msg.getFrom()[0]).getPersonal();
			if(from == null)
				from = ((InternetAddress)msg.getFrom()[0]).getAddress();
			System.out.println("From: " + from);
			String subject = msg.getSubject();
			System.out.println("Subject: " + subject);
		} catch (MessagingException e) {
			System.out.println("Could not identify sender");
		}
		
		Part msgPart = msg;
		try {
			Object content = msgPart.getContent();
			if(content instanceof Multipart) {
				msgPart = ((Multipart)content).getBodyPart(0);
				System.out.println("[MultiPart Message]");
			}
		} catch (IOException e) {
			System.out.println("IOException");
		} catch (MessagingException e) {
			System.out.println("MessagingException");
		}
		
		
	}
}
