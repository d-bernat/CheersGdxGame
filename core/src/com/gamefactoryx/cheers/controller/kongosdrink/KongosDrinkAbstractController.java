package com.gamefactoryx.cheers.controller.kongosdrink;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.gamefactoryx.cheers.CheersGdxGame;
import com.gamefactoryx.cheers.controller.StageEnum;
import com.gamefactoryx.cheers.controller.StageManager;
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
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        downXCoor = screenX;
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if(downXCoor - screenX > 400 ){
            Gdx.input.vibrate(10);
            StageManager.getInstance().showStage(StageEnum.NEW_GAME_STAGE);
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
    protected KongosDrinkMainScreen getScreen(){ return screen; }

    protected void setScreenLock(int lockType) {
        if (CheersGdxGame.getScreenLock() != null)
            CheersGdxGame.getScreenLock().lock(lockType);
    }


}
