// import javax.lang.model.util.ElementScanner14;

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

    @Override
    public int compareTo(CollegeInterface o) {
        if (this.outstate < o.getOutState())
            return -1;
        else if (this.outstate > o.getOutState())
            return 1;
        else if (this.outstate == o.getOutState()) {
            return this.name.compareTo(o.getState());
        } else
            return 0;
    }

    @Override
    public int compareTo(CollegeInterface o, boolean instate) {
        if (instate) {
            if (this.instate < o.getInState())
                return -1;
            else if (this.instate > o.getInState())
                return 1;
            else if (this.instate == o.getInState()) {
                return this.name.compareTo(o.getState());
            } else
                return 0;
        } else {
            return compareTo(o);
        }
    }

}
