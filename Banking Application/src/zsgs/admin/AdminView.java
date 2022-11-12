package zsgs.admin;

import java.util.InputMismatchException;
import java.util.Scanner;

import zsgs.banking.InputValidation;
import zsgs.banking.NewUser;

public class AdminView {

	Scanner sc = new Scanner(System.in);
	boolean validation = true;



	public void adminPageViewer(){

		System.out.println();
		System.out.println("    ADMIN LOGIN");
		while(validation) {
			System.out.print("Enter Admin User Name: ");
			String userName = sc.next();
			System.out.print("Enter Admin Password: ");
			String password = sc.next();
			validation = false;
			if(Admin.validate(userName,password)) {
				validation = true;
				while(validation) {
					AdminController admin = new AdminController();
					System.out.println();
					System.out.println("           ADMIN MENU ");
					System.out.println("1. Print All Customer Details");
					System.out.println("2. Print Specific Customer Details");
					System.out.println("3. Log out              ");

					byte choice = 0;
					try {
						System.out.print("Enter your choice : ");
						choice = sc.nextByte();
					} catch (InputMismatchException e) {
						System.out.println("Enter the 'Numeric' value.");
						sc.next();
					}
					switch (choice) {
					case 1: {

						admin.fetchCustomerDetails();
						break;
					}
					case 2: {
						short accNo = 0;
						while (validation) {
							try {
								System.out.print("Enter your Account: ");
								accNo = sc.nextShort(); 
								NewUser.dbm.fetchUserDetail(accNo, 0);
								validation = (InputValidation.validatingAccNo(accNo)) ? false : true;
								admin.fetchCustomerDetails(accNo);

							} catch (Exception e) {
								System.err.println("Account Number is not in correct Format.");
								sc.next();
							}
						}


						break;
					}
					case 3: {
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
			}

			else {
				System.err.println("Incorrect UserName or Password...");
			}
		}

	}
}
