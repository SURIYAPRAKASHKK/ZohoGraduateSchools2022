package com.zoho.RailwayTicketBooking;

public class Admin {

	public static boolean validate(String uName,String pWord)
	{
		final String userName="Admin";
		final String passWord="Admin@123";
		if(uName.equals(userName)&&pWord.equals(passWord))
		{
			return true;
		}
		else
		{
			return false;
			
		}

	}
}
