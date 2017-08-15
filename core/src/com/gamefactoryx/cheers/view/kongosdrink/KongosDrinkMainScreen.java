package com.gamefactoryx.cheers.view.kongosdrink;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gamefactoryx.cheers.model.kongosdrink.KongosDrinkMainModel;
import com.gamefactoryx.cheers.tool.Orientation;
import com.gamefactoryx.cheers.tool.Resolution;
import com.gamefactoryx.cheers.tool.kongosdrink.Configuration;
import com.gamefactoryx.cheers.view.AbstractScreen;

import java.util.ArrayList;
import java.util.List;


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
    private Texture[] modusTexture;
    private Sprite[] sprite;
    private Sprite[] foregroundSprite;
    private Sprite[] playerSprite;
    private Sprite[] modusSprite;



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
        float X = Orientation.getOrientation() == Input.Orientation.Landscape ? Resolution.getGameWorldWidthLandscape() : Resolution.getGameWorldWidthPortrait();
        float Y = Orientation.getOrientation() == Input.Orientation.Landscape ? Resolution.getGameWorldHeightLandscape() : Resolution.getGameWorldHeightPortrait();
        float DISTANCE_FROM_UPPER_SCREEN_BOUNDARY = Orientation.getOrientation() == Input.Orientation.Landscape ? 0.45f : 0.3f;
        float DISTANCE_FROM_RIGHT_SCREEN_BOUNDARY = Orientation.getOrientation() == Input.Orientation.Landscape ? 0.03f : 0.06f;

        batch = new SpriteBatch();
        setBackgroudTexture();
        setForegroundTexture();
        setModusTexture();
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
        modusSprite = new Sprite[10];
        for(int i = 0; i < 10; i++){
            modusSprite[i] = new Sprite(modusTexture[i]);
            modusSprite[i].setSize(modusSprite[i].getWidth() * 0.25f, modusSprite[i].getHeight() * 0.25f);
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

        List<Integer> indexes = new ArrayList<>();
        if((KongosDrinkMainModel.getInstance().getModus() & KongosDrinkMainModel.CAP) == KongosDrinkMainModel.CAP)
            indexes.add(0);
        if((KongosDrinkMainModel.getInstance().getModus() & KongosDrinkMainModel.CHANGES) == KongosDrinkMainModel.CHANGES)
            indexes.add(1);
        if((KongosDrinkMainModel.getInstance().getModus() & KongosDrinkMainModel.DRINK_TOGETHER) == KongosDrinkMainModel.DRINK_TOGETHER)
            indexes.add(2);
        if((KongosDrinkMainModel.getInstance().getModus() & KongosDrinkMainModel.MIRROR) == KongosDrinkMainModel.MIRROR)
            indexes.add(3);
        if((KongosDrinkMainModel.getInstance().getModus() & KongosDrinkMainModel.NO_TALKING) == KongosDrinkMainModel.NO_TALKING)
            indexes.add(4);
        if((KongosDrinkMainModel.getInstance().getModus() & KongosDrinkMainModel.PIG) == KongosDrinkMainModel.PIG)
            indexes.add(5);
        if((KongosDrinkMainModel.getInstance().getModus() & KongosDrinkMainModel.RULES) == KongosDrinkMainModel.RULES)
            indexes.add(6);
        if((KongosDrinkMainModel.getInstance().getModus() & KongosDrinkMainModel.STAR) == KongosDrinkMainModel.STAR)
            indexes.add(7);
        if((KongosDrinkMainModel.getInstance().getModus() & KongosDrinkMainModel.WC) == KongosDrinkMainModel.WC)
            indexes.add(8);
        if((KongosDrinkMainModel.getInstance().getModus() & KongosDrinkMainModel.YODA) == KongosDrinkMainModel.YODA)
            indexes.add(9);

        int i = 0;
        for(int index: indexes) {
            modusSprite[index].setPosition(300 - 120 * i++, 120);
            modusSprite[index].draw(batch);
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

        for (Texture txt : foregroundTexture)
            if (txt != null)
                txt.dispose();

        for (Texture txt : modusTexture)
            if (txt != null)
                txt.dispose();

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

    private void setModusTexture(){
     modusTexture = new Texture[]{
             new Texture(Gdx.files.internal("common/kongos_drink/cardsigns/cap.png")),
             new Texture(Gdx.files.internal("common/kongos_drink/cardsigns/changes.png")),
             new Texture(Gdx.files.internal("common/kongos_drink/cardsigns/drink_together.png")),
             new Texture(Gdx.files.internal("common/kongos_drink/cardsigns/mirror.png")),
             new Texture(Gdx.files.internal("common/kongos_drink/cardsigns/notalking.png")),
             new Texture(Gdx.files.internal("common/kongos_drink/cardsigns/pig.png")),
             new Texture(Gdx.files.internal("common/kongos_drink/cardsigns/rules.png")),
             new Texture(Gdx.files.internal("common/kongos_drink/cardsigns/star.png")),
             new Texture(Gdx.files.internal("common/kongos_drink/cardsigns/WC.png")),
             new Texture(Gdx.files.internal("common/kongos_drink/cardsigns/yoda.png"))
     };
    }

}