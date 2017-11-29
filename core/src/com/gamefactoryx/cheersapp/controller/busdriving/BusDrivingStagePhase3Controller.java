package com.gamefactoryx.cheersapp.controller.busdriving;


import com.badlogic.gdx.Gdx;
import com.gamefactoryx.cheersapp.controller.AbstractController;
import com.gamefactoryx.cheersapp.controller.StageManager;
import com.gamefactoryx.cheersapp.view.AbstractScreen;

/**
 * Created by bernat on 16.05.2017.
 */
public class BusDrivingStagePhase3Controller extends AbstractController {

    private com.gamefactoryx.cheersapp.model.busdriving.BusDrivingPhase3Model model;

    public BusDrivingStagePhase3Controller(final AbstractScreen screen) {
        super(screen);
        model = com.gamefactoryx.cheersapp.model.busdriving.BusDrivingPhase3Model.getNewInstance();
        setScreenLock(1);
    }


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        super.touchDown(screenX, screenY, pointer, button);

        if (model.isPhaseFinished())
            return true;

        for (int i = 0; i < getScreen().getCountOfButtons(); i++) {
            getScreen().getClicked()[i] = (screenX >= getScreen().getButtons()[i][0].getX() &&
                    screenX <= getScreen().getButtons()[i][0].getX() + getScreen().getButtons()[i][0].getWidth() &&
                    com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightPortrait() - screenY >= getScreen().getButtons()[i][0].getY() &&
                    com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightPortrait() - screenY <= getScreen().getButtons()[i][0].getY() + getScreen().getButtons()[i][0].getHeight());
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
                    int lastCard = model.getBoard().getVCards().last().getValue();
                    com.gamefactoryx.cheersapp.model.busdriving.VCard vCard = com.gamefactoryx.cheersapp.model.busdriving.Croupier.getInstance().getVCards().removeLast();
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
            if (screenX >= getScreen().getButtons()[2][0].getX() &&
                    screenX <= getScreen().getButtons()[2][0].getX() + getScreen().getButtons()[2][0].getWidth() &&
                    com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightPortrait() - screenY >= getScreen().getButtons()[2][0].getY() &&
                    com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightPortrait() - screenY <= getScreen().getButtons()[2][0].getY() + getScreen().getButtons()[2][0].getHeight()) {
                Gdx.input.vibrate(10);
                StageManager.getInstance().showStage(com.gamefactoryx.cheersapp.controller.StageEnum.BUS_DRIVING_STAGE_FOURTH_PHASE);
            }
        }

        return true;
    }

}
