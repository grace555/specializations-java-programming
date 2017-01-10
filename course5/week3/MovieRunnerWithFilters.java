
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.ArrayList;
import java.util.Collections;
import java.text.DecimalFormat;

public class MovieRunnerWithFilters {
    
    private static DecimalFormat f = new DecimalFormat("##.0000");
    
    private static final boolean DETAILS = false;
    
    private void printAverageRatings(int minimalRaters) {
        ArrayList<Rating> ratings = getRatingsByFilter(minimalRaters, new TrueFilter());
        if (DETAILS) {
            for (Rating rating : ratings) {
                System.out.println(round(rating.getValue()) + ", " + 
                                            MovieDatabase.getTitle(rating.getItem()));
            }
        }
        printLowestRatedMovie(ratings.get(0));
    }
    
    private void printAverageRatingsByYear(int minimalRaters, int year) {
        Filter f = new YearAfterFilter(year);
        ArrayList<Rating> ratings = getRatingsByFilter(minimalRaters, f);
        if (DETAILS) {
            for (Rating rating : ratings) {
                System.out.println(round(rating.getValue()) + ", " + 
                                MovieDatabase.getYear(rating.getItem()) + ", " +
                                MovieDatabase.getTitle(rating.getItem()));
            }
        }
        printLowestRatedMovie(ratings.get(0));
    }
    
    private void printAverageRatingsByGenre(int minimalRaters, String genre) {
        Filter f = new GenreFilter(genre);
        ArrayList<Rating> ratings = getRatingsByFilter(minimalRaters, f);
        if (DETAILS) {
            for (Rating rating : ratings) {
                System.out.println(round(rating.getValue()) + ", " + 
                                MovieDatabase.getGenres(rating.getItem()) + ", " +
                                MovieDatabase.getTitle(rating.getItem()));
            }
        }
        printLowestRatedMovie(ratings.get(0));
    }
    
    
    private void printAverageRatingsByMinutes(int minimalRaters, int min, int max) {
        Filter f = new MinutesFilter(min, max);
        ArrayList<Rating> ratings = getRatingsByFilter(minimalRaters, f);
        if (DETAILS) {
            for (Rating rating : ratings) {
                System.out.println(round(rating.getValue()) + ", " + 
                                MovieDatabase.getMinutes(rating.getItem()) + ", " +
                                MovieDatabase.getTitle(rating.getItem()));
            }
        }
        printLowestRatedMovie(ratings.get(0));
    }
    
    private void printAverageRatingsByDirectors(int minimalRaters, String directors) {
        Filter f = new DirectorsFilter(directors);
        ArrayList<Rating> ratings = getRatingsByFilter(minimalRaters, f);
        if (DETAILS) {
            for (Rating rating : ratings) {
                System.out.println(round(rating.getValue()) + ", " + 
                                MovieDatabase.getDirector(rating.getItem()) + ", " +
                                MovieDatabase.getTitle(rating.getItem()));
            }
        }
        printLowestRatedMovie(ratings.get(0));
    }
   
    private void printAverageRatingsByYearAfterAndGenre(int minimalRaters, int year, String genre) {
        AllFilters f = new AllFilters();
        f.addFilter(new YearAfterFilter(year));
        f.addFilter(new GenreFilter(genre));
        ArrayList<Rating> ratings = getRatingsByFilter(minimalRaters, f);
        if (DETAILS) {
            for (Rating rating : ratings) {
                System.out.println(round(rating.getValue()) + ", " + 
                                MovieDatabase.getYear(rating.getItem()) + ", " +
                                MovieDatabase.getGenres(rating.getItem()) + ", " +
                                MovieDatabase.getTitle(rating.getItem()));
            }
        }
        printLowestRatedMovie(ratings.get(0));
    }
    
    private void printAverageRatingsByDirectorsAndMinutes(int minimalRaters, String directors, int min, int max) {
        AllFilters f = new AllFilters();
        f.addFilter(new DirectorsFilter(directors));
        f.addFilter(new MinutesFilter(min, max));
        ArrayList<Rating> ratings = getRatingsByFilter(minimalRaters, f);
        if (DETAILS) {
            for (Rating rating : ratings) {
                System.out.println(round(rating.getValue()) + ", " + 
                                MovieDatabase.getMinutes(rating.getItem()) + ", " +
                                MovieDatabase.getDirector(rating.getItem()) + ", " +
                                MovieDatabase.getTitle(rating.getItem()));
            }
        }
        printLowestRatedMovie(ratings.get(0));
    }
    
    public void quiz() {
        System.out.println("Q4:");
        printAverageRatings(35);
        System.out.println("Q5:");
        printAverageRatingsByYear(20, 2000);
        System.out.println("Q6:");
        printAverageRatingsByGenre(20, "Comedy");
        System.out.println("Q7:");
        printAverageRatingsByMinutes(5, 105, 135);
        System.out.println("Q8:");
        printAverageRatingsByDirectors(4, "Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack");
        System.out.println("Q9:");
        printAverageRatingsByYearAfterAndGenre(8, 1990, "Drama");
        System.out.println("Q10:");
        printAverageRatingsByDirectorsAndMinutes(3, "Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack", 90, 180);
        
    }
    
    private String round(double num) {
        return f.format(num);
    }
    
    private void printLowestRatedMovie(Rating rating) {
        System.out.println("Lowest rated movie: " + MovieDatabase.getTitle(rating.getItem()) + ", " +
                            round(rating.getValue()));
    }
    
    private ArrayList<Rating> getRatingsByFilter(int minimalRaters, Filter f) {
        ThirdRatings sRatings = new ThirdRatings();
        if (DETAILS) {
            System.out.println("Number of raters: " + sRatings.getRaterSize());
            System.out.println("Number of movies: " + MovieDatabase.size());
        }
        ArrayList<Rating> ratings = sRatings.getAverageRatingsByFilter(minimalRaters, f);
        System.out.println("Total rated movies: " + ratings.size());
        Collections.sort(ratings);
        return ratings;
    }

}
