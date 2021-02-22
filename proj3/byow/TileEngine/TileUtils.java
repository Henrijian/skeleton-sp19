package byow.TileEngine;

public class TileUtils {
    /**
     * Fill the 2D tiles at row from startCol to endCol with tile.
     */
    public static void fillRow(TETile[][] tiles, TETile tile, int y, int startX, int endX) {
        if (tiles == null) {
            throw new IllegalArgumentException("Cannot fill in null 2D tiles.");
        }
        if (tile == null) {
            throw new IllegalArgumentException("Cannot fill null tile on 2D tiles.");
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
        if (y < minY || maxY < y) {
            return;
        }
        if (startX < minX) {
            startX = minX;
        }
        if (maxX < endX) {
            endX = maxX;
        }
        for (int x = startX; x <= endX; x++) {
            tiles[x][y] = tile;
        }
    }

    /**
     * Fill the 2D tiles at col from startRow to endRow with tile.
     */
    public static void fillCol(TETile[][] tiles, TETile tile, int x, int startY, int endY) {
        if (tiles == null) {
            throw new IllegalArgumentException("Cannot fill in null 2D tiles.");
        }
        if (tile == null) {
            throw new IllegalArgumentException("Cannot fill null tile on 2D tiles.");
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
        if (x < minX || maxX < x) {
            return;
        }
        if (startY < minY) {
            startY = minY;
        }
        if (maxY < endY) {
            endY = maxY;
        }
        for (int y = startY; y <= endY; y++) {
            tiles[x][y] = tile;
        }
    }
}
