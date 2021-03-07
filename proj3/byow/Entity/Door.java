package byow.Entity;

import byow.Core.PointUtils;
import byow.TileEngine.TETile;

import java.awt.*;

public class Door {
    public final int x; // X coordinate of this door.
    public final int y; // Y coordinate of this door.
    public final TETile closedTile; // Tile represents closed door.
    public final TETile openedTile; // Tile represents opened door.
    public boolean opened; // This door is opened or not.

    public Door(int x, int y, TETile closedTile, TETile openedTile, boolean opened) {
        if (closedTile == null) {
            throw new IllegalArgumentException("Cannot initiate door with null closedTile.");
        }
        if (openedTile == null) {
            throw new IllegalArgumentException("Cannot initiate door with null openedTile.");
        }
        this.x = x;
        this.y = y;
        this.closedTile = closedTile;
        this.openedTile = openedTile;
        this.opened = opened;
    }

    /**
     * Position of this door.
     * @return Point of the position of this door.
     */
    public Point position() {
        return new Point(x, y);
    }

    /**
     * Fill the tiles with tiles of this door.
     * @param tiles the target to fill with.
     */
    public void fill(TETile[][] tiles) {
        if (tiles == null) {
            throw new IllegalArgumentException("Cannot add tiles of door to null tiles.");
        }
        if (tiles.length == 0) {
            return;
        }
        if (tiles[0].length == 0) {
            return;
        }
        int minX = 0;
        int maxX = tiles.length - 1;
        int minY = 0;
        int maxY = tiles[0].length - 1;
        if (!PointUtils.inBoundary(x, y, minX, minY, maxX, maxY)) {
            return;
        }
        if (opened) {
            tiles[x][y] = openedTile;
        } else {
            tiles[x][y] = closedTile;
        }
    }

    @Override
    public String toString() {
        return "x = " + x + ", y = " + y + ", opened = " + opened;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Door)) {
            return false;
        }
        Door otherDoor = (Door) other;
        return x == otherDoor.x && y == otherDoor.y
                && closedTile.equals(otherDoor.closedTile) && openedTile.equals(otherDoor.openedTile)
                && opened == otherDoor.opened;
    }

    @Override
    public int hashCode() {
        final int BASE = 31;
        int result = 17;
        result = BASE * result + Integer.hashCode(x);
        result = BASE * result + Integer.hashCode(y);
        result = BASE * result + closedTile.hashCode();
        result = BASE * result + openedTile.hashCode();
        result = BASE * result + Boolean.hashCode(opened);
        return result;
    }
}
