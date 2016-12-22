
/**
 * Write a description of TitleLastAndMagnitudeComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.Comparator;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        String lw1 = getLastWord(q1.getInfo());
        String lw2 = getLastWord(q2.getInfo());
        int res = lw1.compareTo(lw2);
        if (res == 0) {
            res = Double.compare(q1.getMagnitude(), q2.getMagnitude());
        }
        return res;
    }
    
    private String getLastWord(String info) {
        return info.substring(info.lastIndexOf(" ") + 1);
    }
}
