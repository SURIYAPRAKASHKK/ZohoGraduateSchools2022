package com.zoho.RailwayTicketBooking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.InputMismatchException;

public class AdminModule{
	
	static Scanner scan = new Scanner(System.in);
	
	static HashMap<Integer, Passenger> conformedTicket = new HashMap<>();
	static Queue<Passenger> RACList = new LinkedList<>();
	static Queue<Passenger> waitingList = new LinkedList<>();
	
	
	public static ArrayList<Integer> seatFixing(int numberOfTickets){
		
		
		
		ArrayList<Integer> berth = new ArrayList<>();
		
		int availableWaitingList;
		int availableRAC;
		int availableLowerBerth;
		int availableMiddleBerth;
		int availableUpperBerth;
		int avaibableSideUpperBerth;
		availableRAC = ((15*numberOfTickets)/100);   //15% from total Seats
		//System.out.println(availableRAC);
		
		berth.add(availableRAC);
		
		availableWaitingList = ((10*numberOfTickets)/100); //10% from total Seats
		
		//System.out.println(availableWaitingList);
		
		berth.add(availableWaitingList);
		
		int availableConfirmedSeats = numberOfTickets-(availableRAC+availableWaitingList);
		
		int dividedSeats = availableConfirmedSeats%4;
		int seats = availableConfirmedSeats/4;
		
		if(dividedSeats == 0) {
			availableLowerBerth = seats;
			availableMiddleBerth = seats;
			availableUpperBerth = seats;
			avaibableSideUpperBerth = seats;
			
			berth.add(availableLowerBerth);
			berth.add(availableMiddleBerth);
			berth.add(availableUpperBerth);
			berth.add(avaibableSideUpperBerth);
			
		}
		else if(dividedSeats == 1) {
			availableLowerBerth = seats+1;
			availableMiddleBerth = seats;
			availableUpperBerth = seats;
			avaibableSideUpperBerth = seats;
			
			berth.add(availableLowerBerth);
			berth.add(availableMiddleBerth);
			berth.add(availableUpperBerth);
			berth.add(avaibableSideUpperBerth);
		}
		else if(dividedSeats == 2) {
			availableLowerBerth = seats+1;
			availableMiddleBerth = seats+1;
			availableUpperBerth = seats;
			avaibableSideUpperBerth = seats;
			
			berth.add(availableLowerBerth);
			berth.add(availableMiddleBerth);
			berth.add(availableUpperBerth);
			berth.add(avaibableSideUpperBerth);
		}
		else if(dividedSeats == 3) {
			availableLowerBerth = seats+1;
			availableMiddleBerth = seats+1;
			availableUpperBerth = seats+1;
			avaibableSideUpperBerth = seats;
			
			berth.add(availableLowerBerth);
			berth.add(availableMiddleBerth);
			berth.add(availableUpperBerth);
			berth.add(avaibableSideUpperBerth);
		}
		MainPage.isSeatAlloted = true;
		//System.out.println(berth);
		return berth;
		
	}
	
	/*
	 * private boolean handleInputMismatch(String num) { try { int numeric =
	 * Integer.parseInt(num); return false; } catch(InputMismatchException e) {
	 * System.err.println("Please Enter Numerics Only..."); return true; } }
	 */
	
	public void printBookedTickets() {
		if(conformedTicket.size() > 0) {
			for(int ticketNo : conformedTicket.keySet()) {
				Passenger p = conformedTicket.get(ticketNo);
				System.out.println("Ticket Number    : "+ticketNo);
				System.out.println("Passenger Name   : "+p.getName());
				System.out.println("Passenger Age    : "+p.getAge());
				System.out.println("Passenger Gender : "+p.getGender());
				System.out.println("Passenger Berth  : "+p.getAllottedberth());
				System.out.println("Journey Date     : "+p.getDate());
				System.out.println("------------------------");
			}

		}

		else if(RACList.size() > 0) {
			for(Passenger p : RACList) {
				System.out.println("Ticket Number    : "+p.getTicketNo());
				System.out.println("Passenger Name   : "+p.getName());
				System.out.println("Passenger Age    : "+p.getAge());
				System.out.println("Passenger Gender : "+p.getGender());
				System.out.println("Passenger Berth  : "+p.getAllottedberth());
				System.out.println("Journey Date     : "+p.getDate());
				System.out.println("------------------------");
			}
		}

		else if(waitingList.size() > 0) {
			for(Passenger p : waitingList) {
				System.out.println("Ticket Number    : "+p.getTicketNo());
				System.out.println("Passenger Name   : "+p.getName());
				System.out.println("Passenger Age    : "+p.getAge());
				System.out.println("Passenger Gender : "+p.getGender());
				System.out.println("Passenger Berth  : "+p.getAllottedberth());
				System.out.println("Journey Date     : "+p.getDate());
				System.out.println("------------------------");
			}
		}
		else
			System.out.println("No Tickets yet Booked");
	}
	

}
