package com.gamefactoryx.cheers.controller;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.Queue;
import com.gamefactoryx.cheers.CheersGdxGame;
import com.gamefactoryx.cheers.tool.Card;

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
    private Queue<StageEnum> stageHistory = new Queue<>();


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

    public void showStage() {
        showStage(currentStage);
    }

    public void showLastStage() {
        try {
            showStage(stageHistory.removeLast(), true);
        } catch (Exception e) {
            stageHistory.clear();
            showStage(StageEnum.MAIN_STAGE);
        }
    }

    public void showStage(StageEnum screenEnum) {
        showStage(screenEnum, false);
    }

    // Show in the game the screen which enum type is received
    private void showStage(StageEnum screenEnum, boolean rollback) {
        if (!rollback)
            ifPossibleAddCurrentStageToStageHistory();

        // Get current screen to dispose it
        Screen currentScreen = game.getScreen();
        AbstractController controller = null;
        // Show new screen
        switch (screenEnum) {
            case SPLASH_STAGE:
                controller = StageEnum.SPLASH_STAGE.getController();
                break;
            case MAIN_STAGE:
                controller = StageEnum.MAIN_STAGE.getController();
                break;
            case HALL_OF_FAME_STAGE:
                controller = StageEnum.HALL_OF_FAME_STAGE.getController();
                break;
            case NEW_GAME_STAGE:
                controller = StageEnum.NEW_GAME_STAGE.getController();
                break;
            case KONGOS_DRINK_ZERO_STAGE:
                controller = StageEnum.KONGOS_DRINK_ZERO_STAGE.getController();
                break;
            case I_NEVER_DO_STAGE:
                controller = StageEnum.I_NEVER_DO_STAGE.getController();
                break;
            case KINGS_CUP_SPECIAL_STAGE:
                controller = StageEnum.KINGS_CUP_SPECIAL_STAGE.getController();
                break;
            case BUS_DRIVING_STAGE_ZERO_PHASE:
                controller = StageEnum.BUS_DRIVING_STAGE_ZERO_PHASE.getController();
                break;
            case BUS_DRIVING_STAGE_FIRST_PHASE:
                controller = StageEnum.BUS_DRIVING_STAGE_FIRST_PHASE.getController();
                break;
            case BUS_DRIVING_STAGE_SECOND_PHASE:
                controller = StageEnum.BUS_DRIVING_STAGE_SECOND_PHASE.getController();
                break;
            case BUS_DRIVING_STAGE_THIRD_PHASE:
                controller = StageEnum.BUS_DRIVING_STAGE_THIRD_PHASE.getController();
                break;
            case BUS_DRIVING_STAGE_FOURTH_PHASE:
                controller = StageEnum.BUS_DRIVING_STAGE_FOURTH_PHASE.getController();
                break;
        }

        currentStage = screenEnum;

        game.setScreen(controller.getScreen());

        // Dispose previous screen
        if (currentScreen != null) {
            currentScreen.dispose();
        }
    }

    private void ifPossibleAddCurrentStageToStageHistory() {
        if (currentStage == null)
            return;
        if (isLogicalLeafStage(currentStage))
            return;

        for(int i = 0; i < stageHistory.size; i++){
            if(stageHistory.get(i) == currentStage)
                return;
        }
        stageHistory.addLast(currentStage);
    }

    private boolean isLogicalLeafStage(StageEnum screenEnum) {
        if (screenEnum == null)
            return true;

        switch (screenEnum) {
            case SPLASH_STAGE:
            case BUS_DRIVING_STAGE_ZERO_PHASE:
            case KONGOS_DRINK_ZERO_STAGE:
            case BUS_DRIVING_STAGE_FIRST_PHASE:
            case BUS_DRIVING_STAGE_SECOND_PHASE:
            case BUS_DRIVING_STAGE_THIRD_PHASE:
            case BUS_DRIVING_STAGE_FOURTH_PHASE:
                return true;
            default:
                return false;
        }
    }
}
