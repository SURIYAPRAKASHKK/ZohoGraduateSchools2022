package com.zoho.RailwayTicketBooking;

enum Gender{MALE,FEMALE,TRANSGENDER}
enum BerthPreference{LOWER,MIDDLE,UPPER,SIDE_UPPER,RAC,WL}
public class Passenger {
	
	private String name;
	private byte age;
	private Gender gender;
	private BerthPreference berthPreference;
	//private int seatNo;
	private BerthPreference allottedberth;
	private int ticketNo;
	private boolean hasChild;
	private String date;
	
	
	
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public byte getAge() {
		return age;
	}
	
	public void setAge(byte age) {
		this.age = age;
	}
	
	public Gender getGender() {
		return gender;
	}
	
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	public BerthPreference getBerthPreference() {
		return berthPreference;
	}
	
	public void setBerthPreference(BerthPreference berthPreference) {
		this.berthPreference = berthPreference;
	}

	/*public int getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(int seatNo) {
		this.seatNo = seatNo;
	}*/

	public BerthPreference getAllottedberth() {
		return allottedberth;
	}

	public void setAllottedberth(BerthPreference allottedberth) {
		this.allottedberth = allottedberth;
	}

	public int getTicketNo() {
		return ticketNo;
	}

	public void setTicketNo(int ticketNo) {
		this.ticketNo = ticketNo;
	}

	public boolean HasChild() {
		return hasChild;
	}

	public void setHasChild(boolean hasChild) {
		this.hasChild = hasChild;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
}
