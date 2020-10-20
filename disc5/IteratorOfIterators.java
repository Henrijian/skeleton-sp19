import java.util.*;
public class IteratorOfIterators implements Iterator<Integer> {
    private List<Iterator<Integer>> iteratorList;
    private int curPos;
    public IteratorOfIterators(List<Iterator<Integer>> a) {
        this.iteratorList = a;
        this.curPos = 0;
    }

    @Override
    public boolean hasNext() {
        int checkCount = 0;
        while (checkCount < iteratorList.size() && !iteratorList.get(curPos).hasNext()) {
            curPos += 1;
            if (curPos == iteratorList.size()) {
                curPos = 0;
            }
        }
        return checkCount < iteratorList.size();
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int returnPos = curPos;
        curPos += 1;
        if (curPos == iteratorList.size()) {
            curPos = 0;
        }
        return iteratorList.get(returnPos).next();
    }

    public static void main(String[] args) {
        List<Iterator<Integer>> testIL = new ArrayList<Iterator<Integer>>();
        List<Integer> L1 = new ArrayList<>();
        L1.add(1);
        L1.add(2);
        L1.add(3);
        testIL.add(L1.iterator());
        List<Integer> L2 = new ArrayList<>();
        L2.add(4);
        L2.add(5);
        L2.add(6);
        testIL.add(L2.iterator());
        List<Integer> L3 = new ArrayList<>();
        L3.add(7);
        L3.add(8);
        L3.add(9);
        testIL.add(L3.iterator());

        Iterator<Integer> testI = new IteratorOfIterators(testIL);
        while (testI.hasNext()) {
            System.out.println(testI.next());
        }
    }
}
