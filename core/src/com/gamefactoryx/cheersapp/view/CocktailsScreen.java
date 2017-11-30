package com.gamefactoryx.cheersapp.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import java.util.List;


/**
 * Created by bernat on 16.05.2017.
 */
public class CocktailsScreen extends AbstractScreen {


    private final static FileHandle fontFile = Gdx.files.internal("font/TIMESS.ttf");
    private final static FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
    private FreeTypeFontGenerator generator;
    private int FONT_SIZE;
    private float X, Y;
    private BitmapFont font, fontLabel;


    public CocktailsScreen() {
        super();
    }


    @Override
    public void show() {
        super.show();
        initBackButton();
    }


    @Override
    protected void initSprites() {
        setLandscapeSprite(new Sprite(new Texture("common/cocktails/background.png")));
        setPortraitSprite(new Sprite(new Texture("common/cocktails/background.png")));
        getLandscapeSprite().setSize(com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldWidthLandscape(), com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightLandscape());
        getPortraitSprite().setSize(com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldWidthPortrait(), com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightPortrait());
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        float FONT_SIZE_ON_SCREEN = 0.028f;
        super.resize(width, height);
        if (com.gamefactoryx.cheersapp.tool.Configuration.getLanguage() == com.gamefactoryx.cheersapp.tool.Configuration.LanguageEnum.SK)
            generator = new FreeTypeFontGenerator(com.gamefactoryx.cheersapp.tool.FontHelper.getSkFontFile());
        else
            generator = new FreeTypeFontGenerator(com.gamefactoryx.cheersapp.tool.FontHelper.getFontFile());


        if (com.gamefactoryx.cheersapp.tool.Orientation.getOrientation() == Input.Orientation.Portrait) {
            FONT_SIZE = (int) (com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightPortrait() * FONT_SIZE_ON_SCREEN);
            X = com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldWidthPortrait();
            Y = com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightPortrait();
            getTextBox().setSize(com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldWidthPortrait() * 0.90f, com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightPortrait() * 0.85f);
        } else {
            FONT_SIZE = (int) (com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldWidthLandscape() * FONT_SIZE_ON_SCREEN);
            X = com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldWidthLandscape();
            Y = com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightLandscape();
            getTextBox().setSize(com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldWidthLandscape() * 0.90f, com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightLandscape() * 0.85f);

        }

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
    }

    @Override
    protected void drawText() {
        float SPACE_BETWEEN_TWO_LINES_WITHOUT_ENTER = 1.75f;
        float SPACE_BETWEEN_TWO_LINES_WITH_ENTER = 2.5f;
        float EMPTYCHAR_CHAR_WIDTH_RATIO = 1.6f;
        float EMPTYCHAR_CHAR_WIDTH_RATIO_FOR_LABEL = 1.9f;
        if (com.gamefactoryx.cheersapp.model.CocktailsModel.getInstance().getCocktailToDisplay() > -1) {

            if (com.gamefactoryx.cheersapp.tool.Orientation.getOrientation() == Input.Orientation.Portrait)
                getTextBox().setPosition(X * 0.05f, Y * 0.02f);
            else
                getTextBox().setPosition(X * 0.05f, Y * 0.02f);

            getTextBox().draw(getSpriteBatch());


            List<String> text = com.gamefactoryx.cheersapp.model.CocktailsModel.getInstance().getCocktailText().get(com.gamefactoryx.cheersapp.model.CocktailsModel.getInstance().getCocktailToDisplay());
            float y_offset = 0f;
            for (int i = 0; i < text.size(); i++) {

                if (i == 0)
                    fontLabel.draw(getSpriteBatch(), text.get(i),
                            (X - text.get(i).length() * font.getSpaceWidth() * EMPTYCHAR_CHAR_WIDTH_RATIO_FOR_LABEL) * 0.5f,
                            getTextBox().getHeight() - font.getCapHeight() * 1.3f - y_offset);
                else
                    font.draw(getSpriteBatch(), text.get(i),
                            (X - text.get(i).length() * font.getSpaceWidth() * EMPTYCHAR_CHAR_WIDTH_RATIO) * 0.5f,
                            getTextBox().getHeight() - font.getCapHeight() * 1.3f - y_offset);

                if (text.get(i).indexOf('\n') > -1)
                    y_offset += font.getCapHeight() * SPACE_BETWEEN_TWO_LINES_WITH_ENTER;
                else
                    y_offset += font.getCapHeight() * SPACE_BETWEEN_TWO_LINES_WITHOUT_ENTER;
            }
            getButtons()[getCountOfButtons() - 1][getClicked()[getCountOfButtons() - 1] ? 1 : 0].setPosition(getTextBox().getX() /*+ getTextBox().getWidth() / 2 - getButtons()[i][0].getWidth() / 2*/,
                    getTextBox().getY() /*- rad.getHeight() + getButtons()[i][0].getHeight() * 1.2f*/);
            getButtons()[getCountOfButtons() - 1][getClicked()[getCountOfButtons() - 1] ? 1 : 0].draw(getSpriteBatch());

        }
    }


    @Override
    protected void initTextBox() {
        setTextBox(new Sprite(new Texture("common/cocktails/TextBox.png")));
    }

