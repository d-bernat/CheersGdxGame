package com.gamefactoryx.cheers.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.gamefactoryx.cheers.tool.Card;
import com.gamefactoryx.cheers.tool.Orientation;
import com.gamefactoryx.cheers.tool.Resolution;
import com.gamefactoryx.cheers.view.AbstractScreen;

/**
 * Created by bernat on 28.04.2017.
 */
@SuppressWarnings("DefaultFileTemplate")
final class SplashStageController extends AbstractController {

    SplashStageController(final AbstractScreen screen){
        super(screen);
        setScreenLock(10);
    }



    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        Music mp3Music = Gdx.audio.newMusic(Gdx.files.internal("common/cheers_musik.mp3"));
        mp3Music.play();
        StageManager.getInstance().showStage(StageEnum.MAIN_STAGE);
        return true;
    }


}
