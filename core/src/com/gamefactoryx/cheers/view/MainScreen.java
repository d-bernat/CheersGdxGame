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
public class MainScreen extends AbstractScreen {



    @Override
    protected void initSprites() {
        setLandscapeSprite(new Sprite(new Texture("common/Landscapescreen.png")));
        setPortraitSprite(new Sprite(new Texture("common/Portraitscreen.png")));
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
        setButtons(new Sprite[8][2]);

        getButtons()[0][0] = new Sprite(new Texture(Configuration.getLanguage() + "/mainScreen/sekt_button_clicked_memory.png"));
        getButtons()[0][1] = new Sprite(new Texture(Configuration.getLanguage() + "/mainScreen/sekt_button_clicked_memory_white.png"));
        getButtons()[1][0] = new Sprite(new Texture(Configuration.getLanguage() + "/mainScreen/sekt_button_clicked_settings.png"));
        getButtons()[1][1] = new Sprite(new Texture(Configuration.getLanguage() + "/mainScreen/sekt_button_clicked_settings_white.png"));
        getButtons()[2][0] = new Sprite(new Texture(Configuration.getLanguage() + "/mainScreen/sekt_button_clicked_hall_of_fame.png"));
        getButtons()[2][1] = new Sprite(new Texture(Configuration.getLanguage() + "/mainScreen/sekt_button_clicked_hall_of_fame_white.png"));
        getButtons()[3][0] = new Sprite(new Texture(Configuration.getLanguage() + "/mainScreen/sekt_button_clicked_new_game.png"));
        getButtons()[3][1] = new Sprite(new Texture(Configuration.getLanguage() + "/mainScreen/sekt_button_clicked_new_game_white.png"));
        getButtons()[4][0] = new Sprite(new Texture(Configuration.getLanguage() + "/mainScreen/sekt_button_clicked_add_task.png"));
        getButtons()[4][1] = new Sprite(new Texture(Configuration.getLanguage() + "/mainScreen/sekt_button_clicked_add_task_white.png"));
        switch (Configuration.getLanguage()) {
            case EN:
                getButtons()[5][0] = new Sprite(new Texture("common/button_clicked_sk_lang.png"));
                getButtons()[5][1] = new Sprite(new Texture("common/button_clicked_sk_lang.png"));
                break;
            case DE:
                getButtons()[5][0] = new Sprite(new Texture("common/button_clicked_eng_lang.png"));
                getButtons()[5][1] = new Sprite(new Texture("common/button_clicked_eng_lang.png"));
                break;
            case SK:
                getButtons()[5][0] = new Sprite(new Texture("common/button_clicked_de_lang.png"));
                getButtons()[5][1] = new Sprite(new Texture("common/button_clicked_de_lang.png"));
                break;
        }
        getButtons()[6][0] = new Sprite(new Texture("common/Facebook_icon.png"));
        getButtons()[6][1] = new Sprite(new Texture("common/Facebook_icon.png"));
        getButtons()[7][0] = new Sprite(new Texture("common/Twitter_icon.png"));
        getButtons()[7][1] = new Sprite(new Texture("common/Twitter_icon.png"));


        for (int i = 0; i < getCountOfButtons(); i++)
            for (int j = 0; j < 2; j++) {
                if(i < 5)
                    getButtons()[i][j].setSize(Resolution.getGameWorldWidthPortrait() * 0.2f,
                        Resolution.getGameWorldHeightPortrait() * 0.2f * Resolution.getAspectRatio());
                else
                    getButtons()[i][j].setSize(Resolution.getGameWorldWidthPortrait() * 0.13f,
                            Resolution.getGameWorldHeightPortrait() * 0.13f * Resolution.getAspectRatio());
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
        float DISTANCE_FROM_RIGHT_SCREEN_BOUNDARY = Orientation.getOrientation() == Input.Orientation.Landscape ? 0.03f : 0.06f;
        float DISTANCE_FROM_LEFT_SCREEN_BOUNDARY = Orientation.getOrientation() == Input.Orientation.Landscape ? 0.03f : 0.06f;
        for (int i = 0; i < getCountOfButtons(); i++) {
            if (i == 3)
                offset_index = 0;
            else if (i == 5)
                offset_index = 0;

            float y_offset = offset_index++ * getButtons()[i][CLICKED].getHeight() * SPACE_BETWEEN_BUTTONS;
            int click_index = getClicked()[i] ? CLICKED : FREE;
            if (i < 3)
                getButtons()[i][click_index].setPosition(X * DISTANCE_FROM_LEFT_SCREEN_BOUNDARY,
                        Y - Y * DISTANCE_FROM_UPPER_SCREEN_BOUNDARY - y_offset);
            else if (i >= 3 && i < 5)
                getButtons()[i][click_index].setPosition(X * DISTANCE_FROM_LEFT_SCREEN_BOUNDARY + getButtons()[i][0].getWidth() * SPACE_BETWEEN_BUTTONS,
                        Y - Y * DISTANCE_FROM_UPPER_SCREEN_BOUNDARY - getButtons()[i][0].getHeight() * 0.5f - y_offset);
            else
                getButtons()[i][click_index].setPosition(X * (1.0f - DISTANCE_FROM_RIGHT_SCREEN_BOUNDARY) - getButtons()[i][0].getWidth(),
                        Y - Y * DISTANCE_FROM_UPPER_SCREEN_BOUNDARY / 2.5f - y_offset);
            getButtons()[i][click_index].draw(getSpriteBatch(), 1);
        }
    }

    @Override
    protected void initLogo(){
        //setLogo(new Sprite(new Texture("common/CheersLogo.png")));
        //setLogo(new Sprite(new Texture("common/cheerslogo2.png")));
    }

    @Override
    protected void drawLogo() {
        /*float DISTANCE_FROM_BOTTOM_SCREEN_BOUNDARY = Orientation.getOrientation() == Input.Orientation.Landscape ? 0.15f : 0.15f;
        float DISTANCE_FROM_LEFT_SCREEN_BOUNDARY = Orientation.getOrientation() == Input.Orientation.Landscape ? 0.30f : 0.12f;
        float ZOOM_X = Orientation.getOrientation() == Input.Orientation.Landscape ? 0.42f : 0.76f;
        float ZOOM_Y = Orientation.getOrientation() == Input.Orientation.Landscape ? 0.7f : 0.35f;
        float X = Orientation.getOrientation() == Input.Orientation.Landscape ? Resolution.getGameWorldWidthLandscape() : Resolution.getGameWorldWidthPortrait();
        float Y = Orientation.getOrientation() == Input.Orientation.Landscape ? Resolution.getGameWorldHeightLandscape() : Resolution.getGameWorldHeightPortrait();
        getLogo().setSize(X * ZOOM_X, Y * ZOOM_Y);
        getLogo().setPosition(X * DISTANCE_FROM_LEFT_SCREEN_BOUNDARY, Y * DISTANCE_FROM_BOTTOM_SCREEN_BOUNDARY);
        getLogo().draw(getSpriteBatch(), 1.0f);*/
    }

    @Override
    protected void initCards() {

    }

    @Override
    protected void drawCards() {

    }


}
