package com.gamefactoryx.cheers.view;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.gamefactoryx.cheers.model.BusDrivingPhase2Model;
import com.gamefactoryx.cheers.model.bus_driving.Croupier;
import com.gamefactoryx.cheers.model.bus_driving.Player;
import com.gamefactoryx.cheers.model.bus_driving.VCard;
import com.gamefactoryx.cheers.tool.*;

import java.util.Locale;


/**
 * Created by bernat on 16.05.2017.
 */
public class BusDrivingPhase2Screen extends AbstractScreen {


    private float X, Y;

    private BitmapFont font;
    private FreeTypeFontGenerator generator;
    private int FONT_SIZE;


    public BusDrivingPhase2Screen() {
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
       // boolean isSomePlayerActive = false;
        float DISTANCE_FROM_TEXTBOX_BOTTOM = 0.65f;
        getTextBox().setPosition(X * 0.05f, Y * 0.52f);
        if (!BusDrivingPhase2Model.getInstance().isPhaseFinished()) {
            getTextBox().setSize(Resolution.getGameWorldWidthPortrait() * 0.90f, Resolution.getGameWorldHeightPortrait() * 0.150f);
            outer:
            for(Player player: BusDrivingPhase2Model.getInstance().getPlayers()) {
                for (VCard vCard : player.getVCards()) {
                    if (vCard.getCredit() > 0) {
                        FontHelper.getGlyphLayout().setText(font, player.getName());
                        getTextBox().draw(getSpriteBatch());
                        font.draw(getSpriteBatch(), FontHelper.getGlyphLayout(), X * 0.48f - FontHelper.getGlyphLayout().width / 2.4f, Y * DISTANCE_FROM_TEXTBOX_BOTTOM);
                        FontHelper.getGlyphLayout().setText(font, getMessage(vCard.getCredit()));
                        font.draw(getSpriteBatch(), FontHelper.getGlyphLayout(), X * 0.46f - FontHelper.getGlyphLayout().width / 2.4f,
                                (Y * DISTANCE_FROM_TEXTBOX_BOTTOM) - FontHelper.getGlyphLayout().height * 2.5f);
                        break outer;
                    }
                }
            }

        } else {
            int y_offset = 0;
            int counter = 0;
            getTextBox().draw(getSpriteBatch());
            for(Player player: BusDrivingPhase2Model.getInstance().getPlayers()){
                if(player.isAlive()) {
                    ++counter;
                    FontHelper.getGlyphLayout().setText(font, player.getName());
                    font.draw(getSpriteBatch(), FontHelper.getGlyphLayout(), X * 0.48f - FontHelper.getGlyphLayout().width / 2.4f,
                            (Y * DISTANCE_FROM_TEXTBOX_BOTTOM) - FontHelper.getGlyphLayout().height * -1.65f * y_offset++);
                }
            }
            getTextBox().setSize(Resolution.getGameWorldWidthPortrait() * 0.90f, FontHelper.getGlyphLayout().height * 1.65f * (1 + counter) + FontHelper.getGlyphLayout().height * 2.5f);
            FontHelper.getGlyphLayout().setText(font, BusDrivingPhase2Model.getInstance().getFinalMessage());

            font.draw(getSpriteBatch(), FontHelper.getGlyphLayout(), X * 0.48f - FontHelper.getGlyphLayout().width / 2.4f,
                    (Y * DISTANCE_FROM_TEXTBOX_BOTTOM ) - FontHelper.getGlyphLayout().height * 3.0f);
        }
    }


    @Override
    protected void initTextBox() {
        setTextBox(new Sprite(new Texture(Configuration.getLanguage() + "/Busdrivingscreen/busdriving_phase_2/text_box_horizontal.png")));
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
        if(BusDrivingPhase2Model.getInstance().isPhaseFinished()) {
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
        //setFaceDownBigCard(new Sprite(new Texture("common/busdriving_cards/facedown_big_card.png")));
        //setFaceDownSmallCard(new Sprite(new Texture("common/busdriving_cards/facedown_small_card.png")));
    }

    @Override
    protected void drawCards() {
        int x_offset = 0;
        int y_offset = 0;
        for(Player player: BusDrivingPhase2Model.getInstance().getPlayers()){
            for (VCard vCard : player.getVCards()) {
                Sprite scard = new Sprite(Card.getInstance().getCardTexture(vCard.getCardIndex(), CardSize.SMALL, vCard.getOrientation()));
                scard.setSize(X * 0.15f, Y * 0.12f);
                scard.setPosition(X * 0.05f +
                        (Configuration.getMaxPlayers() - BusDrivingPhase2Model.getInstance().getCountOfPlayers())/2.0f * scard.getWidth() +
                        scard.getWidth() * x_offset,
                        Y * 0.78f - scard.getHeight() * 0.15f * y_offset++);
                scard.draw(getSpriteBatch(), 1.0f);
            }
            ++x_offset;
            y_offset = 0;
        }

        x_offset = 0;
        y_offset = 0;
        int index = 0;
        for (VCard vCard : BusDrivingPhase2Model.getInstance().getBoard().getVCards()) {
            Sprite scard = new Sprite(Card.getInstance().getCardTexture(vCard.getCardIndex(), CardSize.SMALL, vCard.getOrientation()));
            if (index == 0)
                y_offset = 0;
            else if (index == 5) {
                y_offset = 1;
                x_offset = 0;
            } else if (index == 9) {
                y_offset = 2;
                x_offset = 0;
            } else if (index == 12) {
                y_offset = 3;
                x_offset = 0;
            } else if (index == 14) {
                y_offset = 4;
                x_offset = 0;
            }

            float NEXT_FLOOR_X_OFFSET = 0.58f;
            float X_GAP_BETWEEN_TWO_CARDS = 1.2f;
            float DISTANCE_FROM_SCREEN_BOTTOM = 0.02f;
            float DISTANCE_FROM_SCREEN_LEFT = 0.08f;

            scard.setSize(X * 0.15f, Y * 0.12f);
            if (BusDrivingPhase2Model.getInstance().isScrolled())
                scard.setPosition(X * DISTANCE_FROM_SCREEN_LEFT + scard.getWidth() * NEXT_FLOOR_X_OFFSET * y_offset + scard.getWidth() * X_GAP_BETWEEN_TWO_CARDS * x_offset++, -4.5f * Y * DISTANCE_FROM_SCREEN_BOTTOM + scard.getHeight() * y_offset);
            else
                scard.setPosition(X * DISTANCE_FROM_SCREEN_LEFT + scard.getWidth() * NEXT_FLOOR_X_OFFSET * y_offset + scard.getWidth() * X_GAP_BETWEEN_TWO_CARDS * x_offset++, Y * DISTANCE_FROM_SCREEN_BOTTOM + scard.getHeight() * y_offset);

            if(BusDrivingPhase2Model.getInstance().isPhaseFinished())
                scard.draw(getSpriteBatch(), 100.0f);
            else
                scard.draw(getSpriteBatch(), 1.0f);
            ++index;
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
