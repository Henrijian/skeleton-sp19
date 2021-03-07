package byow.Input;

public interface InputDevice {
    /**
     * The next character of input.
     * @return Next character of input, if there is not remaining characters of input, throw an exception.
     */
    char getNextKey();

    /**
     * Is there having next character in input.
     * @return True, if there are remaining characters of input, else false.
     */
    boolean possibleNextInput();
}
