package com.gamefactoryx.cheers.controller;

import com.badlogic.gdx.Input;
import com.gamefactoryx.cheers.tool.Orientation;
import com.gamefactoryx.cheers.tool.Resolution;
import com.gamefactoryx.cheers.view.AbstractScreen;


/**
 * Created by bernat on 28.04.2017.
 */
@SuppressWarnings("DefaultFileTemplate")
public final class KingsCupSpecialStageController extends AbstractController {

    private int lastYPointerPos;

    KingsCupSpecialStageController(final AbstractScreen screen) {
        super(screen);
    }


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
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
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        for (int i = 0; i < getScreen().getCountOfButtons(); i++) {
            if (getScreen().getClicked()[i])
                switch (i) {
                    case 0:
                        StageManager.getInstance().showStage(StageEnum.MAIN_STAGE);
                }

            getScreen().getClicked()[i] = false;
        }


        return true;
    }

    @Override
    public boolean touchDragged (int screenX, int screenY, int pointer) {

        if(screenX >= getScreen().getTextBox().getX() &&
                screenX <= getScreen().getTextBox().getX() + getScreen().getTextBox().getWidth() &&
                screenY >= getScreen().getTextBox().getY() &&
                screenY <= getScreen().getTextBox().getY() + getScreen().getTextBox().getHeight()) {
            if(screenY < lastYPointerPos) {
                //if (getScreen().getYScrollPos() > 0)
                    getScreen().setYScrollPos(getScreen().getYScrollPos() - 1);
            }
            else
                getScreen().setYScrollPos(getScreen().getYScrollPos() + 1);
            lastYPointerPos = screenY;
        }

        return true;
    }





}