package com.gamefactoryx.cheers.view;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.gamefactoryx.cheers.model.BusDrivingPhase4Model;
import com.gamefactoryx.cheers.model.bus_driving.Croupier;
import com.gamefactoryx.cheers.model.bus_driving.VCard;
import com.gamefactoryx.cheers.tool.*;

import java.util.Locale;


/**
 * Created by bernat on 16.05.2017.
 */
public class BusDrivingPhase4Screen extends AbstractScreen {


    private float X, Y;

    private BitmapFont font;
    private FreeTypeFontGenerator generator;
    private int FONT_SIZE;
    private float cardWidth, cardHight;


    public BusDrivingPhase4Screen() {
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
        getTextBox().setSize(Resolution.getGameWorldWidthLandscape() * 0.50f, Resolution.getGameWorldHeightLandscape() * 0.12f);
        float DISTANCE_FROM_TEXTBOX_BOTTOM = 0.81f;
        getTextBox().setPosition(X * 0.22f, Y * 0.73f);
        getTextBox().draw(getSpriteBatch());

        String name = BusDrivingPhase4Model.getInstance().getBusDriver().getName();

        FontHelper.getGlyphLayout().setText(font, name);
        font.draw(getSpriteBatch(), FontHelper.getGlyphLayout(), X * 0.47f - FontHelper.getGlyphLayout().width / 2.0f, Y * DISTANCE_FROM_TEXTBOX_BOTTOM);

        String info =   BusDrivingPhase4Model.getInstance().isPhaseFinished() ?
                        String.format("Game over :-), drunk: %d", BusDrivingPhase4Model.getInstance().getTotalDrunk())
                        : String.format("drink: %d / drunk: %d", BusDrivingPhase4Model.getInstance().getDrinkPoints(), BusDrivingPhase4Model.getInstance().getTotalDrunk());

        FontHelper.getGlyphLayout().setText(font, info);
        font.draw(getSpriteBatch(), FontHelper.getGlyphLayout(), X * 0.47f - FontHelper.getGlyphLayout().width / 2.0f,
                (Y * DISTANCE_FROM_TEXTBOX_BOTTOM) - FontHelper.getGlyphLayout().height * 2.5f);
    }


    @Override
    protected void initTextBox() {
        setTextBox(new Sprite(new Texture(Configuration.getLanguage() + "/Busdrivingscreen/busdriving_phase_3/text_box_horizontal.png")));
    }

    @Override
    protected void initButtons() {


        setButtons(new Sprite[2][2]);

        getButtons()[0][0] = new Sprite(new Texture(Configuration.getLanguage() + "/Busdrivingscreen/Overtime/overtime_pop_up_symbol_higher_busdriving.png"));
        getButtons()[0][0].setSize(getButtons()[0][0].getWidth() * 0.7f, getButtons()[0][0].getHeight() * 0.7f);
        getButtons()[0][1] = new Sprite(new Texture(Configuration.getLanguage() + "/Busdrivingscreen/Overtime/overtime_pop_up_symbol_higher_busdriving.png"));
        getButtons()[0][1].setSize(getButtons()[0][1].getWidth() * 0.7f, getButtons()[0][1].getHeight() * 0.7f);
        getButtons()[1][0] = new Sprite(new Texture(Configuration.getLanguage() + "/Busdrivingscreen/Overtime/overtime_pop_up_symbol_lower_busdriving.png"));
        getButtons()[1][0].setSize(getButtons()[1][0].getWidth() * 0.7f, getButtons()[1][0].getHeight() * 0.7f);
        getButtons()[1][1] = new Sprite(new Texture(Configuration.getLanguage() + "/Busdrivingscreen/Overtime/overtime_pop_up_symbol_lower_busdriving.png"));
        getButtons()[1][1].setSize(getButtons()[1][1].getWidth() * 0.7f, getButtons()[1][1].getHeight() * 0.7f);
        setClicked(new boolean[getCountOfButtons()]);

    }

    @Override
    protected void drawButtons() {
        if(!BusDrivingPhase4Model.getInstance().isPhaseFinished()) {
            float PORTRAIT_DISTANCE_FROM_BOTTOM = 0.02f;
            int y_offset = 0;
            for (int i = 0; i < getCountOfButtons(); i++) {
                int click_index = getClicked()[i] ? CLICKED : FREE;
                getButtons()[i][click_index].setPosition(X * 0.11f + BusDrivingPhase4Model.getInstance().getActiveCardIndex() * cardWidth * 1.1f,
                        Y * PORTRAIT_DISTANCE_FROM_BOTTOM + y_offset++ * Y * 0.52f);
                getButtons()[i][click_index].draw(getSpriteBatch(), 50.0f);
            }
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
        VCard vCard = Croupier.getInstance().getVCards().last();
        Sprite scard = new Sprite(Card.getInstance().getCardTexture(vCard.getCardIndex(), CardSize.BIG, vCard.getOrientation()));
        cardHight = scard.getHeight() * 0.35f;
        cardWidth = scard.getWidth() * 0.35f;
    }

    @Override
    protected void drawCards() {
        int x_offset = 0;
        for(VCard vCard : BusDrivingPhase4Model.getInstance().getBoard().getVCards()) {
            Sprite scard = new Sprite(Card.getInstance().getCardTexture(vCard.getCardIndex(), CardSize.BIG, vCard.getOrientation()));
            scard.setPosition(X * 0.08f + x_offset++ * cardWidth * 1.1f, Y * 0.19f);
            scard.setSize(cardWidth, cardHight);
            scard.draw(getSpriteBatch(), 1.0f);
        }

    }

    @Override
    public void dispose() {
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
