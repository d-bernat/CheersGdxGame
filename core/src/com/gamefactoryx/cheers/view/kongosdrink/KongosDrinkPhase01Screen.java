package com.gamefactoryx.cheers.view.kongosdrink;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.gamefactoryx.cheers.model.kongosdrink.AvatarType;
import com.gamefactoryx.cheers.model.kongosdrink.KongosDrinkMainModel;
import com.gamefactoryx.cheers.model.kongosdrink.KongosDrinkPhase01Model;
import com.gamefactoryx.cheers.tool.FontHelper;
import com.gamefactoryx.cheers.tool.Orientation;
import com.gamefactoryx.cheers.tool.Resolution;
import com.gamefactoryx.cheers.tool.kongosdrink.Configuration;
import com.gamefactoryx.cheers.view.AbstractScreen;


/**
 * Created by bernat on 16.05.2017.
 */
public class KongosDrinkPhase01Screen extends AbstractScreen {


    private float X, Y;

    private BitmapFont font;
    private FreeTypeFontGenerator generator;
    private int FONT_SIZE;
    private Sprite teamTextBox;
    private Sprite avatarBox;
    private Sprite disabledSprite;


    public KongosDrinkPhase01Screen() {
        super();
    }


    @Override
    public void show() {
        super.show();
        initBackButton();
        initRulesButton(KongosDrinkPhase01Model.getInstance());
    }


    @Override
    protected void initSprites() {
        setLandscapeSprite(new Sprite(new Texture(com.gamefactoryx.cheers.tool.Configuration.getLanguage() + "/kongosdrink/kongosridescreen.png")));
        setPortraitSprite(new Sprite(new Texture(com.gamefactoryx.cheers.tool.Configuration.getLanguage() + "/kongosdrink/kongosridescreen.png")));
        getLandscapeSprite().setSize(Resolution.getGameWorldWidthLandscape(), Resolution.getGameWorldHeightLandscape());
        getPortraitSprite().setSize(Resolution.getGameWorldWidthPortrait(), Resolution.getGameWorldHeightPortrait());

        teamTextBox = new Sprite(new Texture("common/kongos_drink/text_box_horizontal.png"));
        avatarBox = new Sprite(new Texture("common/kongos_drink/box.png"));
        disabledSprite = new Sprite( new Texture ("common/kongos_drink/disabled.png"));

    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        float FONT_SIZE_ON_SCREEN = 0.03f;
        if (com.gamefactoryx.cheers.tool.Configuration.getLanguage() == com.gamefactoryx.cheers.tool.Configuration.LanguageEnum.SK)
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

        getLoadingSprite().setSize(X * 0.4f, X * 0.4f
                * getLoadingSprite().getHeight() / getLoadingSprite().getWidth());
        getLoadingSprite().setPosition(X * 0.5f - getLoadingSprite().getWidth() * 0.5f, Y * 0.5f - getLoadingSprite().getHeight() * 0.5f);

        for (int i = 1; i < getButtons().length; i++) {
            getButtons()[i][0].setSize(X * 0.1f, X * 0.1f);
        }
        getTextBox().setSize(X * 0.65f, Y * 0.077f);

        getButtons()[getButtons().length - 2][0].setSize(X * 0.135f, X * 0.135f);
        getButtons()[getButtons().length - 2][1].setSize(X * 0.135f, X * 0.135f);
        getButtons()[getButtons().length - 1][0].setSize(X * 0.135f, X * 0.135f);
        getButtons()[getButtons().length - 1][1].setSize(X * 0.135f, X * 0.135f);



        if (Configuration.getInstance().getGameType() == Configuration.GameTypeEnum.DOGFIGHT)
            teamTextBox.setSize(X * 0.65f, Y * 0.1f);
        else if (Configuration.getInstance().getGameType() == Configuration.GameTypeEnum.TEAMOFTWO_VS_TEAMOFTWO)
            teamTextBox.setSize(X * 0.65f, Y * 0.07f);
        else
            teamTextBox.setSize(X * 0.65f, Y * 0.2f);

        avatarBox.setSize(X * 0.65f, getButtons()[1][0].getHeight() * Math.round(AvatarType.values().length * 0.2f * 1.1f ));
        disabledSprite.setSize(X * 0.1f, X * 0.1f);
    }

