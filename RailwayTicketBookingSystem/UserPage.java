package com.zoho.RailwayTicketBooking;

import java.util.ArrayList;
import java.util.Scanner;
//import java.util.Date;

public class UserPage {

	Scanner sc = new Scanner(System.in);


	public void userModule() {
		BookTicket obj = new BookTicket();
		boolean repeat = true;
		boolean validation = false;
		int cancelCount = 0;
		int ticketCount = 0;
		byte age = 0;

		while(repeat) {
			System.out.println();
			System.out.println("1. Book");
			System.out.println("2. Cancel");
			System.out.println("3. Print your Ticket Details");
			System.out.println("4. Print available tickets");
			System.out.println("5. Exit");
			System.out.println();
			System.out.print("Enter your Choice: ");
			int choice = sc.nextInt();


			switch(choice) {
			case 1:{

				//	int availableTickets = obj.lowerBerthCount + obj.middleBerthCount + obj.upperBerthCount + obj.sideUpperBerthCount +obj.sideLowerRACCount + obj.waitingListCount;
				int availableTickets = BookTicket.lowerBerthCount +  BookTicket.middleBerthCount + BookTicket.upperBerthCount +  BookTicket.sideUpperBerthCount +  BookTicket.sideLowerRACCount +  BookTicket.waitingListCount;
				repeat = true;
				while(repeat) {
					System.out.print("please Enter how many ticket you want to book: ");

					try {
						ticketCount = Integer.parseInt(sc.next());
						repeat = false;
					}
					catch(Exception e){
						System.err.println("Please Enter Valid Number Only");
					}
				}
				
				repeat = true;
				String journeyDate = null;
				while(repeat) {
					try {
						System.out.print("Enter your Date Of Journey in order (dd-mm-yyyy) : ");
						String doj = sc.next();
						repeat = (Validation.validatingDate(doj)) ? false : true;
						if (repeat) {
							System.out.println("Enter a valid date.");
						} else {
							journeyDate = doj;
							//System.out.println(date);
						}
					} catch (Exception e) {
						System.err.println("Enter the correct format.");
						sc.next();
					}
					
				}
				
				repeat = true;

				ArrayList<Passenger> list = new ArrayList<>();
				int temp = 1;

				if(availableTickets == 0) {
					System.out.println("No Tickets Available");
				}

				else if(ticketCount <= availableTickets) {

					for(int i= 0; i < ticketCount; i++) {

						if(availableTickets > 0) {	

							//System.out.println("Booking Processing");
							Passenger passenger = new Passenger();
							
							passenger.setDate(journeyDate);
							
							System.out.println("Enter Passenger " + temp + " name:(Please Give your Name without space) ");
							String name = sc.next();
							passenger.setName(name);

							validation = true;

							while(validation) {
								try {
									System.out.println("Enter Passenger "+temp+" Age: ");
									age = Byte.parseByte(sc.next());
									validation = Validation.validatingAge(age);
									passenger.setAge(age);
									if(validation)
										validation = true;
									else if(!validation)
										validation = false;
								}
								catch(Exception e) {
									System.out.println("Please Enter only Integer value in age column");
									validation = true;
								}
							}

							byte genderChoice = 0;
							//	byte hasChild = 0;
							validation = true;

							while(validation) {

								System.out.println("Enter Passenger "+temp+" Gender: \n1.MALE  \n2.FEMALE  \n3.TRANSGENDER");
								genderChoice = sc.nextByte();
								validation = Validation.validatingThreeChoice(genderChoice);

							}

							if(genderChoice == 1)
								passenger.setGender(Gender.MALE);

							else if(genderChoice == 2) 
								passenger.setGender(Gender.FEMALE);

							else
								passenger.setGender(Gender.TRANSGENDER);

							byte berthPreferenceChoice = 0;
							validation = true;

							while(validation) {
								System.out.println("Enter Passenger "+temp+" Berth Preference: \n1.LOWER \n2.MIDDLE  \n3.UPPER \n4.SIDE UPPER");
								berthPreferenceChoice = sc.nextByte();
								validation = Validation.validatingFourChoice(berthPreferenceChoice);
							}

							if(berthPreferenceChoice == 1)
								passenger.setBerthPreference(BerthPreference.LOWER);

							else if(berthPreferenceChoice == 2)
								passenger.setBerthPreference(BerthPreference.MIDDLE);

							else if(berthPreferenceChoice == 3)
								passenger.setBerthPreference(BerthPreference.UPPER);

							else
								passenger.setBerthPreference(BerthPreference.SIDE_UPPER);

							
							
							
							list.add(passenger);

							temp++;
						}
					}
					System.out.println("Your Journey Date : "+journeyDate);
					System.out.println(obj.ticketBooker(list));
				}
				else if(ticketCount > availableTickets) {
					System.out.println("Only "+(availableTickets+cancelCount)+ " Tickets are avalible. Please Book within Range.");
				}

				break;
			}
			case 2:{
				System.out.println("Canceling Processing");
				System.out.println("Enter your Ticket Number");
				String ticketNum = sc.next();
				System.out.println(obj.cancelTicket(Integer.parseInt(ticketNum)));
				cancelCount++;

				break;
			}

			case 3:{
				System.out.println("Printing your ticket Details Processing");
				System.out.println("Enter your Ticket Number");
				int ticketNum = sc.nextInt();
				System.out.println(obj.printingTicket(ticketNum));
				break;
			}

			case 4:{
				System.out.println("Printing available tickets Processing");
				obj.availableTickets();
				break;
			}
			case 5:{
				System.out.println("Thank You User");
				repeat = false;
				break;
			}

			default:{
				System.err.println("Please Select Proper Option...");
			}


			}

		}

	}

}
