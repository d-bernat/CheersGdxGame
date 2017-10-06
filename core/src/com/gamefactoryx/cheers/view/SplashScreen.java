package com.gamefactoryx.cheers.view;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.gamefactoryx.cheers.model.SplashScreenModel;
import com.gamefactoryx.cheers.tool.Card;
import com.gamefactoryx.cheers.tool.Orientation;
import com.gamefactoryx.cheers.tool.Resolution;

/**
 * Created by bernat on 28.04.2017.
 */
@SuppressWarnings("DefaultFileTemplate")
public class SplashScreen extends AbstractScreen {

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
        getLandscapeSprite().setSize(Resolution.getGameWorldWidthLandscape(), Resolution.getGameWorldHeightLandscape());
        getPortraitSprite().setSize(Resolution.getGameWorldWidthPortrait(), Resolution.getGameWorldHeightPortrait());
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        if (Orientation.getOrientation() == Input.Orientation.Portrait) {
            X = Resolution.getGameWorldWidthPortrait();
            Y = Resolution.getGameWorldHeightPortrait();
            getLogo().setSize(X * 1.5f, X * 1.5f
                    * getLogo().getHeight()/getLogo().getWidth());

        } else {
            X = Resolution.getGameWorldWidthLandscape();
            Y = Resolution.getGameWorldHeightLandscape();
            getLogo().setSize(X, X
                    * getLogo().getWidth()/getLogo().getHeight());

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
