package com.gamefactoryx.cheers.view.kongosdrink;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.gamefactoryx.cheers.model.Subject;
import com.gamefactoryx.cheers.model.kongosdrink.KongosDrinkPhase0Model;
import com.gamefactoryx.cheers.tool.FontHelper;
import com.gamefactoryx.cheers.tool.Orientation;
import com.gamefactoryx.cheers.tool.Resolution;
import com.gamefactoryx.cheers.tool.kongosdrink.Configuration;
import com.gamefactoryx.cheers.view.AbstractScreen;


/**
 * Created by bernat on 16.05.2017.
 */
public class KongosDrinkPhase0Screen extends AbstractScreen {


    private float X, Y;

    private BitmapFont font;
    private FreeTypeFontGenerator generator;
    private int FONT_SIZE;


    public KongosDrinkPhase0Screen() {
        super();
    }


    @Override
    public void show() {
        super.show();
        initBackButton();
    }


    @Override
    protected void initSprites() {
        setLandscapeSprite(new Sprite(new Texture(com.gamefactoryx.cheers.tool.Configuration.getLanguage() + "/kongosdrink/kongosridescreen.png")));
        setPortraitSprite(new Sprite(new Texture(com.gamefactoryx.cheers.tool.Configuration.getLanguage() + "/kongosdrink/kongosridescreen.png")));
        getLandscapeSprite().setSize(Resolution.getGameWorldWidthLandscape(), Resolution.getGameWorldHeightLandscape());
        getPortraitSprite().setSize(Resolution.getGameWorldWidthPortrait(), Resolution.getGameWorldHeightPortrait());
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        float FONT_SIZE_ON_SCREEN = 0.03f;
        if (com.gamefactoryx.cheers.tool.Configuration.getLanguage() == com.gamefactoryx.cheers.tool.Configuration.LanguageEnum.SK)
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

        FontHelper.getParameter().size = FONT_SIZE;
        FontHelper.getParameter().color = new Color(166.0f / 255.0f, 124.0f / 255.0f, 82f / 255.0f, 1f);
        BitmapFont temp = font;
        font = generator.generateFont(FontHelper.getParameter());
        if (temp != null)
            temp.dispose();
        generator.dispose();
    }

    @Override
    protected void drawText() {
        float DISTANCE_FROM_TEXTBOX_BOTTOM = 0.81f;
        int y_offset = 0;
        int page = KongosDrinkPhase0Model.getInstance().getPage();
        int maxPlayersProPage = com.gamefactoryx.cheers.tool.Configuration.getMaxPlayersProConfigPage();
        for (int i = maxPlayersProPage * (page - 1) ; i < maxPlayersProPage * (page - 1) + getMaxPlayers(); i++) {
            String name = KongosDrinkPhase0Model.getInstance().getPlayers().get(i).getName();
            FontHelper.getGlyphLayout().setText(font, name);
            font.draw(getSpriteBatch(), FontHelper.getGlyphLayout(), X * 0.46f - FontHelper.getGlyphLayout().width / 2.4f,
                    (Y * DISTANCE_FROM_TEXTBOX_BOTTOM) - y_offset++ * FontHelper.getGlyphLayout().height * 5.72f);
        }
    }


    @Override
    protected void initTextBox() {
        //setTextBox(new Sprite(new Texture(com.gamefactoryx.cheers.tool.Configuration.getLanguage() + "/Busdrivingscreen/busdriving_names/namebox_busdriving.png")));
    }

    @Override
    protected void initButtons() {
        setButtons(new Sprite[8][2]);
        Texture txt = new Texture(com.gamefactoryx.cheers.tool.Configuration.getLanguage() + "/Busdrivingscreen/busdriving_names/namebox_busdriving.png");
        for (int i = 0; i < getCountOfButtons() - 3; i++)
            for (int j = 0; j < 2; j++) {
                getButtons()[i][j] = new Sprite(txt);
                getButtons()[i][j].setSize(Resolution.getGameWorldWidthPortrait() * 0.65f, Resolution.getGameWorldHeightPortrait() * 0.077f);
            }

        getButtons()[4][0] = new Sprite(new Texture(com.gamefactoryx.cheers.tool.Configuration.getLanguage() + "/Busdrivingscreen/busdriving_names/okay.png"));
        getButtons()[4][1] = new Sprite(new Texture(com.gamefactoryx.cheers.tool.Configuration.getLanguage() + "/Busdrivingscreen/busdriving_names/bad.png"));
        getButtons()[4][0].setSize(Resolution.getGameWorldWidthPortrait() * 0.1f, Resolution.getGameWorldHeightPortrait() * Resolution.getAspectRatio() * 0.1f);
        getButtons()[4][1].setSize(Resolution.getGameWorldWidthPortrait() * 0.1f, Resolution.getGameWorldHeightPortrait() * Resolution.getAspectRatio() * 0.1f);

        getButtons()[5][0] = new Sprite(new Texture("common/kongos_drink/1st_side/boys.png"));
        getButtons()[5][1] = new Sprite(new Texture("common/kongos_drink/1st_side/girls.png"));
        getButtons()[5][0].setSize(Resolution.getGameWorldWidthPortrait() * 0.1f, Resolution.getGameWorldHeightPortrait() * Resolution.getAspectRatio() * 0.1f);
        getButtons()[5][1].setSize(Resolution.getGameWorldWidthPortrait() * 0.1f, Resolution.getGameWorldHeightPortrait() * Resolution.getAspectRatio() * 0.1f);

        getButtons()[6][0] = new Sprite(new Texture("common/continue.png"));
        getButtons()[6][1] = new Sprite(new Texture("common/continue.png"));
        getButtons()[6][0].setSize(Resolution.getGameWorldWidthPortrait() * 0.2f, Resolution.getGameWorldHeightPortrait() * Resolution.getAspectRatio() * 0.2f);
        getButtons()[6][1].setSize(Resolution.getGameWorldWidthPortrait() * 0.2f, Resolution.getGameWorldHeightPortrait() * Resolution.getAspectRatio() * 0.2f);

        getButtons()[7][0] = new Sprite(new Texture("common/gold.png"));
        getButtons()[7][1] = new Sprite(new Texture("common/grey.png"));
        getButtons()[7][0].setSize(Resolution.getGameWorldWidthPortrait() * 0.03f, Resolution.getGameWorldHeightPortrait() * Resolution.getAspectRatio() * 0.03f);
        getButtons()[7][1].setSize(Resolution.getGameWorldWidthPortrait() * 0.03f, Resolution.getGameWorldHeightPortrait() * Resolution.getAspectRatio() * 0.03f);

        setClicked(new boolean[getCountOfButtons()]);
    }

