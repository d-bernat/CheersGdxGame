package com.gamefactoryx.cheersapp.controller.kongosdrink;

import com.gamefactoryx.cheersapp.view.kongosdrink.KongosDrinkMainScreen;


/**
 * Created by bernat on 28.04.2017.
 */
@SuppressWarnings("DefaultFileTemplate")
public enum KongosDrinkStageEnum {
    KONGOS_DRINK_MAIN_STAGE {
        public KongosDrinkAbstractController getController() {
            return new com.gamefactoryx.cheersapp.controller.kongosdrink.KongosDrinkMainController(new KongosDrinkMainScreen());
        }
    };

    @SuppressWarnings("unused")
    public abstract KongosDrinkAbstractController getController();
}

