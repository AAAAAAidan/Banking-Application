package BankingApplication;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Input {

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
				System.out.println("Invalid input. Please try again.");
			}
		} while (!validInput);
		
		return input;
	}
	
}
