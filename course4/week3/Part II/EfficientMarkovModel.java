
/**
 * Write a description of MarkovTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EfficientMarkovModel extends AbstractMarkovModel {
    
    private int markovNum = 1;
    private HashMap<String, ArrayList<String>> map;
    
    public EfficientMarkovModel(int markovNum) {
        super();
        this.markovNum = markovNum;
    }
    
    @Override
    public void setTraining(String s){
        super.setTraining(s);
        buildMap();
        printHashMapInfo();
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
            if (follows == null || follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1) + next;
        }
        
        return sb.toString();
    }
    
    private void buildMap() {
        map = new HashMap<String, ArrayList<String>>();
        for (int i = 0; i < myText.length() - markovNum; i++) {
            String key = myText.substring(i, i + markovNum);
            String next = myText.substring(i + markovNum, i + markovNum + 1);
            if (map.containsKey(key)) {
                map.get(key).add(next);
            } else {
                ArrayList<String> temp = new ArrayList<String>();
                temp.add(next);
                map.put(key, temp);
            }
        }
        String key = myText.substring(myText.length() - markovNum);
        if (!map.containsKey(key)) {
            map.put(key, new ArrayList<String>());
        }
    }
    
    public void printHashMapInfo() {
        System.out.println("Number of keys: " + map.size());
        int maxLen = 0;
        String key = "";
        for (Map.Entry<String, ArrayList<String>> e : map.entrySet()) {
            if (maxLen < e.getValue().size()) {
                key = e.getKey();
                maxLen = e.getValue().size();
            }
            /*
            System.out.print(e.getKey() + ": ");
            for (String s : e.getValue()) {
                System.out.print(s + ",");
            }
            System.out.println("");
            */
        }
        System.out.println("Largest Length: " + key + " with " + maxLen);
    }
    
    @Override
    protected ArrayList<String> getFollows(String key) {
        return map.get(key);
    }
    
    public String toString() {
        return "EfficientMarkovModel of order n, n = " + markovNum;
    }
}
