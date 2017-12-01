package com.gamefactoryx.cheersapp.controller.kongosdrink;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.gamefactoryx.cheersapp.CheersGdxGame;
import com.gamefactoryx.cheersapp.controller.AbstractController;
import com.gamefactoryx.cheersapp.controller.StageEnum;
import com.gamefactoryx.cheersapp.controller.StageManager;
import com.gamefactoryx.cheersapp.tool.Resolution;
import com.gamefactoryx.cheersapp.tool.kongosdrink.Configuration;
import com.gamefactoryx.cheersapp.tool.kongosdrink.PlayerToTeamsTransformator;
import com.gamefactoryx.cheersapp.view.AbstractScreen;

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
            Configuration.getInstance().setPlayers(PlayerToTeamsTransformator.toTeams(Configuration.getInstance().getPlayers()));
            StageManager.getInstance().showStage(StageEnum.KONGOS_DRINK_ZERO_ONE_STAGE);
            return true;
        }

        for(int i = 0; i < 6; ++i ){
            if (screenX >= getScreen().getButtons()[i][0].getX() &&
                    screenX <= getScreen().getButtons()[i][0].getX() + getScreen().getButtons()[i][0].getWidth() &&
                    Resolution.getGameWorldHeightPortrait() - screenY >= getScreen().getButtons()[i][0].getY() &&
                    Resolution.getGameWorldHeightPortrait() - screenY <= getScreen().getButtons()[i][0].getY() + getScreen().getButtons()[6][0].getHeight()) {
                switch(i){
                    case 0:
                        if(getScreen().getButtons()[i][0].isAllowed())
                            Configuration.getInstance().setGameSize(Configuration.GameSizeEnum.TEN);
                        else
                            StageManager.getInstance().getGame().getPlatformResolver().requestPurchase(CheersGdxGame.productID_fullVersion);
                        break;
                    case 1: Configuration.getInstance().setGameSize(Configuration.GameSizeEnum.FIFTEEN);break;
                    case 2: Configuration.getInstance().setGameSize(Configuration.GameSizeEnum.TWENTY);break;
                    case 3:
                        if(getScreen().getButtons()[i][0].isAllowed())
                            Configuration.getInstance().setGameSize(Configuration.GameSizeEnum.THRITY);
                        else
                            StageManager.getInstance().getGame().getPlatformResolver().requestPurchase(CheersGdxGame.productID_fullVersion);
                        break;
                    case 4:
                        if(getScreen().getButtons()[i][0].isAllowed())
                            Configuration.getInstance().setGameSize(Configuration.GameSizeEnum.FORTY);
                        else
                            StageManager.getInstance().getGame().getPlatformResolver().requestPurchase(CheersGdxGame.productID_fullVersion);
                        break;
                    case 5:
                        if(getScreen().getButtons()[i][0].isAllowed())
                            Configuration.getInstance().setGameSize(Configuration.GameSizeEnum.FIFTY);
                        else
                            StageManager.getInstance().getGame().getPlatformResolver().requestPurchase(CheersGdxGame.productID_fullVersion);
                        break;
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
                    case 6:
                        Configuration.getInstance().setGameType(Configuration.GameTypeEnum.DOGFIGHT);
                        Gdx.input.vibrate(10);
                        break;
                    case 7:
                        if(getScreen().getButtons()[i][0].isAllowed())
                            if(Configuration.getInstance().enabledPlayers() > 2 && Configuration.getInstance().enabledPlayers() % 2 == 0) {
                                Configuration.getInstance().setGameType(Configuration.GameTypeEnum.TEAMOFTWO_VS_TEAMOFTWO);
                                Gdx.input.vibrate(10);
                            }
                        else
                            StageManager.getInstance().getGame().getPlatformResolver().requestPurchase(CheersGdxGame.productID_fullVersion);
                        break;
                    case 8:
                        if(Configuration.getInstance().enabledPlayers() > 2) {
                            Configuration.getInstance().setGameType(Configuration.GameTypeEnum.TEAM_VS_TEAM);
                            Gdx.input.vibrate(10);
                        }
                        break;
                }

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
