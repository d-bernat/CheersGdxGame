package com.gamefactoryx.cheers.view;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.gamefactoryx.cheers.controller.ButtonController;
import com.gamefactoryx.cheers.controller.TextController;
import com.gamefactoryx.cheers.tool.Orientation;
import com.gamefactoryx.cheers.tool.Resolution;

/**
 * Created by bernat on 28.04.2017.
 */
@SuppressWarnings("DefaultFileTemplate")
public class MainScreen extends AbstractScreen {


    public MainScreen(TextController controller) {
        super(controller);
    }

    @Override
    protected void initSprites() {
        setLandscapeSprite(new Sprite(new Texture("mainScreen/MainScreenLandscape.png")));
        setPortraitSprite(new Sprite(new Texture("mainScreen/MainScreenPortrait.png")));
        getLandscapeSprite().setSize(Resolution.getGameWorldWidthLandscape(), Resolution.getGameWorldHeightLandscape());
        getPortraitSprite().setSize(Resolution.getGameWorldWidthPortrait(), Resolution.getGameWorldHeightPortrait());
    }


    @Override
    protected void drawButtons() {
        int offset_index = 0;
        for (int i = 0; i < getController().getCountOfButtons(); i++) {
            if (i == 3 && Orientation.getOrientation() == Input.Orientation.Landscape)
                offset_index = 0;
            float y_offset = offset_index++ * getController().getButtons()[i][CLICKED].getHeight() * 1.0f;
            int click_index = getController().getClicked()[i] ? CLICKED : FREE;
            if (Orientation.getOrientation() == Input.Orientation.Landscape) {
                if (i < 3)
                    getController().getButtons()[i][click_index].setPosition(Resolution.getGameWorldWidthLandscape() * 0.01f,
                            Resolution.getGameWorldHeightLandscape() - Resolution.getGameWorldHeightLandscape() * 0.35f - y_offset);
                else
                    getController().getButtons()[i][click_index].setPosition(Resolution.getGameWorldWidthLandscape() * 0.01f + getController().getButtons()[i][0].getWidth(),
                            Resolution.getGameWorldHeightLandscape() - Resolution.getGameWorldHeightLandscape() * 0.35f -
                                    y_offset - getController().getButtons()[i][click_index].getHeight() * 0.5f);

            } else {
                getController().getButtons()[i][click_index].setPosition(Resolution.getGameWorldWidthPortrait() * 0.01f,
                        Resolution.getGameWorldHeightPortrait() - Resolution.getGameWorldHeightPortrait() * 0.20f - y_offset);
            }
            getController().getButtons()[i][click_index].draw(getSpriteBatch(), 1);
        }
    }

    @Override
    protected void drawText() {

    }

}
