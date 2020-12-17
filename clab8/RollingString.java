/**
 * A String-like class that allows users to add and remove characters in the String
 * in constant time and have a constant-time hash function. Used for the Rabin-Karp
 * string-matching algorithm.
 */
class RollingString{
    private StringBuilder stringBuff;
    private int hashBuff;
    private int length;
    private int oldCharHashBash;
    /**
     * Number of total possible int values a character can take on.
     * DO NOT CHANGE THIS.
     */
    static final int UNIQUECHARS = 128;

    /**
     * The prime base that we are using as our mod space. Happens to be 61B. :)
     * DO NOT CHANGE THIS.
     */
    static final int PRIMEBASE = 6113;

    /**
     * Initializes a RollingString with a current value of String s.
     * s must be the same length as the maximum length.
     */
    public RollingString(String s, int length) {
        assert(s.length() == length);
        this.stringBuff = new StringBuilder(s);
        this.hashBuff = 0;
        for (int i = 0; i < length; i++) {
            this.hashBuff = (((this.hashBuff * UNIQUECHARS) % PRIMEBASE) + (int) s.charAt(i)) % PRIMEBASE;
        }
        this.length = length;
        this.oldCharHashBash = 1;
        for (int i = 1; i < length; i++) {
            this.oldCharHashBash = (this.oldCharHashBash * UNIQUECHARS) % PRIMEBASE;
        }
    }

    /**
     * Adds a character to the back of the stored "string" and 
     * removes the first character of the "string". 
     * Should be a constant-time operation.
     */
    public void addChar(char c) {
        this.hashBuff = ((this.hashBuff + PRIMEBASE - (int) stringBuff.charAt(0) * oldCharHashBash)
                * UNIQUECHARS + (int) c) % PRIMEBASE;
        stringBuff.deleteCharAt(0);
        stringBuff.append(c);
    }

    /**
     * Returns the "string" stored in this RollingString, i.e. materializes
     * the String. Should take linear time in the number of characters in
     * the string.
     */
    public String toString() {
        return stringBuff.toString();
    }

    /**
     * Returns the fixed length of the stored "string".
     * Should be a constant-time operation.
     */
    public int length() {
        return stringBuff.length();
    }


    /**
     * Checks if two RollingStrings are equal.
     * Two RollingStrings are equal if they have the same characters in the same
     * order, i.e. their materialized strings are the same.
     */
    @Override
    public boolean equals(Object o) {
        return this.toString().equals(o.toString());
    }

    /**
     * Returns the hashcode of the stored "string".
     * Should take constant time.
     */
    @Override
    public int hashCode() {
        return hashBuff;
    }
}
