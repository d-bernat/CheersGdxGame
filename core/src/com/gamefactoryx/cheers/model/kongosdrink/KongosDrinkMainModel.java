package com.gamefactoryx.cheers.model.kongosdrink;

import com.gamefactoryx.cheers.tool.FunnyNameGenerator;
import com.gamefactoryx.cheers.tool.kongosdrink.Configuration;

/**
 * Created by Bernat on 10.07.2017.
 */
public class KongosDrinkMainModel {
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
                    break;
                case 1:
                    player[i].setAvatar(AvatarType.SLOVAKIA);
                    player[i].setPosition(14);
                    break;
                case 2:
                    player[i].setAvatar(AvatarType.GERMANY);
                    player[i].setPosition(3);
                    break;
                case 3:
                    player[i].setAvatar(AvatarType.CZECH);
                    player[i].setPosition(5);
                    break;
            }
            player[i].setName(FunnyNameGenerator.getFunnyName(i));
        }

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
}
