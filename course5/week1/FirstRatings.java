
/**
 * Write a description of FirstRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {
    
    private ArrayList<Movie> loadMoive(String csvFile) {
        ArrayList<Movie> res = new ArrayList<>();
        FileResource fr = new FileResource(csvFile);
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord record : parser) {
            Movie movie = new Movie(
                record.get("id"),
                record.get("title"),
                record.get("year"),
                record.get("genre"),
                record.get("director"),
                record.get("country"),
                record.get("poster"),
                Integer.parseInt(record.get("minutes"))
            );
            res.add(movie);
        }
        return res;
    }
    
    private ArrayList<Rater> loadRater(String csvFile) {
        HashMap<String, Rater> map = new HashMap<>();
        FileResource fr = new FileResource(csvFile);
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord record : parser) {
            String raterID = record.get("rater_id");
            if (!map.containsKey(raterID)) {
                map.put(raterID, new Rater(raterID));
            }
            map.get(raterID).addRating(record.get("movie_id"), Double.parseDouble(record.get("rating")));
        }
        ArrayList<Rater> res = new ArrayList<>();
        for (Rater r : map.values()) {
            res.add(r);
        }
        return res;
    }
    
    private ArrayList<Movie> filtering(ArrayList<Movie> movies, MovieFilter filter) {
        ArrayList<Movie> res = new ArrayList<>();
        for (Movie m : movies) {
            if (filter.meetCriteria(m)) {
                res.add(m);
            }
        }
        return res;
    }
    
    
    
    public void assignment() {
        ArrayList<Movie> list = loadMoive("data//ratedmoviesfull.csv");
        System.out.println("Total Movies: " + list.size());
        
        String genre = "Comedy";
        ArrayList<Movie> tempList = filtering(list, new MovieFilterByGenre(genre));
        System.out.println("Genre is " + genre + ": " + tempList.size());
        
        tempList = filtering(list, new MovieFilterByTime(">", 150));
        System.out.println("Minutes > 150: " + tempList.size());
        
        
        Map.Entry<String, ArrayList<Movie>> entry = testMaxNumMoviesByDirector(list);
        System.out.println(entry.getKey() + " has " + entry.getValue().size() + " movies");
        for (Movie m : entry.getValue()) {
            //System.out.println(m);
        }
        
        ArrayList<Rater> rlist = loadRater("data//ratings.csv");
        System.out.println("Total Raters: " +rlist.size());
        
        RatersBy raters = new RatersBy(rlist);
        Rater r = raters.getByRaterID("193");
        System.out.println("Number of ratings for rater " + r.getID() + ": " + r.numRatings());
        
        List<Rater> tempRaters = raters.getByMaxNumRatings();
        System.out.println("Max num of ratings rater: " + tempRaters.size());
        for (Rater rater : tempRaters) {
            System.out.println("RaterID: " + rater.getID() + ", number of ratings: " + rater.numRatings());
        }
        
        String movieID = "1798709";
        tempRaters = raters.getByMovie(movieID);
        System.out.println("Num of Raters for movie " + movieID + ": " + tempRaters.size());
        
        List<String> movieIDs = raters.getRatedMovieIDs();
        System.out.println("There are " + movieIDs.size() + " rated movies");
        
    }
    
    private Map.Entry<String, ArrayList<Movie>> testMaxNumMoviesByDirector(List<Movie> list) {
        Map.Entry<String, ArrayList<Movie>> entry = null;
        MoviesBy movies = new MoviesBy(list);
        Map<String, ArrayList<Movie>> map = movies.getByDirector();
        int max = 0;
        for (Map.Entry<String, ArrayList<Movie>> e : map.entrySet()) {
            if (e.getValue().size() > max) {
                entry = e;
                max = e.getValue().size();
            }
        }
        return entry;
    }
}
