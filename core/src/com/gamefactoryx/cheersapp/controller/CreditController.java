package com.gamefactoryx.cheersapp.controller;

import com.gamefactoryx.cheersapp.model.CreditModel;
import com.gamefactoryx.cheersapp.view.AbstractScreen;


/**
 * Created by bernat on 28.04.2017.
 */
@SuppressWarnings("DefaultFileTemplate")
final class CreditController extends AbstractController {

    private int lastYPointerPos;

    CreditController(final AbstractScreen screen) {
        super(screen);
        setScreenLock(1);
        StageManager.getInstance().getGame().getAdMobRequestHandler().showAds(false);
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
        if(!CreditModel.getInstance().isAnimate()){
            com.gamefactoryx.cheersapp.controller.StageManager.getInstance().showStage(com.gamefactoryx.cheersapp.controller.StageEnum.MAIN_STAGE);
            return true;
        }

        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {

       /* int SCROLLING_SLOW_DOWN = 30;
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

        }*/

        return true;
    }

}
