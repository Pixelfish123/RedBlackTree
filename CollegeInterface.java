
public interface CollegeInterface extends Comparable<CollegeInterface> {
    // public CollegeInterface(String name, String state, int instate, int
    // outstate);
    public String getState();

    public String getName();

    public int getInState();

    public int getOutState();

    public int compareTo(CollegeInterface o);

    public int compareTo(CollegeInterface o, boolean instate);
}
