package com.gamefactoryx.cheers.controller;


import com.gamefactoryx.cheers.CheersGdxGame;
import com.gamefactoryx.cheers.model.BusDrivingPhase4Model;
import com.gamefactoryx.cheers.model.bus_driving.Croupier;
import com.gamefactoryx.cheers.model.bus_driving.VCard;
import com.gamefactoryx.cheers.tool.Resolution;
import com.gamefactoryx.cheers.view.AbstractScreen;

/**
 * Created by bernat on 16.05.2017.
 */
public class BusDrivingStagePhase4Controller extends AbstractController {

    private BusDrivingPhase4Model model;
    private static boolean flag;
    private StringBuilder typedName = new StringBuilder();
    private boolean shift;
    private boolean keyboardOn;
    private VCard activeCard;
    private String tempName;


    public BusDrivingStagePhase4Controller(final AbstractScreen screen) {
        super(screen);
        model = BusDrivingPhase4Model.getNewInstance();
        setScreenLock();
    }


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
/*        if (model.isPhaseFinished())
            return true;

        for (int i = 0; i < getScreen().getCountOfButtons(); i++) {
            getScreen().getClicked()[i] = (screenX >= getScreen().getButtons()[i][0].getX() &&
                    screenX <= getScreen().getButtons()[i][0].getX() + getScreen().getButtons()[i][0].getWidth() &&
                    Resolution.getGameWorldHeightPortrait() - screenY >= getScreen().getButtons()[i][0].getY() &&
                    Resolution.getGameWorldHeightPortrait() - screenY <= getScreen().getButtons()[i][0].getY() + getScreen().getButtons()[i][0].getHeight());
        }*/
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        /*if (!model.isPhaseFinished()) {
            for (int i = 0; i < getScreen().getCountOfButtons(); i++) {
                if (getScreen().getClicked()[i]) {
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
                                //StageManager.getInstance().showStage(StageEnum.BUS_DRIVING_STAGE_FIRST_PHASE);
                            }
                            break;
                        case 1:
                            if (lastCard > model.getBoard().getVCards().last().getValue())
                                model.nextPlayer();
                            else {
                                model.setPhaseFinished(model.getActivePlayer());
                                model.setFinalMessage();
                            }
                            //StageManager.getInstance().showStage(StageEnum.BUS_DRIVING_STAGE_FIRST_PHASE);

                            break;
                    }

                }

                getScreen().getClicked()[i] = false;
            }
        } else {
            if (screenX >= getScreen().getTextBox().getX() &&
                    screenX <= getScreen().getTextBox().getX() + getScreen().getTextBox().getWidth() &&
                    Resolution.getGameWorldHeightPortrait() - screenY >= getScreen().getTextBox().getY() &&
                    Resolution.getGameWorldHeightPortrait() - screenY <= getScreen().getTextBox().getY() + getScreen().getTextBox().getHeight()) {
                StageManager.getInstance().showStage(StageEnum.BUS_DRIVING_STAGE_FIRST_PHASE);
            }
        }*/

        return true;
    }


    private void setScreenLock() {
        if (CheersGdxGame.getScreenLock() != null)
            CheersGdxGame.getScreenLock().lock(0);
    }


}
