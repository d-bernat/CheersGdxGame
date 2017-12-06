package com.gamefactoryx.cheersapp.controller;

import com.gamefactoryx.cheersapp.tool.Configuration;
import com.gamefactoryx.cheersapp.view.AbstractScreen;

/**
 * Created by bernat on 28.04.2017.
 */
@SuppressWarnings("DefaultFileTemplate")
final class SplashStageController extends com.gamefactoryx.cheersapp.controller.AbstractController {

    SplashStageController(final AbstractScreen screen) {
        super(screen);
        setScreenLock(10);
        StageManager.getInstance().getGame().getAdMobRequestHandler().showAds(false);
        Configuration.playMusic();
    }


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        com.gamefactoryx.cheersapp.controller.StageManager.getInstance().showStage(com.gamefactoryx.cheersapp.controller.StageEnum.MAIN_STAGE);
        return true;
    }

}
