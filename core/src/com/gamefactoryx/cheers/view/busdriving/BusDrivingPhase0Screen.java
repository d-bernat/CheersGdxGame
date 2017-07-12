package com.gamefactoryx.cheers.view.busdriving;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.gamefactoryx.cheers.model.busdriving.BusDrivingPhase0Model;
import com.gamefactoryx.cheers.model.busdriving.Croupier;
import com.gamefactoryx.cheers.tool.*;
import com.gamefactoryx.cheers.view.AbstractScreen;


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
    }


    @Override
    protected void initSprites() {
        setLandscapeSprite(new Sprite(new Texture(Configuration.getLanguage() + "/Busdrivingscreen/Busfahrenscreen-landscape.png")));
        setPortraitSprite(new Sprite(new Texture(Configuration.getLanguage() + "/Busdrivingscreen/busdriving_names/Busfahrenscreen_1st.png")));
        getLandscapeSprite().setSize(Resolution.getGameWorldWidthLandscape(), Resolution.getGameWorldHeightLandscape());
        getPortraitSprite().setSize(Resolution.getGameWorldWidthPortrait(), Resolution.getGameWorldHeightPortrait());
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        float FONT_SIZE_ON_SCREEN = 0.03f;
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
        for (int i = 0; i < getCountOfButtons() - 2; i++) {
            String name = BusDrivingPhase0Model.getInstance().getPlayers().get(i).getName();
            FontHelper.getGlyphLayout().setText(font, name);
            font.draw(getSpriteBatch(), FontHelper.getGlyphLayout(), X * 0.46f - FontHelper.getGlyphLayout().width / 2.4f,
                    (Y * DISTANCE_FROM_TEXTBOX_BOTTOM) - y_offset++ * FontHelper.getGlyphLayout().height * 5.72f);
        }
    }


    @Override
    protected void initTextBox() {
        setTextBox(new Sprite(new Texture(Configuration.getLanguage() + "/Busdrivingscreen/busdriving_phase_1/text_box_horizontal.png")));
    }

    @Override
    protected void initButtons() {
        setButtons(new Sprite[8][2]);

        for (int i = 0; i < getCountOfButtons() - 2; i++)
            for (int j = 0; j < 2; j++) {
                getButtons()[i][j] = new Sprite(new Texture(Configuration.getLanguage() + "/Busdrivingscreen/busdriving_names/namebox_busdriving.png"));
                if (i == 0 && j == 0)
                    getButtons()[i][j].setSize(getButtons()[0][0].getWidth() * 1.15f, getButtons()[0][0].getHeight() * 1.15f);
                else
                    getButtons()[i][j].setSize(getButtons()[0][0].getWidth(), getButtons()[0][0].getHeight());
            }

        getButtons()[6][0] = new Sprite(new Texture(Configuration.getLanguage() + "/Busdrivingscreen/busdriving_names/okay.png"));
        getButtons()[6][1] = new Sprite(new Texture(Configuration.getLanguage() + "/Busdrivingscreen/busdriving_names/bad.png"));
        getButtons()[7][0] = new Sprite(new Texture("common/continue.png"));
        getButtons()[7][1] = new Sprite(new Texture("common/continue.png"));
        getButtons()[7][0].setSize(getButtons()[7][0].getWidth()*2.0f, getButtons()[7][0].getHeight()*2.0f);
        getButtons()[7][1].setSize(getButtons()[7][1].getWidth()*2.0f, getButtons()[7][1].getHeight()*2.0f);


        setClicked(new boolean[getCountOfButtons()]);
    }

    @Override
    protected void drawButtons() {
        float PORTRAIT_DISTANCE_FROM_BOTTOM = 0.76f;
        int y_offset = 0;

        for (int i = 0; i < Configuration.getMaxPlayers(); i++) {
            int click_index = getClicked()[i] ? CLICKED : FREE;
            getButtons()[i][click_index].setPosition(X * 0.18f,
                    Y * PORTRAIT_DISTANCE_FROM_BOTTOM - y_offset * getButtons()[i][click_index].getHeight() * 1.475f);
            getButtons()[i][click_index].draw(getSpriteBatch(), Croupier.getInstance().getPlayers().get(i).isActive() ? 1.0f : 100.0f);
            if(i > 1) {
                getButtons()[6][Croupier.getInstance().getPlayers().get(i).isActive() ? 0 : 1].setPosition(X * 0.75f,
                        Y * PORTRAIT_DISTANCE_FROM_BOTTOM - y_offset * getButtons()[i][0].getHeight() * 1.475f);
                getButtons()[6][Croupier.getInstance().getPlayers().get(i).isActive() ? 0 : 1].draw(getSpriteBatch(), 1.0f);
            }
            ++y_offset;
        }

        getButtons()[7][0].setPosition(X * 0.75f, Y * 0.02f);
        getButtons()[7][0].draw(getSpriteBatch(), 1.0f);
    }

    @Override
    protected void initLogo() {

    }

    @Override
    protected void drawLogo() {

    }

    @Override
    protected void initCards() {
        //   setFaceDownBigCard(new Sprite(new Texture("common/busdriving_cards/facedown_big_card.png")));
        //   setFaceDownSmallCard(new Sprite(new Texture("common/busdriving_cards/facedown_small_card.png")));
    }

    @Override
    protected void drawCards() {
        /*int x_offset = 0;
        getFaceDownBigCard().setPosition(X * 0.22f, Y * 0.23f);
        if (BusDrivingPhase1Model.getInstance().getBoard().getVCards().size == 0) {
            getFaceDownBigCard().draw(getSpriteBatch(), 1.0f);
        } else {
            for (VCard vCard : BusDrivingPhase1Model.getInstance().getBoard().getVCards()) {
                Sprite scard = new Sprite(Card.getInstance().getCardTexture(vCard.getCardIndex(), CardSize.BIG, vCard.getOrientation()));
                scard.setPosition(X * 0.22f, Y * 0.23f);
                scard.draw(getSpriteBatch(), 1.0f);
            }
        }

        for (VCard vCard : BusDrivingPhase1Model.getInstance().getPlayers().get(BusDrivingPhase1Model.getInstance().getActivePlayer()).getVCards()) {
            Sprite scard = new Sprite(Card.getInstance().getCardTexture(vCard.getCardIndex(), CardSize.SMALL, vCard.getOrientation()));
            scard.setSize(X * 0.2f, Y * 0.2f);
            scard.setPosition(X * 0.1f + scard.getWidth() * x_offset++, Y * 0.02f);
            scard.draw(getSpriteBatch(), 1.0f);
        }*/
    }

    @Override
    public void dispose() {
        // getFaceDownBigCard().getTexture().dispose();
        // getFaceDownSmallCard().getTexture().dispose();
        super.dispose();
    }


}
