package byow.Entity;

import byow.Shape.Rectangle;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
import org.junit.Assert;
import org.junit.Test;

public class RectRoomTest {
    private static final TETile FLOOR = Tileset.FLOOR;
    private static final TETile WALL = Tileset.WALL;
    private static final TETile OPENED = FLOOR;
    private static final TETile CLOSED = WALL;

    @Test
    public void innerAreaTest() {
        RectRoom room = new RectRoom(0 , 0, 100, 50, FLOOR, WALL);
        Assert.assertEquals(5000, room.innerArea());
    }

    @Test
    public void wallAreaTest() {
        RectRoom room = new RectRoom(0 , 0, 100, 50, FLOOR, WALL);
        Assert.assertEquals(304, room.wallArea());
    }

    @Test
    public void outerAreaTest() {
        RectRoom room = new RectRoom(0 , 0, 100, 50, FLOOR, WALL);
        Assert.assertEquals(5304, room.outerArea());
    }

    @Test
    public void innerShapeTest() {
        RectRoom room = new RectRoom(0 , 0, 100, 50, FLOOR, WALL);
        Assert.assertEquals(new Rectangle(0, 0, 100, 50), room.innerShape());
    }

    @Test
    public void outerShapeTest() {
        RectRoom room = new RectRoom(0 , 0, 100, 50, FLOOR, WALL);
        Assert.assertEquals(new Rectangle(-1, -1, 102, 52), room.outerShape());
    }

    @Test
    public void overlapWithTest() {
        RectRoom room = new RectRoom(0 , 0, 50, 50, FLOOR, WALL);
        RectRoom disjointRoom = new RectRoom(53, 53, 50, 50, FLOOR, WALL);
        Assert.assertFalse(room.overlapWith(disjointRoom));
        RectRoom overlapRoom = new RectRoom(25, 25, 50, 50, FLOOR, WALL);
        Assert.assertTrue(room.overlapWith(overlapRoom));
    }

    @Test
    public void addDoorTest() {
        RectRoom room = new RectRoom(0, 0, 50, 50, FLOOR, WALL);
        Door successDoor = new Door(50, 49, CLOSED, OPENED, true);
        Assert.assertTrue(room.addDoor(successDoor));
        Door failDoor = new Door(51, 51, CLOSED, OPENED, true);
        Assert.assertFalse(room.addDoor(failDoor));
    }

    @Test
    public void equalityTest() {
        final int X = 0;
        final int Y = 0;
        final int WIDTH = 50;
        final int HEIGHT = 50;
        RectRoom room = new RectRoom(X, Y, WIDTH, HEIGHT, FLOOR, WALL);
        RectRoom sameRoom = new RectRoom(X, Y, WIDTH, HEIGHT, FLOOR, WALL);
        Assert.assertTrue(room.equals(sameRoom));
        Assert.assertTrue(room.hashCode() == sameRoom.hashCode());
        RectRoom diffXRoom = new RectRoom(X + 1, Y, WIDTH, HEIGHT, FLOOR, WALL);
        Assert.assertFalse(room.equals(diffXRoom));
        Assert.assertFalse(room.hashCode() == diffXRoom.hashCode());
        RectRoom diffYRoom = new RectRoom(X, Y + 1, WIDTH, HEIGHT, FLOOR, WALL);
        Assert.assertFalse(room.equals(diffYRoom));
        Assert.assertFalse(room.hashCode() == diffYRoom.hashCode());
        RectRoom diffWidthRoom = new RectRoom(X, Y, WIDTH + 1, HEIGHT, FLOOR, WALL);
        Assert.assertFalse(room.equals(diffWidthRoom));
        Assert.assertFalse(room.hashCode() == diffWidthRoom.hashCode());
        RectRoom diffHeightRoom = new RectRoom(X, Y, WIDTH, HEIGHT + 1, FLOOR, WALL);
        Assert.assertFalse(room.equals(diffHeightRoom));
        Assert.assertFalse(room.hashCode() == diffHeightRoom.hashCode());
        RectRoom diffFloorRoom = new RectRoom(X, Y, WIDTH, HEIGHT, WALL, WALL);
        Assert.assertFalse(room.equals(diffFloorRoom));
        Assert.assertFalse(room.hashCode() == diffFloorRoom.hashCode());
        RectRoom diffWallRoom = new RectRoom(X, Y, WIDTH, HEIGHT, FLOOR, FLOOR);
        Assert.assertFalse(room.equals(diffWallRoom));
        Assert.assertFalse(room.hashCode() == diffWallRoom.hashCode());
        RectRoom diffDoorsRoom = new RectRoom(X, Y, WIDTH, HEIGHT, FLOOR, WALL);
        diffDoorsRoom.addDoor(new Door(X + WIDTH, Y + HEIGHT - 1, CLOSED, OPENED, true));
        Assert.assertFalse(room.equals(diffDoorsRoom));
        Assert.assertFalse(room.hashCode() == diffDoorsRoom.hashCode());
        final int DOOR_X = X + WIDTH;
        final int DOOR_Y = Y;
        room.addDoor(new Door(DOOR_X, DOOR_Y, CLOSED, OPENED, true));
        RectRoom sameDoorsRoom = new RectRoom(X, Y, WIDTH, HEIGHT, FLOOR, WALL);
        sameDoorsRoom.addDoor(new Door(DOOR_X, DOOR_Y, CLOSED, OPENED, true));
        Assert.assertTrue(room.equals(sameDoorsRoom));
        Assert.assertTrue(room.hashCode() == sameDoorsRoom.hashCode());
    }
}
