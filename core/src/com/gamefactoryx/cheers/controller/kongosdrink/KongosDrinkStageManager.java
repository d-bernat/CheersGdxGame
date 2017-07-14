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
    //private KongosDrinkStageEnum currentStage;
    //private Queue<StageEnum> stageHistory = new Queue<>();

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

   /* public void showStage() {
        showStage(currentStage);
    }

    public void showLastStage() {
        try {
            showStage(stageHistory.removeLast(), true);
        } catch (Exception e) {
            stageHistory.clear();
            showSecondStage(StageEnum.MAIN_STAGE);
        }
    }

    public void showStage(KongosDrinkStageEnum screenEnum) {
        showStage(screenEnum, false);
    }

    public void showSecondStage(StageEnum screenEnum) {
        showStage(screenEnum, false);
    }*/


    // Show in the game the screen which enum type is received
    public void showStage(KongosDrinkStageEnum screenEnum/*, boolean rollback*/) {
        /*if (!rollback)
            ifPossibleAddCurrentStageToStageHistory();*/

        // Get current screen to dispose it
        Screen currentScreen = game.getScreen();
        KongosDrinkAbstractController controller = null;
        // Show new screen
        switch (screenEnum) {
            case KONGOS_DRINK_MAIN_STAGE:
                controller = KongosDrinkStageEnum.KONGOS_DRINK_MAIN_STAGE.getController();
                break;
        }

        //currentStage = screenEnum;

        game.setScreen(controller.getScreen());

        // Dispose previous screen
        if (currentScreen != null) {
            currentScreen.dispose();
        }
    }

    /*private void ifPossibleAddCurrentStageToStageHistory() {
        if (currentStage == null)
            return;
        if (isLogicalLeafStage(currentStage))
            return;

        if (stageHistory.size > 0) {
            if (stageHistory.last() != currentStage)
                stageHistory.addLast(currentStage);
        } else
            stageHistory.addLast(currentStage);
    }

    private boolean isLogicalLeafStage(StageEnum screenEnum) {
        if (screenEnum == null)
            return true;

        switch (screenEnum) {
            case SPLASH_STAGE:
            case BUS_DRIVING_STAGE_ZERO_PHASE:
            case BUS_DRIVING_STAGE_FIRST_PHASE:
            case BUS_DRIVING_STAGE_SECOND_PHASE:
            case BUS_DRIVING_STAGE_THIRD_PHASE:
            case BUS_DRIVING_STAGE_FOURTH_PHASE:
                return true;
            default:
                return false;
        }
    }*/
}
