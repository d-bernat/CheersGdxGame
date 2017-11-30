package com.gamefactoryx.cheersapp.tool.kongosdrink;

import java.util.*;

/**
 * Created by Bernat on 08.05.2017.
 */
public class Configuration {

    public enum GameSizeEnum {
        TEN(10), FIFTEEN(15), TWENTY(20), THRITY(30), FORTY(40), FIFTY(50);
        private final int value;

        GameSizeEnum(int value) {
            this.value = value;
        }

        public String toString() {
            return Integer.toString(value);
        }

        public int getValue() {
            return value;
        }


    }

    public enum GameTypeEnum {
        DOGFIGHT("dogfight"), TEAMOFTWO_VS_TEAMOFTWO("2team vs 2team"), TEAM_VS_TEAM("team vs team");
        private String value;

        GameTypeEnum(String value) {
            this.value = value;

        }

        public String value() {
            return this.value;
        }
    }



    private static Configuration instance;
    private  GameSizeEnum gameSize = GameSizeEnum.THRITY;
    private  boolean penalty;
    private  int sound;
    private  List<com.gamefactoryx.cheersapp.model.kongosdrink.Player> players = new ArrayList<>();

    private  GameTypeEnum gameType = GameTypeEnum.DOGFIGHT;
    private  long modusTypeInterval = 600_000;
    private  int MAX_PLAYERS = 16;

    public static  Configuration getInstance(){
        if(instance == null) {
            instance = new Configuration();
            return instance;
        }
        else
            return instance;
    }

    public static  Configuration getNewInstance(){
        instance = new Configuration();
        return instance;
    }

    public  GameTypeEnum getGameType() {
        return gameType;
    }

    public  GameSizeEnum getGameSize() {
        return gameSize;
    }

    public  void setGameType(GameTypeEnum gameType) {
        this.gameType = gameType;
    }

    public  void setGameSize(GameSizeEnum _gameSize) {
        gameSize = _gameSize;
    }


    private Configuration() {
        for(int i = 0; i < getMaxPlayers(); i++) {
            com.gamefactoryx.cheersapp.model.kongosdrink.Player p = new com.gamefactoryx.cheersapp.model.kongosdrink.Player(com.gamefactoryx.cheersapp.tool.FunnySubjectGenerator.getFunnySubject(i));
            if(i < 2)
                p.setEnable(true);
            players.add(p);
        }
    }

    public static class KongosDrink {
        public static final int DISTANCE_BETWEEN_TWO_FIELDS = 187;
    }

    public  int getHowManyConfigurationScreens(){
        return com.gamefactoryx.cheersapp.tool.Configuration.getMaxPlayers() / 6 + 1;
    }

    public  boolean isPenalty() {
        return penalty;
    }

    public  void setPenalty(boolean _penalty) {
        penalty = _penalty;
    }

    public  int getSound() {
        return sound;
    }

    public  void setSound(int _sound) {
        sound = _sound;
    }

    public  List<com.gamefactoryx.cheersapp.model.kongosdrink.Player> getPlayers() {
        return players;
    }

    public int enabledPlayers(){
        int rs = 0;
        for(com.gamefactoryx.cheersapp.model.kongosdrink.Player player: players) if(player.isEnable()) ++rs;

        return rs;
    }
    public void setPlayers(List<com.gamefactoryx.cheersapp.model.kongosdrink.Player> players) {
        this.players = players;
    }

    /*public  String getPlayerName(int index) {
        return players.get(index).getPlayerName();
    }*/

    public com.gamefactoryx.cheersapp.model.kongosdrink.Player getRandomPlayer(int excluded) {
        return getRandomPlayer(excluded, com.gamefactoryx.cheersapp.model.Subject.Sex.DONT_CARE);
    }

    public com.gamefactoryx.cheersapp.model.kongosdrink.Player getRandomPlayer(int excluded, com.gamefactoryx.cheersapp.model.Subject.Sex sex) {
        int randomNum = 0;
        List<Integer> rndIndexList = new ArrayList<>();
        for(int i = 0; i < getEnablePlayersAmount(); i++){
            if(i != excluded)
                rndIndexList.add(i);
        }

        Collections.shuffle(rndIndexList);

        int acc = 0;
        do {
            if(rndIndexList.size() <= acc) break;
            randomNum = rndIndexList.get(acc);
            ++acc;
        }
        /////!!!!!!!!!!!!!!!!!!it wont work properly for more then one subject in player
        while (sex != com.gamefactoryx.cheersapp.model.Subject.Sex.DONT_CARE && players.get(randomNum).getSex() != sex);

        return players.get(randomNum);
    }


    public  long getModusTypeInterval() {
        return modusTypeInterval;
    }

    public  void setModusTypeInterval(long modusTypeInterval) {
        this.modusTypeInterval = modusTypeInterval;
    }

    private  int getEnablePlayersAmount() {
        int ret = 0;
        for(com.gamefactoryx.cheersapp.model.kongosdrink.Player player: getPlayers()){
            if(player.isEnable()) ++ret;
        }

        return ret;
    }


    public  int getMaxPlayers(){
        return MAX_PLAYERS;
    }

}
