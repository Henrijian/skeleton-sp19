import static org.junit.Assert.*;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TestLinkedListDeque {
    @Test
    public void testSize() {
//        LinkedListDeque<Integer> input = new LinkedListDeque<Integer>(1, 2, 3, 4, 5);
//        int actual = input.size();
//        int expected = 5;
//        assertEquals(expected, actual);
    }

    @Test
    public void testEquals() {
//        LinkedListDeque<Integer> input = new LinkedListDeque<Integer>(5, 10 ,15);
//        LinkedListDeque<Integer> actual = input;
//        LinkedListDeque<Integer> expected = new LinkedListDeque<Integer>(5, 10, 15);
//        assertTrue(expected.equals(actual));
    }

    @Test
    public void testAddFirst() {
//        LinkedListDeque<Integer> input1 = new LinkedListDeque<Integer>(1, 2, 3, 4, 5);
//        input1.addFirst(0);
//        LinkedListDeque<Integer>actual1 = input1;
//        LinkedListDeque<Integer> expected1 = new LinkedListDeque<Integer>(0, 1, 2, 3, 4, 5);
//        assertTrue(expected1.equals(actual1));
//
//        LinkedListDeque<Integer> input2 = new LinkedListDeque<Integer>(8, 10);
//        input2.addFirst(6);
//        input2.addFirst(4);
//        input2.addFirst(2);
//        LinkedListDeque<Integer>actual2 = input2;
//        LinkedListDeque<Integer> expected2 = new LinkedListDeque<Integer>(2, 4, 6, 8, 10);
//        assertTrue(expected2.equals(actual2));
    }

    @Test
    public void testAddLast() {
//        LinkedListDeque<Integer> input1 = new LinkedListDeque<Integer>(1, 2, 3, 4, 5);
//        input1.addLast(6);
//        LinkedListDeque<Integer>actual1 = input1;
//        LinkedListDeque<Integer> expected1 = new LinkedListDeque<Integer>(1, 2, 3, 4, 5, 6);
//        assertTrue(expected1.equals(actual1));
//
//        LinkedListDeque<Integer> input2 = new LinkedListDeque<Integer>(2, 4);
//        input2.addLast(6);
//        input2.addLast(8);
//        input2.addLast(10);
//        LinkedListDeque<Integer>actual2 = input2;
//        LinkedListDeque<Integer> expected2 = new LinkedListDeque<Integer>(2, 4, 6, 8, 10);
//        assertTrue(expected2.equals(actual2));
    }

    @Test
    public void testIsEmpty() {
//        LinkedListDeque<Integer> input1 = new LinkedListDeque<Integer>();
//        assertTrue(input1.isEmpty());
//
//        LinkedListDeque<Integer> input2 = new LinkedListDeque<Integer>(1, 2, 3);
//        assertFalse(input2.isEmpty());
    }

    @Test
    public void testPrintDeque() {
//        ByteArrayOutputStream outContent1 = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outContent1));
//        PrintStream originalOut = System.out;
//        try {
//            outContent1.reset();
//            LinkedListDeque<Integer> input1 = new LinkedListDeque<Integer>(1, 2, 3, 4, 5);
//            input1.printDeque();
//            assertEquals("1 2 3 4 5\n", outContent1.toString());
//
//            outContent1.reset();
//            LinkedListDeque<String> input2 = new LinkedListDeque<String>("My", "name", "is", "Henri");
//            input2.printDeque();
//            assertEquals("My name is Henri\n", outContent1.toString());
//        } finally {
//            System.setOut(originalOut);
//        }
    }

    @Test
    public void testRemoveFirst() {
//        LinkedListDeque<Integer> input1 = new LinkedListDeque<Integer>(1, 2, 3, 4, 5);
//        Integer actual1 = input1.removeFirst();
//        Integer expected1 = 1;
//        assertEquals(expected1, actual1);
//        assertEquals(4, input1.size());
//
//        LinkedListDeque<Integer> input2 = new LinkedListDeque<Integer>();
//        Integer actual2 = input2.removeFirst();
//        Integer expected2 = null;
//        assertEquals(expected2, actual2);
//        assertEquals(0, input2.size());
    }

    @Test
    public void testRemoveLast() {
//        LinkedListDeque<Integer> input1 = new LinkedListDeque<Integer>(1, 2, 3, 4, 5);
//        Integer actual1 = input1.removeLast();
//        Integer expected1 = 5;
//        assertEquals(expected1, actual1);
//        assertEquals(4, input1.size());
//
//        LinkedListDeque<Integer> input2 = new LinkedListDeque<Integer>();
//        Integer actual2 = input2.removeLast();
//        Integer expected2 = null;
//        assertEquals(expected2, actual2);
//        assertEquals(0, input2.size());
    }

    @Test
    public void testGet() {
//        LinkedListDeque<Integer> input1 = new LinkedListDeque<Integer>(1, 2, 3, 4, 5);
//        Integer actual1 = input1.get(2);
//        Integer expected1 = 3;
//        assertEquals(expected1, actual1);
//
//        LinkedListDeque<Integer> input2 = new LinkedListDeque<Integer>(2, 4, 6, 8);
//        Integer actual2 = input2.get(4);
//        Integer expected2 = null;
//        assertEquals(expected2, actual2);
    }

    @Test
    public void testGetRecursive() {
//        LinkedListDeque<Integer> input1 = new LinkedListDeque<Integer>(1, 2, 3, 4, 5);
//        Integer actual1 = input1.getRecursive(2);
//        Integer expected1 = 3;
//        assertEquals(expected1, actual1);
//
//        LinkedListDeque<Integer> input2 = new LinkedListDeque<Integer>(2, 4, 6, 8);
//        Integer actual2 = input2.getRecursive(4);
//        Integer expected2 = null;
//        assertEquals(expected2, actual2);
    }

    @Test
    public void testLinkedListDeque() {
//        LinkedListDeque<Integer> input1 = new LinkedListDeque<Integer>(1, 2, 3, 4, 5);
//        LinkedListDeque<Integer> actual1 = new LinkedListDeque<Integer>(input1);
//        LinkedListDeque<Integer> expected1 = new LinkedListDeque<Integer>(1, 2, 3, 4, 5);
//        assertTrue(expected1.equals(actual1));
    }
}
