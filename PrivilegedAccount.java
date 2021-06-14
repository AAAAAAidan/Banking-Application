package BankingApplication;

public class PrivilegedAccount extends Account {

	public PrivilegedAccount() {
		super();
	}

	public PrivilegedAccount(String userName, int userSsn, String userAddress, String bankName, String bankAddress) {
		super(userName, userSsn, userAddress, bankName, bankAddress);
	}
	
	@Override
	public void withdraw() {
		System.out.print("Enter withdrawal amount: ");
		double amount = Double.valueOf(Input.getInput("[0-9.]+"));
		this.balance -= amount;
		System.out.printf("Successfully withdrew $%.2f\n", amount);
		System.out.println("Press enter to continue.");
		Input.getInput();
	}
	
	@Override
	public double calculateAmountDue() {
		return this.balance < 0 ? -this.balance : 0;
	}
	
}
