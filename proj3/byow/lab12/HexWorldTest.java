package byow.lab12;

import org.junit.Assert;
import org.junit.Test;

import java.awt.*;

public class HexWorldTest {
    @Test
    public void hexagonHeightTest() {
        Point lowerLeftPt = new Point(0, 0);
        Assert.assertEquals(2, new HexWorld.Hexagon(lowerLeftPt, 1).height());
        Assert.assertEquals(4, new HexWorld.Hexagon(lowerLeftPt, 2).height());
        Assert.assertEquals(6, new HexWorld.Hexagon(lowerLeftPt, 3).height());
        Assert.assertEquals(8, new HexWorld.Hexagon(lowerLeftPt, 4).height());
        Assert.assertEquals(10, new HexWorld.Hexagon(lowerLeftPt, 5).height());
    }

    @Test
    public void hexagonWidthTest() {
        Point lowerLeftPt = new Point(0, 0);
        Assert.assertEquals(1, new HexWorld.Hexagon(lowerLeftPt, 1).width());
        Assert.assertEquals(4, new HexWorld.Hexagon(lowerLeftPt, 2).width());
        Assert.assertEquals(7, new HexWorld.Hexagon(lowerLeftPt, 3).width());
        Assert.assertEquals(10, new HexWorld.Hexagon(lowerLeftPt, 4).width());
        Assert.assertEquals(13, new HexWorld.Hexagon(lowerLeftPt, 5).width());
    }

    @Test
    public void hexagonVertStartXTest() {
        // Start point at (0, 0)
        Point startPt = new Point(0, 0);
        // Test hexagon with size 1
        HexWorld.Hexagon hex1 = new HexWorld.Hexagon(startPt, 1);
        Assert.assertEquals(0, hex1.vertStartX(0));
        Assert.assertEquals(0, hex1.vertStartX(1));
        // Test hexagon with size 2
        HexWorld.Hexagon hex2 = new HexWorld.Hexagon(startPt, 2);
        Assert.assertEquals(1, hex2.vertStartX(0));
        Assert.assertEquals(0, hex2.vertStartX(1));
        Assert.assertEquals(0, hex2.vertStartX(2));
        Assert.assertEquals(1, hex2.vertStartX(3));
        // Test hexagon with size 3
        HexWorld.Hexagon hex3 = new HexWorld.Hexagon(startPt, 3);
        Assert.assertEquals(2, hex3.vertStartX(0));
        Assert.assertEquals(1, hex3.vertStartX(1));
        Assert.assertEquals(0, hex3.vertStartX(2));
        Assert.assertEquals(0, hex3.vertStartX(3));
        Assert.assertEquals(1, hex3.vertStartX(4));
        Assert.assertEquals(2, hex3.vertStartX(5));
        // Test hexagon with size 4
        HexWorld.Hexagon hex4 = new HexWorld.Hexagon(startPt, 4);
        Assert.assertEquals(3, hex4.vertStartX(0));
        Assert.assertEquals(2, hex4.vertStartX(1));
        Assert.assertEquals(1, hex4.vertStartX(2));
        Assert.assertEquals(0, hex4.vertStartX(3));
        Assert.assertEquals(0, hex4.vertStartX(4));
        Assert.assertEquals(1, hex4.vertStartX(5));
        Assert.assertEquals(2, hex4.vertStartX(6));
        Assert.assertEquals(3, hex4.vertStartX(7));
    }

    @Test
    public void hexagonVertEndXTest() {
        // Start point at (0, 0)
        Point startPt = new Point(0, 0);
        // Test hexagon with size 1
        HexWorld.Hexagon hex1 = new HexWorld.Hexagon(startPt, 1);
        Assert.assertEquals(0, hex1.vertEndX(0));
        Assert.assertEquals(0, hex1.vertEndX(1));
        // Test hexagon with size 2
        HexWorld.Hexagon hex2 = new HexWorld.Hexagon(startPt, 2);
        Assert.assertEquals(2, hex2.vertEndX(0));
        Assert.assertEquals(3, hex2.vertEndX(1));
        Assert.assertEquals(3, hex2.vertEndX(2));
        Assert.assertEquals(2, hex2.vertEndX(3));
        // Test hexagon with size 3
        HexWorld.Hexagon hex3 = new HexWorld.Hexagon(startPt, 3);
        Assert.assertEquals(4, hex3.vertEndX(0));
        Assert.assertEquals(5, hex3.vertEndX(1));
        Assert.assertEquals(6, hex3.vertEndX(2));
        Assert.assertEquals(6, hex3.vertEndX(3));
        Assert.assertEquals(5, hex3.vertEndX(4));
        Assert.assertEquals(4, hex3.vertEndX(5));
        // Test hexagon with size 4
        HexWorld.Hexagon hex4 = new HexWorld.Hexagon(startPt, 4);
        Assert.assertEquals(6, hex4.vertEndX(0));
        Assert.assertEquals(7, hex4.vertEndX(1));
        Assert.assertEquals(8, hex4.vertEndX(2));
        Assert.assertEquals(9, hex4.vertEndX(3));
        Assert.assertEquals(9, hex4.vertEndX(4));
        Assert.assertEquals(8, hex4.vertEndX(5));
        Assert.assertEquals(7, hex4.vertEndX(6));
        Assert.assertEquals(6, hex4.vertEndX(7));
    }
}
