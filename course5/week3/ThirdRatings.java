
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class ThirdRatings {

    private ArrayList<Rater> myRaters;
    private RatersBy raters;
    
    
    public ThirdRatings() {
        //this("data//ratings_short.csv");
        this("data//ratings.csv");
    }
    
    public ThirdRatings(String ratingsfile) {
        FirstRatings fr = new FirstRatings();
        myRaters = fr.loadRater(ratingsfile);        
        raters = new RatersBy(myRaters);
    }
    
    public int getRaterSize() {
        return myRaters.size();
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<String> movieIDs = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<Rating> ratings = new ArrayList<Rating>();
        for (String movieID : movieIDs) {
            double rating = getAverageByID(movieID, minimalRaters);
            if (Math.abs(rating - 0) > 1e-5) {
                ratings.add(new Rating(movieID, rating));
            }
        }
        return ratings;
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria) {
        ArrayList<String> movieIDs = MovieDatabase.filterBy(filterCriteria);
        ArrayList<Rating> ratings = new ArrayList<Rating>();
        for (String movieID : movieIDs) {
            double rating = getAverageByID(movieID, minimalRaters);
            if (Math.abs(rating - 0) > 1e-5) {
                ratings.add(new Rating(movieID, rating));
            }
        }
        return ratings;
    }
    
    private double getAverageByID(String movieID, int minimalRaters) {
        List<Rater> list = raters.getByMovie(movieID);
        if (list.size() < minimalRaters) {
            return 0;
        }
        double sum = 0;
        for (Rater r : list) {
            sum += r.getRating(movieID);
        }
        return sum / list.size();
    }
}