package byow.Core;

import byow.Entity.World;
import byow.FrameEngine.Frame;
import byow.FrameEngine.MainMenuFrame;
import byow.FrameEngine.WorldFrame;
import byow.TileEngine.TETile;

import java.util.LinkedList;

public class Engine {
    /* Feel free to change the width and height. */
    public static final int WORLD_WIDTH = 80;
    public static final int WORLD_HEIGHT = 30;
    public static final int FRAME_WIDTH = 1280;
    public static final int FRAME_HEIGHT = 496;

    private Config config;

    public Engine() {
        this.config = new Config(WORLD_WIDTH, WORLD_HEIGHT, FRAME_WIDTH, FRAME_HEIGHT);
    }

    /**
     * Method used for exploring a fresh world. This method should handle all inputs,
     * including inputs from the main menu.
     */
    public void interactWithKeyboard() {
        LinkedList<Frame> frames = new LinkedList<>();
        frames.addLast(new MainMenuFrame(config));
        while (frames.size() > 0) {
            Frame frame = frames.removeFirst();
            frame.start();
            if (frame.possibleNextFrame()) {
                frames.addLast(frame.getNextFrame());
            }
        }
    }

    /**
     * Method used for autograding and testing your code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The engine should
     * behave exactly as if the user typed these characters into the engine using
     * interactWithKeyboard.
     *
     * Recall that strings ending in ":q" should cause the game to quite save. For example,
     * if we do interactWithInputString("n123sss:q"), we expect the game to run the first
     * 7 commands (n123sss) and then quit and save. If we then do
     * interactWithInputString("l"), we should be back in the exact same state.
     *
     * In other words, both of these calls:
     *   - interactWithInputString("n123sss:q")
     *   - interactWithInputString("lww")
     *
     * should yield the exact same world state as:
     *   - interactWithInputString("n123sssww")
     *
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] interactWithInputString(String input) {
        // TODO: Fill out this method so that it run the engine using the input
        // passed in as an argument, and return a 2D tile representation of the
        // world that would have been drawn if the same inputs had been given
        // to interactWithKeyboard().
        //
        // See proj3.byow.InputDemo for a demo of how you can make a nice clean interface
        // that works for many different input types.
        long seed = Long.parseLong(input);
        World world = new World(WORLD_WIDTH, WORLD_HEIGHT);
        world.randWorld(seed);
        return world.tiles();
    }
}
