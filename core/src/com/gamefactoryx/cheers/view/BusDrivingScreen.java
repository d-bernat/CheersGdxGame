package com.gamefactoryx.cheers.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.gamefactoryx.cheers.model.BusDrivingModel;
import com.gamefactoryx.cheers.model.Configuration;
import com.gamefactoryx.cheers.model.bus_driving.Card;
import com.gamefactoryx.cheers.tool.Resolution;


/**
 * Created by bernat on 16.05.2017.
 */
public class BusDrivingScreen extends AbstractScreen {


    public BusDrivingScreen(){
        super();
        setDataModel(BusDrivingModel.getInstance());
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
    protected void drawText() {

    }

    @Override
    protected void initTextBox() {

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
        BusDrivingModel model = ((BusDrivingModel)getDataModel());
        for(Integer iCard: model.getCroupier().getCards()) {
            Card card = new Card(iCard, Card.CardSize.BIG);
            getCardSprites().put(card.getFile(Card.CardSize.BIG), new Sprite(model.getCardTextures().get(card.getFile(Card.CardSize.BIG))));
        }

        for(Integer iCard: model.getCroupier().getCards()) {
            Card card = new Card(iCard, Card.CardSize.SMALL);
            getCardSprites().put(card.getFile(Card.CardSize.SMALL), new Sprite(model.getCardTextures().get(card.getFile(Card.CardSize.SMALL))));
        }

    }

    @Override
    protected void drawCards() {
        BusDrivingModel model = ((BusDrivingModel)getDataModel());

        int index = 0;
        for(Integer iCard: model.getPlayer().getCards())
        {
            Card card = new Card(iCard, Card.CardSize.SMALL);
            Sprite scard = getCardSprites().get(card.getFile(Card.CardSize.SMALL));
            scard.setPosition(0f, 100.0f * index++);
            scard.draw(getSpriteBatch(), 1.0f);
        }

        for(Integer iCard: model.getPlayer().getCards())
        {
            Card card = new Card(iCard, Card.CardSize.SMALL);
            Sprite scard = getCardSprites().get(card.getFile(Card.CardSize.BIG));
            scard.setPosition(200.00f, 100.0f * index++);
            scard.draw(getSpriteBatch(), 1.0f);
        }
    }


}
