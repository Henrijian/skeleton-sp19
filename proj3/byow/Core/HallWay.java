package byow.Core;

import byow.PriorityQueue.ArrayHeapMinPQ;
import byow.TileEngine.TETile;

import java.awt.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class HallWay {
    public final Point from; // One end of hallway.
    public final Point to; // Another end of hallway.
    public final TETile floorTile; // Tile of hallway floor.
    public final TETile wallTile; // Tile of hallway wall.

    public HallWay(Point from, Point to, TETile floor, TETile wall) {
        if (from == null) {
            throw new IllegalArgumentException("From position of hallway is null.");
        }
        if (to == null) {
            throw new IllegalArgumentException("To position of hallway is null.");
        }
        if (floor == null) {
            throw new IllegalArgumentException("Floor of hallway is null.");
        }
        if (wall == null) {
            throw new IllegalArgumentException("Wall of hallway is null.");
        }
        this.from = from;
        this.to = to;
        this.floorTile = floor;
        this.wallTile = wall;
    }

    /**
     * Fill the tiles with tiles of this hallway.
     * @param tiles the target to fill with.
     * @param blocks the set of tiles, when encounter these tiles, ignore to fill them.
     */
    public void fill(TETile[][] tiles, Set<TETile> blocks) {
        if (tiles == null) {
            throw new IllegalArgumentException("Cannot add tiles of hallway to null tiles.");
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
        if (!PointUtils.inBoundary(from, minX, minY, maxX, maxY)) {
            return;
        }
        if (!PointUtils.inBoundary(to, minX, minY, maxX, maxY)) {
            return;
        }
        HashSet<Point> blockedPosSet = new HashSet<>();
        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                TETile tile = tiles[x][y];
                if (!blocks.contains(tile)) {
                    continue;
                }
                Point blockPos = new Point(x, y);
                blockedPosSet.add(blockPos);
            }
        }
        List<Point> trace = PointUtils.shortestTrace(from, to, blockedPosSet);
        // Fill floor of hallway.
        for (Point tracePos: trace) {
            tiles[tracePos.x][tracePos.y] = floorTile;
        }
        // Fill wall of hallway.
        for (Point tracePos: trace) {
            Point[] surroundPosList = PointUtils.surroundNeighbors(tracePos);
            for (Point surroundPos: surroundPosList) {
                if (!PointUtils.inBoundary(surroundPos, minX, minY, maxX, maxY)) {
                    continue;
                }
                if (blockedPosSet.contains(surroundPos)) {
                    continue;
                }
                TETile surroundPosTile = tiles[surroundPos.x][surroundPos.y];
                if (surroundPosTile.equals(floorTile)) {
                    continue;
                }
                tiles[surroundPos.x][surroundPos.y] = wallTile;
            }
        }
    }

    @Override
    public String toString() {
        return "from = (" + from.x + ", " + from.y + "), to = (" + to.x + ", " + to.y + "), floor tile = "
                + floorTile.toString() + ", wall tile = " + wallTile.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof HallWay)) {
            return false;
        }
        HallWay otherHallway = (HallWay) other;
        return from.equals(otherHallway.from) && to.equals(otherHallway.to)
                && floorTile.equals(otherHallway.floorTile) && wallTile.equals(otherHallway.wallTile);
    }

    @Override
    public int hashCode() {
        final int BASE = 31;
        int result = 17;
        result = BASE * result + from.hashCode();
        result = BASE * result + to.hashCode();
        result = BASE * result + floorTile.hashCode();
        result = BASE * result + wallTile.hashCode();
        return result;
    }
}
