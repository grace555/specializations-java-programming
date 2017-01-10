
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    private MoviesBy movies;
    private RatersBy raters;
    
    
    public SecondRatings() {
        // default constructor
        //this("data//ratedmovies_short.csv", "data//ratings_short.csv");
        this("data//ratedmoviesfull.csv", "data//ratings.csv");
    }
    
    public SecondRatings(String moviefile, String ratingsfile) {
        FirstRatings fr = new FirstRatings();
        myMovies = fr.loadMovie(moviefile);
        myRaters = fr.loadRater(ratingsfile);
        movies = new MoviesBy(myMovies);
        raters = new RatersBy(myRaters);
    }
    
    public int getMovieSize() {
        return myMovies.size();
    }
    
    public int getRaterSize() {
        return myRaters.size();
    }
    
    public String getTitle(String movieID) {
        Movie m = movies.getByID(movieID);
        if (m == null) {
            return "NO SUCH MOVIE ID.";
        }
        return m.getTitle();
    }
    
    public String getID(String title) {
        Movie m = movies.getByTitle(title);
        if (m == null) {
            return "NO SUCH TITLE.";
        }
        return m.getID();
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        List<String> movieIDs = raters.getRatedMovieIDs();
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