package com.gamefactoryx.cheersapp.view;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.gamefactoryx.cheersapp.model.SplashScreenModel;
import com.gamefactoryx.cheersapp.tool.Resolution;

/**
 * Created by bernat on 28.04.2017.
 */
@SuppressWarnings("DefaultFileTemplate")
public class SplashScreen extends com.gamefactoryx.cheersapp.view.AbstractScreen {

    private float X, Y;

    @Override
    public void show() {
        super.show();
        SplashScreenModel.getInstance().setLaterLogoDown(3000);
    }

    @Override
    protected void initSprites() {
        setLandscapeSprite(new Sprite(new Texture("common/screen2_landscape_loading.png")));
        setPortraitSprite(new Sprite(new Texture("common/screen2_portrait_loading.png")));
        getLandscapeSprite().setSize(com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldWidthLandscape(), com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightLandscape());
        getPortraitSprite().setSize(com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldWidthPortrait(), com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightPortrait());
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        if (com.gamefactoryx.cheersapp.tool.Orientation.getOrientation() == Input.Orientation.Portrait) {
            X = com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldWidthPortrait();
            Y = com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightPortrait();
            getLogo().setSize(X * 1.5f, X * 1.5f
                    * Resolution.getAspectRatio());

        } else {
            X = com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldWidthLandscape();
            Y = com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightLandscape();
            getLogo().setSize(X, X
                    * Resolution.getAspectRatio());

        }
        getLogo().setPosition(X * 0.5f - getLogo().getWidth() * 0.5f, Y * 0.5f - getLogo().getHeight() * 0.5f);
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
        setLogo(new Sprite(new Texture("common/screen1_landscape_loading.jpg")));

    }

    @Override
    protected void drawLogo() {
        if(SplashScreenModel.getInstance().isLogoUp())
            getLogo().draw(getSpriteBatch(),1);

    }

    @Override
    protected void initCards() {

    }

    @Override
    protected void initLoadingSprite() {

    }

    @Override
    protected void drawLoadingSprite() {

    }

    @Override
    protected void drawCards() {

    }


}
