package byow.Shape;

import org.junit.Assert;
import org.junit.Test;

import java.awt.*;

public class RectangleTest {
    @Test
    public void boundaryTest() {
        Rectangle rect = new Rectangle(0, 0, 2, 3);
        Boundary boundary = rect.boundary();
        Assert.assertEquals(0, boundary.minX);
        Assert.assertEquals(0, boundary.minY);
        Assert.assertEquals(2, boundary.maxX);
        Assert.assertEquals(3, boundary.maxY);
    }

    @Test
    public void centerTest() {
        Rectangle rect = new Rectangle(-1, -1, 2, 2);
        Point center = rect.center();
        Assert.assertEquals(0, center.x);
        Assert.assertEquals(0, center.y);
    }

    @Test
    public void relationTest() {
        Rectangle rect = new Rectangle(-2, -2, 4, 4);
        // Disjoint
        Rectangle disjointRect = new Rectangle(3, 3, 1, 1);
        Assert.assertEquals(Relation.DISJOINT, rect.relationTo(disjointRect));
        // Intersection
        Rectangle intersectRect = new Rectangle(1, 1, 2, 2);
        Assert.assertEquals(Relation.INTERSECT, rect.relationTo(intersectRect));
        // A in B
        Rectangle outerRect = new Rectangle(-3, -3, 6, 6);
        Assert.assertEquals(Relation.A_IN_B, rect.relationTo(outerRect));
        // B in A
        Rectangle innerRect = new Rectangle(-1, -1 , 2, 2);
        Assert.assertEquals(Relation.B_IN_A, rect.relationTo(innerRect));
    }

    @Test
    public void directionTest() {
        final int LEFT_X = -1;
        final int BOTTOM_Y = -1;
        final int WIDTH = 2;
        final int HEIGHT = 2;
        Rectangle rect = new Rectangle(LEFT_X, BOTTOM_Y, WIDTH, HEIGHT);
        // Top
        Rectangle topRect = new Rectangle(LEFT_X, BOTTOM_Y + HEIGHT + 1 , WIDTH, HEIGHT);
        Assert.assertEquals(Direction.TOP, rect.directionTo(topRect));
        // Right top
        Rectangle rightTopRect = new Rectangle(LEFT_X + WIDTH + 1, BOTTOM_Y + HEIGHT + 1, WIDTH, HEIGHT);
        Assert.assertEquals(Direction.RIGHT_TOP, rect.directionTo(rightTopRect));
        // Right
        Rectangle rightRect = new Rectangle(LEFT_X + WIDTH + 1, BOTTOM_Y, WIDTH, HEIGHT);
        Assert.assertEquals(Direction.RIGHT, rect.directionTo(rightRect));
        // Right bottom
        Rectangle rightBottomRect = new Rectangle(LEFT_X + WIDTH + 1, BOTTOM_Y - HEIGHT - 1, WIDTH, HEIGHT);
        Assert.assertEquals(Direction.RIGHT_BOTTOM, rect.directionTo(rightBottomRect));
        // Bottom
        Rectangle bottomRect = new Rectangle(LEFT_X, BOTTOM_Y - HEIGHT - 1,WIDTH, HEIGHT);
        Assert.assertEquals(Direction.BOTTOM, rect.directionTo(bottomRect));
        // Left bottom
        Rectangle leftBottomRect = new Rectangle(LEFT_X - WIDTH - 1, BOTTOM_Y - HEIGHT - 1, WIDTH, HEIGHT);
        Assert.assertEquals(Direction.LEFT_BOTTOM, rect.directionTo(leftBottomRect));
        // Left
        Rectangle leftRect = new Rectangle(LEFT_X - WIDTH - 1, BOTTOM_Y, WIDTH, HEIGHT);
        Assert.assertEquals(Direction.LEFT, rect.directionTo(leftRect));
        // Left Top
        Rectangle leftTopRect = new Rectangle(LEFT_X - WIDTH - 1, BOTTOM_Y + HEIGHT + 1, WIDTH, HEIGHT);
        Assert.assertEquals(Direction.LEFT_TOP, rect.directionTo(leftTopRect));
    }

