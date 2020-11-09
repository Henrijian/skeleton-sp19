import org.junit.Test;
import static org.junit.Assert.*;

public class WeightedQuickUnionPathCompressionUFTest {
    @Test
    public void basicTests () {
        int itemCount = 1000;
        WeightedQuickUnionPathCompressionUF disjointSets = new WeightedQuickUnionPathCompressionUF(itemCount);
        for (int i = 0; i < itemCount; i++) {
            for (int j = i+1; j < itemCount; j++) {
                assertFalse(disjointSets.connected(i, j));
                disjointSets.union(i, j);
                assertTrue(disjointSets.connected(i, j));
            }
        }
    }
}
