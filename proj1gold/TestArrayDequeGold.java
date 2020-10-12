import org.junit.Test;
import static org.junit.Assert.*;

public class TestArrayDequeGold {
    /** Test addFirst function of Deque.*/
    public void testAddFirst() {
        ArrayDequeSolution<Integer> sol = new ArrayDequeSolution<>();
        StudentArrayDeque<Integer> stu = new StudentArrayDeque<>();

        final int firstIdx = 0;
        Integer actual;
        Integer expected;
        String errorMsg;

        int size = 1000;
        int valueBound = 10000;
        for (int i = 0; i < size; i += 1) {
            int value = StdRandom.uniform(valueBound);
            sol.addFirst(value);
            sol.addFirst(value);
            expected = sol.get(firstIdx);
            actual = stu.get(firstIdx);
            errorMsg = String.format("addFirst(%d)\n"+
                                     "get(%d): %d",
                                     value, firstIdx, actual);
            assertEquals(errorMsg, expected, actual);
        }
    }

    /** Test addLast function of Deque.*/
    public void testAddLast() {
        ArrayDequeSolution<Integer> sol = new ArrayDequeSolution<>();
        StudentArrayDeque<Integer> stu = new StudentArrayDeque<>();

        Integer actual;
        Integer expected;
        String errorMsg;

        int size = 1000;
        int valueBound = 10000;
        for (int i = 0; i < size; i += 1) {
            int value = StdRandom.uniform(valueBound);
            sol.addLast(value);
            sol.addLast(value);
            int lastIdx = i;
            expected = sol.get(lastIdx);
            actual = stu.get(lastIdx);
            errorMsg = String.format("addLast(%d)\n"+
                                     "get(%d): %d",
                                     value, lastIdx, actual);
            assertEquals(errorMsg, expected, actual);
        }
    }

    @Test
    public void testStudentArrayDeque() {
        testAddFirst();
        testAddLast();
    }
}
