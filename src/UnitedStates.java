import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class UnitedStates {
    private ArrayList<State> states;

    // constructor
    public UnitedStates() {
        // 50 states + DC
        states = new ArrayList<>(51);

        // load in code file and fill up the states arraylist
        loadCodesFile();
        loadNeighborsFile();

    }

    private void loadNeighborsFile() {
        Scanner neighbors;
        try {
            neighbors = new Scanner(new File("contiguous-usa.txt"));
        } catch (FileNotFoundException e) {
            System.err.println("Could not find the adjacent states file");
            e.printStackTrace();
            System.exit(1);
            return;
        }

        while(neighbors.hasNextLine()) {
            String line = neighbors.nextLine();
            String[] values = line.split(" ");

            /*
             * values[0] is one state, values[1] is another
             * Find the state in the states ArrayList that has the code values[0]
             * then we need to add a neighbor, values[1] to that state
             *      First look up the state object associated with values[1]
             * To do this, we need to search our arraylist
             *
             * Let's assume we have a findState(String code) method
             */
            findState(values[0]).addNeighbor(findState(values[1]));
            findState(values[1]).addNeighbor(findState(values[0]));
        }
        return;
    }

    /**
     * This uses a while loop with an integer to access each value
     * preconditions: code must exist in the states array
     *
     * @param code Two-letter abbreviation for a state
     * @return the State from the states ArrayList that is associated with the code provided
     */
    private State findState1(String code) {
        int i = 0;
        while(!states.get(i).getCode().equals(code)) {
            i++;
        }
        // I would be outside the bounds of the arraylist, this would cause a crash
        //IndexOutOfBounds exception
        return states.get(i);
    }

    /**
     * This uses a for-each loop to access all elements in the ArrayList
     * preconditions: code must exist in the states array
     *
     * @param code Two-letter abbreviation for a state
     * @return the State from the states ArrayList that is associated with the code provided
     */
    // FIX ME add null checks
    private State findState2(String code) {
        for(State s : states) {
            if(s.getCode().equals(code)) {
                return s;
            }
        }
        return null;
    }

    /**
     * This uses an iterator to access all elements in the ArrayList
     * preconditions: code must exist in the states array
     *
     * @param code Two-letter abbreviation for a state
     * @return the State from the states ArrayList that is associated with the code provided
     */
    private State findState3(String code) {
        Iterator<State> i = states.iterator();
        // keep looping while the iterator hasn't processed each element in the ArrayList
        while(i.hasNext()) {
            // get the next element from the ArrayList
            State s = i.next();
            if (s.getCode().equals(code)) {
                return s;
            }
        }
        // we didn't find it
        return null;
    }

    /**
     * This uses indexOf method in List ADT
     * preconditions: code must exist in the states array
     *
     * @param code Two-letter abbreviation for a state
     * @return the State from the states ArrayList that is associated with the code provided
     */
    private State findState(String code) {
        // making a temp object so I can compare the code
        return states.get(states.indexOf(new State(code, code)));
    }

    /**
     * Load in the information stored in codes.csv
     * create each state and add it to the states ArrayList
     */
    private void loadCodesFile() {
        // scanner can be used to read a file
        Scanner codes;
        try {
            // opened the file for reading with the scanner
            codes = new Scanner(new File("codes.csv"));
        } catch (FileNotFoundException e) {
            System.err.println("Could not find the codes file");
            e.printStackTrace();
            System.exit(1);
            return;           // this line should never be reached
        }

        // if we get here we have successfully opened the file
        while(codes.hasNextLine()) {                    // for each line in the file
            String line = codes.nextLine();             // we read the line
            String[] values = line.split(",");   // we split the line using the commas in the file
            State s = new State(values[0], values[1]);  // create a State object from the relevant information
            states.add(s);                              // add the state object to the states ArrayList
        }
    return;
    }

    public void printStates() {
        // for-in loop that loops over all elements in a collection
        // for ( tmp var : collection)
        // in the body, var will be a single element from the collection
        // we will continue to loop until all elements of the collection have been the tmp var
        for(State s : states) {
            // printf gives formatted output
            // %s is a string placeholder
            // in the format string 1$ is the first argument, 2$ the second etc.
            System.out.printf("%1$-20s (%2$s): ", s.getName(), s.getCode());
            for(int i = 0; i < s.getNeighborCount()-1; i++) {
                System.out.printf("%s, ", s.getNeighbor(i).getName());
            }
            if(s.getNeighborCount()>0) {
                System.out.printf("%s", s.getNeighbor(s.getNeighborCount() - 1).getName());
            }
            System.out.println();
        }

    }
}
