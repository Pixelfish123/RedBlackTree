import java.util.List;

public interface CollegeSearchFrontendInterface {
	// public CollegeSearchFrontendFD(Scanner userInput,
	// CollegeSearchBackendInterface backend);
	public void runCommandLoop();

	public char mainMenuPrompt();

	public void loadDataCommand();

	public List<String> chooseSearchStatesPrompt();

	public void findTopCommand(List<String> states);

	public void findBottomCommand(List<String> states);

	public void priceRangeCommand(List<String> states);
}

