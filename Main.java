package BankingApplication;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Main {
	
	// TODO
	// Add comments
	// Add account selection
	// Add main account view and edit
	// Reduce redundancy in main
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, 
												  IllegalArgumentException, InvocationTargetException, 
												  NoSuchMethodException, SecurityException, ClassNotFoundException {
		
		String[] menuTitles = {
			"Banking Application",
			"Checking Account",
			"Savings Account",
			"Privileged Account",
			"Credit Card",
		};
		
		String[] mainMenuOptions = {
			"Checking Account",
			"Savings Account",
			"Privileged Account",
			"Credit Card",
			"Exit"
		};
					
		String[] subMenuOptions = {
			"View ACCOUNT balance",
			"Deposit to ACCOUNT",
			"Withdraw from ACCOUNT",
			"New ACCOUNT",
			"Back"
		};
					
		String[] subMenuActions = {
			null,
			"display",
			"deposit",
			"withdraw",
			"initialize"
		};
		
		Account mainAccount = new Account();
		mainAccount.initialize();
		HashMap<String, List<Account>> accountMap = new HashMap<String, List<Account>>();
		int menuIndex = 0;
		String[] options = new String[5];
		
		while (menuIndex >= 0) {
			
			if (menuIndex == 0) {
				options = mainMenuOptions;
			}
			else {
				options = subMenuOptions;
			}
			
			System.out.println("\n------- " + menuTitles[menuIndex] + " ------- \n");
			System.out.println("Select from the following:");
			String[] validInputs = new String[options.length];
			String title = menuTitles[menuIndex];
			int i = 0;
			
			while (i < options.length) {
				String option = options[i].replaceAll("ACCOUNT", title.toLowerCase());
				validInputs[i++] = Integer.toString(i);
				System.out.println("\t" + i + " - " + option);
			}
			
			int inputIndex = Integer.parseInt(Input.getInput("[" + String.join("", validInputs) + "]"));
			
			if (inputIndex == options.length) {
				
				if (options[inputIndex-1] == "Exit") {
					System.out.println("\n------- Exiting Program -------\n");
					menuIndex = -1;
				}
				else {
					menuIndex = 0;
				}
				
			}
			else if (!title.equals(menuTitles[0])) {
				
				System.out.println("\n------- " + options[inputIndex-1].replaceAll("ACCOUNT", title.toLowerCase()) + " -------\n");
				String className = "BankingApplication." + title.replaceAll(" ", "");
				
				if (subMenuActions[inputIndex].equals(subMenuActions[subMenuActions.length-1])) {
					Account account = (Account) Class.forName(className).getDeclaredConstructor().newInstance();
					
					if (accountMap.get(className) == null) {
						LinkedList<Account> list = new LinkedList<Account>();
						list.add(account);
						accountMap.put(className, list);
					}
					else {
						accountMap.get(className).add(account);
					}
					
					System.out.println("New " + title.toLowerCase() + " created.");
					System.out.println("Press enter to continue.");
					Input.getInput();
				}
				else {
					if (accountMap.get(className) != null) {
						for(Account account : accountMap.get(className)) {
							System.out.println("\n------- " + account.getAccountNumber() + " -------\n");
							account.getClass().getMethod(subMenuActions[inputIndex]).invoke(account);
						}
					}
					else {
						System.out.println("No open accounts.");
						System.out.println("Press enter to continue.");
						Input.getInput();
					}
				}
			}
			else {
				menuIndex = inputIndex;
			}
		}
	}
}
