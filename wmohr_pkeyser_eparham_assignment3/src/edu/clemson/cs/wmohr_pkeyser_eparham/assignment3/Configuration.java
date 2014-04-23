package edu.clemson.cs.wmohr_pkeyser_eparham.assignment3;

import java.io.Serializable;
import java.util.Properties;

public class Configuration implements Serializable {
	private static final long serialVersionUID = 7225744747121052547L;
	
	private String email;
	private String smtp;
	private String password;
	private String port;
	private boolean tsl;

	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String a) {
		email = a;
	}

	public String getSmtp() {
		return smtp;
	}

	public void setSmtp(String smtp) {
		this.smtp = smtp;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public boolean getTsl() {
		boolean retBool = true;
		if(!tsl)
			retBool = false;
		
		return retBool;
	}

	public void setTsl(boolean tsl) {
		System.out.println("woot, it's: " + tsl);
		this.tsl = tsl;
	}
}
