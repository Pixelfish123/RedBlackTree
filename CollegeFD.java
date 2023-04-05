
/**
 * Placeholder class so CollegeSearchFrontendFD will functions
 */
public class CollegeFD implements CollegeInterface {

	private String name;
	private String state;
	private int instate;
	private int outstate;

	public CollegeFD(String name, String state, int instate, int outstate) {
		this.name = name;
		this.state = state;
		this.instate = instate;
		this.outstate = outstate;
	}

	public String getState() {
		return state;
	}

	public String getName() {
		return name;
	}

	public int getInState() {
		return 0;
	}

	public int getOutState() {
		return 0;
	}

	@Override
	public int compareTo(CollegeInterface o) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'compareTo'");
	}

	@Override
	public int compareTo(CollegeInterface o, boolean instate) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'compareTo'");
	}
}
