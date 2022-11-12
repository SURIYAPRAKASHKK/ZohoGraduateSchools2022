package zsgs.admin;

import java.util.ArrayList;

import zsgs.banking.NewUser;
import zsgs.banking.User;
import zsgs.database.CustomerDBManagement;

public class AdminController {

	public void fetchCustomerDetails() {


		ArrayList<User> details = new ArrayList<>();
		details = NewUser.dbm.fetchUserDetail();
		int temp = 1;
		System.out.println("All Customer Details:");
		System.out.println("---------------------");
		for(User user: details) {
			System.out.print(temp+") ");
			System.out.println("Customer Name   : "+user.getName());
			System.out.println("   Account Number  : "+user.getAccountNumber());
			System.out.println("   Geneder         : "+user.getGender());
			System.out.println("   Contact No      : "+user.getContactNumber());
			System.out.println("   DOB             : "+user.getDob());
			System.out.println("   PAN             : "+user.getPan());
			System.out.println("   UPI ID          : "+user.getUserName());
			System.out.println("   Current Balance : "+user.getBalance());
			System.out.println("-------------------------------------------");
			temp++;
		}
	}


	public void fetchCustomerDetails(short accNo) {

		User user;
		if(NewUser.dbm.accExist(accNo)) {
			user = new CustomerDBManagement().fetchUserDetail(accNo, 0);
			System.out.println();
			System.out.println("   Customer Name   : "+user.getName());
			System.out.println("   Account Number  : "+user.getAccountNumber());
			System.out.println("   Geneder         : "+user.getGender());
			System.out.println("   Contact No      : "+user.getContactNumber());
			System.out.println("   DOB             : "+user.getDob());
			System.out.println("   PAN             : "+user.getPan());
			System.out.println("   UPI ID          : "+user.getUserName());
			System.out.println("   Current Balance : "+user.getBalance());
		}
		else {
			System.out.println("Account Not Exist. ");
		}

	}


}
