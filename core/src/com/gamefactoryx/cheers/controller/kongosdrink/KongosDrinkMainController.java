package com.gamefactoryx.cheers.controller.kongosdrink;

import com.badlogic.gdx.Screen;
import com.gamefactoryx.cheers.model.kongosdrink.KongosDrinkMainModel;
import com.gamefactoryx.cheers.model.kongosdrink.PlayerModel;
import com.gamefactoryx.cheers.tool.Configuration;


/**
 * Created by bernat on 28.04.2017.
 */
@SuppressWarnings("DefaultFileTemplate")
final public class KongosDrinkMainController extends KongosDrinkAbstractController {

    private boolean forward = true;
    private boolean isRunning = false;
    private boolean suspend = false;
    private int delay = 1;

    public KongosDrinkMainController(final Screen screen) {
        super(screen);
        setScreenLock(0);
        KongosDrinkMainModel.getInstance().setPlayers(new PlayerModel[]{ new PlayerModel(), new PlayerModel(), new PlayerModel()});
        KongosDrinkMainModel.getInstance().setPosition(1);
        KongosDrinkMainModel.getInstance().getPlayers()[0].setPosition(11);
        KongosDrinkMainModel.getInstance().getPlayers()[1].setPosition(15);
        KongosDrinkMainModel.getInstance().getPlayers()[2].setPosition(3);
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

        switch(KongosDrinkMainModel.getInstance().getPosition()) {
            case 1:
                movePlayground(11);
                break;
            case 11:
                KongosDrinkMainModel.getInstance().getPlayers()[0].setActive(true);
                movePlayground(4);
                break;
            case 4:
                KongosDrinkMainModel.getInstance().getPlayers()[0].setActive(false);
                KongosDrinkMainModel.getInstance().getPlayers()[0].setPosition(4);
                movePlayground(3);
                break;
            case 3:
                KongosDrinkMainModel.getInstance().getPlayers()[2].setActive(true);
                movePlayground(10);
                break;
            case 10:
                KongosDrinkMainModel.getInstance().getPlayers()[2].setActive(false);
                KongosDrinkMainModel.getInstance().getPlayers()[2].setPosition(10);
                movePlayground(15);
                break;
            case 15:
                KongosDrinkMainModel.getInstance().getPlayers()[1].setActive(true);
                movePlayground(2);
                break;
            case 2:
                KongosDrinkMainModel.getInstance().getPlayers()[2].setActive(false);
                KongosDrinkMainModel.getInstance().getPlayers()[2].setPosition(2);
                break;
        }
        return false;

    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {

        //KongosDrinkMainModel.getInstance().setXcoor(this.screenX - screenX);
        return false;
    }


    private void movePlayground(final int  position){
        if( KongosDrinkMainModel.getInstance().getPosition() == position)
            return;


        suspend = false;

        if (!isRunning) {
            isRunning = true;

            forward = KongosDrinkMainModel.getInstance().getPosition() < position;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (!suspend) {
                        for (int i = 1; i < 9; i++) {
                            for(int j = 0; j < KongosDrinkMainModel.getInstance().getPlayers().length; j++)
                                if(KongosDrinkMainModel.getInstance().getPlayers()[j].isActive())
                                    if(forward)
                                        KongosDrinkMainModel.getInstance().getPlayers()[j].setRotate(-10.0f);
                                    else
                                        KongosDrinkMainModel.getInstance().getPlayers()[j].setRotate(10.0f);

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
                                && position == -1) {
                        }

                        if (KongosDrinkMainModel.getInstance().getPosition() != -1 &&
                                KongosDrinkMainModel.getInstance().getXxcoor() == (position - 1) * Configuration.DISTANCE_BETWEEN_TWO_FIELDS) {
                            isRunning = false;
                            break;
                        }

                        if (KongosDrinkMainModel.getInstance().getXxcoor() <= 0 && !forward) {
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
                    }
                    KongosDrinkMainModel.getInstance().setPosition(position);
                    forward = false;
                    for(int i = 0; i < KongosDrinkMainModel.getInstance().getPlayers().length; i++)
                        if(KongosDrinkMainModel.getInstance().getPlayers()[i].isActive())
                            KongosDrinkMainModel.getInstance().getPlayers()[i].setRotate(0.0f);

                }
            }).start();
        }

    }


    private void backup(final int  position){
        suspend = false;

        if (!isRunning) {
            isRunning = true;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int i = 0; i < KongosDrinkMainModel.getInstance().getPlayers().length; i++)
                        if(KongosDrinkMainModel.getInstance().getPlayers()[i].isActive())
                            KongosDrinkMainModel.getInstance().getPlayers()[i].setRotate(-4.0f);

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
                                && position == -1) {
                            forward = false;
                            for(int i = 0; i < KongosDrinkMainModel.getInstance().getPlayers().length; i++)
                                if(KongosDrinkMainModel.getInstance().getPlayers()[i].isActive())
                                    KongosDrinkMainModel.getInstance().getPlayers()[i].setRotate(4.0f);
                        }

                        if (KongosDrinkMainModel.getInstance().getPosition() != -1 &&
                                KongosDrinkMainModel.getInstance().getXxcoor() == (KongosDrinkMainModel.getInstance().getPosition() - 1) * Configuration.DISTANCE_BETWEEN_TWO_FIELDS &&
                                forward) {
                            forward = false;
                            for(int i = 0; i < KongosDrinkMainModel.getInstance().getPlayers().length; i++)
                                if(KongosDrinkMainModel.getInstance().getPlayers()[i].isActive())
                                    KongosDrinkMainModel.getInstance().getPlayers()[i].setRotate(4.0f);
                            delay = 500;
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
                            /*KongosDrinkMainModel.getInstance().setXxcoor(KongosDrinkMainModel.getInstance().getXxcoor() - 1);
                            KongosDrinkMainModel.getInstance().setXcoor(KongosDrinkMainModel.getInstance().getXcoor() - 1);*/
                        }

                        long time = System.currentTimeMillis();
                        while (System.currentTimeMillis() < time + delay) {
                        }
                        delay = 1;
                    }
                }
            }).start();
        }

    }
}
