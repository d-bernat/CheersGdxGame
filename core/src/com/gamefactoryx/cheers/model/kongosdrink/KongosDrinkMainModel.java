package com.gamefactoryx.cheers.model.kongosdrink;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.gamefactoryx.cheers.tool.FunnyNameGenerator;
import com.gamefactoryx.cheers.tool.kongosdrink.Configuration;

import java.util.HashMap;

/**
 * Created by Bernat on 10.07.2017.
 */
public class KongosDrinkMainModel {

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

    ;

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
        Player[] players = Configuration.getPlayers();
        for (int i = 0; i < players.length; i++) {
            players[i].setPosition(1);
            players[i].setFinished(false);
            players[i].setActive(false);
        }
        //player[i].setName(FunnyNameGenerator.getFunnyName(i));


        modusTypeTextMap = new HashMap<>();
        modusTypeTextMap.put(CAP, Gdx.files.internal(com.gamefactoryx.cheers.tool.Configuration.getLanguage() + "/kongosdrink/cards/cardsigns_text/cap.txt").

                readString());
        modusTypeTextMap.put(CHANGES, Gdx.files.internal(com.gamefactoryx.cheers.tool.Configuration.getLanguage() + "/kongosdrink/cards/cardsigns_text/changes.txt").

                readString());
        modusTypeTextMap.put(DRINK_TOGETHER, Gdx.files.internal(com.gamefactoryx.cheers.tool.Configuration.getLanguage() + "/kongosdrink/cards/cardsigns_text/drink_together.txt").

                readString());
        modusTypeTextMap.put(MIRROR, Gdx.files.internal(com.gamefactoryx.cheers.tool.Configuration.getLanguage() + "/kongosdrink/cards/cardsigns_text/mirror.txt").

                readString());
        modusTypeTextMap.put(NO_TALKING, Gdx.files.internal(com.gamefactoryx.cheers.tool.Configuration.getLanguage() + "/kongosdrink/cards/cardsigns_text/notalking.txt").

                readString());
        modusTypeTextMap.put(PIG, Gdx.files.internal(com.gamefactoryx.cheers.tool.Configuration.getLanguage() + "/kongosdrink/cards/cardsigns_text/pig.txt").

                readString());
        modusTypeTextMap.put(RULES, Gdx.files.internal(com.gamefactoryx.cheers.tool.Configuration.getLanguage() + "/kongosdrink/cards/cardsigns_text/rules.txt").

                readString());
        modusTypeTextMap.put(STAR, Gdx.files.internal(com.gamefactoryx.cheers.tool.Configuration.getLanguage() + "/kongosdrink/cards/cardsigns_text/star.txt").

                readString());
        modusTypeTextMap.put(WC, Gdx.files.internal(com.gamefactoryx.cheers.tool.Configuration.getLanguage() + "/kongosdrink/cards/cardsigns_text/wc.txt").

                readString());
        modusTypeTextMap.put(YODA, Gdx.files.internal(com.gamefactoryx.cheers.tool.Configuration.getLanguage() + "/kongosdrink/cards/cardsigns_text/yoda.txt").

                readString());

        setPosition(0);

        setPlayerIndex(0);

        setIndex(0);

        setXcoor(0);

        setXxcoor(0);

        setStep(Step.LEVEL);

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
}
