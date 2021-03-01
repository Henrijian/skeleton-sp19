package byow.Shape;
import byow.TileEngine.TETile;

import java.awt.*;

public class Rectangle implements Shape {
    public final Point leftBottomCorner;
    public final int width;
    public final int height;

    /**
     * Initiate a rectangle with its left bottom corner at (x, y) and with w width and h height.
     * */
    public Rectangle(int x, int y, int w, int h) {
        if (w <= 0) {
            throw new IllegalArgumentException(String.format("Width must be positive, w is %d", w));
        }
        if (h <= 0) {
            throw new IllegalArgumentException(String.format("Height must be positive, h is %s", h));
        }
        this.leftBottomCorner = new Point(x, y);
        this.width = w;
        this.height = h;
    }

    @Override
    public Boundary boundary() {
        int minX = leftBottomCorner.x;
        int minY = leftBottomCorner.y;
        int maxX = leftBottomCorner.x + width;
        int maxY = leftBottomCorner.y + height;
        return new Boundary(minX, minY, maxX, maxY);
    }

    @Override
    public Point center() {
        int centerX = leftBottomCorner.x + width / 2;
        int centerY = leftBottomCorner.y + height / 2;
        return new Point(centerX, centerY);
    }

    @Override
    public Relation relationTo(Shape other) {
        if (other == null) {
            throw new IllegalArgumentException("Cannot get relation with null shape.");
        }
        if (other instanceof Rectangle) {
            Boundary otherBound = other.boundary();
            Boundary myBound = boundary();
            if (myBound.maxX < otherBound.minX || otherBound.maxX < myBound.minX ||myBound.maxY < otherBound.minY || otherBound.maxY < myBound.minY) {
                return Relation.DISJOINT;
            } else if (otherBound.minX < myBound.minX && myBound.maxX < otherBound.maxX && otherBound.minY < myBound.minY && myBound.maxY < otherBound.maxY) {
                return Relation.A_IN_B;
            } else if (myBound.minX < otherBound.minX && otherBound.maxX < myBound.maxX && myBound.minY < otherBound.minY && otherBound.maxY < myBound.maxY) {
                return Relation.B_IN_A;
            } else {
                return Relation.INTERSECT;
            }
        } else {
            throw new IllegalArgumentException("Unsupported shape for getting relation with rectangle.");
        }
    }

    @Override
    public Direction directionTo(Shape other) {
        if (other == null) {
            throw new IllegalArgumentException("Cannot get direction to null shape.");
        }
        Boundary otherBound = other.boundary();
        Point otherCenter = otherBound.center();
        Point myCenter = center();
        int deltaX = otherCenter.x - myCenter.x;
        int deltaY = otherCenter.y - myCenter.y;
        double angle = Math.toDegrees(Math.atan2(deltaY, deltaX));
        if (angle < 0) {
            angle = angle + 360;
        }
        if (0 < angle && angle < 90) {
            return Direction.RIGHT_TOP;
        } else if (angle == 90) {
            return Direction.TOP;
        } else if (90 < angle && angle < 180) {
            return Direction.LEFT_TOP;
        } else if (angle == 180) {
            return Direction.LEFT;
        } else if (180 < angle && angle < 270) {
            return Direction.LEFT_BOTTOM;
        } else if (angle == 270) {
            return Direction.BOTTOM;
        } else if (270 < angle && angle < 360) {
            return Direction.RIGHT_BOTTOM;
        } else {
            return Direction.RIGHT;
        }
    }

    /**
     * @source https://www.programmersought.com/article/44554760896/
     */
    @Override
    public double distanceTo(Shape other) {
        if (other == null) {
            throw new IllegalArgumentException("Cannot get distance to null shape.");
        }
        double distance;
        if (other instanceof Rectangle) {
            Rectangle otherRect = (Rectangle) other;
            Rectangle myRect = this;
            Point otherCenter = otherRect.center();
            Point myCenter = myRect.center();
            int centerDeltaX = Math.abs(otherCenter.x - myCenter.x);
            int centerDeltaY = Math.abs(otherCenter.y - myCenter.y);
            int minDisjointDistX = (myRect.width + otherRect.width) / 2;
            int minDisjointDistY = (myRect.height + otherRect.height) / 2;
            // Two rectangles do not intersect, and there are two rectangles partially overlapping in the X-axis direction.
            // The minimum distance is the distance between the lower line of the upper rectangle and the upper line of the lower rectangle.
            if (centerDeltaX < minDisjointDistX && centerDeltaY >= minDisjointDistY) {
                distance = centerDeltaY - minDisjointDistY;
            }
            // Two rectangles do not intersect. There are two partially overlapping rectangles in the Y-axis direction.
            // The minimum distance is the distance between the right line of the left rectangle and the left line of the right rectangle.
            else if (centerDeltaY < minDisjointDistY && centerDeltaX >= minDisjointDistX) {
                distance = centerDeltaX - minDisjointDistX;
            }
            // Two rectangles do not intersect, two rectangles that do not overlap in the X-axis and Y-axis directions,
            // the minimum distance is the distance between the two closest vertices.
            // Using the Pythagorean theorem, it is easy to calculate this distance
            else if (centerDeltaX >= minDisjointDistX && centerDeltaY >= minDisjointDistY) {
                int borderDeltaX = centerDeltaX - minDisjointDistX;
                int borderDeltaY = centerDeltaY - minDisjointDistY;
                int borderDeltaXSqr = borderDeltaX * borderDeltaX;
                int borderDeltaYSqr = borderDeltaY * borderDeltaY;
                distance = Math.sqrt(borderDeltaXSqr + borderDeltaYSqr);
            }
            // The intersection of two rectangles, the minimum distance is negative, return -1.
            else {
                distance = -1;
            }
            return distance;
        } else {
            throw new IllegalArgumentException("Unsupported shape for getting distance from rectangle.");
        }
    }

