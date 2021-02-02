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
    public void hexagonStartXTest() {
        // Start point at (0, 0)
        Point startPt = new Point(0, 0);
        // Test hexagon with side 1
        int level1 = 1;
        Assert.assertEquals(0, HexWorld.hexagonStartX(startPt, level1, 0));
        Assert.assertEquals(0, HexWorld.hexagonStartX(startPt, level1, 1));
        // Test hexagon with side 2
        int level2 = 2;
        Assert.assertEquals(1, HexWorld.hexagonStartX(startPt, level2, 0));
        Assert.assertEquals(0, HexWorld.hexagonStartX(startPt, level2, 1));
        Assert.assertEquals(0, HexWorld.hexagonStartX(startPt, level2, 2));
        Assert.assertEquals(1, HexWorld.hexagonStartX(startPt, level2, 3));
        // Test hexagon with side 3
        int level3 = 3;
        Assert.assertEquals(2, HexWorld.hexagonStartX(startPt, level3, 0));
        Assert.assertEquals(1, HexWorld.hexagonStartX(startPt, level3, 1));
        Assert.assertEquals(0, HexWorld.hexagonStartX(startPt, level3, 2));
        Assert.assertEquals(0, HexWorld.hexagonStartX(startPt, level3, 3));
        Assert.assertEquals(1, HexWorld.hexagonStartX(startPt, level3, 4));
        Assert.assertEquals(2, HexWorld.hexagonStartX(startPt, level3, 5));
        // Test hexagon with side 4
        int level4 = 4;
        Assert.assertEquals(3, HexWorld.hexagonStartX(startPt, level4, 0));
        Assert.assertEquals(2, HexWorld.hexagonStartX(startPt, level4, 1));
        Assert.assertEquals(1, HexWorld.hexagonStartX(startPt, level4, 2));
        Assert.assertEquals(0, HexWorld.hexagonStartX(startPt, level4, 3));
        Assert.assertEquals(0, HexWorld.hexagonStartX(startPt, level4, 4));
        Assert.assertEquals(1, HexWorld.hexagonStartX(startPt, level4, 5));
        Assert.assertEquals(2, HexWorld.hexagonStartX(startPt, level4, 6));
        Assert.assertEquals(3, HexWorld.hexagonStartX(startPt, level4, 7));
    }

    @Test
    public void hexagonEndPtTest() {
        // Start point at (0, 0)
        Point startPt = new Point(0, 0);
        // Test hexagon with side 1
        int level1 = 1;
        Assert.assertEquals(0, HexWorld.hexagonEndX(startPt, level1, 0));
        Assert.assertEquals(0, HexWorld.hexagonEndX(startPt, level1, 1));
        // Test hexagon with side 2
        int level2 = 2;
        Assert.assertEquals(2, HexWorld.hexagonEndX(startPt, level2, 0));
        Assert.assertEquals(3, HexWorld.hexagonEndX(startPt, level2, 1));
        Assert.assertEquals(3, HexWorld.hexagonEndX(startPt, level2, 2));
        Assert.assertEquals(2, HexWorld.hexagonEndX(startPt, level2, 3));
        // Test hexagon with side 3
        int level3 = 3;
        Assert.assertEquals(4, HexWorld.hexagonEndX(startPt, level3, 0));
        Assert.assertEquals(5, HexWorld.hexagonEndX(startPt, level3, 1));
        Assert.assertEquals(6, HexWorld.hexagonEndX(startPt, level3, 2));
        Assert.assertEquals(6, HexWorld.hexagonEndX(startPt, level3, 3));
        Assert.assertEquals(5, HexWorld.hexagonEndX(startPt, level3, 4));
        Assert.assertEquals(4, HexWorld.hexagonEndX(startPt, level3, 5));
        // Test hexagon with side 4
        int level4 = 4;
        Assert.assertEquals(6, HexWorld.hexagonEndX(startPt, level4, 0));
        Assert.assertEquals(7, HexWorld.hexagonEndX(startPt, level4, 1));
        Assert.assertEquals(8, HexWorld.hexagonEndX(startPt, level4, 2));
        Assert.assertEquals(9, HexWorld.hexagonEndX(startPt, level4, 3));
        Assert.assertEquals(9, HexWorld.hexagonEndX(startPt, level4, 4));
        Assert.assertEquals(8, HexWorld.hexagonEndX(startPt, level4, 5));
        Assert.assertEquals(7, HexWorld.hexagonEndX(startPt, level4, 6));
        Assert.assertEquals(6, HexWorld.hexagonEndX(startPt, level4, 7));
    }
}
