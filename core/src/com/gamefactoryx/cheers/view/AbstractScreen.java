package com.gamefactoryx.cheers.view;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.*;
import com.gamefactoryx.cheers.model.Model;
import com.gamefactoryx.cheers.tool.Configuration;
import com.gamefactoryx.cheers.tool.Orientation;
import com.gamefactoryx.cheers.tool.Resolution;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bernat on 28.04.2017.
 */
@SuppressWarnings("DefaultFileTemplate")
public abstract class AbstractScreen implements Screen {


    protected final int FREE = 0;
    protected final int CLICKED = 1;

    private static Camera camera;
    private static Viewport landscapeViewport;
    private static Viewport portraitViewport;

    private Sprite landscapeSprite;
    private Sprite portraitSprite;
    private Sprite logo;
    private Batch spriteBatch;
    private boolean[] clicked;
    private Sprite[][] buttons;
    private Map<String, Sprite> cardSprites = new HashMap<>();
    private Sprite textBox;
    private Sprite backButtonSprite;
    private Sprite rulesButtonSprite;
    private Sprite loadingSprite;


    private int yScrollPos;

    private static Map<String, Sprite> cardCache = new HashMap<>();
    private Sprite faceDownBigCard;
    private Sprite faceDownSmallCard;

    private Model rulesModel;
    private Sprite rulesTextBoxLandscape;
    private Sprite rulesTextBoxPortrait;

    public Sprite[][] getButtons() {
        return buttons;
    }

    public boolean[] getClicked() {
        return clicked;
    }

    public int getCountOfButtons() {
        return buttons != null ? buttons.length : 0;
    }

    public Sprite getTextBox() {
        return textBox;
    }


    public Sprite getLandscapeSprite() {
        return landscapeSprite;
    }

    public Sprite getPortraitSprite() {
        return portraitSprite;
    }

    public Batch getSpriteBatch() {
        return spriteBatch;
    }

    public Sprite getLogo() {
        return logo;
    }

    public Map<String, Sprite> getCardSprites() {
        return cardSprites;
    }

    public void setClicked(boolean[] clicked) {
        this.clicked = clicked;
    }

    public void setButtons(Sprite[][] buttons) {
        this.buttons = buttons;
    }

    public void setTextBox(Sprite textBox) {
        this.textBox = textBox;
    }

    public void setLogo(Sprite logo) {
        this.logo = logo;
    }

    public Sprite getLoadingSprite() {
        return loadingSprite;
    }

    public void setLoadingSprite(Sprite loadingSprite) {
        this.loadingSprite = loadingSprite;
    }

    protected abstract void initSprites();

    protected abstract void drawText();

    protected abstract void initTextBox();

    protected abstract void initButtons();

    protected abstract void drawButtons();

    protected abstract void initLogo();

    protected abstract void drawLogo();

    protected abstract void initCards();

    protected abstract void initLoadingSprite();
    protected abstract void drawLoadingSprite();

    protected abstract void drawCards();

    public int getYScrollPos() {
        return yScrollPos;
    }

    public void setYScrollPos(int yScrollPos) {
        this.yScrollPos = yScrollPos;
    }

    public static Map<String, Sprite> getCardCache() {
        return cardCache;
    }

    public static void setCardCache(Map<String, Sprite> cardCache) {
        AbstractScreen.cardCache = cardCache;
    }

    public Sprite getFaceDownBigCard() {
        return faceDownBigCard;
    }

    public void setFaceDownBigCard(Sprite faceDownBigCard) {
        this.faceDownBigCard = faceDownBigCard;
    }

    public Sprite getFaceDownSmallCard() {
        return faceDownSmallCard;
    }

    public void setFaceDownSmallCard(Sprite faceDownSmallCard) {
        this.faceDownSmallCard = faceDownSmallCard;
    }

    @Override
    public void show() {
        if (camera == null) {
            camera = new OrthographicCamera();
            portraitViewport = new StretchViewport(Resolution.getGameWorldWidthPortrait(),
                    Resolution.getGameWorldHeightPortrait(), camera);
            landscapeViewport = new StretchViewport(Resolution.getGameWorldWidthLandscape(),
                    Resolution.getGameWorldHeightLandscape(), camera);
            getViewport().apply(false);
        }
        spriteBatch = new SpriteBatch();


        initSprites();
        initLogo();
        initButtons();
        initCards();
        initTextBox();
        initLoadingSprite();
        initRulesTextBoxes();
    }

    private void initRulesTextBoxes(){
        rulesTextBoxLandscape = new Sprite(new Texture("common/rules_pop_up_landscape.png"));
        rulesTextBoxPortrait = new Sprite(new Texture("common/rules_Pop_up_Portrait.png"));
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        drawSprite();
    }

    @Override
    public void resize(int width, int height) {
        if(rulesButtonSprite != null) {
            if (Orientation.getOrientation() == Input.Orientation.Portrait) {
                rulesButtonSprite.setPosition(Resolution.getGameWorldWidthPortrait() - rulesButtonSprite.getWidth(),
                        Resolution.getGameWorldHeightPortrait() - rulesButtonSprite.getHeight());
            } else {
                rulesButtonSprite.setPosition(Resolution.getGameWorldWidthLandscape() - rulesButtonSprite.getWidth(),
                        Resolution.getGameWorldHeightLandscape() - rulesButtonSprite.getHeight());
            }
        }
        if(rulesTextBoxLandscape != null && rulesTextBoxPortrait != null){
            rulesTextBoxLandscape.setSize( Resolution.getGameWorldWidthLandscape() * 0.8f, Resolution.getGameWorldHeightLandscape() * 0.8f);
            rulesTextBoxPortrait.setSize( Resolution.getGameWorldWidthPortrait() * 0.8f, Resolution.getGameWorldHeightPortrait() * 0.8f);
        }


        getViewport().update(width, height);
        setCameraPosition();
    }

