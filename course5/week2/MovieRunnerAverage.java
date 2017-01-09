
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.ArrayList;
import java.util.Collections;
import java.text.DecimalFormat;

public class MovieRunnerAverage {
    
    private static DecimalFormat f = new DecimalFormat("##.0000");
    
    private void printAverageRatings(int minimalRaters) {
        SecondRatings sRatings = new SecondRatings();
        
        ArrayList<Rating> ratings = sRatings.getAverageRatings(minimalRaters);
        Collections.sort(ratings);
        for (Rating rating : ratings) {
            //System.out.println(round(rating.getValue()) + ", " + sRatings.getTitle(rating.getItem()));
        }
        Rating rating = ratings.get(0);
        System.out.println("Lowest rating movie: " + round(rating.getValue()) + ", " + sRatings.getTitle(rating.getItem()));
        System.out.println("Total movies: " + ratings.size());
    }
    
    private void getAverageRatingOneMovie(String title, int minRaters) {
        int minimalRaters = minRaters;
        SecondRatings sRatings = new SecondRatings();
        String movieID = sRatings.getID(title);
        ArrayList<Rating> ratings = sRatings.getAverageRatings(minimalRaters);
        Rating r = null;
        for (Rating rating : ratings) {
            if (rating.getItem().equals(movieID)) {
                r = rating;
            }
        }
        if (r == null) {
            System.out.println("NO SUCH MOVIE");
            return;
        }
        System.out.println(round(r.getValue()) + ", " + title);
        
    }
    
    public void quiz() {
        String title = "The Maze Runner";
        System.out.println("Q5: " + title);
        getAverageRatingOneMovie(title, 2);
        
        title = "Moneyball";
        System.out.println("Q6: " + title);
        getAverageRatingOneMovie(title, 2);
        
        title = "Vacation";
        System.out.println("Q7: " + title);
        getAverageRatingOneMovie(title, 2);
        
        System.out.println("Q8: ");
        printAverageRatings(50);
        
        System.out.println("Q9: ");
        printAverageRatings(20);
        
        System.out.println("Q10: ");
        printAverageRatings(12);
    }
    
    private String round(double num) {
        return f.format(num);
    }
    
    
}
