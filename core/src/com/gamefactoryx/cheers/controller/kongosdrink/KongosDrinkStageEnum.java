package com.gamefactoryx.cheers.controller.kongosdrink;

import com.gamefactoryx.cheers.controller.*;
import com.gamefactoryx.cheers.view.*;
import com.gamefactoryx.cheers.view.kongosdrink.KongosDrinkMainScreen;


/**
 * Created by bernat on 28.04.2017.
 */
@SuppressWarnings("DefaultFileTemplate")
public enum KongosDrinkStageEnum {
    KONGOS_DRINK_MAIN_STAGE {
        public KongosDrinkAbstractController getController() {
            return new KongosDrinkMainController(new KongosDrinkMainScreen());
        }
    };

    @SuppressWarnings("unused")
    public abstract KongosDrinkAbstractController getController();
}

