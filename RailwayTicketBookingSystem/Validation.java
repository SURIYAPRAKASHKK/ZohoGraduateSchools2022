package com.zoho.RailwayTicketBooking;

public class Validation {
	
	public static Boolean validatingThreeChoice(int input) {

		if(input == 1 || input == 2 || input == 3) 
			return false;
		else {
			System.out.println("Enter proper Choice");
			return true;
		}

	}
	
	public static Boolean validatingTwoChoice(byte input) {
		
		if(input == 1 || input == 2 ) 
			return false;
		else {
			System.out.println("Enter proper Choice");
			return true;
		}
	}

	public static Boolean validatingFourChoice(byte input) {
		
		if(input == 1 || input == 2 || input == 3 || input == 4 ) 
			return false;
		else {
			System.out.println("Enter proper Choice");
			return true;
		}
	}

	public static boolean validatingAge(byte age) {
		if(age > 0 && age < 100)
			return false;
		else {
			System.out.println("Enter Passenger Correct Age");
			return true;
		}
		
	}
	
	public static Boolean validatingDate(String date) {
		return date.matches("^(0?[1-9]|[12][0-9]|3[01])[/-](0?[1-9]|1[012])[/-]\\d{4}$");
	}
}
