package zsgs.banking;

import java.util.HashSet;
import java.util.Scanner;

import zsgs.admin.AdminView;

public class MainPage {  


	boolean validation;
	static Scanner sc = new Scanner(System.in);

	static HashSet<Long> contactCheck;
	static HashSet<String> panCheck;
	static HashSet<String> upiIdCheck;
	byte choice;

	public MainPage() {

		contactCheck = new HashSet<Long>();
		panCheck = new HashSet<String>();
		upiIdCheck = new HashSet<String>();
	}

	private void mainPageViewer() {   

		System.out.println("Welcome to ZSGS Bank");
		System.out.println("********************");
		while (true) {
			try {
				System.out.println();
				System.out.println(" MAIN MENU ");
				System.out.println("1. Admin             ");
				System.out.println("2. New User          ");
				System.out.println("3. Registered User   ");
				System.out.println("4. Exit              ");
				
				validation = true;
				while(validation) {
					try {
						System.out.print("Enter your choice : ");
						choice = Byte.parseByte(sc.next());
						validation = InputValidation.validatingFourOptions(choice);
					}catch(Exception e) {
						System.err.println(e);
						sc.next();
					}
				}
				switch (choice) {
				case 1:{
					AdminView admin = new AdminView();
					admin.adminPageViewer();
					break;
				}
				case 2:  {
					NewUser user = new NewUser();
					user.creatAccount();
					break;
				}
				case 3: {
					RegisteredUser ru = new RegisteredUser();
					ru.checkUser();
					break;
				}
				case 4: {
					System.out.println("Thankyou for choosing ZSGS Bank.");
					System.exit(1);
				}
				default:{ 
					System.out.println("Enter the above given options only.");
					break;
				}
				}
			} catch (Exception e) {
				System.err.println("Enter Numeric Values Only");
				sc.next();
			}
		}
	}

	public static void main(String[] args) {

		MainPage manage = new MainPage();
		manage.mainPageViewer();
	}
}