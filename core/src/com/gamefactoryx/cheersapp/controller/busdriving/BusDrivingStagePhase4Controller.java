package com.gamefactoryx.cheersapp.controller.busdriving;


import com.badlogic.gdx.Gdx;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bernat on 16.05.2017.
 */
public class BusDrivingStagePhase4Controller extends com.gamefactoryx.cheersapp.controller.AbstractController {

    private com.gamefactoryx.cheersapp.model.busdriving.BusDrivingPhase4Model model;

    public BusDrivingStagePhase4Controller(final com.gamefactoryx.cheersapp.view.AbstractScreen screen) {
        super(screen);
        model = com.gamefactoryx.cheersapp.model.busdriving.BusDrivingPhase4Model.getNewInstance();
        setScreenLock(0);
    }


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        super.touchDown(screenX, screenY, pointer, button);

        if (model.isPhaseFinished())
            return true;

        for (int i = 0; i < getScreen().getCountOfButtons(); i++) {
            getScreen().getClicked()[i] = (screenX >= getScreen().getButtons()[i][0].getX() &&
                    screenX <= getScreen().getButtons()[i][0].getX() + getScreen().getButtons()[i][0].getWidth() &&
                    com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightLandscape() - screenY >= getScreen().getButtons()[i][0].getY() &&
                    com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightLandscape() - screenY <= getScreen().getButtons()[i][0].getY() + getScreen().getButtons()[i][0].getHeight());
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if(!super.touchUp(screenX, screenY, pointer, button))
            return true;

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
            com.gamefactoryx.cheersapp.controller.StageManager.getInstance().showStage(com.gamefactoryx.cheersapp.controller.StageEnum.MAIN_STAGE);
        }

        return true;
    }

    private void updateHallOfFame() {
        String name = com.gamefactoryx.cheersapp.model.busdriving.BusDrivingPhase4Model.getInstance().getBusDriver().getName();
        int  drunk =  com.gamefactoryx.cheersapp.model.busdriving.BusDrivingPhase4Model.getInstance().getTotalDrunk();
        if(drunk > 0)
            com.gamefactoryx.cheersapp.model.HallOfFameModel.getInstance().put(drunk, name);

    }

    private void changeCardOnBoardAndGetOldToCroupier() {
        List<com.gamefactoryx.cheersapp.model.busdriving.VCard> cards = new ArrayList<>();
        int index = 0;
        for (com.gamefactoryx.cheersapp.model.busdriving.VCard vCard : model.getBoard().getVCards()) {
            if (model.getActiveCardIndex() == index++) {
                cards.add(com.gamefactoryx.cheersapp.model.busdriving.Croupier.getInstance().getVCard());

            } else
                cards.add(vCard);

        }
        model.getBoard().getVCards().clear();
        for (com.gamefactoryx.cheersapp.model.busdriving.VCard vCard : cards)
            model.getBoard().getVCards().addLast(vCard);
    }
}
