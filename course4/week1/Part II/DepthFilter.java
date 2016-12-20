
/**
 * Write a description of DepthFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DepthFilter implements Filter
{
    private double depthMin;
    private double depthMax;
    
    public DepthFilter(double min, double max) {
        depthMin = min;
        depthMax = max;
    }
    
    public boolean satisfies(QuakeEntry qe) {
        return depthMin <= qe.getDepth() && qe.getDepth() <= depthMax; 
    }
    
    public String getName() {
        return "DepthFilter(" + depthMin + ", " + depthMax + ")";
    }
}
