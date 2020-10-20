import java.util.*;
import java.util.NoSuchElementException;
public class FilteredList<T> implements Iterable<T> {
    private List<T> list;
    private Predicate<T> pred;

    public FilteredList(List<T> L, Predicate<T> filter) {
        this.list = L;
        this.pred = filter;
    }

    @Override
    public Iterator<T> iterator() {
        return new FilteredListIterator(list, pred);
    }

    private class FilteredListIterator implements Iterator<T> {
        private List<T> list;
        private Predicate<T> pred;
        private int curPos;
        public FilteredListIterator (List<T> l, Predicate<T> f) {
            this.list = l;
            this.pred = f;
            this.curPos = 0;
        }

        @Override
        public boolean hasNext() {
            while (curPos < list.size() && !pred.test(list.get(curPos))) {
                curPos += 1;
            }
            return curPos < list.size();
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            curPos += 1;
            return list.get(curPos - 1);
        }
    }

    public static void main(String[] args) {
        ArrayList<String> testL = new ArrayList<>();
        testL.add("my");
        testL.add("name");
        testL.add("is");
        testL.add("Henri.");
        Filter<String> testFilter = new Filter<>();
        FilteredList<String> FL = new FilteredList<>(testL, testFilter);
        for (String x: FL) {
            System.out.println(x);
        }
    }
}
