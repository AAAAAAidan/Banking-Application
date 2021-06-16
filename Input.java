package BankingApplication;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Input {
	
	public static void waitForEnter() {
		System.out.println("Press enter to continue");
		getInput();
	}
	
	public static String getInput() {
		return getInput(".*+");
	}
	
	public static String getInput(String pattern) {
		
		Scanner scan = new Scanner(System.in);
		String input;
		boolean validInput;
		
		do {
			input = scan.nextLine();
			validInput = Pattern.matches(pattern, input);
			
			if (!validInput) {
				Console.printLine("Invalid input: please try again");
			}
		} while (!validInput);
		
		return input;
	}
	
	public static int getSelectionIndex(String[] items) {
		int selection = Integer.parseInt(getInput("[1-" + items.length + "]+"));
		return selection - 1;
	}
	
}
