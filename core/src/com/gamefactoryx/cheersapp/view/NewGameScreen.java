package com.gamefactoryx.cheersapp.view;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.gamefactoryx.cheersapp.tool.Configuration;
import com.gamefactoryx.cheersapp.tool.Orientation;

/**
 * Created by bernat on 28.04.2017.
 */
@SuppressWarnings("DefaultFileTemplate")
public class NewGameScreen extends com.gamefactoryx.cheersapp.view.AbstractScreen {


    @Override
    protected void initSprites() {
        setLandscapeSprite(new Sprite(new Texture(Configuration.getLanguage() + "/newGameScreen/NewGameScreenLandscape.png")));
        setPortraitSprite(new Sprite(new Texture(Configuration.getLanguage() + "/newGameScreen/NewGameScreenPortrait.png")));
        getLandscapeSprite().setSize(com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldWidthLandscape(), com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightLandscape());
        getPortraitSprite().setSize(com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldWidthPortrait(), com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightPortrait());
    }

    @Override
    public void show(){
        super.show();
        initBackButton();
    }

    @Override
    protected void drawText() {

    }

    @Override
    protected void initTextBox() {

    }

    @Override
    protected void initButtons() {
        setButtons(new Sprite[4][2]);

        getButtons()[0][0] = new Sprite(new Texture(Configuration.getLanguage() + "/newGameScreen/sekt_button_kongos_drink_clicked.png"));
        getButtons()[0][1] = new Sprite(new Texture(Configuration.getLanguage() + "/newGameScreen/sekt_button_kongos_drink_clicked_white.png"));
        getButtons()[1][0] = new Sprite(new Texture(Configuration.getLanguage() + "/newGameScreen/sekt_button_busdriving.png"));
        getButtons()[1][1] = new Sprite(new Texture(Configuration.getLanguage() + "/newGameScreen/sekt_button_busdriving_white.png"));
        getButtons()[2][0] = new Sprite(new Texture(Configuration.getLanguage() + "/newGameScreen/sekt_button_ididnot_clicked.png"));
        getButtons()[2][1] = new Sprite(new Texture(Configuration.getLanguage() + "/newGameScreen/sekt_button_ididnot_clicked_white.png"));
        getButtons()[3][0] = new Sprite(new Texture(Configuration.getLanguage() + "/newGameScreen/sekt_button_kingscup_clicked.png"));
        getButtons()[3][1] = new Sprite(new Texture(Configuration.getLanguage() + "/newGameScreen/sekt_button_kingscup_clicked_white.png"));

        for (int i = 0; i < getCountOfButtons(); i++)
            for (int j = 0; j < 2; j++) {
                getButtons()[i][j].setSize(com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldWidthPortrait() * 0.9f,
                        com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightPortrait() * 0.19f * com.gamefactoryx.cheersapp.tool.Resolution.getAspectRatio());
            }
        setClicked(new boolean[getCountOfButtons()]);

    }

    @Override
    protected void drawButtons() {
        float SPACE_BETWEEN_BUTTONS = 1.2f;
        int y_offset = 0;
        Input.Orientation orientation = Orientation.getOrientation();
        float X = orientation == Input.Orientation.Landscape ? com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldWidthLandscape() : com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldWidthPortrait();
        float Y = orientation == Input.Orientation.Landscape ? com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightLandscape() : com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightPortrait();
        float DISTANCE_FROM_UPPER_SCREEN_BOUNDARY = orientation == Input.Orientation.Landscape ? 0.45f : 0.3f;
        float DISTANCE_FROM_LEFT_SCREEN_BOUNDARY = orientation == Input.Orientation.Landscape ? 0.25f : 0.06f;
        for (int i = 0; i < getCountOfButtons(); i++) {
            float dy = y_offset++ * getButtons()[i][CLICKED].getHeight() * SPACE_BETWEEN_BUTTONS;
            int click_index = getClicked()[i] ? CLICKED : FREE;
            getButtons()[i][click_index].setPosition(X * DISTANCE_FROM_LEFT_SCREEN_BOUNDARY,
                    Y - Y * DISTANCE_FROM_UPPER_SCREEN_BOUNDARY - dy);
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
    protected void initLoadingSprite() {

    }

    @Override
    protected void drawLoadingSprite() {

    }

    @Override
    protected void drawCards() {

    }


}
