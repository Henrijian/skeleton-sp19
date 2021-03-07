package byow.Entity;

import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
import org.junit.Assert;
import org.junit.Test;

public class DoorTest {
    @Test
    public void equalityTest() {
        final int X = 0;
        final int Y = 0;
        final TETile LOCKED_TILE = Tileset.LOCKED_DOOR;
        final TETile UNLOCKED_TILE = Tileset.UNLOCKED_DOOR;
        Door door = new Door(X, Y, LOCKED_TILE, UNLOCKED_TILE,true);
        Door sameDoor = new Door(X, Y, LOCKED_TILE, UNLOCKED_TILE, true);
        Assert.assertEquals(door, sameDoor);
        Assert.assertEquals(door.hashCode(), sameDoor.hashCode());
        Door diffXDoor = new Door(X + 1, Y, LOCKED_TILE, UNLOCKED_TILE, true);
        Assert.assertNotEquals(door, diffXDoor);
        Assert.assertNotEquals(door.hashCode(), diffXDoor.hashCode());
        Door diffYDoor = new Door(X, Y + 1, LOCKED_TILE, UNLOCKED_TILE, true);
        Assert.assertNotEquals(door, diffYDoor);
        Assert.assertNotEquals(door.hashCode(), diffYDoor.hashCode());
        Door diffLockedDoor = new Door(X, Y, UNLOCKED_TILE, UNLOCKED_TILE, true);
        Assert.assertNotEquals(door, diffLockedDoor);
        Assert.assertNotEquals(door.hashCode(), diffLockedDoor.hashCode());
        Door diffUnlockedDoor = new Door(X, Y, LOCKED_TILE, LOCKED_TILE, true);
        Assert.assertNotEquals(door, diffUnlockedDoor);
        Assert.assertNotEquals(door.hashCode(), diffUnlockedDoor.hashCode());
        Door diffStatusDoor = new Door(X, Y, UNLOCKED_TILE, LOCKED_TILE, true);
        Assert.assertNotEquals(door, diffStatusDoor);
        Assert.assertNotEquals(door.hashCode(), diffStatusDoor.hashCode());
    }
}
