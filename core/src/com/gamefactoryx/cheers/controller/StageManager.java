package com.gamefactoryx.cheers.controller;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

/**
 * Created by bernat on 28.04.2017.
 */
@SuppressWarnings("DefaultFileTemplate")
public final class StageManager {

    // Singleton: unique instance
    private static StageManager instance;

    // Reference to game
    private Game game;
    private StageEnum currentStage;

    // Singleton: private constructor
    private StageManager() {
        super();
    }

    // Singleton: retrieve instance
    public static StageManager getInstance() {
        if (instance == null) {
            instance = new StageManager();
        }
        return instance;
    }

    // Initialization with the game class
    public void initialize(Game game) {
        this.game = game;
    }

    public void showStage(){
        showStage(currentStage);
    }
    // Show in the game the screen which enum type is received
    public void showStage(StageEnum screenEnum) {

        // Get current screen to dispose it
        Screen currentScreen = game.getScreen();
        AbstractController controller = null;
        // Show new screen
        switch (screenEnum) {
            case MAIN_STAGE:
                controller = StageEnum.MAIN_STAGE.getController();
                break;
            case NEW_GAME_STAGE:
                controller = StageEnum.NEW_GAME_STAGE.getController();
                break;
            case I_NEVER_DO_STAGE:
                controller = StageEnum.I_NEVER_DO_STAGE.getController();
                break;
            case KINGS_CUP_SPECIAL_STAGE:
                controller = StageEnum.KINGS_CUP_SPECIAL_STAGE.getController();
                break;
        }

        currentStage = screenEnum;
        game.setScreen(controller.getScreen());

        // Dispose previous screen
        if (currentScreen != null) {
            currentScreen.dispose();
        }
    }
}
