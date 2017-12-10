package com.gamefactoryx.cheersapp.controller.busdriving;


import com.badlogic.gdx.Gdx;
import com.gamefactoryx.cheersapp.controller.StageManager;

/**
 * Created by bernat on 16.05.2017.
 */
public class BusDrivingStagePhase2Controller extends com.gamefactoryx.cheersapp.controller.AbstractController {

    private com.gamefactoryx.cheersapp.model.busdriving.BusDrivingPhase2Model model;
    private com.gamefactoryx.cheersapp.model.busdriving.VCard activeCard;


    public BusDrivingStagePhase2Controller(final com.gamefactoryx.cheersapp.view.AbstractScreen screen) {
        super(screen);
        model = com.gamefactoryx.cheersapp.model.busdriving.BusDrivingPhase2Model.getNewInstance();
        model.getBoard().getVCards().clear();
        for (int i = 0; i < 15; i++) {
            com.gamefactoryx.cheersapp.model.busdriving.VCard vCard = com.gamefactoryx.cheersapp.model.busdriving.Croupier.getInstance().getVCard();
            vCard.setOrientation(com.gamefactoryx.cheersapp.tool.CardOrientation.BACK);
            model.getBoard().getVCards().addLast(vCard);
        }

        for (com.gamefactoryx.cheersapp.model.busdriving.Player player : model.getPlayers())
            for (com.gamefactoryx.cheersapp.model.busdriving.VCard vCard : player.getVCards())
                vCard.setOrientation(com.gamefactoryx.cheersapp.tool.CardOrientation.FACE);
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
            for (com.gamefactoryx.cheersapp.model.busdriving.VCard vCard : model.getBoard().getVCards()) {
                ++vCard_index;
                if (vCard_index == 9)
                    model.setScrolled(true);

                if (vCard.getOrientation() == com.gamefactoryx.cheersapp.tool.CardOrientation.BACK) {
                    vCard.setOrientation(com.gamefactoryx.cheersapp.tool.CardOrientation.FACE);
                    activeCard = vCard;
                    for (com.gamefactoryx.cheersapp.model.busdriving.Player player : model.getPlayers()) {
                        if (player.isEnable())
                            for (com.gamefactoryx.cheersapp.model.busdriving.VCard vCardPlayer : player.getVCards()) {
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
                for (com.gamefactoryx.cheersapp.model.busdriving.Player player : model.getPlayers()) {
                    if (player.isEnable())
                        for (com.gamefactoryx.cheersapp.model.busdriving.VCard playerVCard : player.getVCards()) {
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
                float y = StageManager.getInstance().getGame().isAdMobVisible() ?
                        getScreen().getButtons()[0][0].getY() + StageManager.getInstance().getGame().getAdMobHeight() :
                        getScreen().getButtons()[0][0].getY();
                if(screenX >= getScreen().getButtons()[0][0].getX() &&
                        screenX <= getScreen().getButtons()[0][0].getX() + getScreen().getButtons()[0][0].getWidth()&&
                        com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightPortrait() - screenY >= y &&
                        com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightPortrait() - screenY <= y + getScreen().getButtons()[0][0].getHeight()) {
                    Gdx.input.vibrate(10);
                    if (isThereMoreThenOneLooser())
                        com.gamefactoryx.cheersapp.controller.StageManager.getInstance().showStage(com.gamefactoryx.cheersapp.controller.StageEnum.BUS_DRIVING_STAGE_THIRD_PHASE);
                    else
                        com.gamefactoryx.cheersapp.controller.StageManager.getInstance().showStage(com.gamefactoryx.cheersapp.controller.StageEnum.BUS_DRIVING_STAGE_FOURTH_PHASE);
                }
            }
        }
        return true;
    }

    private boolean isThereMoreThenOneLooser() {
        int counter = 0;
        for (com.gamefactoryx.cheersapp.model.busdriving.Player player : model.getPlayers()) {
            if (player.isAlive() && player.isEnable()) ++counter;
            if (counter > 1) break;

        }

        return counter > 1;
    }

}
