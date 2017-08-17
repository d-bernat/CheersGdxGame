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

    private Player[] players;

    private int modus;

    private boolean finished;


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

    private KongosDrinkMainModel(){
        Player[] player = new Player[Configuration.getPlayersInGame()];
        for(int i = 0; i < Configuration.getPlayersInGame(); i++) {
            player[i] = new Player();
            switch(i){
                case 0:
                    player[i].setAvatar(AvatarType.BULGARIA);
                    player[i].setPosition(11);
                    player[i].setName("Samko");
                    break;
                case 1:
                    player[i].setAvatar(AvatarType.SLOVAKIA);
                    player[i].setPosition(14);
                    player[i].setName("Kajka");
                    break;
                case 2:
                    player[i].setAvatar(AvatarType.GERMANY);
                    player[i].setPosition(3);
                    player[i].setName("Baska");
                    break;
                case 3:
                    player[i].setAvatar(AvatarType.CZECH);
                    player[i].setPosition(5);
                    player[i].setName("Majo");
                    break;
            }
            player[i].setName(FunnyNameGenerator.getFunnyName(i));
        }

        modusTypeTextMap = new HashMap<>();
        modusTypeTextMap.put(CAP, Gdx.files.internal(com.gamefactoryx.cheers.tool.Configuration.getLanguage() + "/kongosdrink/cards/cardsigns_text/cap_de.txt").readString());
        modusTypeTextMap.put(CHANGES, Gdx.files.internal(com.gamefactoryx.cheers.tool.Configuration.getLanguage() + "/kongosdrink/cards/cardsigns_text/changes_de.txt").readString());
        modusTypeTextMap.put(DRINK_TOGETHER, Gdx.files.internal(com.gamefactoryx.cheers.tool.Configuration.getLanguage() + "/kongosdrink/cards/cardsigns_text/drink_together_de.txt").readString());
        modusTypeTextMap.put(MIRROR, Gdx.files.internal(com.gamefactoryx.cheers.tool.Configuration.getLanguage() + "/kongosdrink/cards/cardsigns_text/mirror_de.txt").readString());
        modusTypeTextMap.put(NO_TALKING, Gdx.files.internal(com.gamefactoryx.cheers.tool.Configuration.getLanguage() + "/kongosdrink/cards/cardsigns_text/notalking_de.txt").readString());
        modusTypeTextMap.put(PIG, Gdx.files.internal(com.gamefactoryx.cheers.tool.Configuration.getLanguage() + "/kongosdrink/cards/cardsigns_text/pig_de.txt").readString());
        modusTypeTextMap.put(RULES, Gdx.files.internal(com.gamefactoryx.cheers.tool.Configuration.getLanguage() + "/kongosdrink/cards/cardsigns_text/rules_de.txt").readString());
        modusTypeTextMap.put(STAR, Gdx.files.internal(com.gamefactoryx.cheers.tool.Configuration.getLanguage() + "/kongosdrink/cards/cardsigns_text/star_de.txt").readString());
        modusTypeTextMap.put(WC, Gdx.files.internal(com.gamefactoryx.cheers.tool.Configuration.getLanguage() + "/kongosdrink/cards/cardsigns_text/WC_de.txt").readString());
        modusTypeTextMap.put(YODA, Gdx.files.internal(com.gamefactoryx.cheers.tool.Configuration.getLanguage() + "/kongosdrink/cards/cardsigns_text/Yoda_de.txt").readString());

        setPlayers(player);
        setPosition(1);
        setPlayerIndex(0);
        setIndex(0);
        setXcoor(0);
        setXxcoor(0);
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

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
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
}
