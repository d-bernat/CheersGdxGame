package com.gamefactoryx.cheers.view.kongosdrink;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.gamefactoryx.cheers.model.kongosdrink.Card;
import com.gamefactoryx.cheers.model.kongosdrink.KongosDrinkMainModel;
import com.gamefactoryx.cheers.model.kongosdrink.Player;
import com.gamefactoryx.cheers.tool.FontHelper;
import com.gamefactoryx.cheers.tool.Orientation;
import com.gamefactoryx.cheers.tool.Resolution;
import com.gamefactoryx.cheers.tool.kongosdrink.Configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created by bernat on 28.04.2017.
 */
@SuppressWarnings("DefaultFileTemplate")
public class KongosDrinkMainScreen implements Screen/*extends AbstractScreen*/ {

    public KongosDrinkMainScreen() {
        KongosDrinkMainModel.getNewInstance();
    }

    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Texture[] backgroundTexture;
    private Texture[] foregroundTexture;
    private Texture[] modusTexture;
    private Texture[] mainButtonsTexture;
    private Sprite[] sprite;
    private Sprite[] foregroundSprite;
    private Sprite[] playerSprite;
    private CtrlSprite[] modusSprite;
    private CtrlSprite[] mainButtonsSprite;
    private Sprite finishFlag;
    private final static FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
    private FreeTypeFontGenerator generator;
    private int FONT_SIZE;
    private BitmapFont font;
    private BitmapFont nameFont;
    private Sprite textBox;
    private Sprite backButtonSprite;

    public Sprite getTextBox() {
        return textBox;
    }

    public void setTextBox(Sprite textBox) {
        this.textBox = textBox;
    }

    public CtrlSprite[] getMainButtonsSprite() {
        return mainButtonsSprite;
    }

    public Sprite getBackButtonSprite() {
        return backButtonSprite;
    }

    private void initTextBox() {
        setTextBox(new Sprite(new Texture("common/kongos_drink/level/pop_up_txt_box.png")));
        getTextBox().setSize(getTextBox().getWidth() * 0.35f, getTextBox().getHeight() * 0.5f);
    }

    /*private void initFinishFlag() {
        finishFlag = new Sprite(new Texture("common/kongos_drink/finished.png"));
        finishFlag.setSize(finishFlag.getWidth() * 0.3f, finishFlag.getHeight() * 0.3f);
    }*/

    private void initMainButtonSprite() {
        mainButtonsSprite = new CtrlSprite[4];
        for (int i = 0; i < mainButtonsSprite.length; i++) {
            mainButtonsSprite[i] = new CtrlSprite(mainButtonsTexture[i]);
            if (i == 1)
                mainButtonsSprite[i].setSize(mainButtonsTexture[i].getWidth() * 0.3f, mainButtonsTexture[i].getHeight() * 0.3f);
            else
                mainButtonsSprite[i].setSize(mainButtonsTexture[i].getWidth() * 0.25f, mainButtonsTexture[i].getHeight() * 0.25f);
        }
        mainButtonsSprite[0].setActive(true);
        mainButtonsSprite[1].setActive(false);
        mainButtonsSprite[2].setActive(false);
        mainButtonsSprite[3].setActive(false);

    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        setBackgroudTexture();
        setForegroundTexture();
        setModusTexture();
        setMainButtonsTexture();
        sprite = new Sprite[backgroundTexture.length];
        for (int i = 0; i < sprite.length; i++) {
            sprite[i] = new Sprite(backgroundTexture[i]);
            sprite[i].setOrigin(0, 0);
            sprite[i].setPosition(-sprite[i].getWidth() / 4, -sprite[i].getHeight() / 2);
        }

        playerSprite = new Sprite[getEnablePlayersAmount()];
        for (int i = 0; i < playerSprite.length; i++) {
            String avatar = Configuration.getPlayers().get(i).getAvatar().toString();
            playerSprite[i] = new Sprite(
                    new Texture(Gdx.files.internal("common/kongos_drink/player/" + avatar + "/" + avatar + "_1.png")));

            playerSprite[i].setSize(playerSprite[i].getWidth() * 0.5f, playerSprite[i].getHeight() * 0.5f);
            playerSprite[i].setOrigin(playerSprite[i].getWidth() / 2, playerSprite[i].getHeight() / 2);
            playerSprite[i].setPosition(-playerSprite[i].getWidth() / 4, -playerSprite[i].getHeight() / 2 * 3.9f);
        }

        if (foregroundTexture != null) {
            foregroundSprite = new Sprite[foregroundTexture.length];
            for (int i = 0; i < foregroundSprite.length; i++) {
                if (foregroundTexture[i] != null) {
                    foregroundSprite[i] = new Sprite(foregroundTexture[i]);
                    foregroundSprite[i].setOrigin(0, 0);
                    foregroundSprite[i].setPosition(-foregroundSprite[i].getWidth() / 4, -foregroundSprite[i].getHeight() / 2);
                }
            }
        }
        modusSprite = new CtrlSprite[10];
        for (int i = 0; i < 10; i++) {
            modusSprite[i] = new CtrlSprite(modusTexture[i]);
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

        initMainButtonSprite();
        initTextBox();
        //initFinishFlag();
        initMainButtonSprite();
        initBackButton();
    }

    private int getEnablePlayersAmount() {
        int ret = 0;
        for (Player player : Configuration.getPlayers()) {
            if (player.isEnable()) ++ret;
        }

        return ret;
    }

    public CtrlSprite[] getModusSprite() {
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
        // BitmapFont temp = font;
        font = generator.generateFont(parameter);
        parameter.color = new Color(0.0f, 0.0f, 0.0f, 1f);
        nameFont = generator.generateFont(parameter);

        /*if (temp != null)
            temp.dispose();*/
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
                Configuration.getPlayers()[KongosDrinkMainModel.getInstance().getPlayerIndex()].isEnable()
                ));
*/

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        sprite[KongosDrinkMainModel.getInstance().getIndex()].setPosition(-sprite[KongosDrinkMainModel.getInstance().getIndex()].getWidth() / 4 - KongosDrinkMainModel.getInstance().getXcoor(), -sprite[KongosDrinkMainModel.getInstance().getIndex()].getHeight() / 2);
        sprite[KongosDrinkMainModel.getInstance().getIndex()].draw(batch);
        for (int i = playerSprite.length - 1; i >= 0; --i) {
            if (Configuration.getPlayers().get(i).isActive())
                playerSprite[i].setPosition(-playerSprite[i].getWidth() / 4,
                        -playerSprite[i].getHeight() / 2 * 2.9f);
            else
                playerSprite[i].setPosition(-playerSprite[i].getWidth() / 4 + Configuration.getPlayers().get(i).getNormPosition() - KongosDrinkMainModel.getInstance().getXxcoor(),
                        -playerSprite[i].getHeight() / 2 * 2.9f);
            if (Configuration.getPlayers().get(i).getRotate() != 0)
                playerSprite[i].rotate(Configuration.getPlayers().get(i).getRotate());
            /*else
                playerSprite[i].rotate(0.0f - playerSprite[i].getRotation());*/
            playerSprite[i].draw(batch);
        }
        if (Configuration.getPlayers().get(KongosDrinkMainModel.getInstance().getPlayerIndex()).getRotate() == 0) {
            playerSprite[KongosDrinkMainModel.getInstance().getPlayerIndex()].draw(batch);
        }

