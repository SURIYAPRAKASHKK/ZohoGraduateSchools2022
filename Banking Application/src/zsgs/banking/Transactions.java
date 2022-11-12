package zsgs.banking;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import zsgs.database.MiniStatement;


public class Transactions implements BankingProcess {

	static Scanner sc = new Scanner(System.in);

	User customer;
	boolean validation = true;
	String message;
	float amount, balance;

	public Transactions(User customer) {

		this.customer = customer;
	}

	private void deposit(float amount) {

		balance = customer.getBalance();
		customer.setBalance(balance += amount);
		System.out.println("Amount Rs." + amount + "/- Credited in your Account.");
		NewUser.pdm.handlePassbook(customer, amount, 2);
	}

	private void withdraw(float amount) {

		balance = customer.getBalance();
		if (balance > amount) {
			customer.setBalance(balance -= amount);
			System.out.println("Amount Rs." + amount + "/- Debited from your Account.");
			NewUser.pdm.handlePassbook(customer, amount, 3);

		} else {
			System.out.println("You have insufficient Balance");
		}
	}

	private void transfer(float amount, User payee) {

		withdraw(amount);
		balance = payee.getBalance();
		payee.setBalance(balance += amount);
		NewUser.pdm.handlePassbook(payee, amount, 2);
	}

	public void getDepositAmount() {

		while (validation) {
			try {
				System.out.print("Enter Deposit Amount : ");
				amount = sc.nextFloat();
				validation = (InputValidation.validatingAmount(amount)) ? false : true;  //1 to 50000 only
			} catch (InputMismatchException e) {
				System.out.println("Enter Numeric values only.");
				sc.next();
			}
			if (validation) {
				System.out.println("Please Deposit Rs.1/- to  Rs.50,000/- only");
			} else {
				deposit(amount);
			}
		}
		validation = true;
	}

	public void getWithdrawAmount() {

		while (validation) {
			try {
				System.out.print("Enter Withdraw Amount : ");
				amount = sc.nextFloat();
				validation = (InputValidation.validatingAmount(amount)) ? false : true;   // 1 to 50000 only
			} catch (InputMismatchException e) {
				System.out.println("Enter Numeric values only.");
				sc.next();
			}
			if (validation) {
				System.out.println("WithDraw Limit is Rs.1/- to  Rs.50,000/- only");
			} else {
				withdraw(amount);
			}
		}
		validation = true;
	}

	private void transferAccount() {

		while (validation) {
			try {
				System.out.print("Enter payee Account Number : ");
				short accNo = sc.nextShort();
				if (NewUser.dbm.accExist(accNo) && accNo != customer.getAccountNumber()) {
					System.out.print("Enter Transfering Amount : ");
					amount = sc.nextFloat();
					if (amount > 0 && amount <= 50000) {
						User payee = NewUser.dbm.fetchUserDetail(accNo, 0);
						transfer(amount, payee);
						validation = false;
					} else {
						System.out.println("Contact your bank Manager for more than Rs. 50000/- transfer.");
					}
				} else {
					System.out.println("Enter a valid Account Number Or User Not Exist.");
				}
			} catch (Exception e) {
				System.err.println("Input is not valid.");
				sc.next();
			}
		}
		validation = true;
	}

	private void transferUPI() {

		while (validation) {
			try {
				System.out.print("Enter payee UPI ID : ");
				String userName = sc.next();
				if (NewUser.dbm.upiExist(userName) && userName != customer.getUserName()) {
					System.out.print("Enter Transfering Amount : ");
					amount = sc.nextFloat();
					if (amount > 0 && amount <= 50000) {
						User payee = NewUser.dbm.fetchUserDetail(userName);
						transfer(amount, payee);
						validation = false;
					} else {
						System.out.println("Transaction Limit is Rs.1/- to  Rs.50,000/- only");
					}

				}
			} catch (Exception e) {
				System.err.println("Input is not valid.");
				sc.next();
			}
		}
		validation = true;
	}

	public void getTransferMode() {
		while (validation) {
		System.out.println(" TRANSFER METHODS ");
		System.out.println("1. Account to Account ");
		System.out.println("2. UPI to UPI         ");
		System.out.println("3. Back               ");

		
			byte choice = 0;
			try {
				System.out.print("Enter your choice : ");
				choice = sc.nextByte();
			} catch (InputMismatchException e) {
				System.out.println("Enter 'Numeric' values only.");
				sc.next();
			}
			switch (choice) {
			case 1: { 
				transferAccount();
				break;
			}
			case 2: { 
				transferUPI();
				break;
			}
			case 3: {
				validation = false;
				break;
			}
			default: { 
				System.out.println("Enter the above given options only.");
				break;
			}
			}
		}
	}

	public void getMiniStatement() {

		ArrayList<MiniStatement> mst = NewUser.pdm.lastFiveTransaction(customer.getAccountNumber());
		System.out.println("+--------+---------------------------+------------+------------+------------+");
		System.out.printf("| %-6s | %-25s | %-10s | %-10s | %-10s |\n", "SLNO", "DATE & TIME", "DEBIT", "CREDIT",
				"BALANCE");
		System.out.println("+--------+---------------------------+------------+------------+------------+");
		int i = 1;
		for (MiniStatement m : mst) {
			System.out.printf("| %-6d | %-25s |", i++, m.getDateAndTime());
			System.out.printf(" %-10.2f |", m.getDebit());
			System.out.printf(" %-10.2f |", m.getCredit());
			System.out.printf(" %-10.2f |\n", m.getBalance());
		}
		System.out.println("+--------+---------------------------+------------+------------+------------+");
	}

	public void getUserInfo() {

		System.out.println("Customer's Account Number   : " + customer.getAccountNumber());
		System.out.println("Customer's Name             : " + customer.getName());
		System.out.println("Customer's Gender           : " + customer.getGender());
		System.out.println("Customer's UPI ID           : " + customer.getUserName());
		System.out.println("Customer's Contact Number   : " + customer.getContactNumber());
		System.out.println("Customer's Account Balance  : " + customer.getBalance());
	}

	public void deleteAccount() {

		NewUser.dbm.handleData(customer, 2);
	}
}