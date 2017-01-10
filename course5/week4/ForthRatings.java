
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class ForthRatings {
    
    
    public ForthRatings() {
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
    }
    
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        return getAverageRatingsByFilter(minimalRaters, new TrueFilter());
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
        List<Rater> list = RaterDatabase.getRatersByMovie(movieID);
        if (list.size() < minimalRaters) {
            return 0;
        }
        double sum = 0;
        for (Rater r : list) {
            sum += r.getRating(movieID);
        }
        return sum / list.size();
    }
    
    private double dotProduct(Rater me, Rater r) {
        ArrayList<String> ratedMovieIDs = me.getItemsRated();
        double sum = 0;
        for (String movieID : ratedMovieIDs) {
            if (r.hasRating(movieID)) {
                double meCentredRating = me.getRating(movieID) - 5;
                double rCentredRating = r.getRating(movieID) - 5;
                sum += meCentredRating * rCentredRating;
            }
        }
        return sum;
    }
    
    private ArrayList<Rating> getSimilarities(String raterID) {
        ArrayList<Rating> list = new ArrayList<Rating>();
        Rater me = RaterDatabase.getRater(raterID);
        for (Rater r : RaterDatabase.getRaters()) {
            if (r != me) {
                double res = dotProduct(me, r);
                if (res > 0) {
                    list.add(new Rating(r.getID(), res));
                }
            }
        }
        Collections.sort(list, Collections.reverseOrder());
        return list;
    }
    
    public ArrayList<Rating> getSimilarRatings(String raterID, int numSimilarRaters, int minimalRaters) {
        return getSimilarRatingsByFilter(raterID, numSimilarRaters, minimalRaters, new TrueFilter());
    }
    
    public ArrayList<Rating> getSimilarRatingsByFilter(String raterID, int numSimilarRaters, 
                                        int minimalRaters, Filter filterCriteria) {
        ArrayList<Rating> similarities = getSimilarities(raterID);
        ArrayList<Rating> res = new ArrayList<>();
        ArrayList<String> movieIDs = MovieDatabase.filterBy(filterCriteria);
        for (String movieID : movieIDs) {
            double sum = 0;
            int count = 0;
            for (int i = 0; i < numSimilarRaters; i++) {
                Rating r = similarities.get(i);
                Rater rater = RaterDatabase.getRater(r.getItem());
                if (rater.hasRating(movieID)) {
                    sum += r.getValue() * rater.getRating(movieID);
                    count++;
                }
            }
            if (count >= minimalRaters) {
                res.add(new Rating(movieID, sum / count));
            }
        }
        Collections.sort(res, Collections.reverseOrder());
        return res;
    }
}