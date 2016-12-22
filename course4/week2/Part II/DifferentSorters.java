
/**
 * Write a description of class DifferentSorters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class DifferentSorters {
    
    private static String source = "data/earthQuakeDataWeekDec6sample1.atom";
    
    public void sortWithCompareTo(int quakeNumber) {
        EarthQuakeParser parser = new EarthQuakeParser();
        ArrayList<QuakeEntry> list  = parser.read(source);
        Collections.sort(list);
        System.out.println("Print quake entry in position " + quakeNumber);
        System.out.println(list.get(quakeNumber));

    }    

    public void sortByMagnitude() {
        EarthQuakeParser parser = new EarthQuakeParser();
        ArrayList<QuakeEntry> list  = parser.read(source);
        Collections.sort(list, new MagnitudeComparator());
        for(QuakeEntry qe: list) {
            System.out.println(qe);
        }
    }
    

    public void sortByDistance() {
        EarthQuakeParser parser = new EarthQuakeParser();
        ArrayList<QuakeEntry> list  = parser.read(source);
        // Location is Durham, NC
        Location where = new Location(35.9886, -78.9072);
        Collections.sort(list, new DistanceComparator(where));
        for(QuakeEntry qe: list) {
            System.out.println(qe);
        }

    }
    
    public void sortByTitleAndDepth(int quakeNumber) {
        EarthQuakeParser parser = new EarthQuakeParser();
        ArrayList<QuakeEntry> list = parser.read(source);
        Collections.sort(list, new TitleAndDepthComparator());
        System.out.println("Print quake entry in position " + quakeNumber);
        System.out.println(list.get(quakeNumber));
    }
    
    public void sortByLastWordInTitleThenByMagnitude(int quakeNumber) {
        EarthQuakeParser parser = new EarthQuakeParser();
        ArrayList<QuakeEntry> list = parser.read(source);
        Collections.sort(list, new TitleLastAndMagnitudeComparator());
        System.out.println("Print quake entry in position " + quakeNumber);
        System.out.println(list.get(quakeNumber));
    }
    
    public void quizSolution() {
        System.out.println("Q4: 2,4,5,9,8,1 => 1,2,5,9,8,4 (two passes of selection sort, small to large)");
        System.out.println("Q5: 4,2,5,9,8,1 => 2,4,5,1,8,9 (two passes of bubble sort, small to large)");
        System.out.println("Q6: ");
        source = "data/earthQuakeDataWeekDec6sample2.atom";
        sortWithCompareTo(600);
        System.out.println("Q7: ");
        source = "data/earthQuakeDataWeekDec6sample2.atom";
        sortByTitleAndDepth(500);
        System.out.println("Q8: ");
        source = "data/earthQuakeDataWeekDec6sample1.atom";
        sortByLastWordInTitleThenByMagnitude(500);
    }
}
