package com.gamefactoryx.cheers.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.gamefactoryx.cheers.model.HallOfFameModel;
import com.gamefactoryx.cheers.model.INeverDoModel;
import com.gamefactoryx.cheers.tool.Configuration;
import com.gamefactoryx.cheers.tool.FontHelper;
import com.gamefactoryx.cheers.tool.Orientation;
import com.gamefactoryx.cheers.tool.Resolution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by bernat on 28.04.2017.
 */
@SuppressWarnings("DefaultFileTemplate")
public class HallOfFameScreen extends AbstractScreen {

    private final static FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
    private FreeTypeFontGenerator generator;
    private int FONT_SIZE;
    private float X, Y;
    private BitmapFont font;
    private HallOfFameModel dataModel;


    @Override
    public void show() {
        super.show();
        dataModel = HallOfFameModel.getInstance();
    }


    @Override
    protected void initSprites() {
        setLandscapeSprite(new Sprite(new Texture(Configuration.getLanguage() + "/HallofFame/hall_of_fame_screen_busdriving.png")));
        setPortraitSprite(new Sprite(new Texture(Configuration.getLanguage() + "/HallofFame/hall_of_fame_screen_busdriving.png")));
        getLandscapeSprite().setSize(Resolution.getGameWorldWidthLandscape(), Resolution.getGameWorldHeightLandscape());
        getPortraitSprite().setSize(Resolution.getGameWorldWidthPortrait(), Resolution.getGameWorldHeightPortrait());
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        float FONT_SIZE_ON_SCREEN = 0.05f;
        if (Configuration.getLanguage() == Configuration.LanguageEnum.SK)
            generator = new FreeTypeFontGenerator(FontHelper.getSkFontFile());
        else
            generator = new FreeTypeFontGenerator(FontHelper.getFontFile());
        if (Orientation.getOrientation() == Input.Orientation.Portrait) {
            FONT_SIZE = (int) (Resolution.getGameWorldHeightPortrait() * FONT_SIZE_ON_SCREEN);
            X = Resolution.getGameWorldWidthPortrait();
            Y = Resolution.getGameWorldHeightPortrait();
        } else {
            FONT_SIZE = (int) (Resolution.getGameWorldWidthLandscape() * FONT_SIZE_ON_SCREEN);
            X = Resolution.getGameWorldWidthLandscape();
            Y = Resolution.getGameWorldHeightLandscape();
        }
/*        getTextBox().setSize(X * 0.8f, Y * 0.45f);
        for (int i = 0; i < getCountOfButtons(); i++)
            for (int j = 0; j < 2; j++) {
                getButtons()[i][j].setSize(Resolution.getGameWorldWidthPortrait() * 0.77f,
                        Resolution.getGameWorldHeightPortrait() * 0.17f * Resolution.getAspectRatio());
            }*/

        parameter.size = FONT_SIZE;
        parameter.color = new Color(166.0f / 255.0f, 124.0f / 255.0f, 82f / 255.0f, 1f);
        BitmapFont temp = font;
        font = generator.generateFont(parameter);
        if (temp != null)
            temp.dispose();
        generator.dispose();

    }

    @Override
    protected void drawText() {
        float DISTANCE_FROM_UP = 0.8f;
        float DISTANCE_FROM_LEFT = Orientation.getOrientation() == Input.Orientation.Portrait ?  0.15f : 0.28f;
        //getTextBox().setPosition(X * 0.1f, Y * 0.35f);
        //getTextBox().draw(getSpriteBatch());
        List<String> scorers =  dataModel.get();
        int y_offset = 0;
        for (String scorer : scorers){
                String[] s = scorer.split(":");
                String val = String.format("%3s:    %s", s[0], s[1]);
                font.draw(getSpriteBatch(), val,
                        X * DISTANCE_FROM_LEFT,
                        Y * DISTANCE_FROM_UP - font.getCapHeight() * 1.9f * y_offset++);
        }
    }


    @Override
    protected void initTextBox() {
        //setTextBox(new Sprite(new Texture(Configuration.getLanguage() + "/iNeverDoScreen/INeverDoScreen-TextBox.png")));
        //setTextBox(new Sprite(new Texture("de/iNeverDoScreen/Ineverdoscreenkasten.png")));

    }

    @Override
    protected void initButtons() {
       /* setButtons(new Sprite[1][2]);

        getButtons()[0][0] = new Sprite(new Texture(Configuration.getLanguage() + "/iNeverDoScreen/Ineverdoscreenicon.png"));
        getButtons()[0][1] = new Sprite(new Texture(Configuration.getLanguage() + "/iNeverDoScreen/Ineverdoscreenicon_white.png"));

        setClicked(new boolean[getCountOfButtons()]);*/
    }

    @Override
    protected void drawButtons() {
       /* float PORTRAIT_DISTANCE_FROM_TEXT_BOX = 0.22f;
        float LANDSCAPE_DISTANCE_FROM_TEXT_BOX = 0.15f;
        for (int i = 0; i < getCountOfButtons(); i++) {
            int click_index = getClicked()[i] ? CLICKED : FREE;
            if (Orientation.getOrientation() == Input.Orientation.Portrait)
                getButtons()[i][click_index].setPosition(X * 0.115f,
                        Y * PORTRAIT_DISTANCE_FROM_TEXT_BOX);
            else
                getButtons()[i][click_index].setPosition(0.5f * (X - getButtons()[i][click_index].getWidth()),
                        Y * LANDSCAPE_DISTANCE_FROM_TEXT_BOX);

            getButtons()[i][click_index].draw(getSpriteBatch(), 1);
        }*/
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
