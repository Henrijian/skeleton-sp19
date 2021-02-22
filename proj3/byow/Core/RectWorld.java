package byow.Core;

import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.Random;

public class RectWorld {
    private final int width; // Width of world.
    private final int height; // Height of world.
    private final RectRooms rooms; // Rooms in world.
    public final TETile[][] tiles; // Tiles constructing the world.

    public RectWorld(int w, int h) {
        if (w <= 0) {
            throw new IllegalArgumentException("Width of world is not positive, width: " + w);
        }
        if (h <= 0) {
            throw new IllegalArgumentException("Height of world is not positive, height: " + h);
        }
        this.width = w;
        this.height = h;
        this.rooms = new RectRooms();
        this.tiles = new TETile[w][h];

        init();
    }

    /**
     * Initialize the world, letting all tiles of world to nothing.
     */
    public void init() {
        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.height; y++) {
                this.tiles[x][y] = Tileset.NOTHING;
            }
        }
        rooms.clear();
    }

    /**
     * Randomly adding rooms to the world.
     * @param seed seed of randomness.
     */
    public void randRooms(int seed) {
        init();
        /* The world cannot contain the smallest area room
           which is the room with one space and surrounding by wall. */
        if (this.width < 3 || this.height < 3) {
            return;
        }
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
            RectRoom newRoom = new RectRoom(roomX, roomY, roomWidth, roomHeight, Tileset.FLOOR, Tileset.WALL);
            if (rooms.add(newRoom)) {
                attempts = 1;
            } else {
                attempts = attempts + 1;
            }
        }
        rooms.fill(tiles);
    }
}
