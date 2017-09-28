package com.gamefactoryx.cheers.controller.busdriving;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.gamefactoryx.cheers.controller.AbstractController;
import com.gamefactoryx.cheers.controller.StageEnum;
import com.gamefactoryx.cheers.controller.StageManager;
import com.gamefactoryx.cheers.model.PlayerNameCache;
import com.gamefactoryx.cheers.model.Subject;
import com.gamefactoryx.cheers.model.busdriving.BusDrivingPhase0Model;
import com.gamefactoryx.cheers.model.busdriving.Croupier;
import com.gamefactoryx.cheers.tool.Configuration;
import com.gamefactoryx.cheers.tool.Resolution;
import com.gamefactoryx.cheers.view.AbstractScreen;

/**
 * Created by bernat on 16.05.2017.
 */
public class BusDrivingStagePhase0Controller extends AbstractController {

    private StringBuilder typedName = new StringBuilder();
    private boolean shift;
    private boolean keyboardOn;
    private String tempName;
    private BusDrivingPhase0Model model;
    private int activeBoxIndex;
    private int touchPos;


    public BusDrivingStagePhase0Controller(final AbstractScreen screen) {
        super(screen);
        setScreenLock(1);
        enableKeyboard(false);
        model = BusDrivingPhase0Model.getNewInstance();
    }


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        if (screenX >= getScreen().getButtons()[6][0].getX() &&
                screenX <= getScreen().getButtons()[6][0].getX() + getScreen().getButtons()[6][0].getWidth() &&
                Resolution.getGameWorldHeightPortrait() - screenY >= getScreen().getButtons()[6][0].getY() &&
                Resolution.getGameWorldHeightPortrait() - screenY <= getScreen().getButtons()[6][0].getY() + getScreen().getButtons()[6][0].getHeight())
            model.setLoadingNextStage(true);



            touchPos = screenX;
        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        int distance = screenX - touchPos;
        touchPos = 0;

        if (!super.touchUp(screenX, screenY, pointer, button))
            return true;

        if (keyboardOn) {
            enableKeyboard(false);
            setPlayerName();
            return true;
        }

        if (screenX >= getScreen().getButtons()[6][0].getX() &&
                screenX <= getScreen().getButtons()[6][0].getX() + getScreen().getButtons()[6][0].getWidth() &&
                Resolution.getGameWorldHeightPortrait() - screenY >= getScreen().getButtons()[6][0].getY() &&
                Resolution.getGameWorldHeightPortrait() - screenY <= getScreen().getButtons()[6][0].getY() + getScreen().getButtons()[6][0].getHeight()) {
            Gdx.input.vibrate(10);
            StageManager.getInstance().showStage(StageEnum.BUS_DRIVING_STAGE_FIRST_PHASE);
            return true;
        }

        int maxPlayersProPage = Configuration.getMaxPlayersProConfigPage();
        int page = model.getPage();


