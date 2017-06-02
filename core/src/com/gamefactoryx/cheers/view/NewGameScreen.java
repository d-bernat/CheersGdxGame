package com.gamefactoryx.cheers.view;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.gamefactoryx.cheers.tool.Configuration;
import com.gamefactoryx.cheers.tool.Orientation;
import com.gamefactoryx.cheers.tool.Resolution;

/**
 * Created by bernat on 28.04.2017.
 */
@SuppressWarnings("DefaultFileTemplate")
public class NewGameScreen extends AbstractScreen {



    @Override
    protected void initSprites() {
        setLandscapeSprite(new Sprite(new Texture(Configuration.getLanguage() + "/newGameScreen/NewGameScreenLandscape.png")));
        setPortraitSprite(new Sprite(new Texture(Configuration.getLanguage() + "/newGameScreen/NewGameScreenPortrait.png")));
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
        setButtons(new Sprite[3][2]);

        getButtons()[0][0] = new Sprite(new Texture(Configuration.getLanguage() + "/newGameScreen/sekt_button_busdriving.png"));
        getButtons()[0][1] = new Sprite(new Texture(Configuration.getLanguage() + "/newGameScreen/sekt_button_busdriving.png"));
        getButtons()[1][0] = new Sprite(new Texture(Configuration.getLanguage() + "/newGameScreen/sekt_button_ididnot_clicked.png"));
        getButtons()[1][1] = new Sprite(new Texture(Configuration.getLanguage() + "/newGameScreen/sekt_button_ididnot_clicked.png"));
        getButtons()[2][0] = new Sprite(new Texture(Configuration.getLanguage() + "/newGameScreen/sekt_button_kingscup_clicked.png"));
        getButtons()[2][1] = new Sprite(new Texture(Configuration.getLanguage() + "/newGameScreen/sekt_button_kingscup_clicked.png"));

        for (int i = 0; i < getCountOfButtons(); i++)
            for (int j = 0; j < 2; j++) {
                getButtons()[i][j].setSize(Resolution.getGameWorldWidthPortrait() * 0.9f,
                        Resolution.getGameWorldHeightPortrait() * 0.19f * Resolution.getAspectRatio());
            }
        setClicked(new boolean[getCountOfButtons()]);

    }

    @Override
    protected void drawButtons() {
        float SPACE_BETWEEN_BUTTONS = 1.2f;
        int offset_index = 0;

        float X = Orientation.getOrientation() == Input.Orientation.Landscape ? Resolution.getGameWorldWidthLandscape() : Resolution.getGameWorldWidthPortrait();
        float Y = Orientation.getOrientation() == Input.Orientation.Landscape ? Resolution.getGameWorldHeightLandscape() : Resolution.getGameWorldHeightPortrait();
        float DISTANCE_FROM_UPPER_SCREEN_BOUNDARY = Orientation.getOrientation() == Input.Orientation.Landscape ? 0.45f : 0.3f;
        float DISTANCE_FROM_LEFT_SCREEN_BOUNDARY = Orientation.getOrientation() == Input.Orientation.Landscape ? 0.25f : 0.06f;
        for (int i = 0; i < getCountOfButtons(); i++) {
            if (i == 3)
                offset_index = 0;
            else if (i == 5)
                offset_index = 0;

            float y_offset = offset_index++ * getButtons()[i][CLICKED].getHeight() * SPACE_BETWEEN_BUTTONS;
            int click_index = getClicked()[i] ? CLICKED : FREE;
            getButtons()[i][click_index].setPosition(X * DISTANCE_FROM_LEFT_SCREEN_BOUNDARY,
                    Y - Y * DISTANCE_FROM_UPPER_SCREEN_BOUNDARY - y_offset);
            getButtons()[i][click_index].draw(getSpriteBatch(), 1);
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
    protected void drawCards() {

    }


}
