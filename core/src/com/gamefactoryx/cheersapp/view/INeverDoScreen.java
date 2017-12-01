package com.gamefactoryx.cheersapp.view;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.gamefactoryx.cheersapp.tool.Configuration;
import com.gamefactoryx.cheersapp.model.INeverDoModel;
import com.gamefactoryx.cheersapp.tool.FontHelper;
import com.gamefactoryx.cheersapp.tool.Orientation;
import com.gamefactoryx.cheersapp.tool.Resolution;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by bernat on 28.04.2017.
 */
@SuppressWarnings("DefaultFileTemplate")
public class INeverDoScreen extends com.gamefactoryx.cheersapp.view.AbstractScreen {

    private final static FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
    private FreeTypeFontGenerator generator;
    private int FONT_SIZE;
    private float X, Y;
    private BitmapFont font;
    private INeverDoModel dataModel;
    private List<String> text;
    private String plainText;


    @Override
    public void show() {
        super.show();
        dataModel = INeverDoModel.getInstance();
        plainText = dataModel.getLine();
        initBackButton();
        initRulesButton(dataModel);
    }

    /*private List<String> splitLine() {
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
    }*/
    private List<String> splitLine() {
        List<String> text = new ArrayList<>();
        int num_of_chars = (int) (X * 0.8f / font.getSpaceWidth() * 0.5f);
        StringBuilder sb = new StringBuilder();
        if (plainText.length() > num_of_chars) {

            for (String line : plainText.split("\\n")) {
                for (String word : line.split(" ")) {
                    if (sb.length() == 0) {
                        sb.append(word);
                    } else if (sb.length() + word.length() + 1 < num_of_chars) {
                        sb.append(" ");
                        sb.append(word);
                    } else {
                        text.add(sb.toString());
                        sb.setLength(0);
                        sb.append(word);
                    }
                }
                sb.append('\n');
                text.add(sb.toString());
                sb.setLength(0);
            }
        } else {
            sb.append(plainText);
            sb.append('\n');
            text = Collections.singletonList(sb.toString());
        }


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
        float FONT_SIZE_ON_SCREEN = 0.04f;
        super.resize(width, height);
        if (Configuration.getLanguage() == Configuration.LanguageEnum.SK)
            generator = new FreeTypeFontGenerator(FontHelper.getSkFontFile());
        else
            generator = new FreeTypeFontGenerator(FontHelper.getFontFile());


        if (Orientation.getOrientation() == Input.Orientation.Portrait) {
            FONT_SIZE = (int) (Resolution.getGameWorldHeightPortrait() * FONT_SIZE_ON_SCREEN);
            X = Resolution.getGameWorldWidthPortrait();
            Y = Resolution.getGameWorldHeightPortrait();
//            getTextBox().setSize(Resolution.getGameWorldWidthPortrait() * 0.92f, Resolution.getGameWorldHeightPortrait() * 0.85f);
        } else {
            FONT_SIZE = (int) (Resolution.getGameWorldWidthLandscape() * FONT_SIZE_ON_SCREEN);
            X = Resolution.getGameWorldWidthLandscape();
            Y = Resolution.getGameWorldHeightLandscape();
//            getTextBox().setSize(Resolution.getGameWorldWidthLandscape() * 0.92f, Resolution.getGameWorldHeightLandscape() * 0.85f);

        }
        getTextBox().setSize(X * 0.8f, Y * 0.25f);
        for (int i = 0; i < getCountOfButtons() - 1; i++)
            for (int j = 0; j < 2; j++) {
                if (i == 0)
                    getButtons()[i][j].setSize(Resolution.getGameWorldWidthPortrait() * 0.77f,
                            Resolution.getGameWorldHeightPortrait() * 0.17f * Resolution.getAspectRatio());
                else{
                    getButtons()[i][j].setSize(Resolution.getGameWorldWidthPortrait() * 0.2f,
                            Resolution.getGameWorldHeightPortrait() * 0.2f * Resolution.getAspectRatio());
                }
            }

        getButtons()[4][0].setSize(X * 0.7f,
                X * 0.7f * getButtons()[4][0].getHeight() / getButtons()[4][0].getWidth());
        getButtons()[4][1].setSize(X * 0.7f,
                X * 0.7f * getButtons()[4][1].getHeight() / getButtons()[4][1].getWidth());

        parameter.size = FONT_SIZE;
        parameter.color = new Color(166.0f / 255.0f, 124.0f / 255.0f, 82f / 255.0f, 1f);

        BitmapFont temp = font;
        font = generator.generateFont(parameter);

        parameter.size = FONT_SIZE + 10;

        if (temp != null)
            temp.dispose();


        generator.dispose();
        text = splitLine();

    }

