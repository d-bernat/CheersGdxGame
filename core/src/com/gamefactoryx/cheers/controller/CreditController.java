package com.gamefactoryx.cheers.controller;

import com.badlogic.gdx.Input;
import com.gamefactoryx.cheers.controller.AbstractController;
import com.gamefactoryx.cheers.tool.Orientation;
import com.gamefactoryx.cheers.tool.Resolution;
import com.gamefactoryx.cheers.view.AbstractScreen;


/**
 * Created by bernat on 28.04.2017.
 */
@SuppressWarnings("DefaultFileTemplate")
final class CreditController extends AbstractController {

    private int lastYPointerPos;

    CreditController(final AbstractScreen screen) {
        super(screen);
        setScreenLock(10);

        /*new Thread(new Runnable() {
            @Override
            public void run() {
                int acc = 0;
                while (acc < 5) {
                    long time = System.currentTimeMillis();
                    while (System.currentTimeMillis() < time + 1000L) {
                    }
                    ++acc;
                    getScreen().setYScrollPos(getScreen().getYScrollPos() + 1);

                }
            }

        }).start();*/

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
    public boolean touchDragged(int screenX, int screenY, int pointer) {

        int SCROLLING_SLOW_DOWN = 30;
        if (screenX >= getScreen().getTextBox().getX() &&
                screenX <= getScreen().getTextBox().getX() + getScreen().getTextBox().getWidth() &&
                Resolution.getGameWorldHeightPortrait() - screenY >= getScreen().getTextBox().getY() &&
                Resolution.getGameWorldHeightPortrait() - screenY <= getScreen().getTextBox().getY() + getScreen().getTextBox().getHeight() &&
                Orientation.getOrientation() == Input.Orientation.Portrait ||

                screenX >= getScreen().getTextBox().getX() &&
                        screenX <= getScreen().getTextBox().getX() + getScreen().getTextBox().getWidth() &&
                        Resolution.getGameWorldHeightLandscape() - screenY >= getScreen().getTextBox().getY() &&
                        Resolution.getGameWorldHeightLandscape() - screenY <= getScreen().getTextBox().getY() + getScreen().getTextBox().getHeight() &&
                        Orientation.getOrientation() == Input.Orientation.Landscape
                ) {
            if (screenY < lastYPointerPos - SCROLLING_SLOW_DOWN) {
                getScreen().setYScrollPos(getScreen().getYScrollPos() + 1);
                lastYPointerPos = screenY;
            } else if (screenY > lastYPointerPos + SCROLLING_SLOW_DOWN) {
                getScreen().setYScrollPos(getScreen().getYScrollPos() - 1);
                lastYPointerPos = screenY;
            }

        }

        return true;
    }


}