        for (int i = 0; i < /*getScreen().getCountOfButtons() - 3*/ getMaxPlayers(); i++) {

            if (screenX >= getScreen().getButtons()[i][0].getX() + getScreen().getButtons()[i][0].getWidth() * 0.9f &&
                    screenX <= getScreen().getButtons()[i][0].getX() + getScreen().getButtons()[i][0].getWidth() &&
                    Resolution.getGameWorldHeightPortrait() - screenY >= getScreen().getButtons()[i][0].getY() &&
                    Resolution.getGameWorldHeightPortrait() - screenY <= getScreen().getButtons()[i][0].getY() + getScreen().getButtons()[i][0].getHeight()) {

                activeBoxIndex = i;

                if (Croupier.getInstance().getPlayers().get(activeBoxIndex + maxPlayersProPage * (page - 1)).isEnable()) {
                    for (int j = activeBoxIndex + Configuration.getMaxPlayersProConfigPage() * (model.getPage() - 1); j < Configuration.getMaxPlayers(); j++)
                        model.getPlayers().get(j).setEnable(false);
                } else {
                    for (int j = 0; j <= activeBoxIndex + com.gamefactoryx.cheers.tool.Configuration.getMaxPlayersProConfigPage() * (model.getPage() - 1); j++)
                        model.getPlayers().get(j).setEnable(true);
                }
                return true;

            } else if (screenX >= getScreen().getButtons()[i][0].getX() &&
                    screenX <= getScreen().getButtons()[i][0].getX() + getScreen().getButtons()[i][0].getWidth() * 0.1f &&
                    Resolution.getGameWorldHeightPortrait() - screenY >= getScreen().getButtons()[i][0].getY() &&
                    Resolution.getGameWorldHeightPortrait() - screenY <= getScreen().getButtons()[i][0].getY() + getScreen().getButtons()[i][0].getHeight()) {
                model.getPlayers().get(i + maxPlayersProPage * (page - 1)).setSex(
                        model.getPlayers().get(i + maxPlayersProPage * (page - 1)).getSex() == Subject.Sex.MALE ? Subject.Sex.FEMALE : Subject.Sex.MALE);
                return true;
            } else if (screenX >= getScreen().getButtons()[i][0].getX() &&
                    screenX <= getScreen().getButtons()[i][0].getX() + getScreen().getButtons()[i][0].getWidth() * 0.85f &&
                    Resolution.getGameWorldHeightPortrait() - screenY >= getScreen().getButtons()[i][0].getY() &&
                    Resolution.getGameWorldHeightPortrait() - screenY <= getScreen().getButtons()[i][0].getY() + getScreen().getButtons()[i][0].getHeight()) {

                if (model.getPlayers().get(i + maxPlayersProPage * (page - 1)).isEnable()) {
                    activeBoxIndex = i;
                    tempName = model.getPlayers().get(activeBoxIndex + maxPlayersProPage * (page - 1)).getName();
                    model.getPlayers().get(activeBoxIndex + maxPlayersProPage * (page - 1)).setName("");
                    enableKeyboard(true);
                }
                return true;
            }
        }
        if (distance <= 100) {
            if (!model.isLastPage())
                model.setPage(model.getPage() + 1);

        } else if (distance >= -100) {
            if (!model.isFirstPage()) {
                model.setPage(model.getPage() - 1);

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
                    int maxPlayersProPage = com.gamefactoryx.cheers.tool.Configuration.getMaxPlayersProConfigPage();
                    int page = model.getPage();

                    typedName.append(shift ? Input.Keys.toString(keycode).toUpperCase() : Input.Keys.toString(keycode).toLowerCase());
                    model.getPlayers().get(activeBoxIndex + maxPlayersProPage * (page - 1)).setName(typedName.toString());
                    PlayerNameCache.addName(typedName.toString(), model.getPlayers().get(activeBoxIndex + maxPlayersProPage * (page - 1)).getPosition());
                }
                break;
        }
        return false;
    }


    private void enableKeyboard(boolean enabled) {
        keyboardOn = enabled;
        Gdx.input.setOnscreenKeyboardVisible(enabled);
    }


    /*private void setPlayerName() {
        if (typedName.toString().trim().length() > 0)
            model.getPlayers().get(activeBoxIndex).setName(typedName.toString());
        else
            model.getPlayers().get(activeBoxIndex).setName(tempName);
        typedName.setLength(0);
    }*/

    private void setPlayerName() {
        int maxPlayersProPage = com.gamefactoryx.cheers.tool.Configuration.getMaxPlayersProConfigPage();
        int page = model.getPage();

        if (typedName.toString().trim().length() > 0)
            model.getPlayers().get(activeBoxIndex + maxPlayersProPage * (page - 1)).setName(typedName.toString());
        else
            model.getPlayers().get(activeBoxIndex + maxPlayersProPage * (page - 1)).setName(tempName);
        typedName.setLength(0);
    }

    private int getMaxPlayers() {
        int page = model.getPage();
        int maxPlayersProPage = com.gamefactoryx.cheers.tool.Configuration.getMaxPlayersProConfigPage();
        return Configuration.getMaxPlayers() >= (page * maxPlayersProPage) ? maxPlayersProPage :
                maxPlayersProPage - (page * maxPlayersProPage - Configuration.getMaxPlayers());
    }


}