    @Override
    protected void drawText() {
        float EMPTYCHAR_CHAR_WIDTH_RATIO = 1.6f;
        float DISTANCE_FROM_TEXTBOX_BOTTOM = 0.4f;
        if (Orientation.getOrientation() == Input.Orientation.Portrait) {
            getTextBox().setPosition(X * 0.1f, Y * 0.55f);
            getTextBox().draw(getSpriteBatch());
            for (int i = 0; i < text.size(); i++)
                font.draw(getSpriteBatch(), text.get(i),
                        (X - text.get(i).length() * font.getSpaceWidth() * EMPTYCHAR_CHAR_WIDTH_RATIO) * 0.5f,
                        Y - (Y - font.getCapHeight() * text.size()) * DISTANCE_FROM_TEXTBOX_BOTTOM - font.getCapHeight() * 1.3f * i + getTextBox().getHeight() * 0.45f);
            font.draw(getSpriteBatch(), Configuration.getINeverDoGameType().toString(), getTextBox().getX() * 1.1f, getTextBox().getY() + getTextBox().getHeight() * 0.98f);
        } else {
            getTextBox().setPosition(X * 0.02f, Y * 0.35f);
            getTextBox().draw(getSpriteBatch());
            for (int i = 0; i < text.size(); i++)
                font.draw(getSpriteBatch(), text.get(i),
                        (X - text.get(i).length() * font.getSpaceWidth() * EMPTYCHAR_CHAR_WIDTH_RATIO) * 0.3f,
                        Y  - (Y - font.getCapHeight() * text.size()) * DISTANCE_FROM_TEXTBOX_BOTTOM - font.getCapHeight() * 1.3f * i);

            font.draw(getSpriteBatch(), Configuration.getINeverDoGameType().toString(), getTextBox().getX() * 1.1f, getTextBox().getY() + getTextBox().getHeight() * 0.98f);
        }
    }


    @Override
    protected void initTextBox() {
        setTextBox(new Sprite(new Texture(Configuration.getLanguage() + "/iNeverDoScreen/INeverDoScreen-TextBox.png")));
        //setTextBox(new Sprite(new Texture("de/iNeverDoScreen/Ineverdoscreenkasten.png")));

    }

    @Override
    protected void initButtons() {
        setButtons(new CheckedButton[5][2]);

        getButtons()[0][0] = new CheckedButton(new Texture(Configuration.getLanguage() + "/iNeverDoScreen/Ineverdoscreenicon.png"));
        getButtons()[0][1] = new CheckedButton(new Texture(Configuration.getLanguage() + "/iNeverDoScreen/Ineverdoscreenicon_white.png"));
        getButtons()[1][0] = new CheckedButton(new Texture("common/ineverhave/18+.png"));
        getButtons()[1][1] = new CheckedButton(new Texture("common/ineverhave/18+.png"));
        getButtons()[2][0] = new CheckedButton(new Texture("common/ineverhave/mix.png"));
        getButtons()[2][1] = new CheckedButton(new Texture("common/ineverhave/mix.png"));
        getButtons()[3][0] = new CheckedButton(new Texture("common/ineverhave/standart.png"));
        getButtons()[3][1] = new CheckedButton(new Texture("common/ineverhave/standart.png"));
        getButtons()[4][0] = new CheckedButton(new Texture(Configuration.getLanguage() + "/premium_inever.png"), true);
        getButtons()[4][1] = new CheckedButton(new Texture(Configuration.getLanguage() + "/premium_inever.png"), true);
        setClicked(new boolean[getCountOfButtons()]);
    }

