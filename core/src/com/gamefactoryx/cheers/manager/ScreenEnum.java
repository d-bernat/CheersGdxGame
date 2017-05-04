package com.gamefactoryx.cheers.manager;

import com.gamefactoryx.cheers.controller.screen.INeverDoScreenController;
import com.gamefactoryx.cheers.controller.screen.MainScreenController;
import com.gamefactoryx.cheers.controller.screen.NewGameScreenController;
import com.gamefactoryx.cheers.view.AbstractScreen;
import com.gamefactoryx.cheers.view.INeverDoScreen;
import com.gamefactoryx.cheers.view.MainScreen;
import com.gamefactoryx.cheers.view.NewGameScreen;

/**
 * Created by bernat on 28.04.2017.
 */
@SuppressWarnings("DefaultFileTemplate")
public enum ScreenEnum {

    MAIN_SCREEN {
        public AbstractScreen getScreen() {
            return new MainScreen(new MainScreenController());
        }
    },
    NEW_GAME_SCREEN {
        public AbstractScreen getScreen() {
            return new NewGameScreen(new NewGameScreenController());
        }
    },
    I_NEVER_DO_SCREEN {
        public AbstractScreen getScreen() {
            return new INeverDoScreen(new INeverDoScreenController());
        }
    };

    @SuppressWarnings("unused")
    public abstract AbstractScreen getScreen();
}

