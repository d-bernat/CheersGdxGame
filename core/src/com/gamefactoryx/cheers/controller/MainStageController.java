package com.gamefactoryx.cheers.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.gamefactoryx.cheers.CheersGdxGame;
import com.gamefactoryx.cheers.model.HallOfFameModel;
import com.gamefactoryx.cheers.tool.Configuration;
import com.gamefactoryx.cheers.tool.Orientation;
import com.gamefactoryx.cheers.tool.Resolution;
import com.gamefactoryx.cheers.view.AbstractScreen;


/**
 * Created by bernat on 28.04.2017.
 */
@SuppressWarnings("DefaultFileTemplate")
final class MainStageController extends AbstractController {

    private static int counter;
    MainStageController(final AbstractScreen screen){
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
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        for (int i = 0; i < getScreen().getButtons().length; i++) {
            if (getScreen().getClicked()[i]) {
                Gdx.input.vibrate(10);
                switch (i) {
                    case 0:
                        StageManager.getInstance().showStage(StageEnum.CREDIT_STAGE);
                        break;
                    case 1:
                        StageManager.getInstance().showStage(StageEnum.SETUP_STAGE);
                        break;
                    case 2:
                        StageManager.getInstance().showStage(StageEnum.HALL_OF_FAME_STAGE);
                        break;
                    case 3:
                        StageManager.getInstance().showStage(StageEnum.NEW_GAME_STAGE);
                        break;
                    case 4:
                        StageManager.getInstance().showStage(StageEnum.COCKTAILS_STAGE);
                        break;
                    case 5:
                        StageManager.getInstance().showStage(StageEnum.HELP_STAGE);
                        break;
                    case 6:
                        switch (Configuration.getLanguage()) {
                            case DE:
                                //Configuration.setLanguage(Configuration.LanguageEnum.EN);
                                break;
                            case EN:
                                //Configuration.setLanguage(Configuration.LanguageEnum.DE);
                                break;
                            //case SK:
                            //    Configuration.setLanguage(Configuration.LanguageEnum.DE);
                            //    break;
                        }
                        StageManager.getInstance().showStage(StageEnum.MAIN_STAGE);
                        break;
                    case 7:
                        CheersGdxGame.getFacebookLinkHandler().openFacebookPage("fb://page/312135055904603", "https://www.facebook.com/BernatCooperation");
                        break;
                    case 8:
                        //CheersGdxGame.getInstagramLinkHandler().openInstagramPage("fb://page/312135055904603", "https://www.facebook.com/BernatCooperation");
                        break;

                    default:
                        //StageManager.getInstance().showStage(StageEnum.MAIN_STAGE);
                }
            }
            getScreen().getClicked()[i] = false;
        }


        return true;
    }

}
