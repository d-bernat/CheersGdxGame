package com.gamefactoryx.cheersapp.view.kongosdrink;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkSetupModel;
import com.gamefactoryx.cheersapp.tool.kongosdrink.Configuration;
import com.gamefactoryx.cheersapp.view.AbstractScreen;
import com.gamefactoryx.cheersapp.view.CheckedButton;


/**
 * Created by bernat on 16.05.2017.
 */
public class KongosDrinkSetupScreen extends AbstractScreen {


    private float X, Y;
    private Sprite fieldLengthFrame, gameModusFrame;
    private Sprite disabledSprite;


    public KongosDrinkSetupScreen() {
        super();
    }


    @Override
    public void show() {
        super.show();
        initBackButton();
        initRulesButton(KongosDrinkSetupModel.getInstance());
    }


    @Override
    protected void initSprites() {
        setLandscapeSprite(new Sprite(new Texture(com.gamefactoryx.cheersapp.tool.Configuration.getLanguage() + "/kongosdrink/kongosridescreen.png")));
        setPortraitSprite(new Sprite(new Texture(com.gamefactoryx.cheersapp.tool.Configuration.getLanguage() + "/kongosdrink/kongosridescreen.png")));
        getLandscapeSprite().setSize(com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldWidthLandscape(), com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightLandscape());
        getPortraitSprite().setSize(com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldWidthPortrait(), com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightPortrait());

        fieldLengthFrame = new Sprite( new Texture (com.gamefactoryx.cheersapp.tool.Configuration.getLanguage() + "/kongosdrink/field_length.png"));
        gameModusFrame = new Sprite( new Texture (com.gamefactoryx.cheersapp.tool.Configuration.getLanguage() + "/kongosdrink/mode.png"));
        disabledSprite = new Sprite( new Texture ("common/kongos_drink/disabled.png"));
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        if (com.gamefactoryx.cheersapp.tool.Orientation.getOrientation() == Input.Orientation.Portrait) {
            X = com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldWidthPortrait();
            Y = com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightPortrait();
        } else {
            X = com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldWidthLandscape();
            Y = com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightLandscape();
        }

        final float FACTOR = 0.15f;
        /*for(int  i = 0; i < getButtons().length - 4; i++){
            getButtons()[i][0].setSize(X * FACTOR, X * FACTOR
                    * getButtons()[i][0].getHeight()/getButtons()[i][0].getWidth());
            getButtons()[i][1].setSize(X * FACTOR, X * FACTOR
                    * getButtons()[i][1].getHeight()/getButtons()[i][1].getWidth());
        }*/
        for(int  i = 0; i < getButtons().length - 4; i++){
            getButtons()[i][0].setSize(X * FACTOR, X * FACTOR);
            getButtons()[i][1].setSize(X * FACTOR, X * FACTOR);
        }

        for(int  i = 6; i < getButtons().length - 1; i++) {
            getButtons()[i][0].setSize(X * 0.6f, Y * 0.07f);
            getButtons()[i][1].setSize(X * FACTOR, X * FACTOR
                    * getButtons()[i][1].getHeight() / getButtons()[i][1].getWidth());
        }
        fieldLengthFrame.setSize(X * 0.7f, Y * 0.25f);
        gameModusFrame.setSize(X * 0.7f, Y * 0.31f);
        disabledSprite.setSize(X * 0.1f, X * 0.1f);

    }

    @Override
    protected void drawText() {
    }


    @Override
    protected void initTextBox() {

    }

    @Override
    protected void initButtons() {
        setButtons(new CheckedButton[10][2]);
        getButtons()[0][0] = new CheckedButton(new Texture("common/kongos_drink/10.png"), true);
        getButtons()[0][1] = new CheckedButton(new Texture("common/kongos_drink/10.png"), true);
        getButtons()[1][0] = new CheckedButton(new Texture("common/kongos_drink/15.png"));
        getButtons()[1][1] = new CheckedButton(new Texture("common/kongos_drink/15.png"));
        getButtons()[2][0] = new CheckedButton(new Texture("common/kongos_drink/20.png"));
        getButtons()[2][1] = new CheckedButton(new Texture("common/kongos_drink/20.png"));
        getButtons()[3][0] = new CheckedButton(new Texture("common/kongos_drink/30.png"), true);
        getButtons()[3][1] = new CheckedButton(new Texture("common/kongos_drink/30.png"), true);
        getButtons()[4][0] = new CheckedButton(new Texture("common/kongos_drink/40.png"), true);
        getButtons()[4][1] = new CheckedButton(new Texture("common/kongos_drink/40.png"), true);
        getButtons()[5][0] = new CheckedButton(new Texture("common/kongos_drink/50.png"), true);
        getButtons()[5][1] = new CheckedButton(new Texture("common/kongos_drink/50.png"), true);

        getButtons()[6][0] = new CheckedButton(new Texture(com.gamefactoryx.cheersapp.tool.Configuration.getLanguage() + "/kongosdrink/singleplayer.png"));
        getButtons()[6][1] = new CheckedButton(new Texture(com.gamefactoryx.cheersapp.tool.Configuration.getLanguage() + "/kongosdrink/singleplayer.png"));
        getButtons()[7][0] = new CheckedButton(new Texture(com.gamefactoryx.cheersapp.tool.Configuration.getLanguage() + "/kongosdrink/tagteam.png"), true);
        getButtons()[7][1] = new CheckedButton(new Texture(com.gamefactoryx.cheersapp.tool.Configuration.getLanguage() + "/kongosdrink/tagteam.png"), true);
        getButtons()[8][0] = new CheckedButton(new Texture(com.gamefactoryx.cheersapp.tool.Configuration.getLanguage() + "/kongosdrink/teams.png"), true);
        getButtons()[8][1] = new CheckedButton(new Texture(com.gamefactoryx.cheersapp.tool.Configuration.getLanguage() + "/kongosdrink/teams.png"), true);


        getButtons()[9][0] = new CheckedButton(new Texture("common/continue.png"));
        getButtons()[9][1] = new CheckedButton(new Texture("common/continue.png"));
        getButtons()[9][0].setSize(com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldWidthPortrait() * 0.2f, com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightPortrait() * com.gamefactoryx.cheersapp.tool.Resolution.getAspectRatio() * 0.2f);
        getButtons()[9][1].setSize(com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldWidthPortrait() * 0.2f, com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightPortrait() * com.gamefactoryx.cheersapp.tool.Resolution.getAspectRatio() * 0.2f);

        setClicked(new boolean[getCountOfButtons()]);
    }

