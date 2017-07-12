package com.gamefactoryx.cheers.controller.busdriving;


import com.badlogic.gdx.Gdx;
import com.gamefactoryx.cheers.controller.AbstractController;
import com.gamefactoryx.cheers.controller.StageEnum;
import com.gamefactoryx.cheers.controller.StageManager;
import com.gamefactoryx.cheers.model.busdriving.BusDrivingPhase2Model;
import com.gamefactoryx.cheers.model.busdriving.Croupier;
import com.gamefactoryx.cheers.model.busdriving.Player;
import com.gamefactoryx.cheers.model.busdriving.VCard;
import com.gamefactoryx.cheers.tool.CardOrientation;
import com.gamefactoryx.cheers.tool.Resolution;
import com.gamefactoryx.cheers.view.AbstractScreen;

/**
 * Created by bernat on 16.05.2017.
 */
public class BusDrivingStagePhase2Controller extends AbstractController {

    private BusDrivingPhase2Model model;
    private VCard activeCard;


    public BusDrivingStagePhase2Controller(final AbstractScreen screen) {
        super(screen);
        model = BusDrivingPhase2Model.getNewInstance();
        model.getBoard().getVCards().clear();
        for (int i = 0; i < 15; i++) {
            VCard vCard = Croupier.getInstance().getVCard();
            vCard.setOrientation(CardOrientation.BACK);
            model.getBoard().getVCards().addLast(vCard);
        }

        for (Player player : model.getPlayers())
            for (VCard vCard : player.getVCards())
                vCard.setOrientation(CardOrientation.FACE);
        setScreenLock(1);
    }


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        if(!super.touchUp(screenX, screenY, pointer, button))
            return true;

        int vCard_index = -1;

        if (!model.canAnyPlayerDropCard(activeCard) &&
                !model.isPhaseFinished()) {
            Gdx.input.vibrate(10);
            for (VCard vCard : model.getBoard().getVCards()) {
                ++vCard_index;
                if (vCard_index == 9)
                    model.setScrolled(true);

                if (vCard.getOrientation() == CardOrientation.BACK) {
                    vCard.setOrientation(CardOrientation.FACE);
                    activeCard = vCard;
                    for (Player player : model.getPlayers()) {
                        if (player.isActive())
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
            if (!model.isPhaseFinished()) {
                outer:
                for (Player player : model.getPlayers()) {
                    if (player.isActive())
                        for (VCard playerVCard : player.getVCards()) {
                            if (activeCard.equals(playerVCard)) {
                                Gdx.input.vibrate(10);
                                player.removeVCard(playerVCard);
                                model.checkAndSetPhaseFinished(activeCard);
                                break outer;
                            }
                        }
                }
            } else {
                //todo next phase
                if(screenX >= getScreen().getButtons()[0][0].getX() &&
                        screenX <= getScreen().getButtons()[0][0].getX() + getScreen().getButtons()[0][0].getWidth()&&
                        Resolution.getGameWorldHeightPortrait() - screenY >= getScreen().getButtons()[0][0].getY() &&
                        Resolution.getGameWorldHeightPortrait() - screenY <= getScreen().getButtons()[0][0].getY() + getScreen().getButtons()[0][0].getHeight()) {
                    Gdx.input.vibrate(10);
                    if (isThereMoreThenOneLooser())
                        StageManager.getInstance().showStage(StageEnum.BUS_DRIVING_STAGE_THIRD_PHASE);
                    else
                        StageManager.getInstance().showStage(StageEnum.BUS_DRIVING_STAGE_FOURTH_PHASE);
                }
            }
        }
        return true;
    }

    private boolean isThereMoreThenOneLooser() {
        int counter = 0;
        for (Player player : model.getPlayers()) {
            if (player.isAlive() && player.isActive()) ++counter;
            if (counter > 1) break;

        }

        return counter > 1;
    }

}