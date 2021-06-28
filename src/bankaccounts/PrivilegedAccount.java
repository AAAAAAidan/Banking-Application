package bankaccounts;

import myutilities.Console;
import myutilities.Input;

public class PrivilegedAccount extends Account {
	
	protected static final String MENU_TITLE = "Privileged Account";
	protected static final String[][] MENU_ITEMS = {
			{"View privileged account", "BankingApplication.PrivilegedAccount", "view"},
			{"Deposit to privileged account", "BankingApplication.PrivilegedAccount", "deposit"},
			{"Withdraw from privileged account", "BankingApplication.PrivilegedAccount", "withdraw"},
			{"New privileged account", "BankingApplication.PrivilegedAccount", "PrivilegedAccount"},
			{"Back", null, null}
		};
	
	public PrivilegedAccount() {
		super();
	}
	
	public PrivilegedAccount(String userName, int userSsn, String userAddress, String bankName, String bankAddress) {
		super(userName, userSsn, userAddress, bankName, bankAddress);
	}
	
	public static String getMenuTitle() {
		return MENU_TITLE;
	}
	
	public static String[][] getMenuItems() {
		return MENU_ITEMS;
	}
	
	@Override
	public void withdraw() {
		Console.printFormat("Current account balance: $%.2f\n", this.balance);
		Console.print("Enter withdrawal amount: ");
		double amount = Double.valueOf(Input.getInput("[0-9.]+"));
		this.balance -= amount;
		Console.printFormat("Withdrew $%.2f\n", amount);
	}
	
	@Override
	public double calculateAmountDue() {
		return this.balance < 0 ? -this.balance * Account.INTEREST_RATE : 0;
	}
	
	@Override
	public String toString() {
		String str = "";
		str += String.format("Account balance: $%.2f\n", this.balance);
		str += String.format("Due interest: $%.2f\n", calculateAmountDue());
		str += String.format("Date created: %1$td %1$tB %1$tY %1$tr\n", this.dateCreated);
		return str;
	}
	
}
