package edu.clemson.cs.wmohr_pkeyser_eparham.assignment3;

import java.io.Serializable;

public class Contact implements Serializable {
	private String name;
	private String postal;
	private String phone;
	private String email;
	
	public Contact () {
		name = " ";
		postal = " ";
		email = " ";
		phone = " ";
	}
	
	public Contact (String n, String p, String e, String num) {
		name = n;
		postal = p;
		email = e;
		phone = num;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getPostal() {
		return this.postal;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public String getPhone() {
		return this.phone;
	}
	
	public void setName(String a) {
		name = a;
	}
	
	public void setPostal(String a) {
		postal = a;
	}
	
	public void setEmail(String a) {
		email = a;
	}
	
	public void setPhone(String a) {
		phone = a;
	}
}
