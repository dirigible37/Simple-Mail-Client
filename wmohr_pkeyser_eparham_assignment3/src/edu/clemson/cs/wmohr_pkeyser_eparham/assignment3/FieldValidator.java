package edu.clemson.cs.wmohr_pkeyser_eparham.assignment3;

public class FieldValidator {

	private static FieldValidator instance;
	
	static {
		instance = new FieldValidator();
	}
	
	public static FieldValidator getInstance() {
		return instance;
	}
	
	public boolean validateEmail(String address) {
		boolean retBool = true;
		System.out.println("Hi there user");
		int atIndex = address.indexOf('@');
		int dotIndex = address.indexOf('.');

		//System.out.println("atIndex = dotIndex = ");
		
		if(atIndex > 0) {
			for(int i = 0; i < atIndex; i++) {
				if(!Character.isLetterOrDigit(address.charAt(i))) {
					retBool = false;
					i += atIndex; // end loop
				}
			}
			if(dotIndex != -1) {
				for(int i = atIndex + 1; i < dotIndex; i++) {
					if(!Character.isLetterOrDigit(address.charAt(i))) {
						retBool = false;
						i += dotIndex;
					}
				}
				for(int i = dotIndex + 1; i < address.length(); i++) {
					System.out.println("i = " + i + " len = " + address.length());
					if(!Character.isLetterOrDigit(address.charAt(i))) {
						retBool = false;
						i += dotIndex;
					}
				}
			} else {
				//no '.' found in email address!
				retBool = false;
			}
		} else {
			//either no @ or @ is first char of address
			retBool = false;
		}
		
		return retBool;
	}
	
}
