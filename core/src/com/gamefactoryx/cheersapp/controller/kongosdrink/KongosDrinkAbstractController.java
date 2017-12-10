package com.gamefactoryx.cheersapp.controller.kongosdrink;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.gamefactoryx.cheersapp.controller.StageManager;
import com.gamefactoryx.cheersapp.tool.Orientation;
import com.gamefactoryx.cheersapp.view.kongosdrink.KongosDrinkMainScreen;

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
                com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().setFinished(true);
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
                com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightLandscape() - screenY <= 200 &&
                Orientation.getOrientation() == Input.Orientation.Landscape ||

                screenX <= 200 &&
                        com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightPortrait() - screenY <= 200 &&
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
        if (com.gamefactoryx.cheersapp.CheersGdxGame.getScreenLock() != null)
            com.gamefactoryx.cheersapp.CheersGdxGame.getScreenLock().lock(lockType);
    }


}
