public class OffByN implements CharacterComparator {
    private int N;

    public OffByN(int n) {
        this.N = n;
    }

    @Override
    public boolean equalChars(char x, char y) {
        int diff = Character.compare(x, y);
        int absDiff = java.lang.Math.abs(diff);
        return absDiff == N;
    }
}
