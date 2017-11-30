package com.gamefactoryx.cheersapp.view.busdriving;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.gamefactoryx.cheersapp.model.busdriving.BusDrivingPhase4Model;
import com.gamefactoryx.cheersapp.model.busdriving.Croupier;
import com.gamefactoryx.cheersapp.tool.Card;
import com.gamefactoryx.cheersapp.view.AbstractScreen;
import com.gamefactoryx.cheersapp.view.CheckedButton;


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
        initBackButton();
        initRulesButton(BusDrivingPhase4Model.getInstance());
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
        getTextBox().setSize(com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldWidthLandscape() * 0.50f, com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightLandscape() * 0.12f);
        float DISTANCE_FROM_TEXTBOX_BOTTOM = 0.81f;
        getTextBox().setPosition(X * 0.22f, Y * 0.73f);
        getTextBox().draw(getSpriteBatch());

        String name = BusDrivingPhase4Model.getInstance().getBusDriver().getName();

        com.gamefactoryx.cheersapp.tool.FontHelper.getGlyphLayout().setText(font, name);
        font.draw(getSpriteBatch(), com.gamefactoryx.cheersapp.tool.FontHelper.getGlyphLayout(), X * 0.47f - com.gamefactoryx.cheersapp.tool.FontHelper.getGlyphLayout().width / 2.0f, Y * DISTANCE_FROM_TEXTBOX_BOTTOM);

        String info =   BusDrivingPhase4Model.getInstance().isPhaseFinished() ?
                        String.format("Game over :-), drunk: %d", BusDrivingPhase4Model.getInstance().getTotalDrunk())
                        : String.format("drink: %d / drunk: %d", BusDrivingPhase4Model.getInstance().getDrinkPoints(), BusDrivingPhase4Model.getInstance().getTotalDrunk());

        com.gamefactoryx.cheersapp.tool.FontHelper.getGlyphLayout().setText(font, info);
        font.draw(getSpriteBatch(), com.gamefactoryx.cheersapp.tool.FontHelper.getGlyphLayout(), X * 0.47f - com.gamefactoryx.cheersapp.tool.FontHelper.getGlyphLayout().width / 2.0f,
                (Y * DISTANCE_FROM_TEXTBOX_BOTTOM) - com.gamefactoryx.cheersapp.tool.FontHelper.getGlyphLayout().height * 2.5f);
    }


    @Override
    protected void initTextBox() {
        setTextBox(new Sprite(new Texture(com.gamefactoryx.cheersapp.tool.Configuration.getLanguage() + "/Busdrivingscreen/text_box_horizontal.png")));
    }

    @Override
    protected void initButtons() {


        setButtons(new CheckedButton[2][2]);

        getButtons()[0][0] = new CheckedButton(new Texture(com.gamefactoryx.cheersapp.tool.Configuration.getLanguage() + "/Busdrivingscreen/Overtime/overtime_pop_up_symbol_higher_busdriving.png"));
        getButtons()[0][0].setSize(com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldWidthLandscape() * 0.18f, com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightLandscape() * 0.14f);
        getButtons()[0][1] = new CheckedButton(new Texture(com.gamefactoryx.cheersapp.tool.Configuration.getLanguage() + "/Busdrivingscreen/Overtime/overtime_pop_up_symbol_higher_busdriving.png"));
        getButtons()[0][1].setSize(com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldWidthLandscape() * 0.18f, com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightLandscape() * 0.14f);
        getButtons()[1][0] = new CheckedButton(new Texture(com.gamefactoryx.cheersapp.tool.Configuration.getLanguage() + "/Busdrivingscreen/Overtime/overtime_pop_up_symbol_lower_busdriving.png"));
        getButtons()[1][0].setSize(com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldWidthLandscape() * 0.18f, com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightLandscape() * 0.14f);
        getButtons()[1][1] = new CheckedButton(new Texture(com.gamefactoryx.cheersapp.tool.Configuration.getLanguage() + "/Busdrivingscreen/Overtime/overtime_pop_up_symbol_lower_busdriving.png"));
        getButtons()[1][1].setSize(com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldWidthLandscape() * 0.18f, com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightLandscape() * 0.14f);
        setClicked(new boolean[getCountOfButtons()]);

    }

    @Override
    protected void drawButtons() {
        if(!BusDrivingPhase4Model.getInstance().isPhaseFinished()) {
            float PORTRAIT_DISTANCE_FROM_BOTTOM = 0.02f;
            int y_offset = 0;
            for (int i = 0; i < getCountOfButtons(); i++) {
                int click_index = getClicked()[i] ? CLICKED : FREE;
                getButtons()[i][click_index].setPosition(X * 0.07f + BusDrivingPhase4Model.getInstance().getActiveCardIndex() * cardWidth * 1.1f,
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
        com.gamefactoryx.cheersapp.model.busdriving.VCard vCard = Croupier.getInstance().getVCards().last();
        Sprite scard = new Sprite(com.gamefactoryx.cheersapp.tool.Card.getInstance().getCardTexture(vCard.getCardIndex(), com.gamefactoryx.cheersapp.tool.CardSize.BIG, vCard.getOrientation()));
        cardWidth = com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldWidthLandscape() * 0.12f;
        cardHight = com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightLandscape() * 0.34f;
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
        for(com.gamefactoryx.cheersapp.model.busdriving.VCard vCard : BusDrivingPhase4Model.getInstance().getBoard().getVCards()) {
            Sprite scard = new Sprite(Card.getInstance().getCardTexture(vCard.getCardIndex(), com.gamefactoryx.cheersapp.tool.CardSize.BIG, vCard.getOrientation()));
            scard.setPosition(X * 0.04f + x_offset++ * cardWidth * 1.1f, Y * 0.18f);
            scard.setSize(cardWidth, cardHight);
            scard.draw(getSpriteBatch(), 1.0f);
        }

    }

    @Override
    public void dispose() {
        if(font !=null)
            font.dispose();
        super.dispose();
    }


}
