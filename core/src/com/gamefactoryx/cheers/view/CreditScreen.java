package com.gamefactoryx.cheers.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.gamefactoryx.cheers.model.CreditModel;
import com.gamefactoryx.cheers.model.KingsCupSpecialModel;
import com.gamefactoryx.cheers.tool.Configuration;
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
public class CreditScreen extends AbstractScreen {

    private final static FileHandle fontFile = Gdx.files.internal("font/TIMESS.ttf");
    private final static FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
    private FreeTypeFontGenerator generator;
    private int FONT_SIZE;
    private float X, Y;
    private BitmapFont font, fontLabel;
    private String plainText;
    private List<String> text;



    private List<String> splitLine() {
        List<String> text = new ArrayList<>();
        int num_of_chars = (int) (getTextBox().getWidth() / font.getSpaceWidth() * 0.5f);
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
    public void show() {
        super.show();
        FileHandle taskFile = Gdx.files.internal(com.gamefactoryx.cheers.tool.Configuration.getLanguage() + "/credits/credits.txt");
        plainText = taskFile.readString("UTF-8");
        initBackButton();
        CreditModel.getInstance().setyOffset(Resolution.getGameWorldHeightPortrait() * 0.7f);
        CreditModel.getInstance().startAnimation();
    }


    @Override
    protected void initSprites() {
        setLandscapeSprite(new Sprite(new Texture("common/credits.png")));
        setPortraitSprite(new Sprite(new Texture("common/credits.png")));
        getLandscapeSprite().setSize(Resolution.getGameWorldWidthLandscape(), Resolution.getGameWorldHeightLandscape());
        getPortraitSprite().setSize(Resolution.getGameWorldWidthPortrait(), Resolution.getGameWorldHeightPortrait());
    }

    @Override
    public void resize(int width, int height) {
        float FONT_SIZE_ON_SCREEN = 0.03f;
        super.resize(width, height);
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

        getTextBox().setSize(X, Y);

        parameter.size = FONT_SIZE;
        parameter.color = new Color(166.0f / 255.0f, 124.0f / 255.0f, 82f / 255.0f, 1f);

        BitmapFont temp = font;
        font = generator.generateFont(parameter);

        parameter.size = FONT_SIZE + 10;
        BitmapFont tempLabel = fontLabel;
        fontLabel = generator.generateFont(parameter);

        if (temp != null)
            temp.dispose();
        if (tempLabel != null)
            tempLabel.dispose();


        generator.dispose();
        text = splitLine();

        CreditModel.getInstance().setMaxYOffset(-font.getCapHeight() * text.size() * 2.0f);

    }

    @Override
    protected void drawText() {

        float SPACE_BETWEEN_TWO_LINES_WITHOUT_ENTER = 1.75f;
        float SPACE_BETWEEN_TWO_LINES_WITH_ENTER = 2.5f;
        float EMPTYCHAR_CHAR_WIDTH_RATIO = 1.7f;

        float y_offset = CreditModel.getInstance().getyOffset();

        for (int i = 0; i < text.size(); i++) {

            if (text.get(i).contains(":"))
                fontLabel.draw(getSpriteBatch(), text.get(i),
                        (X - text.get(i).length() * font.getSpaceWidth() * EMPTYCHAR_CHAR_WIDTH_RATIO) * 0.5f,
                         Y * 0.8f - font.getCapHeight() * 1.3f - y_offset);
            else
                font.draw(getSpriteBatch(), text.get(i),
                        (X - text.get(i).length() * font.getSpaceWidth() * EMPTYCHAR_CHAR_WIDTH_RATIO) * 0.53f,
                        Y * 0.8f - font.getCapHeight() * 1.3f - y_offset);

            if (text.get(i).indexOf('\n') > -1)
                y_offset += font.getCapHeight() * SPACE_BETWEEN_TWO_LINES_WITH_ENTER;
            else
                y_offset += font.getCapHeight() * SPACE_BETWEEN_TWO_LINES_WITHOUT_ENTER;

        }

        getTextBox().setPosition(0, 0);
        getTextBox().draw(getSpriteBatch());

    }


    @Override
    protected void initTextBox() {
        setTextBox(new Sprite(new Texture("common/credits2.png")));

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
        if (fontLabel != null)
            fontLabel.dispose();
        super.dispose();
    }


}
