package com.gamefactoryx.cheers.view.kongosdrink;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.gamefactoryx.cheers.model.Subject;
import com.gamefactoryx.cheers.model.kongosdrink.KongosDrinkPhase0Model;
import com.gamefactoryx.cheers.tool.FontHelper;
import com.gamefactoryx.cheers.tool.Orientation;
import com.gamefactoryx.cheers.tool.Resolution;
import com.gamefactoryx.cheers.tool.kongosdrink.Configuration;
import com.gamefactoryx.cheers.view.AbstractScreen;


/**
 * Created by bernat on 16.05.2017.
 */
public class KongosDrinkSetupScreen extends AbstractScreen {


    private float X, Y;

    public KongosDrinkSetupScreen() {
        super();
    }


    @Override
    public void show() {
        super.show();
        initBackButton();
    }


    @Override
    protected void initSprites() {
        setLandscapeSprite(new Sprite(new Texture(com.gamefactoryx.cheers.tool.Configuration.getLanguage() + "/kongosdrink/kongosridescreen.png")));
        setPortraitSprite(new Sprite(new Texture(com.gamefactoryx.cheers.tool.Configuration.getLanguage() + "/kongosdrink/kongosridescreen.png")));
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
    }

    @Override
    protected void drawText() {
    }


    @Override
    protected void initTextBox() {
    }

    @Override
    protected void initButtons() {
        setButtons(new Sprite[7][2]);
        getButtons()[0][0] = new Sprite(new Texture("common/kongos_drink/10.png"));
        getButtons()[0][1] = new Sprite(new Texture("common/kongos_drink/10.png"));
        getButtons()[1][0] = new Sprite(new Texture("common/kongos_drink/15.png"));
        getButtons()[1][1] = new Sprite(new Texture("common/kongos_drink/15.png"));
        getButtons()[2][0] = new Sprite(new Texture("common/kongos_drink/20.png"));
        getButtons()[2][1] = new Sprite(new Texture("common/kongos_drink/20.png"));
        getButtons()[3][0] = new Sprite(new Texture("common/kongos_drink/30.png"));
        getButtons()[3][1] = new Sprite(new Texture("common/kongos_drink/30.png"));
        getButtons()[4][0] = new Sprite(new Texture("common/kongos_drink/40.png"));
        getButtons()[4][1] = new Sprite(new Texture("common/kongos_drink/40.png"));
        getButtons()[5][0] = new Sprite(new Texture("common/kongos_drink/50.png"));
        getButtons()[5][1] = new Sprite(new Texture("common/kongos_drink/50.png"));

        getButtons()[6][0] = new Sprite(new Texture("common/continue.png"));
        getButtons()[6][1] = new Sprite(new Texture("common/continue.png"));
        getButtons()[6][0].setSize(Resolution.getGameWorldWidthPortrait() * 0.2f, Resolution.getGameWorldHeightPortrait() * Resolution.getAspectRatio() * 0.2f);
        getButtons()[6][1].setSize(Resolution.getGameWorldWidthPortrait() * 0.2f, Resolution.getGameWorldHeightPortrait() * Resolution.getAspectRatio() * 0.2f);

        setClicked(new boolean[getCountOfButtons()]);
    }

    @Override
    protected void drawButtons() {
        float PORTRAIT_DISTANCE_FROM_BOTTOM = 0.76f;
        float y_offset = 0, x_offset = 0;
        int x_index = 0;

        for (int i = 0; i < getButtons().length - 1; i++) {
            if (i == 3) {
                x_index = 0;
                y_offset = getButtons()[i][0].getHeight() * 1.3f;
            }
            x_offset = getButtons()[i][0].getWidth() * 1.3f * x_index++;
            getButtons()[i][0].setPosition(X * 0.5f - getButtons()[i][0].getWidth() * 1.3f * 3 * 0.5f   + x_offset, Y * 0.75f - y_offset);
            float alpha = 1.0f;
            if( i == 0 ) alpha = Configuration.getGameSize().getValue() == 10 ? 1.0f : 0.5f;
            else if( i == 1 ) alpha = Configuration.getGameSize().getValue() == 15 ? 1.0f : 0.5f;
            else  alpha = Configuration.getGameSize().getValue()  == (i) * 10 ? 1.0f : 0.5f;

            getButtons()[i][0].draw(getSpriteBatch(), alpha);

        }

        getButtons()[6][0].setPosition(X * 0.75f, Y * 0.02f);
        getButtons()[6][0].draw(getSpriteBatch(), 1.0f);

    }

    @Override
    protected void initLogo() {
        setLogo(new Sprite(new Texture("common/logo.png")));
        float logoRatio = getLogo().getHeight() / getLogo().getWidth();
        getLogo().setSize(Resolution.getGameWorldWidthPortrait() * 0.4f, Resolution.getGameWorldHeightPortrait() * Resolution.getAspectRatio() * 0.4f * logoRatio);
        getLogo().setPosition(Resolution.getGameWorldWidthPortrait() / 3.65f, Resolution.getGameWorldHeightPortrait() / 7f);
    }

    @Override
    protected void drawLogo() {
        getLogo().draw(getSpriteBatch(), 75.0f);
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
