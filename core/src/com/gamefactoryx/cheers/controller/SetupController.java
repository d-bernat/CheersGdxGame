package com.gamefactoryx.cheers.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.gamefactoryx.cheers.tool.Configuration;
import com.gamefactoryx.cheers.tool.Orientation;
import com.gamefactoryx.cheers.tool.Resolution;
import com.gamefactoryx.cheers.view.AbstractScreen;


/**
 * Created by bernat on 28.04.2017.
 */
@SuppressWarnings("DefaultFileTemplate")
final class SetupController extends AbstractController {

    private static int counter;

    SetupController(final AbstractScreen screen) {
        super(screen);
        setScreenLock(10);
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

        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        for (int i = 0; i < getScreen().getButtons().length; i++) {
            if (getScreen().getClicked()[i]) {
                Gdx.input.vibrate(10);
                switch (i) {
                    case 0:
                        Configuration.setPlayMusic(false);
                        break;
                    case 1:
                        Configuration.setPlayMusic(true);
                        break;
                }
                Configuration.playMusic();
                getScreen().getClicked()[i] = false;
            }
        }
        return super.touchUp(screenX, screenY, pointer, button);
    }
}
