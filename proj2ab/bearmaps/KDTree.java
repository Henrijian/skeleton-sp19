package bearmaps;

import bearmaps.Point;
import bearmaps.PointSet;

import java.util.HashSet;
import java.util.List;

public class KDTree implements PointSet {
    private class Rect {
        private double top;
        private double right;
        private double bottom;
        private double left;

        public Rect() {
            top = Double.MAX_VALUE;
            right = Double.MAX_VALUE;
            bottom = -Double.MAX_VALUE;
            left = -Double.MAX_VALUE;
        }

        public Rect(double top, double right, double bottom, double left) {
            if (top < bottom || right < left) {
                throw new IllegalArgumentException("top or right boundary of rectangle is smaller " +
                        "than bottom or left boundary of rectangle.");
            }
            this.top = top;
            this.right = right;
            this.bottom = bottom;
            this.left = left;
        }

        public double getTop() {
            return top;
        }

        public double getRight() {
            return right;
        }

        public double getBottom() {
            return bottom;
        }

        public double getLeft() {
            return left;
        }

        public void setTop(double top) {
            if (top < this.bottom) {
                throw new IllegalArgumentException("Setting top is smaller than bottom of rectangle.");
            }
            this.top = top;
        }

        public void setRight(double right) {
            if (right < this.left) {
                throw new IllegalArgumentException("Setting right is smaller than left of rectangle.");
            }
            this.right = right;
        }

        public void setBottom(double bottom) {
            if (bottom > this.top) {
                throw new IllegalArgumentException("Setting bottom is greater than top of rectangle.");
            }
            this.bottom = bottom;
        }

        public void setLeft(double left) {
            if (left > this.right) {
                throw new IllegalArgumentException("Setting left is greater than right of rectangle.");
            }
            this.left = left;
        }

        public boolean includePoint(Point p) {
            if (p == null) {
                return false;
            }
            return left <= p.getX() && p.getX() <= right && bottom <= p.getY() && p.getY() <= top;
        }

        public Point nearestPointTo(Point p) {
            if (p == null) {
                throw new IllegalArgumentException("Cannot find the nearest point to null in rectangle.");
            }

            double x;
            if (p.getX() < left) {
                x = left;
            } else if (right < p.getX()) {
                x = right;
            } else {
                x = p.getX();
            }

            double y;
            if (p.getY() < bottom) {
                y = bottom;
            } else if (top < p.getY()) {
                y = top;
            } else {
                y = p.getY();
            }

            return new Point(x, y);
        }

        public String toString() {
            return String.format("Top: %.10f, Right: %.10f, Bottom: %.10f, Left: %.10f", top, right, bottom, left);
        }
    }

    private class Node {
        public Point point;
        public Node right;
        public Node left;
        public boolean considerX;
        public Rect rect;

        public Node(Point point, boolean considerX, Rect rect) {
            if (point == null) {
                throw new IllegalArgumentException("Point for initiating Node cannot be null.");
            }
            if (rect == null) {
                throw new IllegalArgumentException("Rect for initiating Node cannot be null.");
            }
            if (!rect.includePoint(point)) {
                throw new IllegalArgumentException(String.format("Rect(%s) does not contain point(%s).", rect, point));
            }
            this.point = point;
            this.right = null;
            this.left = null;
            this.considerX = considerX;
            this.rect = rect;
        }

        public Rect leftRect() {
            if (considerX) {
                return new Rect(rect.top, point.getX(), rect.bottom, rect.left);
            } else {
                return new Rect(point.getY(), rect.right, rect.bottom, rect.left);
            }
        }

        public Rect rightRect() {
            if (considerX) {
                return new Rect(rect.top, rect.right, rect.bottom, point.getX());
            } else {
                return new Rect(rect.top, rect.right, point.getY(), rect.left);
            }
        }
    }

    /** Root of bearmaps.KDTree, break tie at right. */
    private Node root;

    public KDTree(List<Point> points) {
        for (Point point : points) {
            Point newPoint = new Point(point.getX(), point.getY());
            addPoint(newPoint);
        }
    }

    private Node addPoint(Node root, Point point, boolean considerX, Rect rect) {
        if (point == null) {
            return root;
        }
        if (root == null) {
            return new Node(point, considerX, rect);
        }
        Rect leftRect = root.leftRect();
        if (leftRect.includePoint(point)) {
            root.left = addPoint(root.left, point, !considerX, leftRect);
        } else {
            Rect rightRect = root.rightRect();
            root.right = addPoint(root.right, point, !considerX, rightRect);
        }
        return root;
    }

    private void addPoint(Point point) {
        if (root == null) {
            root = new Node(point, true, new Rect());
        } else {
            root = addPoint(root, point, root.considerX, root.rect);
        }
    }

    private Node nearest(Node root, Point goal, Node best) {
        if (goal == null) {
            return null;
        }
        if (root == null) {
            return best;
        }

        double bestDist = Point.distance(best.point, goal);
        double rootDist = Point.distance(root.point, goal);
        if (rootDist < bestDist) {
            best = root;
            bestDist = rootDist;
        }

        Node goodSide;
        Node badSide;
        Point goodPoint;
        Point badPoint;
        Point rootPoint = root.point;
        if ((root.considerX && goal.getX() < rootPoint.getX()) || (!root.considerX && goal.getY() < rootPoint.getY())) {
            goodSide = root.left;
            badSide = root.right;
            goodPoint = root.leftRect().nearestPointTo(goal);
            badPoint = root.rightRect().nearestPointTo(goal);
        } else {
            goodSide = root.right;
            badSide = root.left;
            goodPoint = root.rightRect().nearestPointTo(goal);
            badPoint = root.leftRect().nearestPointTo(goal);
        }

        double goodDist = Point.distance(goodPoint, goal);
        if (goodDist < bestDist) {
            best = nearest(goodSide, goal, best);
            bestDist = Point.distance(best.point, goal);;
        }

        double badDist = Point.distance(badPoint, goal);
        if (badDist < bestDist) {
            best = nearest(badSide, goal, best);
        }

        return best;
    }

    @Override
    public Point nearest(double x, double y) {
        if (root == null) {
            return null;
        }
        Point goal = new Point(x, y);
        Node nearestNode = nearest(root, goal, root);
        return nearestNode.point;
    }
}
