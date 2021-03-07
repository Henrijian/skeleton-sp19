package byow.FrameEngine;

import byow.Core.Config;
import edu.princeton.cs.introcs.StdDraw;

import java.util.NoSuchElementException;

abstract class BaseFrame implements Frame {
    Config config;
    Frame nextFrame;

    public BaseFrame(Config config) {
        if (config == null) {
            throw new IllegalArgumentException("Cannot initiate base frame without config.");
        }
        this.config = config;
        StdDraw.setCanvasSize(width(), height());
        StdDraw.setXscale(0, width());
        StdDraw.setYscale(0, height());
        StdDraw.enableDoubleBuffering();
        this.nextFrame = null;
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
    public boolean possibleNextFrame() {
        return nextFrame != null;
    }

    @Override
    public Frame getNextFrame() {
        if (nextFrame == null) {
            throw new NoSuchElementException("There is no next frame can be accessed.");
        }
        return nextFrame;
    }
}
