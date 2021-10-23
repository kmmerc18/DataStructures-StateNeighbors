import java.util.ArrayList;
import java.util.List;

public class State {
    private String name;
    private String code;

    private List<State> neighbors;

    // constructor
    public State(String name, String code){
        this.name = name;
        this.code = code;

        // construct an empty array list where we will store all of our state neighbors
        // the default capacity is ten
        this.neighbors = new ArrayList<>();
    }

    // instance methods
    public String getName() {
        return this.name;
    }

    public String getCode() {
        return this.code;
    }

    public int getNeighborCount() {
        // with an array this would be .length
        return neighbors.size();
    }

    public State getNeighbor(int i) {
        // with an array this would be [i], but arraylists are objects so they have methods for that
        return this.neighbors.get(i);
    }

    public void addNeighbor(State s) {
        this.neighbors.add(s);
    }

    public boolean equals(Object o) {
        if(o == this) {
            // this is the exact same object
            return true;
        }
        else if(!(o instanceof State)) {
            // this is the wrong type
            return false;
        }
        // we know that o is a State so it is safe to cast o to type State
        State s = (State) o;
        return s.code.equals(this.code);
    }
}
