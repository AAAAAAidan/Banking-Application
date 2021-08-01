package model;

import java.util.Date;
import java.util.regex.Pattern;

import util.Console;
import util.Input;

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
  protected static final String MENU_TITLE = "Banking Application";
  protected static final String DIRECTORY = "model.";
  protected static final String[][] MENU_ITEMS = {
      {"Manage checking accounts", DIRECTORY + "CheckingAccount", null},
      {"Manage savings accounts", DIRECTORY + "SavingsAccount", null},
      {"Manage privileged accounts", DIRECTORY + "PrivilegedAccount", null},
      {"Manage credit cards", DIRECTORY + "CreditCard", null},
      {"View account information", DIRECTORY + "Account", "view"},
      {"Edit account information", DIRECTORY + "Account", "edit"},
      {"Exit", null, null}
    };
  
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
  
  public static String getMenuTitle() {
    return MENU_TITLE;
  }
  
  public static String getDirectory() {
    return DIRECTORY;
  }
  
  public static String[][] getMenuItems() {
    return MENU_ITEMS;
  }
  
  @Override
  public String toString() {
    String str = "";
    str += String.format("User name: %s\n", this.userName);
    str += String.format("User SSN: %s\n", this.userSsn);
    str += String.format("User address: %s\n", this.userAddress);
    str += String.format("Bank name: %s\n", this.bankName);
    str += String.format("Bank address: %s\n", this.bankAddress);
    str += String.format("Routing number: #%s\n", this.routingNumber);
    str += String.format("Account number: #%s\n", this.accountNumber);
    str += String.format("Date created: %1$tF %1$tT %1$tZ\n", this.dateCreated);
    return str;
  }
  
  @Override
  public void view() {
    Console.print(this.toString());
  }
  
  @Override
  public void deposit() {
    Console.printFormat("Current account balance: $%.2f\n", this.balance);
    Console.print("Enter deposit amount: ");
    double amount = Double.valueOf(Input.getInput("[0-9.]+"));
    Console.printLine("Is this deposit part of your income? (y/n)");
    String input = Input.getInput("[yYnN]");
    
    // If the deposit is part of financial income, then subtract tax
    if (Pattern.matches("[yY]", input)) {
      double tax = calculateTax(amount);
      Console.printFormat("Taxed amount: $%.2f\n",  tax);
      amount -= tax;
    }
    
    this.balance += amount;
    Console.printFormat("Deposited $%.2f\n", amount);
  }
  
  @Override
  public void withdraw() {
    Console.printFormat("Current account balance: $%.2f\n", this.balance);
    Console.print("Enter withdrawal amount: ");
    double amount = Double.valueOf(Input.getInput("[0-9.]+"));
    
    if (this.balance >= amount) {
      this.balance -= amount;
      Console.printFormat("Withdrew $%.2f\n", amount);
    }
    else {
      Console.printLine("Withdrawal rejected: balance is too low");
    }
  }
  
  @Override
  public void edit() {
    Console.print("Enter new user name: ");
    this.userName = Input.getInput("[a-zA-Z\s]+");
    Console.print("Enter new user SSN: ");
    this.userSsn = Integer.parseInt(Input.getInput("[0-9]+"));
    Console.print("Enter new user address: ");
    this.userAddress = Input.getInput("[a-zA-Z0-9\s]+");
    Console.print("Enter new bank name: ");
    this.bankName = Input.getInput("[a-zA-Z0-9\s]+");
    Console.print("Enter new bank address: ");
    this.bankAddress = Input.getInput("[a-zA-Z0-9\s]+");
    Console.printLine("Account updated");
  }
  
  @Override
  public double calculateTax(double deposit) {
    return Account.TAX_RATE * deposit;
  }
  
  @Override
  public double calculateInterest() {
    return 0;
  }
  
  @Override
  public double calculateAmountDue() {
    return 0;
  }
  
}
