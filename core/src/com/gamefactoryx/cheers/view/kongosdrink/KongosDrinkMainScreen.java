package com.gamefactoryx.cheers.view.kongosdrink;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.gamefactoryx.cheers.model.kongosdrink.KongosDrinkMainModel;
import com.gamefactoryx.cheers.tool.FontHelper;
import com.gamefactoryx.cheers.tool.Orientation;
import com.gamefactoryx.cheers.tool.Resolution;
import com.gamefactoryx.cheers.tool.kongosdrink.CardTextParser;
import com.gamefactoryx.cheers.tool.kongosdrink.Configuration;
import com.gamefactoryx.cheers.view.AbstractScreen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created by bernat on 28.04.2017.
 */
@SuppressWarnings("DefaultFileTemplate")
public class KongosDrinkMainScreen  implements Screen/*extends AbstractScreen*/ {

    public KongosDrinkMainScreen() {
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
    private ModusSprite[] modusSprite;
    private final static FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
    private FreeTypeFontGenerator generator;
    private int FONT_SIZE;
    private BitmapFont font;
    private Sprite textBox;

    public Sprite getTextBox() {
        return textBox;
    }
    public void setTextBox(Sprite textBox) {
        this.textBox = textBox;
    }

    protected void initTextBox() {
        setTextBox(new Sprite(new Texture("common/kongos_drink/level/pop_up_txt_box.png")));
        getTextBox().setSize(getTextBox().getWidth() * 0.35f, getTextBox().getHeight() * 0.5f);
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

        playerSprite = new Sprite[Configuration.getPlayers().length];
        for (int i = 0; i < playerSprite.length; i++) {
            String avatar = Configuration.getPlayers()[i].getAvatar().toString();
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
        modusSprite = new ModusSprite[10];
        for (int i = 0; i < 10; i++) {
            modusSprite[i] = new ModusSprite(modusTexture[i]);
            modusSprite[i].setSize(modusSprite[i].getWidth() * 0.25f, modusSprite[i].getHeight() * 0.25f);
            switch (i) {
                case 0:
                    modusSprite[i].setType(KongosDrinkMainModel.CAP);
                    break;
                case 1:
                    modusSprite[i].setType(KongosDrinkMainModel.CHANGES);
                    break;
                case 2:
                    modusSprite[i].setType(KongosDrinkMainModel.DRINK_TOGETHER);
                    break;
                case 3:
                    modusSprite[i].setType(KongosDrinkMainModel.MIRROR);
                    break;
                case 4:
                    modusSprite[i].setType(KongosDrinkMainModel.NO_TALKING);
                    break;
                case 5:
                    modusSprite[i].setType(KongosDrinkMainModel.PIG);
                    break;
                case 6:
                    modusSprite[i].setType(KongosDrinkMainModel.RULES);
                    break;
                case 7:
                    modusSprite[i].setType(KongosDrinkMainModel.STAR);
                    break;
                case 8:
                    modusSprite[i].setType(KongosDrinkMainModel.WC);
                    break;
                case 9:
                    modusSprite[i].setType(KongosDrinkMainModel.YODA);
                    break;
            }
        }
        initTextBox();
    }

    public ModusSprite[] getModusSprite() {
        return modusSprite;
    }

    @Override
    public void resize(int width, int height) {
        camera = new OrthographicCamera(960, 540);
        //camera = new OrthographicCamera(Resolution.getGameWorldWidthLandscape(), Resolution.getGameWorldHeightLandscape());

        float FONT_SIZE_ON_SCREEN = 0.03f;
        if (com.gamefactoryx.cheers.tool.Configuration.getLanguage() == com.gamefactoryx.cheers.tool.Configuration.LanguageEnum.SK)
            generator = new FreeTypeFontGenerator(FontHelper.getSkFontFile());
        else
            generator = new FreeTypeFontGenerator(FontHelper.getFontFile());
        if (Orientation.getOrientation() == Input.Orientation.Portrait) {
            FONT_SIZE = (int) (540 * FONT_SIZE_ON_SCREEN);
        } else {
            FONT_SIZE = (int) (960 * FONT_SIZE_ON_SCREEN);
        }

        parameter.size = FONT_SIZE;
        parameter.color = new Color(166.0f / 255.0f, 124.0f / 255.0f, 82f / 255.0f, 1f);
        BitmapFont temp = font;
        font = generator.generateFont(parameter);
        if (temp != null)
            temp.dispose();
        generator.dispose();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void render(float delta) {
        /*Gdx.app.log("Status", String.format("PlayerIndex:%s, PlayerPosition:%s, Active:%s",
                KongosDrinkMainModel.getInstance().getPlayerIndex(),
                Configuration.getPlayers()[KongosDrinkMainModel.getInstance().getPlayerIndex()].getPosition(),
                Configuration.getPlayers()[KongosDrinkMainModel.getInstance().getPlayerIndex()].isActive()
                ));
*/

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        sprite[KongosDrinkMainModel.getInstance().getIndex()].setPosition(-sprite[KongosDrinkMainModel.getInstance().getIndex()].getWidth() / 4 - KongosDrinkMainModel.getInstance().getXcoor(), -sprite[KongosDrinkMainModel.getInstance().getIndex()].getHeight() / 2);
        sprite[KongosDrinkMainModel.getInstance().getIndex()].draw(batch);
        for (int i = playerSprite.length - 1; i >= 0; --i) {
            if (Configuration.getPlayers()[i].isActive())
                playerSprite[i].setPosition(-playerSprite[i].getWidth() / 4,
                        -playerSprite[i].getHeight() / 2 * 2.9f);
            else
                playerSprite[i].setPosition(-playerSprite[i].getWidth() / 4 + Configuration.getPlayers()[i].getNormPosition() - KongosDrinkMainModel.getInstance().getXxcoor(),
                        -playerSprite[i].getHeight() / 2 * 2.9f);
            if (Configuration.getPlayers()[i].getRotate() != 0)
                playerSprite[i].rotate(Configuration.getPlayers()[i].getRotate());
            /*else
                playerSprite[i].rotate(0.0f - playerSprite[i].getRotation());*/
            playerSprite[i].draw(batch);
        }
        if (Configuration.getPlayers()[KongosDrinkMainModel.getInstance().getPlayerIndex()].getRotate() == 0)
            playerSprite[KongosDrinkMainModel.getInstance().getPlayerIndex()].draw(batch);

        if (foregroundSprite[KongosDrinkMainModel.getInstance().getIndex()] != null) {
            foregroundSprite[KongosDrinkMainModel.getInstance().getIndex()].setPosition(-foregroundSprite[KongosDrinkMainModel.getInstance().getIndex()].getWidth() / 4 - KongosDrinkMainModel.getInstance().getXcoor(), -foregroundSprite[KongosDrinkMainModel.getInstance().getIndex()].getHeight() / 2);
            foregroundSprite[KongosDrinkMainModel.getInstance().getIndex()].draw(batch);
        }


        modusSprite[0].setActive((KongosDrinkMainModel.getInstance().getModus() & KongosDrinkMainModel.CAP) == KongosDrinkMainModel.CAP);
        modusSprite[1].setActive((KongosDrinkMainModel.getInstance().getModus() & KongosDrinkMainModel.CHANGES) == KongosDrinkMainModel.CHANGES);
        modusSprite[2].setActive((KongosDrinkMainModel.getInstance().getModus() & KongosDrinkMainModel.DRINK_TOGETHER) == KongosDrinkMainModel.DRINK_TOGETHER);
        modusSprite[3].setActive((KongosDrinkMainModel.getInstance().getModus() & KongosDrinkMainModel.MIRROR) == KongosDrinkMainModel.MIRROR);
        modusSprite[4].setActive((KongosDrinkMainModel.getInstance().getModus() & KongosDrinkMainModel.NO_TALKING) == KongosDrinkMainModel.NO_TALKING);
        modusSprite[5].setActive((KongosDrinkMainModel.getInstance().getModus() & KongosDrinkMainModel.PIG) == KongosDrinkMainModel.PIG);
        modusSprite[6].setActive((KongosDrinkMainModel.getInstance().getModus() & KongosDrinkMainModel.RULES) == KongosDrinkMainModel.RULES);
        modusSprite[7].setActive((KongosDrinkMainModel.getInstance().getModus() & KongosDrinkMainModel.STAR) == KongosDrinkMainModel.STAR);
        modusSprite[8].setActive((KongosDrinkMainModel.getInstance().getModus() & KongosDrinkMainModel.WC) == KongosDrinkMainModel.WC);
        modusSprite[9].setActive((KongosDrinkMainModel.getInstance().getModus() & KongosDrinkMainModel.YODA) == KongosDrinkMainModel.YODA);

        int i = 0;
        for (ModusSprite ms : modusSprite) {
            if (ms.isActive()) {
                ms.setPosition(300 - 120 * i++, 120);
                ms.draw(batch);
                if (ms.isClicked()) {
                    getTextBox().setPosition(-440, -200);
                    getTextBox().draw(batch);
                    List<String> text = splitLine(KongosDrinkMainModel.getInstance().getModusTypeTextMap().get(ms.getType()));
                    float y_offset = 0f;
                    float SPACE_BETWEEN_TWO_LINES_WITHOUT_ENTER = 1.75f;
                    float SPACE_BETWEEN_TWO_LINES_WITH_ENTER = 3.5f;
                    for (String line : text) {
                        font.draw(batch, line, -410, 150 - y_offset);
                        if (y_offset == 0)
                            y_offset += font.getCapHeight() * SPACE_BETWEEN_TWO_LINES_WITH_ENTER;
                        else
                            y_offset += font.getCapHeight() * SPACE_BETWEEN_TWO_LINES_WITHOUT_ENTER;
                    }
                }
            }
        }
        batch.end();
    }

   // @Override
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
        if (font != null)
            font.dispose();

        if (getTextBox() != null)
            getTextBox().getTexture().dispose();

        //super.dispose();
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

    private void setModusTexture() {
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

    private List<String> splitLine(String plainText) {
        List<String> text = new ArrayList<>();
        int num_of_chars = (int) (getTextBox().getWidth() / font.getSpaceWidth() * 0.5f);
        StringBuilder sb = new StringBuilder();
        if (plainText.length() > num_of_chars) {

            for (String line : plainText.split("\\n")) {
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
            sb.append(plainText);
            sb.append('\n');
            text = Collections.singletonList(sb.toString());
        }


        return text;
    }
}