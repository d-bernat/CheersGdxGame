package com.gamefactoryx.cheers.controller.kongosdrink;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.gamefactoryx.cheers.controller.AbstractController;
import com.gamefactoryx.cheers.controller.StageEnum;
import com.gamefactoryx.cheers.controller.StageManager;
import com.gamefactoryx.cheers.model.kongosdrink.KongosDrinkMainModel;
import com.gamefactoryx.cheers.view.AbstractScreen;


/**
 * Created by bernat on 28.04.2017.
 */
@SuppressWarnings("DefaultFileTemplate")
final public class KongosDrinkMainController extends KongosDrinkAbstractController {

    private boolean forward = true;
    private boolean isRunning = false;
    private boolean suspend = false;
    private int delay = 3;
    private static final int DISTANCE_BETWEEN_TWO_FIELDS = 187;

    public KongosDrinkMainController(final Screen screen) {
        super(screen);
        setScreenLock(0);
        KongosDrinkMainModel.getInstance().setPosition(49);
    }


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        if(!super.touchUp(screenX, screenY, pointer, button)) {
            KongosDrinkMainModel.getInstance().setXxcoor(0);
            KongosDrinkMainModel.getInstance().setXcoor(0);
            KongosDrinkMainModel.getInstance().setPlayerIndex(0);
            suspend = true;
            return false;
        }
        suspend = false;

        if (!isRunning) {
            isRunning = true;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    KongosDrinkMainModel.getInstance().setRotate(-4.0f);
                    while (!suspend) {


                        for (int i = 1; i < 9; i++) {
                            if (KongosDrinkMainModel.getInstance().getXxcoor() == 960 * i && forward) {
                                KongosDrinkMainModel.getInstance().setIndex(i);
                                KongosDrinkMainModel.getInstance().setXcoor(0);
                            }


                            if (KongosDrinkMainModel.getInstance().getXxcoor() == 960 * i && !forward) {
                                KongosDrinkMainModel.getInstance().setIndex(i - 1);
                                KongosDrinkMainModel.getInstance().setXcoor(960);
                            }
                        }

                        if (KongosDrinkMainModel.getInstance().getXxcoor() >= 960 * 9 &&
                                forward
                                && KongosDrinkMainModel.getInstance().getPosition() == -1) {
                            forward = false;
                            KongosDrinkMainModel.getInstance().setRotate(4.0f);
                        }

                        if (KongosDrinkMainModel.getInstance().getPosition() != -1 &&
                                KongosDrinkMainModel.getInstance().getXxcoor() == (KongosDrinkMainModel.getInstance().getPosition() - 1) * DISTANCE_BETWEEN_TWO_FIELDS  &&
                                forward) {
                            forward = false;
                            KongosDrinkMainModel.getInstance().setRotate(4.0f);
                            delay = 1000;
                        }

                        if (KongosDrinkMainModel.getInstance().getXxcoor() <= 0 && !forward) {
                            forward = true;
                            KongosDrinkMainModel.getInstance().setXxcoor(0);
                            KongosDrinkMainModel.getInstance().setXcoor(0);
                            KongosDrinkMainModel.getInstance().setIndex(0);
                            KongosDrinkMainModel.getInstance().setRotate(0f);
                            isRunning = false;
                            break;
                        }


                        if (forward) {
                            KongosDrinkMainModel.getInstance().setXxcoor(KongosDrinkMainModel.getInstance().getXxcoor() + 1);
                            KongosDrinkMainModel.getInstance().setXcoor(KongosDrinkMainModel.getInstance().getXcoor() + 1);
                        } else {
                            KongosDrinkMainModel.getInstance().setXxcoor(KongosDrinkMainModel.getInstance().getXxcoor() - 1);
                            KongosDrinkMainModel.getInstance().setXcoor(KongosDrinkMainModel.getInstance().getXcoor() - 1);
                        }

                        long time = System.currentTimeMillis();
                        while (System.currentTimeMillis() < time + delay) {
                        }
                        delay = 3;
                    }
                }
            }).start();
        }

        return false;

    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {

        //KongosDrinkMainModel.getInstance().setXcoor(this.screenX - screenX);
        return false;
    }
}
