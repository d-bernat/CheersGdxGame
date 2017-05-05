package com.gamefactoryx.cheers.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.gamefactoryx.cheers.tool.Orientation;
import com.gamefactoryx.cheers.tool.Resolution;
import com.gamefactoryx.cheers.view.AbstractScreen;


/**
 * Created by bernat on 28.04.2017.
 */
@SuppressWarnings("DefaultFileTemplate")
public class MainStageController extends AbstractController {

    private final AbstractScreen screen;

    MainStageController(AbstractScreen view){
        this.screen = view;
        Gdx.input.setInputProcessor(this);
    }




    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        for (int i = 0; i < screen.getCountOfButtons(); i++) {
            screen.getClicked()[i] = (screenX >= screen.getButtons()[i][0].getX() &&
                    screenX <= screen.getButtons()[i][0].getX() + screen.getButtons()[i][0].getWidth() &&
                    Resolution.getGameWorldHeightPortrait() - screenY >= screen.getButtons()[i][0].getY() &&
                    Resolution.getGameWorldHeightPortrait() - screenY <= screen.getButtons()[i][0].getY() + screen.getButtons()[i][0].getHeight() &&
                    Orientation.getOrientation() == Input.Orientation.Portrait
                    ||
                    screenX >= screen.getButtons()[i][0].getX() &&
                            screenX <= screen.getButtons()[i][0].getX() + screen.getButtons()[i][0].getWidth() &&
                            Resolution.getGameWorldHeightLandscape() - screenY >= screen.getButtons()[i][0].getY() &&
                            Resolution.getGameWorldHeightLandscape() - screenY <= screen.getButtons()[i][0].getY() + screen.getButtons()[i][0].getHeight() &&
                            Orientation.getOrientation() == Input.Orientation.Landscape);
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        for (int i = 0; i < screen.getButtons().length; i++) {
            if (screen.getClicked()[i])
                switch (i) {
                    case 0:
                        StageManager.getInstance().showStage(StageEnum.NEW_GAME_STAGE);
                        break;
                    default:
                        StageManager.getInstance().showStage(StageEnum.MAIN_STAGE);
                }
            screen.getClicked()[i] = false;
        }


        return true;
    }

    @Override
    public AbstractScreen getScreen(){ return screen; }
}
