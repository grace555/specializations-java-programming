
/**
 * Write a description of TitleAndDepthComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Comparator;

public class TitleAndDepthComparator implements Comparator<QuakeEntry> {
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        int res = q1.getInfo().compareTo(q2.getInfo());
        if (res == 0) {
            res = Double.compare(q1.getDepth(), q2.getDepth());
        }
        return res;
    }
}
