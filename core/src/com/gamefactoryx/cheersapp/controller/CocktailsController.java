package com.gamefactoryx.cheersapp.controller;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.gamefactoryx.cheersapp.tool.Configuration;
import com.gamefactoryx.cheersapp.tool.Resolution;

/**
 * Created by bernat on 16.05.2017.
 */
public class CocktailsController extends AbstractController {

    private com.gamefactoryx.cheersapp.model.CocktailsModel model;
    private int touchPos;


    public CocktailsController(final com.gamefactoryx.cheersapp.view.AbstractScreen screen) {
        super(screen);
        setScreenLock(1);
        model = com.gamefactoryx.cheersapp.model.CocktailsModel.getInstance().getNewInstance();
        StageManager.getInstance().getGame().getAdMobRequestHandler().showAds(true);
    }


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        touchPos = screenX;
        super.touchDown(screenX, screenY, pointer, button);

        if (com.gamefactoryx.cheersapp.model.CocktailsModel.getInstance().getCocktailToDisplay() > -1) {
            int i = getScreen().getCountOfButtons() - 1;
            float y = Configuration.isPremium() ? getScreen().getButtons()[i][0].getY(): getScreen().getButtons()[i][0].getY() + 50;
            getScreen().getClicked()[i] = (screenX >= getScreen().getButtons()[i][0].getX() &&
                    screenX <= getScreen().getButtons()[i][0].getX() + getScreen().getButtons()[i][0].getWidth() &&
                    com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightPortrait() - screenY >= y &&
                    com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightPortrait() - screenY <= y + getScreen().getButtons()[i][0].getHeight() &&
                    com.gamefactoryx.cheersapp.tool.Orientation.getOrientation() == Input.Orientation.Portrait
                    ||
                    screenX >= getScreen().getButtons()[i][0].getX() &&
                            screenX <= getScreen().getButtons()[i][0].getX() + getScreen().getButtons()[i][0].getWidth() &&
                            com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightLandscape() - screenY >= y &&
                            com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightLandscape() - screenY <= y + getScreen().getButtons()[i][0].getHeight() &&
                            com.gamefactoryx.cheersapp.tool.Orientation.getOrientation() == Input.Orientation.Landscape);
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {



        if (!super.touchUp(screenX, screenY, pointer, button))
            return true;



        int distance = screenX - touchPos;
        touchPos = 0;

        int page = model.getPage();
        int maxCocktailsProPage = 4;
        //for (int i = 0; i < CocktailsModel.cocktailNames.length; i++) {
        if (model.getCocktailToDisplay() git checkou== -1) {
            getScreen().getClicked()[getScreen().getCountOfButtons() - 1] = false;
            for (int i = (page - 1) * maxCocktailsProPage; i < maxCocktailsProPage + (page - 1) * maxCocktailsProPage; i++) {
                if (i >= 0 && i < com.gamefactoryx.cheersapp.model.CocktailsModel.cocktailNames.length) {
                    float y = Configuration.isPremium() ? getScreen().getButtons()[i][0].getY(): getScreen().getButtons()[i][0].getY() + 50;
                    if (screenX >= getScreen().getButtons()[i][0].getX() &&
                            screenX <= getScreen().getButtons()[i][0].getX() + getScreen().getButtons()[i][0].getWidth() &&
                            com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightPortrait() - screenY >= y &&
                            com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightPortrait() - screenY <= y + getScreen().getButtons()[i][0].getHeight()) {
                        model.setCocktailToDisplay(i);

                        Gdx.input.vibrate(20);
                        return true;
                    }
                }
            }
        } else {
            if(getScreen().getClicked()[getScreen().getCountOfButtons() - 1]) {
                model.setCocktailToDisplay(-1);
                getScreen().getClicked()[getScreen().getCountOfButtons() - 1] = false;
            }
            return true;
        }

        if (com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightPortrait() - screenY <= 350) {
            return true;
        }


        if (distance <= 200) {
            if (!model.isLastPage())
                model.setPage(model.getPage() + 1);

        } else if (distance >= -200) {
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
            case Input.Keys.BACK:
                StageManager.getInstance().showLastStage();
                return false;
        }
        return true;
    }


}
