package com.gamefactoryx.cheers.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.gamefactoryx.cheers.controller.ButtonController;
import com.gamefactoryx.cheers.controller.TextController;
import com.gamefactoryx.cheers.tool.Orientation;
import com.gamefactoryx.cheers.tool.Resolution;
import com.gamefactoryx.cheers.controller.screen.NewGameScreenController;

/**
 * Created by bernat on 28.04.2017.
 */
@SuppressWarnings("DefaultFileTemplate")
public class NewGameScreen extends AbstractScreen {


    public NewGameScreen(TextController controller) {
        super(controller);
    }

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
        for (int i = 0; i < getController().getCountOfButtons(); i++) {
            int click_index = getController().getClicked()[i] ? CLICKED : FREE;
            float y_offset = offset_index++ * getController().getButtons()[i][click_index].getHeight() * 1.0f;
            if (Orientation.getOrientation() == Input.Orientation.Landscape) {
                if (i < getController().getCountOfButtons() - 1)
                    getController().getButtons()[i][click_index].setPosition(Resolution.getGameWorldWidthLandscape() * 0.01f,
                            Resolution.getGameWorldHeightLandscape() - Resolution.getGameWorldHeightLandscape() * 0.35f - y_offset);
                else
                    getController().getButtons()[i][click_index].setPosition(Resolution.getGameWorldWidthLandscape() * 0.01f,
                            0f);

            } else {
                if (i < getController().getCountOfButtons() - 1)
                    getController().getButtons()[i][click_index].setPosition(Resolution.getGameWorldWidthPortrait() * 0.01f,
                            Resolution.getGameWorldHeightPortrait() - Resolution.getGameWorldHeightPortrait() * 0.25f - y_offset);
                else
                    getController().getButtons()[i][click_index].setPosition(Resolution.getGameWorldWidthPortrait() * 0.01f,
                            0f);
            }
            getController().getButtons()[i][click_index].draw(getSpriteBatch(), 1);
        }
    }

    @Override
    protected void drawText() {

    }

}
