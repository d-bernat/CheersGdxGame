package com.gamefactoryx.cheers.controller.kongosdrink;

import com.gamefactoryx.cheers.controller.AbstractController;
import com.gamefactoryx.cheers.view.AbstractScreen;


/**
 * Created by bernat on 28.04.2017.
 */
@SuppressWarnings("DefaultFileTemplate")
final public class KongosDrinkMainController extends AbstractController {

    private static int counter;
    public KongosDrinkMainController(final AbstractScreen screen){
        super(screen);
        setScreenLock(0);
    }



    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return super.touchUp(screenX, screenY, pointer, button);
    }

}
