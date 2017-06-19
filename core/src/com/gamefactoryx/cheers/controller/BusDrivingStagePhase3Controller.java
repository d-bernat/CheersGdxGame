package com.gamefactoryx.cheers.controller;


import com.badlogic.gdx.Gdx;
import com.gamefactoryx.cheers.CheersGdxGame;
import com.gamefactoryx.cheers.model.BusDrivingPhase3Model;
import com.gamefactoryx.cheers.model.bus_driving.Croupier;
import com.gamefactoryx.cheers.model.bus_driving.VCard;
import com.gamefactoryx.cheers.tool.Resolution;
import com.gamefactoryx.cheers.view.AbstractScreen;

/**
 * Created by bernat on 16.05.2017.
 */
public class BusDrivingStagePhase3Controller extends AbstractController {

    private BusDrivingPhase3Model model;

    public BusDrivingStagePhase3Controller(final AbstractScreen screen) {
        super(screen);
        model = BusDrivingPhase3Model.getNewInstance();
        setScreenLock(1);
    }


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (model.isPhaseFinished())
            return true;

        for (int i = 0; i < getScreen().getCountOfButtons(); i++) {
            getScreen().getClicked()[i] = (screenX >= getScreen().getButtons()[i][0].getX() &&
                    screenX <= getScreen().getButtons()[i][0].getX() + getScreen().getButtons()[i][0].getWidth() &&
                    Resolution.getGameWorldHeightPortrait() - screenY >= getScreen().getButtons()[i][0].getY() &&
                    Resolution.getGameWorldHeightPortrait() - screenY <= getScreen().getButtons()[i][0].getY() + getScreen().getButtons()[i][0].getHeight());
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (!model.isPhaseFinished()) {
            for (int i = 0; i < getScreen().getCountOfButtons(); i++) {
                if (getScreen().getClicked()[i]) {
                    Gdx.input.vibrate(10);
                    int lastCard = model.getBoard().getVCards().last().getValue();
                    VCard vCard = Croupier.getInstance().getVCards().removeLast();
                    model.getBoard().addCard(vCard);
                    switch (i) {
                        case 0:
                            if (lastCard < model.getBoard().getVCards().last().getValue())
                                model.nextPlayer();
                            else {
                                model.setPhaseFinished(model.getActivePlayer());
                                model.setFinalMessage();
                            }
                            break;
                        case 1:
                            if (lastCard > model.getBoard().getVCards().last().getValue())
                                model.nextPlayer();
                            else {
                                model.setPhaseFinished(model.getActivePlayer());
                                model.setFinalMessage();
                            }
                            break;
                    }

                }

                getScreen().getClicked()[i] = false;
            }
        } else {
            Gdx.input.vibrate(10);
            StageManager.getInstance().showStage(StageEnum.BUS_DRIVING_STAGE_FOURTH_PHASE);
        }

        return true;
    }

}