    /**
     * @source https://www.programmersought.com/article/44554760896/
     */
    @Override
    public Point closestPointTo(Shape other) {
        if (other == null) {
            throw new IllegalArgumentException("Cannot get closest point to null shape.");
        }
        Point closestPoint;
        if (other instanceof Rectangle) {
            Rectangle otherRect = (Rectangle) other;
            Rectangle myRect = this;
            Point otherCenter = otherRect.center();
            Point myCenter = myRect.center();
            int centerDeltaX = Math.abs(otherCenter.x - myCenter.x);
            int centerDeltaY = Math.abs(otherCenter.y - myCenter.y);
            int minDisjointDistX = (myRect.width + otherRect.width) / 2;
            int minDisjointDistY = (myRect.height + otherRect.height) / 2;
            // Two rectangles do not intersect, and there are two rectangles partially overlapping in the X-axis direction.
            // The closest point is on the lower or the upper line of this rectangle.
            if (centerDeltaX < minDisjointDistX && centerDeltaY >= minDisjointDistY) {
                int myMinX = myRect.leftBottomCorner.x;
                int myMaxX = myRect.leftBottomCorner.x + myRect.width - 1;
                int otherMinX = otherRect.leftBottomCorner.x;
                int closestX = myMinX;
                if (myMinX <= otherMinX && otherMinX <= myMaxX) {
                    closestX = otherMinX;
                }
                int closestY = myRect.leftBottomCorner.y;
                if (myCenter.y < otherCenter.y) {
                    closestY = myRect.leftBottomCorner.y + myRect.height - 1;
                }
                closestPoint = new Point(closestX, closestY);
            }
            // Two rectangles do not intersect. There are two partially overlapping rectangles in the Y-axis direction.
            // The closest point is on the left or the right line of this rectangle.
            else if (centerDeltaY < minDisjointDistY && centerDeltaX >= minDisjointDistX) {
                int myMinY = myRect.leftBottomCorner.y;
                int myMaxY = myRect.leftBottomCorner.y + myRect.height - 1;
                int otherMinY = otherRect.leftBottomCorner.y;
                int closestX = myRect.leftBottomCorner.x;
                if (myCenter.x <= otherCenter.x) {
                    closestX = myRect.leftBottomCorner.x + myRect.width - 1;
                }
                int closestY = myMinY;
                if (myMinY <= otherMinY && otherMinY <= myMaxY) {
                    closestY = otherMinY;
                }
                closestPoint = new Point(closestX, closestY);
            }
            // Two rectangles do not intersect, two rectangles that do not overlap in the X-axis and Y-axis directions,
            // the closest point is on the corner of this rectangle.
            else if (centerDeltaX >= minDisjointDistX && centerDeltaY >= minDisjointDistY) {
                int closestX = myRect.leftBottomCorner.x;
                if (myCenter.x <= otherCenter.x) {
                    closestX = myRect.leftBottomCorner.x + myRect.width - 1;
                }
                int closestY = myRect.leftBottomCorner.y;
                if (myCenter.y < otherCenter.y) {
                    closestY = myRect.leftBottomCorner.y + myRect.height - 1;
                }
                closestPoint = new Point(closestX, closestY);
            }
            // The intersection of two rectangles, the closest point cannot be determined, return null.
            else {
                closestPoint = null;
            }
            return closestPoint;
        } else {
            throw new IllegalArgumentException("Unsupported shape for getting closest point on rectangle.");
        }
    }

    @Override
    public double area() {
        return width * height;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Rectangle)) {
            return false;
        }
        Rectangle otherRect = (Rectangle) other;
        return leftBottomCorner.equals(otherRect.leftBottomCorner)
                && width == otherRect.width && height == otherRect.height;
    }

    @Override
    public int hashCode() {
        final int BASE = 31;
        int result = 17;
        result = result * BASE + leftBottomCorner.hashCode();
        result = result * BASE + Integer.hashCode(width);
        result = result * BASE + Integer.hashCode(height);
        return result;
    }
}
