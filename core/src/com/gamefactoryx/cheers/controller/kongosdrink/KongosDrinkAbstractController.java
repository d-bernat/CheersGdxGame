package com.gamefactoryx.cheers.controller.kongosdrink;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.gamefactoryx.cheers.CheersGdxGame;
import com.gamefactoryx.cheers.controller.StageEnum;
import com.gamefactoryx.cheers.controller.StageManager;
import com.gamefactoryx.cheers.tool.Orientation;
import com.gamefactoryx.cheers.tool.Resolution;
import com.gamefactoryx.cheers.view.AbstractScreen;
import com.gamefactoryx.cheers.view.kongosdrink.KongosDrinkMainScreen;

/**
 * Created by bernat on 05.05.2017.
 */
@SuppressWarnings("DefaultFileTemplate")
abstract public class KongosDrinkAbstractController extends InputAdapter {

    private final KongosDrinkMainScreen screen;
    private int downXCoor;

    protected KongosDrinkAbstractController(final KongosDrinkMainScreen screen){
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
        return true;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        downXCoor = screenX;
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
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

        return true;
    }
    protected KongosDrinkMainScreen getScreen(){ return screen; }

    protected void setScreenLock(int lockType) {
        if (CheersGdxGame.getScreenLock() != null)
            CheersGdxGame.getScreenLock().lock(lockType);
    }


}
