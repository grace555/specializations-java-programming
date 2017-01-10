
/**
 * Write a description of DirectorsFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DirectorsFilter implements Filter {
    private String[] directors;
    
    public DirectorsFilter(String inDirectors) {
        this.directors = inDirectors.split(",");
    }
    
    @Override
    public boolean satisfies(String id) {
        String tempD = MovieDatabase.getDirector(id);
        for (String d : directors) {
            if (tempD.indexOf(d) != -1) {
                return true;
            }
        }
        return false;
    }
}
