package com.zoho.RailwayTicketBooking;

//import java.util.ArrayList;
//import java.util.Scanner;
import java.util.*;


public class MainPage {

	static Scanner sc = new Scanner(System.in);

	static boolean isSeatAlloted;
	static ArrayList<Integer> seats;

	public static void main(String[] args) {

		System.out.println("Railway Ticket Booking System");
		System.out.println("#############################");
		boolean repeat = true;
		boolean repeat1 = true;
		boolean repeat2 = true;
		isSeatAlloted = false;
		int options = 0;


		while(repeat) {

			System.out.println();
			System.out.println("1. User");
			System.out.println("2. Admin");
			System.out.println("3. Exit");
			repeat1 = true;
			while(repeat1) {
				System.out.print("Enter the Choice: ");

				try {
					options = Integer.parseInt(sc.next());
					repeat1 = false;
				}
				catch(Exception e){
					System.err.println("Please Enter Valid Number Only");
				}
			}

			switch(options) {
			case 1:{
				if(!isSeatAlloted) {
					System.out.println("Before First Booking Admin must Allocate the seats");
					options = 2;
				}
				else {
					System.out.println();
					System.out.println("User Module:");
					System.out.println("-----------");
					UserPage user = new UserPage();
					user.userModule();
					break;
				}
			}

			case 2:{
				System.out.println("Admin Module");
				System.out.println("------------");

				boolean isCredentialfine = true;
				while(isCredentialfine) {
					System.out.print("Enter the username : ");
					String uName = sc.next();
					System.out.print("Enter password : ");
					String pWord = sc.next();
					System.out.println();


					if(Admin.validate(uName, pWord)) {
						isCredentialfine = false;
						if(!isSeatAlloted) {
							//System.out.println("Before First Booking Admin must Allocate the seats");
							System.out.print("Enter total Number of Train Seats for Allocation: ");
							int totalSeats = sc.nextInt();
						//	seats = AdminModule.seatAlloting();
							seats = AdminModule.seatFixing(totalSeats);
							
							break;
						}

						else {
							
							while(repeat2) {
								System.out.println("1. Print all Booked Tickets ");
								System.out.println("2. Exit ");
								System.out.print("Select your Choice: ");
								byte option = sc.nextByte();
								repeat2 = true;

								switch(option) {
								case 1:{
									AdminModule admin = new AdminModule();
									admin.printBookedTickets();
									break;
								}
								case 2:{
									System.out.println("******Thank You Admin*******");
									repeat2 = false;
									break;
								}
								default:{
									System.out.println("Please Select Proper Option...");
									break;
								}
								}
							}
						}
					}
					else
						//System.out.println();
						System.err.println("Invalid User Name or Password");
				}
				

				break;
			}

			case 3:{
				System.out.println("-----Thank you------");
				repeat = false;
				break;
			}

			default:{

				System.out.println("Please Select Proper Option...");
				break;
			}

			}
		}

	}
}
