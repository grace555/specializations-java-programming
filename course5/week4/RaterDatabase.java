
/**
 * Write a description of RaterDatabase here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class RaterDatabase {
    private static HashMap<String,Rater> ourRaters;
     
    private static void initialize() {
        // this method is only called from addRatings 
        if (ourRaters == null) {
            ourRaters = new HashMap<String,Rater>();
        }
    }

    public static void initialize(String filename) {
        if (ourRaters == null) {
            ourRaters= new HashMap<String,Rater>();
            addRatings("data/" + filename);
        }
    }   
    
    public static void addRatings(String filename) {
        initialize(); 
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord record : parser) {
            String id = formatID(record.get("rater_id"));
            String item = formatID(record.get("movie_id"));
            String rating = record.get("rating");
            addRaterRating(id,item,Double.parseDouble(rating));
        }
    }
    
    public static void addRaterRating(String raterID, String movieID, double rating) {
        initialize(); 
        Rater rater =  null;
        if (ourRaters.containsKey(raterID)) {
            rater = ourRaters.get(raterID); 
        } 
        else { 
            rater = new EfficientRater(raterID);
            ourRaters.put(raterID,rater);
        }
        rater.addRating(movieID,rating);
    } 
             
    public static Rater getRater(String id) {
        initialize();
        
        return ourRaters.get(id);
    }
    
    public static ArrayList<Rater> getRaters() {
        initialize();
        ArrayList<Rater> list = new ArrayList<Rater>(ourRaters.values());
        
        return list;
    }
 
    public static int size() {
        return ourRaters.size();
    }
    
    public static ArrayList<Rater> getRatersByMovie(String movieID) {
        initialize();
        ArrayList<Rater> list = new ArrayList<>();
        for (Rater r : ourRaters.values()) {
            if (r.hasRating(movieID)) {
                list.add(r);
            }
        }
        return list;
    }
    
    private static String formatID(String id) {
        return id.replaceFirst("^0+(?!$)", "").trim();
    }
}
