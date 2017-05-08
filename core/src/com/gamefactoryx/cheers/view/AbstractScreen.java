package com.gamefactoryx.cheers.view;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.gamefactoryx.cheers.model.Configuration;
import com.gamefactoryx.cheers.tool.Orientation;
import com.gamefactoryx.cheers.tool.Resolution;

/**
 * Created by bernat on 28.04.2017.
 */
@SuppressWarnings("DefaultFileTemplate")
public abstract class AbstractScreen implements Screen {


    final int FREE = 0;
    final int CLICKED = 1;

    private Camera camera;
    private Viewport viewport;

    private Sprite landscapeSprite;
    private Sprite portraitSprite;
    private Batch spriteBatch;
    private boolean[] clicked;
    private Sprite[][] buttons;
    private Sprite textBox;

    private int yScrollPos;

    public Sprite[][] getButtons() {
        return buttons;
    }
    public boolean[] getClicked() {
        return clicked;
    }
    public int getCountOfButtons() {
        return buttons.length;
    }
    public Sprite getTextBox(){ return textBox; }

    Sprite getLandscapeSprite() {
        return landscapeSprite;
    }
    Sprite getPortraitSprite() {
        return portraitSprite;
    }
    Batch getSpriteBatch(){
        return spriteBatch;
    }

    void setClicked(boolean[] clicked) {
        this.clicked = clicked;
    }

    void setButtons(Sprite[][] buttons) {
        this.buttons = buttons;
    }

    void setTextBox(Sprite textBox) {
        this.textBox = textBox;
    }

    protected abstract void initSprites();
    protected abstract void drawText();
    protected abstract void initTextBox();


    public int getYScrollPos() {
        return yScrollPos;
    }

    public void setYScrollPos(int yScrollPos) {
        this.yScrollPos = yScrollPos;
    }

    void drawButtons() {
        for (int i = 0; i < getCountOfButtons(); i++) {
            int click_index = getClicked()[i] ? CLICKED : FREE;
            if (Orientation.getOrientation() == Input.Orientation.Landscape) {
                getButtons()[i][click_index].setPosition(Resolution.getGameWorldWidthLandscape() * 0.01f +  i * getButtons()[i][0].getWidth(),
                        0f);

            } else {
                getButtons()[i][click_index].setPosition(Resolution.getGameWorldWidthPortrait() * 0.01f  + i * getButtons()[i][0].getWidth(),
                        0f);
            }
            getButtons()[i][click_index].draw(getSpriteBatch(), 1);
        }
    }

    void initButtons() {
        setButtons(new Sprite[3][2]);

        getButtons()[0][0] = new Sprite(new Texture("common/button_free_de_lang.png"));
        getButtons()[0][1] = new Sprite(new Texture("common/button_clicked_de_lang.png"));
        getButtons()[1][0] = new Sprite(new Texture("common/button_free_eng_lang.png"));
        getButtons()[1][1] = new Sprite(new Texture("common/button_clicked_eng_lang.png"));
        getButtons()[2][0] = new Sprite(new Texture("common/button_free_back_to_main.png"));
        getButtons()[2][1] = new Sprite(new Texture("common/button_clicked_back_to_main.png"));

        for(int i = 0; i < getCountOfButtons(); i++) {
            getButtons()[i][0].setSize(Resolution.getGameWorldWidthPortrait() * 0.2f,
                    Resolution.getGameWorldHeightPortrait() * 0.2f * Resolution.getAspectRatio());
            getButtons()[i][1].setSize(Resolution.getGameWorldWidthPortrait() * 0.2f,
                    Resolution.getGameWorldHeightPortrait() * 0.2f * Resolution.getAspectRatio());
        }

        setClicked(new boolean[getCountOfButtons()]);
    }

    @Override
    public void show() {
        spriteBatch = new SpriteBatch();
        camera = new OrthographicCamera();
        if (Orientation.getOrientation() == Input.Orientation.Portrait)
            viewport = new FillViewport(Resolution.getGameWorldWidthPortrait() / Resolution.getAspectRatio(),
                    Resolution.getGameWorldHeightPortrait(), camera);
        else
            viewport = new FillViewport(Resolution.getGameWorldWidthLandscape() / Resolution.getAspectRatio(),
                    Resolution.getGameWorldHeightLandscape(), camera);
        viewport.apply();

        initSprites();
        initButtons();
        initTextBox();

    }

    @Override
    public final void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        drawSprite();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
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
    public final void dispose() {
        for( int i = 0; i < getCountOfButtons(); i++ )
            for( int j = 0; j < getButtons()[i].length; j++)
                getButtons()[i][j].getTexture().dispose();

        if(getTextBox() != null )
            getTextBox().getTexture().dispose();

        portraitSprite.getTexture().dispose();
        landscapeSprite.getTexture().dispose();

        spriteBatch.dispose();
    }





    void setLandscapeSprite(Sprite landscapeSprite) {
        this.landscapeSprite = landscapeSprite;
        landscapeSprite.setSize(Resolution.getGameWorldWidthLandscape(), Resolution.getGameWorldHeightLandscape());
    }

    void setPortraitSprite(Sprite portraitSprite) {
        this.portraitSprite = portraitSprite;
        portraitSprite.setSize(Resolution.getGameWorldWidthPortrait(), Resolution.getGameWorldHeightPortrait());
    }


    private void drawSprite() {
        spriteBatch.begin();
        spriteBatch.setProjectionMatrix(camera.combined);
        drawMainSprite();
        drawButtons();
        drawText();
        spriteBatch.end();
    }


    private void setCameraPosition() {
        if (Orientation.getOrientation() == Input.Orientation.Landscape)
            camera.position.set(Resolution.getGameWorldWidthLandscape() / 2 - 1, Resolution.getGameWorldHeightLandscape() / 2, 0);
        else
            camera.position.set(Resolution.getGameWorldWidthPortrait() / 2, Resolution.getGameWorldHeightPortrait() / 2, 0);
        camera.update();

    }

    private void drawMainSprite() {
        if (Orientation.getOrientation() == Input.Orientation.Landscape) {
            landscapeSprite.draw(spriteBatch, 1);
        } else {
            portraitSprite.draw(spriteBatch, 1);
        }
    }

}