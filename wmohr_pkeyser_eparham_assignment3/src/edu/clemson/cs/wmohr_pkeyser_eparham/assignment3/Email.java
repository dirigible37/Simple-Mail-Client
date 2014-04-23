package edu.clemson.cs.wmohr_pkeyser_eparham.assignment3;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

public class Email implements Serializable {

	private static final long serialVersionUID = 1982584735795341300L;
	private String from;
	private List<String> to = new ArrayList<String>();
	private String smtpHost;
	private String password;
	private String subject;
	private String message;
	private String port;
	private boolean tls;

	public Email() {
		this.from = "";
		this.smtpHost = "";
		this.port = "";
		this.subject = "";
		this.message = "";
		this.tls = false;
	}

	public void sendEmail() {
		try {
			Properties props = new Properties();

			//Make the true/false ones dynamic
			props.put("mail.smtp.from", from);
			props.put("mail.smtp.host", smtpHost);
			props.put("mail.smtp.port", port);
			props.put("mail.smtp.auth", true);
			props.put("mail.smtp.socketFactory.port", port);
			props.put("mail.smtp.socketFactory.class",
					"javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.socketFactory.fallback", "false");
			props.put("mail.smtp.starttls.enable", tls);
			
			Authenticator auth = new Authenticator() {
	            @Override
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(from, password);
	            }
	        };

			Session session = Session.getDefaultInstance(props, auth);

			Message msg = new MimeMessage(session);

			for (int i = 0; i < to.size(); i++) {
				msg.addRecipient(RecipientType.TO,
						new InternetAddress(to.get(i)));
				System.out.println("Sending to: " + to.get(i));
			}

			msg.setSubject(subject);
			msg.setFrom(new InternetAddress(from));

			msg.setText(message);
			Transport.send(msg);
			System.out.println("Message sent");

		} catch (Exception exc) {
			System.out.println("Exception: " + exc);
		}
	}

	public void setTls(boolean tls) {
		this.tls = tls;
	}

	public void setFrom(String from) {
		this.from = from;
	}
	
	public void addRecipient(String recipient) {
		this.to = Arrays.asList(recipient.split(","));
	}

	public void setSmtpHost(String smtp) {
		this.smtpHost = smtp;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setPort(String port) {
		this.port = port;
	}
	

	public String getTo() {
		return to.get(0);
	}

	public String getSubject() {
		return subject;
	}
	
	public String getFrom() {
		return from;
	}
	
	public String getMessage() {
		return message;
	}

}
