package byow.lab13;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MemoryGame {
    private int width;
    private int height;
    private int round;
    private Random rand;
    private boolean gameOver;
    private boolean playerTurn;
    private final Color BACKGROUND_COLOR = Color.BLACK;
    private final Color PEN_COLOR = Color.WHITE;
    private final String FONT_FACE = "Monaco";
    private static final char[] CHARACTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final String[] ENCOURAGEMENT = {"You can do this!", "I believe in you!",
                                                   "You got this!", "You're a star!", "Go Bears!",
                                                   "Too easy for you!", "Wow, so impressive!"};

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please enter a seed");
            return;
        }

        int seed = Integer.parseInt(args[0]);
        MemoryGame game = new MemoryGame(40, 40, seed);
        game.startGame();
    }

    public MemoryGame(int width, int height, int seed) {
        /* Sets up StdDraw so that it has a width by height grid of 16 by 16 squares as its canvas
         * Also sets up the scale so the top left is (0,0) and the bottom right is (width, height)
         */
        if (width <= 0) {
            throw new IllegalArgumentException("Width for creating MemoryGame cannot be zero or negative.");
        }
        if (height <= 0) {
            throw new IllegalArgumentException("Height for creating MemoryGame cannot be zero or negative.");
        }
        if (seed <= 0) {
            throw new IllegalArgumentException("Seed for creating MemoryGame cannot be zero or negative.");
        }
        this.width = width;
        this.height = height;
        StdDraw.setCanvasSize(this.width * 16, this.height * 16);
        Font font = new Font(FONT_FACE, Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, this.width);
        StdDraw.setYscale(0, this.height);
        StdDraw.clear(BACKGROUND_COLOR);
        StdDraw.enableDoubleBuffering();

        // Initialize random number generator
        rand = new Random(seed);
    }

    /**
     * Generate random string of letters of length n
     * */
    public String generateRandomString(int n) {
        if (n <= 0) {
            return null;
        }
        StringBuilder resultBuilder = new StringBuilder();
        int remainingChars = n;
        while (remainingChars > 0) {
            int randIdx = rand.nextInt(CHARACTERS.length);
            char randChar = CHARACTERS[randIdx];
            resultBuilder.append(randChar);
            remainingChars--;
        }
        String result = resultBuilder.toString();
        return result;
    }

    /**
     * Take the string and display it in the center of the screen
     * If game is not over, display relevant game information at the top of the screen
     * */
    public void drawFrame(String s) {
        // Clear canvas.
        StdDraw.clear(BACKGROUND_COLOR);

        // Draw information of current status.
        StdDraw.setPenColor(PEN_COLOR);
        Font infoFont = new Font(FONT_FACE, Font.PLAIN, 20);
        StdDraw.setFont(infoFont);
        int infoRow = height - 1;
        // Draw round.
        String roundInfo = String.format("Round: %d", round);
        StdDraw.textLeft(1, infoRow, roundInfo);
        // Draw player turn.
        String playerInfo;
        if (playerTurn) {
            playerInfo = "Type!";
        } else {
            playerInfo = "Watch!";
        }
        StdDraw.text(width / 2, infoRow, playerInfo);
        // Draw encouraging text.
        Random random = new Random();
        int randIdx = random.nextInt(ENCOURAGEMENT.length);
        String encouragingInfo = ENCOURAGEMENT[randIdx];
        StdDraw.textRight(width - 1, infoRow, encouragingInfo);
        // Draw divider.
        StdDraw.line(0, infoRow - 1, width, infoRow - 1);

        // Draw String s character by character.
        StdDraw.setPenColor(PEN_COLOR);
        Font strFont = new Font(FONT_FACE, Font.BOLD, 30);
        StdDraw.setFont(strFont);
        StdDraw.text(width / 2, height / 2, s);

        // Show canvas.
        StdDraw.show();
    }

    /**
     * Display each character in letters, making sure to blank the screen between letters
     */
    public void flashSequence(String letters) {
        try {
            for (char letter: letters.toCharArray()) {
                drawFrame(String.valueOf(letter));
                TimeUnit.MILLISECONDS.sleep(1000);
                drawFrame("");
                TimeUnit.MILLISECONDS.sleep(500);
            }
        } catch (InterruptedException e) {
            // do nothing.
        }
    }

    /**
     * Read n letters of player input
     */
    public String solicitNCharsInput(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Number n for requiring key typed cannot be negative.");
        }
        StringBuilder resultBuilder = new StringBuilder();
        for (int keyCount = 0; keyCount < n; keyCount++) {
            while (!StdDraw.hasNextKeyTyped()) {
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    // do nothing.
                }
            }
            resultBuilder.append(StdDraw.nextKeyTyped());
            drawFrame(resultBuilder.toString());
        }
        String result = resultBuilder.toString();
        return result;
    }

    public void startGame() {
        //Set any relevant variables before the game starts
        round = 0;
        gameOver = false;

        //Establish Engine loop
        while (!gameOver) {
            try {
                // show round message.
                round++;
                drawFrame(String.format("Round %d, good luck!", round));
                TimeUnit.MILLISECONDS.sleep(1000);
                // generating question
                playerTurn = false;
                String expectStr = generateRandomString(round);
                flashSequence(expectStr);
                // get answer
                playerTurn = true;
                String actualStr = solicitNCharsInput(round);
                // check answer
                gameOver = !expectStr.equals(actualStr);
                if (!gameOver) {
                    drawFrame("Correct! Well done!");
                    TimeUnit.MILLISECONDS.sleep(1000);
                }
            } catch (InterruptedException e) {
                // do nothing
            }
        }
        drawFrame(String.format("Game Over! You made it to round: %d", round));
    }

}
