package com.gamefactoryx.cheersapp.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.gamefactoryx.cheersapp.tool.Configuration;
import com.gamefactoryx.cheersapp.view.AbstractScreen;

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
                com.gamefactoryx.cheersapp.controller.StageManager.getInstance().showLastStage();
        }
        return true;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (Configuration.isShowBackButton()) {
            if (com.gamefactoryx.cheersapp.tool.Orientation.getOrientation() == Input.Orientation.Landscape) {
                readyGoBack = screenX >= com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldWidthLandscape() * 0.55f &&
                        com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightLandscape() - screenY <= 350;
            } else {

                readyGoBack = screenX >= com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldWidthPortrait() * 0.55f &&
                        com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightPortrait() - screenY <= 350;
            }
        }
        if(Configuration.isShowRules() && getScreen().getRulesButtonSprite() != null){
                if(screenX >= getScreen().getRulesButtonSprite().getX() &&
                        screenX <= getScreen().getRulesButtonSprite().getX() + getScreen().getRulesButtonSprite().getWidth() &&
                        com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightPortrait() - screenY >= getScreen().getRulesButtonSprite().getY() &&
                        com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightPortrait() - screenY <= getScreen().getRulesButtonSprite().getY() + getScreen().getRulesButtonSprite().getHeight() &&
                        com.gamefactoryx.cheersapp.tool.Orientation.getOrientation() == Input.Orientation.Portrait
                        ||
                        screenX >= getScreen().getRulesButtonSprite().getX() &&
                                screenX <= getScreen().getRulesButtonSprite().getX() + getScreen().getRulesButtonSprite().getWidth() &&
                                com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightLandscape() - screenY >= getScreen().getRulesButtonSprite().getY() &&
                                com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightLandscape() - screenY <= getScreen().getRulesButtonSprite().getY() + getScreen().getRulesButtonSprite().getHeight() &&
                                com.gamefactoryx.cheersapp.tool.Orientation.getOrientation() == Input.Orientation.Landscape){
                    Gdx.input.vibrate(10);
                    getScreen().getRulesModel().setShowRulesText(true);
                }
            }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        //if(downXCoor - screenX > 400 ){
        if( getScreen().getRulesModel() != null && getScreen().getRulesModel().isShowRulesText() ) {
            getScreen().getRulesModel().setShowRulesText(false);

            return false;
        }


        if (screen.getBackButtonSprite() == null || !Configuration.isShowBackButton())
            return true;



        boolean flag = false;
        if (Configuration.isShowBackButton() && readyGoBack) {
            if (com.gamefactoryx.cheersapp.tool.Orientation.getOrientation() == Input.Orientation.Landscape) {
                flag = screenX <= com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldWidthLandscape() * 0.45f &&
                        com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightLandscape() - screenY <= 350;
            } else {

                flag = screenX <= com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldWidthPortrait() * 0.45f &&
                        com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightPortrait() - screenY <= 350;
            }
        }

        if (flag) {
            readyGoBack = false;
            Gdx.input.vibrate(10);
            com.gamefactoryx.cheersapp.controller.StageManager.getInstance().showLastStage();
            return true;
        }

        return true;
    }

    protected AbstractScreen getScreen() {
        return screen;
    }

    protected void setScreenLock(int lockType) {
        if (com.gamefactoryx.cheersapp.CheersGdxGame.getScreenLock() != null)
            com.gamefactoryx.cheersapp.CheersGdxGame.getScreenLock().lock(lockType);
    }


}
