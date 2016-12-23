
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*;
import java.util.ArrayList;

public class Tester {
    
    public void testGetFollows() {
        String st = "this is a test yes this is a test.";
        st = st.replace('\n', ' ');
        MarkovOne markov = new MarkovOne();
        markov.setTraining(st);
        printFollows(markov.getFollows("t"));
        printFollows(markov.getFollows("e"));
        printFollows(markov.getFollows("es"));
        printFollows(markov.getFollows("."));
        printFollows(markov.getFollows("t."));
    }
    
    public void testGetFollowsWithFile() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovOne markov = new MarkovOne();
        markov.setTraining(st);
        printFollows(markov.getFollows("he"));
    }
    
    private void printFollows(ArrayList<String> in) {
        System.out.println("There are " + in.size() + " following characters");
        /*
        for (String s : in) {
            System.out.print(s + ", ");
        }
        System.out.println("");
        */
    }
    
}
