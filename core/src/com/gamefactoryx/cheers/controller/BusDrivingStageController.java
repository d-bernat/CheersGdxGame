package com.gamefactoryx.cheers.controller;


import com.gamefactoryx.cheers.model.BusDrivingModel;
import com.gamefactoryx.cheers.view.AbstractScreen;

/**
 * Created by bernat on 16.05.2017.
 */
public class BusDrivingStageController extends AbstractController {

    private  BusDrivingModel model;
    private int index;

    BusDrivingStageController(final AbstractScreen screen) {
        super(screen);
        model = BusDrivingModel.getInstance();
        //getScreen().setDataModel(BusDrivingModel.getInstance());
        //model = ((BusDrivingModel)getScreen().getDataModel());
//        model.getCroupier().shuffle();

    }
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        model.getPlayer().addCard(model.getCroupier().getCard());
        return true;
    }

}
