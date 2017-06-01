package com.gamefactoryx.cheers.view;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.gamefactoryx.cheers.model.BusDrivingModel;
import com.gamefactoryx.cheers.model.bus_driving.Player;
import com.gamefactoryx.cheers.model.bus_driving.VCard;
import com.gamefactoryx.cheers.tool.Configuration;
import com.gamefactoryx.cheers.model.bus_driving.Card;
import com.gamefactoryx.cheers.tool.FontHelper;
import com.gamefactoryx.cheers.tool.Orientation;
import com.gamefactoryx.cheers.tool.Resolution;

import java.util.Locale;


/**
 * Created by bernat on 16.05.2017.
 */
public class BusDrivingScreen extends AbstractScreen {


    private float X, Y;
    private static BusDrivingModel dataModel = BusDrivingModel.getInstance();

    private BitmapFont font;
    private FreeTypeFontGenerator generator;
    private int FONT_SIZE;

    public BusDrivingScreen() {
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


        switch (dataModel.getPhase().getName()) {
            case "PHASE_1": {
                getTextBox().setSize(Resolution.getGameWorldWidthPortrait() * 0.90f, Resolution.getGameWorldHeightPortrait() * 0.150f);
                float DISTANCE_FROM_TEXTBOX_BOTTOM = 0.89f;
                getTextBox().setPosition(X * 0.05f, Y * 0.76f);
                getTextBox().draw(getSpriteBatch());
                if (dataModel.getMessage() == null || dataModel.getMessage().length() == 0) {
                    String name = dataModel.getPlayer().getName();
                    String task = dataModel.getTask();
                    FontHelper.getGlyphLayout().setText(font, name);
                    font.draw(getSpriteBatch(), FontHelper.getGlyphLayout(), X * 0.45f - FontHelper.getGlyphLayout().width / 2.4f, Y * DISTANCE_FROM_TEXTBOX_BOTTOM);
                    FontHelper.getGlyphLayout().setText(font, task);
                    font.draw(getSpriteBatch(), FontHelper.getGlyphLayout(), X * 0.42f - FontHelper.getGlyphLayout().width / 2.4f,
                            (Y * DISTANCE_FROM_TEXTBOX_BOTTOM) - FontHelper.getGlyphLayout().height * 2.5f);
                } else {
                    FontHelper.getGlyphLayout().setText(font, dataModel.getMessage());
                    font.draw(getSpriteBatch(), FontHelper.getGlyphLayout(), X * 0.48f - FontHelper.getGlyphLayout().width / 2.4f,
                            (Y * DISTANCE_FROM_TEXTBOX_BOTTOM) - FontHelper.getGlyphLayout().height * 1.5f);
                }
            }
            break;
            case "PHASE_2": {
                boolean isSomePlayerActive = false;
                float DISTANCE_FROM_TEXTBOX_BOTTOM = 0.65f;
                getTextBox().setPosition(X * 0.05f, Y * 0.52f);
                getTextBox().draw(getSpriteBatch());
                if (dataModel.getMessage() == null || dataModel.getMessage().length() == 0) {
                    getTextBox().setSize(Resolution.getGameWorldWidthPortrait() * 0.90f, Resolution.getGameWorldHeightPortrait() * 0.150f);
                    dataModel.firstPlayer();
                    outer:
                    do {
                        Player player = dataModel.getPlayer();
                        for (VCard vCard : player.getVCards()) {
                            if (vCard.getCredit() > 0) {
                                FontHelper.getGlyphLayout().setText(font, dataModel.getPlayer().getName());
                                font.draw(getSpriteBatch(), FontHelper.getGlyphLayout(), X * 0.45f - FontHelper.getGlyphLayout().width / 2.4f, Y * DISTANCE_FROM_TEXTBOX_BOTTOM);
                                FontHelper.getGlyphLayout().setText(font, getMessage(vCard.getCredit()));
                                font.draw(getSpriteBatch(), FontHelper.getGlyphLayout(), X * 0.42f - FontHelper.getGlyphLayout().width / 2.4f,
                                        (Y * DISTANCE_FROM_TEXTBOX_BOTTOM) - FontHelper.getGlyphLayout().height * 2.5f);
                                isSomePlayerActive = true;
                                break outer;
                            }
                        }

                    } while (dataModel.nextPlayer());
                    if (dataModel.getPhase().getBoard().isAllCardsOnBoardFace() && !isSomePlayerActive) {
                        dataModel.result();
                        dataModel.setMessage("GO_ON");
                    }
                } else {

//                    getTextBox().setPosition(X * 0.05f, Y * 0.52f);
                    int y_offset = 0;
                    for(Player player: dataModel.getLoosers()){
                        FontHelper.getGlyphLayout().setText(font, player.getName());
                        font.draw(getSpriteBatch(), FontHelper.getGlyphLayout(), X * 0.48f - FontHelper.getGlyphLayout().width / 2.4f,
                                (Y * DISTANCE_FROM_TEXTBOX_BOTTOM ) - FontHelper.getGlyphLayout().height * -1.65f *  y_offset++);
                    }
                    getTextBox().setSize(Resolution.getGameWorldWidthPortrait() * 0.90f, FontHelper.getGlyphLayout().height * 1.65f * (1 + dataModel.getLoosers().size()) + FontHelper.getGlyphLayout().height * 2.5f);
                    FontHelper.getGlyphLayout().setText(font, dataModel.getMessage());
                    font.draw(getSpriteBatch(), FontHelper.getGlyphLayout(), X * 0.48f - FontHelper.getGlyphLayout().width / 2.4f,
                            (Y * DISTANCE_FROM_TEXTBOX_BOTTOM ) - FontHelper.getGlyphLayout().height * 3.0f);

                }
            }
            break;
            case "PHASE_3":
                getTextBox().setSize(Resolution.getGameWorldWidthPortrait() * 0.90f, Resolution.getGameWorldHeightPortrait() * 0.150f);
                float DISTANCE_FROM_TEXTBOX_BOTTOM = 0.89f;
                getTextBox().setPosition(X * 0.05f, Y * 0.76f);
                getTextBox().draw(getSpriteBatch());
                if (dataModel.getMessage() == null || dataModel.getMessage().length() == 0) {
                    String name = dataModel.getPlayer().getName();
                    String task = dataModel.getTask();
                    FontHelper.getGlyphLayout().setText(font, name);
                    font.draw(getSpriteBatch(), FontHelper.getGlyphLayout(), X * 0.45f - FontHelper.getGlyphLayout().width / 2.4f, Y * DISTANCE_FROM_TEXTBOX_BOTTOM);
                    FontHelper.getGlyphLayout().setText(font, task);
                    font.draw(getSpriteBatch(), FontHelper.getGlyphLayout(), X * 0.42f - FontHelper.getGlyphLayout().width / 2.4f,
                            (Y * DISTANCE_FROM_TEXTBOX_BOTTOM) - FontHelper.getGlyphLayout().height * 2.5f);
                } else {
                    FontHelper.getGlyphLayout().setText(font, dataModel.getMessage());
                    font.draw(getSpriteBatch(), FontHelper.getGlyphLayout(), X * 0.48f - FontHelper.getGlyphLayout().width / 2.4f,
                            (Y * DISTANCE_FROM_TEXTBOX_BOTTOM) - FontHelper.getGlyphLayout().height * 1.5f);
                }
                break;
        }
    }


