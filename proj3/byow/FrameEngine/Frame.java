package byow.FrameEngine;

public interface Frame {
    /**
     * @return Width of this frame in pixels.
     */
    int width();

    /**
     * @return Height of this frame in pixels.
     */
    int height();

    /**
     * Show this frame.
     */
    void show();

    /**
     * Executing this frame.
     */
    void start();

    /**
     * Is there having next frame resulting from this frame.
     * @return True, if there have any frame, else false.
     */
    boolean possibleNextFrame();

    /**
     * If possibleNextFrame is true, return the resulting next frame, else throwing an exception.
     * @return The resulting next frame, else an exception.
     */
    Frame getNextFrame();
}
