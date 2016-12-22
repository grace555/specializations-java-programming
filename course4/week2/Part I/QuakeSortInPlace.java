
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
    
    private int q1Passes = 50;
    
    public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted) {
        for (int i = 1; i <= numSorted; i++) {
            if (quakeData.get(i - 1).getMagnitude() > quakeData.get(i).getMagnitude()) {
                QuakeEntry temp = quakeData.get(i - 1);
                quakeData.set(i - 1, quakeData.get(i));
                quakeData.set(i, temp);
            }
        }
    }
    
    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in) {
        for (int i =  in.size() - 1; i >= 0; i--) {
            onePassBubbleSort(in, i);
        }
    }
    
    public boolean checkInSortedOrder(ArrayList<QuakeEntry> data) {
        for (int i = 1; i < data.size(); i++) {
            if (data.get(i - 1).getMagnitude() > data.get(i).getMagnitude()) {
                return false;
            }
        }
        return true;
    }
    
    public int sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in) {
        int count = 0;
        for (int i =  in.size() - 1; i >= 0; i--) {
            onePassBubbleSort(in, i);
            count++;
            if (checkInSortedOrder(in)) {
                break;
            }
        }
        return count;
    }
    
    public int getLargestDepth(ArrayList<QuakeEntry> quakes, int from) {
        int maxIdx = from;
        for (int i = from + 1; i < quakes.size(); i++) {
            if (quakes.get(i).getDepth() > quakes.get(maxIdx).getDepth()) {
                maxIdx = i;
            }
        }
        return maxIdx;
    }
    
    
    public void sortByLargestDepth(ArrayList<QuakeEntry> in) {
        int count = 0;
        for (int i = 0; i < in.size(); i++) {
            int maxIdx = getLargestDepth(in, i);
            QuakeEntry temp = in.get(i);
            in.set(i, in.get(maxIdx));
            in.set(maxIdx, temp);
            count++;
            if (count == q1Passes) {
                break;
            }
        }
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
            
        }
        
    }
    
    public int sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in) {
        int count = 0;
        for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
            count++;
            if (checkInSortedOrder(in)) {
                break;
            }
        }
        return count;
    }

    public void quizSolution() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/earthQuakeDataDec6sample2.atom";
        
        q1Passes = 50;
        System.out.printf("Q1: (after %d passes)\n", q1Passes);
        ArrayList<QuakeEntry> list  = parser.read(source);
        sortByLargestDepth(list);
        System.out.println(list.get(list.size() - 1));
        
        System.out.printf("Q2: ");
        source = "data/earthQuakeDataWeekDec6sample1.atom";
        list  = parser.read(source);
        System.out.println(sortByMagnitudeWithCheck(list));
        
        System.out.printf("Q3: ");
        source = "data/earthQuakeDataWeekDec6sample1.atom";
        list  = parser.read(source);
        System.out.println(sortByMagnitudeWithBubbleSortWithCheck(list));
        
        System.out.println("See Part II");

    }
    
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/earthQuakeDataDec6sample2.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                              qe.getLocation().getLatitude(),
                              qe.getLocation().getLongitude(),
                              qe.getMagnitude(),
                              qe.getInfo());
        }
        
    }
}
