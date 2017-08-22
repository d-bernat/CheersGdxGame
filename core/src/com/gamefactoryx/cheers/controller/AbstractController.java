package com.gamefactoryx.cheers.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.gamefactoryx.cheers.CheersGdxGame;
import com.gamefactoryx.cheers.tool.Configuration;
import com.gamefactoryx.cheers.tool.Orientation;
import com.gamefactoryx.cheers.tool.Resolution;
import com.gamefactoryx.cheers.view.AbstractScreen;

/**
 * Created by bernat on 05.05.2017.
 */
@SuppressWarnings("DefaultFileTemplate")
abstract public class AbstractController extends InputAdapter {

    private final AbstractScreen screen;


    protected AbstractController(final AbstractScreen screen){
        this.screen = screen;
        Gdx.input.setInputProcessor(this);
        Gdx.input.setCatchBackKey(true);

    }
    @Override
    public boolean keyDown(int keycode) {

        switch (keycode){
            case Input.Keys.BACK:
                StageManager.getInstance().showLastStage();
        }
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        //if(downXCoor - screenX > 400 ){
        if(screen.getBackButtonSprite() == null)
            return true;

        if(     screenX <= 200 &&
                Resolution.getGameWorldHeightLandscape() - screenY <= 200 &&
                Orientation.getOrientation() == Input.Orientation.Landscape ||

                screenX <= 200 &&
                Resolution.getGameWorldHeightPortrait() - screenY <= 200 &&
                Orientation.getOrientation() == Input.Orientation.Portrait)
        {
            Gdx.input.vibrate(10);
            StageManager.getInstance().showLastStage();
            return false;
        }

       /* for (int i = 0; i < getScreen().getCountOfButtons(); i++) {
            if (getScreen().getClicked()[i])
                switch (i) {
                    case 0:
                        Configuration.setLanguage(Configuration.LanguageEnum.DE);
                        StageManager.getInstance().showStage();
                        break;
                    case 1:
                        Configuration.setLanguage(Configuration.LanguageEnum.EN);
                        StageManager.getInstance().showStage();
                        break;
                    case 2:
                        StageManager.getInstance().showStage(StageEnum.MAIN_STAGE);
                        break;
                }

            getScreen().getClicked()[i] = false;
        }*/
        return true;
    }
    protected AbstractScreen getScreen(){ return screen; }

    protected void setScreenLock(int lockType) {
        if (CheersGdxGame.getScreenLock() != null)
            CheersGdxGame.getScreenLock().lock(lockType);
    }


}
