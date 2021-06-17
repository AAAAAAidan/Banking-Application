package BankingApplication;

public class CheckingAccount extends Account {
	
	protected static final String MENU_TITLE = "Checking Account";
	protected static final String[][] MENU_ITEMS = {
			{"View checking account", "BankingApplication.CheckingAccount", "view"},
			{"Deposit to checking account", "BankingApplication.CheckingAccount", "deposit"},
			{"Withdraw from checking account", "BankingApplication.CheckingAccount", "withdraw"},
			{"New checking account", "BankingApplication.CheckingAccount", "CheckingAccount"},
			{"Back", null, null}
		};
	
	public CheckingAccount() {
		super();
	}
	
	public CheckingAccount(String userName, int userSsn, String userAddress, String bankName, String bankAddress) {
		super(userName, userSsn, userAddress, bankName, bankAddress);
	}
	
	public static String getMenuTitle() {
		return MENU_TITLE;
	}
	
	public static String[][] getMenuItems() {
		return MENU_ITEMS;
	}
	
	@Override
	public String toString() {
		String str = "";
		str += String.format("Account balance: $%.2f\n", this.balance);
		str += String.format("Date created: %1$td %1$tB %1$tY %1$tr\n", this.dateCreated);
		return str;
	}
	
}
