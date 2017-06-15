package com.gamefactoryx.cheers.view;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.gamefactoryx.cheers.tool.Card;
import com.gamefactoryx.cheers.tool.Configuration;
import com.gamefactoryx.cheers.tool.Orientation;
import com.gamefactoryx.cheers.tool.Resolution;

/**
 * Created by bernat on 28.04.2017.
 */
@SuppressWarnings("DefaultFileTemplate")
public class SplashScreen extends AbstractScreen {


    @Override
    public void show() {
        super.show();
        Card.getInstance();
    }

    @Override
    protected void initSprites() {
        setLandscapeSprite(new Sprite(new Texture("common/screen2_landscape_loading.png")));
        setPortraitSprite(new Sprite(new Texture("common/screen2_portrait_loading.png")));
        getLandscapeSprite().setSize(Resolution.getGameWorldWidthLandscape(), Resolution.getGameWorldHeightLandscape());
        getPortraitSprite().setSize(Resolution.getGameWorldWidthPortrait(), Resolution.getGameWorldHeightPortrait());
    }


    @Override
    protected void drawText() {

    }

    @Override
    protected void initTextBox() {

    }

    @Override
    protected void initButtons() {
    }

    @Override
    protected void drawButtons() {
    }

    @Override
    protected void initLogo() {
    }

    @Override
    protected void drawLogo() {
    }

    @Override
    protected void initCards() {

    }

    @Override
    protected void drawCards() {

    }


}
