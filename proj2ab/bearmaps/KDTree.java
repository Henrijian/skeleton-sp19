package bearmaps;

import bearmaps.Point;
import bearmaps.PointSet;

import java.util.HashSet;
import java.util.List;

public class KDTree implements PointSet {
    private class Node {
        public Point point;
        public Node right;
        public Node left;

        public Node(Point p) {
            point = p;
            right = null;
            left = null;
        }
    }

    /** Root of bearmaps.KDTree, break tie at right. */
    private Node root;

    public KDTree(List<Point> points) {
        for (int i = 0; i < points.size(); i++) {
            Point point = points.get(i);
            Point newPoint = new Point(point.getX(), point.getY());
            if (i == 0) {
                root = new Node(newPoint);
                continue;
            }
            addPoint(newPoint);
        }
    }

    private Node addPoint(Node tree, Point point, int level) {
        if (point == null) {
            return tree;
        }
        if (tree == null) {
            return new Node(point);
        }
        Point treePoint = tree.point;
        boolean considerX = level / 2 == 0;
        if (considerX) {
            if (point.getX() < treePoint.getX()) {
                tree.left = addPoint(tree.left, point, level++);
            } else {
                tree.right = addPoint(tree.right, point, level++);
            }
        } else {
            if (point.getY() < treePoint.getY()) {
                tree.left = addPoint(tree.left, point, level++);
            } else {
                tree.right = addPoint(tree.right, point, level++);
            }
        }
        return tree;
    }

    private void addPoint(Point point) {
        root = addPoint(root, point, 0);
    }

    @Override
    public Point nearest(double x, double y) {
        if (root == null) {
            return null;
        }
        return null;
    }
}
