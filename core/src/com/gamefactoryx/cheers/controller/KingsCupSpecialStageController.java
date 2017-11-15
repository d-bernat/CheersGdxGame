package com.gamefactoryx.cheers.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.gamefactoryx.cheers.model.KingsCupSpecialModel;
import com.gamefactoryx.cheers.tool.Orientation;
import com.gamefactoryx.cheers.tool.Resolution;
import com.gamefactoryx.cheers.view.AbstractScreen;

import java.util.Random;


/**
 * Created by bernat on 28.04.2017.
 */
@SuppressWarnings("DefaultFileTemplate")
final class KingsCupSpecialStageController extends AbstractController {

    private int lastYPointerPos;
    private static boolean animationRunning;
    private float position;

    KingsCupSpecialStageController(final AbstractScreen screen) {
        super(screen);
        animationRunning = false;
        setScreenLock(10);
        KingsCupSpecialModel.getNewInstance();
        Gdx.app.log("************", KingsCupSpecialModel.getInstance().getRotation() + "");
        Gdx.app.log("************", KingsCupSpecialModel.getInstance().getRotation() + "");
        Gdx.app.log("************", KingsCupSpecialModel.getInstance().getRotation() + "");
        Gdx.app.log("************", KingsCupSpecialModel.getInstance().getRotation() + "");
        Gdx.app.log("************", KingsCupSpecialModel.getInstance().getRotation() + "");
        Gdx.app.log("************", KingsCupSpecialModel.getInstance().getRotation() + "");

    }


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        super.touchDown(screenX, screenY, pointer, button);
        lastYPointerPos = screenY;
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if(!super.touchUp(screenX, screenY, pointer, button)) {
            return true;
        }
        if(!animationRunning) {
            KingsCupSpecialModel.getNewInstance();
            animation();
        }
        return true;
    }

    @Override
    public boolean touchDragged (int screenX, int screenY, int pointer) {

        int SCROLLING_SLOW_DOWN = 30;
        if(screenX >= getScreen().getTextBox().getX() &&
                screenX <= getScreen().getTextBox().getX() + getScreen().getTextBox().getWidth() &&
                Resolution.getGameWorldHeightPortrait() - screenY >= getScreen().getTextBox().getY() &&
                Resolution.getGameWorldHeightPortrait() - screenY <= getScreen().getTextBox().getY() + getScreen().getTextBox().getHeight() &&
                Orientation.getOrientation() == Input.Orientation.Portrait ||

                screenX >= getScreen().getTextBox().getX() &&
                        screenX <= getScreen().getTextBox().getX() + getScreen().getTextBox().getWidth() &&
                        Resolution.getGameWorldHeightLandscape() - screenY >= getScreen().getTextBox().getY() &&
                        Resolution.getGameWorldHeightLandscape() - screenY <= getScreen().getTextBox().getY() + getScreen().getTextBox().getHeight() &&
                        Orientation.getOrientation() == Input.Orientation.Landscape
                ) {
            if(screenY < lastYPointerPos - SCROLLING_SLOW_DOWN) {
                getScreen().setYScrollPos(getScreen().getYScrollPos() + 1);
                lastYPointerPos = screenY;
            }
            else if(screenY > lastYPointerPos + SCROLLING_SLOW_DOWN) {
                getScreen().setYScrollPos(getScreen().getYScrollPos() - 1);
                lastYPointerPos = screenY;
            }

        }

        return true;
    }


    public void animation() {
        animationRunning = true;
        Random rand = new Random();
        final int n = rand.nextInt((5-1) + 1) + 1;

        new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                int cykles = n * 20;
                while (animationRunning && i < cykles) {
                    long time = System.currentTimeMillis();
                    while (System.currentTimeMillis() < time + 50L) {
                    }
                    if(i < cykles / 2)
                        KingsCupSpecialModel.getInstance().setRotation(KingsCupSpecialModel.getInstance().getRotation() - 1.0f);
                    else
                        KingsCupSpecialModel.getInstance().setRotation(KingsCupSpecialModel.getInstance().getRotation() + 1.0f);

                    i++;
                }
                animationRunning = false;
                KingsCupSpecialModel.getInstance().setRotation(0.0f);


            }

        }).start();
    }

}