        /*finishFlag.setPosition(-(finishFlag.getWidth() * 2) / 4 + (Configuration.getGameSize().getValue() - 1) * Configuration.KongosDrink.DISTANCE_BETWEEN_TWO_FIELDS - KongosDrinkMainModel.getInstance().getXxcoor(),
                -finishFlag.getHeight() / 2 * 1.9f);
        finishFlag.draw(batch);*/


        if (foregroundSprite != null && foregroundSprite[KongosDrinkMainModel.getInstance().getIndex()] != null) {
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


        for (CtrlSprite ms : modusSprite) {
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

        if (!KongosDrinkMainModel.getInstance().isTextBoxDisplayed() && !KongosDrinkMainModel.getInstance().isAnimationRunning()) {
            Card card = KongosDrinkMainModel.getInstance().getActiveCard();
            if (card != null) {
                getTextBox().setPosition(-440, -200);
                getTextBox().draw(batch);
                font.draw(batch, String.format("%s (%s)",
                        Configuration.getPlayers().get(KongosDrinkMainModel.getInstance().getPlayerIndex()).getName(),
                        card.getPoint()), -410, 150);

                float SPACE_BETWEEN_TWO_LINES_WITHOUT_ENTER = 1.75f;
                float SPACE_BETWEEN_TWO_LINES_WITH_ENTER = 3.5f;
                float y_offset = font.getCapHeight() * SPACE_BETWEEN_TWO_LINES_WITH_ENTER;
                List<String> text = splitLine(card.getText());
                for (String line : text) {
                    font.draw(batch, line, -410, 150 - y_offset);
                    if (y_offset == 0)
                        y_offset += font.getCapHeight() * SPACE_BETWEEN_TWO_LINES_WITH_ENTER;
                    else
                        y_offset += font.getCapHeight() * SPACE_BETWEEN_TWO_LINES_WITHOUT_ENTER;
                }

            }
        }

        if (!KongosDrinkMainModel.getInstance().isTextBoxDisplayed() && !KongosDrinkMainModel.getInstance().isAnimationRunning()) {
            for (int ii = 1; ii < mainButtonsSprite.length; ii++) {
                if (mainButtonsSprite[ii].isActive()) {
                    if (ii == 1) {
                        mainButtonsSprite[ii].setPosition(-mainButtonsSprite[ii].getWidth(), -mainButtonsSprite[ii].getHeight() / 4);
                        mainButtonsSprite[ii].draw(batch);
                        nameFont.draw(batch, Configuration.getPlayers().get(KongosDrinkMainModel.getInstance().getPlayerIndex()).getName(),
                                mainButtonsSprite[ii].getX() * 0.9f,
                                mainButtonsSprite[ii].getY() + mainButtonsSprite[ii].getHeight() * 0.9f);
                    } else {
                        mainButtonsSprite[ii].setPosition(60 + 120 * (ii - 2), -80);
                        mainButtonsSprite[ii].draw(batch);
                    }

                }
            }
        }
        if (backButtonSprite != null) {
            backButtonSprite.draw(batch);
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

        if(foregroundTexture != null) {
            for (Texture txt : foregroundTexture)
                if (txt != null)
                    txt.dispose();
        }

        for (Texture txt : modusTexture)
            if (txt != null)
                txt.dispose();

        for (Texture txt : mainButtonsTexture)
            if (txt != null)
                txt.dispose();

        if (font != null)
            font.dispose();

        if (nameFont != null)
            nameFont.dispose();

        if (getTextBox() != null)
            getTextBox().getTexture().dispose();
        if (finishFlag != null)
            finishFlag.getTexture().dispose();

        if (backButtonSprite != null)
            backButtonSprite.getTexture().dispose();
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
                        new Texture(Gdx.files.internal("common/kongos_drink/game_design/50/50.9.jpg")),
                        new Texture(Gdx.files.internal("common/kongos_drink/game_design/50/50.10.jpg"))};
                break;
            case FORTY:
                backgroundTexture = new Texture[]{new Texture(Gdx.files.internal("common/kongos_drink/game_design/40/40.1.jpg")),
                        new Texture(Gdx.files.internal("common/kongos_drink/game_design/40/40.2.jpg")),
                        new Texture(Gdx.files.internal("common/kongos_drink/game_design/40/40.3.jpg")),
                        new Texture(Gdx.files.internal("common/kongos_drink/game_design/40/40.4.jpg")),
                        new Texture(Gdx.files.internal("common/kongos_drink/game_design/40/40.5.jpg")),
                        new Texture(Gdx.files.internal("common/kongos_drink/game_design/40/40.6.jpg")),
                        new Texture(Gdx.files.internal("common/kongos_drink/game_design/40/40.7.jpg")),
                        new Texture(Gdx.files.internal("common/kongos_drink/game_design/40/40.8.jpg"))};
                break;
            case THRITY:
                backgroundTexture = new Texture[]{new Texture(Gdx.files.internal("common/kongos_drink/game_design/30/30.1.jpg")),
                        new Texture(Gdx.files.internal("common/kongos_drink/game_design/30/30.2.jpg")),
                        new Texture(Gdx.files.internal("common/kongos_drink/game_design/30/30.3.jpg")),
                        new Texture(Gdx.files.internal("common/kongos_drink/game_design/30/30.4.jpg")),
                        new Texture(Gdx.files.internal("common/kongos_drink/game_design/30/30.5.jpg")),
                        new Texture(Gdx.files.internal("common/kongos_drink/game_design/30/30.6.jpg"))};
                break;
            case TWENTY:
                backgroundTexture = new Texture[]{new Texture(Gdx.files.internal("common/kongos_drink/game_design/20/20.1.jpg")),
                        new Texture(Gdx.files.internal("common/kongos_drink/game_design/20/20.2.jpg")),
                        new Texture(Gdx.files.internal("common/kongos_drink/game_design/20/20.3.jpg")),
                        new Texture(Gdx.files.internal("common/kongos_drink/game_design/20/20.4.jpg"))};
                break;
            case FIFTEEN:
                backgroundTexture = new Texture[]{new Texture(Gdx.files.internal("common/kongos_drink/game_design/15/15.1.jpg")),
                        new Texture(Gdx.files.internal("common/kongos_drink/game_design/15/15.2.jpg")),
                        new Texture(Gdx.files.internal("common/kongos_drink/game_design/15/15.3.jpg"))};
                break;
            case TEN:
                backgroundTexture = new Texture[]{new Texture(Gdx.files.internal("common/kongos_drink/game_design/10/10.1.jpg")),
                        new Texture(Gdx.files.internal("common/kongos_drink/game_design/10/10.2.jpg"))};
                break;

        }
        KongosDrinkMainModel.getInstance().setCountOfTextures(backgroundTexture.length);

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
                        null,
                        null};
            case FORTY:
            case THRITY:
            case TWENTY:
            case TEN:
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

    private void setMainButtonsTexture() {
        mainButtonsTexture = new Texture[]{
                new Texture(Gdx.files.internal("common/kongos_drink/other_team.png")),
                new Texture(Gdx.files.internal("common/kongos_drink/level/levelbox_preview.png")),
                new Texture(Gdx.files.internal("common/kongos_drink/drink.png")),
                new Texture(Gdx.files.internal("common/kongos_drink/continue.png"))
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

    private void initBackButton() {
        backButtonSprite = new Sprite(new Texture("common/return.png"));
        backButtonSprite.setSize(Resolution.getGameWorldWidthPortrait() * 0.03f, Resolution.getGameWorldHeightPortrait() * Resolution.getAspectRatio() * 0.03f);
        backButtonSprite.setPosition(-960 / 2, -540 / 2);

    }

}