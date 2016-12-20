
/**
 * Write a description of MatchAllFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.ArrayList;

public class MatchAllFilter implements Filter 
{
    private ArrayList<Filter> filters;
    
    public MatchAllFilter() {
        filters = new ArrayList<Filter>();
    }
    
    public void addFilter(Filter filter) {
        filters.add(filter);
    }
    
    public boolean satisfies(QuakeEntry qe) {
        for (Filter filter : filters) {
            if (!filter.satisfies(qe)) {
                return false;
            }
        }
        return true;
    }
    
    public String getName() {
        StringBuilder sb = new StringBuilder();
        sb.append("Filters used are: ");
        for (Filter filter : filters) {
            sb.append(filter.getName());
            sb.append(", ");
        }
        sb.setLength(sb.length() - 2);
        sb.append(".");
        return sb.toString();
    }

}