    @Override
    protected void drawText() {
        getTextBox().setPosition(X * 0.5f - getTextBox().getWidth() * 0.5f, Y * 0.8f);
        getTextBox().draw(getSpriteBatch(), 1.0f);
        if (Configuration.getInstance().getGameType() != Configuration.GameTypeEnum.DOGFIGHT) {
            teamTextBox.setPosition(getTextBox().getX(), getTextBox().getY() - teamTextBox.getHeight());
            teamTextBox.draw(getSpriteBatch(), 1.0f);
        }

        float DISTANCE_FROM_TEXTBOX_BOTTOM = 0.85f;
        String name = Configuration.getInstance().getPlayers().get(KongosDrinkPhase01Model.getInstance().getPlayerToConfigureIndex()).getName();
        FontHelper.getGlyphLayout().setText(font, name);
        font.draw(getSpriteBatch(), FontHelper.getGlyphLayout(), X * 0.46f - FontHelper.getGlyphLayout().width / 2.4f,
                (Y * DISTANCE_FROM_TEXTBOX_BOTTOM));

        if (Configuration.getInstance().getGameType() != Configuration.GameTypeEnum.DOGFIGHT) {
            for (int i = 1;
                 i < Configuration.getInstance().getPlayers().get(KongosDrinkPhase01Model.getInstance().getPlayerToConfigureIndex()).getSubjects().size();
                 i++) {
                FontHelper.getGlyphLayout().setText(font,
                        Configuration.getInstance().getPlayers().get(KongosDrinkPhase01Model.getInstance()
                                .getPlayerToConfigureIndex()).getSubjects().get(i).getName());
                font.draw(getSpriteBatch(), FontHelper.getGlyphLayout(), teamTextBox.getX() * 1.5f,
                        teamTextBox.getY() + teamTextBox.getHeight() * 0.92f - (i - 1) * FontHelper.getGlyphLayout().height * 1.15f);
            }
        }

        int av_index = Configuration.getInstance().getPlayers().get(KongosDrinkPhase01Model.getInstance().getPlayerToConfigureIndex()).getAvatar().value() + 1;
        getButtons()[av_index][0].setPosition(X * 0.5f - getTextBox().getWidth() * 0.5f, Y * 0.8f + getTextBox().getHeight() * 0.7f);
        getButtons()[av_index][0].draw(getSpriteBatch(), 1.0f);
    }


    @Override
    protected void initTextBox() {
        setTextBox(new Sprite(new Texture(com.gamefactoryx.cheers.tool.Configuration.getLanguage() + "/Busdrivingscreen/busdriving_names/namebox_busdriving.png")));
    }

    @Override
    protected void initButtons() {
        setButtons(new Sprite[AvatarType.values().length + 1 + 2][2]);
        getButtons()[0][0] = new Sprite(new Texture("common/continue.png"));
        getButtons()[0][1] = new Sprite(new Texture("common/continue.png"));
        getButtons()[0][0].setSize(Resolution.getGameWorldWidthPortrait() * 0.2f, Resolution.getGameWorldHeightPortrait() * Resolution.getAspectRatio() * 0.2f);
        getButtons()[0][1].setSize(Resolution.getGameWorldWidthPortrait() * 0.2f, Resolution.getGameWorldHeightPortrait() * Resolution.getAspectRatio() * 0.2f);


        for(int i = 0; i < AvatarType.values().length; i++ ){
            getButtons()[i+1][0] = new Sprite(new Texture("common/kongos_drink/player/" + AvatarType.values()[i].toString() + "/" + AvatarType.values()[i].toString() + "_1.png"));
            getButtons()[i+1][1] = new Sprite(new Texture("common/kongos_drink/player/" + AvatarType.values()[i].toString() + "/" + AvatarType.values()[i].toString() + "_1.png"));
        }

        getButtons()[getButtons().length - 2][0] = new Sprite(new Texture("common/review.png"));
        getButtons()[getButtons().length - 2][1] = new Sprite(new Texture("common/review.png"));
        getButtons()[getButtons().length - 1][0] = new Sprite(new Texture("common/forward.png"));
        getButtons()[getButtons().length - 1][1] = new Sprite(new Texture("common/forward.png"));

        setClicked(new boolean[getCountOfButtons()]);
    }

