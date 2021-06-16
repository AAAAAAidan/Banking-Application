package BankingApplication;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Main {
	
	// TODO
	// Add comments
	// Add account selection
	// Add JSON save file
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, 
												  IllegalArgumentException, InvocationTargetException, 
												  NoSuchMethodException, SecurityException, ClassNotFoundException {
		
		HashMap<String, List<Account>> accountMap = new HashMap<String, List<Account>>();
		
		Console.printHeader("Create Account");
		Account mainAccount = new Account();
		mainAccount.edit();
		Account[] newMainAccount = {mainAccount};
		accountMap.put(Account.getMenuTitle(), Arrays.asList(newMainAccount));
		
		String choice = null;
		String address = "BankingApplication.Account";
		String method = "view";
		
		while (address != null) {
			Account menuClass = (Account) Class.forName(address).getDeclaredConstructor().newInstance();
			String menuTitle = (String) menuClass.getClass().getMethod("getMenuTitle").invoke(menuClass);
			String[][] menuItems = (String[][]) menuClass.getClass().getMethod("getMenuItems").invoke(menuClass);
			String[] menuChoices = new String[menuItems.length];
			String[] menuAddresses = new String[menuItems.length];
			String[] menuMethods = new String[menuItems.length];
			
			for (int i = 0; i < menuItems.length; i++) {
				menuChoices[i] = menuItems[i][0];
				menuAddresses[i] = menuItems[i][1];
				menuMethods[i] = menuItems[i][2];
			}
			
			Console.printHeader(menuTitle);
			Console.printList(menuChoices);
			
			int inputIndex = Input.getSelectionIndex(menuChoices);
			
			choice = menuChoices[inputIndex];
			address = menuAddresses[inputIndex];
			method = menuMethods[inputIndex];
			
			if (choice == "Exit") {
				Console.printHeader("Exiting Program");
				address = null;
			}
			else if (choice == "Back") {
				address = "BankingApplication.Account";
			}
			else if (method != null) {
				Console.printHeader(choice);
				List<Account> accounts = accountMap.get(menuTitle);
				
				if (method.equals(menuTitle.replaceAll(" ", ""))) {
					Account account = (Account) Class.forName(address).getDeclaredConstructor().newInstance();
					
					if (accounts == null) {
						List<Account> accountList = new ArrayList<Account>();
						accountList.add(account);
						accountMap.put(menuTitle, accountList);
					}
					else {
						System.out.println(menuTitle);
						accountMap.get(menuTitle).add(account);
					}
					
					Console.printFormat("New %s created.\n", menuTitle.toLowerCase());
					Input.waitForEnter();
				}
				else {
					if (accounts != null) {
						for(Account account : accounts) {
							Console.printHeader("Account #" + String.valueOf(account.getAccountNumber()));
							account.getClass().getMethod(method).invoke(account);
							Input.waitForEnter();
						}
					}
					else {
						Console.printLine("No open accounts");
						Input.waitForEnter();
					}
				}
			}
		}
	}
}
