import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
    private final static String source = "data/nov20quakedata.atom";
    
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");

        //Filter f = new MagnitudeFilter(4.0, 5.0); 
        //ArrayList<QuakeEntry> m6 = filter(list, f);
        //f = new DepthFilter(-35000.0, -12000.0);
        //ArrayList<QuakeEntry> m7  = filter(m6, f); 
        Filter f = new DistanceFilter(new Location(35.42, 139.43), 10000000);
        ArrayList<QuakeEntry> m6 = filter(list, f);
        f = new PhraseFilter("end", "Japan");
        ArrayList<QuakeEntry> m7 = filter(m6, f);
        
        for (QuakeEntry qe: m7) { 
            System.out.println(qe);
        } 
    }
    
    public void testMatchAllFilter() {
        EarthQuakeParser parser = new EarthQuakeParser();
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MagnitudeFilter(0.0, 2.0));
        maf.addFilter(new DepthFilter(-100000.0, -10000.0));
        maf.addFilter(new PhraseFilter("any", "a"));
        ArrayList<QuakeEntry> res = filter(list, maf);
        for (QuakeEntry qe: res) { 
            System.out.println(qe);
        }
        System.out.println(maf.getName());
    }

    public void testMatchAllFilter2() {
        EarthQuakeParser parser = new EarthQuakeParser();
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MagnitudeFilter(0.0, 3.0));
        maf.addFilter(new DistanceFilter(new Location(36.1314, -95.9372), 10000000));
        maf.addFilter(new PhraseFilter("any", "Ca"));
        ArrayList<QuakeEntry> res = filter(list, maf);
        for (QuakeEntry qe: res) { 
            System.out.println(qe);
        }
        System.out.println(maf.getName());
    }
    
    private void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
    }

    private void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }
    }

   public void test() {
       EarthQuakeParser parser = new EarthQuakeParser();
       ArrayList<QuakeEntry> list  = parser.read(source);
       Filter f;
       MatchAllFilter maf;
       ArrayList<QuakeEntry> res;
       
       f = new DepthFilter(-12000.0, -10000.0);
       res = filter(list, f);
       //need DepthFilter be exlusive 
       System.out.println("Q1: 127");
       
       f = new DepthFilter(-4000.0, -2000.0);
       res = filter(list, f);
       //need DepthFilter be exlusive 
       System.out.println("Q2: 157");
       
       f = new PhraseFilter("start", "Quarry Blast");
       res = filter(list, f);
       System.out.println("Q3: " + res.size());
       
       f = new PhraseFilter("end", "Alaska");
       res = filter(list, f);
       System.out.println("Q4: " + res.size());
       
       f = new PhraseFilter("any", "Can");
       res = filter(list, f);
       System.out.println("Q5: " + res.size());
       
       System.out.println("Q6: 5.10");
       
       System.out.println("Q7: Japan");
       
       maf = new MatchAllFilter();
       maf.addFilter(new DistanceFilter(new Location(39.7392, -104.9903), 1000000 ));
       maf.addFilter(new PhraseFilter("end", "a"));
       res = filter(list, maf);
       System.out.println("Q8: " + res.size()); 
       
       maf = new MatchAllFilter();
       maf.addFilter(new MagnitudeFilter(3.5, 4.5));
       maf.addFilter(new DepthFilter(-55000.0, -20000.0));
       res = filter(list, maf);
       System.out.println("Q9: " + res.size());
       
       maf = new MatchAllFilter();
       maf.addFilter(new MagnitudeFilter(1.0, 4.0));
       maf.addFilter(new DepthFilter(-180000.0, -30000.0));
       maf.addFilter(new PhraseFilter("any", "o"));
       res = filter(list, maf);
       System.out.println("Q10: " + res.size());
       
       maf = new MatchAllFilter();
       maf.addFilter(new MagnitudeFilter(0.0, 5.0));
       maf.addFilter(new DistanceFilter(new Location(55.7308, 9.1153), 3000000));
       maf.addFilter(new PhraseFilter("any", "e"));
       res = filter(list, maf);
       System.out.println("Q11: " + res.size());
       
       System.out.println("Q12: In the DistanceFromFilter class, a private variable for the name was not assigned the value of the parameter representing the name of the filter in the Constructor method.");
   }
}
