package com.gamefactoryx.cheers.controller.kongosdrink;

import com.badlogic.gdx.Gdx;
import com.gamefactoryx.cheers.controller.AbstractController;
import com.gamefactoryx.cheers.controller.StageEnum;
import com.gamefactoryx.cheers.controller.StageManager;
import com.gamefactoryx.cheers.model.kongosdrink.KongosDrinkMainModel;
import com.gamefactoryx.cheers.view.AbstractScreen;


/**
 * Created by bernat on 28.04.2017.
 */
@SuppressWarnings("DefaultFileTemplate")
final public class KongosDrinkMainController extends AbstractController {

    private static int counter;
    private int screenX;
    private boolean forward = true;
    private boolean isRunning = false;

    public KongosDrinkMainController(final AbstractScreen screen) {
        super(screen);
        setScreenLock(0);
    }


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        this.screenX = screenX;
        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        /*if(KongosDrinkMainModel.getInstance().getIndex() == 0)
            KongosDrinkMainModel.getInstance().setIndex(1);
        else
            KongosDrinkMainModel.getInstance().setIndex(0);*/
        if (!isRunning) {
            isRunning = true;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {

                        if (KongosDrinkMainModel.getInstance().getXxcoor() == 960 && forward) {
                            KongosDrinkMainModel.getInstance().setIndex(1);
                            KongosDrinkMainModel.getInstance().setXcoor(0);
                        }

                        if (KongosDrinkMainModel.getInstance().getXxcoor() == 960 && !forward) {
                            KongosDrinkMainModel.getInstance().setIndex(0);
                            KongosDrinkMainModel.getInstance().setXcoor(960);
                        }


                        if (KongosDrinkMainModel.getInstance().getXxcoor() == 1920 && forward) {
                            KongosDrinkMainModel.getInstance().setIndex(2);
                            KongosDrinkMainModel.getInstance().setXcoor(0);
                        }

                        if (KongosDrinkMainModel.getInstance().getXxcoor() == 1920 && !forward) {
                            KongosDrinkMainModel.getInstance().setIndex(1);
                            KongosDrinkMainModel.getInstance().setXcoor(960);
                        }

                        if (KongosDrinkMainModel.getInstance().getXxcoor() == 2880 && forward) {
                            KongosDrinkMainModel.getInstance().setIndex(3);
                            KongosDrinkMainModel.getInstance().setXcoor(0);
                        }

                        if (KongosDrinkMainModel.getInstance().getXxcoor() == 2880 && !forward) {
                            KongosDrinkMainModel.getInstance().setIndex(2);
                            KongosDrinkMainModel.getInstance().setXcoor(960);
                        }

                        if (KongosDrinkMainModel.getInstance().getXxcoor() >= 3840 && forward)
                            forward = false;

                        if (KongosDrinkMainModel.getInstance().getXxcoor() <= 0 && !forward) {
                            forward = true;
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
                        while (System.currentTimeMillis() < time + 3) {
                        }
                    }
                    isRunning = false;
                }
            }).start();
        }

        return super.touchUp(screenX, screenY, pointer, button);

    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {

        //KongosDrinkMainModel.getInstance().setXcoor(this.screenX - screenX);
        return false;
    }
}
