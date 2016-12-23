
/**
 * Write a description of class MarkovWordOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MarkovWordTwo implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
   
    public MarkovWordTwo() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-2);  // random word to start with
        String key1 = myText[index];
        String key2 = myText[index + 1];
        sb.append(key1);
        sb.append(" ");
        sb.append(key2);
        sb.append(" ");
        for(int k=0; k < numWords-2; k++){
            ArrayList<String> follows = getFollows(key1, key2);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key1 = key2;
            key2 = next;
        }
        
        return sb.toString().trim();
    }
    
    private ArrayList<String> getFollows(String key1, String key2) {
        ArrayList<String> follows = new ArrayList<String>();
        int index = indexOf(myText, key1, key2, 0);
        while (index != -1) {
            if (index + 2 < myText.length) {
                follows.add(myText[index + 2]);
            }
            index = indexOf(myText, key1, key2, index + 1);
        }
        return follows;
    }
    
    private int indexOf(String[] words, String target1, String target2, int start) {
        for (int i = start; i < words.length - 2; i++) {
            if (target1.equals(words[i]) && target2.equals(words[i + 1])) {
                return i;
            }
        }
        return -1;
    }
}
