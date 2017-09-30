package com.gamefactoryx.cheers.controller.kongosdrink;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.gamefactoryx.cheers.controller.AbstractController;
import com.gamefactoryx.cheers.controller.StageEnum;
import com.gamefactoryx.cheers.controller.StageManager;
import com.gamefactoryx.cheers.model.PlayerNameCache;
import com.gamefactoryx.cheers.model.Subject;
import com.gamefactoryx.cheers.model.kongosdrink.KongosDrinkPhase0Model;
import com.gamefactoryx.cheers.tool.Resolution;
import com.gamefactoryx.cheers.tool.kongosdrink.Configuration;
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

        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        if (!super.touchUp(screenX, screenY, pointer, button))
            return true;

        if (screenX >= getScreen().getButtons()[6][0].getX() &&
                screenX <= getScreen().getButtons()[6][0].getX() + getScreen().getButtons()[6][0].getWidth() &&
                Resolution.getGameWorldHeightPortrait() - screenY >= getScreen().getButtons()[6][0].getY() &&
                Resolution.getGameWorldHeightPortrait() - screenY <= getScreen().getButtons()[6][0].getY() + getScreen().getButtons()[6][0].getHeight()) {
            StageManager.getInstance().showStage(StageEnum.KONGOS_DRINK_ZERO_STAGE);
            Gdx.input.vibrate(10);
            return true;
        }

        for(int i = 0; i < 6; ++i ){
            if (screenX >= getScreen().getButtons()[i][0].getX() &&
                    screenX <= getScreen().getButtons()[i][0].getX() + getScreen().getButtons()[i][0].getWidth() &&
                    Resolution.getGameWorldHeightPortrait() - screenY >= getScreen().getButtons()[i][0].getY() &&
                    Resolution.getGameWorldHeightPortrait() - screenY <= getScreen().getButtons()[i][0].getY() + getScreen().getButtons()[6][0].getHeight()) {
                switch(i){
                    case 0: Configuration.setGameSize(Configuration.GameSizeEnum.TEN);break;
                    case 1: Configuration.setGameSize(Configuration.GameSizeEnum.FIFTEEN);break;
                    case 2: Configuration.setGameSize(Configuration.GameSizeEnum.TWENTY);break;
                    case 3: Configuration.setGameSize(Configuration.GameSizeEnum.THRITY);break;
                    case 4: Configuration.setGameSize(Configuration.GameSizeEnum.FORTY);break;
                    case 5: Configuration.setGameSize(Configuration.GameSizeEnum.FIFTY);break;
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
