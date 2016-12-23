
/**
 * Write a description of MarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Random;
import java.util.ArrayList;

public class MarkovWord implements IMarkovModel {
    
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    
    
    public MarkovWord(int numWords) {
        this.myOrder = numWords;
        myRandom = new Random();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text) {
        myText = text.split("\\s+");
    }
    
    public int indexOf(String[] words, WordGram target, int start) {
        for (int i = start; i < words.length - myOrder; i++) {
            WordGram source = new WordGram(words, i, myOrder);
            if (source.equals(target)) {
                return i;
            }
        }
        return -1;
    }
    
    public ArrayList<String> getFollows(WordGram kGram) {
        ArrayList<String> follows = new ArrayList<String>();
        int index = indexOf(myText, kGram, 0);
        while (index != -1) {
            if (index + myOrder < myText.length) {
                follows.add(myText[index + myOrder]);
            }
            index = indexOf(myText, kGram, index + 1);
        }
        return follows;
    }
    
    public String getRandomText(int numWords) {
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length - myOrder);
        String[] tempWords = new String[myOrder];
        for (int i = 0; i < myOrder; i++) {
            tempWords[i] = myText[index + i];
            sb.append(myText[index + i]);
            sb.append(" ");
        }
        WordGram kGram = new WordGram(tempWords, 0, myOrder);
        for (int k = 0; k < numWords - myOrder; k++) {
            ArrayList<String> follows = getFollows(kGram);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            kGram = kGram.shiftAdd(next);
        }
        return sb.toString().trim();
    }
}
