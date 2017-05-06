package com.gamefactoryx.cheers.view;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.gamefactoryx.cheers.tool.Orientation;
import com.gamefactoryx.cheers.tool.Resolution;

/**
 * Created by bernat on 28.04.2017.
 */
@SuppressWarnings("DefaultFileTemplate")
public class NewGameScreen extends AbstractScreen {


    @Override
    protected void initSprites() {
        setLandscapeSprite(new Sprite(new Texture("newGameScreen/NewGameScreenLandscape.png")));
        setPortraitSprite(new Sprite(new Texture("newGameScreen/NewGameScreenPortrait.png")));
        getLandscapeSprite().setSize(Resolution.getGameWorldWidthLandscape(), Resolution.getGameWorldHeightLandscape());
        getPortraitSprite().setSize(Resolution.getGameWorldWidthPortrait(), Resolution.getGameWorldHeightPortrait());
    }


    @Override
    protected void drawButtons() {
        int offset_index = 0;
        for (int i = 0; i < getCountOfButtons(); i++) {
            int click_index = getClicked()[i] ? CLICKED : FREE;
            float y_offset = offset_index++ * getButtons()[i][click_index].getHeight() * 1.0f;
            if (Orientation.getOrientation() == Input.Orientation.Landscape) {
                if (i < getCountOfButtons() - 1)
                    getButtons()[i][click_index].setPosition(Resolution.getGameWorldWidthLandscape() * 0.01f,
                            Resolution.getGameWorldHeightLandscape() - Resolution.getGameWorldHeightLandscape() * 0.35f - y_offset);
                else
                    getButtons()[i][click_index].setPosition(Resolution.getGameWorldWidthLandscape() * 0.01f,
                            0f);

            } else {
                if (i < getCountOfButtons() - 1)
                    getButtons()[i][click_index].setPosition(Resolution.getGameWorldWidthPortrait() * 0.01f,
                            Resolution.getGameWorldHeightPortrait() - Resolution.getGameWorldHeightPortrait() * 0.25f - y_offset);
                else
                    getButtons()[i][click_index].setPosition(Resolution.getGameWorldWidthPortrait() * 0.01f,
                            0f);
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
        setButtons(new Sprite[3][2]);

        getButtons()[0][0] = new Sprite(new Texture("newGameScreen/sekt_button_ididnot_free.png"));
        getButtons()[0][1] = new Sprite(new Texture("newGameScreen/sekt_button_ididnot_clicked.png"));
        getButtons()[1][0] = new Sprite(new Texture("newGameScreen/sekt_button_kingscup_free.png"));
        getButtons()[1][1] = new Sprite(new Texture("newGameScreen/sekt_button_kingscup_clicked.png"));
        getButtons()[2][0] = new Sprite(new Texture("base/button_free_back_to_main.png"));
        getButtons()[2][1] = new Sprite(new Texture("base/button_clicked_back_to_main.png"));

        for (int i = 0; i < getCountOfButtons(); i++) {
            for (int j = 0; j < 2; j++) {
                if(i < getCountOfButtons() - 1)
                    getButtons()[i][j].setSize(Resolution.getGameWorldWidthPortrait(),
                            Resolution.getGameWorldHeightPortrait() * 0.35f * Resolution.getAspectRatio());
                else
                    getButtons()[i][j].setSize(Resolution.getGameWorldWidthPortrait() * 0.2f,
                            Resolution.getGameWorldHeightPortrait() * 0.2f * Resolution.getAspectRatio());

            }
        }
        setClicked( new boolean[getCountOfButtons()]);
    }


}
