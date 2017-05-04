package com.gamefactoryx.cheers.manager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.gamefactoryx.cheers.view.AbstractScreen;

/**
 * Created by bernat on 28.04.2017.
 */
@SuppressWarnings("DefaultFileTemplate")
public class ScreenManager {

    // Singleton: unique instance
    private static ScreenManager instance;

    // Reference to game
    private Game game;

    // Singleton: private constructor
    private ScreenManager() {
        super();
    }

    // Singleton: retrieve instance
    public static ScreenManager getInstance() {
        if (instance == null) {
            instance = new ScreenManager();
        }
        return instance;
    }

    // Initialization with the game class
    public void initialize(Game game) {
        this.game = game;
    }

    // Show in the game the screen which enum type is received
    public void showScreen(ScreenEnum screenEnum) {

        // Get current screen to dispose it
        Screen currentScreen = game.getScreen();
        AbstractScreen newScreen = null;
        // Show new screen
        switch (screenEnum) {
            case MAIN_SCREEN:
                newScreen = ScreenEnum.MAIN_SCREEN.getScreen();
                break;
            case NEW_GAME_SCREEN:
                newScreen = ScreenEnum.NEW_GAME_SCREEN.getScreen();
                break;
            case I_NEVER_DO_SCREEN:
                newScreen = ScreenEnum.I_NEVER_DO_SCREEN.getScreen();
                break;
        }


        game.setScreen(newScreen);

        // Dispose previous screen
        if (currentScreen != null) {
            currentScreen.dispose();
        }
    }
}
