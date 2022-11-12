package zsgs.banking;

import java.util.InputMismatchException;
import java.util.Scanner;

public class RegisteredUser {

	public void checkUser() {

		User customer = null;
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		boolean validation = true;
		short accNo = 0;
		while (validation) {
			try {
				System.out.print("Enter your Account Number : ");
				accNo = sc.nextShort();
				validation = (InputValidation.validatingAccNo(accNo)) ? false : true;
			} catch (Exception e) {
				System.err.println("Account Number is not in correct Format.");
				sc.next();
			}
		}
		System.out.print("Enter your Password       : ");
		String password = sc.next();
		if (NewUser.dbm.accCheck(accNo, password)) {   
			customer = NewUser.dbm.fetchUserDetail(accNo, 0);
			validation = true;
			while (validation) {
				Transactions transfer = new Transactions(customer);
				System.out.println();
				System.out.println(" USER MENU ");
				System.out.println("1. Deposit              ");
				System.out.println("2. Withdraw.            ");
				System.out.println("3. Account Transfer     ");
				System.out.println("4. User Information     ");
				System.out.println("5. Mini Statement       ");
				System.out.println("6. Delete Account       ");
				System.out.println("7. Log out              ");
				
				byte choice = 0;
				try {
					System.out.println("Enter your choice : ");
					choice = sc.nextByte();
				} catch (InputMismatchException e) {
					System.out.println("Enter the 'Numeric' value.");
					sc.next();
				}
				switch (choice) {
				case 1: {
					transfer.getDepositAmount();  //We can deposit 1 to 50,000 only
					break;
				}
				case 2: {
					transfer.getWithdrawAmount();
					break;
				}
				case 3: {
					transfer.getTransferMode();
					break;
				}
				case 4: {
					transfer.getUserInfo();
					break;
				}
				case 5: {
					transfer.getMiniStatement();
					break;
				}
				case 6: {
					transfer.deleteAccount();
					break;
				}
				case 7: {
					System.out.println("Successfully Logged out...!");
					validation = false;
					break;
				}
				default: { 
					System.out.println("Enter the given options only.");
					break;
				}
				}
				}
			}else {
				System.out.println("Invalid AccNo or Password.");
		}
	}
}