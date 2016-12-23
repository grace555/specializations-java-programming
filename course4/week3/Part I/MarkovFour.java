
/**
 * Write a description of MarkovTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.Random;
import java.util.ArrayList;

public class MarkovFour {
    private String myText;
    private Random myRandom;
    private int markovNum = 4;
    
    public MarkovFour() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
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
    
    public ArrayList<String> getFollows(String key) {
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
}
