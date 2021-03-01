package byow.Core;

import byow.PriorityQueue.ArrayHeapMinPQ;
import byow.TileEngine.TETile;

import java.awt.*;
import java.nio.channels.Pipe;
import java.util.*;
import java.util.List;

public class PointUtils {
    public static boolean inBoundary(Point point, int boundMinX, int boundMinY, int boundMaxX, int boundMaxY) {
        if (point == null) {
            throw new IllegalArgumentException("Cannot check null point in boundary.");
        }
        return inBoundary(point.x, point.y, boundMinX, boundMinY, boundMaxX, boundMaxY);
    }

    public static boolean inBoundary(int x, int y, int boundMinX, int boundMinY, int boundMaxX, int boundMaxY) {
        return boundMinX <= x && x <= boundMaxX && boundMinY <= y && y <= boundMaxY;
    }

    public static Point[] verticalNeighbors(Point point) {
        if (point == null) {
            throw new IllegalArgumentException("Cannot get vertical neighbors from null point.");
        }
        Point[] result = new Point[4];
        result[0] = new Point(point.x, point.y + 1); // Top.
        result[1] = new Point(point.x + 1, point.y); // Right.
        result[2] = new Point(point.x, point.y - 1); // Bottom.
        result[3] = new Point(point.x - 1, point.y); // Left.
        return result;
    }

    public static Point[] diagonalNeighbors(Point point) {
        if (point == null) {
            throw new IllegalArgumentException("Cannot get diagonal neighbors from null point.");
        }
        Point[] result = new Point[4];
        result[0] = new Point(point.x + 1, point.y + 1); // Top right.
        result[1] = new Point(point.x + 1, point.y - 1); // Bottom right.
        result[2] = new Point(point.x - 1, point.y - 1); // Bottom left.
        result[3] = new Point(point.x - 1, point.y + 1); // Top Left.
        return result;
    }

    public static Point[] surroundNeighbors(Point point) {
        if (point == null) {
            throw new IllegalArgumentException("Cannot get surrounded neighbors from null point.");
        }
        Point[] verticalNeighbors = verticalNeighbors(point);
        Point[] diagonalNeighbors = diagonalNeighbors(point);
        Point[] result = new Point[verticalNeighbors.length + diagonalNeighbors.length];
        System.arraycopy(verticalNeighbors, 0, result, 0, verticalNeighbors.length);
        System.arraycopy(diagonalNeighbors, 0, result, verticalNeighbors.length, diagonalNeighbors.length);
        return result;
    }

    /**
     * Get the shortest trace which connect start and end points.
     * @param start start point of trace.
     * @param end end point of trace.
     * @param blocks blocked points, trace cannot contains these points.
     * @return list of traced points, null if theres is no trace between start and end points.
     */
    public static List<Point> shortestTrace(Point start, Point end, Set<Point> blocks) {
        if (start == null) {
            throw new IllegalArgumentException("Cannot get the shortest trace from null start point.");
        }
        if (end == null) {
            throw new IllegalArgumentException("Cannot get the shortest trace to null end point.");
        }
        ArrayHeapMinPQ<Point> distanceMinPQ = new ArrayHeapMinPQ<>();
        LinkedList<Point> trace = new LinkedList<>();
        distanceMinPQ.add(start, distanceSqrBetween(start, end));
        while (distanceMinPQ.size() > 0) {
            Point tracePos = distanceMinPQ.removeSmallest();
            trace.addLast(tracePos);
            if (tracePos.equals(end)) {
                break;
            }
            Point[] verticalPosList = PointUtils.verticalNeighbors(tracePos);
            for (Point verticalPos: verticalPosList) {
                if (distanceMinPQ.contains(verticalPos)) {
                    continue;
                }
                if (trace.contains(verticalPos)) {
                    continue;
                }
                if (blocks != null && blocks.contains(verticalPos)) {
                    continue;
                }
                int distanceSqr = PointUtils.distanceSqrBetween(verticalPos, end);
                distanceMinPQ.add(verticalPos, distanceSqr);
            }
        }
        // Check whether the trace connects start and end points.
        if (!trace.getFirst().equals(start) || !trace.getLast().equals(end)) {
            // Cannot get the trace which connects start and end points.
            return null;
        }
        // Validate trace, if the trace is continuous,
        // the distance of trace points next to each other is equal 1.
        Point prevPos = null;
        for (Point curPos: trace) {
            if (prevPos != null) {
                int distanceSqr = PointUtils.distanceSqrBetween(prevPos, curPos);
                if (distanceSqr != 1) {
                    return null;
                }
            }
            prevPos = curPos;
        }
        return trace;
    }

    public static int distanceSqrBetween(Point point1, Point point2) {
        int deltaX = point1.x - point2.x;
        int deltaY = point1.y - point2.y;
        return  deltaX * deltaX + deltaY * deltaY;
    }

    public static double distanceBetween(Point point1, Point point2) {
        return Math.sqrt(distanceSqrBetween(point1, point2));
    }
}
