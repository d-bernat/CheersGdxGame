package com.gamefactoryx.cheersapp.controller.kongosdrink;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.gamefactoryx.cheersapp.controller.AbstractController;
import com.gamefactoryx.cheersapp.controller.StageManager;
import com.gamefactoryx.cheersapp.model.kongosdrink.AvatarType;
import com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel;
import com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkPhase01Model;
import com.gamefactoryx.cheersapp.tool.kongosdrink.Configuration;
import com.gamefactoryx.cheersapp.view.AbstractScreen;

/**
 * Created by bernat on 16.05.2017.
 */
public class KongosDrinkPhase01Controller extends AbstractController {

    private StringBuilder typedName = new StringBuilder();
    private boolean shift;
    private boolean keyboardOn;
    private String tempName;
    // private int activeBoxIndex;
    // private int touchPos;


    public KongosDrinkPhase01Controller(final AbstractScreen screen) {
        super(screen);
        setScreenLock(1);
        KongosDrinkPhase01Model.getInstance().setPlayerToConfigureIndex(0);
        //enableKeyboard(false);
        //model = KongosDrinkPhase0Model.getInstance();
        //Configuration.getInstance();
    }


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        //touchPos = screenX;
        //int lastButtonIndex = getScreen().getButtons().length - 1;
        float y = StageManager.getInstance().getGame().isAdMobVisible() ?
                getScreen().getButtons()[0][0].getY() + StageManager.getInstance().getGame().getAdMobHeight() :
                getScreen().getButtons()[0][0].getY();
        if (screenX >= getScreen().getButtons()[0][0].getX() &&
                screenX <= getScreen().getButtons()[0][0].getX() + getScreen().getButtons()[0][0].getWidth() &&
                com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightPortrait() - screenY >= y &&
                com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightPortrait() - screenY <= y + getScreen().getButtons()[0][0].getHeight()) {
            KongosDrinkMainModel.getInstance().setLoadingNextStage(true);
        }

        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        /*int distance = screenX - touchPos;
        touchPos = 0;*/

        if (!super.touchUp(screenX, screenY, pointer, button))
            return true;

        if (keyboardOn) {
            enableKeyboard(false);
            setPlayerName();
            return true;
        }

        for (int i = 1; i < getScreen().getButtons().length; i++) {
            float y = StageManager.getInstance().getGame().isAdMobVisible() ?
                    getScreen().getButtons()[i][0].getY() + StageManager.getInstance().getGame().getAdMobHeight() :
                    getScreen().getButtons()[i][0].getY();
            if (screenX >= getScreen().getButtons()[i][0].getX() &&
                    screenX <= getScreen().getButtons()[i][0].getX() + getScreen().getButtons()[i][0].getWidth() &&
                    com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightPortrait() - screenY >= y &&
                    com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightPortrait() - screenY <= y + getScreen().getButtons()[i][0].getHeight()) {

                if (i < getScreen().getButtons().length - 2) {
                    if (!isDisabled(i)) {
                        Gdx.input.vibrate(10);

                        for (AvatarType at : AvatarType.values()) {
                            if (at.value() == i - 1) {
                                Configuration.getInstance().getPlayers()
                                        .get(KongosDrinkPhase01Model.getInstance().getPlayerToConfigureIndex()).setAvatar(at);
                                break;
                            }
                        }
                    }
                } else {
                    if (i == getScreen().getButtons().length - 2 && KongosDrinkPhase01Model.getInstance().getPlayerToConfigureIndex() > 0) {
                        Gdx.input.vibrate(10);
                        KongosDrinkPhase01Model.getInstance().setPlayerToConfigureIndex(KongosDrinkPhase01Model.getInstance().getPlayerToConfigureIndex() - 1);
                    } else if (i == getScreen().getButtons().length - 1 && KongosDrinkPhase01Model.getInstance().getPlayerToConfigureIndex() < Configuration.getInstance().enabledPlayers() - 1) {
                        Gdx.input.vibrate(10);
                        KongosDrinkPhase01Model.getInstance().setPlayerToConfigureIndex(KongosDrinkPhase01Model.getInstance().getPlayerToConfigureIndex() + 1);
                    }
                }
            }
        }
        float y = StageManager.getInstance().getGame().isAdMobVisible() ?
                getScreen().getButtons()[0][0].getY() + StageManager.getInstance().getGame().getAdMobHeight() :
                getScreen().getButtons()[0][0].getY();
        if (screenX >= getScreen().getButtons()[0][0].getX() &&
                screenX <= getScreen().getButtons()[0][0].getX() + getScreen().getButtons()[0][0].getWidth() &&
                com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightPortrait() - screenY >= y &&
                com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightPortrait() - screenY <= y + getScreen().getButtons()[0][0].getHeight()) {
            Gdx.input.vibrate(10);
            StageManager.getInstance().getGame().getInterstitialResolver().showOrLoadInterstitial();
            com.gamefactoryx.cheersapp.controller.kongosdrink.KongosDrinkStageManager.getInstance().showStage(com.gamefactoryx.cheersapp.controller.kongosdrink.KongosDrinkStageEnum.KONGOS_DRINK_MAIN_STAGE);
            return true;
        }

