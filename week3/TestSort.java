import org.junit.Test;
import static org.junit.Assert.*;

public class TestSort {
    @Test
    public void testFindSmallest() {
        String[] input1 = {"i", "love", "chicken", "nugget"};
        int actual1 = Sort.findSmallest(input1, 0);
        int expected1 = 2;
        assertEquals(expected1, actual1);

        String[] input2 = {"my", "cat", "is", "lele"};
        int actual2 = Sort.findSmallest(input2, 2);
        int expected2 = 2;
        assertEquals(expected2, actual2);
    }

    @Test
    public void testSwap() {
        String[] input = {"my", "name", "is", "henri"};
        Sort.swap(input, 0, 2);
        String[] expected = {"is", "name", "my", "henri"};

        assertArrayEquals(expected, input);
    }

    @Test
    public void testSortStart() {
        String[] input = {"hello", "the", "beautiful", "world"};
        Sort.sortStart(input, 1);
        String[] expected = {"hello", "beautiful", "the", "world"};

        assertArrayEquals(expected, input);
    }

    @Test
    public void testSort() {
        String[] input = {"ok", "i", "hope", "this", "would", "pass"};
        Sort.sort(input);
        String[] expected = {"hope", "i", "ok", "pass", "this", "would"};

        assertArrayEquals(expected, input);
    }

    @Test
    public void testObjects() {
        Sort input1 = new Sort(1, "a", 1.0);
        Sort input2 = new Sort(1, "a", 1.0);
        assertEquals(input1, input2);
    }
}
