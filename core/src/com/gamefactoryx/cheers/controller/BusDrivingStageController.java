package com.gamefactoryx.cheers.controller;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.gamefactoryx.cheers.CheersGdxGame;
import com.gamefactoryx.cheers.model.BusDrivingModel;
import com.gamefactoryx.cheers.model.PlayerNameCache;
import com.gamefactoryx.cheers.model.bus_driving.Card;
import com.gamefactoryx.cheers.model.bus_driving.Player;
import com.gamefactoryx.cheers.model.bus_driving.VCard;
import com.gamefactoryx.cheers.tool.Resolution;
import com.gamefactoryx.cheers.view.AbstractScreen;

/**
 * Created by bernat on 16.05.2017.
 */
public class BusDrivingStageController extends AbstractController {

    private BusDrivingModel model;
    private static boolean flag;
    private StringBuilder typedName = new StringBuilder();
    private boolean shift;
    private boolean keyboardOn;
    private static Card activeCard;
    private String tempName;

    public BusDrivingStageController(final AbstractScreen screen) {
        super(screen);
        model = BusDrivingModel.getInstance();
        setScreenLockForPhase(model.getPhase().getName());
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        switch (model.getPhase().getName()) {
            case "PHASE_1":
                touchUp1Phase(screenX, screenY, pointer, button);
                break;
            case "PHASE_2":
                touchUp2Phase(screenX, screenY, pointer, button);
                break;
        }

        return true;
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
                    model.getPlayer().setName(typedName.toString());
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
                    model.getPlayer().setName(typedName.toString());
                    PlayerNameCache.addName(typedName.toString(), model.getPlayer().getPosition());
                }
                break;
        }
        return false;
    }


    private void enableKeyboard(boolean enabled) {
        keyboardOn = enabled;
        Gdx.input.setOnscreenKeyboardVisible(enabled);
    }


    private void setScreenLockForPhase(String phaseName) {
        switch (phaseName) {
            case "PHASE_1":
                if (CheersGdxGame.getScreenLock() != null)
                    CheersGdxGame.getScreenLock().lock(1);
                break;
            case "PHASE_2":
                if (CheersGdxGame.getScreenLock() != null) {
                    CheersGdxGame.getScreenLock().lock(1);
                }
                break;
        }
    }

    private boolean touchUp1Phase(int screenX, int screenY, int pointer, int button) {
        if (screenX >= getScreen().getTextBox().getX() &&
                screenX <= getScreen().getTextBox().getX() + getScreen().getTextBox().getWidth() &&
                Resolution.getGameWorldHeightPortrait() - screenY >= getScreen().getTextBox().getY() &&
                Resolution.getGameWorldHeightPortrait() - screenY <= getScreen().getTextBox().getY() + getScreen().getTextBox().getHeight()) {
            if (model.getMessage() != null && model.getMessage().length() > 0) {
                if (model.getPhase().isPhaseFinished()) {
                    model.nextPhase();
                    setScreenLockForPhase(model.getPhase().getName());
                    model.setMessage("");
                }
            } else {
                tempName = model.getPlayer().getName();
                model.getPlayer().setName("");
                enableKeyboard(true);
            }

        } else {
            if (model.getMessage() == null || model.getMessage().length() == 0) {
                //is keyboard on?
                if (keyboardOn) {
                    setPlayerName();
                    enableKeyboard(false);
                }
                //keyboard is off
                else {
                    //should you restart phase?
                    if (screenX < 100) {
                        model.reset();
                        setScreenLockForPhase(model.getPhase().getName());
                        flag = false;
                        return true;
                    }

                    if (screenX >= getScreen().getFaceDownBigCard().getX() &&
                            screenX <= getScreen().getFaceDownBigCard().getX() + getScreen().getFaceDownBigCard().getWidth() &&
                            Resolution.getGameWorldHeightPortrait() - screenY >= getScreen().getFaceDownBigCard().getY() &&
                            Resolution.getGameWorldHeightPortrait() - screenY <= getScreen().getFaceDownBigCard().getY() + getScreen().getFaceDownBigCard().getHeight()) {
                        /*Gdx.app.log("BUG424", "*********DEBUGGING***************");
                        Gdx.app.log("screenX", ":" +screenX);
                        Gdx.app.log("screenY", ":" +screenY);
                        Gdx.app.log("getScreen().getFaceDownBigCard().getX()", ":" +getScreen().getFaceDownBigCard().getX());
                        Gdx.app.log("getScreen().getFaceDownBigCard().getY()", ":" +getScreen().getFaceDownBigCard().getY());
                        Gdx.app.log("getScreen().getFaceDownBigCard().getWidth()", ":" +getScreen().getFaceDownBigCard().getWidth());
                        Gdx.app.log("getScreen().getFaceDownBigCard().getHeight()", ":" +getScreen().getFaceDownBigCard().getHeight());
                        Gdx.app.log("Resolution.getGameWorldHeightPortrait()", ":" +Resolution.getGameWorldHeightPortrait());*/
                        model.getPhase().nextTurn();
                    } else {
                        /*Gdx.app.log("screenX", ":" +screenX);
                        Gdx.app.log("screenY", ":" +screenY);
                        Gdx.app.log("getScreen().getFaceDownBigCard().getX()", ":" +getScreen().getFaceDownBigCard().getX());
                        Gdx.app.log("getScreen().getFaceDownBigCard().getY()", ":" +getScreen().getFaceDownBigCard().getY());
                        Gdx.app.log("getScreen().getFaceDownBigCard().getWidth()", ":" +getScreen().getFaceDownBigCard().getWidth());
                        Gdx.app.log("getScreen().getFaceDownBigCard().getHeight()", ":" +getScreen().getFaceDownBigCard().getHeight());
                        Gdx.app.log("Resolution.getGameWorldHeightPortrait()", ":" +Resolution.getGameWorldHeightPortrait());*/

                    }
                }
            }
        }
        return true;
    }

    private boolean touchUp2Phase(int screenX, int screenY, int pointer, int button) {
        //turn the card
        int vCard_index = -1;

        if (screenY >= Resolution.getGameWorldHeightPortrait() / 2.0f) {
            // if (model.getMessage() == null || model.getMessage().length() == 0) {
            for (VCard vCard : model.getPhase().getBoard().getVCards()) {
                ++vCard_index;
                if (vCard_index == 9)
                    model.setScrollPyramide(true);
                if (vCard.getOrientation() == VCard.CardOrientation.BACK) {
                    vCard.setOrientation(VCard.CardOrientation.FACE);
                    model.getPhase().getBoard().setAllCardsOnBoardFace(vCard_index == 14);
                    activeCard = new Card(vCard.getCardIndex(), Card.CardSize.SMALL);
                    model.firstPlayer();
                    do {
                        Player player = model.getPlayer();
                        for (VCard playerVCard : player.getVCards()) {
                            Card playerCard = new Card(playerVCard.getCardIndex(), Card.CardSize.SMALL);
                            if (activeCard.equals(playerCard)) {
                                if (vCard_index < 5)
                                    playerVCard.setCredit(1);
                                else if (vCard_index < 9)
                                    playerVCard.setCredit(2);
                                else if (vCard_index < 12)
                                    playerVCard.setCredit(3);
                                else if (vCard_index < 14)
                                    playerVCard.setCredit(4);

                                else if (vCard_index == 14)
                                    playerVCard.setCredit(5);
                            } else {
                                playerVCard.setCredit(0);
                            }
                        }
                    } while (model.nextPlayer());
                    break;
                }

            }

        } else {
            //yes I will do something
            if (screenX >= getScreen().getTextBox().getX() &&
                    screenX <= getScreen().getTextBox().getX() + getScreen().getTextBox().getWidth() &&
                    Resolution.getGameWorldHeightPortrait() - screenY >= getScreen().getTextBox().getY() &&
                    Resolution.getGameWorldHeightPortrait() - screenY <= getScreen().getTextBox().getY() + getScreen().getTextBox().getHeight()) {
                if (model.getMessage() == null || model.getMessage().length() == 0) {
                    if (activeCard != null) {
                        model.firstPlayer();
                        outer:
                        do {
                            Player player = model.getPlayer();
                            //todo continue
                            for (VCard playerVCard : player.getVCards()) {
                                Card playerCard = new Card(playerVCard.getCardIndex(), Card.CardSize.SMALL);
                                //todo comparable
                                if (activeCard.getValue() == playerCard.getValue()) {
                                    player.removeVCard(playerVCard);
                                    break outer;
                                }
                            }
                        }
                        while (model.nextPlayer());
                    }
                } else {
                    //todo next phase
                    model.reset();
                    setScreenLockForPhase(model.getPhase().getName());
                    flag = false;
                    activeCard = null;
                }
               /* Gdx.app.log("BUG424", "*********DEBUGGING***************");
                Gdx.app.log("screenX", ":" + screenX);
                Gdx.app.log("screenY", ":" + screenY);
                Gdx.app.log("getScreen().getTextBox().getX()", ":" + getScreen().getTextBox().getX());
                Gdx.app.log("getScreen().getTextBox().getY()", ":" + getScreen().getTextBox().getY());
                Gdx.app.log("getScreen().getTextBox().getWidth()", ":" + getScreen().getTextBox().getWidth());
                Gdx.app.log("getScreen().getTextBox().getHeight()", ":" + getScreen().getTextBox().getHeight());
                Gdx.app.log("Resolution.getGameWorldHeightPortrait()", ":" + Resolution.getGameWorldHeightPortrait());*/
            }else {
                /*Gdx.app.log("BUG524", "*********DEBUGGING***************");
                Gdx.app.log("screenX", ":" + screenX);
                Gdx.app.log("screenY", ":" + screenY);
                Gdx.app.log("getScreen().getTextBox().getX()", ":" + getScreen().getTextBox().getX());
                Gdx.app.log("getScreen().getTextBox().getY()", ":" + getScreen().getTextBox().getY());
                Gdx.app.log("getScreen().getTextBox().getWidth()", ":" + getScreen().getTextBox().getWidth());
                Gdx.app.log("getScreen().getTextBox().getHeight()", ":" + getScreen().getTextBox().getHeight());
                Gdx.app.log("Resolution.getGameWorldHeightPortrait()", ":" + Resolution.getGameWorldHeightPortrait());*/

            }
        }

        return true;
    }

    private boolean touchUp3Phase(int screenX, int screenY, int pointer, int button) {
        return true;
    }

    private boolean touchUp4Phase(int screenX, int screenY, int pointer, int button) {
        return true;
    }

    private void setPlayerName() {
        if (typedName.toString().trim().length() > 0)
            model.getPlayer().setName(typedName.toString());
        else
            model.getPlayer().setName(tempName);
        typedName.setLength(0);
    }
}
