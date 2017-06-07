package com.gamefactoryx.cheers.controller;


import com.gamefactoryx.cheers.CheersGdxGame;
import com.gamefactoryx.cheers.model.BusDrivingPhase2Model;
import com.gamefactoryx.cheers.model.bus_driving2.Croupier;
import com.gamefactoryx.cheers.model.bus_driving2.Player;
import com.gamefactoryx.cheers.model.bus_driving2.VCard;
import com.gamefactoryx.cheers.tool.CardOrientation;
import com.gamefactoryx.cheers.tool.Resolution;
import com.gamefactoryx.cheers.view.AbstractScreen;

import java.util.Collections;

/**
 * Created by bernat on 16.05.2017.
 */
public class BusDrivingStagePhase2Controller extends AbstractController {

    private BusDrivingPhase2Model model;
    private static boolean flag;
    private StringBuilder typedName = new StringBuilder();
    private boolean shift;
    private boolean keyboardOn;
    private VCard activeCard;
    private String tempName;


    public BusDrivingStagePhase2Controller(final AbstractScreen screen) {
        super(screen);
        model = BusDrivingPhase2Model.getNewInstance();
        model.getBoard().getVCards().clear();
        for(int i = 0; i < 15; i++){
            VCard vCard = Croupier.getInstance().getVCard();
            vCard.setOrientation(CardOrientation.BACK);
            model.getBoard().getVCards().addLast(vCard);
        }

        for(Player player: model.getPlayers())
            for(VCard vCard: player.getVCards())
                vCard.setOrientation(CardOrientation.FACE);
        setScreenLock();
    }


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        int vCard_index = -1;

        if (screenY >= Resolution.getGameWorldHeightPortrait() / 2.0f) {
            for (VCard vCard : model.getBoard().getVCards()) {
                ++vCard_index;
                if (vCard_index == 9)
                    model.setScrolled(true);

                if (vCard.getOrientation() == CardOrientation.BACK) {
                    vCard.setOrientation(CardOrientation.FACE);
                    activeCard = vCard;
                    for (Player player : model.getPlayers()) {
                        for (VCard vCardPlayer : player.getVCards()) {
                            if (vCard.equals(vCardPlayer)) {
                                if (vCard_index < 5)
                                    vCardPlayer.setCredit(1);
                                else if (vCard_index < 9)
                                    vCardPlayer.setCredit(2);
                                else if (vCard_index < 12)
                                    vCardPlayer.setCredit(3);
                                else if (vCard_index < 14)
                                    vCardPlayer.setCredit(4);

                                else if (vCard_index == 14)
                                    vCardPlayer.setCredit(5);
                            } else {
                                vCardPlayer.setCredit(0);
                            }
                        }
                    }
                    break;
                }
            }
            model.checkAndSetPhaseFinished(activeCard);

        } else {
            //yes I will do something
            if (screenX >= getScreen().getTextBox().getX() &&
                    screenX <= getScreen().getTextBox().getX() + getScreen().getTextBox().getWidth() &&
                    Resolution.getGameWorldHeightPortrait() - screenY >= getScreen().getTextBox().getY() &&
                    Resolution.getGameWorldHeightPortrait() - screenY <= getScreen().getTextBox().getY() + getScreen().getTextBox().getHeight()) {
                if (!model.isPhaseFinished()) {
                    if (activeCard != null) {
                        outer:
                        for (Player player : model.getPlayers()) {
                            for (VCard playerVCard : player.getVCards()) {
                                if (activeCard.equals(playerVCard)) {
                                    player.removeVCard(playerVCard);
                                    break outer;
                                }
                            }
                        }
                    }

                } else {
                    //todo next phase
                    if(isThereMoreThenOneLooser())
                        StageManager.getInstance().showStage(StageEnum.BUS_DRIVING_STAGE_THIRD_PHASE);
                    else
                        StageManager.getInstance().showStage(StageEnum.BUS_DRIVING_STAGE_FIRST_PHASE);
                }
            }
        }
        return true;
    }

    private boolean isThereMoreThenOneLooser() {
        int counter = 0;
        for(Player player: model.getPlayers()){
            if(player.isAlive()) ++counter;
            if(counter > 1) break;

        }

        return counter > 1;
    }


    private void setScreenLock() {
        if (CheersGdxGame.getScreenLock() != null)
            CheersGdxGame.getScreenLock().lock(1);
    }


}
