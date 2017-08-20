package com.gamefactoryx.cheers.controller.kongosdrink;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.gamefactoryx.cheers.controller.AbstractController;
import com.gamefactoryx.cheers.controller.StageEnum;
import com.gamefactoryx.cheers.controller.StageManager;
import com.gamefactoryx.cheers.model.PlayerNameCache;
//import com.gamefactoryx.cheers.model.busdriving.Croupier;
import com.gamefactoryx.cheers.model.kongosdrink.KongosDrinkPhase0Model;
//import com.gamefactoryx.cheers.tool.Configuration;
import com.gamefactoryx.cheers.tool.Resolution;
import com.gamefactoryx.cheers.tool.kongosdrink.Configuration;
import com.gamefactoryx.cheers.view.AbstractScreen;

/**
 * Created by bernat on 16.05.2017.
 */
public class KongosDrinkPhase0Controller extends AbstractController {

    private StringBuilder typedName = new StringBuilder();
    private boolean shift;
    private boolean keyboardOn;
    private String tempName;
    private KongosDrinkPhase0Model model;
    private int activeBoxIndex;


    public KongosDrinkPhase0Controller(final AbstractScreen screen) {
        super(screen);
        setScreenLock(1);
        enableKeyboard(false);
        model = KongosDrinkPhase0Model.getNewInstance();
    }


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {


        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        if(!super.touchUp(screenX, screenY, pointer, button))
            return true;

        if(keyboardOn){
            enableKeyboard(false);
            setPlayerName();
            return true;
        }


        for (int i = 0; i < getScreen().getCountOfButtons() - 2; i++) {

            if (screenX >= getScreen().getButtons()[i][0].getX() + getScreen().getButtons()[i][0].getWidth() * 0.9f &&
                    screenX <= getScreen().getButtons()[i][0].getX() + getScreen().getButtons()[i][0].getWidth() &&
                    Resolution.getGameWorldHeightPortrait() - screenY >= getScreen().getButtons()[i][0].getY() &&
                    Resolution.getGameWorldHeightPortrait() - screenY <= getScreen().getButtons()[i][0].getY() + getScreen().getButtons()[i][0].getHeight()) {

                activeBoxIndex = i;

                if (Configuration.getPlayers().get(activeBoxIndex).isEnable()) {
                    for (int j = activeBoxIndex; j < com.gamefactoryx.cheers.tool.Configuration.getMaxPlayers(); j++)
                        model.getPlayers().get(j).setEnable(false);
                }
                else {
                    for (int j = 0; j <= activeBoxIndex; j++)
                        model.getPlayers().get(j).setEnable(true);
                }



            } else if (screenX >= getScreen().getButtons()[i][0].getX() &&
                    screenX <= getScreen().getButtons()[i][0].getX() + getScreen().getButtons()[i][0].getWidth() * 0.85f &&
                    Resolution.getGameWorldHeightPortrait() - screenY >= getScreen().getButtons()[i][0].getY() &&
                    Resolution.getGameWorldHeightPortrait() - screenY <= getScreen().getButtons()[i][0].getY() + getScreen().getButtons()[i][0].getHeight()) {

                if(model.getPlayers().get(i).isEnable()) {
                    activeBoxIndex = i;
                    tempName = model.getPlayers().get(activeBoxIndex).getName();
                    model.getPlayers().get(activeBoxIndex).setName("");
                    enableKeyboard(true);
                }
            }
        }

        if(screenX >= getScreen().getButtons()[7][0].getX() &&
                screenX <= getScreen().getButtons()[7][0].getX() + getScreen().getButtons()[7][0].getWidth()&&
                Resolution.getGameWorldHeightPortrait() - screenY >= getScreen().getButtons()[7][0].getY() &&
                Resolution.getGameWorldHeightPortrait() - screenY <= getScreen().getButtons()[7][0].getY() + getScreen().getButtons()[7][0].getHeight())
            //StageManager.getInstance().showStage(StageEnum.BUS_DRIVING_STAGE_FIRST_PHASE);
            KongosDrinkStageManager.getInstance().showStage(KongosDrinkStageEnum.KONGOS_DRINK_MAIN_STAGE);


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
//                    model.getPlayers().get(model.getActivePlayer()).setName(typedName.toString());
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
                    model.getPlayers().get(activeBoxIndex).setName(typedName.toString());
                    PlayerNameCache.addName(typedName.toString(), model.getPlayers().get(activeBoxIndex).getPosition());
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
            model.getPlayers().get(activeBoxIndex).setName(typedName.toString());
        else
            model.getPlayers().get(activeBoxIndex).setName(tempName);
        typedName.setLength(0);
    }

}
