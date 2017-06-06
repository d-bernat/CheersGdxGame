package com.gamefactoryx.cheers.controller;


import com.gamefactoryx.cheers.CheersGdxGame;
import com.gamefactoryx.cheers.model.BusDrivingPhase2Model;
import com.gamefactoryx.cheers.model.BusDrivingPhase3Model;
import com.gamefactoryx.cheers.model.bus_driving2.Croupier;
import com.gamefactoryx.cheers.model.bus_driving2.Player;
import com.gamefactoryx.cheers.model.bus_driving2.VCard;
import com.gamefactoryx.cheers.tool.CardOrientation;
import com.gamefactoryx.cheers.tool.Resolution;
import com.gamefactoryx.cheers.view.AbstractScreen;

/**
 * Created by bernat on 16.05.2017.
 */
public class BusDrivingStagePhase3Controller extends AbstractController {

    private BusDrivingPhase3Model model;
    private static boolean flag;
    private StringBuilder typedName = new StringBuilder();
    private boolean shift;
    private boolean keyboardOn;
    private VCard activeCard;
    private String tempName;


    public BusDrivingStagePhase3Controller(final AbstractScreen screen) {
        super(screen);
        model = BusDrivingPhase3Model.getNewInstance();
        setScreenLock();
    }


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        return true;
    }


    private void setScreenLock() {
        if (CheersGdxGame.getScreenLock() != null)
            CheersGdxGame.getScreenLock().lock(1);
    }


}
