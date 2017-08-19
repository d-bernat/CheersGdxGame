package com.gamefactoryx.cheers.view.busdriving;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.gamefactoryx.cheers.model.busdriving.BusDrivingPhase3Model;
import com.gamefactoryx.cheers.model.busdriving.VCard;
import com.gamefactoryx.cheers.tool.*;
import com.gamefactoryx.cheers.view.AbstractScreen;

import java.util.Locale;


/**
 * Created by bernat on 16.05.2017.
 */
public class BusDrivingPhase3Screen extends AbstractScreen {


    private float X, Y;

    private BitmapFont font;
    private FreeTypeFontGenerator generator;
    private int FONT_SIZE;


    public BusDrivingPhase3Screen() {
        super();
    }


    @Override
    public void show() {
        super.show();
    }


    @Override
    protected void initSprites() {
        setLandscapeSprite(new Sprite(new Texture(Configuration.getLanguage() + "/Busdrivingscreen/busdrivingscreen-landscape.png")));
        setPortraitSprite(new Sprite(new Texture(Configuration.getLanguage() + "/Busdrivingscreen/busdrivingscreen.png")));
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

        String name = BusDrivingPhase3Model.getInstance().getActivePlayer().getName();
        String task = BusDrivingPhase3Model.getInstance().getTask();
        FontHelper.getGlyphLayout().setText(font, name);
        font.draw(getSpriteBatch(), FontHelper.getGlyphLayout(), X * 0.48f - FontHelper.getGlyphLayout().width / 2.4f, Y * DISTANCE_FROM_TEXTBOX_BOTTOM);
        if (BusDrivingPhase3Model.getInstance().isPhaseFinished()) {
            FontHelper.getGlyphLayout().setText(font, BusDrivingPhase3Model.getInstance().getFinalMessage());
            font.draw(getSpriteBatch(), FontHelper.getGlyphLayout(), X * 0.46f - FontHelper.getGlyphLayout().width / 2.4f,
                    (Y * DISTANCE_FROM_TEXTBOX_BOTTOM) - FontHelper.getGlyphLayout().height * 2.5f);

        } else {
            FontHelper.getGlyphLayout().setText(font, task);
            font.draw(getSpriteBatch(), FontHelper.getGlyphLayout(), X * 0.46f - FontHelper.getGlyphLayout().width / 2.4f,
                    (Y * DISTANCE_FROM_TEXTBOX_BOTTOM) - FontHelper.getGlyphLayout().height * 2.5f);
        }
    }


    @Override
    protected void initTextBox() {
        setTextBox(new Sprite(new Texture(Configuration.getLanguage() + "/Busdrivingscreen/busdriving_phase_3/text_box_horizontal.png")));
    }

    @Override
    protected void initButtons() {
        setButtons(new Sprite[3][2]);

        getButtons()[0][0] = new Sprite(new Texture(Configuration.getLanguage() + "/Busdrivingscreen/Overtime/overtime_pop_up_symbol_higher_busdriving.png"));
        getButtons()[0][1] = new Sprite(new Texture(Configuration.getLanguage() + "/Busdrivingscreen/Overtime/overtime_pop_up_symbol_higher_busdriving.png"));
        getButtons()[0][0].setSize(Resolution.getGameWorldWidthPortrait() * 0.4f, Resolution.getGameWorldHeightPortrait() * 0.1f);
        getButtons()[0][1].setSize(Resolution.getGameWorldWidthPortrait() * 0.4f, Resolution.getGameWorldHeightPortrait() * 0.1f);
        getButtons()[1][0] = new Sprite(new Texture(Configuration.getLanguage() + "/Busdrivingscreen/Overtime/overtime_pop_up_symbol_lower_busdriving.png"));
        getButtons()[1][1] = new Sprite(new Texture(Configuration.getLanguage() + "/Busdrivingscreen/Overtime/overtime_pop_up_symbol_lower_busdriving.png"));
        getButtons()[1][0].setSize(Resolution.getGameWorldWidthPortrait() * 0.4f, Resolution.getGameWorldHeightPortrait() * 0.1f);
        getButtons()[1][1].setSize(Resolution.getGameWorldWidthPortrait() * 0.4f, Resolution.getGameWorldHeightPortrait() * 0.1f);
        getButtons()[2][0] = new Sprite(new Texture("common/continue.png"));
        getButtons()[2][1] = new Sprite(new Texture("common/continue.png"));
        getButtons()[2][0].setSize(Resolution.getGameWorldWidthPortrait() * 0.2f, Resolution.getGameWorldHeightPortrait() * Resolution.getAspectRatio() * 0.2f);
        getButtons()[2][1].setSize(Resolution.getGameWorldWidthPortrait() * 0.2f, Resolution.getGameWorldHeightPortrait() * Resolution.getAspectRatio() * 0.2f);
        setClicked(new boolean[getCountOfButtons()]);
    }

    @Override
    protected void drawButtons() {
        if (!BusDrivingPhase3Model.getInstance().isPhaseFinished()) {
            float PORTRAIT_DISTANCE_FROM_BOTTOM = 0.05f;
            int x_offset = 0;
            for (int i = 0; i < getCountOfButtons(); i++) {
                int click_index = getClicked()[i] ? CLICKED : FREE;
                getButtons()[i][click_index].setPosition(X * 0.03f + X * 0.53f * x_offset++,
                        Y * PORTRAIT_DISTANCE_FROM_BOTTOM);
                getButtons()[i][click_index].draw(getSpriteBatch(), 50.0f);
            }
        } else {
            getButtons()[2][0].setPosition(X * 0.75f, Y * 0.02f);
            getButtons()[2][0].draw(getSpriteBatch(), 1.0f);
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
        //setFaceDownBigCard(new Sprite(new Texture("common/busdriving_cards/facedown_big_card.png")));
        //setFaceDownSmallCard(new Sprite(new Texture("common/busdriving_cards/facedown_small_card.png")));
    }

    @Override
    protected void drawCards() {
        VCard vCard = BusDrivingPhase3Model.getInstance().getBoard().getVCards().last();
        Sprite scard = new Sprite(Card.getInstance().getCardTexture(vCard.getCardIndex(), CardSize.BIG, vCard.getOrientation()));
        scard.setSize(Resolution.getGameWorldWidthPortrait() * 0.6f, Resolution.getGameWorldHeightPortrait() * 0.52f);
        scard.setPosition(X * 0.20f, Y * 0.23f);
        scard.draw(getSpriteBatch(), 1.0f);

    }

    @Override
    public void dispose() {
        if (font != null)
            font.dispose();
        super.dispose();

    }

    private String getMessage(int credit) {
        switch (Configuration.getLanguage()) {
            case DE:
                if (credit == 1)
                    return String.format(Locale.GERMAN, "%s %d %s", "Du verteilst ", credit, " Schluck!");
                else
                    return String.format(Locale.GERMAN, "%s %d %s", "Du verteilst ", credit, " Schlücke!");
            case EN:
                if (credit == 1)
                    return String.format(Locale.ENGLISH, "%s %d %s", "Du verteilst ", credit, " Schluck!");
                else
                    return String.format(Locale.ENGLISH, "%s %d %s", "Du verteilst ", credit, " Schlücke!");
            default:
                if (credit == 1)
                    return String.format(Locale.GERMAN, "%s %d %s", "Du verteilst ", credit, " Schluck!");
                else
                    return String.format(Locale.GERMAN, "%s %d %s", "Du verteilst ", credit, " Schlücke!");
        }


    }

}
