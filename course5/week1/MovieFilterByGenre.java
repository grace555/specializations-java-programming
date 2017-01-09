
/**
 * Write a description of MovieFilterByGenre here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class MovieFilterByGenre implements MovieFilter {
    
    private String genre;
    
    public MovieFilterByGenre(String genre) {
        this.genre = genre;
    }

    public boolean meetCriteria(Movie movies) {
        return movies.getGenres().indexOf(genre) != -1;
    }
}
