package com.gamefactoryx.cheersapp.view.busdriving;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.gamefactoryx.cheersapp.model.busdriving.Croupier;
import com.gamefactoryx.cheersapp.tool.FontHelper;
import com.gamefactoryx.cheersapp.view.AbstractScreen;
import com.gamefactoryx.cheersapp.view.CheckedButton;


/**
 * Created by bernat on 16.05.2017.
 */
public class BusDrivingPhase0Screen extends AbstractScreen {


    private float X, Y;

    private BitmapFont font;
    private FreeTypeFontGenerator generator;
    private int FONT_SIZE;

    public BusDrivingPhase0Screen() {
        super();
    }


    @Override
    public void show() {
        super.show();
        initBackButton();
        initRulesButton(com.gamefactoryx.cheersapp.model.busdriving.BusDrivingPhase0Model.getInstance());
    }


    @Override
    protected void initSprites() {
        setLandscapeSprite(new Sprite(new Texture(com.gamefactoryx.cheersapp.tool.Configuration.getLanguage() + "/Busdrivingscreen/busdrivingscreen-landscape.png")));
        setPortraitSprite(new Sprite(new Texture(com.gamefactoryx.cheersapp.tool.Configuration.getLanguage() + "/Busdrivingscreen/busdrivingscreen.png")));
        getLandscapeSprite().setSize(com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldWidthLandscape(), com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightLandscape());
        getPortraitSprite().setSize(com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldWidthPortrait(), com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightPortrait());
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        float FONT_SIZE_ON_SCREEN = 0.03f;
        if (com.gamefactoryx.cheersapp.tool.Configuration.getLanguage() == com.gamefactoryx.cheersapp.tool.Configuration.LanguageEnum.SK)
            generator = new FreeTypeFontGenerator(com.gamefactoryx.cheersapp.tool.FontHelper.getSkFontFile());
        else
            generator = new FreeTypeFontGenerator(com.gamefactoryx.cheersapp.tool.FontHelper.getFontFile());

        if (com.gamefactoryx.cheersapp.tool.Orientation.getOrientation() == Input.Orientation.Portrait) {
            FONT_SIZE = (int) (com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightPortrait() * FONT_SIZE_ON_SCREEN);
            X = com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldWidthPortrait();
            Y = com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightPortrait();
        } else {
            FONT_SIZE = (int) (com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldWidthLandscape() * FONT_SIZE_ON_SCREEN);
            X = com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldWidthLandscape();
            Y = com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightLandscape();
        }

        com.gamefactoryx.cheersapp.tool.FontHelper.getParameter().size = FONT_SIZE;
        com.gamefactoryx.cheersapp.tool.FontHelper.getParameter().color = new Color(166.0f / 255.0f, 124.0f / 255.0f, 82f / 255.0f, 1f);
        BitmapFont temp = font;
        font = generator.generateFont(com.gamefactoryx.cheersapp.tool.FontHelper.getParameter());
        if (temp != null)
            temp.dispose();
        generator.dispose();
        getLoadingSprite().setSize(X * 0.4f, X * 0.4f
                * getLoadingSprite().getHeight()/getLoadingSprite().getWidth());
        getLoadingSprite().setPosition(X * 0.5f - getLoadingSprite().getWidth() * 0.5f, Y * 0.5f - getLoadingSprite().getHeight() * 0.5f);
    }

    @Override
    protected void drawText() {
        float DISTANCE_FROM_TEXTBOX_BOTTOM = 0.81f;
        int y_offset = 0;
        int page = com.gamefactoryx.cheersapp.model.busdriving.BusDrivingPhase0Model.getInstance().getPage();
        int maxPlayersProPage = com.gamefactoryx.cheersapp.tool.Configuration.getMaxPlayersProConfigPage();
        for (int i = maxPlayersProPage * (page - 1) ; i < maxPlayersProPage * (page - 1) + getMaxPlayers(); i++) {
            String name = com.gamefactoryx.cheersapp.model.busdriving.BusDrivingPhase0Model.getInstance().getPlayers().get(i).getName();
            com.gamefactoryx.cheersapp.tool.FontHelper.getGlyphLayout().setText(font, name);
            font.draw(getSpriteBatch(), com.gamefactoryx.cheersapp.tool.FontHelper.getGlyphLayout(), X * 0.46f - com.gamefactoryx.cheersapp.tool.FontHelper.getGlyphLayout().width / 2.4f,
                    (Y * DISTANCE_FROM_TEXTBOX_BOTTOM) - y_offset++ * FontHelper.getGlyphLayout().height * 5.72f);
        }
    }


    @Override
    protected void initTextBox() {
        setTextBox(new Sprite(new Texture(com.gamefactoryx.cheersapp.tool.Configuration.getLanguage() + "/Busdrivingscreen/busdriving_names/namebox_busdriving.png")));
    }

