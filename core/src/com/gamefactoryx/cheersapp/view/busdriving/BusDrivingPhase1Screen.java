package com.gamefactoryx.cheersapp.view.busdriving;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.gamefactoryx.cheersapp.tool.FontHelper;
import com.gamefactoryx.cheersapp.view.AbstractScreen;


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
        initBackButton();
        initRulesButton(com.gamefactoryx.cheersapp.model.busdriving.BusDrivingPhase1Model.getInstance());
        com.gamefactoryx.cheersapp.tool.Card.getInstance();

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
        float FONT_SIZE_ON_SCREEN = 0.04f;
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
    }

    @Override
    protected void drawText() {
        getTextBox().setSize(com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldWidthPortrait() * 0.90f, com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightPortrait() * 0.150f);
        float DISTANCE_FROM_TEXTBOX_BOTTOM = 0.89f;
        getTextBox().setPosition(X * 0.05f, Y * 0.76f);
        getTextBox().draw(getSpriteBatch());
        if (!com.gamefactoryx.cheersapp.model.busdriving.BusDrivingPhase1Model.getInstance().isPhaseFinished()) {
            String name = com.gamefactoryx.cheersapp.model.busdriving.BusDrivingPhase1Model.getInstance().getPlayers().get(com.gamefactoryx.cheersapp.model.busdriving.BusDrivingPhase1Model.getInstance().getActivePlayer()).getName();
            String task = com.gamefactoryx.cheersapp.model.busdriving.BusDrivingPhase1Model.getInstance().getTask();
            com.gamefactoryx.cheersapp.tool.FontHelper.getGlyphLayout().setText(font, name);
            font.draw(getSpriteBatch(), com.gamefactoryx.cheersapp.tool.FontHelper.getGlyphLayout(), X * 0.48f - com.gamefactoryx.cheersapp.tool.FontHelper.getGlyphLayout().width / 2.4f, Y * DISTANCE_FROM_TEXTBOX_BOTTOM);
            com.gamefactoryx.cheersapp.tool.FontHelper.getGlyphLayout().setText(font, task);
            font.draw(getSpriteBatch(), com.gamefactoryx.cheersapp.tool.FontHelper.getGlyphLayout(), X * 0.46f - com.gamefactoryx.cheersapp.tool.FontHelper.getGlyphLayout().width / 2.4f,
                    (Y * DISTANCE_FROM_TEXTBOX_BOTTOM) - com.gamefactoryx.cheersapp.tool.FontHelper.getGlyphLayout().height * 2.5f);
        } else {
            com.gamefactoryx.cheersapp.tool.FontHelper.getGlyphLayout().setText(font, com.gamefactoryx.cheersapp.model.busdriving.BusDrivingPhase1Model.getInstance().getFinalMessage());
            font.draw(getSpriteBatch(), com.gamefactoryx.cheersapp.tool.FontHelper.getGlyphLayout(), X * 0.48f - com.gamefactoryx.cheersapp.tool.FontHelper.getGlyphLayout().width / 2.4f,
                    (Y * DISTANCE_FROM_TEXTBOX_BOTTOM) - FontHelper.getGlyphLayout().height * 1.5f);
        }
    }


    @Override
    protected void initTextBox() {
        setTextBox(new Sprite(new Texture(com.gamefactoryx.cheersapp.tool.Configuration.getLanguage() + "/Busdrivingscreen/text_box_horizontal.png")));
    }

    @Override
    protected void initButtons() {
        setButtons(new Sprite[1][2]);
        getButtons()[0][0] = new Sprite(new Texture("common/continue.png"));
        getButtons()[0][1] = new Sprite(new Texture("common/continue.png"));
        getButtons()[0][0].setSize(com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldWidthPortrait() * 0.2f, com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightPortrait() * com.gamefactoryx.cheersapp.tool.Resolution.getAspectRatio() * 0.2f);
        getButtons()[0][1].setSize(com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldWidthPortrait() * 0.2f, com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightPortrait() * com.gamefactoryx.cheersapp.tool.Resolution.getAspectRatio() * 0.2f);

    }

    @Override
    protected void drawButtons() {
        if(com.gamefactoryx.cheersapp.model.busdriving.BusDrivingPhase1Model.getInstance().isPhaseFinished()) {
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
    protected void initLoadingSprite() {

    }

    @Override
    protected void drawLoadingSprite() {

    }

    @Override
    protected void drawCards() {
        int x_offset = 0;
        getFaceDownBigCard().setPosition(X * 0.18f, Y * 0.22f);
        if (com.gamefactoryx.cheersapp.model.busdriving.BusDrivingPhase1Model.getInstance().getBoard().getVCards().size == 0) {
            getFaceDownBigCard().setSize(com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldWidthPortrait() * 0.6f, com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightPortrait() * 0.52f);
            getFaceDownBigCard().draw(getSpriteBatch(), 1.0f);
        } else {
            for (com.gamefactoryx.cheersapp.model.busdriving.VCard vCard : com.gamefactoryx.cheersapp.model.busdriving.BusDrivingPhase1Model.getInstance().getBoard().getVCards()) {
                Sprite scard = new Sprite(com.gamefactoryx.cheersapp.tool.Card.getInstance().getCardTexture(vCard.getCardIndex(), com.gamefactoryx.cheersapp.tool.CardSize.BIG, vCard.getOrientation()));
                scard.setPosition(X * 0.18f, Y * 0.22f);
                scard.setSize(com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldWidthPortrait() * 0.6f, com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightPortrait() * 0.52f);
                scard.draw(getSpriteBatch(), 1.0f);
            }
        }

        for (com.gamefactoryx.cheersapp.model.busdriving.VCard vCard : com.gamefactoryx.cheersapp.model.busdriving.BusDrivingPhase1Model.getInstance().getPlayers().get(com.gamefactoryx.cheersapp.model.busdriving.BusDrivingPhase1Model.getInstance().getActivePlayer()).getVCards()) {
            Sprite scard = new Sprite(com.gamefactoryx.cheersapp.tool.Card.getInstance().getCardTexture(vCard.getCardIndex(), com.gamefactoryx.cheersapp.tool.CardSize.SMALL, vCard.getOrientation()));
            scard.setSize(com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldWidthPortrait() * 0.2f, com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightPortrait() * 0.18f);
            scard.setPosition(X * 0.1f + scard.getWidth() * x_offset++, Y * 0.02f);
            if(com.gamefactoryx.cheersapp.model.busdriving.BusDrivingPhase1Model.getInstance().isPhaseFinished())
                scard.draw(getSpriteBatch(), 100.0f);
            else
                scard.draw(getSpriteBatch(), 1.0f);
        }
    }

    @Override
    public void dispose() {
        getFaceDownBigCard().getTexture().dispose();
        getFaceDownSmallCard().getTexture().dispose();
        if(font !=null)
            font.dispose();
        super.dispose();
    }


}
