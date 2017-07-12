package com.gamefactoryx.cheers.view.busdriving;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.gamefactoryx.cheers.model.busdriving.BusDrivingPhase1Model;
import com.gamefactoryx.cheers.tool.*;
import com.gamefactoryx.cheers.model.busdriving.VCard;
import com.gamefactoryx.cheers.view.AbstractScreen;


/**
 * Created by bernat on 16.05.2017.
 */
public class BusDrivingPhase1Screen extends AbstractScreen {


    private float X, Y;

    private BitmapFont font;
    private FreeTypeFontGenerator generator;
    private int FONT_SIZE;

    public BusDrivingPhase1Screen() {
        super();
    }


    @Override
    public void show() {
        super.show();
    }


    @Override
    protected void initSprites() {
        setLandscapeSprite(new Sprite(new Texture(Configuration.getLanguage() + "/Busdrivingscreen/Busfahrenscreen-landscape.png")));
        setPortraitSprite(new Sprite(new Texture(Configuration.getLanguage() + "/Busdrivingscreen/Busfahrenscreen.png")));
        getLandscapeSprite().setSize(Resolution.getGameWorldWidthLandscape(), Resolution.getGameWorldHeightLandscape());
        getPortraitSprite().setSize(Resolution.getGameWorldWidthPortrait(), Resolution.getGameWorldHeightPortrait());
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        float FONT_SIZE_ON_SCREEN = 0.04f;
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
        getTextBox().setSize(Resolution.getGameWorldWidthPortrait() * 0.90f, Resolution.getGameWorldHeightPortrait() * 0.150f);
        float DISTANCE_FROM_TEXTBOX_BOTTOM = 0.89f;
        getTextBox().setPosition(X * 0.05f, Y * 0.76f);
        getTextBox().draw(getSpriteBatch());
        if (!BusDrivingPhase1Model.getInstance().isPhaseFinished()) {
            String name = BusDrivingPhase1Model.getInstance().getPlayers().get(BusDrivingPhase1Model.getInstance().getActivePlayer()).getName();
            String task = BusDrivingPhase1Model.getInstance().getTask();
            FontHelper.getGlyphLayout().setText(font, name);
            font.draw(getSpriteBatch(), FontHelper.getGlyphLayout(), X * 0.48f - FontHelper.getGlyphLayout().width / 2.4f, Y * DISTANCE_FROM_TEXTBOX_BOTTOM);
            FontHelper.getGlyphLayout().setText(font, task);
            font.draw(getSpriteBatch(), FontHelper.getGlyphLayout(), X * 0.46f - FontHelper.getGlyphLayout().width / 2.4f,
                    (Y * DISTANCE_FROM_TEXTBOX_BOTTOM) - FontHelper.getGlyphLayout().height * 2.5f);
        } else {
            FontHelper.getGlyphLayout().setText(font, BusDrivingPhase1Model.getInstance().getFinalMessage());
            font.draw(getSpriteBatch(), FontHelper.getGlyphLayout(), X * 0.48f - FontHelper.getGlyphLayout().width / 2.4f,
                    (Y * DISTANCE_FROM_TEXTBOX_BOTTOM) - FontHelper.getGlyphLayout().height * 1.5f);
        }
    }


    @Override
    protected void initTextBox() {
        setTextBox(new Sprite(new Texture(Configuration.getLanguage() + "/Busdrivingscreen/busdriving_phase_1/text_box_horizontal.png")));
    }

    @Override
    protected void initButtons() {
        setButtons(new Sprite[1][2]);
        getButtons()[0][0] = new Sprite(new Texture("common/continue.png"));
        getButtons()[0][1] = new Sprite(new Texture("common/continue.png"));
        getButtons()[0][0].setSize(getButtons()[0][0].getWidth()*2.0f, getButtons()[0][0].getHeight()*2.0f);
        getButtons()[0][1].setSize(getButtons()[0][1].getWidth()*2.0f, getButtons()[0][1].getHeight()*2.0f);

    }

    @Override
    protected void drawButtons() {
        if(BusDrivingPhase1Model.getInstance().isPhaseFinished()) {
            getButtons()[0][0].setPosition(X * 0.75f, Y * 0.02f);
            getButtons()[0][0].draw(getSpriteBatch(), 1.0f);
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
        setFaceDownBigCard(new Sprite(new Texture("common/busdriving_cards/facedown_big_card.png")));
        setFaceDownSmallCard(new Sprite(new Texture("common/busdriving_cards/facedown_small_card.png")));
    }

    @Override
    protected void drawCards() {
        int x_offset = 0;
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
            if(BusDrivingPhase1Model.getInstance().isPhaseFinished())
                scard.draw(getSpriteBatch(), 100.0f);
            else
                scard.draw(getSpriteBatch(), 1.0f);
        }
    }

    @Override
    public void dispose() {
        getFaceDownBigCard().getTexture().dispose();
        getFaceDownSmallCard().getTexture().dispose();
        super.dispose();
    }


}
