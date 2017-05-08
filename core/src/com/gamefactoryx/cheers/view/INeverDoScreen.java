package com.gamefactoryx.cheers.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.gamefactoryx.cheers.model.Configuration;
import com.gamefactoryx.cheers.model.INeverDoModel;
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
public class INeverDoScreen extends AbstractScreen {

    private final static FileHandle fontFile = Gdx.files.internal("font/SemirResimovicRukopisniFONT.otf");
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
        int num_of_chars = (int) (getTextBox().getWidth()/font.getSpaceWidth() * 0.9f);
        if(line.length() > num_of_chars) {
            StringBuilder sb = new StringBuilder();
            for(String s: line.split(" ")){
               if(sb.length() == 0) {
                    sb.append(s);
                }
                else if(sb.length() + s.length() + 1 < num_of_chars) {
                    sb.append(" ");
                    sb.append(s);
                }else {
                    text.add(sb.toString());
                    sb.setLength(0);
                    sb.append(s);
                }
            }
            text.add(sb.toString());
        }
        else
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
        generator = new FreeTypeFontGenerator(fontFile);
        if(Orientation.getOrientation() == Input.Orientation.Portrait) {
            FONT_SIZE = (int) (Resolution.getGameWorldHeightPortrait() * 0.05f);
            X = Resolution.getGameWorldWidthPortrait();
            Y = Resolution.getGameWorldHeightPortrait();
            getTextBox().setSize(Resolution.getGameWorldWidthPortrait() * 0.8f, Resolution.getGameWorldHeightPortrait() * 0.4f );
        }else{
            FONT_SIZE = (int) (Resolution.getGameWorldWidthLandscape() * 0.05f);
            X = Resolution.getGameWorldWidthLandscape();
            Y = Resolution.getGameWorldHeightLandscape();
            getTextBox().setSize(Resolution.getGameWorldWidthLandscape() * 0.8f, Resolution.getGameWorldHeightLandscape() * 0.4f );

        }

        parameter.size = FONT_SIZE;
        parameter.color = Color.BLACK;
        BitmapFont temp = font;
        font = generator.generateFont(parameter);
        if(temp != null)
            temp.dispose();
        generator.dispose();
        text = splitLine();

    }

    @Override
    protected void drawText() {
        getTextBox().setPosition(X * 0.1f,Y * 0.3f);
        getTextBox().draw(getSpriteBatch());
        for(int i = 0; i < text.size(); i++)
            font.draw(getSpriteBatch(), text.get(i),
                    (X - text.get(i).length()* font.getSpaceWidth() * 1.0f) * 0.5f,
                    Y - (Y  - font.getCapHeight() * text.size()) * 0.5f - font.getCapHeight() * 1.0f * i);
    }


    @Override
    protected void initTextBox(){
        setTextBox(new Sprite(new Texture("common/TextBoxPortrait.png")));

    }

}
