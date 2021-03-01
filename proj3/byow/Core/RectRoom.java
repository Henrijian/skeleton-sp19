package byow.Core;

import byow.Shape.Rectangle;
import byow.Shape.Relation;
import byow.TileEngine.TETile;
import byow.TileEngine.TileUtils;

import java.awt.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

public class RectRoom {
    public final int x; // x coordinate of bottom-left corner.
    public final int y; // y coordinate of bottom-left corner.
    public final int width; // width of rectangle room(not including wall).
    public final int height; // height of rectangle room(not including wall).
    public final TETile floorTile; // tile of room floor.
    public final TETile wallTile; // tile of room wall.
    public final HashSet<Door> doors;

    /**
     * Create a rectangle room with its bottom-left corner at (x, y) coordinate, and with w width, h height,
     * and surrounded with wall tile.
     * @param x x coordinate of bottom-left corner.
     * @param y y coordinate of bottom-left corner.
     * @param w width of rectangle room(not including wall).
     * @param h height of rectangle room(not including wall).
     * @param floor tile of room floor.
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
        this.doors = new HashSet<>();
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
    public int wallArea () {
        return width * 2 + height * 2 + 4;
    }

    /**
     * Inner area + wall area.
     */
    public int outerArea () {
        return innerArea() + wallArea();
    }

    /**
     * Inner shape of room.
     */
    public Rectangle innerShape() {
        return new Rectangle(x, y, width, height);
    }

    /**
     * Wall shape of room(including inner space).
     */
    public Rectangle outerShape () {
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
        return outerShape().relationTo(other.outerShape()) != Relation.DISJOINT;
    }

    /**
     * Fill the tiles with tiles of this room.
     * @param tiles the target to fill with.
     */
    public void fill(TETile[][] tiles) {
        if (tiles == null) {
            throw new IllegalArgumentException("Cannot add tiles of room to null tiles.");
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
        // Fill doors.
        for (Door door: doors) {
            door.fill(tiles);
        }
    }

    /**
     * Check is the given coordinate at a valid door position(this is on the wall)(not including corners of outer shape of room).
     * @param x x coordinate of door.
     * @param y y coordinate of door.
     * @return True, if the given position is valid.
     */
    private boolean isValidDoorPosition(int x, int y) {
        if (x == this.x - 1 || x == this.x + width) {
            return (this.y <= y) && (y < this.y + height);
        } else if (y == this.y - 1 || y == this.y + height) {
            return (this.x <= x) && (x < this.x + width);
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("x = ").append(this.x);
        result.append(", y = ").append(this.y);
        result.append(", width = ").append(this.width);
        result.append(", height = ").append(this.height);
        result.append(", doors = ");
        for (Door door: doors) {
            result.append(door.toString());
        }
        return result.toString();
    }

    /**
     * Get the closest door position of this room to target room.
     * @param target target room.
     * @return Valid door position of this room which is the closest to target room.
     */
    public Point closestDoorPositionTo(RectRoom target) {
        if (target == null) {
            throw new IllegalArgumentException("Cannot get closet door position of this room to null target.");
        }
        Rectangle myOuterShape = outerShape();
        Rectangle targetOuterShape = target.outerShape();
        Point myClosestPoint = myOuterShape.closestPointTo(targetOuterShape);
        Point targetClosestPoint = targetOuterShape.closestPointTo(myOuterShape);
        // If the closest point is not a valid door position, it must at corners of room.
        Point closestDoorPoint = myClosestPoint;
        if (!isValidDoorPosition(closestDoorPoint.x, closestDoorPoint.y)) {
            Point[] doorPointCandidates = PointUtils.verticalNeighbors(closestDoorPoint);
            int closestDistanceSqr = Integer.MAX_VALUE;
            for (Point doorPointCandidate: doorPointCandidates) {
                if (!isValidDoorPosition(doorPointCandidate.x, doorPointCandidate.y)) {
                    continue;
                }
                int distanceSqr = PointUtils.distanceSqrBetween(doorPointCandidate, targetClosestPoint);
                if (distanceSqr < closestDistanceSqr) {
                    closestDistanceSqr = distanceSqr;
                    closestDoorPoint = doorPointCandidate;
                }
            }
            if (!isValidDoorPosition(closestDoorPoint.x, closestDoorPoint.y)) {
                throw new IllegalArgumentException("Cannot find closest door position of this room to target room."
                        + System.lineSeparator() + "This room: " + this
                        + System.lineSeparator() + "Target room: " + target);
            }
        }
        return closestDoorPoint;
    }

    /**
     * Add door to this room.
     * @param door the door prepared for adding to this room.
     * @return True, if adding door to this room successfully.
     */
    public boolean addDoor(Door door) {
        if (door == null) {
            return false;
        }
        if (!isValidDoorPosition(door.x, door.y)) {
            return false;
        }
        return doors.add(door);
    }

    public boolean addDoors(Collection<Door> doors) {
        for (Door door: doors) {
            if (!addDoor(door)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof RectRoom)) {
            return false;
        }
        RectRoom otherRoom = (RectRoom) other;
        return x == otherRoom.x && y == otherRoom.y
                && width == otherRoom.width && height == otherRoom.height
                && floorTile.equals(otherRoom.floorTile) && wallTile.equals(otherRoom.wallTile)
                && doors.equals(otherRoom.doors);
    }

    @Override
    public int hashCode() {
        final int BASE = 31;
        int result = 17;
        result = BASE * result + Integer.hashCode(x);
        result = BASE * result + Integer.hashCode(y);
        result = BASE * result + Integer.hashCode(width);
        result = BASE * result + Integer.hashCode(height);
        result = BASE * result + floorTile.hashCode();
        result = BASE * result + wallTile.hashCode();
        result = BASE * result + doors.hashCode();
        return result;
    }
}
