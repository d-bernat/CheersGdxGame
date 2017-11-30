package com.gamefactoryx.cheersapp.controller;


import com.gamefactoryx.cheersapp.CheersGdxGame;

/**
 * Created by bernat on 28.04.2017.
 */
@SuppressWarnings("DefaultFileTemplate")
final class HallOfFameController extends AbstractController {

    private static int counter;
    HallOfFameController(final com.gamefactoryx.cheersapp.view.AbstractScreen screen){
        super(screen);
        setScreenLock(10);
        com.gamefactoryx.cheersapp.controller.StageManager.getInstance().getGame().getPlatformResolver().requestPurchase(CheersGdxGame.productID_fullVersion);
    }



    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        super.touchUp(screenX, screenY, pointer, button);
        return true;
    }

}
