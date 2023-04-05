import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Provide a text-based user interface to the searching capabilities of the
 * College Finder application
 * 
 * @author Madeleine Hastings
 */
public class CollegeSearchFrontendFD implements CollegeSearchFrontendInterface {

	private Scanner userInput;
	private CollegeSearchBackendInterface backend;

	public CollegeSearchFrontendFD(Scanner userInput, CollegeSearchBackendInterface backend) {
		this.userInput = userInput;
		this.backend = backend;
	}

	/**
	 * Helper method to display a 70 column wide row of slashes: a horizontal rule.
	 */
	private void hr() {
		System.out.println("//////////////////////////////////////////////////////////////////////");
	}

	/**
	 * This loop repeatedly prompts the user for commands and displays appropriate
	 * feedback for each. This continues until the user enters 'Q' to quit.
	 */
	public void runCommandLoop() {
		// display welcome message
		hr();
		System.out.println("Welcome to the College Finder App.");
		hr();

		List<String> states;
		char command = '\0';
		while (command != 'Q') { // main loop continues until user chooses to quit
			command = this.mainMenuPrompt();
			switch (command) {
			case 'L':
				loadDataCommand();
				break;
			case 'T':
				states = chooseSearchStatesPrompt();
				findTopCommand(states);
				break;
			case 'B':
				states = chooseSearchStatesPrompt();
				findBottomCommand(states);
				break;
			case 'P':
				states = chooseSearchStatesPrompt();
				priceRangeCommand(states);
				break;
			case 'Q':
				break;
			default:
				System.out.println(
						"Didn't recognize that command. Please type one of the letters presented within []s to identify the command you would like to choose.");
				break;
			}
		}

		// display goodbye message
		hr();
		System.out.println("Thank you for using the College Finder App.");
		hr();
	}

	/**
	 * Prints the command options to System.out and reads user's choice through
	 * userInput scanner.
	 */
	public char mainMenuPrompt() {
		// display menu of choices
		System.out.println("Choose a command from the list below:");
		System.out.println("	[L]oad data from file");
		System.out.println("	Display [T]op most expensive");
		System.out.println("	Display [B]ottom least expensive");
		System.out.println("	Enter [P]rice Range");
		System.out.println("	[Q]uit");

		// read in user's choice, and trim away any leading or trailing whitespace
		System.out.println("Choose command: ");
		String input = userInput.nextLine().trim();
		if (input.length() == 0) { // if user's choice is blank, return null character
			return '\0';
		}
		// otherwise, return an uppercase version of the first character in input
		return Character.toUpperCase(input.charAt(0));
	}

	/**
	 * Prompt user to enter filename, and display error message when loading fails.
	 */
	public void loadDataCommand() {
		System.out.println("Enter the name of the file to load: ");
		String filename = userInput.nextLine().trim();
		try {
			backend.loadData(filename);
		} catch (FileNotFoundException e) {
			System.out.println("Error: Could not find or load file: " + filename);
		}
	}

	/**
	 * This method gives user the ability to interactively add or remove individual
	 * states from their query, before performing any kind of search.
	 * 
	 * @return states list of states to search for
	 */
	public List<String> chooseSearchStatesPrompt() {
		List<String> states = new ArrayList<>();
		while (true) { // loop ends when user presses enter
			System.out.println("List of states to search for: " + states.toString());
			System.out.println("Enter full name of state, or press enter to search: ");
			String state = userInput.nextLine().trim();
			if (state.length() == 0) { // an empty string ends this loop and method call
				return states;
			} else {
				// create object containing both state and in or out-of-state specification
				if (state.contains(state)) {
					states.remove(state); // remove any states that were already in the list
				} else {
					states.add(state); // add any states that were previously missing
				}
			}
		}
	}

	/**
	 * Prompts user for a number of colleges they want to see, and then displays the
	 * list of results of most expensive colleges in accordance to that number.
	 */
	public void findTopCommand(List<String> states) {
		System.out.println("Enter how many options you want to see: ");
		int input = userInput.nextInt();

		List<String> results = backend.findCollegesByMostExpensive(input, states);
		int resultIndex = 1;
		if (results.size() > 0) {
			System.out.println("Found results: ");
			for (String result : results) {
				System.out.println("[" + (resultIndex++) + "] " + result);
			}
		}
	}

	/**
	 * Prompts user for a number of colleges they want to see, and then displays the
	 * list of results of least expensive colleges in accordance to that number.
	 */
	public void findBottomCommand(List<String> states) {
		System.out.println("Enter how many options you want to see: ");
		int input = userInput.nextInt();

		List<String> results = backend.findCollegesByLeastExpensive(input, states);
		int resultIndex = 1;
		if (results.size() > 0) {
			System.out.println("Found results: ");
			for (String result : results) {
				System.out.println("[" + (resultIndex++) + "] " + result);
			}
		}
	}

	/**
	 * Prompts user for their desired price range, and then displays the list of
	 * results of colleges within the price range.
	 */
	public void priceRangeCommand(List<String> states) {
		System.out.println("Enter minimum price: ");
		int min = userInput.nextInt();
		userInput.nextLine();
		System.out.println("Enter maximum price: ");
		int max = userInput.nextInt();

		List<String> results = backend.findCollegesByPriceRange(min, max, states);
		int resultIndex = 1;
		if (results.size() > 0) {
			System.out.println("Found results: ");
			for (String result : results) {
				System.out.println("[" + (resultIndex++) + "] " + result);
			}
		}
	}
}

