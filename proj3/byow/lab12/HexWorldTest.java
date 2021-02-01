package byow.lab12;


import org.junit.Assert;
import org.junit.Test;

import java.awt.*;

public class HexWorldTest {
    @Test
    public void hexagonHeightTest() {
        Assert.assertEquals(2, HexWorld.hexagonHeight(1));
        Assert.assertEquals(4, HexWorld.hexagonHeight(2));
        Assert.assertEquals(6, HexWorld.hexagonHeight(3));
        Assert.assertEquals(8, HexWorld.hexagonHeight(4));
        Assert.assertEquals(10, HexWorld.hexagonHeight(5));
    }

    @Test
    public void hexagonWidthTest() {
        Assert.assertEquals(1, HexWorld.hexagonWidth(1));
        Assert.assertEquals(4, HexWorld.hexagonWidth(2));
        Assert.assertEquals(7, HexWorld.hexagonWidth(3));
        Assert.assertEquals(10, HexWorld.hexagonWidth(4));
        Assert.assertEquals(13, HexWorld.hexagonWidth(5));
    }

    @Test
    public void hexagonStartPtTest() {
        // Start point at (0, 0)
        Point startPt = new Point(0, 0);
        // Test hexagon with side 1
        int level1 = 1;
        Assert.assertEquals(new Point(0, 0), HexWorld.hexagonStartPt(startPt, level1, 0));
        Assert.assertEquals(new Point(0, 1), HexWorld.hexagonStartPt(startPt, level1, 1));
        // Test hexagon with side 2
        int level2 = 2;
        Assert.assertEquals(new Point(1, 0), HexWorld.hexagonStartPt(startPt, level2, 0));
        Assert.assertEquals(new Point(0, 1), HexWorld.hexagonStartPt(startPt, level2, 1));
        Assert.assertEquals(new Point(0, 2), HexWorld.hexagonStartPt(startPt, level2, 2));
        Assert.assertEquals(new Point(1, 3), HexWorld.hexagonStartPt(startPt, level2, 3));
        // Test hexagon with side 3
        int level3 = 3;
        Assert.assertEquals(new Point(2, 0), HexWorld.hexagonStartPt(startPt, level3, 0));
        Assert.assertEquals(new Point(1, 1), HexWorld.hexagonStartPt(startPt, level3, 1));
        Assert.assertEquals(new Point(0, 2), HexWorld.hexagonStartPt(startPt, level3, 2));
        Assert.assertEquals(new Point(0, 3), HexWorld.hexagonStartPt(startPt, level3, 3));
        Assert.assertEquals(new Point(1, 4), HexWorld.hexagonStartPt(startPt, level3, 4));
        Assert.assertEquals(new Point(2, 5), HexWorld.hexagonStartPt(startPt, level3, 5));
        // Test hexagon with side 4
        int level4 = 4;
        Assert.assertEquals(new Point(3, 0), HexWorld.hexagonStartPt(startPt, level4, 0));
        Assert.assertEquals(new Point(2, 1), HexWorld.hexagonStartPt(startPt, level4, 1));
        Assert.assertEquals(new Point(1, 2), HexWorld.hexagonStartPt(startPt, level4, 2));
        Assert.assertEquals(new Point(0, 3), HexWorld.hexagonStartPt(startPt, level4, 3));
        Assert.assertEquals(new Point(0, 4), HexWorld.hexagonStartPt(startPt, level4, 4));
        Assert.assertEquals(new Point(1, 5), HexWorld.hexagonStartPt(startPt, level4, 5));
        Assert.assertEquals(new Point(2, 6), HexWorld.hexagonStartPt(startPt, level4, 6));
        Assert.assertEquals(new Point(3, 7), HexWorld.hexagonStartPt(startPt, level4, 7));
    }

    @Test
    public void hexagonEndPtTest() {
        // Start point at (0, 0)
        Point startPt = new Point(0, 0);
        // Test hexagon with side 1
        int level1 = 1;
        Assert.assertEquals(new Point(0, 0), HexWorld.hexagonEndPt(startPt, level1, 0));
        Assert.assertEquals(new Point(0, 1), HexWorld.hexagonEndPt(startPt, level1, 1));
        // Test hexagon with side 2
        int level2 = 2;
        Assert.assertEquals(new Point(2, 0), HexWorld.hexagonEndPt(startPt, level2, 0));
        Assert.assertEquals(new Point(3, 1), HexWorld.hexagonEndPt(startPt, level2, 1));
        Assert.assertEquals(new Point(3, 2), HexWorld.hexagonEndPt(startPt, level2, 2));
        Assert.assertEquals(new Point(2, 3), HexWorld.hexagonEndPt(startPt, level2, 3));
        // Test hexagon with side 3
        int level3 = 3;
        Assert.assertEquals(new Point(4, 0), HexWorld.hexagonEndPt(startPt, level3, 0));
        Assert.assertEquals(new Point(5, 1), HexWorld.hexagonEndPt(startPt, level3, 1));
        Assert.assertEquals(new Point(6, 2), HexWorld.hexagonEndPt(startPt, level3, 2));
        Assert.assertEquals(new Point(6, 3), HexWorld.hexagonEndPt(startPt, level3, 3));
        Assert.assertEquals(new Point(5, 4), HexWorld.hexagonEndPt(startPt, level3, 4));
        Assert.assertEquals(new Point(4, 5), HexWorld.hexagonEndPt(startPt, level3, 5));
        // Test hexagon with side 4
        int level4 = 4;
        Assert.assertEquals(new Point(6, 0), HexWorld.hexagonEndPt(startPt, level4, 0));
        Assert.assertEquals(new Point(7, 1), HexWorld.hexagonEndPt(startPt, level4, 1));
        Assert.assertEquals(new Point(8, 2), HexWorld.hexagonEndPt(startPt, level4, 2));
        Assert.assertEquals(new Point(9, 3), HexWorld.hexagonEndPt(startPt, level4, 3));
        Assert.assertEquals(new Point(9, 4), HexWorld.hexagonEndPt(startPt, level4, 4));
        Assert.assertEquals(new Point(8, 5), HexWorld.hexagonEndPt(startPt, level4, 5));
        Assert.assertEquals(new Point(7, 6), HexWorld.hexagonEndPt(startPt, level4, 6));
        Assert.assertEquals(new Point(6, 7), HexWorld.hexagonEndPt(startPt, level4, 7));
    }
}
