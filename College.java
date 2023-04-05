
public class College implements CollegeInterface {

	@Override
	public String getState() {
		// TODO Auto-generated method stub
		return "New York";
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Harvard";
	}

	@Override
	public int getInState() {
		// TODO Auto-generated method stub
		return 50;
	}

	@Override
	public int getOutState() {
		// TODO Auto-generated method stub
		return 100;
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
