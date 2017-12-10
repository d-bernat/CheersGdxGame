package com.gamefactoryx.cheersapp.view;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.gamefactoryx.cheersapp.controller.StageManager;
import com.gamefactoryx.cheersapp.tool.Configuration;
import com.gamefactoryx.cheersapp.tool.Orientation;
import com.gamefactoryx.cheersapp.tool.Resolution;

/**
 * Created by bernat on 28.04.2017.
 */
@SuppressWarnings("DefaultFileTemplate")
public class SetupScreen extends com.gamefactoryx.cheersapp.view.AbstractScreen {

    private float X, Y;
    private Sprite settingGroups[] = {
            new Sprite(new Texture(Configuration.getLanguage() + "/settings/musikbox.png")),
            new Sprite(new Texture(Configuration.getLanguage() + "/settings/backbutton.png")),
            new Sprite(new Texture(Configuration.getLanguage() + "/settings/rules.png"))
    };

    @Override
    public void show() {
        super.show();
        initBackButton();
    }

    @Override
    public void render(float delta){
        super.render(delta);
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
            for (Sprite s : settingGroups)
                s.setSize(X * 0.7f, Y * 0.1f);
            for (int i = 0; i < getCountOfButtons(); i++)
                for (int j = 0; j < 2; j++) {
                    getButtons()[i][j].setSize(X * 0.3f,
                            Y * 0.1f * Resolution.getAspectRatio());
                }

        } else {
            for (Sprite s : settingGroups)
                s.setSize(X * 0.5f, Y * 0.22f);

            for (int i = 0; i < getCountOfButtons(); i++)
                for (int j = 0; j < 2; j++) {
                    getButtons()[i][j].setSize(X * 0.2f,
                            Y * 0.22f * Resolution.getAspectRatio());
                }

        }


    }

    @Override
    protected void drawText() {
    /*    getTextBox().setPosition( X * 0.5f - getTextBox().getWidth() * 0.5f, Y * 0.6f);
        getTextBox().draw(getSpriteBatch(), 1.0f);*/
    }


    @Override
    protected void initTextBox() {
        //setTextBox(new Sprite(new Texture(Configuration.getLanguage() + "/settings/musikbox.png")));
    }

    @Override
    protected void initButtons() {
        setButtons(new CheckedButton[6][2]);

        Texture[] tx = {
                new Texture(Configuration.getLanguage() + "/settings/off.png"),
                new Texture(Configuration.getLanguage() + "/settings/on.png"),
        };
        for (int i = 0; i < getCountOfButtons(); i++) {
            getButtons()[i][0] = new CheckedButton(tx[i%2]);
            getButtons()[i][1] = new CheckedButton(tx[i%2]);
        }


        setClicked(new boolean[getCountOfButtons()]);
    }

    @Override
    protected void drawButtons() {

        boolean isPortrait = Orientation.getOrientation() == Input.Orientation.Portrait;
        int offset = 0;
        for (Sprite s : settingGroups) {
            if(isPortrait)
                s.setPosition(X * 0.5f - s.getWidth() * 0.5f, Y * 0.7f - s.getHeight() * 1.25f * offset++);
            else
                s.setPosition(X * 0.5f - s.getWidth() * 0.5f, Y * 0.6f - s.getHeight() * 1.25f * offset++);
            s.draw(getSpriteBatch(), 1.0f);
        }

        int x_offset = 0, y_offset = 0;

        for (int i = 0; i < getCountOfButtons(); i++) {
            if(isPortrait)
                getButtons()[i][0].setPosition(X * 0.5f - getButtons()[0][0].getWidth() * 1.1f * 2 * 0.5f + x_offset * getButtons()[i][0].getWidth() * 1.2f,
                        Y * 0.7f + settingGroups[0].getHeight() * 0.45f - getButtons()[0][0].getHeight() * 0.5f - settingGroups[0].getHeight() * 1.25f * y_offset);
            else
                getButtons()[i][0].setPosition(X * 0.5f - getButtons()[0][0].getWidth() * 1.1f * 2 * 0.5f + x_offset * getButtons()[i][0].getWidth() * 1.2f,
                        Y * 0.6f + settingGroups[0].getHeight() * 0.45f - getButtons()[0][0].getHeight() * 0.5f - settingGroups[0].getHeight() * 1.25f * y_offset);

            if (i == 1) {
                getButtons()[i][0].draw(getSpriteBatch(), Configuration.isPlayMusic() ? 1.0f : 0.5f);
                ++y_offset;
                x_offset = 0;
            } else if (i == 0) {
                getButtons()[i][0].draw(getSpriteBatch(), Configuration.isPlayMusic() ? 0.5f : 1.0f);
                ++x_offset;
            } else if (i == 3) {
                getButtons()[i][0].draw(getSpriteBatch(), Configuration.isShowBackButton() ? 1.0f : 0.5f);
                ++y_offset;
                x_offset = 0;
            } else if (i == 2) {
                getButtons()[i][0].draw(getSpriteBatch(), Configuration.isShowBackButton() ? 0.5f : 1.0f);
                ++x_offset;
            } else if (i == 5)
                getButtons()[i][0].draw(getSpriteBatch(), Configuration.isShowRules() ? 1.0f : 0.5f);
            else if (i == 4) {
                getButtons()[i][0].draw(getSpriteBatch(), Configuration.isShowRules() ? 0.5f : 1.0f);
                ++x_offset;
            }

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
        for (Sprite s : settingGroups)
            s.getTexture().dispose();
        super.dispose();
    }
}
