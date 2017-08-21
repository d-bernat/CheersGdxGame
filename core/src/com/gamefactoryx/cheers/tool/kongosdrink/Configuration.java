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
        TEN(10), TWENTY(20), THRITY(30), FORTY(40), FIFTY(50);
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


    private static GameSizeEnum gameSize = GameSizeEnum.FORTY;
    private static boolean penalty;
    private static int sound;
    private static final List<Player> players = new ArrayList<>();

    private static GameType gameType = GameType.TEAM_VS_TEAM;
    private static long modusTypeInterval = 120_000;


    public static GameSizeEnum getGameSize() {
        return gameSize;
    }

    public static void setGameSize(GameSizeEnum _gameSize) {
        gameSize = _gameSize;
    }

    static {
        for(int i = 0; i < com.gamefactoryx.cheers.tool.Configuration.getMaxPlayers(); i++)
            players.add(new Player(FunnySubjectGenerator.getFunnySubject(i)));
    }

    private Configuration() {
    }

    public static class KongosDrink {
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

    public static List<Player> getPlayers() {
        return players;
    }

    public static GameType getGameType() {
        return gameType;
    }

    public static void setGameType(GameType _gameType) {
        gameType = _gameType;
    }

    public static String getPlayerName(int index) {
        return players.get(index).getName();
    }

    public static Player getRandomPlayer(int excluded) {
        return getRandomPlayer(excluded, Subject.Sex.DONT_CARE);
    }

    public static Player getRandomPlayer(int excluded, Subject.Sex sex) {
        int randomNum = 0;
        int acc = 0;
        do {
           do{ randomNum = (new Random()).nextInt(getEnablePlayersAmount());} while(randomNum == excluded);
            ++acc;
        }
        while ((!(randomNum != excluded &&
                (sex == Subject.Sex.DONT_CARE || players.get(randomNum).getSex() == sex)))
                && acc < players.size());
        return players.get(randomNum);
    }


    public static long getModusTypeInterval() {
        return modusTypeInterval;
    }

    public static void setModusTypeInterval(long modusTypeInterval) {
        Configuration.modusTypeInterval = modusTypeInterval;
    }

    private static int getEnablePlayersAmount() {
        int ret = 0;
        for(Player player: Configuration.getPlayers()){
            if(player.isEnable()) ++ret;
        }

        return ret;
    }
}
