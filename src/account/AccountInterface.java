package account;

public interface AccountInterface {
	
	void view();
	void deposit();
	void withdraw();
	void edit();
	
	double calculateTax(double deposit);
	double calculateInterest();
	double calculateAmountDue();
	
}
