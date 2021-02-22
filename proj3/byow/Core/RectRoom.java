package byow.Core;

import byow.Shape.Rectangle;
import byow.Shape.Relation;
import byow.TileEngine.TETile;
import byow.TileEngine.TileUtils;
import org.w3c.dom.css.Rect;

public class RectRoom {
    public final int x; // x coordinate of bottom-left corner.
    public final int y; // y coordinate of bottom-left corner.
    public final int width; // width of rectangle room(not including wall).
    public final int height; // height of rectangle room(not including wall).
    public final TETile floorTile; // tile of room floor.
    public final TETile wallTile; // tile of room wall.

    /**
     * Create a rectangle room with its bottom-left corner at (x, y) coordinate, and with w width, h height,
     * and surrounded with wall tile.
     * @param x x coordinate of bottom-left corner.
     * @param y y coordinate of bottom-left corner.
     * @param w width of rectangle room(not including wall).
     * @param h height of rectangle room(not including wall).
     * @param wall tile of room wall.
     */
    public RectRoom(int x, int y, int w, int h, TETile floor, TETile wall) {
        if (w <= 0) {
            throw new IllegalArgumentException("Width of room less than or equals to 0, w = " + w);
        }
        if (h <= 0) {
            throw new IllegalArgumentException("Height of room less than or equals to 0, h = " + h);
        }
        if (floor == null) {
            throw new IllegalArgumentException("Floor of room is null.");
        }
        if (wall == null) {
            throw new IllegalArgumentException("Wall of room is null.");
        }
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
        this.floorTile = floor;
        this.wallTile = wall;
    }

    /**
     * Room area without calculating wall area.
     */
    public int innerArea() {
        return width * height;
    }

    /**
     * Wall area which is surrounding room.
     */
    public int wallArea() {
        return width * 2 + height * 2 + 4;
    }

    /**
     * Inner area + wall area.
     */
    public int totalArea() {
        return innerArea() + wallArea();
    }

    /**
     * Inner shape of room.
     */
    private Rectangle innerShape() {
        return new Rectangle(x, y, width, height);
    }

    /**
     * Wall shape of room.
     */
    private Rectangle wallShape() {
        return new Rectangle(x - 1, y - 1, width + 2, height + 2);
    }

    /**
     * Whether this room overlaps with the other room.
     * @param other the other room.
     * @return True, if this room overlap with other room, else false.
     */
    public boolean overlapWith(RectRoom other) {
        if (other == null) {
            throw new IllegalArgumentException("Cannot check overlap with null room.");
        }
        return wallShape().relationTo(other.wallShape()) != Relation.DISJOINT;
    }

    /**
     * Fill the tiles with tiles of this room.
     * @param tiles the target to fill with.
     */
    public void fill(TETile[][] tiles) {
        if (tiles == null) {
            throw new IllegalArgumentException("Cannot add tiles of room to null tiles.");
        }
        if (tiles.length == 0) {
            return;
        }
        if (tiles[0].length == 0) {
            return;
        }
        // Fill floor tiles.
        int floorTopY = y + height - 1;
        int floorBottomY = y;
        int floorLeftX = x;
        int floorRightX = x + width - 1;
        for (int floorX = floorLeftX; floorX <= floorRightX; floorX++) {
            TileUtils.fillCol(tiles, floorTile, floorX, floorBottomY, floorTopY);
        }
        // Fill wall tiles.
        int wallTopY = y + height;
        int wallBottomY = y - 1;
        int wallLeftX = x - 1;
        int wallRightX = x + width;
        // Fill top wall tiles.
        TileUtils.fillRow(tiles, wallTile, wallTopY, wallLeftX, wallRightX);
        // Fill bottom wall tiles.
        TileUtils.fillRow(tiles, wallTile, wallBottomY, wallLeftX, wallRightX);
        // Fill left wall tiles.
        TileUtils.fillCol(tiles, wallTile, wallLeftX, wallBottomY, wallTopY);
        // Fill right wall tiles.
        TileUtils.fillCol(tiles, wallTile, wallRightX, wallBottomY, wallTopY);
    }
}
