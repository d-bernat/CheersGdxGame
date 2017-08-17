package com.gamefactoryx.cheers.tool.kongosdrink;

import com.gamefactoryx.cheers.model.kongosdrink.AvatarType;
import com.gamefactoryx.cheers.model.kongosdrink.Player;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Bernat on 08.05.2017.
 */
public class Configuration {

    public enum GameSizeEnum{
        THRITY(30), FORTY(40), FIFTY(50);
        private final int value;
        GameSizeEnum(int value){
            this.value = value;
        }
        public String toString(){
            return Integer.toString(value);
        }
    }

    public enum GameType {
        DOGFIGHT("dogfight"), TEAMOFTWO_VS_TEAMOFTWO("2team vs 2team"), TEAM_VS_TEAM("team vs team");
        private String value;

        GameType(String value) {
            this.value = value;

        }

        public String value() {
            return this.value;
        }
    }



    private static GameSizeEnum gameSize = GameSizeEnum.FIFTY;
    private static boolean penalty;
    private static int sound;
    private static Player[] players = { new Player("Samko", Player.SEX.MALE, AvatarType.BULGARIA),
                                            new Player("Kajka", Player.SEX.FEMALE, AvatarType.SLOVAKIA),
                                            new Player("Baska", Player.SEX.FEMALE, AvatarType.GERMANY),
                                            new Player("Majo", Player.SEX.MALE, AvatarType.CZECH) };

    private static GameType gameType = GameType.TEAM_VS_TEAM;
    private static long modusTypeInterval = 10_000;


    public static GameSizeEnum getGameSize(){
        return gameSize;
    }
    public static void setGameSize(GameSizeEnum _gameSize){
        gameSize = _gameSize;
    }

    private Configuration(){}
    public static class KongosDrink{
        public static final int DISTANCE_BETWEEN_TWO_FIELDS = 187;
    }

    public static boolean isPenalty() {
        return penalty;
    }

    public static void setPenalty(boolean _penalty) {
        penalty = _penalty;
    }

    public static int getSound() {
        return sound;
    }

    public static void setSound(int _sound) {
        sound = _sound;
    }

    public static Player[] getPlayers() {
        return players;
    }

    public static GameType getGameType() {
        return gameType;
    }

    public static void setGameType(GameType _gameType) {
        gameType = _gameType;
    }

    public static String getPlayerName(int index){
        return players[index].getName();
    }

    public static Player getRandomPlayer(int excluded){
        return getRandomPlayer(excluded, Player.SEX.DONT_CARE);
    }

    public static Player getRandomPlayer(int excluded, Player.SEX sex){
        int randomNum;
        int acc = 0;
        do {
            randomNum = (new Random()).nextInt(players.length);
            ++acc;
        }while((!(randomNum != excluded && (sex == Player.SEX.DONT_CARE || players[randomNum].getSex() == sex))) && acc < players.length);
        return players[randomNum];
    }


    public static long getModusTypeInterval() {
        return modusTypeInterval;
    }

    public static void setModusTypeInterval(long modusTypeInterval) {
        Configuration.modusTypeInterval = modusTypeInterval;
    }
}
