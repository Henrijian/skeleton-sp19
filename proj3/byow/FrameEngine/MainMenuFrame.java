package byow.FrameEngine;

import byow.Core.Config;
import byow.Input.KeyboardInput;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;
import java.util.HashMap;

public class MainMenuFrame extends BaseFrame {
    private final HashMap<Character, MenuItem> menuItemKeyMap;

    private enum MenuItem {
        NEW_GAME, LOAD_GAME, QUIT
    }

    public MainMenuFrame (Config config) {
        super(config);
        this.menuItemKeyMap = new HashMap<>();
        menuItemKeyMap.put('n', MenuItem.NEW_GAME);
        menuItemKeyMap.put('l', MenuItem.LOAD_GAME);
        menuItemKeyMap.put('q', MenuItem.QUIT);
    }

    @Override
    public void show() {
        final Color BACKGROUND_COLOR = Color.BLACK;
        final Color PEN_COLOR = Color.WHITE;

        StdDraw.clear(BACKGROUND_COLOR);
        // Draw title.
        final Font TITLE_FONT = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(TITLE_FONT);
        StdDraw.setPenColor(PEN_COLOR);
        final int TITLE_X = width() / 2;
        final int TITLE_Y = (height() * 3) / 4;
        final String TITLE = "CS61B BYOW";
        StdDraw.text(TITLE_X, TITLE_Y, TITLE);
        // Draw menu items.
        final Font MENU_ITEM_FONT = new Font("Monaco", Font.BOLD, 20);
        final int MENU_ITEM_BORDER = MENU_ITEM_FONT.getSize();
        StdDraw.setFont(MENU_ITEM_FONT);
        StdDraw.setPenColor(PEN_COLOR);
        final int MENU_ITEM_X = width() / 2;
        // Draw "new game" menu item.
        int menuItemY = height() / 2;
        StdDraw.text(MENU_ITEM_X, menuItemY, "New Game(N)");
        // Draw "load saved game" menu item.
        menuItemY = menuItemY - MENU_ITEM_FONT.getSize() - MENU_ITEM_BORDER;
        StdDraw.text(MENU_ITEM_X, menuItemY, "Load(L)");
        // Draw "quit game" menu item.
        menuItemY = menuItemY - MENU_ITEM_FONT.getSize() - MENU_ITEM_BORDER;
        StdDraw.text(MENU_ITEM_X, menuItemY, "Quit(Q)");
        StdDraw.show();
    }

    @Override
    public void start() {
        show();
        KeyboardInput keyboardInput = new KeyboardInput(true);
        while (keyboardInput.possibleNextInput()) {
            char gotKey = Character.toLowerCase(keyboardInput.getNextKey());
            if (!menuItemKeyMap.containsKey(gotKey)) {
                continue;
            }
            MenuItem selectedItem = menuItemKeyMap.get(gotKey);
            boolean finished = true;
            switch (selectedItem) {
                case NEW_GAME:
                    nextFrame = new NewGameFrame(config);
                    break;
                case LOAD_GAME:
                    nextFrame = null;
                    break;
                case QUIT:
                    nextFrame = null;
                    break;
                default:
                    finished = false;
                    break;
            }
            if (finished) {
                return;
            }
        }
    }
}
