package byow.InterfaceEngine;

import byow.Core.Config;
import edu.princeton.cs.introcs.StdDraw;

import java.util.NoSuchElementException;

abstract class BaseInterface implements UserInterface {
    Config config;
    UserInterface nextUserInterface;

    public BaseInterface (Config config) {
        if (config == null) {
            throw new IllegalArgumentException("Cannot initiate base frame without config.");
        }
        this.config = config;
        if (!config.hideInterface) {
            initializeCanvas();
        }
        this.nextUserInterface = null;
    }

    private void initializeCanvas() {
        StdDraw.setCanvasSize(width(), height());
        StdDraw.setXscale(0, width());
        StdDraw.setYscale(0, height());
        StdDraw.enableDoubleBuffering();
    }

    @Override
    public int width() {
        return this.config.frameWidth;
    }

    @Override
    public int height() {
        return this.config.frameHeight;
    }

    @Override
    public boolean possibleNextInterface() {
        return nextUserInterface != null;
    }

    @Override
    public UserInterface getNextInterface() {
        if (nextUserInterface == null) {
            throw new NoSuchElementException("There is no next frame can be accessed.");
        }
        return nextUserInterface;
    }
}
