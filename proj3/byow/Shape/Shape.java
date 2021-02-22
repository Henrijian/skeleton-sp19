package byow.Shape;

import java.awt.*;

public interface Shape {
    /**
     * Get the rectangle boundary of shape.
     * */
    Boundary boundary();

    Point center();

    /**
     * Get the relationship between this shape and another shape.
     * @param other other shape
     * @return relation between two shapes.
     */
    Relation relationTo(Shape other);

    /**
     * Get the direction toward the other shape according to the center of each shape.
     * @param other other shape.
     * @return direction to other shape.
     */
    Direction directionTo(Shape other);

    /**
     * Get the closest distance between this and the other shape.
     * @param other other shape.
     * @return the closest distance between them.
     */
    double distanceTo(Shape other);

    /**
     * Get the closest point on this shape perimeter to the other shape.
     * @param other other shape.
     * @return the closest point on this shape perimeter.
     */
    Point closestPointTo(Shape other);

    /**
     * Get the area of this shape.
     */
    double area();
}
