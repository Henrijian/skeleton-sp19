import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestArrayDequeGold {
    private List<String> callStack = new ArrayList<>();

    private String callStackMessage() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < callStack.size(); i += 1) {
            String stackMsg = callStack.get(i);
            result.append(stackMsg);
            if (i != callStack.size() - 1) {
                result.append("\n");
            }
        }
        return result.toString();
    }

    /** Test addFirst function of Deque.*/
    public void testAddFirst() {
        callStack.clear();
        ArrayDequeSolution<Integer> sol = new ArrayDequeSolution<>();
        StudentArrayDeque<Integer> stu = new StudentArrayDeque<>();
        callStack.add("new StudentArrayDeque<>()");

        final int firstIdx = 0;
        Integer actual;
        Integer expected;
        String stackMsg;

        int size = 1000;
        int valueBound = 10000;
        for (int i = 0; i < size; i += 1) {
            int value = StdRandom.uniform(valueBound);
            sol.addFirst(value);
            stu.addFirst(value);
            stackMsg = String.format("addFirst(%d)", value);
            callStack.add(stackMsg);
            expected = sol.get(firstIdx);
            actual = stu.get(firstIdx);
            stackMsg = String.format("get(%d): %d", firstIdx, actual);
            callStack.add(stackMsg);
            assertEquals(callStackMessage(), expected, actual);
        }
    }

    /** Test addLast function of Deque.*/
    public void testAddLast() {
        callStack.clear();
        ArrayDequeSolution<Integer> sol = new ArrayDequeSolution<>();
        StudentArrayDeque<Integer> stu = new StudentArrayDeque<>();
        callStack.add("new StudentArrayDeque<>()");

        int lastIdx;
        Integer actual;
        Integer expected;
        String stackMsg;

        int size = 1000;
        int valueBound = 10000;
        for (int i = 0; i < size; i += 1) {
            int value = StdRandom.uniform(valueBound);
            sol.addLast(value);
            stu.addLast(value);
            stackMsg = String.format("addLast(%d)", value);
            callStack.add(stackMsg);
            lastIdx = i;
            expected = sol.get(lastIdx);
            actual = stu.get(lastIdx);
            stackMsg = String.format("get(%d): %d", lastIdx, actual);
            callStack.add(stackMsg);
            assertEquals(callStackMessage(), expected, actual);
        }
    }

    public void testIsEmpty() {
        callStack.clear();
        StudentArrayDeque<Integer> stu = new StudentArrayDeque<>();
        callStack.add("new StudentArrayDeque<>()");

        callStack.add("isEmpty()");
        assertTrue(callStackMessage(), stu.isEmpty());

        int valueBound = 10000;
        Integer value = StdRandom.uniform(valueBound);
        stu.addFirst(value);
        String errorMsg = String.format("addFirst(%d)\n"+
                                        "isEmpty()",
                                        value);
        assertFalse(errorMsg, stu.isEmpty());

        Integer removedValue = stu.removeFirst();
        errorMsg = String.format("addFirst(%d)\n"+
                                 "removeFirst(): %d\n"+
                                 "isEmpty()",
                                 value, removedValue);
        assertTrue(errorMsg, stu.isEmpty());
    }

    @Test
    public void testStudentArrayDeque() {
        testAddFirst();
        testAddLast();
        testIsEmpty();
    }
}