    @Override
    protected void drawButtons() {


        avatarBox.setPosition(getTextBox().getX(),
                Y * 0.54f - getButtons()[getButtons().length - 3][0].getHeight() * 1.12f * (Math.round(AvatarType.values().length * 0.2f) - 1));
        avatarBox.draw(getSpriteBatch(), 1.0f);
        int y_offset = 0;
        int x_offset = 0;
        for (int i = 1; i < getButtons().length - 2; i++) {
            if (x_offset == 5) {
                ++y_offset;
                x_offset = 0;
            }
            getButtons()[i][0].setPosition(X * 0.5f - getButtons()[i][0].getWidth() * 5 * 1.1f * 0.5f + getButtons()[i][0].getWidth() * x_offset * 1.1f,
                    Y * 0.54f - getButtons()[i][0].getHeight() * y_offset * 1.1f);
            int av_index = Configuration.getInstance().getPlayers().get(KongosDrinkPhase01Model.getInstance().getPlayerToConfigureIndex()).getAvatar().value() + 1;
            getButtons()[i][0].draw(getSpriteBatch(), av_index == i ? 1.0f : 0.5f);
            if(isDisabled(i)) {
                disabledSprite.setPosition(X * 0.5f - getButtons()[i][0].getWidth() * 5 * 1.1f * 0.5f + getButtons()[i][0].getWidth() * x_offset * 1.1f,
                        Y * 0.54f - getButtons()[i][0].getHeight() * y_offset * 1.1f);
                disabledSprite.draw(getSpriteBatch(), 1.0f);
            }
            ++x_offset;
        }

        getButtons()[getButtons().length - 2][0].setPosition(getTextBox().getX() - getButtons()[getButtons().length - 2][0].getWidth(), getTextBox().getY());
        getButtons()[getButtons().length - 1][0].setPosition(getTextBox().getX() + getTextBox().getWidth(), getTextBox().getY());
        getButtons()[getButtons().length - 2][0].draw(getSpriteBatch(),
                KongosDrinkPhase01Model.getInstance().getPlayerToConfigureIndex() == 0 ? 0.5f : 1.0f);
        getButtons()[getButtons().length - 1][0].draw(getSpriteBatch(),
                KongosDrinkPhase01Model.getInstance().getPlayerToConfigureIndex() == Configuration.getInstance().enabledPlayers() - 1 ? 0.5f : 1.0f);

        getButtons()[0][0].setPosition(X * 0.75f, Y * 0.02f);
        getButtons()[0][0].draw(getSpriteBatch(), 1.0f);
    }

    @Override
    protected void initLogo() {
        setLogo(new Sprite(new Texture("common/logo.png")));
        float logoRatio = getLogo().getHeight() / getLogo().getWidth();
        getLogo().setSize(Resolution.getGameWorldWidthPortrait() * 0.4f, Resolution.getGameWorldHeightPortrait() * Resolution.getAspectRatio() * 0.4f * logoRatio);
        getLogo().setPosition(Resolution.getGameWorldWidthPortrait() / 3.65f, Resolution.getGameWorldHeightPortrait() / 7f);
    }

    @Override
    protected void drawLogo() {
        getLogo().draw(getSpriteBatch(), 75.0f);
    }

    @Override
    protected void initCards() {

    }

    @Override
    protected void initLoadingSprite() {
        setLoadingSprite(new Sprite(new Texture(com.gamefactoryx.cheers.tool.Configuration.getLanguage() + "/loading.png")));
    }

    @Override
    protected void drawLoadingSprite() {
        if (KongosDrinkMainModel.getInstance().isLoadingNextStage())
            getLoadingSprite().draw(getSpriteBatch(), 1);

    }

    @Override
    protected void drawCards() {
    }

    @Override
    public void dispose() {
        if (font != null)
            font.dispose();
        super.dispose();

        teamTextBox.getTexture().dispose();
        avatarBox.getTexture().dispose();
        disabledSprite.getTexture().dispose();
    }


    private boolean isDisabled(int index){

        boolean rs = false;
        for(int i = 0; i < Configuration.getInstance().getPlayers().size(); i++) {
            int av_index = Configuration.getInstance().getPlayers().get(KongosDrinkPhase01Model.getInstance().getPlayerToConfigureIndex()).getAvatar().value() + 1;
            if( index == Configuration.getInstance().getPlayers().get(i).getAvatar().value() + 1 && av_index != index) {
                rs = true;
            }else{
            }
        }
        return rs;
    }

}
