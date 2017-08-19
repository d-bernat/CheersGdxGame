package com.gamefactoryx.cheers.controller;

import com.gamefactoryx.cheers.controller.busdriving.BusDrivingStagePhase1Controller;
import com.gamefactoryx.cheers.controller.busdriving.BusDrivingStagePhase2Controller;
import com.gamefactoryx.cheers.controller.busdriving.BusDrivingStagePhase3Controller;
import com.gamefactoryx.cheers.controller.busdriving.BusDrivingStagePhase4Controller;
import com.gamefactoryx.cheers.controller.kongosdrink.KongosDrinkMainController;
import com.gamefactoryx.cheers.controller.kongosdrink.KongosDrinkPhase0Controller;
import com.gamefactoryx.cheers.view.*;
import com.gamefactoryx.cheers.view.kongosdrink.KongosDrinkMainScreen;
import com.gamefactoryx.cheers.view.kongosdrink.KongosDrinkPhase0Screen;


/**
 * Created by bernat on 28.04.2017.
 */
@SuppressWarnings("DefaultFileTemplate")
public enum StageEnum {
    SPLASH_STAGE {
        public AbstractController getController() {
            return new SplashStageController(new SplashScreen());
        }
    },
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
    KONGOS_DRINK_PHASE0_STAGE {
        public AbstractController getController() {
            return new KongosDrinkPhase0Controller(new KongosDrinkPhase0Screen());
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
    BUS_DRIVING_STAGE_ZERO_PHASE {
        public AbstractController getController() {
            return new com.gamefactoryx.cheers.controller.busdriving.BusDrivingStagePhase0Controller(new com.gamefactoryx.cheers.view.busdriving.BusDrivingPhase0Screen());
        }
    },
    BUS_DRIVING_STAGE_FIRST_PHASE {
        public AbstractController getController() {
            return new BusDrivingStagePhase1Controller(new com.gamefactoryx.cheers.view.busdriving.BusDrivingPhase1Screen());
        }
    },
    BUS_DRIVING_STAGE_SECOND_PHASE {
        public AbstractController getController() {
            return new BusDrivingStagePhase2Controller(new com.gamefactoryx.cheers.view.busdriving.BusDrivingPhase2Screen());
        }
    },
    BUS_DRIVING_STAGE_THIRD_PHASE {
        public AbstractController getController() {
            return new BusDrivingStagePhase3Controller(new com.gamefactoryx.cheers.view.busdriving.BusDrivingPhase3Screen());
        }
    },
    BUS_DRIVING_STAGE_FOURTH_PHASE {
        public AbstractController getController() {
            return new BusDrivingStagePhase4Controller(new com.gamefactoryx.cheers.view.busdriving.BusDrivingPhase4Screen());
        }
    },
    HALL_OF_FAME_STAGE {
        public AbstractController getController() {
            return new HallOfFameController(new HallOfFameScreen());
        }
    };

    @SuppressWarnings("unused")
    public abstract AbstractController getController();
}

