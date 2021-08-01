import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import model.Account;
import util.Console;
import util.Input;

public class Main {

  public static void main(String[] args) throws InstantiationException, 
      IllegalAccessException, IllegalArgumentException, InvocationTargetException,
      NoSuchMethodException, SecurityException, ClassNotFoundException {

    HashMap<String, List<Account>> accountMap = new HashMap<String, List<Account>>();

    Console.printHeader("Create Account");
    Account mainAccount = new Account();
    mainAccount.edit();
    Account[] newMainAccount = {mainAccount};
    accountMap.put(Account.getMenuTitle(), Arrays.asList(newMainAccount));

    String choice = null;
    String address = Account.getDirectory() + "Account";
    String method = "view";

    // Begin looping through the menu system
    // The menu system uses class addresses to keep track of which menu to use
    // Menus accessed from their associated classes follow the format { ... {choice, method, address} ... }
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

      int menuIndex = Input.getSelectionIndex(menuChoices);

      choice = menuChoices[menuIndex];
      address = menuAddresses[menuIndex];
      method = menuMethods[menuIndex];

      if (choice == "Exit") {
        Console.printHeader("Exiting Program");
        address = null;
      }
      else if (choice == "Back") {
        address = Account.getDirectory() + "Account";
      }
      else if (method != null) {
        Console.printHeader(choice);
        List<Account> accounts = accountMap.get(menuTitle);
        Account account = null;

        // If the method calls for a new class instance
        if (method.equals(menuTitle.replaceAll(" ", ""))) {
          account = (Account) Class.forName(address).getDeclaredConstructor().newInstance();

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
        // Else use one of the current class instances
        else {
          if (accounts != null) {
            String accountMenuChoice = null;
            int accountMenuIndex = 0;

            // If there are multiple accounts, then use an account selection menu
            if (accounts.size() > 1) {
              String[] accountMenuChoices = new String[accounts.size() + 1];

              for (int i = 0; i < accounts.size(); i++) {
                String menuChoice = String.format("Account #%s ($%.2f)", accounts.get(i).getAccountNumber(), accounts.get(i).getBalance());
                accountMenuChoices[i] = menuChoice;
              }

              accountMenuChoices[accounts.size()] = "Back";
              Console.printList(accountMenuChoices);
              accountMenuIndex = Input.getSelectionIndex(accountMenuChoices);
              accountMenuChoice = accountMenuChoices[accountMenuIndex];
            }

            if (accountMenuChoice != "Back") {
              account = accounts.get(accountMenuIndex);
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
