public class IntList {
    public int first;
    public IntList rest;

    public IntList(int f, IntList r) {
        first = f;
        rest = r;
    }

    /**
     * Return IntList with all elements in L be squared in non-destructive way.
     * @param L the list prepared for squared.
     * @return squared L.
     */
    public static IntList square(IntList L) {
        if (L == null) {
            return L;
        }
        return new IntList(L.first * L.first, square(L.rest));
    }

    public static IntList squareIterative(IntList L) {
        if (L == null) {
            return L;
        }
        IntList res = new IntList(L.first * L.first, null);
        IntList resP = res;
        IntList LP = L.rest;
        while (LP != null) {
            resP.rest = new IntList(LP.first * LP.first, null);
            resP = resP.rest;
            LP = LP.rest;
        }
        return res;
    }

    /**
     * Return IntList with all elements in L be squared in destructive way.
     * @param L the list prepared for squared.
     * @return squared L.
     */
    public static IntList squareDestructive(IntList L) {
        if (L == null) {
            return L;
        }
        L.first *= L.first;
        return squareDestructive(L.rest);
    }

    public static IntList squareDestructiveIterative(IntList L) {
        IntList p = L;
        while (p != null) {
            p.first *= p.first;
            p = p.rest;
        }
        return L;
    }

    public static void main(String[] args) {
        IntList L = new IntList(5, null);
        L = new IntList(4, L);
        L = new IntList(3, L);
        L = new IntList(2, L);
        L = new IntList(1, L);
        IntList squaredL1 = IntList.square(L);
        IntList squaredL2 = IntList.squareIterative(L);
        squareDestructive(L);
        squareDestructiveIterative(L);
    }
}
