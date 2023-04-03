// --== CS400 Spring 2023 File Header Information ==--
// Name: Alan Liang
// Email: aliang26@wisc.edu
// Team: CI
// TA: Karan Grover
// Lecturer: Florian Heimerl
// Notes to Grader: <optional extra notes>

/**
 * This class represents a college and implements the CollegeInterface.
 */
public class CollegeDW implements CollegeInterface {
    private String name;
    private String state;
    private int instate;
    private int outstate;

    public CollegeDW(String name, String state, int instate, int outstate) {
        this.name = name;
        this.state = state;
        this.instate = instate;
        this.outstate = outstate;
    }

    @Override
    public String getState() {
        return state;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getInState() {
        return instate;
    }

    @Override
    public int getOutState() {
        return outstate;
    }

    public String toString() {
        return name + "\nState: " + state + "\nInstate cost: $" + instate + "\nOutstate cost: $" + outstate;
    }
}
