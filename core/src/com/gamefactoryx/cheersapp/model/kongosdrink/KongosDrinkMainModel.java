package com.gamefactoryx.cheersapp.model.kongosdrink;

import com.badlogic.gdx.Gdx;
import com.gamefactoryx.cheersapp.tool.kongosdrink.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Bernat on 10.07.2017.
 */
public class KongosDrinkMainModel extends com.gamefactoryx.cheersapp.model.Model {

    public static final int CAP = 2;
    public static final int CHANGES = 4;
    public static final int DRINK_TOGETHER = 8;
    public static final int MIRROR = 16;
    public static final int NO_TALKING = 32;
    public static final int PIG = 64;
    public static final int RULES = 128;
    public static final int STAR = 256;
    public static final int WC = 512;
    public static final int YODA = 1024;


    public enum Step {LEVEL, TASK, MOVE}


    private final HashMap<Integer, String> modusTypeTextMap;


    private static KongosDrinkMainModel instance;
    //which screen elem
    private int index;
    //total screen chain x coor
    private int xxcoor;
    //screen elem x coor
    private int xcoor;
    //screen chain position
    private int position = -1;

    //which player
    private int playerIndex;

    private int modus;

    private boolean finished;

    private int level;

    private Step step;

    private com.gamefactoryx.cheersapp.model.kongosdrink.Card activeCard;

    private Map<Integer,List<com.gamefactoryx.cheersapp.model.kongosdrink.Card>> cards;

    private boolean animationRunning;

    private int enablePlayersSize;

    private boolean textBoxDisplayed;

    private int countOfTextures;

    private boolean loadingNextStage;

    private boolean whoIsWho;


    public boolean isWhoIsWho() {
        return whoIsWho;
    }

    public void setWhoIsWho(boolean whoIsWho) {
        this.whoIsWho = whoIsWho;
    }

    public static KongosDrinkMainModel getInstance() {
        if (instance == null) {
            instance = new KongosDrinkMainModel();
        }

        return instance;
    }

    public static KongosDrinkMainModel getNewInstance() {
        instance = new KongosDrinkMainModel();
        return instance;
    }

    private KongosDrinkMainModel() {

        cards = CardFactory.getNewInstance().getCards();

        List<Player> players = Configuration.getInstance().getPlayers();
        for (Player player: players) {
            if(player.isEnable()) {
                player.setPosition(1);
                player.setFinished(false);
                player.setActive(false);
                ++enablePlayersSize;
            }
            else{
                player.setPosition(0);
                player.setFinished(false);
                player.setActive(false);

            }

        }

        modusTypeTextMap = new HashMap<>();
        modusTypeTextMap.put(CAP, Gdx.files.internal(com.gamefactoryx.cheersapp.tool.Configuration.getLanguage() + "/kongosdrink/cards/cardsigns_text/cap.txt").

                readString());
        modusTypeTextMap.put(CHANGES, Gdx.files.internal(com.gamefactoryx.cheersapp.tool.Configuration.getLanguage() + "/kongosdrink/cards/cardsigns_text/changes.txt").

                readString());
        modusTypeTextMap.put(DRINK_TOGETHER, Gdx.files.internal(com.gamefactoryx.cheersapp.tool.Configuration.getLanguage() + "/kongosdrink/cards/cardsigns_text/drink_together.txt").

                readString());
        modusTypeTextMap.put(MIRROR, Gdx.files.internal(com.gamefactoryx.cheersapp.tool.Configuration.getLanguage() + "/kongosdrink/cards/cardsigns_text/mirror.txt").

                readString());
        modusTypeTextMap.put(NO_TALKING, Gdx.files.internal(com.gamefactoryx.cheersapp.tool.Configuration.getLanguage() + "/kongosdrink/cards/cardsigns_text/notalking.txt").

                readString());
        modusTypeTextMap.put(PIG, Gdx.files.internal(com.gamefactoryx.cheersapp.tool.Configuration.getLanguage() + "/kongosdrink/cards/cardsigns_text/pig.txt").

                readString());
        modusTypeTextMap.put(RULES, Gdx.files.internal(com.gamefactoryx.cheersapp.tool.Configuration.getLanguage() + "/kongosdrink/cards/cardsigns_text/rules.txt").

                readString());
        modusTypeTextMap.put(STAR, Gdx.files.internal(com.gamefactoryx.cheersapp.tool.Configuration.getLanguage() + "/kongosdrink/cards/cardsigns_text/star.txt").

                readString());
        modusTypeTextMap.put(WC, Gdx.files.internal(com.gamefactoryx.cheersapp.tool.Configuration.getLanguage() + "/kongosdrink/cards/cardsigns_text/wc.txt").

                readString());
        modusTypeTextMap.put(YODA, Gdx.files.internal(com.gamefactoryx.cheersapp.tool.Configuration.getLanguage() + "/kongosdrink/cards/cardsigns_text/yoda.txt").

                readString());

        setPosition(0);

        setPlayerIndex(0);

        setIndex(0);

        setXcoor(0);

        setXxcoor(0);

        setStep(Step.LEVEL);

    }

    @Override
    protected void initRulesText() {
        rulesText = null;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getXcoor() {
        return xcoor;
    }

    public void setXcoor(int xcoor) {
        this.xcoor = xcoor;
    }

    public int getXxcoor() {
        return xxcoor;
    }

    public void setXxcoor(int xxcoor) {
        this.xxcoor = xxcoor;
    }

    public int getPlayerIndex() {
        return playerIndex;
    }

    public void setPlayerIndex(int playerIndex) {
        this.playerIndex = playerIndex;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getModus() {
        return modus;
    }

    public void setModus(int modus) {
        this.modus = modus;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public HashMap<Integer, String> getModusTypeTextMap() {
        return modusTypeTextMap;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Step getStep() {
        return step;
    }

    public void setStep(Step step) {
        this.step = step;
    }

    public com.gamefactoryx.cheersapp.model.kongosdrink.Card getActiveCard() {
        return activeCard;
    }

    public void setActiveCard(com.gamefactoryx.cheersapp.model.kongosdrink.Card activeCard) {
        this.activeCard = activeCard;
    }

    public Map<Integer, List<com.gamefactoryx.cheersapp.model.kongosdrink.Card>> getCards() {
        return cards;
    }

    public boolean isAnimationRunning() {
        return animationRunning;
    }

    public void setAnimationRunning(boolean animationRunning) {
        this.animationRunning = animationRunning;
    }

    public int getEnablePlayersSize() {
        return enablePlayersSize;
    }

    public boolean isTextBoxDisplayed() {
        return textBoxDisplayed;
    }

    public void setTextBoxDisplayed(boolean textBoxDisplayed) {
        this.textBoxDisplayed = textBoxDisplayed;
    }


    public int getCountOfTextures() {
        return countOfTextures;
    }

    public void setCountOfTextures(int countOfTextures) {
        this.countOfTextures = countOfTextures;
    }

    public boolean isLoadingNextStage() {
        return loadingNextStage;
    }

    public void setLoadingNextStage(boolean loadingNextStage) {
        this.loadingNextStage = loadingNextStage;
    }

}
