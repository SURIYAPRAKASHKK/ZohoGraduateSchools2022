package zsgs.banking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;

import zsgs.database.CustomerDBManagement;
import zsgs.database.PassbookDBManagement;

public class NewUser extends InputValidation {

	static InputStreamReader isr = new InputStreamReader(System.in);
	static BufferedReader br = new BufferedReader(isr);
	static Scanner sc = new Scanner(System.in);
	public static CustomerDBManagement dbm = new CustomerDBManagement();
	public static PassbookDBManagement pdm = new PassbookDBManagement();

	boolean validation = true;
	User user;

	private enum Genders{
		FEMALE, MALE, OTHERS;
	}

	private String getName() {

		String name = null;
		while (validation) {
			try {
				System.out.print("Enter your Name : ");
				name = br.readLine();
			} catch (Exception e) {
				System.err.println("Enter a valid Name.");
			}
			validation = (validatingName(name)) ? false : true;
			if (validation) {
				System.out.println("Give a valid Name.");
			}
		}
		validation = true;
		return name;
	}

	private Genders getGender() {

		Genders gender = null;
		char gen = 0;
		while(validation) {
			try {
				System.out.print("Enter your Gender [F or M or O] : ");  
				gen = sc.next().toUpperCase().charAt(0);    //lower also accepted we just use toUpperCase()
				validation = (validatingGender(gen)) ? false : true;
				if(! validation) {
					switch(gen) {
					case 'F': {
						gender = Genders.FEMALE;
						break;
					}
					case 'M': {
						gender = Genders.MALE;
						break;
					}
					case 'O': {
						gender = Genders.OTHERS;
						break;
					}
					}
				} else {
					System.out.println("Enter a valid gender.");
				}
			} catch (Exception e) {
				System.err.println("Give a valid gender.");
				sc.next();
			}
		}
		validation = true;
		return gender;
	}


	private long getContactNumber() {

		long contact = 0;
		while (validation) {
			try {
				System.out.print("Enter your Contact Number : ");
				contact = sc.nextLong();
				validation = (validatingNumber(contact)) ? false : true;  //The Mobile number must be start with 6789 only.
				if (!MainPage.contactCheck.add(contact)) {
					System.out.println("This mobile number is already exists.");
					validation = true;
				}

			} catch (InputMismatchException e) {
				System.err.println("Give 'Numeric' values only.");    ///------
				sc.next();
			}
			if (validation) {
				System.out.println("Give a valid contact number.");
			}
		}
		validation = true;
		return contact;
	}

	private String getDOB() {

		String date = null;
		while (validation) {
			try {
				System.out.print("Enter your DOB in order (dd-mm-yyyy) : ");
				String dob = sc.next();
				validation = (validatingDate(dob)) ? false : true;
				if (validation) {
					System.out.println("Enter a valid date.");
				} else {
					date = dob;
				}
			} catch (Exception e) {
				System.err.println("Enter the correct format.");
				sc.next();
			}
		}
		validation = true;
		return date;
	}

	private String getPan() {

		String pan = null;
		while (validation) {
			try {
				System.out.print("Enter your PAN Number : ");
				pan = sc.next();
				validation = (validatingPan(pan)) ? false : true;       //[A-Z]{5}[0-9]{4}[A-Z]{1} format
				if (!MainPage.panCheck.add(pan)) {                 //-----
					System.out.println("Pan Number already exists.");
					validation = true;
				}
			} catch (Exception e) {
				System.err.println("Enter the valid format.");
				sc.next();
			}
			if (validation) {
				System.out.println("Give a valid PAN Number (Valid Format is ABCDE1234F)");
				
			}
		}
		validation = true;
		return pan;
	}

	private String getUserName() {

		String userName = null;
		while (validation) {
			try {
				System.out.print("Create UPI ID : ");
				userName = br.readLine();
				validation = (validatingUserName(userName)) ? false : true;
				if (!MainPage.upiIdCheck.add(userName)) {
					System.out.println("Username already exists.");
					validation = true;
				}
			} catch (Exception e) {
				System.err.println("Enter a valid Username.");
			}
			if (validation) {
				System.out.println("Give a valid Username.");
			}
		}
		validation = true;
		return userName;
	}

	private String getPassword() {

		String password = null;
		System.out.println("Password Constrains :-");
		System.out.println("Password must Contains minimum 8 Characters(1 Uppercase, 1 Lowercase, 1 Digit, 1 Special Character).");
		while (validation) {
			try {
				System.out.print("Create a Password : ");
				password = br.readLine();
			} catch (Exception e) {
				System.err.println("Enter a valid Password.");
			}
			validation = (validatingPassword(password)) ? false : true;  //----
			if (validation) {
				System.out.println("Enter a valid Password.");
			}
		}
		validation = true;
		return password;
	}

	private String getUpiPin() {

		String pin = null;
		while (validation) {
			try {
				System.out.print("Create a Upi Pin : ");
				System.out.println("Upi PIN must be any four digits only");
				pin = sc.next();
			} catch (InputMismatchException e) {
				System.err.println("Enter Four Digit Numbers only.");
				sc.next();
			}
			validation = (validatingPin(pin)) ? false : true;
			if (validation) {
				System.out.println("Enter the valid pin.");
			}
		}
		validation = true;
		return pin;
	}

	private float getInitialAmount() {

		float amount = 0;
		while (validation) {
			try {
				System.out.print("Enter Initial Deposit : ");
				amount = sc.nextFloat();
				validation = (validatingInitialAmount(amount)) ? false : true;  // 500 to 2000 only accepted
			} catch (InputMismatchException e) {
				System.err.println("Enter 'numeric' vlaues only.");
				sc.next();
			}
			if (validation) {
				System.out.println("Deposit initial amount Rs.500/- to Rs.2000/-");
			}
		}
		validation = true;
		return amount;
	}

	private void accountResult(User user) {

		System.out.println("Your Account is Successfully Created : ");
		System.out.println("Account Number is                : " + user.getAccountNumber());  //Account Number starts from 1000 only
		System.out.println("Account Holder Name is           : " + user.getName());
		System.out.println("Account Holder Gender is         : " + user.getGender());
		System.out.println("Account Holder Contact Number is : " + user.getContactNumber());
		System.out.println("Account Holder PAN Number is     : " + user.getPan());
		System.out.println("Account Holder UPI_ID is         : " + user.getUserName());
		System.out.println("Current Account Balance is       : Rs." + user.getBalance() + "/-");

	}

	public void creatAccount() throws IOException {

		user = new User();

		System.out.println("For Account Creation Please Fill these details : ");

		user.setName(getName());
		user.setGender(getGender().toString());
		user.setContactNumber(getContactNumber());
		user.setDob(getDOB());
		user.setPan(getPan());
		user.setUserName(getUserName());
		user.setPassword(getPassword());
		user.setUpiPin(getUpiPin());
		user.setBalance(getInitialAmount());

		dbm.handleData(user, 1);    //adding the customer details inside customer_details table

		pdm.handlePassbook(user, user.getBalance(), 1);    // It creates the passbook table 
		pdm.handlePassbook(user, user.getBalance(), 2);		//Insert first record for initial amount credit

		accountResult(user);
	}
}