        y = StageManager.getInstance().getGame().isAdMobVisible() ?
                getScreen().getTextBox().getY() + StageManager.getInstance().getGame().getAdMobHeight() :
                getScreen().getTextBox().getY();
        if (screenX >= getScreen().getTextBox().getX() &&
                screenX <= getScreen().getTextBox().getX() + getScreen().getTextBox().getWidth() &&
                com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightPortrait() - screenY >= y &&
                com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightPortrait() - screenY <= y + getScreen().getTextBox().getHeight()) {
            tempName = Configuration.getInstance().getPlayers().get(KongosDrinkPhase01Model.getInstance().getPlayerToConfigureIndex()).getName();
            Configuration.getInstance().getPlayers().get(KongosDrinkPhase01Model.getInstance().getPlayerToConfigureIndex()).setName("");
            enableKeyboard(true);
            return true;
        }


       /* int maxPlayersProPage = Configuration.getMaxPlayersProConfigPage();
        int page = model.getPage();

        for (int i = 0; i < getMaxPlayers(); i++) {

            if (screenX >= getScreen().getButtons()[i][0].getX() + getScreen().getButtons()[i][0].getWidth() * 0.9f &&
                    screenX <= getScreen().getButtons()[i][0].getX() + getScreen().getButtons()[i][0].getWidth() &&
                    Resolution.getGameWorldHeightPortrait() - screenY >= getScreen().getButtons()[i][0].getY() &&
                    Resolution.getGameWorldHeightPortrait() - screenY <= getScreen().getButtons()[i][0].getY() + getScreen().getButtons()[i][0].getHeight()) {

                activeBoxIndex = i;

                if (Configuration.getInstance().getPlayers().get(activeBoxIndex + maxPlayersProPage * (page - 1)).isEnable()) {
                    for (int j = activeBoxIndex + Configuration.getMaxPlayersProConfigPage() * (model.getPage() - 1); j < Configuration.getInstance().getMaxPlayers(); j++)
                        model.getPlayers().get(j).setEnable(false);
                } else {
                    for (int j = 0; j <= activeBoxIndex + Configuration.getMaxPlayersProConfigPage() * (model.getPage() - 1); j++)
                        model.getPlayers().get(j).setEnable(true);
                }
                return true;
            } else if (screenX >= getScreen().getButtons()[i][0].getX() &&
                    screenX <= getScreen().getButtons()[i][0].getX() + getScreen().getButtons()[i][0].getWidth() * 0.1f &&
                    Resolution.getGameWorldHeightPortrait() - screenY >= getScreen().getButtons()[i][0].getY() &&
                    Resolution.getGameWorldHeightPortrait() - screenY <= getScreen().getButtons()[i][0].getY() + getScreen().getButtons()[i][0].getHeight()) {
                Configuration.getInstance().getPlayers().get(i + maxPlayersProPage * (page - 1)).setSex(
                        Configuration.getInstance().getPlayers().get(i + maxPlayersProPage * (page - 1)).getSex() == Subject.Sex.MALE ? Subject.Sex.FEMALE : Subject.Sex.MALE);
                return true;
            } else if (screenX >= getScreen().getButtons()[i][0].getX() &&
                    screenX <= getScreen().getButtons()[i][0].getX() + getScreen().getButtons()[i][0].getWidth() &&
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
        }*/

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
                    Configuration.getInstance().getPlayers().get(KongosDrinkPhase01Model.getInstance().getPlayerToConfigureIndex()).setName(typedName.toString());

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
                    Configuration.getInstance().getPlayers().get(KongosDrinkPhase01Model.getInstance().getPlayerToConfigureIndex()).setName(typedName.toString());
                    //PlayerNameCache.addName(typedName.toString(), model.getPlayers().get(activeBoxIndex + maxPlayersProPage * (page - 1)).getPosition());
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
            Configuration.getInstance().getPlayers().get(KongosDrinkPhase01Model.getInstance().getPlayerToConfigureIndex()).setName(typedName.toString());
        else
            Configuration.getInstance().getPlayers().get(KongosDrinkPhase01Model.getInstance().getPlayerToConfigureIndex()).setName(tempName);
        typedName.setLength(0);
    }

    private boolean isDisabled(int index){

        boolean rs = false;
        for(int i = 0; i < Configuration.getInstance().getPlayers().size(); i++) {
            int av_index = Configuration.getInstance().getPlayers().get(KongosDrinkPhase01Model.getInstance().getPlayerToConfigureIndex()).getAvatar().value() + 1;
            if( index == Configuration.getInstance().getPlayers().get(i).getAvatar().value() + 1 && av_index != index) {
                rs = true;
            }else{
            }
        }
        return rs;
    }
}
