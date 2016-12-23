
public class WordGram {
    private String[] myWords;
    private int myHash;

    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
    }

    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt "+index);
        }
        return myWords[index];
    }

    public int length(){
        // TODO: Complete this method
        return myWords.length;
    }

    public String toString(){
        String ret = "";
        // TODO: Complete this method
        StringBuilder sb = new StringBuilder();
        for (String s : myWords) {
            sb.append(s);
            sb.append(' ');
        }
        ret = sb.toString();
        return ret.trim();
    }

    public boolean equals(Object o) {
        WordGram other = (WordGram) o;
        // TODO: Complete this method
        if (this.length() != other.length()) {
            return false;
        }
        for (int i = 0; i < this.length(); i++) {
            if (!this.wordAt(i).equals(other.wordAt(i))) {
                return false;
            }
        }
        return true;

    }

    public WordGram shiftAdd(String word) { 
        // shift all words one towards 0 and add word at the end. 
        // you lose the first word
        // TODO: Complete this method
        String out[] = new String[this.length()];
        for (int i = 0; i < this.length() - 1; i++) {
            out[i] = this.wordAt(i + 1);
        }
        out[this.length() - 1] = word;
        return new WordGram(out, 0, out.length);
    }
    
    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

}