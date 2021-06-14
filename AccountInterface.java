package BankingApplication;

public interface AccountInterface {
	
	void display();
	void deposit();
	void withdraw();
	void initialize();
	
	double calculateTax(double deposit);
	double calculateInterest();
	double calculateAmountDue();
	
}
