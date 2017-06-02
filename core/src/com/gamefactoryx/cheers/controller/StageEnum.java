package com.gamefactoryx.cheers.controller;

import com.gamefactoryx.cheers.view.*;
import com.gamefactoryx.cheers.view.BusDrivingScreen;

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
    BUS_DRIVING_STAGE_FIRST_PHASE {
        public AbstractController getController() {
            return new BusDrivingStagePhase1Controller(new BusDrivingPhase1Screen());
        }
    },
    BUS_DRIVING_STAGE_SECOND_PHASE {
        public AbstractController getController() {
            return new BusDrivingStagePhase2Controller(new BusDrivingPhase2Screen());
        }
    };

    @SuppressWarnings("unused")
    public abstract AbstractController getController();
}

