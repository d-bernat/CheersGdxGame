package com.gamefactoryx.cheers.controller;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.gamefactoryx.cheers.model.BusDrivingModel;
import com.gamefactoryx.cheers.model.PlayerNameCache;
import com.gamefactoryx.cheers.tool.Orientation;
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

    BusDrivingStageController(final AbstractScreen screen) {
        super(screen);
        model = BusDrivingModel.getInstance();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        if (screenX >= getScreen().getTextBox().getX() &&
                screenX <= getScreen().getTextBox().getX() + getScreen().getTextBox().getWidth() &&
                Resolution.getGameWorldHeightPortrait() - screenY >= getScreen().getTextBox().getY() &&
                Resolution.getGameWorldHeightPortrait() - screenY <= getScreen().getTextBox().getY() + getScreen().getTextBox().getHeight()) {

            model.getPlayer().setName("");
            enableKeyboard(true);

        } else {
            if (keyboardOn) {
                model.getPlayer().setName(typedName.toString());
                typedName.setLength(0);
                enableKeyboard(false);
            } else {
                boolean next = true;
                if (screenX < 100) {
                    model.reset();
                    flag = false;
                    return true;
                } else if (model.getPhase().isFinished()) {
                    return true;
                } else if (flag) {
                    if (next) {
                        if(model.getPlayer().getCards().size < 4 )
                            model.getPlayer().addCard(model.getPhase().getBoard().getCards().removeLast());
                        else
                            model.getPhase().nextRound();
                    }
                } else {
                    if (next)
                        model.getPhase().getBoard().addCard(model.getCroupier().getCard());
                }
                flag = !flag;
            }
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
}
