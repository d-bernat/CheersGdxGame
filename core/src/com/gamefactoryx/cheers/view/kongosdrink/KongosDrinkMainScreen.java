package com.gamefactoryx.cheers.view.kongosdrink;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gamefactoryx.cheers.model.kongosdrink.KongosDrinkMainModel;
import com.gamefactoryx.cheers.model.kongosdrink.PlayerModel;
import com.gamefactoryx.cheers.view.AbstractScreen;


/**
 * Created by bernat on 28.04.2017.
 */
@SuppressWarnings("DefaultFileTemplate")
public class KongosDrinkMainScreen extends AbstractScreen {

    private KongosDrinkMainModel dataModel = KongosDrinkMainModel.getInstance();
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Texture[] texture;
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
        texture = new Texture[]{ new Texture(Gdx.files.internal("common/kongos_drink/game_design/50/50.1.jpg")),
                    new Texture(Gdx.files.internal("common/kongos_drink/game_design/50/50.2.jpg")),
                    new Texture(Gdx.files.internal("common/kongos_drink/game_design/50/50.3.jpg")),
                    new Texture(Gdx.files.internal("common/kongos_drink/game_design/50/50.4.jpg")),
                    new Texture(Gdx.files.internal("common/kongos_drink/game_design/50/50.5.jpg")),
                    new Texture(Gdx.files.internal("common/kongos_drink/game_design/50/50.6.jpg")),
                    new Texture(Gdx.files.internal("common/kongos_drink/game_design/50/50.7.jpg")),
                    new Texture(Gdx.files.internal("common/kongos_drink/game_design/50/50.8.jpg")),
                    new Texture(Gdx.files.internal("common/kongos_drink/game_design/50/50.9.jpg"))};

        foregroundTexture = new Texture[]{ null,
                null,
                new Texture(Gdx.files.internal("common/kongos_drink/game_design/50/50.3.1.jpg")),
                new Texture(Gdx.files.internal("common/kongos_drink/game_design/50/50.4.1.png")),
                new Texture(Gdx.files.internal("common/kongos_drink/game_design/50/50.5.1.png")),
                null,
                null,
                null,
                null};



        sprite = new Sprite[texture.length];
        for(int i = 0; i < sprite.length; i++) {
            sprite[i] = new Sprite(texture[i]);
            sprite[i].setOrigin(0, 0);
            sprite[i].setPosition(-sprite[i].getWidth() / 4, -sprite[i].getHeight() / 2);
        }

        playerSprite = new Sprite[KongosDrinkMainModel.getInstance().getPlayers().length];
        for(int i = 0; i < playerSprite.length; i++){
            playerSprite[i] = new Sprite(KongosDrinkMainModel.getInstance().getPlayers()[i].getAvatar());
            playerSprite[i].setSize(playerSprite[i].getWidth() * 0.5f, playerSprite[i].getHeight() * 0.5f);
            playerSprite[i].setOrigin(playerSprite[i].getWidth() / 2, playerSprite[i].getHeight() / 2);
            playerSprite[i].setPosition(-playerSprite[i].getWidth() / 4, -playerSprite[i].getHeight() / 2 * 3.9f);
        }

        foregroundSprite = new Sprite[foregroundTexture.length];
        for(int i = 0; i < sprite.length; i++) {
            if(foregroundTexture[i] != null) {
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
        sprite[dataModel.getIndex()].setPosition(-sprite[dataModel.getIndex()].getWidth()/4 - dataModel.getXcoor(),-sprite[dataModel.getIndex()].getHeight()/2);
        sprite[dataModel.getIndex()].draw(batch);
        for(int i =0; i < playerSprite.length; ++i) {
            if(KongosDrinkMainModel.getInstance().getPlayers()[i].isActive())
                playerSprite[i].setPosition(-playerSprite[i].getWidth() / 4,
                        -playerSprite[i].getHeight() / 2 * 2.9f);
            else
                playerSprite[i].setPosition(-playerSprite[i].getWidth() / 4 + KongosDrinkMainModel.getInstance().getPlayers()[i].getNormPosition() - dataModel.getXxcoor(),
                    -playerSprite[i].getHeight() / 2 * 2.9f);
            if (dataModel.getPlayers()[i].getRotate() != 0)
                playerSprite[i].rotate(dataModel.getPlayers()[i].getRotate());
            else
                playerSprite[i].rotate(0.0f - playerSprite[i].getRotation());
            playerSprite[i].draw(batch);
        }
        if(foregroundSprite[dataModel.getIndex()] != null){
            foregroundSprite[dataModel.getIndex()].setPosition(-foregroundSprite[dataModel.getIndex()].getWidth() / 4 - dataModel.getXcoor(), -foregroundSprite[dataModel.getIndex()].getHeight() / 2);
            foregroundSprite[dataModel.getIndex()].draw(batch);
        }
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        for(Texture txt: texture)
            txt.dispose();

        for(PlayerModel player: KongosDrinkMainModel.getInstance().getPlayers())
            player.getAvatar().dispose();

        for(Texture ftxt: foregroundTexture)
            if(ftxt != null)
                ftxt.dispose();

        super.dispose();
    }
}
