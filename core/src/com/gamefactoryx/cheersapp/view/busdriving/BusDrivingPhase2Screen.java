package com.gamefactoryx.cheersapp.view.busdriving;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.gamefactoryx.cheersapp.model.busdriving.BusDrivingPhase2Model;
import com.gamefactoryx.cheersapp.model.busdriving.Player;
import com.gamefactoryx.cheersapp.model.busdriving.VCard;
import com.gamefactoryx.cheersapp.tool.Resolution;
import com.gamefactoryx.cheersapp.view.AbstractScreen;

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
        initBackButton();
        initRulesButton(BusDrivingPhase2Model.getInstance());
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
       // boolean isSomePlayerActive = false;
        float DISTANCE_FROM_TEXTBOX_BOTTOM = 0.65f;
        getTextBox().setPosition(X * 0.05f, Y * 0.52f);
        if (!BusDrivingPhase2Model.getInstance().isPhaseFinished()) {
            getTextBox().setSize(com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldWidthPortrait() * 0.90f, com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightPortrait() * 0.150f);
            outer:
            for(Player player: BusDrivingPhase2Model.getInstance().getPlayers()) {
                for (VCard vCard : player.getVCards()) {
                    if (vCard.getCredit() > 0) {
                        com.gamefactoryx.cheersapp.tool.FontHelper.getGlyphLayout().setText(font, player.getName());
                        getTextBox().draw(getSpriteBatch());
                        font.draw(getSpriteBatch(), com.gamefactoryx.cheersapp.tool.FontHelper.getGlyphLayout(), X * 0.48f - com.gamefactoryx.cheersapp.tool.FontHelper.getGlyphLayout().width / 2.4f, Y * DISTANCE_FROM_TEXTBOX_BOTTOM);
                        com.gamefactoryx.cheersapp.tool.FontHelper.getGlyphLayout().setText(font, getMessage(vCard.getCredit()));
                        font.draw(getSpriteBatch(), com.gamefactoryx.cheersapp.tool.FontHelper.getGlyphLayout(), X * 0.46f - com.gamefactoryx.cheersapp.tool.FontHelper.getGlyphLayout().width / 2.4f,
                                (Y * DISTANCE_FROM_TEXTBOX_BOTTOM) - com.gamefactoryx.cheersapp.tool.FontHelper.getGlyphLayout().height * 2.5f);
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
                    com.gamefactoryx.cheersapp.tool.FontHelper.getGlyphLayout().setText(font, player.getName());
                    font.draw(getSpriteBatch(), com.gamefactoryx.cheersapp.tool.FontHelper.getGlyphLayout(), X * 0.48f - com.gamefactoryx.cheersapp.tool.FontHelper.getGlyphLayout().width / 2.4f,
                            (Y * DISTANCE_FROM_TEXTBOX_BOTTOM) - com.gamefactoryx.cheersapp.tool.FontHelper.getGlyphLayout().height * -1.65f * y_offset++);
                }
            }
            getTextBox().setSize(com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldWidthPortrait() * 0.90f, com.gamefactoryx.cheersapp.tool.FontHelper.getGlyphLayout().height * 1.65f * (1 + counter) + com.gamefactoryx.cheersapp.tool.FontHelper.getGlyphLayout().height * 2.5f);
            com.gamefactoryx.cheersapp.tool.FontHelper.getGlyphLayout().setText(font, BusDrivingPhase2Model.getInstance().getFinalMessage());

            font.draw(getSpriteBatch(), com.gamefactoryx.cheersapp.tool.FontHelper.getGlyphLayout(), X * 0.48f - com.gamefactoryx.cheersapp.tool.FontHelper.getGlyphLayout().width / 2.4f,
                    (Y * DISTANCE_FROM_TEXTBOX_BOTTOM ) - com.gamefactoryx.cheersapp.tool.FontHelper.getGlyphLayout().height * 3.0f);
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
        getButtons()[0][1].setSize(com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldWidthPortrait() * 0.2f, com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightPortrait() * Resolution.getAspectRatio() * 0.2f);

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
    protected void initLoadingSprite() {

    }

    @Override
    protected void drawLoadingSprite() {

    }

    @Override
    protected void drawCards() {
        int x_offset = 0;
        int y_offset = 0;
        for(Player player: BusDrivingPhase2Model.getInstance().getPlayers()){
            for (VCard vCard : player.getVCards()) {
                Sprite scard = new Sprite(com.gamefactoryx.cheersapp.tool.Card.getInstance().getCardTexture(vCard.getCardIndex(), com.gamefactoryx.cheersapp.tool.CardSize.SMALL, vCard.getOrientation()));
                scard.setSize(X * 0.15f, Y * 0.12f);
                scard.setPosition(X * 0.05f +
                        (/*Configuration.getMaxPlayers()*/ 6 - BusDrivingPhase2Model.getInstance().getCountOfPlayers())/2.0f * scard.getWidth() +
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
            Sprite scard = new Sprite(com.gamefactoryx.cheersapp.tool.Card.getInstance().getCardTexture(vCard.getCardIndex(), com.gamefactoryx.cheersapp.tool.CardSize.SMALL, vCard.getOrientation()));
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
        if(font !=null)
            font.dispose();
        super.dispose();

    }
    private String getMessage(int credit) {
        switch (com.gamefactoryx.cheersapp.tool.Configuration.getLanguage()) {
            case DE:
                if (credit == 1)
                    return String.format(Locale.GERMAN, "%s %d %s", "Du verteilst ", credit, " Schluck!");
                else
                    return String.format(Locale.GERMAN, "%s %d %s", "Du verteilst ", credit, " Schlücke!");
            case EN:
                if (credit == 1)
                    return String.format(Locale.ENGLISH, "%s %d %s", "You pass on", credit, " sip!");
                else
                    return String.format(Locale.ENGLISH, "%s %d %s", "You pass on", credit, " sips!");
            default:
                if (credit == 1)
                    return String.format(Locale.GERMAN, "%s %d %s", "Du verteilst ", credit, " Schluck!");
                else
                    return String.format(Locale.GERMAN, "%s %d %s", "Du verteilst ", credit, " Schlücke!");
        }


    }


}
