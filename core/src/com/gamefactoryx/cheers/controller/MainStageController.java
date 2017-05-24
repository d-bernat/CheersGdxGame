package com.gamefactoryx.cheers.controller;

import com.badlogic.gdx.Input;
import com.gamefactoryx.cheers.tool.Configuration;
import com.gamefactoryx.cheers.tool.Orientation;
import com.gamefactoryx.cheers.tool.Resolution;
import com.gamefactoryx.cheers.view.AbstractScreen;


/**
 * Created by bernat on 28.04.2017.
 */
@SuppressWarnings("DefaultFileTemplate")
final class MainStageController extends AbstractController {

    MainStageController(final AbstractScreen screen){
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

        for (int i = 0; i < getScreen().getButtons().length; i++) {
            if (getScreen().getClicked()[i])
                switch (i) {
                    case 3:
                        StageManager.getInstance().showStage(StageEnum.NEW_GAME_STAGE);
                        break;
                    case 5:
                        switch(Configuration.getLanguage())
                        {
                            case DE:
                                Configuration.setLanguage(Configuration.LanguageEnum.EN);
                                break;
                            case EN:
                                Configuration.setLanguage(Configuration.LanguageEnum.SK);
                                break;
                            case SK:
                                Configuration.setLanguage(Configuration.LanguageEnum.DE);
                                break;
                        }
                        StageManager.getInstance().showStage(StageEnum.MAIN_STAGE);
                        break;
                    default:
                        //StageManager.getInstance().showStage(StageEnum.MAIN_STAGE);
                }
            getScreen().getClicked()[i] = false;
        }


        return true;
    }

}
