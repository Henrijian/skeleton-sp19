package byow.lab12;
import org.junit.Test;
import static org.junit.Assert.*;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.awt.*;
import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 60;
    private static final int HEIGHT = 30;

    private static void validateSide(int side) {
        if (side <= 0) {
            throw new IllegalArgumentException("side must be positive value, getting side: " + side);
        }
    }

    private static void validateLevel(int level) {
        if (level < 0) {
            throw new IllegalArgumentException("level must be positive value, getting level: " + level);
        }
    }

    private static void validateSideAndLevel(int side, int level) {
        validateSide(side);
        validateLevel(level);
        int hexHeight = hexagonHeight(side);
        if (hexHeight <= level) {
            throw new IllegalArgumentException("level is out of hexagon height, level: " + level + ", height: " + hexHeight);
        }
    }

    /**
     * Get height of hexagon with side side.
     * */
    public static int hexagonHeight(int side) {
        validateSide(side);
        return side * 2;
    }

    /**
     * Get width of hexagon with side side.
     */
    public static int hexagonWidth(int side) {
        validateSide(side);
        return 3 * side - 2;
    }

    /**
     * Get start position of hexagon, which lower left point is (x, y), at level level(0 - base).
     */
    public static Point hexagonStartPt(Point lowerLeftPt, int side, int level) {
        validateSideAndLevel(side, level);
        int startX = lowerLeftPt.x;
        int startY = lowerLeftPt.y;
        int hexHeight = hexagonHeight(side);
        int hexHalfHeight = hexHeight / 2;
        int deltaX;
        if (level < hexHalfHeight) {
            deltaX = side - 1 - level;
        } else {
            deltaX = level - side;
        }
        int deltaY = level;
        return new Point(startX + deltaX, startY + deltaY);
    }

    /**
     * Get end position of hexagon, which lower left point is (x, y), at level level(0 - base).
     */
    public static Point hexagonEndPt(Point lowerLeftPt, int side, int level) {
        validateSideAndLevel(side, level);
        int startX = lowerLeftPt.x;
        int startY = lowerLeftPt.y;
        int hexWidth = hexagonWidth(side);
        int hexHeight = hexagonHeight(side);
        int hexHalfHeight = hexHeight / 2;
        int deltaX;
        if (level < hexHalfHeight) {
            deltaX = hexWidth - 1 - side + 1 + level;
        } else {
            deltaX = hexWidth - 1  - level + side;
        }
        int deltaY = level;
        return new Point(startX + deltaX, startY + deltaY);
    }

    public static void addHexagon(TETile[][] world, Point lowerLeftPt, int side, TETile tile) {
        // Check arguments.
        if (world == null) {
            throw new IllegalArgumentException("world cannot be null.");
        }
        validateSide(side);
        if (tile == null) {
            throw new IllegalArgumentException("tile for constructing hexagon cannot be null.");
        }

        int worldWidth = world.length;
        int worldHeight = world[0].length;
        int hexHeight = hexagonHeight(side);
        for (int level = 0; level < hexHeight; level++) {
            int hexY = lowerLeftPt.y + level;
            if (hexY < 0 || worldHeight <= hexHeight) {
                continue;
            }
            Point hexStartPt = hexagonStartPt(lowerLeftPt, side, level);
            Point hexEndPt = hexagonEndPt(lowerLeftPt, side, level);
            int hexStartX = hexStartPt.x;
            int hexEndX = hexEndPt.x;
            for (int hexX = hexStartX; hexX <= hexEndX; hexX++) {
                if (hexX < 0 || worldWidth <= hexX) {
                    continue;
                }
                world[hexX][hexY] = tile;
            }
        }
    }

    public static void main(String[] args) {
        // initialize the tile rendering engine with a window of size WIDTH x HEIGHT
        TERenderer renderer = new TERenderer();
        renderer.initialize(WIDTH, HEIGHT);

        // initialize tiles
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        addHexagon(world, new Point(0, 0), 10, Tileset.FLOWER);

        // draws the world to the screen
        renderer.renderFrame(world);
    }
}
