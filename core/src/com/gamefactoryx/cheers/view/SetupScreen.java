package com.gamefactoryx.cheers.view;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.gamefactoryx.cheers.model.HallOfFameModel;
import com.gamefactoryx.cheers.tool.Configuration;
import com.gamefactoryx.cheers.tool.FontHelper;
import com.gamefactoryx.cheers.tool.Orientation;
import com.gamefactoryx.cheers.tool.Resolution;

import java.util.List;

/**
 * Created by bernat on 28.04.2017.
 */
@SuppressWarnings("DefaultFileTemplate")
public class SetupScreen extends AbstractScreen {

    private float X, Y;

    @Override
    public void show() {
        super.show();
        initBackButton();
    }


    @Override
    protected void initSprites() {
        setLandscapeSprite(new Sprite(new Texture("common/Landscapescreen.png")));
        setPortraitSprite(new Sprite(new Texture("common/Portraitscreen.png")));
        getLandscapeSprite().setSize(Resolution.getGameWorldWidthLandscape(), Resolution.getGameWorldHeightLandscape());
        getPortraitSprite().setSize(Resolution.getGameWorldWidthPortrait(), Resolution.getGameWorldHeightPortrait());

    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        if (Orientation.getOrientation() == Input.Orientation.Portrait) {
            X = Resolution.getGameWorldWidthPortrait();
            Y = Resolution.getGameWorldHeightPortrait();
        } else {
            X = Resolution.getGameWorldWidthLandscape();
            Y = Resolution.getGameWorldHeightLandscape();
        }
        if (Orientation.getOrientation() == Input.Orientation.Portrait) {
            getTextBox().setSize(X * 0.7f, Y * 0.1f);
            for (int i = 0; i < getCountOfButtons(); i++)
                for (int j = 0; j < 2; j++) {
                    getButtons()[i][j].setSize(X * 0.3f,
                            Y * 0.1f * Resolution.getAspectRatio());
                }

        }
        else {
            getTextBox().setSize(X * 0.5f, Y * 0.22f);
            for (int i = 0; i < getCountOfButtons(); i++)
                for (int j = 0; j < 2; j++) {
                    getButtons()[i][j].setSize(X * 0.2f,
                            Y * 0.22f * Resolution.getAspectRatio());
                }

        }



    }

    @Override
    protected void drawText() {
        getTextBox().setPosition( X * 0.5f - getTextBox().getWidth() * 0.5f, Y * 0.6f);
        getTextBox().draw(getSpriteBatch(), 1.0f);
    }


    @Override
    protected void initTextBox() {
        setTextBox(new Sprite(new Texture(Configuration.getLanguage() + "/settings/musikbox.png")));
    }

    @Override
    protected void initButtons() {
        setButtons(new Sprite[2][2]);

        getButtons()[0][0] = new Sprite(new Texture(Configuration.getLanguage() + "/settings/off.png"));
        getButtons()[0][1] = new Sprite(new Texture(Configuration.getLanguage() + "/settings/off.png"));
        getButtons()[1][0] = new Sprite(new Texture(Configuration.getLanguage() + "/settings/on.png"));
        getButtons()[1][1] = new Sprite(new Texture(Configuration.getLanguage() + "/settings/on.png"));

        setClicked(new boolean[getCountOfButtons()]);

    }

    @Override
    protected void drawButtons() {
        for (int i = 0; i < getCountOfButtons(); i++){
            getButtons()[i][0].setPosition(X * 0.5f - getButtons()[0][0].getWidth() * 1.1f * 2 * 0.5f + i * getButtons()[i][0].getWidth() * 1.2f,
                    Y * 0.6f + getTextBox().getHeight() * 0.45f - getButtons()[0][0].getHeight() * 0.5f );
            if( i  == 1 )
                getButtons()[i][0].draw(getSpriteBatch(), Configuration.isPlayMusic() ? 1.0f: 0.5f);
            else
                getButtons()[i][0].draw(getSpriteBatch(), Configuration.isPlayMusic() ? 0.5f: 1.0f);
        }
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
    protected void initLoadingSprite() {

    }

    @Override
    protected void drawLoadingSprite() {

    }

    @Override
    protected void drawCards() {

    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
