package com.gamefactoryx.cheers.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.gamefactoryx.cheers.model.KingsCupSpecialModel;
import com.gamefactoryx.cheers.tool.Orientation;
import com.gamefactoryx.cheers.tool.Resolution;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by bernat on 28.04.2017.
 */
@SuppressWarnings("DefaultFileTemplate")
public class KingsCupSpecialScreen extends AbstractScreen {

    private final static FileHandle fontFile = Gdx.files.internal("base/SemirResimovicRukopisniFONT.otf");
    private final static FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
    private FreeTypeFontGenerator generator;
    private int FONT_SIZE;
    private float X, Y;
    private BitmapFont font;
    private KingsCupSpecialModel dataModel;
    private String plainText;
    private List<String> text;



    private List<String> splitLine() {
        List<String> text = new ArrayList<>();
        int num_of_chars = (int) (getTextBox().getWidth() / font.getSpaceWidth() * 0.9f);
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
        dataModel = KingsCupSpecialModel.getInstance();
        plainText = dataModel.getText();
    }

    @Override
    protected void initSprites() {
        setLandscapeSprite(new Sprite(new Texture("kingsCupSpecial/KingsCupSpecialScreenLandscape.png")));
        setPortraitSprite(new Sprite(new Texture("kingsCupSpecial/KingsCupSpecialScreenPortrait.png")));
        getLandscapeSprite().setSize(Resolution.getGameWorldWidthLandscape(), Resolution.getGameWorldHeightLandscape());
        getPortraitSprite().setSize(Resolution.getGameWorldWidthPortrait(), Resolution.getGameWorldHeightPortrait());
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        generator = new FreeTypeFontGenerator(fontFile);
        if (Orientation.getOrientation() == Input.Orientation.Portrait) {
            FONT_SIZE = (int) (Resolution.getGameWorldHeightPortrait() * 0.035f);
            X = Resolution.getGameWorldWidthPortrait();
            Y = Resolution.getGameWorldHeightPortrait();
            getTextBox().setSize(Resolution.getGameWorldWidthPortrait() * 0.8f, Resolution.getGameWorldHeightPortrait() * 0.7f);
        } else {
            FONT_SIZE = (int) (Resolution.getGameWorldWidthLandscape() * 0.035f);
            X = Resolution.getGameWorldWidthLandscape();
            Y = Resolution.getGameWorldHeightLandscape();
            getTextBox().setSize(Resolution.getGameWorldWidthLandscape() * 0.8f, Resolution.getGameWorldHeightLandscape() * 0.7f);

        }

        parameter.size = FONT_SIZE;
        parameter.color = Color.BLACK;
        BitmapFont temp = font;
        font = generator.generateFont(parameter);

        if (temp != null)
            temp.dispose();
        generator.dispose();
        text = splitLine();

    }

    @Override
    protected void drawText() {

        getTextBox().setPosition(X * 0.1f, Y * 0.15f);
        getTextBox().draw(getSpriteBatch());
        float y_offset = 0f;
        if(getYScrollPos() < 0) setYScrollPos(0);
        else if(getYScrollPos() > text.size() - 10) setYScrollPos(text.size() - 10);
        for (int i = getYScrollPos(); i < text.size(); i++) {

            font.draw(getSpriteBatch(), text.get(i),
                    (X - text.get(i).length() * font.getSpaceWidth() * 1.0f) * 0.5f,
                    Y - getTextBox().getY() - font.getCapHeight()  - y_offset);

            if (text.get(i).indexOf('\n') > -1)
                y_offset += font.getCapHeight() * 1.5f;
            else
                y_offset += font.getCapHeight() * 1.0f;

            if(y_offset > getTextBox().getHeight() * 0.9f) break;
        }
    }


    @Override
    protected void initTextBox() {
        setTextBox(new Sprite(new Texture("base/TextBoxPortrait.png")));

    }

}