    @Test
    public void distanceTest() {
        final double PRECISION = 0.0000000001;
        final int LEFT_X = -1;
        final int BOTTOM_Y = -1;
        final int WIDTH = 2;
        final int HEIGHT = 2;
        Rectangle rect = new Rectangle(LEFT_X, BOTTOM_Y, WIDTH, HEIGHT);
        // myself
        Assert.assertEquals(-1, rect.distanceTo(rect), PRECISION);
        // Top
        Rectangle topRect = new Rectangle(LEFT_X, BOTTOM_Y + HEIGHT + 1 , WIDTH, HEIGHT);
        Assert.assertEquals(1, rect.distanceTo(topRect), PRECISION);
        // Right top
        Rectangle rightTopRect = new Rectangle(LEFT_X + WIDTH + 1, BOTTOM_Y + HEIGHT + 1, WIDTH, HEIGHT);
        Assert.assertEquals(Math.sqrt(2), rect.distanceTo(rightTopRect), PRECISION);
        // Right
        Rectangle rightRect = new Rectangle(LEFT_X + WIDTH + 1, BOTTOM_Y, WIDTH, HEIGHT);
        Assert.assertEquals(1, rect.distanceTo(rightRect), PRECISION);
        // Right bottom
        Rectangle rightBottomRect = new Rectangle(LEFT_X + WIDTH + 1, BOTTOM_Y - HEIGHT - 1, WIDTH, HEIGHT);
        Assert.assertEquals(Math.sqrt(2), rect.distanceTo(rightBottomRect), PRECISION);
        // Bottom
        Rectangle bottomRect = new Rectangle(LEFT_X, BOTTOM_Y - HEIGHT - 1,WIDTH, HEIGHT);
        Assert.assertEquals(1, rect.distanceTo(bottomRect), PRECISION);
        // Left bottom
        Rectangle leftBottomRect = new Rectangle(LEFT_X - WIDTH - 1, BOTTOM_Y - HEIGHT - 1, WIDTH, HEIGHT);
        Assert.assertEquals(Math.sqrt(2), rect.distanceTo(leftBottomRect), PRECISION);
        // Left
        Rectangle leftRect = new Rectangle(LEFT_X - WIDTH - 1, BOTTOM_Y, WIDTH, HEIGHT);
        Assert.assertEquals(1, rect.distanceTo(leftRect), PRECISION);
        // Left Top
        Rectangle leftTopRect = new Rectangle(LEFT_X - WIDTH - 1, BOTTOM_Y + HEIGHT + 1, WIDTH, HEIGHT);
        Assert.assertEquals(Math.sqrt(2), rect.distanceTo(leftTopRect), PRECISION);
    }

    @Test
    public void closestPointTest() {
        final int LEFT_X = -1;
        final int BOTTOM_Y = -1;
        final int WIDTH = 2;
        final int HEIGHT = 2;
        Rectangle rect = new Rectangle(LEFT_X, BOTTOM_Y, WIDTH, HEIGHT);
        // myself
        Assert.assertEquals(null, rect.closestPointTo(rect));
        // Top
        Rectangle topRect = new Rectangle(LEFT_X + 1, BOTTOM_Y + HEIGHT + 1 , WIDTH, HEIGHT);
        Point topClosestPoint = new Point(topRect.leftBottomCorner.x, rect.leftBottomCorner.y+rect.height);
        Assert.assertEquals(topClosestPoint, rect.closestPointTo(topRect));
        // Right top
        Rectangle rightTopRect = new Rectangle(LEFT_X + WIDTH + 1, BOTTOM_Y + HEIGHT + 1, WIDTH, HEIGHT);
        Point rightTopClosestPoint = new Point(rect.leftBottomCorner.x+rect.width, rect.leftBottomCorner.y+rect.height);
        Assert.assertEquals(rightTopClosestPoint, rect.closestPointTo(rightTopRect));
        // Right
        Rectangle rightRect = new Rectangle(LEFT_X + WIDTH + 1, BOTTOM_Y + 1, WIDTH, HEIGHT);
        Point rightClosestPoint = new Point(rect.leftBottomCorner.x+rect.width, rightRect.leftBottomCorner.y);
        Assert.assertEquals(rightClosestPoint, rect.closestPointTo(rightRect));
        // Right bottom
        Rectangle rightBottomRect = new Rectangle(LEFT_X + WIDTH + 1, BOTTOM_Y - HEIGHT - 1, WIDTH, HEIGHT);
        Point rightBottomClosestPoint = new Point(rect.leftBottomCorner.x+rect.width, rect.leftBottomCorner.y);
        Assert.assertEquals(rightBottomClosestPoint, rect.closestPointTo(rightBottomRect));
        // Bottom
        Rectangle bottomRect = new Rectangle(LEFT_X + 1, BOTTOM_Y - HEIGHT - 1,WIDTH, HEIGHT);
        Point bottomClosestPoint = new Point(bottomRect.leftBottomCorner.x, rect.leftBottomCorner.y);
        Assert.assertEquals(bottomClosestPoint, rect.closestPointTo(bottomRect));
        // Left bottom
        Rectangle leftBottomRect = new Rectangle(LEFT_X - WIDTH - 1, BOTTOM_Y - HEIGHT - 1, WIDTH, HEIGHT);
        Point leftBottomClosestPoint = rect.leftBottomCorner;
        Assert.assertEquals(leftBottomClosestPoint, rect.closestPointTo(leftBottomRect));
        // Left
        Rectangle leftRect = new Rectangle(LEFT_X - WIDTH - 1, BOTTOM_Y + 1, WIDTH, HEIGHT);
        Point leftClosestPoint = new Point(rect.leftBottomCorner.x, leftRect.leftBottomCorner.y);
        Assert.assertEquals(leftClosestPoint, rect.closestPointTo(leftRect));
        // Left Top
        Rectangle leftTopRect = new Rectangle(LEFT_X - WIDTH - 1, BOTTOM_Y + HEIGHT + 1, WIDTH, HEIGHT);
        Point leftTopClosestPoint = new Point(rect.leftBottomCorner.x, rect.leftBottomCorner.y+rect.height);
        Assert.assertEquals(leftTopClosestPoint, rect.closestPointTo(leftTopRect));
    }
}
