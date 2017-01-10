
/**
 * Write a description of MoviesBy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

public class MoviesBy {
    
    private List<Movie> movies;
    
    public MoviesBy(List<Movie> movies) {
        this.movies = movies;
    }
    
    public Map<String, ArrayList<Movie>> getByDirector() {
        HashMap<String, ArrayList<Movie>> res = new HashMap<>();
        for (Movie m : movies) {
            String[] directors = m.getDirector().split(",");
            for (String director : directors) {
                if (director.charAt(0) == ' ') {
                    director = director.substring(1);
                }
                if (!res.containsKey(director)) {
                    res.put(director, new ArrayList<Movie>());
                }
                res.get(director).add(m);
            }
        }
        return res;
    }
    
    public List<Movie> getByTime(String sign, int time) {
       List<Movie> res = new ArrayList<>();
       for (Movie m : movies) {
           if (meetTimeCriteria(m, sign, time)) {
               res.add(m);
            }
       }
       return res;
    }
    
    private boolean meetTimeCriteria(Movie movies, String sign, int time) {
        if (sign.equals(">")) {
            return movies.getMinutes() > time;
        } else if (sign.equals("<")) {
            return movies.getMinutes() < time;
        } else if (sign.equals("==")) {
            return movies.getMinutes() == time;
        } else if (sign.equals(">=")) {
            return movies.getMinutes() >= time;
        } else if (sign.equals("<=")) {
            return movies.getMinutes() <= time;
        } else {
            return false;
        }
    }
    
    public List<Movie> getByGenre(String genre) {
       List<Movie> res = new ArrayList<>();
       for (Movie m : movies) {
           if (m.getGenres().indexOf(genre) != -1) {
               res.add(m);
            }
       }
       return res;
    }
    
    public Movie getByID(String id) {
        Movie res = null;
        for (Movie m : movies) {
            if (m.getID().equals(id)) {
                res = m;
            }
        }
        return res;
    }
    
    public Movie getByTitle(String title) {
        Movie res = null;
        for (Movie m : movies) {
            if (m.getTitle().equals(title)) {
                res = m;
            }
        }
        return res;
    }
}
