package com.zoho.RailwayTicketBooking;

import java.util.ArrayList;

public class BookTicket extends AdminModule{


	static ArrayList<Integer> berths = MainPage.seats;

	static int lowerBerthCount = berths.get(2);    //LB - 2
	static int upperBerthCount = berths.get(4);	//UB - 2
	static int middleBerthCount = berths.get(3);	//MB - 2
	static int sideUpperBerthCount = berths.get(5);	//SUB - 2
	static int sideLowerRACCount = berths.get(0);	//SLRac - 1
	static int waitingListCount = berths.get(1);   //WL - 1
	static int ticketNumber = 1000;  


	int age;
	BerthPreference berthPreference;
	Gender gender;



	public String ticketBooker(ArrayList<Passenger> l) {

		String str = "";
		BerthPreference berthAlloted;
		for(Passenger p: l) {
			age = p.getAge();

			if(age > 5) {

				berthAlloted = berthAllocator(p);
				p.setAllottedberth(berthAlloted);
				//p.setSeatNo(ticketNumber);
				conformedTicket.put(ticketNumber, p);
				str += p.getName()+ "\nYour Ticket No is :" + ticketNumber +"\nBerth Allowance is : "+p.getAllottedberth()+" \n\n";
			}

			ticketNumber++;
		}
		return str;
	}

	public BerthPreference berthAllocator(Passenger p) {

		berthPreference = p.getBerthPreference();
		age = p.getAge();
		gender = p.getGender();

		if(age > 60 && lowerBerthCount > 0) {
			p.setAllottedberth(BerthPreference.LOWER);
			lowerBerthCount--;
		}

		else if(gender == Gender.FEMALE && p.HasChild() && lowerBerthCount > 0) {
			p.setBerthPreference(BerthPreference.LOWER);
			lowerBerthCount--;
		}

		else if((berthPreference == BerthPreference.LOWER && lowerBerthCount > 0) || (berthPreference == BerthPreference.MIDDLE && middleBerthCount > 0) || (berthPreference == BerthPreference.UPPER && upperBerthCount > 0) || (berthPreference == BerthPreference.SIDE_UPPER && sideUpperBerthCount > 0)) {

			if(berthPreference == BerthPreference.LOWER) {
				p.setAllottedberth(berthPreference);
				//System.out.println("Lower Berth Given");
				lowerBerthCount--;
			}

			else if(berthPreference == BerthPreference.MIDDLE) {
				p.setAllottedberth(berthPreference);
				//System.out.println("Middle Berth Given");
				middleBerthCount--;
			}

			else if(berthPreference == BerthPreference.UPPER) {
				p.setAllottedberth(berthPreference);
				//System.out.println("Upper Berth Given");
				upperBerthCount--;
			}

			else if(berthPreference == BerthPreference.SIDE_UPPER) {
				p.setAllottedberth(berthPreference);
				//System.out.println("Side Upper Berth Given");
				sideUpperBerthCount--;
			}


		}
		else {

			if(lowerBerthCount > 0) {
				p.setAllottedberth(BerthPreference.LOWER);
				//System.out.println("Lower Berth Given");
				lowerBerthCount--;
			}

			else if(middleBerthCount > 0) {
				p.setAllottedberth(BerthPreference.MIDDLE);
				//System.out.println("Middle Berth Given");
				middleBerthCount--;
			}

			else if(upperBerthCount > 0) {
				p.setAllottedberth(BerthPreference.UPPER);
				//System.out.println("Upper Berth Given");
				upperBerthCount--;
			}

			else if(sideUpperBerthCount > 0) {
				p.setAllottedberth(BerthPreference.SIDE_UPPER);
				//System.out.println("Side Upper Berth Given");
				sideUpperBerthCount--;
			}
   
			else if(sideLowerRACCount > 0) {
				p.setAllottedberth(BerthPreference.RAC);
				RACList.offer(p);
				//System.out.println("RAC Alloted");
				sideLowerRACCount--;
			}

			else if(waitingListCount > 0) {
				p.setAllottedberth(BerthPreference.WL);
				waitingList.offer(p);
				//System.out.println("You are in WaitingList");
				waitingListCount--;
			}

		}

		return p.getAllottedberth();

	}
	public String cancelTicket(int ticketNo) {

		String str = "";
		if(conformedTicket.containsKey(ticketNo)){
			Passenger p = conformedTicket.remove(ticketNo);
			BerthPreference berthAllocated = p.getAllottedberth();
			str = "Your Ticket Cancelled Successfully";

			if(!RACList.isEmpty()) {
				RACProcess(berthAllocated);
			}
		}
		else{
			str = "Your ticket Number is not Exits \n";
		}
		return str;
	}

	private void RACProcess(BerthPreference berth) {

		Passenger p = RACList.poll();
		int ticketNo = p.getTicketNo();
		p.setAllottedberth(berth);
		conformedTicket.put(ticketNo,p);
		if(!waitingList.isEmpty()) {
			waitingListProcess();
		}

	}
	private void waitingListProcess() {

		Passenger p = waitingList.poll();
		p.setAllottedberth(BerthPreference.RAC);
		RACList.offer(p);
	}

	public void availableTickets(){

		int berthAvailable = (lowerBerthCount+upperBerthCount+middleBerthCount+sideUpperBerthCount);
		
		//int RACAvailable = 1 - RACList.size();
		//int waiting = 1 - waitingList.size();
		System.out.println();
		String str = "Available Berths = "+berthAvailable+"\n";//+"Available RAC's = "+RACAvailable+"\n"+"Waiting List = "+waiting;
		System.out.println(str);
	}


	public String printingTicket(int ticketNo) {
		//boolean checking = false;
		String str = "";
		Passenger p = null;
		if(conformedTicket.containsKey(ticketNo)) {
			p = conformedTicket.get(ticketNo);
			str +="Ticket Number : "+ticketNo +"\n"
					+ "Name : "+p.getName() + "\n"
					+ "Age : "+p.getAge() + "\n"
					+ "Gender : "+p.getGender() + "\n"
					+ "Allocated Berth : "+p.getAllottedberth();

			return str;
		}

		else if(!conformedTicket.containsKey(ticketNo)) {
			int count = 0;
			for (Passenger obj : RACList){
				if (obj.getTicketNo() == ticketNo){
					str +="Ticket Number : "+ticketNo +"\n"
							+ "Name : "+obj.getName() + "\n"
							+ "Age : "+obj.getAge() + "\n"
							+ "Gender : "+obj.getGender() + "\n"
							+ "Allocated Berth : "+obj.getAllottedberth();
					count++;
					return str;
				}

			}
			for(Passenger obj1: waitingList) {
				if(obj1.getTicketNo() == ticketNo) {
					str +="Ticket Number : "+ticketNo +"\n"
							+ "Name : "+obj1.getName() + "\n"
							+ "Age : "+obj1.getAge() + "\n"
							+ "Gender : "+obj1.getGender() + "\n"
							+ "Allocated Berth : "+obj1.getAllottedberth();

					count++;
					return str;
				}

			}
			if(count == 0) {
				str += "Your Ticket Not Exist \n";
				return str;
			}
		}

		return str;
	}






}