    @Override
    public final void pause() {

    }

    @Override
    public final void resume() {

    }

    @Override
    public final void hide() {

    }

    @Override
    public void dispose() {
        for (int i = 0; i < getCountOfButtons(); i++)
            for (int j = 0; j < getButtons()[i].length; j++)
                getButtons()[i][j].getTexture().dispose();

        if (getTextBox() != null)
            getTextBox().getTexture().dispose();
        if (logo != null)
            logo.getTexture().dispose();
        if (portraitSprite != null)
            portraitSprite.getTexture().dispose();
        if (landscapeSprite != null)
            landscapeSprite.getTexture().dispose();
        if (spriteBatch != null)
            spriteBatch.dispose();
        if (backButtonSprite != null)
            backButtonSprite.getTexture().dispose();
        if (rulesButtonSprite != null)
            rulesButtonSprite.getTexture().dispose();
        if(rulesTextBoxLandscape != null)
            rulesTextBoxLandscape.getTexture().dispose();
        if(rulesTextBoxPortrait != null)
            rulesTextBoxPortrait.getTexture().dispose();

        if(loadingSprite != null)
            loadingSprite.getTexture().dispose();
    }

    public void setLandscapeSprite(Sprite landscapeSprite) {
        this.landscapeSprite = landscapeSprite;
        landscapeSprite.setSize(Resolution.getGameWorldWidthLandscape(), Resolution.getGameWorldHeightLandscape());
    }

    public void setPortraitSprite(Sprite portraitSprite) {
        this.portraitSprite = portraitSprite;
        portraitSprite.setSize(Resolution.getGameWorldWidthPortrait(), Resolution.getGameWorldHeightPortrait());
    }

    public Sprite getBackButtonSprite() {
        return backButtonSprite;
    }

    public Sprite getRulesButtonSprite() {
        return rulesButtonSprite;
    }

    private void drawSprite() {
        spriteBatch.begin();
        spriteBatch.setProjectionMatrix(camera.combined);
        drawMainSprite();
        drawLogo();
        drawCards();
        drawButtons();
        drawText();
        /*if (backButtonSprite != null && Configuration.isShowBackButton())
            backButtonSprite.draw(spriteBatch);*/
        if (rulesButtonSprite != null && Configuration.isShowRules()) {
            rulesButtonSprite.draw(spriteBatch);
            if(rulesModel != null && rulesModel.isShowRulesText()){
                if(Orientation.getOrientation() == Input.Orientation.Portrait){
                    rulesTextBoxPortrait.setPosition(Resolution.getGameWorldWidthPortrait() / 2 - rulesTextBoxPortrait.getWidth() / 2,
                             Resolution.getGameWorldHeightPortrait() /2  - rulesTextBoxPortrait.getHeight() / 2);
                    rulesTextBoxPortrait.draw(getSpriteBatch());
                }else{
                    rulesTextBoxLandscape.setPosition(Resolution.getGameWorldWidthLandscape() / 2 - rulesTextBoxLandscape.getWidth() / 2,
                            Resolution.getGameWorldHeightLandscape() / 2 - rulesTextBoxLandscape.getHeight() / 2);
                    rulesTextBoxLandscape.draw(getSpriteBatch());
                }
            }

        }

        drawLoadingSprite();
        spriteBatch.end();

    }

    private void setCameraPosition() {
        if (Orientation.getOrientation() == Input.Orientation.Landscape)
            camera.position.set(Resolution.getGameWorldWidthLandscape() / 2, Resolution.getGameWorldHeightLandscape() / 2, 0);
        else
            camera.position.set(Resolution.getGameWorldWidthPortrait() / 2, Resolution.getGameWorldHeightPortrait() / 2, 0);
        camera.update();

    }

    void drawMainSprite() {
        if (landscapeSprite != null && portraitSprite != null) {
            if (Orientation.getOrientation() == Input.Orientation.Landscape) {
                landscapeSprite.draw(spriteBatch, 1);
            } else {
                portraitSprite.draw(spriteBatch, 1);
            }
        }
    }

    private Viewport getViewport() {
        if (Orientation.getOrientation() == Input.Orientation.Portrait)
            return portraitViewport;
        else
            return landscapeViewport;
    }

    protected void initBackButton() {
        backButtonSprite = new Sprite(new Texture("common/return.png"));
        backButtonSprite.setSize(Resolution.getGameWorldWidthPortrait() * 0.05f, Resolution.getGameWorldHeightPortrait() * Resolution.getAspectRatio() * 0.05f);

    }

    protected void initRulesButton(Model model) {
        rulesButtonSprite = new Sprite(new Texture(Configuration.getLanguage() + "/rules_icon.png"));
        rulesButtonSprite.setSize(Resolution.getGameWorldWidthPortrait() * 0.1f, Resolution.getGameWorldHeightPortrait() * Resolution.getAspectRatio() * 0.2f);
        rulesModel = model;
    }

    public static Camera getCamera() {
        return camera;
    }

    public Model getRulesModel(){
        return rulesModel;
    }
}