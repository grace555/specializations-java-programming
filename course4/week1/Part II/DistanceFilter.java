
/**
 * Write a description of DistanceFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DistanceFilter implements Filter
{
    private Location loc;
    private double distanceMax;
    
    public DistanceFilter(Location inLoc, double max) {
        loc = new Location(inLoc);
        distanceMax = max;
    }
    
    public boolean satisfies(QuakeEntry qe) {
        return qe.getLocation().distanceTo(loc) < distanceMax;
    }
    
    public String getName() {
        return "DistanceFilter(" + loc + ", " + distanceMax + ")";
    }

}