    @Override
    protected void drawButtons() {
        float PORTRAIT_DISTANCE_FROM_TEXT_BOX = 0.22f;
        float LANDSCAPE_DISTANCE_FROM_TEXT_BOX = 0.15f;
        float x_offset = 0f;
        float y_offset = 0f;
        for (int i = 0; i < getCountOfButtons(); i++) {
            int click_index = getClicked()[i] ? CLICKED : FREE;
            if (Orientation.getOrientation() == Input.Orientation.Portrait)
                if (i == 0)
                    getButtons()[i][click_index].setPosition(X * 0.115f,
                            Y * PORTRAIT_DISTANCE_FROM_TEXT_BOX);
                else {
                    getButtons()[i][click_index].setPosition(X * 0.18f + x_offset,
                            Y * 0.05f - y_offset);
                    // y_offset = getButtons()[i][click_index].getHeight() * 1.05f * i;
                    x_offset = getButtons()[i][click_index].getWidth() * 1.15f * i;
                }
            else {
                if (i == 0)
                    getButtons()[i][click_index].setPosition(0.36f * (X - getButtons()[i][click_index].getWidth()),
                            Y * LANDSCAPE_DISTANCE_FROM_TEXT_BOX);
                else {
                    getButtons()[i][click_index].setPosition(X * 0.87f + x_offset,
                            Y * 0.65f - y_offset);
                    y_offset = getButtons()[i][click_index].getHeight() * 1.15f * i;
                    //x_offset = getButtons()[i][click_index].getWidth() * 1.05f * i;
                }
            }

            switch (i) {
                case 0:
                    getButtons()[i][click_index].draw(getSpriteBatch(),1);
                    break;
                case 1:
                    getButtons()[i][click_index].draw(getSpriteBatch(), Configuration.getINeverDoGameType() == Configuration.INeverDoGameTypeEnum.GAME_18PLUS ? 1 : 0.5f);
                    break;
                case 2:
                    getButtons()[i][click_index].draw(getSpriteBatch(), Configuration.getINeverDoGameType() == Configuration.INeverDoGameTypeEnum.GAME_MIXED ? 1 : 0.5f);
                    break;
                case 3:
                    getButtons()[i][click_index].draw(getSpriteBatch(), Configuration.getINeverDoGameType() == Configuration.INeverDoGameTypeEnum.GAME_STANDARD ? 1 : 0.5f);
                    break;
                case 4:
                    if(!Configuration.isPremium()){
                        getButtons()[i][0].setPosition(0.5f * (X - getButtons()[i][0].getWidth()), Y - Y * 0.6f);
                        getButtons()[i][0].draw(getSpriteBatch(), 1.0f);
                    }
                    break;
            }
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

    @Override
    public void dispose() {
        if (font != null)
            font.dispose();
        super.dispose();
    }

    @Override
    public void setRulesButtonSpritePosition() {
        if (getRulesButtonSprite() != null) {
            if (Orientation.getOrientation() == Input.Orientation.Portrait) {
                getRulesButtonSprite().setPosition(Resolution.getGameWorldWidthPortrait() - getRulesButtonSprite().getWidth() * 1.2f,
                        Resolution.getGameWorldHeightPortrait() - getRulesButtonSprite().getHeight() * 2.0f);
            } else {
                getRulesButtonSprite().setPosition(Resolution.getGameWorldWidthLandscape() - getRulesButtonSprite().getWidth() * 3.0f,
                        Resolution.getGameWorldHeightLandscape() - getRulesButtonSprite().getHeight() * 1.2f);
            }
        }

    }
}

