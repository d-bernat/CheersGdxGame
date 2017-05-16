package com.gamefactoryx.cheers.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.gamefactoryx.cheers.model.Configuration;
import com.gamefactoryx.cheers.tool.Resolution;

/**
 * Created by bernat on 16.05.2017.
 */
public class BusDrivingScreen extends AbstractScreen {

    @Override
    protected void initSprites() {
        setLandscapeSprite(new Sprite(new Texture(Configuration.getLanguage() + "/Busdrivingscreen/Busfahrenscreen-landscape.png")));
        setPortraitSprite(new Sprite(new Texture(Configuration.getLanguage() + "/Busdrivingscreen/Busfahrenscreen.png")));
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
}