    @Override
    protected void initButtons() {
        setButtons(new CheckedButton[com.gamefactoryx.cheersapp.model.CocktailsModel.cocktailNames.length + 1 + 1] [2]);

        for (int i = 0; i < com.gamefactoryx.cheersapp.model.CocktailsModel.cocktailNames.length; ++i) {
            for (int j = 0; j < 2; j++) {

                getButtons()[i][j] = new CheckedButton(new Texture("common/cocktails/" + com.gamefactoryx.cheersapp.model.CocktailsModel.cocktailNames[i] + ".png"));
                getButtons()[i][j].setSize(com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldWidthPortrait() * 0.65f, com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightPortrait() * 0.077f);
            }
        }

        getButtons()[com.gamefactoryx.cheersapp.model.CocktailsModel.cocktailNames.length][0] = new CheckedButton(new Texture("common/gold.png"));
        getButtons()[com.gamefactoryx.cheersapp.model.CocktailsModel.cocktailNames.length][1] = new CheckedButton(new Texture("common/grey.png"));
        getButtons()[com.gamefactoryx.cheersapp.model.CocktailsModel.cocktailNames.length][0].setSize(com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldWidthPortrait() * 0.03f, com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightPortrait() * com.gamefactoryx.cheersapp.tool.Resolution.getAspectRatio() * 0.03f);
        getButtons()[com.gamefactoryx.cheersapp.model.CocktailsModel.cocktailNames.length][1].setSize(com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldWidthPortrait() * 0.03f, com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightPortrait() * com.gamefactoryx.cheersapp.tool.Resolution.getAspectRatio() * 0.03f);
        getButtons()[com.gamefactoryx.cheersapp.model.CocktailsModel.cocktailNames.length + 1][0] = new CheckedButton(new Texture("common/rules_ok.png"));
        getButtons()[com.gamefactoryx.cheersapp.model.CocktailsModel.cocktailNames.length + 1][1] = new CheckedButton(new Texture("common/rules_ok_white.png"));

        setClicked(new boolean[getCountOfButtons()]);
    }

    @Override
    protected void drawButtons() {
        float PORTRAIT_DISTANCE_FROM_BOTTOM = 0.76f;
        int y_offset = 0;
        int page = com.gamefactoryx.cheersapp.model.CocktailsModel.getInstance().getPage();
        int maxCocktailsProPage = 4;


        for (int i = (page - 1) * maxCocktailsProPage; i < maxCocktailsProPage + (page - 1) * maxCocktailsProPage; i++) {
            if (i >= 0 && i < com.gamefactoryx.cheersapp.model.CocktailsModel.cocktailNames.length) {
                int click_index = getClicked()[i] ? CLICKED : FREE;
                getButtons()[i][click_index].setPosition(X * 0.18f,
                        Y * PORTRAIT_DISTANCE_FROM_BOTTOM - y_offset * getButtons()[i][click_index].getHeight() * 1.475f);
                getButtons()[i][click_index].draw(getSpriteBatch(), 1.0f);
                ++y_offset;
            }
        }


        float x_offset = getButtons()[com.gamefactoryx.cheersapp.model.CocktailsModel.cocktailNames.length][1].getWidth() * 2.0f;
        for (int i = 0; i < com.gamefactoryx.cheersapp.model.CocktailsModel.getInstance().getMaxPages(); i++) {
            if (page - 1 == i) {
                getButtons()[com.gamefactoryx.cheersapp.model.CocktailsModel.cocktailNames.length][0].setPosition(X / 2 - com.gamefactoryx.cheersapp.model.CocktailsModel.getInstance().getMaxPages() * getButtons()[com.gamefactoryx.cheersapp.model.CocktailsModel.cocktailNames.length][0].getWidth() + x_offset * i, Y * 0.36f);
                getButtons()[com.gamefactoryx.cheersapp.model.CocktailsModel.cocktailNames.length][0].draw(getSpriteBatch(), 1.0f);
            } else {
                getButtons()[com.gamefactoryx.cheersapp.model.CocktailsModel.cocktailNames.length][1].setPosition(X / 2 - com.gamefactoryx.cheersapp.model.CocktailsModel.getInstance().getMaxPages() * getButtons()[com.gamefactoryx.cheersapp.model.CocktailsModel.cocktailNames.length][1].getWidth() + x_offset * i, Y * 0.36f);
                getButtons()[com.gamefactoryx.cheersapp.model.CocktailsModel.cocktailNames.length][1].draw(getSpriteBatch(), 1.0f);

            }
        }

    }

    @Override
    protected void initLogo() {
        setLogo(new Sprite(new Texture("common/logo.png")));
        float logoRatio = getLogo().getHeight() / getLogo().getWidth();
        getLogo().setSize(com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldWidthPortrait() * 0.4f, com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightPortrait() * com.gamefactoryx.cheersapp.tool.Resolution.getAspectRatio() * 0.4f * logoRatio);
        getLogo().setPosition(com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldWidthPortrait() / 3.65f, com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightPortrait() / 7f);
    }

    @Override
    protected void drawLogo() {
        getLogo().draw(getSpriteBatch(), 1.0f);
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


}
