package bearmaps;

import java.util.HashSet;
import java.util.List;

public class NaivePointSet implements PointSet {
    private final HashSet<Point> pointSet;

    public NaivePointSet(List<Point> points) {
        pointSet = new HashSet<>();
        pointSet.addAll(points);
    }

    @Override
    public Point nearest(double x, double y) {
        Point goal = new Point(x, y);
        double bestSoFar = Double.MAX_VALUE;
        Point bestPoint = null;
        for (Point p: pointSet) {
            double dist = Point.distance(p, goal);
            if (dist < bestSoFar) {
                bestSoFar = dist;
                bestPoint = p;
            }
        }
        return bestPoint;
    }
}
