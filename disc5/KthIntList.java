import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
/** Iterates over every Kth element of the IntList given to the constructor.
 * For example, if L is an IntList containing elements
 * [0, 1, 2, 3, 4, 5, 6, 7] with K = 2, then
 * for (Iterator<Integer> p = new KthIntList(L, 2); p.hasNext(); ) {
 * System.out.println(p.next());
 * }
 * would print get 0, 2, 4, 6. */
public class KthIntList implements Iterator<Integer> {
    public int k;
    private List<Integer> curList;
    private boolean hasNext;

    public KthIntList (List<Integer> I, int k) {
        this.k = k;
        this.curList = I;
        this.hasNext = true;
    }

    /**
     * Returns true if there is a next Kth element. Do not modify.
     */
    public boolean hasNext () {
        return this.hasNext;
    }

    /**
     * Returns the next Kth element of the IntList given in the constructor.
     * Returns the 0th element first. Throws a NoSuchElementException if
     * there are no Integers available to return.
     */
    public Integer next () {
        if (curList.isEmpty()) {
            throw new NoSuchElementException();
        }
        Integer returnInt = curList.remove(0);
        int removeCount = 1;
        while (removeCount < k - 1 && !curList.isEmpty()) {
            curList.remove(0);
        }
        hasNext = !curList.isEmpty();
        return returnInt;
    }
}