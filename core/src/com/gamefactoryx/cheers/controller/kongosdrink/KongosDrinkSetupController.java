package com.gamefactoryx.cheers.controller.kongosdrink;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.gamefactoryx.cheers.controller.AbstractController;
import com.gamefactoryx.cheers.controller.StageEnum;
import com.gamefactoryx.cheers.controller.StageManager;
import com.gamefactoryx.cheers.model.PlayerNameCache;
import com.gamefactoryx.cheers.model.Subject;
import com.gamefactoryx.cheers.model.kongosdrink.KongosDrinkMainModel;
import com.gamefactoryx.cheers.model.kongosdrink.KongosDrinkPhase0Model;
import com.gamefactoryx.cheers.tool.Resolution;
import com.gamefactoryx.cheers.tool.kongosdrink.Configuration;
import com.gamefactoryx.cheers.tool.kongosdrink.PlayerToTeamsTransformator;
import com.gamefactoryx.cheers.view.AbstractScreen;

/**
 * Created by bernat on 16.05.2017.
 */
public class KongosDrinkSetupController extends AbstractController {


    public KongosDrinkSetupController(final AbstractScreen screen) {
        super(screen);
        setScreenLock(1);
    }


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        int lastButtonIndex = getScreen().getButtons().length - 1;
        if (screenX >= getScreen().getButtons()[lastButtonIndex][0].getX() &&
                screenX <= getScreen().getButtons()[lastButtonIndex][0].getX() + getScreen().getButtons()[lastButtonIndex][0].getWidth() &&
                Resolution.getGameWorldHeightPortrait() - screenY >= getScreen().getButtons()[lastButtonIndex][0].getY() &&
                Resolution.getGameWorldHeightPortrait() - screenY <= getScreen().getButtons()[lastButtonIndex][0].getY() + getScreen().getButtons()[lastButtonIndex][0].getHeight()) {
            Configuration.getInstance().setPlayers(PlayerToTeamsTransformator.toTeams(Configuration.getInstance().getPlayers()));
            KongosDrinkMainModel.getInstance().setLoadingNextStage(true);
        }
        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        if (!super.touchUp(screenX, screenY, pointer, button))
            return true;

        int lastButtonIndex = getScreen().getButtons().length - 1;
        if (screenX >= getScreen().getButtons()[lastButtonIndex][0].getX() &&
                screenX <= getScreen().getButtons()[lastButtonIndex][0].getX() + getScreen().getButtons()[lastButtonIndex][0].getWidth() &&
                Resolution.getGameWorldHeightPortrait() - screenY >= getScreen().getButtons()[lastButtonIndex][0].getY() &&
                Resolution.getGameWorldHeightPortrait() - screenY <= getScreen().getButtons()[lastButtonIndex][0].getY() + getScreen().getButtons()[lastButtonIndex][0].getHeight()) {
            Gdx.input.vibrate(10);
            KongosDrinkStageManager.getInstance().showStage(KongosDrinkStageEnum.KONGOS_DRINK_MAIN_STAGE);
            return true;
        }

        for(int i = 0; i < 6; ++i ){
            if (screenX >= getScreen().getButtons()[i][0].getX() &&
                    screenX <= getScreen().getButtons()[i][0].getX() + getScreen().getButtons()[i][0].getWidth() &&
                    Resolution.getGameWorldHeightPortrait() - screenY >= getScreen().getButtons()[i][0].getY() &&
                    Resolution.getGameWorldHeightPortrait() - screenY <= getScreen().getButtons()[i][0].getY() + getScreen().getButtons()[6][0].getHeight()) {
                switch(i){
                    case 0: Configuration.getInstance().setGameSize(Configuration.GameSizeEnum.TEN);break;
                    case 1: Configuration.getInstance().setGameSize(Configuration.GameSizeEnum.FIFTEEN);break;
                    case 2: Configuration.getInstance().setGameSize(Configuration.GameSizeEnum.TWENTY);break;
                    case 3: Configuration.getInstance().setGameSize(Configuration.GameSizeEnum.THRITY);break;
                    case 4: Configuration.getInstance().setGameSize(Configuration.GameSizeEnum.FORTY);break;
                    case 5: Configuration.getInstance().setGameSize(Configuration.GameSizeEnum.FIFTY);break;
                }
                Gdx.input.vibrate(10);
                break;
            }
        }

        for(int i = 6; i < 9; ++i ){
            if (screenX >= getScreen().getButtons()[i][0].getX() &&
                    screenX <= getScreen().getButtons()[i][0].getX() + getScreen().getButtons()[i][0].getWidth() &&
                    Resolution.getGameWorldHeightPortrait() - screenY >= getScreen().getButtons()[i][0].getY() &&
                    Resolution.getGameWorldHeightPortrait() - screenY <= getScreen().getButtons()[i][0].getY() + getScreen().getButtons()[6][0].getHeight()) {
                switch(i){
                    case 6: Configuration.getInstance().setGameType(Configuration.GameTypeEnum.DOGFIGHT);break;
                    case 7: Configuration.getInstance().setGameType(Configuration.GameTypeEnum.TEAMOFTWO_VS_TEAMOFTWO);break;
                    case 8: Configuration.getInstance().setGameType(Configuration.GameTypeEnum.TEAM_VS_TEAM);break;
                }
                Gdx.input.vibrate(10);
                break;
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
            case Input.Keys.BACK:
                StageManager.getInstance().showLastStage();
                break;
        }
        return true;
    }


}
