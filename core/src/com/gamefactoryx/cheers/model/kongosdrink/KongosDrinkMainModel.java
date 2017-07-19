package com.gamefactoryx.cheers.model.kongosdrink;

/**
 * Created by Bernat on 10.07.2017.
 */
public class KongosDrinkMainModel {
    private static KongosDrinkMainModel instance;
    //which screen
    private int index;
    //total x coor
    private int xxcoor;
    //screen x coor
    private int xcoor;
    //rotation of player
    private float rotate;
    //which player
    private int playerIndex;
    private int position = -1;
    //player
    private int playerXCoor;

    private PlayerModel[] players;


   // private OrderedMap<Integer, String> getTopScorers = new OrderedMap<>();

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

    public float getRotate() {
        return rotate;
    }

    public void setRotate(float rotate) {
        this.rotate = rotate;
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

    public int getPlayerXCoor() {
        return playerXCoor;
    }

    public void setPlayerXCoor(int playerXCoor) {
        this.playerXCoor = playerXCoor;
    }


    public PlayerModel[] getPlayers() {
        return players;
    }


    public void setPlayers(PlayerModel[] players) {
        this.players = players;
    }

}
