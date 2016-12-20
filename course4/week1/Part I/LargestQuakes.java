
/**
 * 
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class LargestQuakes {
    //if the source string is passed by the parameter of constructor, there is no need to be static
    private final static String source = "data/nov20quakedata.atom";
    
    public LargestQuakes() {
        
    }
    
    public void findLargestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        ArrayList<QuakeEntry> quakes = parser.read(source);
        System.out.println("read data for "+ quakes.size() +" quakes");
        ArrayList<QuakeEntry> topNQuakes = getLargest(quakes, 50);
        for (QuakeEntry qe : topNQuakes) {
            System.out.println(qe);
        }
        System.out.printf("Found %d quakes that match that criteria\n", topNQuakes.size());
    }
    
    private ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> data, int howMany) {
        ArrayList<QuakeEntry> res = new ArrayList<>();
        for (int i = 0; i < howMany; i++) {
            int index = indexOfLargest(data);
            if (index > -1) {
                res.add(data.get(index));
                data.remove(index);
            }
        }
        return res;
    }
    
    private int indexOfLargest(ArrayList<QuakeEntry> data) {
        int index = -1;
        double maxMagnitude = Double.MIN_VALUE;
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getMagnitude() > maxMagnitude) {
                index = i;
                maxMagnitude = data.get(i).getMagnitude();
            }
        }
        return index;
    }
}
