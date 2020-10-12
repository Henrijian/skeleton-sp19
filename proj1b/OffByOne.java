public class OffByOne implements CharacterComparator {
    @Override
    public boolean equalChars(char x, char y) {
        int diff = Character.compare(x, y);
        int absDiff = java.lang.Math.abs(diff);
        return absDiff == 1;
    }
}
