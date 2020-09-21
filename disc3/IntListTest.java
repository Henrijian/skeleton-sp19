import org.junit.Test;

import static org.junit.Assert.*;

public class IntListTest {

    /**
     * Example test that verifies correctness of the IntList.of static
     * method. The main point of this is to convince you that
     * assertEquals knows how to handle IntLists just fine.
     */

    @Test
    public void testList() {
        IntList one = new IntList(1, null);
        IntList twoOne = new IntList(2, one);
        IntList threeTwoOne = new IntList(3, twoOne);

        IntList x = IntList.of(3, 2, 1);
        assertEquals(threeTwoOne, x);
    }

    @Test
    public void testdSquareList() {
        IntList L = IntList.of(1, 2, 3);
        IntList.dSquareList(L);
        assertEquals(IntList.of(1, 4, 9), L);
    }

    /**
     * Do not use the new keyword in your tests. You can create
     * lists using the handy IntList.of method.
     * <p>
     * Make sure to include test cases involving lists of various sizes
     * on both sides of the operation. That includes the empty list, which
     * can be instantiated, for example, with
     * IntList empty = IntList.of().
     * <p>
     * Keep in mind that dcatenate(A, B) is NOT required to leave A untouched.
     * Anything can happen to A.
     */

    @Test
    public void testSquareListRecursive() {
        IntList L = IntList.of(1, 2, 3);
        IntList res = IntList.squareListRecursive(L);
        assertEquals(IntList.of(1, 2, 3), L);
        assertEquals(IntList.of(1, 4, 9), res);
    }

    @Test
    public void testDcatenate() {
        IntList A = IntList.of(1, 2, 3);
        IntList B = IntList.of(4, 5, 6);
        IntList exp = IntList.of(1, 2, 3, 4, 5, 6);
        assertEquals(exp, IntList.dcatenate(A, B));
        assertEquals(IntList.of(1, 2, 3, 4, 5, 6), A);
    }

    @Test
    public void testCatenate() {
        IntList A = IntList.of(1, 2, 3);
        IntList B = IntList.of(4, 5, 6);
        IntList exp = IntList.of(1, 2, 3, 4, 5, 6);
        assertEquals(exp, IntList.catenate(A, B));
        assertEquals(IntList.of(1, 2, 3), A);
    }

    @Test
    public void testReverse() {
        // test reverse works properly
        IntList input1 = IntList.of(1, 2, 3, 4, 5);
        IntList actual1 = IntList.reverse(input1);
        IntList expected1 = IntList.of(5, 4, 3, 2, 1);
        assertTrue(expected1.equals(actual1));

        // test reverse works destructively
        IntList input2 = IntList.of(2, 4, 6, 8, 10);
        IntList actual2 = IntList.reverse(input2);
        IntList expected2 = input2;
        assertNotEquals(expected2, actual2);

        // test null input
        IntList input3 = null;
        IntList actual3 = IntList.reverse(input3);
        IntList expected3 = null;
        assertTrue(expected3 == actual3);
    }

    @Test
    public void testSkippfy() {
        IntList input1 = IntList.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        input1.skippify();
        IntList actual1 = input1;
        IntList expected1 = IntList.of(1, 3, 6, 10);
        assertTrue(expected1.equals(actual1));

        IntList input2 = IntList.of(9, 8, 7, 6, 5, 4, 3, 2, 1);
        input2.skippify();
        IntList actual2 = input2;
        IntList expected2 = IntList.of(9, 7, 4);
        assertTrue(expected2.equals(actual2));
    }

    @Test
    public void testIlsans() {
        IntList input = IntList.of(1, 2, 2, 3, 4, 5, 2);
        IntList actual = IntList.ilsans(input, 2);
        IntList expected = IntList.of(1, 3, 4, 5);
        assertTrue(expected.equals(actual));
    }

    @Test
    public void testDilsans() {
        IntList input = IntList.of(1, 2, 2, 3, 4, 5, 2);
        IntList actual = IntList.dilsans(input, 2);
        IntList expected = IntList.of(1, 3, 4, 5);
        assertTrue(expected.equals(actual));
    }
    /** If you're running this from the command line, you'll need
      * to add a main method. See ArithmeticTest.java for an
      * example. */
}
