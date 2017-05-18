package com.gamefactoryx.cheers.view;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.gamefactoryx.cheers.model.BusDrivingModel;
import com.gamefactoryx.cheers.model.Configuration;
import com.gamefactoryx.cheers.model.bus_driving.Card;
import com.gamefactoryx.cheers.tool.FontHelper;
import com.gamefactoryx.cheers.tool.Orientation;
import com.gamefactoryx.cheers.tool.Resolution;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by bernat on 16.05.2017.
 */
public class BusDrivingScreen extends AbstractScreen {


    private float X, Y;
    private static BusDrivingModel dataModel = BusDrivingModel.getInstance();
    private static Map<String, Sprite> cardCache = new HashMap<>();
    private Sprite faceDownBigCard;
    private Sprite faceDownSmallCard;
    private BitmapFont font;
    private FreeTypeFontGenerator generator;
    private int FONT_SIZE;

    public BusDrivingScreen() {
        super();
        for (Integer iCard : dataModel.getICards()) {
            Card card = new Card(iCard, Card.CardSize.BIG);
            getCardSprites().put(card.getFileName(Card.CardSize.BIG), new Sprite(dataModel.getCardTextures().get(card.getFileName(Card.CardSize.BIG))));
        }

        for (Integer iCard : dataModel.getICards()) {
            Card card = new Card(iCard, Card.CardSize.SMALL);
            getCardSprites().put(card.getFileName(Card.CardSize.SMALL), new Sprite(dataModel.getCardTextures().get(card.getFileName(Card.CardSize.SMALL))));
        }
        faceDownBigCard = new Sprite(new Texture("common/busdriving_cards/facedown_big_card.png"));
        faceDownSmallCard = new Sprite(new Texture("common/busdriving_cards/facedown_small_card.png"));

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
        float EMPTYCHAR_CHAR_WIDTH_RATIO = 1.6f;
        float DISTANCE_FROM_TEXTBOX_BOTTOM = 0.168f;
        switch (dataModel.getPhase().getName()) {
            case "PHASE_1":
                getTextBox().setPosition(X * 0.14f, Y * 0.78f);
                getTextBox().draw(getSpriteBatch());
                String name;
                //if (name.length() <= 1)
                switch (Configuration.getLanguage()) {
                    case DE:
                        name = "Spieler: " + dataModel.getPlayer().getName();
                        break;
                    case EN:
                        name = "Player: " + dataModel.getPlayer().getName();
                        break;
                    default:
                        name = "Spieler: " + dataModel.getPlayer().getName();

                }
                font.draw(getSpriteBatch(), name, (X - name.length() * font.getSpaceWidth() * EMPTYCHAR_CHAR_WIDTH_RATIO) * 0.5f,
                        Y - (Y - font.getCapHeight() * 2) * DISTANCE_FROM_TEXTBOX_BOTTOM - font.getCapHeight() * 1.3f * 0);
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


    }

    @Override
    protected void drawCards() {
        switch (dataModel.getPhase().getName()) {
            case "PHASE_1":
                int x_offset = 0;
                if (dataModel.getPhase().getBoard().getCards().size == 0) {
                    faceDownBigCard.setPosition(X * 0.22f, Y * 0.23f);
                    faceDownBigCard.draw(getSpriteBatch(), 1.0f);

                }
                for (Integer iCard : dataModel.getPlayer().getCards()) {
                    Sprite scard = cardCache.get(String.format("%d_%s", iCard, Card.CardSize.SMALL.value()));
                    if (scard == null) {
                        Card card = new Card(iCard, Card.CardSize.SMALL);
                        scard = getCardSprites().get(card.getFileName(Card.CardSize.SMALL));
                        cardCache.put(String.format("%d_%s", iCard, Card.CardSize.SMALL.value()), scard);
                    }
                    scard.setSize(X * 0.2f, Y * 0.2f);
                    scard.setPosition(X * 0.1f + scard.getWidth() * x_offset++, Y * 0.02f);
                    scard.draw(getSpriteBatch(), 1.0f);
                }

                for (Integer iCard : dataModel.getPhase().getBoard().getCards()) {
                    Sprite scard = cardCache.get(String.format("%d_%s", iCard, Card.CardSize.BIG.value()));
                    if (scard == null) {
                        Card card = new Card(iCard, Card.CardSize.BIG);
                        scard = getCardSprites().get(card.getFileName(Card.CardSize.BIG));
                        cardCache.put(String.format("%d_%s", iCard, Card.CardSize.BIG.value()), scard);
                    }
                    scard.setPosition(X * 0.22f, Y * 0.23f);
                    scard.draw(getSpriteBatch(), 1.0f);
                }
                break;
        }


    }

    @Override
    public void dispose() {
        faceDownBigCard.getTexture().dispose();
        faceDownSmallCard.getTexture().dispose();
    }

}