    @Override
    protected void drawButtons() {
        float PORTRAIT_DISTANCE_FROM_BOTTOM = 0.76f;
        int y_offset = 0;
        int page = KongosDrinkPhase0Model.getInstance().getPage();
        int maxPlayersProPage = com.gamefactoryx.cheers.tool.Configuration.getMaxPlayersProConfigPage();


        for (int i = 0; i < getMaxPlayers(); i++) {
            int click_index = getClicked()[i] ? CLICKED : FREE;
            getButtons()[i][click_index].setPosition(X * 0.18f,
                    Y * PORTRAIT_DISTANCE_FROM_BOTTOM - y_offset * getButtons()[i][click_index].getHeight() * 1.475f);
            getButtons()[i][click_index].draw(getSpriteBatch(),  Configuration.getInstance().getPlayers().get(i + maxPlayersProPage * (page - 1)).isEnable() ? 1.0f : 100.0f);
            if (i > 1 || KongosDrinkPhase0Model.getInstance().getPage() > 1) {
                getButtons()[4][Configuration.getInstance().getPlayers().get(i + maxPlayersProPage * (page - 1)).isEnable() ? 0 : 1].setPosition(X * 0.75f,
                        Y * PORTRAIT_DISTANCE_FROM_BOTTOM - y_offset * getButtons()[i][0].getHeight() * 1.475f);
                getButtons()[4][Configuration.getInstance().getPlayers().get(i + maxPlayersProPage * (page - 1)).isEnable() ? 0 : 1].draw(getSpriteBatch(), 1.0f);
            }
            getButtons()[5][Configuration.getInstance().getPlayers().get(i + maxPlayersProPage * (page - 1)).getSex() == Subject.Sex.MALE ? 0 : 1].setPosition(X * 0.16f,
                        Y * PORTRAIT_DISTANCE_FROM_BOTTOM - y_offset * getButtons()[i][0].getHeight() * 1.475f);
            getButtons()[5][Configuration.getInstance().getPlayers().get(i + maxPlayersProPage * (page - 1)).getSex() == Subject.Sex.MALE ? 0 : 1].draw(getSpriteBatch(), 1.0f);
            ++y_offset;
        }

        getButtons()[6][0].setPosition(X * 0.75f, Y * 0.02f);
        getButtons()[6][0].draw(getSpriteBatch(), 1.0f);

        float x_offset =  getButtons()[7][1].getWidth() * 2.0f;
        for(int i = 0; i < KongosDrinkPhase0Model.getInstance().getMaxPages(); i++) {
            if( KongosDrinkPhase0Model.getInstance().getPage() - 1 == i) {
                getButtons()[7][0].setPosition(X/2 - KongosDrinkPhase0Model.getInstance().getMaxPages() * getButtons()[7][0].getWidth() + x_offset * i, Y * 0.36f);
                getButtons()[7][0].draw(getSpriteBatch(), 1.0f);
            }
            else{
                getButtons()[7][1].setPosition(X/2 - KongosDrinkPhase0Model.getInstance().getMaxPages() * getButtons()[7][1].getWidth() + x_offset * i, Y * 0.36f);
                getButtons()[7][1].draw(getSpriteBatch(), 1.0f);

            }
        }

    }

    @Override
    protected void initLogo() {
        setLogo(new Sprite(new Texture("common/logo.png")));
        float logoRatio = getLogo().getHeight() / getLogo().getWidth();
        getLogo().setSize(Resolution.getGameWorldWidthPortrait() * 0.4f, Resolution.getGameWorldHeightPortrait() * Resolution.getAspectRatio() * 0.4f * logoRatio);
        getLogo().setPosition(Resolution.getGameWorldWidthPortrait()/3.65f, Resolution.getGameWorldHeightPortrait()/7f);
    }

    @Override
    protected void drawLogo() {
        getLogo().draw(getSpriteBatch(), 75.0f);
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

    private int getMaxPlayers(){
        int page = KongosDrinkPhase0Model.getInstance().getPage();
        int maxPlayersProPage = com.gamefactoryx.cheers.tool.Configuration.getMaxPlayersProConfigPage();
        return  Configuration.getInstance().getMaxPlayers() >= (page * maxPlayersProPage) ? maxPlayersProPage:
                maxPlayersProPage - (page * maxPlayersProPage - Configuration.getInstance().getMaxPlayers());
    }

}
