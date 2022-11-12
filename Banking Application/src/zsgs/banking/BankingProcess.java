package zsgs.banking;
 
public interface BankingProcess {
	
	void getDepositAmount();

	void getWithdrawAmount();

	void getTransferMode();
	
	void getMiniStatement();

	void getUserInfo();
	
	void deleteAccount();
}