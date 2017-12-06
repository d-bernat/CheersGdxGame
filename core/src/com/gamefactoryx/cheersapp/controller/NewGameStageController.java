package com.gamefactoryx.cheersapp.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.gamefactoryx.cheersapp.view.AbstractScreen;

/**
 * Created by bernat on 28.04.2017.
 */
@SuppressWarnings("DefaultFileTemplate")
final class NewGameStageController extends AbstractController {

    NewGameStageController(final AbstractScreen screen){
        super(screen);
        setScreenLock(1);
        StageManager.getInstance().getGame().getAdMobRequestHandler().showAds(false);
    }


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        super.touchDown(screenX, screenY, pointer, button);
        for (int i = 0; i < getScreen().getCountOfButtons(); i++) {
            getScreen().getClicked()[i] = (screenX >= getScreen().getButtons()[i][0].getX() &&
                    screenX <= getScreen().getButtons()[i][0].getX() + getScreen().getButtons()[i][0].getWidth() &&
                    com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightPortrait() - screenY >= getScreen().getButtons()[i][0].getY() &&
                    com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightPortrait() - screenY <= getScreen().getButtons()[i][0].getY() + getScreen().getButtons()[i][0].getHeight() &&
                    com.gamefactoryx.cheersapp.tool.Orientation.getOrientation() == Input.Orientation.Portrait
                    ||
                    screenX >= getScreen().getButtons()[i][0].getX() &&
                            screenX <= getScreen().getButtons()[i][0].getX() + getScreen().getButtons()[i][0].getWidth() &&
                            com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightLandscape() - screenY >= getScreen().getButtons()[i][0].getY() &&
                            com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightLandscape() - screenY <= getScreen().getButtons()[i][0].getY() + getScreen().getButtons()[i][0].getHeight() &&
                            com.gamefactoryx.cheersapp.tool.Orientation.getOrientation() == Input.Orientation.Landscape);
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if(!super.touchUp(screenX, screenY, pointer, button))
            return true;

        for (int i = 0; i < getScreen().getCountOfButtons(); i++) {
            if (getScreen().getClicked()[i]) {
                Gdx.input.vibrate(10);
                switch (i) {
                    case 0:
                        com.gamefactoryx.cheersapp.controller.StageManager.getInstance().showStage(StageEnum.KONGOS_DRINK_ZERO_STAGE);
                        break;
                    case 1:
                        com.gamefactoryx.cheersapp.controller.StageManager.getInstance().showStage(StageEnum.BUS_DRIVING_STAGE_ZERO_PHASE);
                        break;
                    case 2:
                        com.gamefactoryx.cheersapp.controller.StageManager.getInstance().showStage(StageEnum.I_NEVER_DO_STAGE);
                        break;
                    case 3:
                        com.gamefactoryx.cheersapp.controller.StageManager.getInstance().showStage(StageEnum.KINGS_CUP_SPECIAL_STAGE);
                        break;
                }
            }
            getScreen().getClicked()[i] = false;
        }
        return true;
    }


}
