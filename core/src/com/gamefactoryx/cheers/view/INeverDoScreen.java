package com.gamefactoryx.cheers.view;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.gamefactoryx.cheers.tool.Configuration;
import com.gamefactoryx.cheers.model.INeverDoModel;
import com.gamefactoryx.cheers.tool.FontHelper;
import com.gamefactoryx.cheers.tool.Orientation;
import com.gamefactoryx.cheers.tool.Resolution;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by bernat on 28.04.2017.
 */
@SuppressWarnings("DefaultFileTemplate")
public class INeverDoScreen extends AbstractScreen {

    private final static FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
    private FreeTypeFontGenerator generator;
    private int FONT_SIZE;
    private float X, Y;
    private BitmapFont font;
    private INeverDoModel dataModel;
    private List<String> text;
    private String line;


    @Override
    public void show() {
        super.show();
        dataModel = INeverDoModel.getInstance();
        line = dataModel.getLine();
    }

    private List<String> splitLine() {
        List<String> text = new ArrayList<>();
        int num_of_chars = (int) (getTextBox().getWidth() / font.getSpaceWidth() * 0.6f);
        if (line.length() > num_of_chars) {
            StringBuilder sb = new StringBuilder();
            for (String s : line.split(" ")) {
                if (sb.length() == 0) {
                    sb.append(s.trim());
                } else if (sb.length() + s.length() + 1 < num_of_chars) {
                    sb.append(" ");
                    sb.append(s.trim());
                } else {
                    text.add(sb.toString());
                    sb.setLength(0);
                    sb.append(s.trim());
                }
            }
            text.add(sb.toString().trim());
        } else
            text = Collections.singletonList(line);

        return text;
    }


    @Override
    protected void initSprites() {
        setLandscapeSprite(new Sprite(new Texture(Configuration.getLanguage() + "/iNeverDoScreen/INeverDoScreenLandscape.png")));
        setPortraitSprite(new Sprite(new Texture(Configuration.getLanguage() + "/iNeverDoScreen/INeverDoScreenPortrait.png")));
        getLandscapeSprite().setSize(Resolution.getGameWorldWidthLandscape(), Resolution.getGameWorldHeightLandscape());
        getPortraitSprite().setSize(Resolution.getGameWorldWidthPortrait(), Resolution.getGameWorldHeightPortrait());
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        float FONT_SIZE_ON_SCREEN = 0.04f;
        if(Configuration.getLanguage() == Configuration.LanguageEnum.SK)
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
        getTextBox().setSize(X * 0.8f, Y * 0.45f);
        for (int i = 0; i < getCountOfButtons(); i++)
            for (int j = 0; j < 2; j++) {
                getButtons()[i][j].setSize(Resolution.getGameWorldWidthPortrait() * 0.77f,
                        Resolution.getGameWorldHeightPortrait() * 0.17f * Resolution.getAspectRatio());
            }

        parameter.size = FONT_SIZE;
        parameter.color = new Color(166.0f / 255.0f, 124.0f / 255.0f, 82f / 255.0f, 1f);
        BitmapFont temp = font;
        font = generator.generateFont(parameter);
        if (temp != null)
            temp.dispose();
        generator.dispose();
        text = splitLine();

    }

    @Override
    protected void drawText() {
        float EMPTYCHAR_CHAR_WIDTH_RATIO = 1.6f;
        float DISTANCE_FROM_TEXTBOX_BOTTOM = 0.4f;
        getTextBox().setPosition(X * 0.1f, Y * 0.35f);
        getTextBox().draw(getSpriteBatch());
        for (int i = 0; i < text.size(); i++)
            font.draw(getSpriteBatch(), text.get(i),
                    (X - text.get(i).length() * font.getSpaceWidth() * EMPTYCHAR_CHAR_WIDTH_RATIO) * 0.5f,
                    Y - (Y - font.getCapHeight() * text.size()) * DISTANCE_FROM_TEXTBOX_BOTTOM - font.getCapHeight() * 1.3f * i);
    }


    @Override
    protected void initTextBox() {
        setTextBox(new Sprite(new Texture(Configuration.getLanguage() + "/iNeverDoScreen/INeverDoScreen-TextBox.png")));
        //setTextBox(new Sprite(new Texture("de/iNeverDoScreen/Ineverdoscreenkasten.png")));

    }

    @Override
    protected void initButtons() {
        setButtons(new Sprite[1][2]);

        getButtons()[0][0] = new Sprite(new Texture(Configuration.getLanguage() + "/iNeverDoScreen/Ineverdoscreenicon.png"));
        getButtons()[0][1] = new Sprite(new Texture(Configuration.getLanguage() + "/iNeverDoScreen/Ineverdoscreenicon_white.png"));

        setClicked(new boolean[getCountOfButtons()]);
    }

    @Override
    protected void drawButtons() {
        float PORTRAIT_DISTANCE_FROM_TEXT_BOX = 0.22f;
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

    @Override
    public void dispose(){
        if(font !=null)
            font.dispose();
        super.dispose();
    }
}

