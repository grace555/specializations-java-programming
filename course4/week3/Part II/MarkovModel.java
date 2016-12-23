
/**
 * Write a description of MarkovTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.Random;
import java.util.ArrayList;

public class MarkovModel extends AbstractMarkovModel {
    
    private int markovNum = 1;
    
    public MarkovModel(int markovNum) {
        super();
        this.markovNum = markovNum;
    }
    
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length() - markovNum);
        String key = myText.substring(index, index + markovNum);
        sb.append(key);
        for(int k=0; k < numChars - markovNum; k++){
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1) + next;
        }
        
        return sb.toString();
    }
    
    public String toString() {
        return "MarkovModel of order n, n = " + markovNum;
    }
}
