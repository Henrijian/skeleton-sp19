public class RabinKarpAlgorithm {
    /**
     * This algorithm returns the starting index of the matching substring.
     * This method will return -1 if no matching substring is found, or if the input is invalid.
     */
    public static int rabinKarp(String input, String pattern) {
        try {
            int inputLen = input.length();
            int patternLen = pattern.length();
            RollingString patternString = new RollingString(pattern, patternLen);
            int patternHash = patternString.hashCode();
            RollingString searchString = new RollingString(input.substring(0, patternLen), patternLen);
            for (int i = 0; i < inputLen - patternLen + 1; i++) {
                int searchHash = patternString.hashCode();
                if (searchHash == patternHash && patternString.equals(searchString)) {
                    return i;
                }
                searchString.addChar(input.charAt(i + patternLen));
            }
            return -1;
        } catch (Exception e) {
            return -1;
        }
    }
}
