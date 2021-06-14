package BankingApplication;

public class CreditCard extends Account {

	public CreditCard() {
		super();
	}

	public CreditCard(String userName, int userSsn, String userAddress, String bankName, String bankAddress) {
		super(userName, userSsn, userAddress, bankName, bankAddress);
	}
	
	@Override
	public double calculateAmountDue() {
		return this.balance;
	}
	
}
