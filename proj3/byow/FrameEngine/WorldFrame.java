package byow.FrameEngine;

import byow.Core.Config;
import byow.Entity.World;
import byow.Input.KeyboardInput;
import byow.TileEngine.TETile;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class WorldFrame extends BaseFrame {
    /**
     * Status bar height in row count.
     */
    private final int STATUS_BAR_ROW_COUNT = 1;
    /**
     * Side size of tile(square) in pixel.
     */
    private final int minX;
    private final int maxX;
    private final int minY;
    private final int maxY;
    private final int tileSize;
    private final World world;
    private final TETile[][] tiles;
    private Mode mode;
    private String inputCommand;

    private enum Direction {
        UP, RIGHT, BOTTOM, LEFT
    }

    private enum Mode {
        QUERY_DIRECTION, QUERY_COMMAND
    }

    private class RefreshTask extends TimerTask {
        private final Runnable command;

        public RefreshTask(Runnable command) {
            this.command = command;
        }

        @Override
        public void run () {
            if (command != null) {
                command.run();
            }
        }
    }

    public WorldFrame(Config config, long seed) {
        super(config);
        this.minX = 0;
        this.maxX = config.worldWidth;
        this.minY = 0;
        this.maxY = config.worldHeight + STATUS_BAR_ROW_COUNT;
        int sizeX = maxX - minX + 1;
        int sizeY = maxY - minY + 1;
        this.tileSize = Math.min(width() / sizeX, height() / sizeY);
        StdDraw.setCanvasSize(width(), height());
        StdDraw.setXscale(minX, maxX);
        StdDraw.setYscale(minY, maxY);
        this.world = new World(config.worldWidth, config.worldHeight);
        this.world.randWorld(seed);
        this.tiles = world.tiles();
        this.mode = Mode.QUERY_DIRECTION;
        this.inputCommand = "";
    }

    private void showStatus() {
        // Draw status bar background.
        final Color STATUS_BAR_COLOR = Color.BLACK;
        StdDraw.setPenColor(STATUS_BAR_COLOR);
        double statusBarWidth = maxX - minX + 1;
        double statusBarHeight = STATUS_BAR_ROW_COUNT;
        double statusBarCenterX = minX + (statusBarWidth / 2);
        double statusBarCenterY =  maxY - (statusBarHeight / 2);
        StdDraw.filledRectangle(statusBarCenterX, statusBarCenterY, statusBarWidth / 2, statusBarHeight / 2);
        // Draw mouse currently pointing tile.
        final Color PEN_COLOR = Color.WHITE;
        StdDraw.setPenColor(PEN_COLOR);
        final Font STATUS_TEXT_FONT = new Font("Monaco", Font.BOLD, tileSize - 2);
        StdDraw.setFont(STATUS_TEXT_FONT);
        int tileX = (int) Math.floor(StdDraw.mouseX());
        int tileY = (int) Math.floor(StdDraw.mouseY());
        String tileStr;
        if (0 < tiles.length && tileX < tiles.length && tileY < tiles[0].length) {
            tileStr = tiles[tileX][tileY].description();
        } else {
            tileStr = "";
        }
        StdDraw.textLeft(minX, statusBarCenterY, tileStr);
        // Draw bottom line of status bar.
        final double STATUS_BAR_BOTTOM_LINE_Y = maxY - STATUS_BAR_ROW_COUNT;
        StdDraw.line(minX, STATUS_BAR_BOTTOM_LINE_Y, maxX, STATUS_BAR_BOTTOM_LINE_Y);
        StdDraw.show();
    }

    private void showWorldTiles() {
        TETile[][] worldTiles = world.tiles();

        // Draw world tiles background.
        final Color WORLD_COLOR = Color.BLACK;
        StdDraw.setPenColor(WORLD_COLOR);
        double worldWidth = maxX - minX + 1;
        double worldHeight = maxY - minY + 1 - STATUS_BAR_ROW_COUNT;
        double worldCenterX = (minX + maxX) / 2;
        double worldCenterY = (minY + maxY - STATUS_BAR_ROW_COUNT) / 2;
        StdDraw.filledRectangle(worldCenterX, worldCenterY, worldWidth / 2, worldHeight / 2);
        // Draw tiles of world.
        int worldXSize = worldTiles.length;
        if (worldWidth <= 0) {
            return;
        }
        int worldYSize = worldTiles[0].length;
        for (int x = 0; x < worldXSize; x += 1) {
            for (int y = 0; y < worldYSize; y += 1) {
                if (worldTiles[x][y] == null) {
                    throw new IllegalArgumentException("Tile at position x=" + x + ", y=" + y
                            + " is null.");
                }
                worldTiles[x][y].draw(x, y);
            }
        }
        StdDraw.show();
    }

    @Override
    public void show() {
        final Color BACKGROUND_COLOR = Color.BLACK;
        StdDraw.clear(BACKGROUND_COLOR);
        showStatus();
        showWorldTiles();
    }

    @Override
    public void start() {
        show();
        TimerTask refreshStatusTask = new RefreshTask(this::showStatus);
        Timer refreshTimer = new Timer();
        try {
            refreshTimer.scheduleAtFixedRate(refreshStatusTask, new Date(), 100);
            KeyboardInput keyboardInput = new KeyboardInput(true);
            while (keyboardInput.possibleNextInput()) {
                char gotKey = Character.toLowerCase(keyboardInput.getNextKey());
                break;
            }
        } finally {
            refreshTimer.cancel();
        }
    }
}