    @Override
    protected void drawButtons() {

        fieldLengthFrame.setPosition(X * 0.5f - fieldLengthFrame.getWidth() * 0.5f, Y * 0.62f);
        fieldLengthFrame.draw(getSpriteBatch(), 1);
        gameModusFrame.setPosition(X * 0.5f - gameModusFrame.getWidth() * 0.5f, Y * 0.20f);
        gameModusFrame.draw(getSpriteBatch(), 1);


        float PORTRAIT_DISTANCE_FROM_BOTTOM = 0.76f;
        float y_offset = 0, x_offset = 0;
        int x_index = 0;

        for (int i = 0; i < getButtons().length - 4; i++) {
            if (i == 3) {
                x_index = 0;
                y_offset = getButtons()[i][0].getHeight() * 1.3f;
            }
            x_offset = getButtons()[i][0].getWidth() * 1.3f * x_index++;
            //getButtons()[i][0].setPosition(X * 0.5f - (getButtons()[i][0].getWidth() * 1.3f * 2 + getButtons()[i][0].getWidth())  * 0.5f   + x_offset, Y * 0.75f - y_offset);
            getButtons()[i][0].setPosition(X * 0.5f - ( 2 * getButtons()[i][0].getWidth() * 1.3f +  getButtons()[i][0].getWidth()) * 0.5f   + x_offset, Y * 0.75f - y_offset);
            float alpha = 1.0f;
            if( i == 0 ) alpha = Configuration.getInstance().getGameSize().getValue() == 10 ? 1.0f : 0.5f;
            else if( i == 1 ) alpha = Configuration.getInstance().getGameSize().getValue() == 15 ? 1.0f : 0.5f;
            else  alpha = Configuration.getInstance().getGameSize().getValue()  == (i) * 10 ? 1.0f : 0.5f;

            getButtons()[i][0].draw(getSpriteBatch(), alpha);

        }

        for(int i = 6; i < getButtons().length - 1; i++){
            float alpha;
            if( i == 6 ) alpha = Configuration.getInstance().getGameType() == Configuration.GameTypeEnum.DOGFIGHT ? 1.0f : 0.5f;
            else if( i == 7 ) alpha = Configuration.getInstance().getGameType() == Configuration.GameTypeEnum.TEAMOFTWO_VS_TEAMOFTWO ? 1.0f : 0.5f;
            else  alpha = Configuration.getInstance().getGameType() == Configuration.GameTypeEnum.TEAM_VS_TEAM ? 1.0f : 0.5f;

            getButtons()[i][0].setPosition( X * 0.5f  - getButtons()[i][0].getWidth() * 0.5f , Y * 0.4f - getButtons()[i][0].getHeight() * 1.25f * (i - 6));
            getButtons()[i][0].draw(getSpriteBatch(), alpha);
            if(!com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().isLoadingNextStage() && i == 7  && (Configuration.getInstance().enabledPlayers() <= 2 || Configuration.getInstance().enabledPlayers() % 2 != 0 )){
                disabledSprite.setPosition(X * 0.5f  - getButtons()[i][0].getWidth() * 0.5f , Y * 0.4f - getButtons()[i][0].getHeight() * 1.25f * (i - 6));
                disabledSprite.draw(getSpriteBatch(), 1);
            }

            if(!com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().isLoadingNextStage() && i == 8  && Configuration.getInstance().enabledPlayers() <= 2){
                disabledSprite.setPosition(X * 0.5f  - getButtons()[i][0].getWidth() * 0.5f , Y * 0.4f - getButtons()[i][0].getHeight() * 1.25f * (i - 6));
                disabledSprite.draw(getSpriteBatch(), 1);
            }
        }


        getButtons()[9][0].setPosition(X * 0.75f, Y * 0.02f);
        getButtons()[9][0].draw(getSpriteBatch(), 1.0f);

    }

    @Override
    protected void initLogo() {
        setLogo(new Sprite(new Texture("common/logo.png")));
        float logoRatio = getLogo().getHeight() / getLogo().getWidth();
        getLogo().setSize(com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldWidthPortrait() * 0.4f, com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightPortrait() * com.gamefactoryx.cheersapp.tool.Resolution.getAspectRatio() * 0.4f * logoRatio);
        getLogo().setPosition(com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldWidthPortrait() / 3.65f, com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightPortrait() / 7f);
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

    }

    @Override
    protected void drawLoadingSprite() {
    }

    @Override
    protected void drawCards() {
    }

    @Override
    public void dispose() {
        fieldLengthFrame.getTexture().dispose();
        gameModusFrame.getTexture().dispose();
        disabledSprite.getTexture().dispose();
        super.dispose();
    }


}
