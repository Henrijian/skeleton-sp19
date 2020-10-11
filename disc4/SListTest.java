import org.junit.Test;
import static org.junit.Assert.*;

public class SListTest {
    @Test
    public void test() {
        SList L1 = new SList();
        L1.insertFront(123);

        SList L2 = new SList();
        L2.insertFront(555);

        assertNotEquals(L2.getFront(), L1.getFront());
        assertEquals(123, L1.getFront());
    }
}
