package com.gamefactoryx.cheers.model.kongosdrink;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
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

    private PlayerModel[] players;




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
        PlayerModel[] playerModel = new PlayerModel[Configuration.getPlayersInGame()];
        for(int i = 0; i < Configuration.getPlayersInGame(); i++) {
            playerModel[i] = new PlayerModel();
            switch(i){
                case 0:
                    playerModel[i].setAvatar(AvatarType.BULGARIA);
                    playerModel[i].setPosition(11);
                    break;
                case 1:
                    playerModel[i].setAvatar(AvatarType.SLOVAKIA);
                    playerModel[i].setPosition(14);
                    break;
                case 2:
                    playerModel[i].setAvatar(AvatarType.GERMANY);
                    playerModel[i].setPosition(3);
                    break;
                case 3:
                    playerModel[i].setAvatar(AvatarType.CZECH);
                    playerModel[i].setPosition(5);
                    break;
            }
        }

        setPlayers(playerModel);
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

    public PlayerModel[] getPlayers() {
        return players;
    }

    public void setPlayers(PlayerModel[] players) {
        this.players = players;
    }


}
