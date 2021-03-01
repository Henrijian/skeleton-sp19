package byow.Core;

import byow.TileEngine.TETile;

import java.util.LinkedList;

public class RectRooms extends LinkedList<RectRoom> {
    private int innerArea; // total inner area of all rooms.
    private int wallArea; // total wall area of all rooms.

    public RectRooms() {
        this.innerArea = 0;
        this.wallArea = 0;
    }

    /**
     * Add room to list, if this room is not overlapped with existed rooms.
     * @param newRoom the room prepared for added.
     * @return True, if room is added to list, else false.
     */
    public boolean add (RectRoom newRoom) {
        if (newRoom == null) {
            return false;
        }
        for (RectRoom room: this) {
            if (room.overlapWith(newRoom)) {
                return false;
            }
        }
        this.innerArea = this.innerArea + newRoom.innerArea();
        this.wallArea = this.wallArea + newRoom.wallArea();
        return super.add(newRoom);
    }

    /**
     * Total inner area of all rooms.
     */
    public int innerArea() {
        return this.innerArea;
    }

    /**
     * Total wall area of all rooms.
     */
    public int wallArea() {
        return this.wallArea;
    }

    /**
     * Total total area of all rooms.
     */
    public int totalArea() {
        return this.innerArea + this.wallArea;
    }

    /**
     * Clear all rooms.
     */
    public void clear() {
        super.clear();
        this.innerArea = 0;
        this.wallArea = 0;
    }

    /**
     * Fill the tiles with tiles of all rooms.
     * @param tiles the target to fill with.
     */
    public void fill(TETile[][] tiles) {
        if (tiles == null) {
            throw new IllegalArgumentException("Cannot add tiles of all rooms to null tiles.");
        }
        if (tiles.length == 0) {
            return;
        }
        if (tiles[0].length == 0) {
            return;
        }
        for (RectRoom room: this) {
            room.fill(tiles);
        }
        for (RectRoom room: this) {
            room.fill(tiles);
        }
    }

    /**
     * Find the closest room to query room.
     * @param room the query room.
     */
    public RectRoom closestTo(RectRoom room) {
        RectRoom closetRoom = null;
        double closetDistance = Integer.MAX_VALUE;
        for (RectRoom otherRoom: this) {
            double distance = otherRoom.outerShape().distanceTo(room.outerShape());
            if (distance < closetDistance) {
                closetRoom = otherRoom;
                closetDistance = distance;
            }
        }
        return closetRoom;
    }

}
