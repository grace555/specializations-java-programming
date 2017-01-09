
/**
 * Write a description of RatersBy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;

public class RatersBy {
    private List<Rater> raters;
    public RatersBy(List<Rater> raters) {
        this.raters = raters;
    }
    
    public Rater getByRaterID(String id) {
        Rater res = null;
        for (Rater r : raters) {
            if (r.getID().equals(id)) {
                res = r;
            }
        }
        return res;
    }
    
    public List<Rater> getByMaxNumRatings() {
        int max = 0;
        for (Rater r : raters) {
            if (r.numRatings() > max) {
                max = r.numRatings();
            }
        }
        List<Rater> res = new ArrayList<>();
        for (Rater r : raters) {
            if (r.numRatings() == max) {
                res.add(r);
            }
        }
        return res;
    }
    
    public List<Rater> getByMovie(String movie_id) {
        List<Rater> res = new ArrayList<>();
        for (Rater r : raters) {
            if (r.hasRating(movie_id)) {
                res.add(r);
            }
        }
        return res;
    }
    
    public List<String> getRatedMovieIDs() {
        HashSet<String> set = new HashSet<>();
        for (Rater r : raters) {
            ArrayList<String> movieIDs = r.getItemsRated();
            for (String movieID : movieIDs) {
                set.add(movieID);
            }
        }
        List<String> res = new ArrayList<>();
        for (String movieID : set) {
            res.add(movieID);
        }
        return res;
    }
}
