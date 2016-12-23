
/**
 * Write a description of MarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EfficientMarkovWord implements IMarkovModel {
    
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    private HashMap<WordGram, ArrayList<String>> map;
    
    
    public EfficientMarkovWord(int numWords) {
        this.myOrder = numWords;
        myRandom = new Random();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text) {
        myText = text.split("\\s+");
        buildMap();
        //printHashMapInfo();
    }
    
    private void buildMap() {
        map = new HashMap<WordGram, ArrayList<String>>();
        for (int i = 0; i < myText.length - myOrder; i++) {
            WordGram key = new WordGram(myText, i, myOrder);
            String next = myText[i + myOrder];
            if (map.containsKey(key)) {
                map.get(key).add(next);
            } else {
                ArrayList<String> tempList = new ArrayList<String>();
                tempList.add(next);
                map.put(key, tempList);
            }
        }
        WordGram key = new WordGram(myText, myText.length - myOrder, myOrder);
        if (!map.containsKey(key)) {
            map.put(key, new ArrayList<String>());
        }
    }
    
    public void printHashMapInfo() {
        System.out.println("Number of keys: " + map.size());
        int maxLen = 0;
        String key = "";
        for (Map.Entry<WordGram, ArrayList<String>> e : map.entrySet()) {
            if (maxLen < e.getValue().size()) {
                key = e.getKey().toString();
                maxLen = e.getValue().size();
            }
            
            System.out.print(e.getKey() + ": ");
            for (String s : e.getValue()) {
                System.out.print(s + ",");
            }
            System.out.println("");
        }
        System.out.println("Largest Length: " + key + " with " + maxLen);
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
        return map.get(kGram);
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
            if (follows == null || follows.size() == 0) {
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
