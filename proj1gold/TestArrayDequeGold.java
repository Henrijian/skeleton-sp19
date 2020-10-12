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

    /** Test isEmpty function of Deque.*/
    public void testIsEmpty() {
        callStack.clear();

        StudentArrayDeque<Integer> stu;
        String errorMsg;
        final int valueBound = 10000;

        stu = new StudentArrayDeque<>();
        callStack.add("new StudentArrayDeque<>()");

        errorMsg = String.format("isEmpty(): %b", stu.isEmpty());
        callStack.add(errorMsg);
        assertTrue(callStackMessage(), stu.isEmpty());

        Integer value = StdRandom.uniform(valueBound);
        stu.addFirst(value);
        errorMsg = String.format("addFirst(%d)", value);
        callStack.add(errorMsg);

        errorMsg = String.format("isEmpty(): %b", stu.isEmpty());
        callStack.add(errorMsg);
        assertFalse(errorMsg, stu.isEmpty());

        Integer removedValue = stu.removeFirst();
        errorMsg = String.format("removeFirst(): %d", removedValue);
        callStack.add(errorMsg);

        errorMsg = String.format("isEmpty(): %b", stu.isEmpty());
        callStack.add(errorMsg);
        assertTrue(errorMsg, stu.isEmpty());
    }

    /** Test size function of Deque.*/
    public void testSize() {
        callStack.clear();

        final int size = 1000;
        final int valueBound = 10000;
        int actualSize;
        int expectedSize;
        String stackMsg;
        StudentArrayDeque<Integer> stu;

        stu = new StudentArrayDeque<>();
        callStack.add("new StudentArrayDeque<>()");

        actualSize = stu.size();
        expectedSize = 0;
        stackMsg = String.format("size(): %d", actualSize);
        callStack.add(stackMsg);
        assertEquals(callStackMessage(), expectedSize, actualSize);

        for (int i = 0; i < size; i += 1) {
            int value = StdRandom.uniform(valueBound);
            stu.addLast(value);
            stackMsg = String.format("addLast(%d)", value);
            callStack.add(stackMsg);
        }

        actualSize = stu.size();
        expectedSize = size;
        stackMsg = String.format("size(): %d", actualSize);
        callStack.add(stackMsg);
        assertEquals(callStackMessage(), expectedSize, actualSize);

        for (int i = 0; i < size; i += 1) {
            Integer removedValue = stu.removeLast();
            stackMsg = String.format("removeLast(): %d", removedValue);
            callStack.add(stackMsg);
        }

        actualSize = stu.size();
        expectedSize = 0;
        stackMsg = String.format("size(): %d", actualSize);
        callStack.add(stackMsg);
        assertEquals(callStackMessage(), expectedSize, actualSize);

        for (int i = 0; i < size; i += 1) {
            int value = StdRandom.uniform(valueBound);
            stu.addFirst(value);
            stackMsg = String.format("addFirst(%d)", value);
            callStack.add(stackMsg);
        }

        actualSize = stu.size();
        expectedSize = size;
        stackMsg = String.format("size(): %d", actualSize);
        callStack.add(stackMsg);
        assertEquals(callStackMessage(), expectedSize, actualSize);

        for (int i = 0; i < size; i += 1) {
            Integer removedValue = stu.removeFirst();
            stackMsg = String.format("removeFirst(): %d", removedValue);
            callStack.add(stackMsg);
        }

        actualSize = stu.size();
        expectedSize = 0;
        stackMsg = String.format("size(): %d", actualSize);
        callStack.add(stackMsg);
        assertEquals(callStackMessage(), expectedSize, actualSize);
    }


    @Test
    public void testStudentArrayDeque() {
        testAddFirst();
        testAddLast();
        testIsEmpty();
        testSize();
    }
}
