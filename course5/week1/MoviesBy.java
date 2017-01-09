
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

}
