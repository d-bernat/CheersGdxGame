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

    private final AbstractScreen view;

    MainStageController(AbstractScreen view){
        this.view = view;
        Gdx.input.setInputProcessor(this);
    }




    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        for (int i = 0; i < view.getCountOfButtons(); i++) {
            view.getClicked()[i] = (screenX >= view.getButtons()[i][0].getX() &&
                    screenX <= view.getButtons()[i][0].getX() + view.getButtons()[i][0].getWidth() &&
                    Resolution.getGameWorldHeightPortrait() - screenY >= view.getButtons()[i][0].getY() &&
                    Resolution.getGameWorldHeightPortrait() - screenY <= view.getButtons()[i][0].getY() + view.getButtons()[i][0].getHeight() &&
                    Orientation.getOrientation() == Input.Orientation.Portrait
                    ||
                    screenX >= view.getButtons()[i][0].getX() &&
                            screenX <= view.getButtons()[i][0].getX() + view.getButtons()[i][0].getWidth() &&
                            Resolution.getGameWorldHeightLandscape() - screenY >= view.getButtons()[i][0].getY() &&
                            Resolution.getGameWorldHeightLandscape() - screenY <= view.getButtons()[i][0].getY() + view.getButtons()[i][0].getHeight() &&
                            Orientation.getOrientation() == Input.Orientation.Landscape);
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        for (int i = 0; i < view.getButtons().length; i++) {
            if (view.getClicked()[i])
                switch (i) {
                    case 0:
                        StageManager.getInstance().showStage(StageEnum.NEW_GAME_STAGE);
                        break;
                    default:
                        StageManager.getInstance().showStage(StageEnum.MAIN_STAGE);
                }
            view.getClicked()[i] = false;
        }


        return true;
    }

    @Override
    public AbstractScreen getView(){ return view; }
}
