package BankingApplication;

public class SavingsAccount extends Account {
	
	protected static final String MENU_TITLE = "Savings Account";
	protected static final String[][] MENU_ITEMS = {
			{"View savings account", "BankingApplication.SavingsAccount", "view"},
			{"Deposit to savings account", "BankingApplication.SavingsAccount", "deposit"},
			{"Withdraw from savings account", "BankingApplication.SavingsAccount", "withdraw"},
			{"New savings account", "BankingApplication.SavingsAccount", "SavingsAccount"},
			{"Back", null, null}
		};
	
	public SavingsAccount() {
		super();
	}
	
	public SavingsAccount(String userName, int userSsn, String userAddress, String bankName, String bankAddress) {
		super(userName, userSsn, userAddress, bankName, bankAddress);
	}
	
	public static String getMenuTitle() {
		return MENU_TITLE;
	}
	
	public static String[][] getMenuItems() {
		return MENU_ITEMS;
	}
	
	@Override
	public double calculateInterest() {
		return Account.INTEREST_RATE * this.balance;
	}
	
	@Override
	public String toString() {
		String str = "";
		str += String.format("Account balance: $%.2f\n", this.balance);
		str += String.format("Accrued interest: $%.2f\n", calculateInterest());
		str += String.format("Date created: %1$td %1$tB %1$tY %1$tr\n", this.dateCreated);
		return str;
	}
	
}
