package com.gamefactoryx.cheersapp.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.gamefactoryx.cheersapp.controller.StageManager;
import com.gamefactoryx.cheersapp.tool.Configuration;
import com.gamefactoryx.cheersapp.model.KingsCupSpecialModel;
import com.gamefactoryx.cheersapp.tool.FontHelper;
import com.gamefactoryx.cheersapp.tool.Orientation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by bernat on 28.04.2017.
 */
@SuppressWarnings("DefaultFileTemplate")
public class KingsCupSpecialScreen extends com.gamefactoryx.cheersapp.view.AbstractScreen {

    private final static FileHandle fontFile = Gdx.files.internal("font/TIMESS.ttf");
    private final static FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
    private FreeTypeFontGenerator generator;
    private int FONT_SIZE;
    private float X, Y;
    private BitmapFont font, fontLabel;
    private KingsCupSpecialModel dataModel;
    private String plainText;
    private List<String> text;
    private Sprite rad;
    private Sprite radPointer;
    private float rotation;


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
        dataModel = KingsCupSpecialModel.getInstance();
        //plainText = dataModel.getText();
        initBackButton();
        initRulesButton(dataModel);
    }

    @Override
    public void render(float delta){
        super.render(delta);
    }

    @Override
    protected void initSprites() {
        setLandscapeSprite(new Sprite(new Texture(Configuration.getLanguage() + "/kingsCupSpecial/KingsCupSpecialScreenLandscape.png")));
        setPortraitSprite(new Sprite(new Texture(Configuration.getLanguage() + "/kingsCupSpecial/KingsCupSpecialScreenPortrait.png")));
        getLandscapeSprite().setSize(com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldWidthLandscape(), com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightLandscape());
        getPortraitSprite().setSize(com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldWidthPortrait(), com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightPortrait());

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
            FONT_SIZE = (int) (com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightPortrait() * FONT_SIZE_ON_SCREEN);
            X = com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldWidthPortrait();
            Y = com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightPortrait();
            getTextBox().setSize(com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldWidthPortrait() * 0.92f, com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightPortrait() * 0.65f);
        } else {
            FONT_SIZE = (int) (com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldWidthLandscape() * FONT_SIZE_ON_SCREEN);
            X = com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldWidthLandscape();
            Y = com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightLandscape();
            getTextBox().setSize(com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldWidthLandscape() * 0.72f, com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightLandscape() * 0.65f);

        }

        if (X < Y) {
            rad.setSize(X * 0.8f, X * 0.8f);
            radPointer.setSize(X * 0.05f, X * 0.05f);
            getButtons()[0][0].setSize(Y * 0.08f * getButtons()[0][0].getWidth() / getButtons()[0][0].getHeight(), Y * 0.08f);
            getButtons()[0][1].setSize(Y * 0.08f * getButtons()[0][0].getWidth() / getButtons()[0][0].getHeight(), Y * 0.08f);
            getButtons()[1][0].setSize(Y * 0.08f, Y * 0.08f);
            getButtons()[1][1].setSize(Y * 0.08f, Y * 0.08f);

        } else {
            rad.setSize(Y * 0.65f, Y * 0.65f);
            getButtons()[0][0].setSize(Y * 0.1f * getButtons()[0][0].getWidth() / getButtons()[0][0].getHeight(), Y * 0.1f);
            getButtons()[0][1].setSize(Y * 0.1f * getButtons()[0][0].getWidth() / getButtons()[0][0].getHeight(), Y * 0.1f);
            getButtons()[1][0].setSize(Y * 0.1f, Y * 0.1f);
            getButtons()[1][1].setSize(Y * 0.1f, Y * 0.1f);
            radPointer.setSize(Y * 0.05f, Y * 0.05f);
        }
        rad.setOrigin(rad.getWidth() / 2, rad.getHeight() / 2);


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
        //text = splitLine();

    }

    @Override
    protected void drawText() {

        if (KingsCupSpecialModel.getInstance().isShowTask()) {
            float SPACE_BETWEEN_TWO_LINES_WITHOUT_ENTER = 1.75f;
            float SPACE_BETWEEN_TWO_LINES_WITH_ENTER = 2.5f;
            float EMPTYCHAR_CHAR_WIDTH_RATIO = 1.7f;
            if (KingsCupSpecialModel.getInstance().isShowTask()) {
                getTextBox().setPosition(X / 2 - getTextBox().getWidth() / 2, Y / 2 - getTextBox().getHeight() / 2);
                getTextBox().draw(getSpriteBatch());
            }
            plainText = KingsCupSpecialModel.getInstance().getTasks().get(KingsCupSpecialModel.getInstance().getRadValues()[KingsCupSpecialModel.getInstance().getItemPosition()]);
            text = splitLine();
            float y_offset = 0f;
            for (int i = 0; i < text.size(); i++) {

                if (text.get(i).contains(":"))
                    fontLabel.draw(getSpriteBatch(), text.get(i),
                            (X - text.get(i).length() * font.getSpaceWidth() * EMPTYCHAR_CHAR_WIDTH_RATIO) * 0.5f,
                            getTextBox().getY() + getTextBox().getHeight() - font.getCapHeight() * 3f - y_offset);
                else
                    font.draw(getSpriteBatch(), text.get(i),
                            (X - text.get(i).length() * font.getSpaceWidth() * EMPTYCHAR_CHAR_WIDTH_RATIO) * 0.5f,
                            getTextBox().getY() + getTextBox().getHeight() - font.getCapHeight() * 3f - y_offset);

                if (text.get(i).indexOf('\n') > -1)
                    y_offset += font.getCapHeight() * SPACE_BETWEEN_TWO_LINES_WITH_ENTER;
                else
                    y_offset += font.getCapHeight() * SPACE_BETWEEN_TWO_LINES_WITHOUT_ENTER;
            }
            getButtons()[1][getClicked()[1] ? 1 : 0].setPosition(getTextBox().getX() /*+ getTextBox().getWidth() / 2 - getButtons()[i][0].getWidth() / 2*/,
                    getTextBox().getY() /*- rad.getHeight() + getButtons()[i][0].getHeight() * 1.2f*/);
            getButtons()[1][getClicked()[1] ? 1 : 0].draw(getSpriteBatch());

        }
    }


    @Override
    protected void initTextBox() {
        setTextBox(new Sprite(new Texture(Configuration.getLanguage() + "/kingsCupSpecial/KingsCupSpecial-TextBox.png")));

    }

    @Override
    protected void initButtons() {
        setButtons(new CheckedButton[2][2]);

        getButtons()[0][0] = new CheckedButton(new Texture("common/kingscupbutton.png"));
        getButtons()[0][1] = new CheckedButton(new Texture("common/kingscupbutton_white.png"));
        getButtons()[1][0] = new CheckedButton(new Texture("common/rules_ok.png"));
        getButtons()[1][1] = new CheckedButton(new Texture("common/rules_ok_white.png"));

        setClicked(new boolean[getCountOfButtons()]);
    }

    @Override
    protected void drawButtons() {
        getButtons()[0][getClicked()[0] ? 1 : 0].setPosition(X / 2 - getButtons()[0][0].getWidth() / 2, rad.getY() - getButtons()[0][0].getHeight());
        getButtons()[0][getClicked()[0] ? 1 : 0].draw(getSpriteBatch());

    }

    @Override
    protected void initLogo() {

    }

    @Override
    protected void drawLogo() {

    }

    @Override
    protected void initCards() {
        rad = new Sprite(new Texture("common/Rad.png"));
        radPointer = new Sprite(new Texture("common/arrow.png"));

    }

    @Override
    protected void initLoadingSprite() {

    }

    @Override
    protected void drawLoadingSprite() {

    }

    @Override
    protected void drawCards() {
        rad.setPosition(X / 2 - rad.getWidth() / 2, Y / 2 - rad.getHeight() / 2);
        rotation = KingsCupSpecialModel.getInstance().getRotation();
        rad.rotate(rotation);
        float rot = rad.getRotation();
        KingsCupSpecialModel.getInstance().setRadPosition(rot);
        rad.draw(getSpriteBatch());
        radPointer.setPosition(X / 2 - radPointer.getWidth() / 2, rad.getY() + rad.getHeight() - radPointer.getHeight() / 2);
        radPointer.draw(getSpriteBatch());
    }

    @Override
    public void dispose() {

        if (font != null)
            font.dispose();
        if (fontLabel != null)
            fontLabel.dispose();
        if (rad != null)
            rad.getTexture().dispose();
        if (radPointer != null)
            radPointer.getTexture().dispose();
        super.dispose();
    }

    @Override
    public void setRulesButtonSpritePosition() {
        if (getRulesButtonSprite() != null) {
            if (Orientation.getOrientation() == Input.Orientation.Portrait) {
                getRulesButtonSprite().setPosition(com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldWidthPortrait() - getRulesButtonSprite().getWidth(),
                        com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightPortrait() - getRulesButtonSprite().getHeight() * 2.5f);
            }else{
                getRulesButtonSprite().setPosition(com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldWidthLandscape() - getRulesButtonSprite().getWidth(),
                        com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightLandscape() - getRulesButtonSprite().getHeight());
            }
        }

    }

}
