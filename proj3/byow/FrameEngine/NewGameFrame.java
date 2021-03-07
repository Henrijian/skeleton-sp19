package byow.FrameEngine;

import byow.Core.Config;
import byow.Input.KeyboardInput;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;
import java.util.HashMap;

public class NewGameFrame extends BaseFrame {
    private final HashMap<Character, Action> actionKeyMap;
    private long seed;

    private enum Action {
        GO_BACK, START_GAME
    }

    public NewGameFrame(Config config) {
        super(config);
        this.actionKeyMap = new HashMap<>();
        actionKeyMap.put('b', Action.GO_BACK);
        actionKeyMap.put('s', Action.START_GAME);
        this.seed = -1;
    }

    @Override
    public void show() {
        final Color BACKGROUND_COLOR = Color.BLACK;
        StdDraw.clear(BACKGROUND_COLOR);
        final Color PEN_COLOR = Color.WHITE;
        StdDraw.setPenColor(PEN_COLOR);
        final Font TEXT_FONT = new Font("Monaco", Font.BOLD, 20);
        StdDraw.setFont(TEXT_FONT);

        // Draw "go back" menu item.
        final int BORDER = 10;
        final int MENU_ITEM_X = BORDER;
        final int MENU_ITEM_Y = height() - TEXT_FONT.getSize() - BORDER;
        final String MENU_ITEM_CAPTION = "Back(B)";
        StdDraw.textLeft(MENU_ITEM_X, MENU_ITEM_Y, MENU_ITEM_CAPTION);
        // Draw seed.
        final int SEED_X = width() / 2;
        int seedY = (height() * 2) / 3;
        final String SEED_LABEL = "Seed:";
        StdDraw.text(SEED_X, seedY, SEED_LABEL);
        if (seed >= 0) {
            seedY = seedY - TEXT_FONT.getSize() * 2;
            String seedStr = Long.toString(seed);
            StdDraw.text(SEED_X, seedY, seedStr);
        }
        // Draw description.
        final int DESCRIPTION_X = width() / 2;
        int descriptionY = height() / 3;
        final String DESCRIPTION_1 = "Enter the integer of seed for generating world.";
        StdDraw.text(DESCRIPTION_X, descriptionY, DESCRIPTION_1);
        descriptionY = descriptionY - TEXT_FONT.getSize() * 2;
        final String DESCRIPTION_2 = "Press \"s\" to finish entering.";
        StdDraw.text(DESCRIPTION_X, descriptionY, DESCRIPTION_2);

        StdDraw.show();
    }

    @Override
    public void start() {
        show();
        KeyboardInput keyboardInput = new KeyboardInput(true);
        while (keyboardInput.possibleNextInput()) {
            char gotKey = Character.toLowerCase(keyboardInput.getNextKey());
            if (actionKeyMap.containsKey(gotKey)) {
                Action selectedAction = actionKeyMap.get(gotKey);
                boolean finished = true;
                switch (selectedAction) {
                    case GO_BACK:
                        nextFrame = new MainMenuFrame(config);
                        break;
                    case START_GAME:
                        nextFrame = new WorldFrame(config, seed);
                        break;
                    default:
                        finished = false;
                }
                if (finished) {
                    return;
                }
            } else if ('0' <= gotKey && gotKey <= '9') { // if key is number.
                String keyStr = Character.toString(gotKey);
                long keyNum = Long.parseLong(keyStr);
                if (seed < 0) {
                    seed = keyNum;
                } else {
                    seed = seed * 10 + keyNum;
                }
                show();
            }
        }
    }
}
