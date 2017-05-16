package com.gamefactoryx.cheers.controller;

import com.gamefactoryx.cheers.view.*;

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
    },
    KINGS_CUP_SPECIAL_STAGE {
        public AbstractController getController() {
            return new KingsCupSpecialStageController(new KingsCupSpecialScreen());
        }
    },
    BUS_DRIVING_STAGE {
        public AbstractController getController() {
            return new BusDrivingStageController(new BusDrivingScreen());
        }
    };

    @SuppressWarnings("unused")
    public abstract AbstractController getController();
}

