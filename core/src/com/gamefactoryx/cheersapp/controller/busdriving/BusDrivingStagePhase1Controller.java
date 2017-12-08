package com.gamefactoryx.cheersapp.controller.busdriving;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.gamefactoryx.cheersapp.controller.StageManager;

/**
 * Created by bernat on 16.05.2017.
 */
public class BusDrivingStagePhase1Controller extends com.gamefactoryx.cheersapp.controller.AbstractController {

    private com.gamefactoryx.cheersapp.model.busdriving.BusDrivingPhase1Model model;
    private StringBuilder typedName = new StringBuilder();
    private boolean shift;
    private boolean keyboardOn;
    private String tempName;


    public BusDrivingStagePhase1Controller(final com.gamefactoryx.cheersapp.view.AbstractScreen screen) {
        super(screen);
        model = com.gamefactoryx.cheersapp.model.busdriving.BusDrivingPhase1Model.getNewInstance();
        putNewCardToBoard(com.gamefactoryx.cheersapp.tool.CardOrientation.BACK);
        setScreenLock(1);
        StageManager.getInstance().getGame().getInterstitialResolver().showOrLoadInterstitial();
    }

    private void putNewCardToBoard(com.gamefactoryx.cheersapp.tool.CardOrientation cardOrientation) {
        com.gamefactoryx.cheersapp.model.busdriving.VCard vCard = com.gamefactoryx.cheersapp.model.busdriving.Croupier.getInstance().getVCards().removeLast();
        vCard.setOrientation(cardOrientation);
        model.getBoard().addCard(vCard);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if(!super.touchUp(screenX, screenY, pointer, button))
            return true;

        if (model.isPhaseFinished()) {
            if(screenX >= getScreen().getButtons()[0][0].getX() &&
                    screenX <= getScreen().getButtons()[0][0].getX() + getScreen().getButtons()[0][0].getWidth()&&
                    com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightPortrait() - screenY >= getScreen().getButtons()[0][0].getY() &&
                    com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightPortrait() - screenY <= getScreen().getButtons()[0][0].getY() + getScreen().getButtons()[0][0].getHeight()) {
                Gdx.input.vibrate(10);
                com.gamefactoryx.cheersapp.controller.StageManager.getInstance().showStage(com.gamefactoryx.cheersapp.controller.StageEnum.BUS_DRIVING_STAGE_SECOND_PHASE);
                StageManager.getInstance().getGame().getInterstitialResolver().showOrLoadInterstitial();
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
                    putNewCardToBoard(com.gamefactoryx.cheersapp.tool.CardOrientation.BACK);
                    model.setActivePlayer(model.getActivePlayer() + 1);
                }
            }
        }
        return true;
    }


    private void boardCardToPlayer() {
        com.gamefactoryx.cheersapp.model.busdriving.VCard vCard = model.getBoard().getVCards().removeLast();
        vCard.setOrientation(com.gamefactoryx.cheersapp.tool.CardOrientation.FACE);
        model.getPlayers().get(model.getActivePlayer()).addVCard(vCard);
    }

    private void boardCardToFace() {
        model.getBoard().getVCards().last().setOrientation(com.gamefactoryx.cheersapp.tool.CardOrientation.FACE);
    }

    private boolean isBoardCardBack() {
        return model.getBoard().getVCards().size != 0 && model.getBoard().getVCards().last().getOrientation() == com.gamefactoryx.cheersapp.tool.CardOrientation.BACK;
    }

    private boolean isBoardCardFace() {
        return model.getBoard().getVCards().size != 0 && model.getBoard().getVCards().last().getOrientation() == com.gamefactoryx.cheersapp.tool.CardOrientation.FACE;
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
                com.gamefactoryx.cheersapp.controller.StageManager.getInstance().showLastStage();
                return true;
            default:
                if (keycode >= Input.Keys.A && keycode <= Input.Keys.Z && typedName.length() < 8) {

                    typedName.append(shift ? Input.Keys.toString(keycode).toUpperCase() : Input.Keys.toString(keycode).toLowerCase());
                    model.getPlayers().get(model.getActivePlayer()).setName(typedName.toString());
                    com.gamefactoryx.cheersapp.model.PlayerNameCache.addName(typedName.toString(), model.getPlayers().get(model.getActivePlayer()).getPosition());
                }
                break;
        }
        return true;
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
