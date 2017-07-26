package com.gamefactoryx.cheers.view.kongosdrink;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gamefactoryx.cheers.model.kongosdrink.KongosDrinkMainModel;
import com.gamefactoryx.cheers.tool.kongosdrink.Configuration;
import com.gamefactoryx.cheers.view.AbstractScreen;


/**
 * Created by bernat on 28.04.2017.
 */
@SuppressWarnings("DefaultFileTemplate")
public class KongosDrinkMainScreen extends AbstractScreen {

    public KongosDrinkMainScreen(){
        KongosDrinkMainModel.getNewInstance();
    }

    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Texture[] backgroundTexture;
    private Texture[] foregroundTexture;
    private Sprite[] sprite;
    private Sprite[] foregroundSprite;
    private Sprite[] playerSprite;


    @Override
    protected void initSprites() {

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

    }

    @Override
    protected void drawCards() {

    }

    @Override
    public void show() {
        // camera = new OrthographicCamera(960, 540);

        batch = new SpriteBatch();
        setBackgroudTexture();
        setForegroundTexture();
        sprite = new Sprite[backgroundTexture.length];
        for (int i = 0; i < sprite.length; i++) {
            sprite[i] = new Sprite(backgroundTexture[i]);
            sprite[i].setOrigin(0, 0);
            sprite[i].setPosition(-sprite[i].getWidth() / 4, -sprite[i].getHeight() / 2);
        }

        playerSprite = new Sprite[KongosDrinkMainModel.getInstance().getPlayers().length];
        for (int i = 0; i < playerSprite.length; i++) {
            String avatar = KongosDrinkMainModel.getInstance().getPlayers()[i].getAvatar().toString();
            playerSprite[i] = new Sprite(
                    new Texture(Gdx.files.internal("common/kongos_drink/player/" + avatar + "/" + avatar + "_1.png")));

            playerSprite[i].setSize(playerSprite[i].getWidth() * 0.5f, playerSprite[i].getHeight() * 0.5f);
            playerSprite[i].setOrigin(playerSprite[i].getWidth() / 2, playerSprite[i].getHeight() / 2);
            playerSprite[i].setPosition(-playerSprite[i].getWidth() / 4, -playerSprite[i].getHeight() / 2 * 3.9f);
        }

        foregroundSprite = new Sprite[foregroundTexture.length];
        for (int i = 0; i < sprite.length; i++) {
            if (foregroundTexture[i] != null) {
                foregroundSprite[i] = new Sprite(foregroundTexture[i]);
                foregroundSprite[i].setOrigin(0, 0);
                foregroundSprite[i].setPosition(-foregroundSprite[i].getWidth() / 4, -foregroundSprite[i].getHeight() / 2);
            }
        }


    }

    @Override
    public void resize(int width, int height) {
        camera = new OrthographicCamera(960, 540);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        sprite[KongosDrinkMainModel.getInstance().getIndex()].setPosition(-sprite[KongosDrinkMainModel.getInstance().getIndex()].getWidth() / 4 - KongosDrinkMainModel.getInstance().getXcoor(), -sprite[KongosDrinkMainModel.getInstance().getIndex()].getHeight() / 2);
        sprite[KongosDrinkMainModel.getInstance().getIndex()].draw(batch);
        for (int i = 0; i < playerSprite.length; ++i) {
            if (KongosDrinkMainModel.getInstance().getPlayers()[i].isActive())
                playerSprite[i].setPosition(-playerSprite[i].getWidth() / 4,
                        -playerSprite[i].getHeight() / 2 * 2.9f);
            else
                playerSprite[i].setPosition(-playerSprite[i].getWidth() / 4 + KongosDrinkMainModel.getInstance().getPlayers()[i].getNormPosition() - KongosDrinkMainModel.getInstance().getXxcoor(),
                        -playerSprite[i].getHeight() / 2 * 2.9f);
            if (KongosDrinkMainModel.getInstance().getPlayers()[i].getRotate() != 0)
                playerSprite[i].rotate(KongosDrinkMainModel.getInstance().getPlayers()[i].getRotate());
            else
                playerSprite[i].rotate(0.0f - playerSprite[i].getRotation());
            playerSprite[i].draw(batch);
        }
        if (foregroundSprite[KongosDrinkMainModel.getInstance().getIndex()] != null) {
            foregroundSprite[KongosDrinkMainModel.getInstance().getIndex()].setPosition(-foregroundSprite[KongosDrinkMainModel.getInstance().getIndex()].getWidth() / 4 - KongosDrinkMainModel.getInstance().getXcoor(), -foregroundSprite[KongosDrinkMainModel.getInstance().getIndex()].getHeight() / 2);
            foregroundSprite[KongosDrinkMainModel.getInstance().getIndex()].draw(batch);
        }
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        for (Texture txt : backgroundTexture)
            txt.dispose();

        for (Sprite sprite : playerSprite)
            sprite.getTexture().dispose();

        for (Texture ftxt : foregroundTexture)
            if (ftxt != null)
                ftxt.dispose();

        super.dispose();
    }

    private void setBackgroudTexture() {
        switch (Configuration.getGameSize()) {
            case FIFTY:
                backgroundTexture = new Texture[]{new Texture(Gdx.files.internal("common/kongos_drink/game_design/50/50.1.jpg")),
                        new Texture(Gdx.files.internal("common/kongos_drink/game_design/50/50.2.jpg")),
                        new Texture(Gdx.files.internal("common/kongos_drink/game_design/50/50.3.jpg")),
                        new Texture(Gdx.files.internal("common/kongos_drink/game_design/50/50.4.jpg")),
                        new Texture(Gdx.files.internal("common/kongos_drink/game_design/50/50.5.jpg")),
                        new Texture(Gdx.files.internal("common/kongos_drink/game_design/50/50.6.jpg")),
                        new Texture(Gdx.files.internal("common/kongos_drink/game_design/50/50.7.jpg")),
                        new Texture(Gdx.files.internal("common/kongos_drink/game_design/50/50.8.jpg")),
                        new Texture(Gdx.files.internal("common/kongos_drink/game_design/50/50.9.jpg"))};
                break;
            case FORTY:
            case THRITY:
                break;
        }

    }

    private void setForegroundTexture() {
        switch (Configuration.getGameSize()) {
            case FIFTY:
                foregroundTexture = new Texture[]{null,
                        null,
                        new Texture(Gdx.files.internal("common/kongos_drink/game_design/50/50.3.1.jpg")),
                        new Texture(Gdx.files.internal("common/kongos_drink/game_design/50/50.4.1.png")),
                        new Texture(Gdx.files.internal("common/kongos_drink/game_design/50/50.5.1.png")),
                        null,
                        null,
                        null,
                        null};
            case FORTY:
            case THRITY:
                break;
        }
    }
}