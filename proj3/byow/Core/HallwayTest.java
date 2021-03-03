package byow.Core;

import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
import org.junit.Assert;
import org.junit.Test;

import java.awt.*;

public class HallwayTest {
    @Test
    public void equalityTest() {
        final Point FROM = new Point(0, 0);
        final Point TO = new Point(5, 10);
        final TETile FLOOR_TILE = Tileset.FLOOR;
        final TETile WALL_TILE = Tileset.WALL;
        Hallway hallway = new Hallway(FROM, TO, FLOOR_TILE, WALL_TILE);
        Hallway sameHallway = new Hallway(FROM, TO, FLOOR_TILE, WALL_TILE);
        Assert.assertEquals(hallway, sameHallway);
        Assert.assertEquals(hallway.hashCode(), sameHallway.hashCode());
        Point diffFrom = new Point(FROM.x + 1, FROM.y + 2);
        Hallway diffFromHallway = new Hallway(diffFrom, TO, FLOOR_TILE, WALL_TILE);
        Assert.assertNotEquals(hallway, diffFromHallway);
        Assert.assertNotEquals(hallway.hashCode(), diffFromHallway.hashCode());
        Point diffTo = new Point(TO.x + 2, TO.y + 1);
        Hallway diffToHallway = new Hallway(FROM, diffTo, FLOOR_TILE, WALL_TILE);
        Assert.assertNotEquals(hallway, diffToHallway);
        Assert.assertNotEquals(hallway.hashCode(), diffToHallway.hashCode());
        Hallway diffFloorHallway = new Hallway(FROM, TO, WALL_TILE, WALL_TILE);
        Assert.assertNotEquals(hallway, diffFloorHallway);
        Assert.assertNotEquals(hallway.hashCode(), diffFloorHallway.hashCode());
        Hallway diffWallHallway = new Hallway(FROM, TO, FLOOR_TILE, FLOOR_TILE);
        Assert.assertNotEquals(hallway, diffWallHallway);
        Assert.assertNotEquals(hallway.hashCode(), diffWallHallway.hashCode());
    }
}
