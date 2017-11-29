package com.gamefactoryx.cheersapp.controller;

import com.badlogic.gdx.Input;

import java.util.Random;


/**
 * Created by bernat on 28.04.2017.
 */
@SuppressWarnings("DefaultFileTemplate")
final class KingsCupSpecialStageController extends AbstractController {

    private int lastYPointerPos;
    private static boolean animationRunning;
    private float position;

    KingsCupSpecialStageController(final com.gamefactoryx.cheersapp.view.AbstractScreen screen) {
        super(screen);
        animationRunning = false;
        setScreenLock(10);
        com.gamefactoryx.cheersapp.model.KingsCupSpecialModel.getNewInstance();
    }


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        super.touchDown(screenX, screenY, pointer, button);
        lastYPointerPos = screenY;
        for (int i = 0; i < getScreen().getCountOfButtons(); i++) {
            if (i == 0 && !com.gamefactoryx.cheersapp.model.KingsCupSpecialModel.getInstance().isShowTask()) {
                getScreen().getClicked()[i] = (screenX >= getScreen().getButtons()[i][0].getX() &&
                        screenX <= getScreen().getButtons()[i][0].getX() + getScreen().getButtons()[i][0].getWidth() &&
                        com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightPortrait() - screenY >= getScreen().getButtons()[i][0].getY() &&
                        com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightPortrait() - screenY <= getScreen().getButtons()[i][0].getY() + getScreen().getButtons()[i][0].getHeight() &&
                        com.gamefactoryx.cheersapp.tool.Orientation.getOrientation() == Input.Orientation.Portrait
                        ||
                        screenX >= getScreen().getButtons()[i][0].getX() &&
                                screenX <= getScreen().getButtons()[i][0].getX() + getScreen().getButtons()[i][0].getWidth() &&
                                com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightLandscape() - screenY >= getScreen().getButtons()[i][0].getY() &&
                                com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightLandscape() - screenY <= getScreen().getButtons()[i][0].getY() + getScreen().getButtons()[i][0].getHeight() &&
                                com.gamefactoryx.cheersapp.tool.Orientation.getOrientation() == Input.Orientation.Landscape);

            } else if (i == 1 && com.gamefactoryx.cheersapp.model.KingsCupSpecialModel.getInstance().isShowTask()) {
                getScreen().getClicked()[i] = (screenX >= getScreen().getButtons()[i][0].getX() &&
                        screenX <= getScreen().getButtons()[i][0].getX() + getScreen().getButtons()[i][0].getWidth() &&
                        com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightPortrait() - screenY >= getScreen().getButtons()[i][0].getY() &&
                        com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightPortrait() - screenY <= getScreen().getButtons()[i][0].getY() + getScreen().getButtons()[i][0].getHeight() &&
                        com.gamefactoryx.cheersapp.tool.Orientation.getOrientation() == Input.Orientation.Portrait
                        ||
                        screenX >= getScreen().getButtons()[i][0].getX() &&
                                screenX <= getScreen().getButtons()[i][0].getX() + getScreen().getButtons()[i][0].getWidth() &&
                                com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightLandscape() - screenY >= getScreen().getButtons()[i][0].getY() &&
                                com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightLandscape() - screenY <= getScreen().getButtons()[i][0].getY() + getScreen().getButtons()[i][0].getHeight() &&
                                com.gamefactoryx.cheersapp.tool.Orientation.getOrientation() == Input.Orientation.Landscape);
            }else{
                getScreen().getClicked()[i] = false;
            }

        }

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (!super.touchUp(screenX, screenY, pointer, button)) {
            return true;
        }

        if (com.gamefactoryx.cheersapp.model.KingsCupSpecialModel.getInstance().isShowTask()) {
            if (getScreen().getClicked()[1]) {
                com.gamefactoryx.cheersapp.model.KingsCupSpecialModel.getInstance().setShowTask(false);

            }
        } else {

            for (int i = 0; i < getScreen().getCountOfButtons(); i++) {
                if (getScreen().getClicked()[i]) {
                    if (!animationRunning) {
                        com.gamefactoryx.cheersapp.model.KingsCupSpecialModel.getNewInstance();
                        animation();
                    }
                    getScreen().getClicked()[i] = false;
                }
            }
        }

        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {

       /* int SCROLLING_SLOW_DOWN = 30;
        if (screenX >= getScreen().getTextBox().getX() &&
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
            if (screenY < lastYPointerPos - SCROLLING_SLOW_DOWN) {
                getScreen().setYScrollPos(getScreen().getYScrollPos() + 1);
                lastYPointerPos = screenY;
            } else if (screenY > lastYPointerPos + SCROLLING_SLOW_DOWN) {
                getScreen().setYScrollPos(getScreen().getYScrollPos() - 1);
                lastYPointerPos = screenY;
            }

        }*/

        return true;
    }


    public void animation() {
        animationRunning = true;
        Random rand = new Random();
        final int n = rand.nextInt((5 - 1) + 1) + 1;

        new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                int cykles = n * 20;
                while (animationRunning && i < cykles) {
                    long time = System.currentTimeMillis();
                    while (System.currentTimeMillis() < time + 50L) {
                    }
                    if (i < cykles / 2)
                        com.gamefactoryx.cheersapp.model.KingsCupSpecialModel.getInstance().setRotation(com.gamefactoryx.cheersapp.model.KingsCupSpecialModel.getInstance().getRotation() - 1.0f);
                    else
                        com.gamefactoryx.cheersapp.model.KingsCupSpecialModel.getInstance().setRotation(com.gamefactoryx.cheersapp.model.KingsCupSpecialModel.getInstance().getRotation() + 1.0f);

                    i++;
                }
                long time = System.currentTimeMillis();
                while (System.currentTimeMillis() < time + 500L) {
                }

                animationRunning = false;
                com.gamefactoryx.cheersapp.model.KingsCupSpecialModel.getInstance().setShowTask(true);
                com.gamefactoryx.cheersapp.model.KingsCupSpecialModel.getInstance().setRotation(0.0f);


            }

        }).start();
    }

}
