package com.gamefactoryx.cheers.controller;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.Queue;
import com.gamefactoryx.cheers.CheersGdxGame;

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
            StageEnum stageEnum = stageHistory.removeLast();
            switch (stageEnum) {
                case BUS_DRIVING_STAGE_FIRST_PHASE:
                case BUS_DRIVING_STAGE_SECOND_PHASE:
                case BUS_DRIVING_STAGE_THIRD_PHASE:
                case BUS_DRIVING_STAGE_FOURTH_PHASE:
                    showStage(StageEnum.NEW_GAME_STAGE);
                    break;
                default:
                    showStage(stageEnum, true);
            }

        } catch (Exception e) {
            showStage(StageEnum.MAIN_STAGE);
        }
    }

    public void showStage(StageEnum screenEnum) {
        showStage(screenEnum, false);
    }

    // Show in the game the screen which enum type is received
    private void showStage(StageEnum screenEnum, boolean rollBack) {
        if (screenEnum != currentStage && !rollBack) {
            stageHistory.addLast(currentStage);
        }

        // Get current screen to dispose it
        Screen currentScreen = game.getScreen();
        AbstractController controller = null;
        // Show new screen
        switch (screenEnum) {
            case MAIN_STAGE:
                if (CheersGdxGame.getScreenLock() != null)
                    CheersGdxGame.getScreenLock().lock(10);
                controller = StageEnum.MAIN_STAGE.getController();
                break;
            case NEW_GAME_STAGE:
                if (CheersGdxGame.getScreenLock() != null)
                    CheersGdxGame.getScreenLock().lock(10);
                controller = StageEnum.NEW_GAME_STAGE.getController();
                break;
            case I_NEVER_DO_STAGE:
                if (CheersGdxGame.getScreenLock() != null)
                    CheersGdxGame.getScreenLock().lock(10);
                controller = StageEnum.I_NEVER_DO_STAGE.getController();
                break;
            case KINGS_CUP_SPECIAL_STAGE:
                if (CheersGdxGame.getScreenLock() != null)
                    CheersGdxGame.getScreenLock().lock(10);
                controller = StageEnum.KINGS_CUP_SPECIAL_STAGE.getController();
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
}
