
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    
    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }
 
    abstract public String getRandomText(int numChars);
    
    protected ArrayList<String> getFollows(String key) {
        ArrayList<String> res = new ArrayList<String>();
        int klen = key.length();
        int index =  myText.indexOf(key);
        for (int i = 0; i < myText.length() - klen; i++) {
            if (key.equals(myText.substring(i, i + klen))) {
                res.add(myText.substring(i + klen, i + klen + 1));
            }
        }
        return res;
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }

}