    @Override
    protected void initButtons() {
        setButtons(new CheckedButton[8][2]);
        Texture txt = new Texture(com.gamefactoryx.cheersapp.tool.Configuration.getLanguage() + "/Busdrivingscreen/busdriving_names/namebox_busdriving.png");
        for (int i = 0; i < getCountOfButtons() - 3; i++)
            for (int j = 0; j < 2; j++) {
                getButtons()[i][j] = new CheckedButton(txt);
                getButtons()[i][j].setSize(com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldWidthPortrait() * 0.65f, com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightPortrait() * 0.077f);
            }

        getButtons()[4][0] = new CheckedButton(new Texture(com.gamefactoryx.cheersapp.tool.Configuration.getLanguage() + "/Busdrivingscreen/busdriving_names/okay.png"));
        getButtons()[4][1] = new CheckedButton(new Texture(com.gamefactoryx.cheersapp.tool.Configuration.getLanguage() + "/Busdrivingscreen/busdriving_names/bad.png"));
        getButtons()[4][0].setSize(com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldWidthPortrait() * 0.1f, com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightPortrait() * com.gamefactoryx.cheersapp.tool.Resolution.getAspectRatio() * 0.1f);
        getButtons()[4][1].setSize(com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldWidthPortrait() * 0.1f, com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightPortrait() * com.gamefactoryx.cheersapp.tool.Resolution.getAspectRatio() * 0.1f);

        getButtons()[5][0] = new CheckedButton(new Texture("common/kongos_drink/1st_side/boys.png"));
        getButtons()[5][1] = new CheckedButton(new Texture("common/kongos_drink/1st_side/girls.png"));
        getButtons()[5][0].setSize(com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldWidthPortrait() * 0.1f, com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightPortrait() * com.gamefactoryx.cheersapp.tool.Resolution.getAspectRatio() * 0.1f);
        getButtons()[5][1].setSize(com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldWidthPortrait() * 0.1f, com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightPortrait() * com.gamefactoryx.cheersapp.tool.Resolution.getAspectRatio() * 0.1f);

        getButtons()[6][0] = new CheckedButton(new Texture("common/continue.png"));
        getButtons()[6][1] = new CheckedButton(new Texture("common/continue.png"));
        getButtons()[6][0].setSize(com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldWidthPortrait() * 0.2f, com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightPortrait() * com.gamefactoryx.cheersapp.tool.Resolution.getAspectRatio() * 0.2f);
        getButtons()[6][1].setSize(com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldWidthPortrait() * 0.2f, com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightPortrait() * com.gamefactoryx.cheersapp.tool.Resolution.getAspectRatio() * 0.2f);

        getButtons()[7][0] = new CheckedButton(new Texture("common/gold.png"));
        getButtons()[7][1] = new CheckedButton(new Texture("common/grey.png"));
        getButtons()[7][0].setSize(com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldWidthPortrait() * 0.03f, com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightPortrait() * com.gamefactoryx.cheersapp.tool.Resolution.getAspectRatio() * 0.03f);
        getButtons()[7][1].setSize(com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldWidthPortrait() * 0.03f, com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightPortrait() * com.gamefactoryx.cheersapp.tool.Resolution.getAspectRatio() * 0.03f);


        setClicked(new boolean[getCountOfButtons()]);
    }

