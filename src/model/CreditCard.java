package model;

import util.Console;
import util.Input;

public class CreditCard extends Account {
	
	protected double creditLimit = 777;
	
	protected static final String MENU_TITLE = "Credit Card";
	protected static final String[][] MENU_ITEMS = {
			{"View credit card", DIRECTORY + "CreditCard", "view"},
			{"Pay off due balance", DIRECTORY + "CreditCard", "deposit"},
			{"Spend with credit card", DIRECTORY + "CreditCard", "withdraw"},
			{"Change credit limit", DIRECTORY + "CreditCard", "edit"},
			{"New credit card", DIRECTORY + "CreditCard", "CreditCard"},
			{"Back", null, null}
		};
	
	public CreditCard() {
		super();
		this.balance = creditLimit;
	}
	
	public CreditCard(String userName, int userSsn, String userAddress, String bankName, String bankAddress) {
		super(userName, userSsn, userAddress, bankName, bankAddress);
		this.balance = creditLimit;
	}
	
	public static String getMenuTitle() {
		return MENU_TITLE;
	}
	
	public static String[][] getMenuItems() {
		return MENU_ITEMS;
	}
	
	@Override
	public void deposit() {
		Console.printFormat("Due amount: $%.2f\n", calculateAmountDue());
		Console.print("Enter amount to pay: ");
		double amount = Double.valueOf(Input.getInput("[0-9.]+"));
		
		// If the deposit would go past the credit limit
		if (amount > this.calculateAmountDue()) {
			amount = this.calculateAmountDue();
		}
		
		this.balance += amount;
		Console.printFormat("Payed $%.2f\n", amount);
		
		if (this.balance == this.creditLimit) {
			Console.printLine("You have reached your credit limit");
		}
	}
	
	@Override
	public void withdraw() {
		Console.printFormat("Current account balance: $%.2f\n", this.balance);
		Console.print("Enter amount to spend: ");
		double amount = Double.valueOf(Input.getInput("[0-9.]+"));
		
		if (this.balance >= amount) {
			this.balance -= amount;
			Console.printFormat("Spent $%.2f\n", amount);
		}
		else {
			Console.printLine("Payment rejected: not enough available credit");
		}
	}
	
	@Override
	public void edit() {
		Console.print("Enter new credit limit: ");
		double creditLimit = Double.parseDouble(Input.getInput("[0-9.]+"));
		this.balance -= this.creditLimit - creditLimit;
		this.creditLimit = creditLimit;
		Console.printFormat("Credit limit updated to %s\n", this.creditLimit);
	}
	
	@Override
	public double calculateAmountDue() {
		return this.creditLimit - this.balance;
	}
	
	@Override
	public String toString() {
		String str = "";
		str += String.format("Account balance: $%.2f\n", this.balance);
		str += String.format("Credit limit: $%.2f\n", this.creditLimit);
		str += String.format("Due amount: $%.2f\n", calculateAmountDue());
		str += String.format("Date created: %1$td %1$tB %1$tY %1$tr\n", this.dateCreated);
		return str;
	}
	
}
