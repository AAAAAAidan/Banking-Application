package BankingApplication;

public class SavingsAccount extends Account {

	public SavingsAccount() {
		super();
	}

	public SavingsAccount(String userName, int userSsn, String userAddress, String bankName, String bankAddress) {
		super(userName, userSsn, userAddress, bankName, bankAddress);
	}

	@Override
	public double calculateInterest() {
		return Account.INTEREST_RATE * this.balance;
	}
	
}
