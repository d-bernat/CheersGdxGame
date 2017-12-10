package com.gamefactoryx.cheersapp.controller.kongosdrink;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.gamefactoryx.cheersapp.controller.AbstractController;
import com.gamefactoryx.cheersapp.controller.StageEnum;
import com.gamefactoryx.cheersapp.controller.StageManager;
import com.gamefactoryx.cheersapp.model.PlayerNameCache;
import com.gamefactoryx.cheersapp.model.Subject;
import com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkPhase0Model;
import com.gamefactoryx.cheersapp.tool.Resolution;
import com.gamefactoryx.cheersapp.tool.kongosdrink.Configuration;
import com.gamefactoryx.cheersapp.view.AbstractScreen;

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
    private int touchPos;


    public KongosDrinkPhase0Controller(final AbstractScreen screen) {
        super(screen);
        setScreenLock(1);
        enableKeyboard(false);
        model = KongosDrinkPhase0Model.getNewInstance();
        Configuration.getNewInstance();
    }


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        touchPos = screenX;
        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        if (!super.touchUp(screenX, screenY, pointer, button))
            return true;

        int distance = screenX - touchPos;
        touchPos = 0;


        if (keyboardOn) {
            enableKeyboard(false);
            setPlayerName();
            return true;
        }

        float y = StageManager.getInstance().getGame().isAdMobVisible() ?
                getScreen().getButtons()[6][0].getY() + StageManager.getInstance().getGame().getAdMobHeight() :
                getScreen().getButtons()[6][0].getY();
        if (screenX >= getScreen().getButtons()[6][0].getX() &&
                screenX <= getScreen().getButtons()[6][0].getX() + getScreen().getButtons()[6][0].getWidth() &&
                Resolution.getGameWorldHeightPortrait() - screenY >= y &&
                Resolution.getGameWorldHeightPortrait() - screenY <= y + getScreen().getButtons()[6][0].getHeight()) {
            Gdx.input.vibrate(10);
            StageManager.getInstance().showStage(StageEnum.KONGOS_DRINK_SETUP_STAGE);
            return true;
        }

        int maxPlayersProPage = com.gamefactoryx.cheersapp.tool.Configuration.getMaxPlayersProConfigPage();
        int page = model.getPage();

        for (int i = 0; i < /*getScreen().getCountOfButtons() - 3*/ getMaxPlayers(); i++) {
            y = StageManager.getInstance().getGame().isAdMobVisible() ?
                    getScreen().getButtons()[i][0].getY() + StageManager.getInstance().getGame().getAdMobHeight() :
                    getScreen().getButtons()[i][0].getY();
            if (screenX >= getScreen().getButtons()[i][0].getX() + getScreen().getButtons()[i][0].getWidth() * 0.9f &&
                    screenX <= getScreen().getButtons()[i][0].getX() + getScreen().getButtons()[i][0].getWidth() &&
                    Resolution.getGameWorldHeightPortrait() - screenY >= y &&
                    Resolution.getGameWorldHeightPortrait() - screenY <= y + getScreen().getButtons()[i][0].getHeight()) {

                activeBoxIndex = i;

                if (Configuration.getInstance().getPlayers().get(activeBoxIndex + maxPlayersProPage * (page - 1)).isEnable()) {
                    for (int j = activeBoxIndex + com.gamefactoryx.cheersapp.tool.Configuration.getMaxPlayersProConfigPage() * (model.getPage() - 1); j < Configuration.getInstance().getMaxPlayers(); j++)
                        model.getPlayers().get(j/* + maxPlayersProPage * (page - 1)*/).setEnable(false);
                } else {
                    for (int j = 0; j <= activeBoxIndex + com.gamefactoryx.cheersapp.tool.Configuration.getMaxPlayersProConfigPage() * (model.getPage() - 1); j++)
                        model.getPlayers().get(j/* + maxPlayersProPage * (page - 1)*/).setEnable(true);
                }
                return true;
            } else if (screenX >= getScreen().getButtons()[i][0].getX() &&
                    screenX <= getScreen().getButtons()[i][0].getX() + getScreen().getButtons()[i][0].getWidth() * 0.1f &&
                    Resolution.getGameWorldHeightPortrait() - screenY >= y &&
                    Resolution.getGameWorldHeightPortrait() - screenY <= y + getScreen().getButtons()[i][0].getHeight()) {
                Configuration.getInstance().getPlayers().get(i + maxPlayersProPage * (page - 1)).setSex(
                        Configuration.getInstance().getPlayers().get(i + maxPlayersProPage * (page - 1)).getSex() == Subject.Sex.MALE ? Subject.Sex.FEMALE : Subject.Sex.MALE);
                return true;
            } else if (screenX >= getScreen().getButtons()[i][0].getX() &&
                    screenX <= getScreen().getButtons()[i][0].getX() + getScreen().getButtons()[i][0].getWidth() &&
                    Resolution.getGameWorldHeightPortrait() - screenY >= y &&
                    Resolution.getGameWorldHeightPortrait() - screenY <= y + getScreen().getButtons()[i][0].getHeight()) {

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
                    int maxPlayersProPage = com.gamefactoryx.cheersapp.tool.Configuration.getMaxPlayersProConfigPage();
                    int page = model.getPage();
                    model.getPlayers().get(activeBoxIndex + maxPlayersProPage * (page - 1)).setName(typedName.toString());
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
                    int maxPlayersProPage = com.gamefactoryx.cheersapp.tool.Configuration.getMaxPlayersProConfigPage();
                    int page = model.getPage();

                    typedName.append(shift ? Input.Keys.toString(keycode).toUpperCase() : Input.Keys.toString(keycode).toLowerCase());
                    model.getPlayers().get(activeBoxIndex + maxPlayersProPage * (page - 1)).setName(typedName.toString());
                    PlayerNameCache.addName(typedName.toString(), model.getPlayers().get(activeBoxIndex + maxPlayersProPage * (page - 1)).getPosition());
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
        int maxPlayersProPage = com.gamefactoryx.cheersapp.tool.Configuration.getMaxPlayersProConfigPage();
        int page = model.getPage();

        if (typedName.toString().trim().length() > 0)
            model.getPlayers().get(activeBoxIndex + maxPlayersProPage * (page - 1)).setName(typedName.toString());
        else
            model.getPlayers().get(activeBoxIndex + maxPlayersProPage * (page - 1)).setName(tempName);
        typedName.setLength(0);
    }

    private int getMaxPlayers() {
        int page = model.getPage();
        int maxPlayersProPage = com.gamefactoryx.cheersapp.tool.Configuration.getMaxPlayersProConfigPage();
        return Configuration.getInstance().getMaxPlayers() >= (page * maxPlayersProPage) ? maxPlayersProPage :
                maxPlayersProPage - (page * maxPlayersProPage - Configuration.getInstance().getMaxPlayers());
    }


}
