package byow.Shape;

import java.awt.*;

public class Boundary extends Rectangle {
    public final int minX;
    public final int minY;
    public final int maxX;
    public final int maxY;

    /**
     * Rectangle boundary with 4 corner (minX, minY), (minX, maxY), (maxX, minY) and (maxX, maxY)
     * */
    public Boundary(int minX, int minY, int maxX, int maxY) {
        super(minX, minY, maxX - minX, maxY - minY);
        if (maxX <= minX) {
            throw new IllegalArgumentException("Minimum X greater than maximum X, minX = " + minX + ", maxX = " + maxX);
        }
        if (maxY <= minY) {
            throw new IllegalArgumentException("Minimum Y greater than maximum Y, minY = " + minY + ", maxY = " + maxY);
        }
        this.minX = minX;
        this.minY = minY;
        this.maxX = maxX;
        this.maxY = maxY;
    }
}
