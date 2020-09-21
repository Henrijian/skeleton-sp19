import static org.junit.Assert.*;
import org.junit.Test;

public class TestFlatten {
    @Test
    public void TestFlatten() {
        int[][] input = {{1, 2, 3}, {}, {4, 5, 6}};
        int[] actual = Flatten.flatten(input);
        int[] expected = {1, 2, 3, 4, 5, 6};
        assertArrayEquals(expected, actual);
    }
}
