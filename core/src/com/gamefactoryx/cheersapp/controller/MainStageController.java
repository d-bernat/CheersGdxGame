package com.gamefactoryx.cheersapp.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.gamefactoryx.cheersapp.tool.Configuration;
import com.gamefactoryx.cheersapp.view.AbstractScreen;


/**
 * Created by bernat on 28.04.2017.
 */
@SuppressWarnings("DefaultFileTemplate")
final class MainStageController extends com.gamefactoryx.cheersapp.controller.AbstractController {

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

        for (int i = 0; i < getScreen().getButtons().length; i++) {
            if (getScreen().getClicked()[i]) {
                Gdx.input.vibrate(10);
                switch (i) {
                    case 0:
                        com.gamefactoryx.cheersapp.controller.StageManager.getInstance().showStage(com.gamefactoryx.cheersapp.controller.StageEnum.NEW_GAME_STAGE);
                        break;
                    case 1:
                        com.gamefactoryx.cheersapp.controller.StageManager.getInstance().showStage(com.gamefactoryx.cheersapp.controller.StageEnum.COCKTAILS_STAGE);
                        break;
                    case 2:
                        com.gamefactoryx.cheersapp.controller.StageManager.getInstance().showStage(com.gamefactoryx.cheersapp.controller.StageEnum.HELP_STAGE);
                        break;
                    case 3:
                        com.gamefactoryx.cheersapp.controller.StageManager.getInstance().showStage(com.gamefactoryx.cheersapp.controller.StageEnum.HALL_OF_FAME_STAGE);
                        break;
                    case 4:
                        com.gamefactoryx.cheersapp.controller.StageManager.getInstance().showStage(com.gamefactoryx.cheersapp.controller.StageEnum.SETUP_STAGE);
                        break;
                    case 5:
                        com.gamefactoryx.cheersapp.controller.StageManager.getInstance().showStage(com.gamefactoryx.cheersapp.controller.StageEnum.CREDIT_STAGE);
                        break;
                    case 6:
                        switch (Configuration.getLanguage()) {
                            case DE:
                                Configuration.setLanguage(Configuration.LanguageEnum.EN);
                                break;
                            case EN:
                                Configuration.setLanguage(Configuration.LanguageEnum.DE);
                                break;
                            //case SK:
                            //    Configuration.setLanguage(Configuration.LanguageEnum.DE);
                            //    break;
                        }
                        com.gamefactoryx.cheersapp.controller.StageManager.getInstance().showStage(com.gamefactoryx.cheersapp.controller.StageEnum.MAIN_STAGE);
                        break;
                    case 7:
                        com.gamefactoryx.cheersapp.CheersGdxGame.getLinkHandler().openFacebookPage("fb://page/312135055904603", "https://www.facebook.com/BernatCooperation");
                        break;
                    case 8:
                        com.gamefactoryx.cheersapp.CheersGdxGame.getLinkHandler().openInstagramPage("https://www.instagram.com/cheersapp.official");
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
