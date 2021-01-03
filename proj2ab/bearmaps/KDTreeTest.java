package bearmaps;

import edu.princeton.cs.algs4.Stopwatch;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class KDTreeTest {
    @Test
    public void constructorTest() {
        Point p1 = new Point(1.1, 2.2); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(3.3, 4.4);
        Point p3 = new Point(-2.9, 4.2);
        Point p4 = new Point(4.4, 3.3);
        Point p5 = new Point(5.5, 5.5);

        KDTree pointSet = new KDTree(List.of(p1, p2, p3, p4, p5));
    }

    @Test
    public void nearestTest() {
        Random random = new Random();
        ArrayList<Point> points = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            double x = random.nextDouble();
            double y = random.nextDouble();
            Point point = new Point(x, y);
            points.add(point);
        }
        ArrayList<Point> goals = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            double x = random.nextDouble();
            double y = random.nextDouble();
            Point goal = new Point(x, y);
            goals.add(goal);
        }

        Stopwatch naiveStopWatch = new Stopwatch();
        NaivePointSet naivePoints = new NaivePointSet(points);
        ArrayList<Point> naiveNearestPoints = new ArrayList<>();
        for (Point goal: goals) {
            naiveNearestPoints.add(naivePoints.nearest(goal.getX(), goal.getY()));
        }
        double naiveTime = naiveStopWatch.elapsedTime();

        Stopwatch KDStopWatch = new Stopwatch();
        KDTree KDPoints = new KDTree(points);
        ArrayList<Point> KDNearestPoints = new ArrayList<>();
        for (Point goal: goals) {
            KDNearestPoints.add(KDPoints.nearest(goal.getX(), goal.getY()));
        }
        double KDTime = KDStopWatch.elapsedTime();

        Assert.assertEquals(naiveNearestPoints.size(), KDNearestPoints.size());
        for (int i = 0; i < naiveNearestPoints.size(); i++) {
            Point naiveNearestPoint = naiveNearestPoints.get(i);
            Point KDNearestPoint = KDNearestPoints.get(i);
            Assert.assertEquals(naiveNearestPoint.getX(), KDNearestPoint.getX(), 0);
            Assert.assertEquals(naiveNearestPoint.getY(), KDNearestPoint.getY(), 0);
        }

        System.out.println(String.format("Naive point set's elapsed time: %.10f seconds", naiveTime));
        System.out.println(String.format("K-D tree's elapsed time: %.10f seconds", KDTime));
    }
}
