package com.gamefactoryx.cheers.tool.kongosdrink;

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

    public enum GameCreditEnum{
        POINT("point"), SIP("sip");
        private final String name;
        GameCreditEnum(String name){
            this.name = name;
        }
        public String toString(){
            return name;
        }
    }

    public enum GameType {
        DOGFIGHT("dogfight"), TEAM_DOGFIGHT("team dogfight"), TEAM_VS_TEAM("team vs team");
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
    private static int playersInGame = 4;
    private static GameType gameType = GameType.DOGFIGHT;

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

    public static int getPlayersInGame() {
        return playersInGame;
    }

    public static void setPlayersInGame(int _playersInGame) {
        playersInGame = _playersInGame;
    }

    public static GameType getGameType() {
        return gameType;
    }

    public static void setGameType(GameType _gameType) {
        gameType = _gameType;
    }


}
