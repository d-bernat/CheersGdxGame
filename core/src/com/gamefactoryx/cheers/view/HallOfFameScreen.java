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
    private Sprite[] medals = new Sprite[3];

    @Override
    public void show() {
        super.show();
        dataModel = HallOfFameModel.getInstance();
    }


    @Override
    protected void initSprites() {
        setLandscapeSprite(new Sprite(new Texture(Configuration.getLanguage() + "/HallofFame/hall_of_fame_landscape.png")));
        setPortraitSprite(new Sprite(new Texture(Configuration.getLanguage() + "/HallofFame/hall_of_fame_screen.png")));
        getLandscapeSprite().setSize(Resolution.getGameWorldWidthLandscape(), Resolution.getGameWorldHeightLandscape());
        getPortraitSprite().setSize(Resolution.getGameWorldWidthPortrait(), Resolution.getGameWorldHeightPortrait());
        medals[0] = new Sprite(new Texture("common/HallofFame/1place.png"));
        medals[1] = new Sprite(new Texture("common/HallofFame/2place.png"));
        medals[2] = new Sprite(new Texture("common/HallofFame/3place.png"));

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
        if (Orientation.getOrientation() == Input.Orientation.Portrait)
            getTextBox().setSize(X * 0.85f, Y * 0.065f);
        else
            getTextBox().setSize(X * 0.75f, Y * 0.105f);

        for (int i = 0; i < getCountOfButtons(); i++)
            for (int j = 0; j < 2; j++) {
                getButtons()[i][j].setSize(Resolution.getGameWorldWidthPortrait() * 0.77f,
                        Resolution.getGameWorldHeightPortrait() * 0.17f * Resolution.getAspectRatio());
            }
        for (Sprite medal : medals)
            if (Orientation.getOrientation() == Input.Orientation.Portrait)
                medal.setSize(X * 0.2f, X * 0.2f);
            else
                medal.setSize(Y * 0.2f, Y * 0.2f);

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
        float DISTANCE_FROM_LEFT = Orientation.getOrientation() == Input.Orientation.Portrait ? 0.15f : 0.28f;
        float DISTANCE_FROM_LEFT_FOR_MEDALS = Orientation.getOrientation() == Input.Orientation.Portrait ? 0.2f : 0.5f;

        List<String> scorers = dataModel.get();
        int y_offset = 0;
        int index = 0;
        for (String scorer : scorers) {
            String[] s = scorer.split(":");
            String val = String.format("%3s:    %s", s[0], s[1]);
            float xx = X * DISTANCE_FROM_LEFT;
            float yy = Y * DISTANCE_FROM_UP - font.getCapHeight() * 2.3f * y_offset++;
            getTextBox().setPosition(xx * 0.5f, yy - getTextBox().getHeight() / 1.5f);
            getTextBox().draw(getSpriteBatch());
            if (index < 3) {
                medals[index].setPosition(xx * DISTANCE_FROM_LEFT_FOR_MEDALS, yy - getTextBox().getHeight() / 1.5f);
                medals[index].draw(getSpriteBatch());
            }
            ++index;
            font.draw(getSpriteBatch(), val, xx, yy);
        }
    }


    @Override
    protected void initTextBox() {
        setTextBox(new Sprite(new Texture(Configuration.getLanguage() + "/Busdrivingscreen/text_box_horizontal.png")));
    }

    @Override
    protected void initButtons() {
    }

    @Override
    protected void drawButtons() {
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

    @Override
    public void dispose() {
        for (Sprite medal : medals)
            medal.getTexture().dispose();
        if (font != null)
            font.dispose();
        super.dispose();
    }
}
