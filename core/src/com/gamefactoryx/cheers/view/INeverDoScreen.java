package com.gamefactoryx.cheers.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.gamefactoryx.cheers.controller.ButtonController;
import com.gamefactoryx.cheers.controller.TextController;
import com.gamefactoryx.cheers.tool.Orientation;
import com.gamefactoryx.cheers.tool.Resolution;

/**
 * Created by bernat on 28.04.2017.
 */
@SuppressWarnings("DefaultFileTemplate")
public class INeverDoScreen extends AbstractScreen {

    private final static FileHandle fontFile = Gdx.files.internal("base/SemirResimovicRukopisniFONT.otf");
    private final static FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
    private FreeTypeFontGenerator generator;
    private int FONT_SIZE;
    private float X, Y;
    private BitmapFont font;


    private String[] taskLines;

    public INeverDoScreen(TextController controller) {
        super(controller);
        taskLines = getController().getText();
        parameter.borderColor = Color.BLACK;
        parameter.color = Color.BLACK;

    }

    @Override
    protected void initSprites() {
        setLandscapeSprite(new Sprite(new Texture("iNeverDoScreen/INeverDoScreenLandscape.png")));
        setPortraitSprite(new Sprite(new Texture("iNeverDoScreen/INeverDoScreenPortrait.png")));
        getLandscapeSprite().setSize(Resolution.getGameWorldWidthLandscape(), Resolution.getGameWorldHeightLandscape());
        getPortraitSprite().setSize(Resolution.getGameWorldWidthPortrait(), Resolution.getGameWorldHeightPortrait());
    }


    @Override
    protected void drawButtons() {
        for (int i = 0; i < getController().getCountOfButtons(); i++) {
            int click_index = getController().getClicked()[i] ? CLICKED : FREE;
            if (Orientation.getOrientation() == Input.Orientation.Landscape) {
                    getController().getButtons()[i][click_index].setPosition(Resolution.getGameWorldWidthLandscape() * 0.01f,
                            0f);

            } else {
                    getController().getButtons()[i][click_index].setPosition(Resolution.getGameWorldWidthPortrait() * 0.01f,
                            0f);
            }
            getController().getButtons()[i][click_index].draw(getSpriteBatch(), 1);
        }
    }

    @Override public void resize(int width, int height) {
        super.resize(width, height);
        generator = new FreeTypeFontGenerator(fontFile);
        if(Orientation.getOrientation() == Input.Orientation.Portrait) {
            FONT_SIZE = (int) (Resolution.getGameWorldHeightPortrait() * 0.05f);
            X = Resolution.getGameWorldWidthPortrait();
            Y = Resolution.getGameWorldHeightPortrait();
            getController().getTextBox().setSize(Resolution.getGameWorldWidthPortrait() * 0.8f, Resolution.getGameWorldHeightPortrait() * 0.4f );
        }else{
            FONT_SIZE = (int) (Resolution.getGameWorldWidthLandscape() * 0.05f);
            X = Resolution.getGameWorldWidthLandscape();
            Y = Resolution.getGameWorldHeightLandscape();
            getController().getTextBox().setSize(Resolution.getGameWorldWidthLandscape() * 0.8f, Resolution.getGameWorldHeightLandscape() * 0.4f );

        }

        parameter.size = FONT_SIZE;
        font = generator.generateFont(parameter);
        generator.dispose();

    }

    @Override
    protected void drawText() {
        getController().getTextBox().setPosition(X * 0.1f,Y * 0.3f);
        getController().getTextBox().draw(getSpriteBatch());
        for(int i = 0; i < taskLines.length; i++)
            font.draw(getSpriteBatch(), taskLines[i],
                    (X - taskLines[i].length()* font.getSpaceWidth() * 1.0f) * 0.5f,
                    Y - (Y  - font.getCapHeight() * taskLines.length) * 0.5f - font.getCapHeight() * 1.0f * i);
    }

}
