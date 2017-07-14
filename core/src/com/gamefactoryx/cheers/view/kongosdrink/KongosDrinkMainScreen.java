package com.gamefactoryx.cheers.view.kongosdrink;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gamefactoryx.cheers.model.kongosdrink.KongosDrinkMainModel;
import com.gamefactoryx.cheers.view.AbstractScreen;


/**
 * Created by bernat on 28.04.2017.
 */
@SuppressWarnings("DefaultFileTemplate")
public class KongosDrinkMainScreen extends AbstractScreen {

    /*private final static FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
    private FreeTypeFontGenerator generator;
    private int FONT_SIZE;*/
    private float X, Y;
    /*private BitmapFont font;*/
    private KongosDrinkMainModel dataModel = KongosDrinkMainModel.getInstance();
    /*private Sprite[] medals = new Sprite[3];*/

    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Texture[] texture;
    private Sprite[] sprite;

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

        sprite = new Sprite[texture.length];
        for(int i = 0; i < sprite.length; i++) {
            sprite[i] = new Sprite(texture[i]);
            sprite[i].setOrigin(0, 0);
            sprite[i].setPosition(-sprite[i].getWidth() / 4, -sprite[i].getHeight() / 2);
        }
    }


    @Override
    protected void initSprites() {
    /*    setLandscapeSprite(new Sprite(new Texture("common/kongos_ride/background.jpg")));
        setPortraitSprite(new Sprite(new Texture("common/kongos_ride/background.jpg")));
        getLandscapeSprite().setSize(Resolution.getGameWorldWidthLandscape(), Resolution.getGameWorldHeightLandscape());
        getPortraitSprite().setSize(Resolution.getGameWorldWidthPortrait(), Resolution.getGameWorldHeightPortrait());*/
       /* medals[0] = new Sprite(new Texture("common/HallofFame/1place.png"));
        medals[1] = new Sprite(new Texture("common/HallofFame/2place.png"));
        medals[2] = new Sprite(new Texture("common/HallofFame/3place.png"));*/

    }

    @Override
    public void resize(int width, int height) {
        camera = new OrthographicCamera(960, 540);
       // super.resize(width, height);
        //float FONT_SIZE_ON_SCREEN = 0.05f;
       /* if (Configuration.getLanguage() == Configuration.LanguageEnum.SK)
            generator = new FreeTypeFontGenerator(FontHelper.getSkFontFile());
        else
            generator = new FreeTypeFontGenerator(FontHelper.getFontFile());*/
        //if (Orientation.getOrientation() == Input.Orientation.Portrait) {
            //FONT_SIZE = (int) (Resolution.getGameWorldHeightPortrait() * FONT_SIZE_ON_SCREEN);
          //  X = Resolution.getGameWorldWidthPortrait();
           // Y = Resolution.getGameWorldHeightPortrait();
        //} else {
        //    FONT_SIZE = (int) (Resolution.getGameWorldWidthLandscape() * FONT_SIZE_ON_SCREEN);
         //   X = Resolution.getGameWorldWidthLandscape();
         //   Y = Resolution.getGameWorldHeightLandscape();
       // }
/*        if(Orientation.getOrientation() == Input.Orientation.Portrait)
            getTextBox().setSize(X * 0.85f, Y * 0.065f);
        else
            getTextBox().setSize(X * 0.75f, Y * 0.105f);

        for (int i = 0; i < getCountOfButtons(); i++)
            for (int j = 0; j < 2; j++) {
                getButtons()[i][j].setSize(Resolution.getGameWorldWidthPortrait() * 0.77f,
                        Resolution.getGameWorldHeightPortrait() * 0.17f * Resolution.getAspectRatio());
            }*/
        /*for(Sprite medal: medals)
            if(Orientation.getOrientation() == Input.Orientation.Portrait)
                medal.setSize(X * 0.2f, X * 0.2f);
            else
                medal.setSize(Y * 0.2f, Y * 0.2f);

        parameter.size = FONT_SIZE;
        parameter.color = new Color(166.0f / 255.0f, 124.0f / 255.0f, 82f / 255.0f, 1f);
        BitmapFont temp = font;
        font = generator.generateFont(parameter);
        if (temp != null)
            temp.dispose();
        generator.dispose();*/

    }
    @Override
    public void render(float delta) {
        //Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        sprite[dataModel.getIndex()].setPosition(-sprite[dataModel.getIndex()].getWidth()/4 - dataModel.getXcoor(),-sprite[dataModel.getIndex()].getHeight()/2);

        //batch.draw(textureRegion, -100, -100);
        sprite[dataModel.getIndex()].draw(batch);
        batch.end();
    }

    @Override
    protected void drawText() {
    /*    float DISTANCE_FROM_UP = 0.8f;
        float DISTANCE_FROM_LEFT = Orientation.getOrientation() == Input.Orientation.Portrait ? 0.15f : 0.28f;
        float DISTANCE_FROM_LEFT_FOR_MEDALS = Orientation.getOrientation() == Input.Orientation.Portrait ? 0.2f : 0.5f;

        List<String> scorers = dataModel.get();
        int y_offset = 0;
        int index = 0;
        for (String scorer : scorers) {
            String[] s = scorer.split(":");
            String val = String.format("%3s:    %s", s[0], s[1]);
            float xx = X * DISTANCE_FROM_LEFT;
            float yy = Y * DISTANCE_FROM_UP - font.getCapHeight() * 2.3f * y_offset++;
            getTextBox().setPosition(xx * 0.5f, yy - getTextBox().getHeight()/1.5f);
            getTextBox().draw(getSpriteBatch());
            if(index < 3) {
                medals[index].setPosition(xx * DISTANCE_FROM_LEFT_FOR_MEDALS, yy - getTextBox().getHeight()/1.5f);
                medals[index].draw(getSpriteBatch());
            }
            ++index;
            font.draw(getSpriteBatch(), val, xx, yy);
        }*/
    }


    @Override
    protected void initTextBox() {
        //setTextBox(new Sprite(new Texture(Configuration.getLanguage() + "/Busdrivingscreen/busdriving_phase_1/text_box_horizontal.png")));
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
    public void dispose() {
        batch.dispose();
        for(Texture txt: texture)
            txt.dispose();

        /*for(Sprite medal: medals)
            medal.getTexture().dispose();
*/
        //super.dispose();
    }
}
