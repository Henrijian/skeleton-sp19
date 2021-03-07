package byow.Input;

import edu.princeton.cs.introcs.StdDraw;

public class KeyboardInput implements InputDevice {
    private final boolean printTypeKeys;

    public KeyboardInput(boolean printTypeKeys) {
        this.printTypeKeys = printTypeKeys;
    }

    public char getNextKey() {
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char returnChar = StdDraw.nextKeyTyped();
                if (printTypeKeys) {
                    System.out.print(returnChar);
                }
                return returnChar;
            }
        }
    }

    public boolean possibleNextInput() {
        return true;
    }
}
