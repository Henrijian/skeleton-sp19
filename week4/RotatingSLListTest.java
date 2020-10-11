import org.junit.Test;
import static org.junit.Assert.*;

public class RotatingSLListTest {
    @Test
    public void testRotateRight() {
        RotatingSLList input1 = new RotatingSLList();
        input1.addLast(5);
        input1.addLast(9);
        input1.addLast(15);
        input1.addLast(12);
        RotatingSLList.rotateRight(input1);
        RotatingSLList actual1 = input1;
        RotatingSLList expected1 = new RotatingSLList();
        expected1.addLast(12);
        expected1.addLast(5);
        expected1.addLast(9);
        expected1.addLast(15);
        assertTrue(expected1.equals(actual1));
    }
}
