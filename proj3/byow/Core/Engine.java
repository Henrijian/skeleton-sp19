package byow.Core;

import byow.Input.InputDevice;
import byow.Input.KeyboardInput;
import byow.Input.StringInput;
import byow.UserInterfaceEngine.UserInterface;
import byow.UserInterfaceEngine.MainMenuInterface;
import byow.UserInterfaceEngine.WorldInterface;
import byow.TileEngine.TETile;

import java.util.LinkedList;

public class Engine {
    /* Feel free to change the width and height. */
    public static final int WORLD_WIDTH = 80;
    public static final int WORLD_HEIGHT = 30;
    public static final int FRAME_WIDTH = 1280;
    public static final int FRAME_HEIGHT = 496;

    private final Config config;
    private TETile[][] worldTiles; // Tiles of world representing last state of world.

    public Engine () {
        this.config = new Config(WORLD_WIDTH, WORLD_HEIGHT, FRAME_WIDTH, FRAME_HEIGHT, false);
        this.worldTiles = null;
    }

    /**
     * Executing engine by specified input.
     * @param input input.
     */
    private void start(InputDevice input) {
        if (input == null) {
            throw new IllegalArgumentException("Cannot start engine with null input.");
        }
        LinkedList<UserInterface> userInterfaces = new LinkedList<>();
        userInterfaces.addLast(new MainMenuInterface(config));
        while (userInterfaces.size() > 0) {
            UserInterface userInterface = userInterfaces.removeFirst();
            userInterface.start(input);
            if (userInterface instanceof WorldInterface) {
                this.worldTiles = ((WorldInterface) userInterface).worldTiles();
            }
            if (userInterface.possibleNextInterface()) {
                userInterfaces.addLast(userInterface.getNextInterface());
            }
        }
    }

    /**
     * Method used for exploring a fresh world. This method should handle all inputs,
     * including inputs from the main menu.
     */
    public void interactWithKeyboard() {
        KeyboardInput keyboardInput = new KeyboardInput(true);
        start(keyboardInput);
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
        this.config.hideInterface = true;
        StringInput stringInput = new StringInput(input);
        start(stringInput);
        return worldTiles;
    }

    public String toString() {
        return TETile.toString(worldTiles);
    }
}
