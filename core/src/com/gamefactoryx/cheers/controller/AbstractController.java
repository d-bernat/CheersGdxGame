package com.gamefactoryx.cheers.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.gamefactoryx.cheers.model.Configuration;
import com.gamefactoryx.cheers.view.AbstractScreen;

/**
 * Created by bernat on 05.05.2017.
 */
@SuppressWarnings("DefaultFileTemplate")
abstract class AbstractController extends InputAdapter {

    private final AbstractScreen screen;

    AbstractController(final AbstractScreen screen){
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
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        for (int i = 0; i < getScreen().getCountOfButtons(); i++) {
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
        }
        return true;
    }
    AbstractScreen getScreen(){ return screen; }

}
