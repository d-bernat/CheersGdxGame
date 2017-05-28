package com.gamefactoryx.cheers.controller;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.gamefactoryx.cheers.CheersGdxGame;
import com.gamefactoryx.cheers.controller.AbstractController;
import com.gamefactoryx.cheers.controller.StageManager;
import com.gamefactoryx.cheers.model.BusDrivingModel;
import com.gamefactoryx.cheers.model.PlayerNameCache;
import com.gamefactoryx.cheers.model.bus_driving.Card;
import com.gamefactoryx.cheers.model.bus_driving.Player;
import com.gamefactoryx.cheers.model.bus_driving.VCard;
import com.gamefactoryx.cheers.tool.Configuration;
import com.gamefactoryx.cheers.tool.Orientation;
import com.gamefactoryx.cheers.tool.Resolution;
import com.gamefactoryx.cheers.view.AbstractScreen;

import java.util.HashMap;

/**
 * Created by bernat on 16.05.2017.
 */
public class BusDrivingStageController extends AbstractController {

    private BusDrivingModel model;
    private static boolean flag;
    private StringBuilder typedName = new StringBuilder();
    private boolean shift;
    private boolean keyboardOn;
    private Card activeCard;

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
                model.getPlayer().setName(typedName.toString());
                typedName.setLength(0);
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

            model.getPlayer().setName("");
            enableKeyboard(true);

        } else {
            //is keyboard on?
            if (keyboardOn) {
                model.getPlayer().setName(typedName.toString());
                typedName.setLength(0);
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

                    model.getPhase().nextTurn();
                    if (model.getPhase().isPhaseFinished()) {
                        model.nextPhase();
                        setScreenLockForPhase(model.getPhase().getName());
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
            for (VCard vCard : model.getPhase().getBoard().getVCards()) {
                ++vCard_index;
                if (vCard_index == 9)
                    model.setScrollPyramide(true);
                if (vCard.getOrientation() == VCard.CardOrientation.BACK) {
                    vCard.setOrientation(VCard.CardOrientation.FACE);
                    activeCard = new Card(vCard.getCardIndex(), Card.CardSize.SMALL);
                    model.firstPlayer();
                    do {
                        Player player = model.getPlayer();
                        //player.getMessage().clear();
                        for (VCard playerVCard : player.getVCards()) {
                            Card playerCard = new Card(playerVCard.getCardIndex(), Card.CardSize.SMALL);
                            //todo comparable
                            //if (activeCard.getValue() == playerCard.getValue()) {
                            if(activeCard.equals(playerCard)){
                                //playerVCard.setCredit(1);
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
                            } else
                                playerVCard.setCredit(0);
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
                model.reset();
                setScreenLockForPhase(model.getPhase().getName());
                flag = false;
                activeCard = null;

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
}
