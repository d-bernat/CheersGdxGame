package com.gamefactoryx.cheersapp.controller;

import com.gamefactoryx.cheersapp.controller.busdriving.BusDrivingStagePhase0Controller;
import com.gamefactoryx.cheersapp.controller.busdriving.BusDrivingStagePhase1Controller;
import com.gamefactoryx.cheersapp.controller.busdriving.BusDrivingStagePhase2Controller;
import com.gamefactoryx.cheersapp.controller.busdriving.BusDrivingStagePhase3Controller;
import com.gamefactoryx.cheersapp.controller.busdriving.BusDrivingStagePhase4Controller;
import com.gamefactoryx.cheersapp.view.CreditScreen;
import com.gamefactoryx.cheersapp.view.kongosdrink.KongosDrinkSetupScreen;
import com.gamefactoryx.cheersapp.view.busdriving.*;


/**
 * Created by bernat on 28.04.2017.
 */
@SuppressWarnings("DefaultFileTemplate")
public enum StageEnum {
    SPLASH_STAGE {
        public com.gamefactoryx.cheersapp.controller.AbstractController getController() {
            return new SplashStageController(new com.gamefactoryx.cheersapp.view.SplashScreen());
        }
    },
    MAIN_STAGE {
        public com.gamefactoryx.cheersapp.controller.AbstractController getController() {
            return new MainStageController(new com.gamefactoryx.cheersapp.view.MainScreen());
        }
    },
    SETUP_STAGE {
        public com.gamefactoryx.cheersapp.controller.AbstractController getController() {
            return new SetupController(new com.gamefactoryx.cheersapp.view.SetupScreen());
        }
    },
    HELP_STAGE {
        public com.gamefactoryx.cheersapp.controller.AbstractController getController() {
            return new HelpController(new com.gamefactoryx.cheersapp.view.HelpScreen());
        }
    },
    CREDIT_STAGE {
        public com.gamefactoryx.cheersapp.controller.AbstractController getController() {
            return new CreditController(new CreditScreen());
        }
    },
    NEW_GAME_STAGE {
        public com.gamefactoryx.cheersapp.controller.AbstractController getController() {
            return new com.gamefactoryx.cheersapp.controller.NewGameStageController(new com.gamefactoryx.cheersapp.view.NewGameScreen());
        }
    },
    KONGOS_DRINK_SETUP_STAGE {
        public com.gamefactoryx.cheersapp.controller.AbstractController getController() {
            return new com.gamefactoryx.cheersapp.controller.kongosdrink.KongosDrinkSetupController(new KongosDrinkSetupScreen());
        }
    },
    KONGOS_DRINK_ZERO_STAGE {
        public com.gamefactoryx.cheersapp.controller.AbstractController getController() {
            return new com.gamefactoryx.cheersapp.controller.kongosdrink.KongosDrinkPhase0Controller(new com.gamefactoryx.cheersapp.view.kongosdrink.KongosDrinkPhase0Screen());
        }
    },
    KONGOS_DRINK_ZERO_ONE_STAGE {
        public com.gamefactoryx.cheersapp.controller.AbstractController getController() {
            return new com.gamefactoryx.cheersapp.controller.kongosdrink.KongosDrinkPhase01Controller(new com.gamefactoryx.cheersapp.view.kongosdrink.KongosDrinkPhase01Screen());
        }
    },
    I_NEVER_DO_STAGE {
        public com.gamefactoryx.cheersapp.controller.AbstractController getController() {
            return new INeverDoStageController(new com.gamefactoryx.cheersapp.view.INeverDoScreen());
        }
    },
    KINGS_CUP_SPECIAL_STAGE {
        public com.gamefactoryx.cheersapp.controller.AbstractController getController() {
            return new com.gamefactoryx.cheersapp.controller.KingsCupSpecialStageController(new com.gamefactoryx.cheersapp.view.KingsCupSpecialScreen());
        }
    },
    BUS_DRIVING_STAGE_ZERO_PHASE {
        public com.gamefactoryx.cheersapp.controller.AbstractController getController() {
            return new BusDrivingStagePhase0Controller(new BusDrivingPhase0Screen());
        }
    },
    BUS_DRIVING_STAGE_FIRST_PHASE {
        public com.gamefactoryx.cheersapp.controller.AbstractController getController() {
            return new BusDrivingStagePhase1Controller(new BusDrivingPhase1Screen());
        }
    },
    BUS_DRIVING_STAGE_SECOND_PHASE {
        public com.gamefactoryx.cheersapp.controller.AbstractController getController() {
            return new BusDrivingStagePhase2Controller(new BusDrivingPhase2Screen());
        }
    },
    BUS_DRIVING_STAGE_THIRD_PHASE {
        public com.gamefactoryx.cheersapp.controller.AbstractController getController() {
            return new BusDrivingStagePhase3Controller(new BusDrivingPhase3Screen());
        }
    },
    BUS_DRIVING_STAGE_FOURTH_PHASE {
        public com.gamefactoryx.cheersapp.controller.AbstractController getController() {
            return new BusDrivingStagePhase4Controller(new BusDrivingPhase4Screen());
        }
    },
    HALL_OF_FAME_STAGE {
        public com.gamefactoryx.cheersapp.controller.AbstractController getController() {
            return new com.gamefactoryx.cheersapp.controller.HallOfFameController(new com.gamefactoryx.cheersapp.view.HallOfFameScreen());
        }
    },
    COCKTAILS_STAGE {
        public com.gamefactoryx.cheersapp.controller.AbstractController getController() {
            return new com.gamefactoryx.cheersapp.controller.CocktailsController(new com.gamefactoryx.cheersapp.view.CocktailsScreen());
        }
    };

    @SuppressWarnings("unused")
    public abstract com.gamefactoryx.cheersapp.controller.AbstractController getController();
}

