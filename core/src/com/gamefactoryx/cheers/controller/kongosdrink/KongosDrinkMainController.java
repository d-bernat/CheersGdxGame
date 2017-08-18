package com.gamefactoryx.cheers.controller.kongosdrink;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.gamefactoryx.cheers.model.kongosdrink.KongosDrinkMainModel;
import com.gamefactoryx.cheers.tool.Resolution;
import com.gamefactoryx.cheers.tool.kongosdrink.CardTextParser;
import com.gamefactoryx.cheers.tool.kongosdrink.Configuration;
import com.gamefactoryx.cheers.tool.kongosdrink.CoorTransformator;
import com.gamefactoryx.cheers.view.kongosdrink.KongosDrinkMainScreen;
import com.gamefactoryx.cheers.view.kongosdrink.ModusSprite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


/**
 * Created by bernat on 28.04.2017.
 */
@SuppressWarnings("DefaultFileTemplate")
final public class KongosDrinkMainController extends KongosDrinkAbstractController {

    private boolean forward = true;
    private boolean isRunning = false;
    private boolean suspend = false;
    private int delay = 1;

    public KongosDrinkMainController(final KongosDrinkMainScreen screen) {
        super(screen);
        setScreenLock(0);

        new Thread(new Runnable() {
            @Override
            public void run() {
                long accInterval = 0L;
                List<Integer> rnds = new ArrayList<>();
                for (int i = 0; i < 11; i++)
                    rnds.add(i);
                while (!KongosDrinkMainModel.getInstance().isFinished()) {
                    long time = System.currentTimeMillis();

                    while (System.currentTimeMillis() < time + Configuration.getModusTypeInterval() &&
                            !KongosDrinkMainModel.getInstance().isFinished()) {
                    }
                    accInterval += Configuration.getModusTypeInterval();
                    if (!KongosDrinkMainModel.getInstance().isFinished()) {
                        Collections.shuffle(rnds);
                        if (accInterval / Configuration.getModusTypeInterval() < 4)
                            KongosDrinkMainModel.getInstance().setModus((int) (Math.pow(2.0d, rnds.get(0))));
                        else if (accInterval / Configuration.getModusTypeInterval() < 8) {
                            int modus = (int) (Math.pow(2.0d, rnds.get(0))) + (int) (Math.pow(2.0d, rnds.get(1)));
                            KongosDrinkMainModel.getInstance().setModus(modus);
                        } else {
                            int modus = (int) (Math.pow(2.0d, rnds.get(0))) + (int) (Math.pow(2.0d, rnds.get(1))) + (int) (Math.pow(2.0d, rnds.get(2)));
                            KongosDrinkMainModel.getInstance().setModus(modus);
                        }
                    }
                    for(Integer i: KongosDrinkMainModel.getInstance().getModusTypeTextMap().keySet()){
                        KongosDrinkMainModel.getInstance().getModusTypeTextMap().put(i,
                                CardTextParser.replacePlayerNames(KongosDrinkMainModel.getInstance().getModusTypeTextMap().get(i)));
                    }

                }
            }
        }).start();

    }


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        for (ModusSprite ms : getScreen().getModusSprite()) {
            if (ms.isActive()) {
                int x = CoorTransformator.getX(960, screenX);
                int y = CoorTransformator.getY(540, screenY);
                if (x >= ms.getX() &&
                        x <= ms.getX() + ms.getWidth() &&
                        y >= ms.getY() &&
                        y <= ms.getY() + ms.getHeight()) {
                    ms.setClicked(true);
                }
            }
        }

        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        for (ModusSprite ms : getScreen().getModusSprite()) {
            ms.setClicked(false);
        }

        if (!super.touchUp(screenX, screenY, pointer, button)) {
            KongosDrinkMainModel.getInstance().setXxcoor(0);
            KongosDrinkMainModel.getInstance().setXcoor(0);
            KongosDrinkMainModel.getInstance().setPlayerIndex(0);
            KongosDrinkMainModel.getInstance().setPosition(1);
            suspend = true;
            KongosDrinkMainModel.getInstance().setFinished(true);
            return false;
        }

        switch (KongosDrinkMainModel.getInstance().getPosition()) {
            case 1:
                gotoPlayer(0);
                break;
            case 11:
                movePlayer(0, 4);
                break;
            case 4:
                gotoPlayer(2);
                break;
            case 3:
                movePlayer(2, 23);
                break;
            case 23:
                gotoPlayer(1);
                break;
            case 14:
                movePlayer(1, 2);
                break;
        }
        return false;

    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {

        return false;
    }

    private void gotoPlayer(int playerIndex) {
        movePlayground(Configuration.getPlayers()[playerIndex].getPosition());
    }

    private void movePlayer(int playerIndex, int position) {
        Configuration.getPlayers()[playerIndex].setActive(true);
        movePlayground(position);
        //setActive(false) and position will be called in movePlayground additional thread!
    }


    private void movePlayground(final int position) {
        if (KongosDrinkMainModel.getInstance().getPosition() == position) {
            return;
        }


        suspend = false;

        if (!isRunning) {
            isRunning = true;
            forward = KongosDrinkMainModel.getInstance().getPosition() < position;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (!suspend) {
                        for (int i = 1; i < 9; i++) {
                            for (int j = 0; j < Configuration.getPlayers().length; j++)
                                if (Configuration.getPlayers()[j].isActive())
                                    if (forward)
                                        Configuration.getPlayers()[j].setRotate(-10.0f);
                                    else
                                        Configuration.getPlayers()[j].setRotate(10.0f);

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
                                KongosDrinkMainModel.getInstance().getXxcoor() == (position - 1) * Configuration.KongosDrink.DISTANCE_BETWEEN_TWO_FIELDS) {
                            isRunning = false;
                            break;
                        }

                        if (KongosDrinkMainModel.getInstance().getXxcoor() <= 0 && !forward) {
                            KongosDrinkMainModel.getInstance().setXxcoor(0);
                            KongosDrinkMainModel.getInstance().setXcoor(0);
                            KongosDrinkMainModel.getInstance().setIndex(0);
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
                    for (int i = 0; i < Configuration.getPlayers().length; i++)
                        if (Configuration.getPlayers()[i].isActive()) {
                            Configuration.getPlayers()[i].setRotate(0.0f);
                            Configuration.getPlayers()[i].setActive(false);
                            Configuration.getPlayers()[i].setPosition(position);
                        }

                }
            }).start();
        }

    }
}
