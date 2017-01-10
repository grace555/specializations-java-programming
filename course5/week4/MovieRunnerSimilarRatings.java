
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.ArrayList;
import java.util.Collections;
import java.text.DecimalFormat;

public class MovieRunnerSimilarRatings {
    private static DecimalFormat f = new DecimalFormat("##.0000");
    
    private static final boolean DETAILS = false;
    
    private ArrayList<Rating> getRatingsByFilter(String raterID, int numSimilarRaters, 
                                        int minimalRaters, Filter filterCriteria) {
        ForthRatings sRatings = new ForthRatings();
        if (DETAILS) {
            System.out.println("Number of raters: " + RaterDatabase.size());
            System.out.println("Number of movies: " + MovieDatabase.size());
        }
        ArrayList<Rating> ratings = sRatings.getSimilarRatingsByFilter(
                                                                    raterID,
                                                                    numSimilarRaters,
                                                                    minimalRaters,
                                                                    filterCriteria
                                                                );
        System.out.println("Total rated movies: " + ratings.size());
        return ratings;
    }
    
    private void printRatedMovie(ArrayList<Rating> ratings) {
        if (DETAILS) {
            for (Rating rating : ratings) {
                System.out.println(round(rating.getValue()) + ", " + 
                                            MovieDatabase.getTitle(rating.getItem()));
            }
        }
        Rating rating = ratings.get(0);
        System.out.println("Highest weighted average rating movie: " + 
                            MovieDatabase.getTitle(rating.getItem()) + ", " +
                            round(rating.getValue()));
    }
    
    private String round(double num) {
        return f.format(num);
    }
    
    private void printSimilarRatings(String raterID, int numSimilarRaters, int minimalRaters) {
       Filter f = new TrueFilter();
       ArrayList<Rating> ratings = getRatingsByFilter(
                                                raterID,
                                                numSimilarRaters,
                                                minimalRaters,
                                                new TrueFilter()
                                            );
       printRatedMovie(ratings);
    }
    
    private void printSimilarRatingsByGenre(String raterID, int numSimilarRaters, int minimalRaters,
                                            String genre) {
       Filter f = new GenreFilter(genre);
       ArrayList<Rating> ratings = getRatingsByFilter(
                                                raterID,
                                                numSimilarRaters,
                                                minimalRaters,
                                                f
                                            );
       printRatedMovie(ratings);
    }
    
    private void printSimilarRatingsByDirector(String raterID, int numSimilarRaters, int minimalRaters,
                                            String directors) {
       Filter f = new DirectorsFilter(directors);
       ArrayList<Rating> ratings = getRatingsByFilter(
                                                raterID,
                                                numSimilarRaters,
                                                minimalRaters,
                                                f
                                            );
       printRatedMovie(ratings);
    }
    
    private void printSimilarRatingsByGenreAndMinutes(String raterID, int numSimilarRaters, 
                                            int minimalRaters, String genre, int min, int max) {
       AllFilters f = new AllFilters();
       f.addFilter(new GenreFilter(genre));
       f.addFilter(new MinutesFilter(min, max));
       ArrayList<Rating> ratings = getRatingsByFilter(
                                                raterID,
                                                numSimilarRaters,
                                                minimalRaters,
                                                f
                                            );
       printRatedMovie(ratings);
    }
    
    private void printSimilarRatingsByYearAfterAndMinutes(String raterID, int numSimilarRaters, 
                                            int minimalRaters, int year, int min, int max) {
       AllFilters f = new AllFilters();
       f.addFilter(new YearAfterFilter(year));
       f.addFilter(new MinutesFilter(min, max));
       ArrayList<Rating> ratings = getRatingsByFilter(
                                                raterID,
                                                numSimilarRaters,
                                                minimalRaters,
                                                f
                                            );
       printRatedMovie(ratings);
    }
    
    
    public void assignment() {
        
        System.out.println("Q6:");
        String raterID = "337";
        int minimalRaters = 3;
        int numSimilarRaters = 10; 
        printSimilarRatings(raterID, numSimilarRaters, minimalRaters);
        
        System.out.println("Q7:");
        raterID = "964";
        minimalRaters = 5;
        numSimilarRaters = 20;
        String genre = "Mystery";
        printSimilarRatingsByGenre(raterID, numSimilarRaters, minimalRaters, genre);
        
        System.out.println("Q8:");
        raterID = "71";
        minimalRaters = 5;
        numSimilarRaters = 20;
        printSimilarRatings(raterID, numSimilarRaters, minimalRaters);
        
        System.out.println("Q9:");
        raterID = "120";
        minimalRaters = 2;
        numSimilarRaters = 10;
        String directors = "Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh";
        printSimilarRatingsByDirector(raterID, numSimilarRaters, minimalRaters, directors);
        
        System.out.println("Q10:");
        raterID = "168";
        minimalRaters = 3;
        numSimilarRaters = 10;
        genre = "Drama";
        int min = 80;
        int max = 160;
        printSimilarRatingsByGenreAndMinutes(raterID, numSimilarRaters, minimalRaters, genre, min, max);
        
        System.out.println("Q11:");
        raterID = "314";
        minimalRaters = 5;
        numSimilarRaters = 10;
        int year = 1975;
        min = 70;
        max = 200;
        printSimilarRatingsByYearAfterAndMinutes(raterID, numSimilarRaters, minimalRaters, year, min, max);
    }
}
