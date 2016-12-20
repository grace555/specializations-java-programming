
/**
 * Write a description of PhraseFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PhraseFilter implements Filter
{
    private String where;
    private String phrase;
    private int len;
    
    public PhraseFilter(String where, String phrase) {
        this.where = where;
        this.phrase = phrase;
        this.len = phrase.length();
    }
    
    public boolean satisfies(QuakeEntry qe) {
        String info = qe.getInfo();
        boolean res = where.equals("start") && phrase.equals(info.substring(0, len));
        res |= where.equals("any") && info.indexOf(phrase) > -1;
        res |= where.equals("end") && phrase.equals(info.substring(info.length() - len));
        return res;
    }
    
    public String getName() {
        return "PhraseFilter(\"" + where + "\", \"" + phrase + "\")";
    }
}
