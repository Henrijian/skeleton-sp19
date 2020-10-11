import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    // You must use this CharacterComparator and not instantiate
    // new ones.
    static CharacterComparator offByN = new OffByN(5);

    @Test
    public void testEqualsChars() {
        assertTrue(offByN.equalChars('a', 'f'));
        assertTrue(offByN.equalChars('f', 'a'));
        assertFalse(offByN.equalChars('a', 'a'));
        assertFalse(offByN.equalChars('a', 'g'));
        assertFalse(offByN.equalChars('g', 'a'));
    }
}
