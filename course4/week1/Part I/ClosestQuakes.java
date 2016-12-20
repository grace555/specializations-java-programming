
/**
 * Find N-closest quakes
 * 
 * @author Duke Software/Learn to Program
 * @version 1.0, November 2015
 */

import java.util.*;

public class ClosestQuakes {
    
    //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
    public final static String source =  "data/nov20quakedata.atom"; 
    
    public ArrayList<QuakeEntry> getClosest(ArrayList<QuakeEntry> quakeData, Location current, int howMany) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TO DO
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        kSelect(copy, current, 0, copy.size() - 1, howMany);
        for (int i = 0; i < howMany; i++) {
            answer.add(copy.get(i));
        }
        Collections.sort(answer, new Comparator<QuakeEntry>() {
            public int compare(QuakeEntry a, QuakeEntry b) {
                return (int) (a.getLocation().distanceTo(current) - b.getLocation().distanceTo(current));
            }
        });
        return answer;
    }
    
    private void kSelect(ArrayList<QuakeEntry> qList, Location current, int left, int right, int howMany) {
        if (left == right) {
            return;
        }
        int pivotIndex = left + (right - left) / 2;
        pivotIndex = partition(qList, current, left, right, pivotIndex);
        if (pivotIndex > howMany) {
            kSelect(qList, current, left, pivotIndex - 1, howMany);
        } else {
            kSelect(qList, current, pivotIndex + 1, right, howMany);
        } 
    }
    private int partition(ArrayList<QuakeEntry> qList, Location current, int left, int right, int pivotIndex) {
        QuakeEntry pivot = qList.get(pivotIndex);
        int index = left;
        swap(qList, pivotIndex, right);
        while (left < right) {
            if (moreClose(qList.get(left), pivot, current)) {
                swap(qList, left, index++);
            }
            left++;
        }
        swap(qList, index, right);
        return index;
    }
    private boolean moreClose(QuakeEntry a, QuakeEntry b, Location current) {
        return a.getLocation().distanceTo(current) < b.getLocation().distanceTo(current);
    }
    private void swap(ArrayList<QuakeEntry> list, int a, int b) {
        QuakeEntry temp = list.get(a);
        list.set(a, list.get(b));
        list.set(b, temp);
    }
    

    public void findClosestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size());

        Location jakarta  = new Location(-6.211,106.845);

        ArrayList<QuakeEntry> close = getClosest(list,jakarta,3);
        for(int k=0; k < close.size(); k++){
            QuakeEntry entry = close.get(k);
            double distanceInMeters = jakarta.distanceTo(entry.getLocation());
            System.out.printf("%4.2f\t %s\n", distanceInMeters/1000,entry);
        }
        System.out.println("number found: "+close.size());
    }
    
}
