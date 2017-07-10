package com.gamefactoryx.cheers.controller;


import com.badlogic.gdx.Gdx;
import com.gamefactoryx.cheers.CheersGdxGame;
import com.gamefactoryx.cheers.model.BusDrivingPhase4Model;
import com.gamefactoryx.cheers.model.HallOfFameModel;
import com.gamefactoryx.cheers.model.bus_driving.Croupier;
import com.gamefactoryx.cheers.model.bus_driving.VCard;
import com.gamefactoryx.cheers.tool.Resolution;
import com.gamefactoryx.cheers.view.AbstractScreen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by bernat on 16.05.2017.
 */
public class BusDrivingStagePhase4Controller extends AbstractController {

    private BusDrivingPhase4Model model;

    public BusDrivingStagePhase4Controller(final AbstractScreen screen) {
        super(screen);
        model = BusDrivingPhase4Model.getNewInstance();
        setScreenLock(0);
    }


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (model.isPhaseFinished())
            return true;

        for (int i = 0; i < getScreen().getCountOfButtons(); i++) {
            getScreen().getClicked()[i] = (screenX >= getScreen().getButtons()[i][0].getX() &&
                    screenX <= getScreen().getButtons()[i][0].getX() + getScreen().getButtons()[i][0].getWidth() &&
                    Resolution.getGameWorldHeightLandscape() - screenY >= getScreen().getButtons()[i][0].getY() &&
                    Resolution.getGameWorldHeightLandscape() - screenY <= getScreen().getButtons()[i][0].getY() + getScreen().getButtons()[i][0].getHeight());
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (!model.isPhaseFinished()) {
            for (int i = 0; i < getScreen().getCountOfButtons(); i++) {
                if (getScreen().getClicked()[i]) {
                    Gdx.input.vibrate(10);
                    int oldValue = model.getBoard().getVCards().get(model.getActiveCardIndex()).getValue();
                    changeCardOnBoardAndGetOldToCroupier();
                    int newValue = model.getBoard().getVCards().get(model.getActiveCardIndex()).getValue();

                    if (i == 0 && oldValue < newValue || i == 1 && newValue < oldValue) {
                        model.setDrinkPoints(0);
                        Gdx.input.vibrate(100);
                        if (model.getActiveCardIndex() == 6) {
                            model.setPhaseFinished();
                            updateHallOfFame();
                        }
                        else
                            model.setActiveCardIndex(model.getActiveCardIndex() + 1);
                    } else if (oldValue == newValue) {
                        model.setDrinkPoints(model.getActiveCardIndex() + 1);
                    } else {
                        model.setDrinkPoints(model.getActiveCardIndex() + 1);
                        model.setActiveCardIndex(0);
                    }
                }
                getScreen().getClicked()[i] = false;
            }
        } else {
            StageManager.getInstance().showStage(StageEnum.MAIN_STAGE);
        }

        return true;
    }

    private void updateHallOfFame() {
        String name = BusDrivingPhase4Model.getInstance().getBusDriver().getName();
        int  drunk =  BusDrivingPhase4Model.getInstance().getTotalDrunk();
        HallOfFameModel.getInstance().put(drunk, name);

    }

    private void changeCardOnBoardAndGetOldToCroupier() {
        List<VCard> cards = new ArrayList<>();
        int index = 0;
        for (VCard vCard : model.getBoard().getVCards()) {
            if (model.getActiveCardIndex() == index++) {
                cards.add(Croupier.getInstance().getVCard());

            } else
                cards.add(vCard);

        }
        model.getBoard().getVCards().clear();
        for (VCard vCard : cards)
            model.getBoard().getVCards().addLast(vCard);
    }
}
