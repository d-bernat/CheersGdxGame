package com.gamefactoryx.cheers.tool.kongosdrink;

import com.gamefactoryx.cheers.model.Subject;
import com.gamefactoryx.cheers.model.kongosdrink.AvatarType;
import com.gamefactoryx.cheers.model.kongosdrink.Player;
import com.gamefactoryx.cheers.tool.FunnySubjectGenerator;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

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
    private  GameSizeEnum gameSize = GameSizeEnum.FORTY;
    private  boolean penalty;
    private  int sound;
    private  List<Player> players = new ArrayList<>();

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
        for(int i = 0; i < getMaxPlayers(); i++)
            players.add(new Player(FunnySubjectGenerator.getFunnySubject(i)));

    }

    public static class KongosDrink {
        public static final int DISTANCE_BETWEEN_TWO_FIELDS = 187;
    }

    public  int getHowManyConfigurationScreens(){
        return com.gamefactoryx.cheers.tool.Configuration.getMaxPlayers() / 6 + 1;
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

    public  List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    /*public  String getPlayerName(int index) {
        return players.get(index).getPlayerName();
    }*/

    public  Player getRandomPlayer(int excluded) {
        return getRandomPlayer(excluded, Subject.Sex.DONT_CARE);
    }

    public  Player getRandomPlayer(int excluded, Subject.Sex sex) {
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
        while (sex != Subject.Sex.DONT_CARE && players.get(randomNum).getSex() != sex);

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
        for(Player player: getPlayers()){
            if(player.isEnable()) ++ret;
        }

        return ret;
    }


    public  int getMaxPlayers(){
        return MAX_PLAYERS;
    }

}
