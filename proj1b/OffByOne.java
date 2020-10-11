public class OffByOne implements CharacterComparator {
    @Override
    public boolean equalChars(char x, char y) {
        char lowerX = Character.toLowerCase(x);
        char lowerY = Character.toLowerCase(y);
        int diff = Character.compare(lowerX, lowerY);
        int absDiff = java.lang.Math.abs(diff);
        return absDiff == 1;
    }
}
