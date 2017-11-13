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
    private boolean readyGoBack = false;

    protected AbstractController(final AbstractScreen screen) {
        this.screen = screen;
        Gdx.input.setInputProcessor(this);
        Gdx.input.setCatchBackKey(true);

    }

    @Override
    public boolean keyDown(int keycode) {

        switch (keycode) {
            case Input.Keys.BACK:
                StageManager.getInstance().showLastStage();
        }
        return true;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (Configuration.isShowBackButton()) {
            if (Orientation.getOrientation() == Input.Orientation.Landscape) {
                readyGoBack = screenX >= Resolution.getGameWorldWidthLandscape() * 0.55f &&
                        Resolution.getGameWorldHeightLandscape() - screenY <= 350;
            } else {

                readyGoBack = screenX >= Resolution.getGameWorldWidthPortrait() * 0.55f &&
                        Resolution.getGameWorldHeightPortrait() - screenY <= 350;
            }
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        //if(downXCoor - screenX > 400 ){
        if (screen.getBackButtonSprite() == null || !Configuration.isShowBackButton())
            return true;



        boolean flag = false;
        if (Configuration.isShowBackButton() && readyGoBack) {
            if (Orientation.getOrientation() == Input.Orientation.Landscape) {
                flag = screenX <= Resolution.getGameWorldWidthLandscape() * 0.45f &&
                        Resolution.getGameWorldHeightLandscape() - screenY <= 350;
            } else {

                flag = screenX <= Resolution.getGameWorldWidthPortrait() * 0.45f &&
                        Resolution.getGameWorldHeightPortrait() - screenY <= 350;
            }
        }

        if (flag) {
            readyGoBack = false;
            Gdx.input.vibrate(10);
            StageManager.getInstance().showLastStage();
            return true;
        }

        return true;
    }

    protected AbstractScreen getScreen() {
        return screen;
    }

    protected void setScreenLock(int lockType) {
        if (CheersGdxGame.getScreenLock() != null)
            CheersGdxGame.getScreenLock().lock(lockType);
    }


}
