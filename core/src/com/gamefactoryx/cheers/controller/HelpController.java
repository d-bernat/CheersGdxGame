package com.gamefactoryx.cheers.controller;

import com.badlogic.gdx.Input;
import com.gamefactoryx.cheers.model.HelpModel;
import com.gamefactoryx.cheers.tool.Orientation;
import com.gamefactoryx.cheers.tool.Resolution;
import com.gamefactoryx.cheers.view.AbstractScreen;


/**
 * Created by bernat on 28.04.2017.
 */
@SuppressWarnings("DefaultFileTemplate")
final class HelpController extends AbstractController {

    private int lastYPointerPos;

    HelpController(final AbstractScreen screen) {
        super(screen);
        setScreenLock(1);
    }


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        super.touchDown(screenX, screenY, pointer, button);
        lastYPointerPos = screenY;
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        super.touchUp(screenX, screenY, pointer, button);
        return true;
    }

    @Override
    public boolean touchDragged (int screenX, int screenY, int pointer) {

            if(screenY < lastYPointerPos) {
                getScreen().setYScrollPos(Math.min(getScreen().getYScrollPos() + 20, HelpModel.getInstance().getMaxYScrollPos()));
                lastYPointerPos = screenY;
            }
            else if(screenY > lastYPointerPos) {
                getScreen().setYScrollPos(Math.max(getScreen().getYScrollPos() - 20, 0));
                lastYPointerPos = screenY;
            }
        return true;
    }





}
