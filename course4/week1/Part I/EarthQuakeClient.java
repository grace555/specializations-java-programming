import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    
    //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
    private final static String source = "data/nov20quakedata.atom";
    
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    private ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for (QuakeEntry qe : quakeData) {
            if (qe.getMagnitude() > magMin) {
                answer.add(qe);
            }
        }
        return answer;
    }

    private ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax,
    Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for (QuakeEntry qe : quakeData) {
            if (qe.getLocation().distanceTo(from) < distMax) {
                answer.add(qe);
            }
        }
        return answer;
    }
    
    private ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, double minDepth, double maxDepth) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe : quakeData) {
            if (minDepth < qe.getDepth() && qe.getDepth() < maxDepth) {
                answer.add(qe);
            }
        }
        return answer;
    }
    
    private ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, String where, String phrase) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe : quakeData) {
            String info = qe.getInfo();
            int len = phrase.length();
            boolean condition = where.equals("any") && info.indexOf(phrase) > -1;
            condition |= where.equals("start") && phrase.equals(info.substring(0, len));
            condition |= where.equals("end") && phrase.equals(info.substring(info.length() - len));
            if (condition) {
                answer.add(qe);
            }
        }
        return answer;
    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> magnitudeQuakes = filterByMagnitude(list, 5.0);
        for (QuakeEntry qe : magnitudeQuakes) {
           System.out.println(qe); 
        }
        System.out.printf("Found %d quakes that match that criteria\n", magnitudeQuakes.size());
    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
        //Location city = new Location(35.988, -78.907);

        // This location is Bridgeport, CA
        Location city =  new Location(38.17, -118.82);

        // TODO
        ArrayList<QuakeEntry> closeQuakes = filterByDistanceFrom(list, 1000*1000, city);
        for (QuakeEntry qe : closeQuakes) {
            double distanceInMeters = city.distanceTo(qe.getLocation());
            System.out.println(distanceInMeters/1000 + " " + qe.getInfo());
        }
        System.out.printf("Found %d quakes that match that criteria.\n", closeQuakes.size()); 
    }
    
    public void quakesOfDepth(Double minDepth, Double maxDepth) {
        EarthQuakeParser parser = new EarthQuakeParser();
        ArrayList<QuakeEntry> quakes = parser.read(source);
        System.out.printf("read data for %d quakes\n", quakes.size());
        ArrayList<QuakeEntry> depthRangeQuakes = filterByDepth(quakes, minDepth, maxDepth);
        /*
        for (QuakeEntry qe : depthRangeQuakes) {
            System.out.println(qe);
        }
        */
        System.out.printf("Found %d quakes that match that criteria\n", depthRangeQuakes.size());
    }
    
    public void quakesByPhrase(String where, String phrase) {
        EarthQuakeParser parser = new EarthQuakeParser();
        ArrayList<QuakeEntry> quakes = parser.read(source);
        System.out.printf("read data for %d quakes\n", quakes.size());
        ArrayList<QuakeEntry> phraseQuakes = filterByPhrase(quakes, where, phrase);
        /*
        for (QuakeEntry qe : phraseQuakes) {
            System.out.println(qe);
        }
        */
        System.out.printf("Found %d quakes that match %s at %s\n", phraseQuakes.size(), phrase, where);
    }
    
    public void test() {
        quakesOfDepth(-8000.0, -5000.0);
        quakesByPhrase("start", "Explosion");
        quakesByPhrase("end", "California");
        quakesByPhrase("any", "Creek");
    }
    
    
    private void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    private void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
}
