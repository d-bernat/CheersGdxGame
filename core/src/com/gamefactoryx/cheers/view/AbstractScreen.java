package com.gamefactoryx.cheers.view;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.viewport.*;
import com.gamefactoryx.cheers.model.Model;
import com.gamefactoryx.cheers.tool.Configuration;
import com.gamefactoryx.cheers.tool.FontHelper;
import com.gamefactoryx.cheers.tool.Orientation;
import com.gamefactoryx.cheers.tool.Resolution;

import java.util.*;

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

    private final static FreeTypeFontGenerator.FreeTypeFontParameter _parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
    private FreeTypeFontGenerator _generator;
    private int _FONT_SIZE;
    private BitmapFont _font;
    private float _X, _Y;


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

    private void initRulesTextBoxes() {
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
        if (rulesButtonSprite != null) {
            if (Orientation.getOrientation() == Input.Orientation.Portrait) {
                rulesButtonSprite.setPosition(Resolution.getGameWorldWidthPortrait() - rulesButtonSprite.getWidth() * 1.2f,
                        Resolution.getGameWorldHeightPortrait() - rulesButtonSprite.getHeight() * 2.0f);
            } else {
                rulesButtonSprite.setPosition(Resolution.getGameWorldWidthLandscape() - rulesButtonSprite.getWidth() * 1.2f,
                        Resolution.getGameWorldHeightLandscape() - rulesButtonSprite.getHeight() * 2.0f);
            }
        }
        if (rulesTextBoxLandscape != null && rulesTextBoxPortrait != null) {
            rulesTextBoxLandscape.setSize(Resolution.getGameWorldWidthLandscape() * 0.8f, Resolution.getGameWorldHeightLandscape() * 0.8f);
            rulesTextBoxPortrait.setSize(Resolution.getGameWorldWidthPortrait() * 0.8f, Resolution.getGameWorldHeightPortrait() * 0.8f);
        }

        if (Orientation.getOrientation() == Input.Orientation.Portrait) {
            _X = Resolution.getGameWorldWidthPortrait();
            _Y = Resolution.getGameWorldHeightPortrait();
        } else {
            _X = Resolution.getGameWorldWidthLandscape();
            _Y = Resolution.getGameWorldHeightLandscape();
        }

        float FONT_SIZE_ON_SCREEN = 0.04f;
        if (com.gamefactoryx.cheers.tool.Configuration.getLanguage() == com.gamefactoryx.cheers.tool.Configuration.LanguageEnum.SK)
            _generator = new FreeTypeFontGenerator(FontHelper.getSkFontFile());
        else
            _generator = new FreeTypeFontGenerator(FontHelper.getFontFile());
        if (Orientation.getOrientation() == Input.Orientation.Portrait) {
            _FONT_SIZE = (int) (_X * FONT_SIZE_ON_SCREEN);
        } else {
            _FONT_SIZE = (int) (_Y * FONT_SIZE_ON_SCREEN);
        }
        _parameter.size = _FONT_SIZE;
        _parameter.color = new Color(166.0f / 255.0f, 124.0f / 255.0f, 82f / 255.0f, 1f);
        // BitmapFont temp = font;
        _font = _generator.generateFont(_parameter);
        _parameter.color = new Color(0.0f, 0.0f, 0.0f, 1f);

        /*if (temp != null)
            temp.dispose();*/
        _generator.dispose();


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
        if (rulesTextBoxLandscape != null)
            rulesTextBoxLandscape.getTexture().dispose();
        if (rulesTextBoxPortrait != null)
            rulesTextBoxPortrait.getTexture().dispose();
        if(_font != null)
            _font.dispose();
        if (loadingSprite != null)
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
        if (rulesButtonSprite != null && rulesModel.isShowRules()) {


            if (rulesModel != null && rulesModel.isShowRulesText()) {
                if (Orientation.getOrientation() == Input.Orientation.Portrait) {
                    rulesTextBoxPortrait.setPosition(Resolution.getGameWorldWidthPortrait() / 2 - rulesTextBoxPortrait.getWidth() / 2,
                            Resolution.getGameWorldHeightPortrait() / 2 - rulesTextBoxPortrait.getHeight() / 2);
                    rulesTextBoxPortrait.draw(getSpriteBatch());
                } else {
                    rulesTextBoxLandscape.setPosition(Resolution.getGameWorldWidthLandscape() / 2 - rulesTextBoxLandscape.getWidth() / 2,
                            Resolution.getGameWorldHeightLandscape() / 2 - rulesTextBoxLandscape.getHeight() / 2);
                    rulesTextBoxLandscape.draw(getSpriteBatch());
                }
            }
            if(rulesModel.isShowRulesText()) {
                float SPACE_BETWEEN_TWO_LINES_WITHOUT_ENTER = 1.75f;
                float EMPTYCHAR_CHAR_WIDTH_RATIO = 1.7f;
                float y_offset = 0f;
                for (int i = 0; i < splitLine().size(); i++) {


                    _font.draw(getSpriteBatch(), splitLine().get(i),
                            (_X - splitLine().get(i).length() * _font.getSpaceWidth() * EMPTYCHAR_CHAR_WIDTH_RATIO) * 0.5f,
                            (_Y + getYScrollPos()) * 0.85f - _font.getCapHeight() * 1.3f - y_offset);

                    y_offset += _font.getCapHeight() * SPACE_BETWEEN_TWO_LINES_WITHOUT_ENTER;
                }
            }
            rulesButtonSprite.draw(spriteBatch, rulesModel.isShowRulesText() ? 1.0f : 0.5f);
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
        rulesButtonSprite.setSize(Resolution.getGameWorldWidthPortrait() * 0.15f, Resolution.getGameWorldHeightPortrait() * Resolution.getAspectRatio() * 0.15f);
        rulesModel = model;
    }

    public static Camera getCamera() {
        return camera;
    }

    public Model getRulesModel() {
        return rulesModel;
    }

    private List<String> splitLine() {
        List<String> text = new ArrayList<>();
        if (rulesModel.getRulesText() != null) {
            int num_of_chars = (int) (_X * 0.78f / _font.getSpaceWidth() * 0.5f);
            StringBuilder sb = new StringBuilder();
            if (rulesModel.getRulesText().length() > num_of_chars) {

                for (String line : rulesModel.getRulesText().split("\\n")) {
                    for (String word : line.split(" ")) {
                        if (sb.length() == 0) {
                            sb.append(word);
                        } else if (sb.length() + word.length() + 1 < num_of_chars) {
                            sb.append(" ");
                            sb.append(word);
                        } else {
                            text.add(sb.toString());
                            sb.setLength(0);
                            sb.append(word);
                        }
                    }
                    sb.append('\n');
                    text.add(sb.toString());
                    sb.setLength(0);
                }
            } else {
                sb.append(rulesModel.getRulesText());
                sb.append('\n');
                text = Collections.singletonList(sb.toString());
            }

        }
        return text;
    }
}