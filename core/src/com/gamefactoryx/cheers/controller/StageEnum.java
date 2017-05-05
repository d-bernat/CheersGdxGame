package com.gamefactoryx.cheers.controller;

import com.gamefactoryx.cheers.view.INeverDoScreen;
import com.gamefactoryx.cheers.view.MainScreen;
import com.gamefactoryx.cheers.view.NewGameScreen;

/**
 * Created by bernat on 28.04.2017.
 */
@SuppressWarnings("DefaultFileTemplate")
public enum StageEnum {

    MAIN_STAGE {
        public AbstractController getController() {
            return new MainStageController(new MainScreen());
        }
    },
    NEW_GAME_STAGE {
        public AbstractController getController() {
            return new NewGameStageController(new NewGameScreen());
        }
    },
    I_NEVER_DO_STAGE {
        public AbstractController getController() {
            return new INeverDoStageController(new INeverDoScreen());
        }
    };

    @SuppressWarnings("unused")
    public abstract AbstractController getController();
}