    @Override
    protected void initTextBox() {
        setTextBox(new Sprite(new Texture(Configuration.getLanguage() + "/Busdrivingscreen/busdriving_phase_1/text_box_horizontal.png")));
    }

    @Override
    protected void initButtons() {

    }

    @Override
    protected void drawButtons() {

    }

    @Override
    protected void initLogo() {

    }

    @Override
    protected void drawLogo() {

    }

    @Override
    protected void initCards() {
        for (VCard vCard : dataModel.getVCards()) {
            Card card = new Card(vCard.getCardIndex(), Card.CardSize.BIG);
            getCardSprites().put(card.getFileName(Card.CardSize.BIG, VCard.CardOrientation.FACE), new Sprite(dataModel.getCardTextures().get(card.getFileName(Card.CardSize.BIG, VCard.CardOrientation.FACE))));
            getCardSprites().put(card.getFileName(Card.CardSize.BIG, VCard.CardOrientation.BACK), new Sprite(dataModel.getCardTextures().get(card.getFileName(Card.CardSize.BIG, VCard.CardOrientation.BACK))));
        }

        for (VCard vCard : dataModel.getVCards()) {
            Card card = new Card(vCard.getCardIndex(), Card.CardSize.SMALL);
            getCardSprites().put(card.getFileName(Card.CardSize.SMALL, VCard.CardOrientation.FACE), new Sprite(dataModel.getCardTextures().get(card.getFileName(Card.CardSize.SMALL, VCard.CardOrientation.FACE))));
            getCardSprites().put(card.getFileName(Card.CardSize.SMALL, VCard.CardOrientation.BACK), new Sprite(dataModel.getCardTextures().get(card.getFileName(Card.CardSize.SMALL, VCard.CardOrientation.BACK))));
        }
        setFaceDownBigCard(new Sprite(new Texture("common/busdriving_cards/facedown_big_card.png")));
        setFaceDownSmallCard(new Sprite(new Texture("common/busdriving_cards/facedown_small_card.png")));


    }

