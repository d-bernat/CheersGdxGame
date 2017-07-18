package com.gamefactoryx.cheers.controller.kongosdrink;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
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
        KongosDrinkMainModel.getInstance().setPlayers(new PlayerModel[]{new PlayerModel(), new PlayerModel(), new PlayerModel()});
        KongosDrinkMainModel.getInstance().setPosition(1);
        KongosDrinkMainModel.getInstance().getPlayers()[0].setPosition(11);
        KongosDrinkMainModel.getInstance().getPlayers()[0].setAvatar(new Texture(Gdx.files.internal("common/kongos_drink/player/canada/canada_1.png")));
        KongosDrinkMainModel.getInstance().getPlayers()[1].setPosition(15);
        KongosDrinkMainModel.getInstance().getPlayers()[1].setAvatar(new Texture(Gdx.files.internal("common/kongos_drink/player/slovakia/slovakia_1.png")));
        KongosDrinkMainModel.getInstance().getPlayers()[2].setPosition(3);
        KongosDrinkMainModel.getInstance().getPlayers()[2].setAvatar(new Texture(Gdx.files.internal("common/kongos_drink/player/austria/austria_1.png")));
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
                gotoPlayer(0);
                break;
            case 11:
                movePlayer(0, 4);
                break;
            case 4:
                gotoPlayer(2);
                break;
            case 3:
                movePlayer(2, 10);
                break;
            case 10:
                gotoPlayer(1);
                break;
            case 15:
                movePlayer(1, 2);
                break;
        }
        return false;

    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {

        return false;
    }

    private void gotoPlayer(int playerIndex){
        movePlayground(KongosDrinkMainModel.getInstance().getPlayers()[playerIndex].getPosition());
    }

    private void movePlayer(int playerIndex, int position){
        KongosDrinkMainModel.getInstance().getPlayers()[playerIndex].setActive(true);
        movePlayground(position);
        //setActive(false) and position will be called in movePlayground additional thread!
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
                                KongosDrinkMainModel.getInstance().getXxcoor() == (position - 1) * Configuration.KongosDrink.DISTANCE_BETWEEN_TWO_FIELDS) {
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
                        if(KongosDrinkMainModel.getInstance().getPlayers()[i].isActive()) {
                            KongosDrinkMainModel.getInstance().getPlayers()[i].setRotate(0.0f);
                            KongosDrinkMainModel.getInstance().getPlayers()[i].setActive(false);
                            KongosDrinkMainModel.getInstance().getPlayers()[i].setPosition(position);
                        }

                }
            }).start();
        }

    }
}
