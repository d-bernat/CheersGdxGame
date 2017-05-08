package com.gamefactoryx.cheers.view;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.gamefactoryx.cheers.model.Configuration;
import com.gamefactoryx.cheers.tool.Orientation;
import com.gamefactoryx.cheers.tool.Resolution;

/**
 * Created by bernat on 28.04.2017.
 */
@SuppressWarnings("DefaultFileTemplate")
public class MainScreen extends AbstractScreen {


    @Override
    protected void initSprites() {
        setLandscapeSprite(new Sprite(new Texture(Configuration.getLanguage() + "/mainScreen/MainScreenLandscape.png")));
        setPortraitSprite(new Sprite(new Texture(Configuration.getLanguage() + "/mainScreen/MainScreenPortrait.png")));
        getLandscapeSprite().setSize(Resolution.getGameWorldWidthLandscape(), Resolution.getGameWorldHeightLandscape());
        getPortraitSprite().setSize(Resolution.getGameWorldWidthPortrait(), Resolution.getGameWorldHeightPortrait());
    }


    @Override
    protected void drawButtons() {
        int offset_index = 0;
        for (int i = 0; i < getCountOfButtons(); i++) {
            if (i == 3 && Orientation.getOrientation() == Input.Orientation.Landscape)
                offset_index = 0;
            float y_offset = offset_index++ * getButtons()[i][CLICKED].getHeight() * 1.0f;
            int click_index = getClicked()[i] ? CLICKED : FREE;
            if (Orientation.getOrientation() == Input.Orientation.Landscape) {
                if (i < 3)
                    getButtons()[i][click_index].setPosition(Resolution.getGameWorldWidthLandscape() * 0.01f,
                            Resolution.getGameWorldHeightLandscape() - Resolution.getGameWorldHeightLandscape() * 0.35f - y_offset);
                else
                    getButtons()[i][click_index].setPosition(Resolution.getGameWorldWidthLandscape() * 0.01f + getButtons()[i][0].getWidth(),
                            Resolution.getGameWorldHeightLandscape() - Resolution.getGameWorldHeightLandscape() * 0.35f -
                                    y_offset - getButtons()[i][click_index].getHeight() * 0.5f);

            } else {
                getButtons()[i][click_index].setPosition(Resolution.getGameWorldWidthPortrait() * 0.01f,
                        Resolution.getGameWorldHeightPortrait() - Resolution.getGameWorldHeightPortrait() * 0.20f - y_offset);
            }
            getButtons()[i][click_index].draw(getSpriteBatch(), 1);
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
        setButtons(new Sprite[5][2]);

        getButtons()[0][0] = new Sprite(new Texture(Configuration.getLanguage() + "/mainScreen/sekt_button_free_new_game.png"));
        getButtons()[0][1] = new Sprite(new Texture(Configuration.getLanguage() + "/mainScreen/sekt_button_clicked_new_game.png"));
        getButtons()[1][0] = new Sprite(new Texture(Configuration.getLanguage() + "/mainScreen/sekt_button_free_add_task.png"));
        getButtons()[1][1] = new Sprite(new Texture(Configuration.getLanguage() + "/mainScreen/sekt_button_clicked_add_task.png"));
        getButtons()[2][0] = new Sprite(new Texture(Configuration.getLanguage() + "/mainScreen/sekt_button_free_settings.png"));
        getButtons()[2][1] = new Sprite(new Texture(Configuration.getLanguage() + "/mainScreen/sekt_button_clicked_settings.png"));
        getButtons()[3][0] = new Sprite(new Texture(Configuration.getLanguage() + "/mainScreen/sekt_button_free_help.png"));
        getButtons()[3][1] = new Sprite(new Texture(Configuration.getLanguage() + "/mainScreen/sekt_button_clicked_help.png"));
        getButtons()[4][0] = new Sprite(new Texture(Configuration.getLanguage() + "/mainScreen/sekt_button_free_hall_of_fame.png"));
        getButtons()[4][1] = new Sprite(new Texture(Configuration.getLanguage() + "/mainScreen/sekt_button_clicked_hall_of_fame.png"));


        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 2; j++) {
                getButtons()[i][j].setSize(Resolution.getGameWorldWidthPortrait() * 0.3f,
                        Resolution.getGameWorldHeightPortrait() * 0.3f * Resolution.getAspectRatio());
            }
        setClicked(new boolean[5]);
    }

}
