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
    public String getInState() {
        return "$" + instate;
    }

    @Override
    public String getOutState() {
        return "$"+ outstate;
    }

    public String toString() {
        return name + " " + state + " " + instate + " " + outstate;
    }
}
