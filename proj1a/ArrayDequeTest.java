import org.junit.Test;
import static org.junit.Assert.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ArrayDequeTest {
    @Test
    public void testGet() {
        ArrayDeque<String> input1 = new ArrayDeque<String>("my", "name", "is", "henri");
        String actual1 = input1.get(3);
        String expected1 = "henri";
        assertEquals(expected1, actual1);

        ArrayDeque<String> input2 = new ArrayDeque<>();
        String actual2 = input2.get(0);
        String expected2 = null;
        assertEquals(expected2, actual2);
    }

    @Test
    public void testEquals() {
        ArrayDeque<String> input1 = new ArrayDeque<String>("Hello", "the", "world");
        ArrayDeque<String> input2 = new ArrayDeque<String>("Hello", "the", "world");
        assertTrue(input1.equals(input2));

        ArrayDeque<String> input3 = new ArrayDeque<String>("hello", "the", "world");
        assertFalse(input3.equals(input1));
    }

    @Test
    public void testArrayDeque() {
        ArrayDeque<String> input1 = new ArrayDeque<String>();
        int actual1 = input1.size();
        int expected1 = 0;
        assertEquals(expected1, actual1);

        ArrayDeque<String> input2 = new ArrayDeque<String>("my", "name", "is", "henri");
        ArrayDeque<String> input3 = new ArrayDeque<String>("my", "name", "is", "henri");
        assertTrue(input3.equals(input2));

        ArrayDeque<String> input4 = new ArrayDeque<String>(input3);
        assertTrue(input4.equals(input3));
    }

    @Test
    public void testAddFirst() {
        ArrayDeque<String> input1 = new ArrayDeque<String>("b", "c", "d");
        input1.addFirst("a");
        ArrayDeque<String> actual1 = input1;
        ArrayDeque<String> expected1 = new ArrayDeque<String>("a", "b", "c", "d");
        assertTrue(expected1.equals(actual1));

        int actual2 = actual1.size();
        int expected2 = expected1.size();
        assertEquals(expected2, actual2);
    }

    @Test
    public void testAddLast() {
        ArrayDeque<String> input1 = new ArrayDeque<String>("a", "b", "c");
        input1.addLast("d");
        ArrayDeque<String> actual1 = input1;
        ArrayDeque<String> expected1 = new ArrayDeque<String>("a", "b", "c", "d");
        assertTrue(expected1.equals(actual1));

        int actual2 = actual1.size();
        int expected2 = expected1.size();
        assertEquals(expected2, actual2);
    }

    @Test
    public void testIsEmpty() {
        ArrayDeque<String> input1 = new ArrayDeque<String>();
        assertTrue(input1.isEmpty());

        input1.addFirst("a");
        assertFalse(input1.isEmpty());

        ArrayDeque<String> input2 = new ArrayDeque<String>("a", "b", "c");
        assertFalse(input2.isEmpty());
    }

    @Test
    public void testSize() {
        ArrayDeque<String> input1 = new ArrayDeque<String>();
        int actual1 = input1.size();
        int expected1 = 0;
        assertEquals(expected1, actual1);

        input1.addFirst("a");
        int actual2 = input1.size();
        int expected2 = 1;
        assertEquals(expected2, actual2);

        ArrayDeque<String> input2 = new ArrayDeque<String>("a", "b", "c");
        int actual3 = input2.size();
        int expected3 = 3;
        assertEquals(expected3, actual3);
    }

    @Test
    public void testPrintDeque() {
        ByteArrayOutputStream outContent1 = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent1));
        try {
            outContent1.reset();
            ArrayDeque<String> input1 = new ArrayDeque<String>();
            input1.printDeque();
            assertEquals("\n", outContent1.toString());

            outContent1.reset();
            ArrayDeque<String> input2 = new ArrayDeque<String>("My", "name", "is", "Henri");
            input2.printDeque();
            assertEquals("My name is Henri\n", outContent1.toString());
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    public void testRemoveFirst() {
        ArrayDeque<String> input1 = new ArrayDeque<String>();
        String actual1 = input1.removeFirst();
        String expected1 = null;
        assertEquals(expected1, actual1);

        ArrayDeque<String> input2 = new ArrayDeque<String>("a", "b", "c");
        String actual2 = input2.removeFirst();
        String expected2 = "a";
        assertEquals(expected2, actual2);
        String actual3 = input2.removeFirst();
        String expected3 = "b";
        assertEquals(expected3, actual3);
        String actual4 = input2.removeFirst();
        String expected4 = "c";
        assertEquals(expected4, actual4);
    }

    @Test
    public void testRemoveLast() {
        ArrayDeque<String> input1 = new ArrayDeque<String>();
        String actual1 = input1.removeLast();
        String expected1 = null;
        assertEquals(expected1, actual1);

        ArrayDeque<String> input2 = new ArrayDeque<String>("a", "b", "c");
        String actual2 = input2.removeLast();
        String expected2 = "c";
        assertEquals(expected2, actual2);
        String actual3 = input2.removeLast();
        String expected3 = "b";
        assertEquals(expected3, actual3);
        String actual4 = input2.removeLast();
        String expected4 = "a";
        assertEquals(expected4, actual4);
    }

    @Test
    public void testArray() {
        ArrayDeque<Integer> L = new ArrayDeque<Integer>();
        L.addFirst(0);
        Integer removed = L.removeLast();
        L.addFirst(2);
        removed = L.removeLast();
        L.addFirst(4);
        removed = L.removeLast();
        L.addFirst(6);
        removed = L.removeLast();
        L.addFirst(8);

        ArrayDeque<Integer> L2 = new ArrayDeque<Integer>();
        L2.addLast(0);
        L2.addFirst(1);
        L2.addLast(2);
        removed = L2.removeFirst();
        L2.addLast(4);
        L2.addLast(5);
        removed = L2.removeFirst();
        for (int i = 1; i <= 1000; i++) {
            L.addFirst(i);
        }
        for (int i = 1; i <= 2000; i++) {
            L.removeFirst();
        }
        System.out.println("size: " + L.size());
        for (int i = 1; i <= 1000; i++) {
            L.addFirst(i);
        }
    }
}