    @Override
    protected void drawCards() {
        int x_offset = 0;
        int y_offset = 0;
        switch (dataModel.getPhase().getName()) {
            case "PHASE_1":
                getFaceDownBigCard().setPosition(X * 0.22f, Y * 0.23f);
                if (dataModel.getPhase().getBoard().getVCards().size == 0) {
                    getFaceDownBigCard().draw(getSpriteBatch(), 1.0f);
                }
                for (VCard vCard : dataModel.getPlayer().getVCards()) {
                    Sprite scard = getCardCache().get(String.format("%d_%s_%s", vCard.getCardIndex(), Card.CardSize.SMALL.value(), vCard.getOrientation().value()));
                    if (scard == null) {
                        Card card = new Card(vCard.getCardIndex(), Card.CardSize.SMALL);
                        scard = getCardSprites().get(card.getFileName(Card.CardSize.SMALL, vCard.getOrientation()));
                        getCardCache().put(String.format("%d_%s_%s", vCard.getCardIndex(), Card.CardSize.SMALL.value(), vCard.getOrientation().value()), scard);
                    }
                    scard.setSize(X * 0.2f, Y * 0.2f);
                    scard.setPosition(X * 0.1f + scard.getWidth() * x_offset++, Y * 0.02f);
                    scard.draw(getSpriteBatch(), 1.0f);
                }

                for (VCard vCard : dataModel.getPhase().getBoard().getVCards()) {
                    Sprite scard = getCardCache().get(String.format("%d_%s", vCard.getCardIndex(), Card.CardSize.BIG.value(), vCard.getOrientation().value()));
                    if (scard == null) {
                        Card card = new Card(vCard.getCardIndex(), Card.CardSize.BIG);
                        scard = getCardSprites().get(card.getFileName(Card.CardSize.BIG, vCard.getOrientation()));
                        getCardCache().put(String.format("%d_%s_%s", vCard.getCardIndex(), Card.CardSize.BIG.value(), vCard.getOrientation().value()), scard);
                    }
                    scard.setPosition(X * 0.22f, Y * 0.23f);
                    scard.draw(getSpriteBatch(), 1.0f);
                }
                break;
            case "PHASE_2":
                dataModel.firstPlayer();
                do {
                    for (VCard vCard : dataModel.getPlayer().getVCards()) {
                        Sprite scard = getCardCache().get(String.format("%d_%s", vCard.getCardIndex(), Card.CardSize.SMALL.value(), vCard.getOrientation().value()));
                        if (scard == null) {
                            Card card = new Card(vCard.getCardIndex(), Card.CardSize.SMALL);
                            scard = getCardSprites().get(card.getFileName(Card.CardSize.SMALL, vCard.getOrientation()));
                            getCardCache().put(String.format("%d_%s_%s", vCard.getCardIndex(), Card.CardSize.SMALL.value(), vCard.getOrientation().value()), scard);
                        }
                        scard.setSize(X * 0.15f, Y * 0.12f);
                        scard.setPosition(X * 0.05f + scard.getWidth() * x_offset, Y * 0.78f - scard.getHeight() * 0.15f * y_offset++);
                        scard.draw(getSpriteBatch(), 1.0f);
                    }
                    ++x_offset;
                    y_offset = 0;
                } while (dataModel.nextPlayer());

                x_offset = 0;
                y_offset = 0;
                int index = 0;
                for (VCard vCard : dataModel.getPhase().getBoard().getVCards()) {

                    Sprite scard = getCardCache().get(String.format("%d_%s_%s", vCard.getCardIndex(), Card.CardSize.SMALL.value(), vCard.getOrientation().value()));
                    if (scard == null) {
                        Card card = new Card(vCard.getCardIndex(), Card.CardSize.SMALL);
                        scard = getCardSprites().get(card.getFileName(Card.CardSize.SMALL, vCard.getOrientation()));
                        getCardCache().put(String.format("%d_%s_%s", vCard.getCardIndex(), Card.CardSize.SMALL.value(), vCard.getOrientation().value()), scard);
                    }

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
                    if (dataModel.isScrollPyramide())
                        scard.setPosition(X * DISTANCE_FROM_SCREEN_LEFT + scard.getWidth() * NEXT_FLOOR_X_OFFSET * y_offset + scard.getWidth() * X_GAP_BETWEEN_TWO_CARDS * x_offset++, -4.5f * Y * DISTANCE_FROM_SCREEN_BOTTOM + scard.getHeight() * y_offset);
                    else
                        scard.setPosition(X * DISTANCE_FROM_SCREEN_LEFT + scard.getWidth() * NEXT_FLOOR_X_OFFSET * y_offset + scard.getWidth() * X_GAP_BETWEEN_TWO_CARDS * x_offset++, Y * DISTANCE_FROM_SCREEN_BOTTOM + scard.getHeight() * y_offset);

                    scard.draw(getSpriteBatch(), 1.0f);
                    ++index;
                }
                break;
            case "PHASE_3":
                for (VCard vCard : dataModel.getPhase().getBoard().getVCards()) {
                    Sprite scard = getCardCache().get(String.format("%d_%s", vCard.getCardIndex(), Card.CardSize.BIG.value(), vCard.getOrientation().value()));
                    if (scard == null) {
                        Card card = new Card(vCard.getCardIndex(), Card.CardSize.BIG);
                        scard = getCardSprites().get(card.getFileName(Card.CardSize.BIG, vCard.getOrientation()));
                        getCardCache().put(String.format("%d_%s_%s", vCard.getCardIndex(), Card.CardSize.BIG.value(), vCard.getOrientation().value()), scard);
                    }
                    scard.setPosition(X * 0.22f, Y * 0.23f);
                    scard.draw(getSpriteBatch(), 1.0f);
                }
                break;
        }
    }

    @Override
    public void dispose() {
        getFaceDownBigCard().getTexture().dispose();
        getFaceDownSmallCard().getTexture().dispose();
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
