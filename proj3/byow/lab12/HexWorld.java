package byow.lab12;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 28;
    private static final int HEIGHT = 30;
    private static final int SIZE = 3;

    static class Hexagon {
        /**
         * @lowerLeftPt lower left point of boundary rectangle of hexagon.
         * @size side length of hexagon.
         * */
        public final Point lowerLeftPt;
        public final int size;

        public Hexagon(Point lowerLeftPt, int size) {
            if (lowerLeftPt == null) {
                throw new IllegalArgumentException("Initiating Hexagon with null lowerLeftPt is invalid.");
            }
            if (size <= 0) {
                throw new IllegalArgumentException("Initiating Hexagon with not positive size is invalid, size: " + size);
            }
            this.lowerLeftPt = new Point(lowerLeftPt.x, lowerLeftPt.y);
            this.size = size;
        }

        /**
         * Get height of hexagon.
         * */
        public int height() {
            return size * 2;
        }

        /**
         * Get width of hexagon.
         */
        public int width() {
            return 3 * size - 2;
        }

        /**
         * Get point of top left corner of hexagon.
         */
        public Point topLeftPt() {
            int level = height() - 1;
            int y = lowerLeftPt.y + level;
            int x = vertStartX(level);
            return new Point(x, y);
        }

        /**
         * Get point of top right corner of hexagon.
         */
        public Point topRightPt() {
            int level = height() - 1;
            int y = lowerLeftPt.y + level;
            int x = vertEndX(level);
            return new Point(x, y);
        }

        /**
         * Get point of right top corner of hexagon.
         */
        public Point rightTopPt() {
            int level = height() / 2;
            int y = lowerLeftPt.y + level;
            int x = vertEndX(level);
            return new Point(x, y);
        }

        /**
         * Get point of right bottom corner of hexagon.
         */
        public Point rightBottomPt() {
            int level = height() / 2 - 1;
            int y = lowerLeftPt.y + level;
            int x = vertEndX(level);
            return new Point(x, y);
        }

        /**
         * Get point of bottom right corner of hexagon.
         */
        public Point bottomRightPt() {
            int level = 0;
            int y = lowerLeftPt.y + level;
            int x = vertEndX(level);
            return new Point(x, y);
        }

        /**
         * Get point of bottom left corner of hexagon.
         */
        public Point bottomLeftPt() {
            int level = 0;
            int y = lowerLeftPt.y + level;
            int x = vertStartX(level);
            return new Point(x, y);
        }

        /**
         * Get point of left bottom corner of hexagon.
         */
        public Point leftBottomPt() {
            int level = height() / 2 - 1;
            int y = lowerLeftPt.y + level;
            int x = vertStartX(level);
            return new Point(x, y);
        }

        /**
         * Get point of left top corner of hexagon.
         */
        public Point leftTopPt() {
            int level = height() / 2;
            int y = lowerLeftPt.y + level;
            int x = vertStartX(level);
            return new Point(x, y);
        }

        /**
         * Get the top tessellated hexagon.
         */
        public Hexagon tessellatedTopHexagon() {
            int tessellatedX = leftTopPt().x;
            int tessellatedY = topLeftPt().y + 1;
            Point tessellatedPt = new Point(tessellatedX, tessellatedY);
            return new Hexagon(tessellatedPt, size);
        }

        /**
         * Get the top right tessellated hexagon.
         */
        public Hexagon tessellatedTopRightHexagon() {
            int tessellatedX = topRightPt().x + 1;
            int tessellatedY = rightTopPt().y;
            Point tessellatedPt = new Point(tessellatedX, tessellatedY);
            return new Hexagon(tessellatedPt, size);
        }

        /**
         * Get the bottom right tessellated hexagon.
         */
        public Hexagon tessellatedBottomRightHexagon() {
            int tessellatedX = bottomRightPt().x + 1;
            int tessellatedY = bottomRightPt().y - height() / 2;
            Point tessellatedPt = new Point(tessellatedX, tessellatedY);
            return new Hexagon(tessellatedPt, size);
        }

        /**
         * Get the bottom tessellated hexagon.
         */
        public Hexagon tessellatedBottomHexagon() {
            int tessellatedX = leftBottomPt().x;
            int tessellatedY = bottomRightPt().y - height();
            Point tessellatedPt = new Point(tessellatedX, tessellatedY);
            return new Hexagon(tessellatedPt, size);
        }

        /**
         * Get the bottom left tessellated hexagon.
         */
        public Hexagon tessellatedBottomLeftHexagon() {
            int tessellatedX = bottomLeftPt().x - width();
            int tessellatedY = bottomLeftPt().y - height() / 2;
            Point tessellatedPt = new Point(tessellatedX, tessellatedY);
            return new Hexagon(tessellatedPt, size);
        }

        /**
         * Get the bottom left tessellated hexagon.
         */
        public Hexagon tessellatedTopLeftHexagon() {
            int tessellatedX = topLeftPt().x - width();
            int tessellatedY = leftTopPt().y;
            Point tessellatedPt = new Point(tessellatedX, tessellatedY);
            return new Hexagon(tessellatedPt, size);
        }

        private void validateLevel(int level) {
            int hexHeight = height();
            if (level < 0 || hexHeight <= level) {
                throw new IllegalArgumentException("level must be within height, level: " + level + ", height: " + hexHeight);
            }
        }

        /**
         * Get start X position of hexagon at level level(0 - base).
         */
        public int vertStartX(int level) {
            validateLevel(level);
            int startX = lowerLeftPt.x;
            int hexHeight = height();
            int hexHalfHeight = hexHeight / 2;
            int deltaX;
            if (level < hexHalfHeight) {
                deltaX = size - 1 - level;
            } else {
                deltaX = level - size;
            }
            return startX + deltaX;
        }

        /**
         * Get end X position of hexagon at level level(0 - base).
         */
        public int vertEndX(int level) {
            validateLevel(level);
            int startX = lowerLeftPt.x;
            int hexWidth = width();
            int hexHeight = height();
            int hexHalfHeight = hexHeight / 2;
            int deltaX;
            if (level < hexHalfHeight) {
                deltaX = hexWidth - 1 - size + 1 + level;
            } else {
                deltaX = hexWidth - 1  - level + size;
            }
            return startX + deltaX;
        }

        /**
         * Add hexagon to world with tile to fill in hexagon.
         * */
        public void addToWorld(TETile[][] world, TETile tile) {
            // Check arguments.
            if (world == null) {
                throw new IllegalArgumentException("world for addToWorld cannot be null.");
            }
            if (tile == null) {
                throw new IllegalArgumentException("tile for addToWorld hexagon cannot be null.");
            }
            int worldWidth = world.length;
            int worldHeight = world[0].length;
            int hexHeight = height();
            for (int level = 0; level < hexHeight; level++) {
                int hexY = lowerLeftPt.y + level;
                if (hexY < 0 || worldHeight <= hexHeight) {
                    continue;
                }
                int hexStartX = vertStartX(level);
                int hexEndX = vertEndX(level);
                for (int hexX = hexStartX; hexX <= hexEndX; hexX++) {
                    if (hexX < 0 || worldWidth <= hexX) {
                        continue;
                    }
                    world[hexX][hexY] = tile;
                }
            }
        }

        public boolean inWorld(TETile[][] world) {
            // Check arguments.
            if (world == null) {
                throw new IllegalArgumentException("world for inWorld cannot be null.");
            }
            int worldWidth = world.length;
            int worldHeight = world[0].length;
            int minX = lowerLeftPt.x;
            int maxX = lowerLeftPt.x + width() - 1;
            int minY = lowerLeftPt.y;
            int maxY = lowerLeftPt.y + height() - 1;
            return (0 <= minX && maxX < worldWidth && 0 <= minY && maxY <= worldHeight);
        }
    }

    /** Picks a RANDOM tile with a 33% change of being
     *  a wall, 33% chance of being a flower, and 33%
     *  chance of being empty space.
     */
    private static TETile randomTile() {
        Random random = new Random();
        int tileNum = random.nextInt(6);
        return switch (tileNum) {
            case 0 -> Tileset.WALL;
            case 1 -> Tileset.FLOWER;
            case 2 -> Tileset.GRASS;
            case 3 -> Tileset.WATER;
            case 4 -> Tileset.SAND;
            case 5 -> Tileset.TREE;
            default -> Tileset.NOTHING;
        };
    }

    public static void main(String[] args) {
        // Initialize the tile rendering engine with a window of size WIDTH x HEIGHT
        TERenderer renderer = new TERenderer();
        renderer.initialize(WIDTH, HEIGHT);

        // Initialize tiles
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        // Find first hexagon position.
        Hexagon hexagon = new Hexagon(new Point(0,0), SIZE);
        Point lowerLeftPt = new Point(WIDTH / 2 - hexagon.width() / 2, 0);

        // Add hexagon to world.
        Queue<Hexagon> hexQueue = new LinkedList<>();
        hexQueue.add(new Hexagon(lowerLeftPt, SIZE));
        while (hexQueue.size() > 0) {
            hexagon = hexQueue.poll();
            Hexagon leftHexagon = hexagon.tessellatedTopLeftHexagon();
            if (leftHexagon.inWorld(world)) {
                hexQueue.offer(leftHexagon);
            }
            Hexagon rightHexagon = hexagon.tessellatedTopRightHexagon();
            if (rightHexagon.inWorld(world)) {
                hexQueue.offer(rightHexagon);
            }
            if (hexagon.inWorld(world)) {
                hexagon.addToWorld(world, randomTile());
            }
        }

        // draws the world to the screen
        renderer.renderFrame(world);
    }
}
