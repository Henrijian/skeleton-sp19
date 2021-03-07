package byow.Entity;

import byow.PriorityQueue.ArrayHeapMinPQ;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.awt.*;
import java.util.*;

public class World {
    private final TETile FLOOR_TILE = Tileset.FLOOR;
    private final TETile WALL_TILE = Tileset.WALL;
    private final TETile CLOSED_DOOR_TILE = Tileset.LOCKED_DOOR;
    private final TETile OPENED_DOOR_TILE = Tileset.UNLOCKED_DOOR;
    private final int width; // Width of this world.
    private final int height; // Height of this world.
    private final TETile[][] tiles; // Tiles constructing this world.
    private final RectRooms rooms; // Rooms in this world.
    private final Hallways hallWays; // Hallways in this world.

    public World (int w, int h) {
        if (w <= 0) {
            throw new IllegalArgumentException("Width of world is not positive, width: " + w);
        }
        if (h <= 0) {
            throw new IllegalArgumentException("Height of world is not positive, height: " + h);
        }
        this.width = w;
        this.height = h;
        this.tiles = new TETile[w][h];
        this.rooms = new RectRooms();
        this.hallWays = new Hallways();

        init();
    }

    /**
     * Initialize the world, letting all tiles of world to nothing.
     */
    private void init() {
        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.height; y++) {
                this.tiles[x][y] = Tileset.NOTHING;
            }
        }
        rooms.clear();
        hallWays.clear();
    }

    /**
     * Randomly generating rooms in the world.
     * @param seed seed of randomness.
     */
    private void randRooms(long seed) {
        /* The world cannot contain the smallest room
           which is the room with one space and surrounding by wall. */
        if (this.width < 3 || this.height < 3) {
            return;
        }
        rooms.clear();
        final int ROOM_MIN_X = 1; // Preventing wall of room exceeding the world.
        final int ROOM_MIN_Y = 1; // Preventing wall of room exceeding the world.
        final int REQUIRED_AREA = this.width * this.height / 2; // 50% of the world.
        final int MAX_ROOM_COUNT = (this.width / 3) * (this.height / 3); // Meaning the world filled with the smallest area room.
        Random random = new Random(seed);
        int attempts = 0;
        while (rooms.totalArea() < REQUIRED_AREA && attempts < MAX_ROOM_COUNT) {
            int roomWidth = random.nextInt(this.width / 3) + 1;
            int roomHeight = random.nextInt(this.height / 3) + 1;
            int roomMaxX = this.width - roomWidth - 1;
            int roomX = random.nextInt(roomMaxX + 1 - ROOM_MIN_X) + ROOM_MIN_X;
            int roomMaxY = this.height - roomHeight - 1;
            int roomY = random.nextInt(roomMaxY + 1 - ROOM_MIN_Y) + ROOM_MIN_Y;
            RectRoom newRoom = new RectRoom(roomX, roomY, roomWidth, roomHeight, FLOOR_TILE, WALL_TILE);
            if (rooms.add(newRoom)) {
                attempts = 1;
            } else {
                attempts = attempts + 1;
            }
        }
    }

    /**
     * Connect rooms in the world by hallways.
     */
    private void connectRooms() {
        if (rooms == null) {
            throw new IllegalArgumentException("Cannot connect null rooms.");
        }
        hallWays.clear();
        ArrayHeapMinPQ<RectRoom> roomsMinPQ = new ArrayHeapMinPQ<>();
        for (RectRoom room: rooms) {
            roomsMinPQ.add(room, Double.MAX_VALUE);
        }
        HashMap<RectRoom, HashSet<Door>> roomDoors = new HashMap<>();
        while (roomsMinPQ.size() > 0) {
            RectRoom myRoom = roomsMinPQ.removeSmallest();
            RectRoom neighborRoom = null;
            double neighborDistance = Double.MAX_VALUE;
            for (RectRoom otherRoom: roomsMinPQ.items()) {
                double distance = myRoom.outerShape().distanceTo(otherRoom.outerShape());
                if (roomsMinPQ.peekPriority(otherRoom) > distance) {
                    roomsMinPQ.changePriority(otherRoom, distance);
                }
                if (distance < neighborDistance) {
                    neighborRoom = otherRoom;
                    neighborDistance = distance;
                }
            }
            if (neighborRoom == null) {
                continue;
            }
            Point myDoorPos = myRoom.closestDoorPositionTo(neighborRoom);
            Door myDoor = new Door(myDoorPos.x, myDoorPos.y, CLOSED_DOOR_TILE, OPENED_DOOR_TILE, true);
            if (!roomDoors.containsKey(myRoom)) {
                roomDoors.put(myRoom, new HashSet<>());
            }
            roomDoors.get(myRoom).add(myDoor);
            Point neighborDoorPos = neighborRoom.closestDoorPositionTo(myRoom);
            Door neighborDoor = new Door(neighborDoorPos.x, neighborDoorPos.y, CLOSED_DOOR_TILE, OPENED_DOOR_TILE, true);
            if (!roomDoors.containsKey(neighborRoom)) {
                roomDoors.put(neighborRoom, new HashSet<>());
            }
            roomDoors.get(neighborRoom).add(neighborDoor);
            Hallway hallWay = new Hallway(myDoorPos, neighborDoorPos, FLOOR_TILE, WALL_TILE);
            hallWays.add(hallWay);
        }
        // Add door to room.
        for (RectRoom room: roomDoors.keySet()) {
            Set<Door> doors = roomDoors.get(room);
            room.addDoors(doors);
        }
    }

    /**
     * Randomly creating the world.
     * @param seed seed of randomness.
     */
    public void randWorld(long seed) {
        init();
        randRooms(seed);
        connectRooms();
        rooms.fill(tiles);
        HashSet<TETile> blocks = new HashSet<>();
        blocks.add(WALL_TILE);
        hallWays.fill(tiles, blocks, FLOOR_TILE, WALL_TILE);
    }

    public TETile[][] tiles() {
        TETile[][] result = new TETile[width][height];
        for (int x = 0; x < width; x++) {
            if (height >= 0) System.arraycopy(this.tiles[x], 0, result[x], 0, height);
        }
        return result;
    }
}
