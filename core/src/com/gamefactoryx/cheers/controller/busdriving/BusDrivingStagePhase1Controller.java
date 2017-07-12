package com.gamefactoryx.cheers.controller.busdriving;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.gamefactoryx.cheers.controller.AbstractController;
import com.gamefactoryx.cheers.controller.StageEnum;
import com.gamefactoryx.cheers.controller.StageManager;
import com.gamefactoryx.cheers.model.busdriving.BusDrivingPhase1Model;
import com.gamefactoryx.cheers.model.PlayerNameCache;
import com.gamefactoryx.cheers.model.busdriving.Croupier;
import com.gamefactoryx.cheers.model.busdriving.VCard;
import com.gamefactoryx.cheers.tool.CardOrientation;
import com.gamefactoryx.cheers.tool.Resolution;
import com.gamefactoryx.cheers.view.AbstractScreen;

/**
 * Created by bernat on 16.05.2017.
 */
public class BusDrivingStagePhase1Controller extends AbstractController {

    private BusDrivingPhase1Model model;
    private StringBuilder typedName = new StringBuilder();
    private boolean shift;
    private boolean keyboardOn;
    private String tempName;


    public BusDrivingStagePhase1Controller(final AbstractScreen screen) {
        super(screen);
        model = BusDrivingPhase1Model.getNewInstance();
        putNewCardToBoard(CardOrientation.BACK);
        setScreenLock(1);
    }

    private void putNewCardToBoard(CardOrientation cardOrientation) {
        VCard vCard = Croupier.getInstance().getVCards().removeLast();
        vCard.setOrientation(cardOrientation);
        model.getBoard().addCard(vCard);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        super.touchDown(screenX, screenY, pointer, button);
        return !model.isPhaseFinished();
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if(!super.touchUp(screenX, screenY, pointer, button))
            return true;

        if (model.isPhaseFinished()) {
            if(screenX >= getScreen().getButtons()[0][0].getX() &&
                    screenX <= getScreen().getButtons()[0][0].getX() + getScreen().getButtons()[0][0].getWidth()&&
                    Resolution.getGameWorldHeightPortrait() - screenY >= getScreen().getButtons()[0][0].getY() &&
                    Resolution.getGameWorldHeightPortrait() - screenY <= getScreen().getButtons()[0][0].getY() + getScreen().getButtons()[0][0].getHeight()) {
                Gdx.input.vibrate(10);
                StageManager.getInstance().showStage(StageEnum.BUS_DRIVING_STAGE_SECOND_PHASE);
                return true;
            }
        } else {
            Gdx.input.vibrate(10);
            if (isBoardCardBack()) {
                boardCardToFace();
            } else if (isBoardCardFace()) {
                boardCardToPlayer();
            } else {
                if (!model.isPhaseFinished()) {
                    putNewCardToBoard(CardOrientation.BACK);
                    model.setActivePlayer(model.getActivePlayer() + 1);
                }
            }
        }
        return true;
    }


    private void boardCardToPlayer() {
        VCard vCard = model.getBoard().getVCards().removeLast();
        vCard.setOrientation(CardOrientation.FACE);
        model.getPlayers().get(model.getActivePlayer()).addVCard(vCard);
    }

    private void boardCardToFace() {
        model.getBoard().getVCards().last().setOrientation(CardOrientation.FACE);
    }

    private boolean isBoardCardBack() {
        return model.getBoard().getVCards().size != 0 && model.getBoard().getVCards().last().getOrientation() == CardOrientation.BACK;
    }

    private boolean isBoardCardFace() {
        return model.getBoard().getVCards().size != 0 && model.getBoard().getVCards().last().getOrientation() == CardOrientation.FACE;
    }

    @Override
    public boolean keyDown(int keycode) {
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.ENTER:
                setPlayerName();
                enableKeyboard(false);
                break;
            case Input.Keys.BACKSPACE:
                if (typedName.length() > 0) {
                    typedName.setLength(typedName.length() - 1);
                    model.getPlayers().get(model.getActivePlayer()).setName(typedName.toString());
                }
                break;
            case Input.Keys.SHIFT_LEFT:
            case Input.Keys.SHIFT_RIGHT:
                shift = !shift;
                break;
            case Input.Keys.BACK:
                StageManager.getInstance().showLastStage();
                return false;
            default:
                if (keycode >= Input.Keys.A && keycode <= Input.Keys.Z && typedName.length() < 8) {

                    typedName.append(shift ? Input.Keys.toString(keycode).toUpperCase() : Input.Keys.toString(keycode).toLowerCase());
                    model.getPlayers().get(model.getActivePlayer()).setName(typedName.toString());
                    PlayerNameCache.addName(typedName.toString(), model.getPlayers().get(model.getActivePlayer()).getPosition());
                }
                break;
        }
        return false;
    }


    private void enableKeyboard(boolean enabled) {
        keyboardOn = enabled;
        Gdx.input.setOnscreenKeyboardVisible(enabled);
    }


    private void setPlayerName() {
        if (typedName.toString().trim().length() > 0)
            model.getPlayers().get(model.getActivePlayer()).setName(typedName.toString());
        else
            model.getPlayers().get(model.getActivePlayer()).setName(tempName);
        typedName.setLength(0);
    }

}
