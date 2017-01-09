
/**
 * Write a description of MovieFilterByTime here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MovieFilterByTime implements MovieFilter {
    
    private int time;
    private String sign;
    
    public MovieFilterByTime(String sign, int time) {
        this.time = time;
        this.sign = sign;
    }

    public boolean meetCriteria(Movie movies) {
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
}