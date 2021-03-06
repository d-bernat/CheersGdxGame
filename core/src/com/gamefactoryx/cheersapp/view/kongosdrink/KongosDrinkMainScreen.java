package com.gamefactoryx.cheersapp.view.kongosdrink;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.gamefactoryx.cheersapp.tool.kongosdrink.Configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created by bernat on 28.04.2017.
 */
@SuppressWarnings("DefaultFileTemplate")
public class KongosDrinkMainScreen implements Screen/*extends AbstractScreen*/ {

    public KongosDrinkMainScreen() {
        com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getNewInstance();
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
    private Sprite celebrationSprite;
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
            String avatar = Configuration.getInstance().getPlayers().get(i).getAvatar().toString();
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
                    modusSprite[i].setType(com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.CAP);
                    break;
                case 1:
                    modusSprite[i].setType(com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.CHANGES);
                    break;
                case 2:
                    modusSprite[i].setType(com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.DRINK_TOGETHER);
                    break;
                case 3:
                    modusSprite[i].setType(com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.MIRROR);
                    break;
                case 4:
                    modusSprite[i].setType(com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.NO_TALKING);
                    break;
                case 5:
                    modusSprite[i].setType(com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.PIG);
                    break;
                case 6:
                    modusSprite[i].setType(com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.RULES);
                    break;
                case 7:
                    modusSprite[i].setType(com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.STAR);
                    break;
                case 8:
                    modusSprite[i].setType(com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.WC);
                    break;
                case 9:
                    modusSprite[i].setType(com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.YODA);
                    break;
            }
        }

        initMainButtonSprite();
        initTextBox();
        //initFinishFlag();
        initMainButtonSprite();
        initBackButton();
        initCelebrationSprite();
    }

    private void initCelebrationSprite() {
        celebrationSprite = new Sprite(new Texture(Gdx.files.internal("common/end.png")));
    }

    private int getEnablePlayersAmount() {
        int ret = 0;
        for (com.gamefactoryx.cheersapp.model.kongosdrink.Player player : Configuration.getInstance().getPlayers()) {
            if (player.isEnable()) ++ret;
        }

        return ret;
    }

    public CtrlSprite[] getModusSprite() {
        return modusSprite;
    }


    @Override
    public void resize(int width, int height) {
        camera = new OrthographicCamera(960, 960 * com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldHeightLandscape() / com.gamefactoryx.cheersapp.tool.Resolution.getGameWorldWidthLandscape());
        //camera = new OrthographicCamera(Resolution.getGameWorldWidthLandscape(), Resolution.getGameWorldHeightLandscape());

        float FONT_SIZE_ON_SCREEN = 0.03f;
        if (com.gamefactoryx.cheersapp.tool.Configuration.getLanguage() == com.gamefactoryx.cheersapp.tool.Configuration.LanguageEnum.SK)
            generator = new FreeTypeFontGenerator(com.gamefactoryx.cheersapp.tool.FontHelper.getSkFontFile());
        else
            generator = new FreeTypeFontGenerator(com.gamefactoryx.cheersapp.tool.FontHelper.getFontFile());
        if (com.gamefactoryx.cheersapp.tool.Orientation.getOrientation() == Input.Orientation.Portrait) {
            FONT_SIZE = (int) (540 * FONT_SIZE_ON_SCREEN);
        } else {
            FONT_SIZE = (int) (960 * FONT_SIZE_ON_SCREEN);
        }
        celebrationSprite.setSize(960 * 0.8f,
                540 * 0.8f * 540/960);


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
        com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().isFinished();
    }

    @Override
    public void render(float delta) {
        /*Gdx.app.log("Status", String.format("PlayerIndex:%s, PlayerPosition:%s, Active:%s",
                KongosDrinkMainModel.getInstance().getPlayerIndex(),
                Configuration.getPlayers()[KongosDrinkMainModel.getInstance().getPlayerIndex()].getPosition(),
                Configuration.getPlayers()[KongosDrinkMainModel.getInstance().getPlayerIndex()].isEnable()
                ));
*/

        float alpha = com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().isFinished() ? 0.3f : 1.0f;
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        sprite[com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().getIndex()].setPosition(-sprite[com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().getIndex()].getWidth() / 4 - com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().getXcoor(), -sprite[com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().getIndex()].getHeight() / 2);
        sprite[com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().getIndex()].draw(batch, alpha);
        for (int i = playerSprite.length - 1; i >= 0; --i) {
            if (Configuration.getInstance().getPlayers().get(i).isActive())
                playerSprite[i].setPosition(-playerSprite[i].getWidth() / 4,
                        -playerSprite[i].getHeight() / 2 * 2.7f);
            else
                playerSprite[i].setPosition(-playerSprite[i].getWidth() / 4 + Configuration.getInstance().getPlayers().get(i).getNormPosition() - com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().getXxcoor(),
                        -playerSprite[i].getHeight() / 2 * 2.7f);
            if (Configuration.getInstance().getPlayers().get(i).getRotate() != 0)
                playerSprite[i].rotate(Configuration.getInstance().getPlayers().get(i).getRotate());
            /*else
                playerSprite[i].rotate(0.0f - playerSprite[i].getRotation());*/
            playerSprite[i].draw(batch, alpha);
        }
        if (Configuration.getInstance().getPlayers().get(com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().getPlayerIndex()).getRotate() == 0) {
            playerSprite[com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().getPlayerIndex()].draw(batch, alpha);
        }

        /*finishFlag.setPosition(-(finishFlag.getWidth() * 2) / 4 + (Configuration.getGameSize().getValue() - 1) * Configuration.KongosDrink.DISTANCE_BETWEEN_TWO_FIELDS - KongosDrinkMainModel.getInstance().getXxcoor(),
                -finishFlag.getHeight() / 2 * 1.9f);
        finishFlag.draw(batch);*/


        if (foregroundSprite != null && foregroundSprite[com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().getIndex()] != null) {
            foregroundSprite[com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().getIndex()].setPosition(-foregroundSprite[com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().getIndex()].getWidth() / 4 - com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().getXcoor(), -foregroundSprite[com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().getIndex()].getHeight() / 2);
            foregroundSprite[com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().getIndex()].draw(batch, alpha);
        }


        modusSprite[0].setActive((com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().getModus() & com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.CAP) == com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.CAP);
        modusSprite[1].setActive((com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().getModus() & com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.CHANGES) == com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.CHANGES);
        modusSprite[2].setActive((com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().getModus() & com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.DRINK_TOGETHER) == com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.DRINK_TOGETHER);
        modusSprite[3].setActive((com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().getModus() & com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.MIRROR) == com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.MIRROR);
        modusSprite[4].setActive((com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().getModus() & com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.NO_TALKING) == com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.NO_TALKING);
        modusSprite[5].setActive((com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().getModus() & com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.PIG) == com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.PIG);
        modusSprite[6].setActive((com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().getModus() & com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.RULES) == com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.RULES);
        modusSprite[7].setActive((com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().getModus() & com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.STAR) == com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.STAR);
        modusSprite[8].setActive((com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().getModus() & com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.WC) == com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.WC);
        modusSprite[9].setActive((com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().getModus() & com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.YODA) == com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.YODA);


        int i = 0;


        for (CtrlSprite ms : modusSprite) {
            if (ms.isActive()) {
                ms.setPosition(300 - 120 * i++, 120);
                ms.draw(batch);
                if (ms.isClicked()) {
                    getTextBox().setPosition(-440, -200);
                    getTextBox().draw(batch);
                    List<String> text = splitLine(com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().getModusTypeTextMap().get(ms.getType()));
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

        if(com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().isWhoIsWho()){
            getTextBox().setPosition(-440, -200);
            getTextBox().draw(batch);
            List<String> text = new ArrayList<>();
            for(com.gamefactoryx.cheersapp.model.Subject subject:  Configuration.getInstance().getPlayers().get(com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().getPlayerIndex()).getSubjects())
                text.add(subject.getName());
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


        if (!com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().isTextBoxDisplayed() && !com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().isAnimationRunning()
                && !com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().isWhoIsWho()) {
            com.gamefactoryx.cheersapp.model.kongosdrink.Card card = com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().getActiveCard();
            if (card != null) {
                getTextBox().setPosition(-440, -200);
                getTextBox().draw(batch);
                String team = Configuration.getInstance().getPlayers().get(com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().getPlayerIndex()).getName();

                font.draw(batch, String.format("%s (%s)", team, card.getPoint()), -410, 150);

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
        if (com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().isWhoIsWho()) {
            if(Configuration.getInstance().getGameType() != Configuration.GameTypeEnum.DOGFIGHT) {
                mainButtonsSprite[0].setPosition(60 + 120 * (mainButtonsSprite.length - 2), -80);
                mainButtonsSprite[0].draw(batch);
            }
        }

        if (!com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().isTextBoxDisplayed() && !com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().isAnimationRunning()
                && !com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().isWhoIsWho()) {
            for (int ii = 1; ii < mainButtonsSprite.length; ii++) {
                if (mainButtonsSprite[ii].isActive()) {

                    if (ii == 1) {
                        if(Configuration.getInstance().getGameType() != Configuration.GameTypeEnum.DOGFIGHT) {
                            mainButtonsSprite[0].setPosition(60 + 120 * (mainButtonsSprite.length - 2), -80);
                            mainButtonsSprite[0].draw(batch);
                        }

                        mainButtonsSprite[ii].setPosition(-mainButtonsSprite[ii].getWidth(), -mainButtonsSprite[ii].getHeight() / 4);
                        mainButtonsSprite[ii].draw(batch);
                        String team = Configuration.getInstance().getPlayers().get(com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().getPlayerIndex()).getName();
                        nameFont.draw(batch, team,
                                mainButtonsSprite[ii].getX() * 0.9f,
                                mainButtonsSprite[ii].getY() + mainButtonsSprite[ii].getHeight() * 0.9f);
                    } else {
                        mainButtonsSprite[ii].setPosition(60 + 120 * (ii - 2), -80);
                        mainButtonsSprite[ii].draw(batch);
                    }

                }
            }
        }

        if(com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().isFinished()){
            celebrationSprite.setPosition(-celebrationSprite.getWidth()/2, -celebrationSprite.getHeight()/2);
            celebrationSprite.draw(batch);

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

        if(celebrationSprite != null)
            celebrationSprite.getTexture().dispose();
    }

    private void setBackgroudTexture() {
        switch (Configuration.getInstance().getGameSize()) {
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
                        new Texture(Gdx.files.internal("common/kongos_drink/game_design/40/40.8.jpg")),
                        new Texture(Gdx.files.internal("common/kongos_drink/game_design/40/40.9.jpg"))};
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
        com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().setCountOfTextures(backgroundTexture.length);

    }

    private void setForegroundTexture() {
        switch (Configuration.getInstance().getGameSize()) {
            case FIFTY:
                foregroundTexture = new Texture[]{null,
                        new Texture(Gdx.files.internal("common/kongos_drink/game_design/50/50.2.1.png")),
                        new Texture(Gdx.files.internal("common/kongos_drink/game_design/50/50.3.1.png")),
                        new Texture(Gdx.files.internal("common/kongos_drink/game_design/50/50.4.1.png")),
                        new Texture(Gdx.files.internal("common/kongos_drink/game_design/50/50.5.1.png")),
                        null,
                        null,
                        null,
                        null,
                        null};
                break;
            case FORTY:
                foregroundTexture = new Texture[]{null,
                        new Texture(Gdx.files.internal("common/kongos_drink/game_design/40/40.2.1.png")),
                        new Texture(Gdx.files.internal("common/kongos_drink/game_design/40/40.3.1.png")),
                        new Texture(Gdx.files.internal("common/kongos_drink/game_design/40/40.4.1.png")),
                        new Texture(Gdx.files.internal("common/kongos_drink/game_design/40/40.5.1.png")),
                        null,
                        null,
                        null,
                        null};
                break;
            case THRITY:
            case TWENTY:
            case FIFTEEN:
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
        /*backButtonSprite = new Sprite(new Texture("common/return.png"));
        backButtonSprite.setSize(Resolution.getGameWorldWidthPortrait() * 0.03f, Resolution.getGameWorldHeightPortrait() * Resolution.getAspectRatio() * 0.03f);
        backButtonSprite.setPosition(-960 / 2, -540 / 2);*/

    }

}