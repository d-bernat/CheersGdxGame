package com.gamefactoryx.cheers.controller.kongosdrink;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.Queue;
import com.gamefactoryx.cheers.controller.AbstractController;
import com.gamefactoryx.cheers.controller.StageEnum;

/**
 * Created by bernat on 28.04.2017.
 */
@SuppressWarnings("DefaultFileTemplate")
public final class KongosDrinkStageManager {


    // Singleton: unique instance
    private static KongosDrinkStageManager instance;

    // Reference to game
    private Game game;
    // Singleton: private constructor
    private KongosDrinkStageManager() {
        super();
    }

    // Singleton: retrieve instance
    public static KongosDrinkStageManager getInstance() {
        if (instance == null) {
            instance = new KongosDrinkStageManager();
        }
        return instance;
    }

    // Initialization with the game class
    public void initialize(Game game) {
        this.game = game;
    }


    // Show in the game the screen which enum type is received
    public void showStage(KongosDrinkStageEnum screenEnum/*, boolean rollback*/) {
        Screen currentScreen = game.getScreen();
        KongosDrinkAbstractController controller = KongosDrinkStageEnum.KONGOS_DRINK_MAIN_STAGE.getController();
        game.setScreen(controller.getScreen());

        // Dispose previous screen
        if (currentScreen != null) {
            currentScreen.dispose();
        }
    }
}
