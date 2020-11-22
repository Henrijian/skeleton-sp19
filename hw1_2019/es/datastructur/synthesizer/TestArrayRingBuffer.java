package es.datastructur.synthesizer;
import edu.princeton.cs.introcs.StdAudio;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void testBasic() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer(10);
        // test capacity
        assertEquals(10, arb.capacity());
        // test enqueue
        for (int i = 0; i < 10; i++) {
            arb.enqueue(i);
        }
        // test peek
        assertEquals((Integer)0, arb.peek());
        // test dequeue
        assertEquals((Integer)0, arb.dequeue());
        // test filled count
        assertEquals(9, arb.fillCount());
    }
}
