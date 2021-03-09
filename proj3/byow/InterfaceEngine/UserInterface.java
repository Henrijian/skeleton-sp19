package byow.InterfaceEngine;

import byow.Input.InputDevice;

public interface UserInterface {
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
    void start(InputDevice inputDevice);

    /**
     * Is there having next frame resulting from this frame.
     * @return True, if there have any frame, else false.
     */
    boolean possibleNextInterface();

    /**
     * If possibleNextFrame is true, return the resulting next frame, else throwing an exception.
     * @return The resulting next frame, else an exception.
     */
    UserInterface getNextInterface();
}
