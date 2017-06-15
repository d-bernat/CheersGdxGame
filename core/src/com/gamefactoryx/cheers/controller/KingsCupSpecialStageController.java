package com.gamefactoryx.cheers.controller;

import com.badlogic.gdx.Input;
import com.gamefactoryx.cheers.tool.Orientation;
import com.gamefactoryx.cheers.tool.Resolution;
import com.gamefactoryx.cheers.view.AbstractScreen;


/**
 * Created by bernat on 28.04.2017.
 */
@SuppressWarnings("DefaultFileTemplate")
final class KingsCupSpecialStageController extends AbstractController {

    private int lastYPointerPos;

    KingsCupSpecialStageController(final AbstractScreen screen) {
        super(screen);
        setScreenLock(10);
    }


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        lastYPointerPos = screenY;
        for (int i = 0; i < getScreen().getCountOfButtons(); i++) {
            getScreen().getClicked()[i] = (screenX >= getScreen().getButtons()[i][0].getX() &&
                    screenX <= getScreen().getButtons()[i][0].getX() + getScreen().getButtons()[i][0].getWidth() &&
                    Resolution.getGameWorldHeightPortrait() - screenY >= getScreen().getButtons()[i][0].getY() &&
                    Resolution.getGameWorldHeightPortrait() - screenY <= getScreen().getButtons()[i][0].getY() + getScreen().getButtons()[i][0].getHeight() &&
                    Orientation.getOrientation() == Input.Orientation.Portrait
                    ||
                    screenX >= getScreen().getButtons()[i][0].getX() &&
                            screenX <= getScreen().getButtons()[i][0].getX() + getScreen().getButtons()[i][0].getWidth() &&
                            Resolution.getGameWorldHeightLandscape() - screenY >= getScreen().getButtons()[i][0].getY() &&
                            Resolution.getGameWorldHeightLandscape() - screenY <= getScreen().getButtons()[i][0].getY() + getScreen().getButtons()[i][0].getHeight() &&
                            Orientation.getOrientation() == Input.Orientation.Landscape);
        }
        return true;
    }

    @Override
    public boolean touchDragged (int screenX, int screenY, int pointer) {

        int SCROLLING_SLOW_DOWN = 30;
        if(screenX >= getScreen().getTextBox().getX() &&
                screenX <= getScreen().getTextBox().getX() + getScreen().getTextBox().getWidth() &&
                screenY >= getScreen().getTextBox().getY() &&
                screenY <= getScreen().getTextBox().getY() + getScreen().getTextBox().getHeight()) {
            if(screenY < lastYPointerPos - SCROLLING_SLOW_DOWN) {
                getScreen().setYScrollPos(getScreen().getYScrollPos() + 1);
                lastYPointerPos = screenY;
            }
            else if(screenY > lastYPointerPos + SCROLLING_SLOW_DOWN) {
                getScreen().setYScrollPos(getScreen().getYScrollPos() - 1);
                lastYPointerPos = screenY;
            }

        }

        return true;
    }





}
