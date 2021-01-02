package bearmaps;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class KDTreeTest {
    @Test
    public void constructorTest() {
        Point p1 = new Point(1.1, 2.2); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(3.3, 4.4);
        Point p3 = new Point(-2.9, 4.2);

        KDTree pointSet = new KDTree(List.of(p1, p2, p3));
    }

    @Test
    public void nearestTest() {
        Point p1 = new Point(1.1, 2.2); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(3.3, 4.4);
        Point p3 = new Point(-2.9, 4.2);

        KDTree pointSet = new KDTree(List.of(p1, p2, p3));
        Point nearest = pointSet.nearest(3.0, 4.0); // returns p2
        Assert.assertEquals(p2.getX(), nearest.getX(), 0);
        Assert.assertEquals(p2.getY(), nearest.getY(), 0);
    }
}