    @Override
    protected void drawButtons() {
        float PORTRAIT_DISTANCE_FROM_BOTTOM = 0.76f;
        int y_offset = 0;
        int page = com.gamefactoryx.cheersapp.model.busdriving.BusDrivingPhase0Model.getInstance().getPage();
        int maxPlayersProPage = com.gamefactoryx.cheersapp.tool.Configuration.getMaxPlayersProConfigPage();
        int maxPlayers = com.gamefactoryx.cheersapp.tool.Configuration.getMaxPlayers() >= (page * maxPlayersProPage) ? maxPlayersProPage:
                page * maxPlayersProPage - com.gamefactoryx.cheersapp.tool.Configuration.getMaxPlayers();

       /* for (int i = 0; i < maxPlayers; i++) {
            int click_index = getClicked()[i] ? CLICKED : FREE;
            getButtons()[i][click_index].setPosition(X * 0.18f,
                    Y * PORTRAIT_DISTANCE_FROM_BOTTOM - y_offset * getButtons()[i][click_index].getHeight() * 1.475f);
            getButtons()[i][click_index].draw(getSpriteBatch(), Croupier.getInstance().getPlayers().get(i).isEnable() ? 1.0f : 100.0f);
            if (i > 1) {
                getButtons()[4][Croupier.getInstance().getPlayers().get(i).isEnable() ? 0 : 1].setPosition(X * 0.75f,
                        Y * PORTRAIT_DISTANCE_FROM_BOTTOM - y_offset * getButtons()[i][0].getHeight() * 1.475f);
                getButtons()[4][Croupier.getInstance().getPlayers().get(i).isEnable() ? 0 : 1].draw(getSpriteBatch(), 1.0f);
            }
            getButtons()[5][Croupier.getInstance().getPlayers().get(i).getSex() == Subject.Sex.MALE ? 0 : 1].setPosition(X * 0.16f,
                    Y * PORTRAIT_DISTANCE_FROM_BOTTOM - y_offset * getButtons()[i][0].getHeight() * 1.475f);
            getButtons()[5][Croupier.getInstance().getPlayers().get(i).getSex() == Subject.Sex.MALE ? 0 : 1].draw(getSpriteBatch(), 1.0f);
            ++y_offset;

        }*/

        for (int i = 0; i < getMaxPlayers(); i++) {
            int click_index = getClicked()[i] ? CLICKED : FREE;
            getButtons()[i][click_index].setPosition(X * 0.18f,
                    Y * PORTRAIT_DISTANCE_FROM_BOTTOM - y_offset * getButtons()[i][click_index].getHeight() * 1.475f);
            getButtons()[i][click_index].draw(getSpriteBatch(),  Croupier.getInstance().getPlayers().get(i + maxPlayersProPage * (page - 1)).isEnable() ? 1.0f : 100.0f);
            if (i > 1 || com.gamefactoryx.cheersapp.model.busdriving.BusDrivingPhase0Model.getInstance().getPage() > 1) {
                getButtons()[4][Croupier.getInstance().getPlayers().get(i + maxPlayersProPage * (page - 1)).isEnable() ? 0 : 1].setPosition(X * 0.75f,
                        Y * PORTRAIT_DISTANCE_FROM_BOTTOM - y_offset * getButtons()[i][0].getHeight() * 1.475f);
                getButtons()[4][Croupier.getInstance().getPlayers().get(i + maxPlayersProPage * (page - 1)).isEnable() ? 0 : 1].draw(getSpriteBatch(), 1.0f);
            }
            getButtons()[5][Croupier.getInstance().getPlayers().get(i + maxPlayersProPage * (page - 1)).getSex() == com.gamefactoryx.cheersapp.model.Subject.Sex.MALE ? 0 : 1].setPosition(X * 0.16f,
                    Y * PORTRAIT_DISTANCE_FROM_BOTTOM - y_offset * getButtons()[i][0].getHeight() * 1.475f);
            getButtons()[5][Croupier.getInstance().getPlayers().get(i + maxPlayersProPage * (page - 1)).getSex() == com.gamefactoryx.cheersapp.model.Subject.Sex.MALE ? 0 : 1].draw(getSpriteBatch(), 1.0f);
            ++y_offset;
        }

        getButtons()[6][0].setPosition(X * 0.75f, Y * 0.02f);
        getButtons()[6][0].draw(getSpriteBatch(), 1.0f);

        float x_offset =  getButtons()[7][1].getWidth() * 2.0f;
        for(int i = 0; i < com.gamefactoryx.cheersapp.model.busdriving.BusDrivingPhase0Model.getInstance().getMaxPages(); i++) {
            if( com.gamefactoryx.cheersapp.model.busdriving.BusDrivingPhase0Model.getInstance().getPage() - 1 == i) {
                getButtons()[7][0].setPosition(X/2 - com.gamefactoryx.cheersapp.model.busdriving.BusDrivingPhase0Model.getInstance().getMaxPages() * getButtons()[7][0].getWidth() + x_offset * i, Y * 0.36f);
                getButtons()[7][0].draw(getSpriteBatch(), 1.0f);
            }
            else{
                getButtons()[7][1].setPosition(X/2 - com.gamefactoryx.cheersapp.model.busdriving.BusDrivingPhase0Model.getInstance().getMaxPages() * getButtons()[7][1].getWidth() + x_offset * i, Y * 0.36f);
                getButtons()[7][1].draw(getSpriteBatch(), 1.0f);

            }
        }

    }

    @Override
    protected void initLogo() {
        setLogo(new Sprite(new Texture("common/logo.png")));
        float logoRatio = getLogo().getHeight() / getLogo().getWidth();
        getLogo().setSize(com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldWidthPortrait() * 0.4f, com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightPortrait() * com.gamefactoryx.cheersapp.tool.Resolution.getAspectRatio() * 0.4f * logoRatio);
        getLogo().setPosition(com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldWidthPortrait()/3.65f, com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightPortrait()/7f);
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
        setLoadingSprite(new Sprite(new Texture(com.gamefactoryx.cheersapp.tool.Configuration.getLanguage() + "/loading.png")));
    }

    @Override
    protected void drawLoadingSprite() {
        if(com.gamefactoryx.cheersapp.model.busdriving.BusDrivingPhase0Model.getInstance().isLoadingNextStage())
            getLoadingSprite().draw(getSpriteBatch(), 1);

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
        int page = com.gamefactoryx.cheersapp.model.busdriving.BusDrivingPhase0Model.getInstance().getPage();
        int maxPlayersProPage = com.gamefactoryx.cheersapp.tool.Configuration.getMaxPlayersProConfigPage();
        return  com.gamefactoryx.cheersapp.tool.Configuration.getMaxPlayers() >= (page * maxPlayersProPage) ? maxPlayersProPage:
                maxPlayersProPage - (page * maxPlayersProPage - com.gamefactoryx.cheersapp.tool.Configuration.getMaxPlayers());
    }

}
