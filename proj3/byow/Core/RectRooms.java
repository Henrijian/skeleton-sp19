package byow.Core;

import byow.TileEngine.TETile;

import java.util.ArrayList;
import java.util.List;

public class RectRooms {
    private final List<RectRoom> list;
    private int innerArea; // total inner area of all rooms.
    private int wallArea; // total wall area of all rooms.

    public RectRooms() {
        this.list = new ArrayList<>();
        this.innerArea = 0;
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
        for (RectRoom room: list) {
            if (room.overlapWith(newRoom)) {
                return false;
            }
        }
        this.innerArea = this.innerArea + newRoom.innerArea();
        this.wallArea = this.wallArea + newRoom.wallArea();
        return list.add(newRoom);
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
        list.clear();
        innerArea = 0;
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
        for (RectRoom room: list) {
            room.fill(tiles);
        }
    }
}
