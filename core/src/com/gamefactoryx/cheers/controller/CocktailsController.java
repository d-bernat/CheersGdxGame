package com.gamefactoryx.cheers.controller;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.gamefactoryx.cheers.model.CocktailsModel;
import com.gamefactoryx.cheers.model.PlayerNameCache;
import com.gamefactoryx.cheers.model.Subject;
import com.gamefactoryx.cheers.model.kongosdrink.KongosDrinkPhase0Model;
import com.gamefactoryx.cheers.tool.Resolution;
import com.gamefactoryx.cheers.tool.kongosdrink.Configuration;
import com.gamefactoryx.cheers.view.AbstractScreen;

/**
 * Created by bernat on 16.05.2017.
 */
public class CocktailsController extends AbstractController {

    private CocktailsModel model;
    private int touchPos;


    public CocktailsController(final AbstractScreen screen) {
        super(screen);
        setScreenLock(1);
        model = CocktailsModel.getInstance().getNewInstance();
    }


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        touchPos = screenX;
        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if(Resolution.getGameWorldHeightPortrait() - screenY <= 350) {
            return super.touchUp(screenX, screenY, pointer, button);
        }


        int distance = screenX - touchPos;
        touchPos = 0;

        if (!super.touchUp(screenX, screenY, pointer, button))
            return true;

        int page = model.getPage();
        int maxCocktailsProPage = 4;
        //for (int i = 0; i < CocktailsModel.cocktailNames.length; i++) {
        if (model.getCocktailToDisplay() == -1) {
            for (int i = (page - 1) * maxCocktailsProPage; i < maxCocktailsProPage + (page - 1) * maxCocktailsProPage; i++) {
                if (i >= 0 && i < CocktailsModel.cocktailNames.length) {
                    if (screenX >= getScreen().getButtons()[i][0].getX() &&
                            screenX <= getScreen().getButtons()[i][0].getX() + getScreen().getButtons()[i][0].getWidth() &&
                            Resolution.getGameWorldHeightPortrait() - screenY >= getScreen().getButtons()[i][0].getY() &&
                            Resolution.getGameWorldHeightPortrait() - screenY <= getScreen().getButtons()[i][0].getY() + getScreen().getButtons()[i][0].getHeight()) {
                        model.setCocktailToDisplay(i);

                        Gdx.input.vibrate(20);
                        return true;
                    }
                }
            }
        }else{
            model.setCocktailToDisplay(-1);
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
