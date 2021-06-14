package BankingApplication;

import java.util.Date;
import java.util.regex.Pattern;

public class Account implements AccountInterface {

	protected String userName;
	protected int userSsn;
	protected String userAddress;
	protected String bankName;
	protected String bankAddress;
	protected int routingNumber;
	protected int accountNumber;
	protected double balance;
	protected Date dateCreated;
	
	protected static final double INTEREST_RATE = 0.0039; // 0.39%
	protected static final double TAX_RATE = 0.061; // 6.1%
	
	public Account() {
		super();
		this.routingNumber = (int) (Math.random() * 999999998 + 1);
		this.accountNumber = (int) (Math.random() * 999999998 + 1);
		this.balance = 0;
		this.dateCreated = new Date();
	}
	
	public Account(String userName, int userSsn, String userAddress, String bankName, String bankAddress) {
		super();
		this.userName = userName;
		this.userSsn = userSsn;
		this.userAddress = userAddress;
		this.bankName = bankName;
		this.bankAddress = bankAddress;
		this.routingNumber = (int) (Math.random() * 999999998 + 1);
		this.accountNumber = (int) (Math.random() * 999999998 + 1);
		this.balance = 0;
		this.dateCreated = new Date();
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getUserSsn() {
		return userSsn;
	}

	public void setUserSsn(int userSsn) {
		this.userSsn = userSsn;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankAddress() {
		return bankAddress;
	}

	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}

	public int getRoutingNumber() {
		return routingNumber;
	}

	public void setRoutingNumber(int routingNumber) {
		this.routingNumber = routingNumber;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public static double getInterestRate() {
		return INTEREST_RATE;
	}

	public static double getTaxRate() {
		return TAX_RATE;
	}

	@Override
	public void display() {
		System.out.println("Account number: #" + this.accountNumber);
		System.out.printf("Account balance: $%.2f\n", this.balance);
		
		double interest = calculateInterest();
		double amountDue = calculateAmountDue();

		if (interest != 0) {
			System.out.printf("Accrued interest: $%.2f\n",  interest);
		}
		
		if (amountDue != 0) {
			System.out.printf("Due payments: $%.2f\n", amountDue);
		}
		
		System.out.println("Press enter to continue.");
		Input.getInput();
	}

	@Override
	public void deposit() {
		System.out.print("Enter deposit amount: ");
		double amount = Double.valueOf(Input.getInput("[0-9.]+"));
		System.out.println("Is this deposit part of your income? (y/n)");
		String input = Input.getInput("[yYnN]");
		
		if (Pattern.matches("[yY]", input)) {
			double tax = calculateTax(amount);
			System.out.printf("Taxed amount: $%.2f\n",  tax);
			amount -= tax;
		}
		
		this.balance += amount;
		System.out.printf("Deposited $%.2f\n", amount);
		System.out.println("Press enter to continue.");
		Input.getInput();
	}

	@Override
	public void withdraw() {
		System.out.print("Enter withdrawal amount: ");
		double amount = Double.valueOf(Input.getInput("[0-9.]+"));
		
		if (this.balance > amount) {
			this.balance -= amount;
			System.out.printf("Successfully withdrew $%.2f\n", amount);
		}
		else {
			System.out.println("Withdrawal rejected: amount can't be greater than account balance.");
		}
		
		System.out.println("Press enter to continue.");
		Input.getInput();
	}
	
	@Override
	public void initialize() {
		System.out.println("\n------- Create Account -------\n");
		System.out.print("Enter user name: ");
		this.userName = Input.getInput("[a-zA-Z\s]+");
		System.out.print("Enter user SSN: ");
		this.userSsn = Integer.parseInt(Input.getInput("[0-9]+"));
		System.out.print("Enter user address: ");
		this.userAddress = Input.getInput("[a-zA-Z0-9\s]+");
		System.out.print("Enter bank name: ");
		this.bankName = Input.getInput("[a-zA-Z0-9\s]+");
		System.out.print("Enter bank address: ");
		this.bankAddress = Input.getInput("[a-zA-Z0-9\s]+");
	}
	
	@Override
	public double calculateTax(double deposit) {
		return Account.TAX_RATE * deposit;
	}
	
	@Override
	public double calculateInterest() {
		return 0;
	};
	
	@Override
	public double calculateAmountDue() {
		return 0;
	};
	
}
