package com.gamefactoryx.cheers.tool;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

/**
 * Created by Bernat on 08.05.2017.
 */
public class Configuration {

    private static Music mp3Music = Gdx.audio.newMusic(Gdx.files.internal("common/cheers_musik.mp3"));

    public static enum LanguageEnum {
        DE("de"), EN("en"), SK("sk");
        private final String name;

        private LanguageEnum(String name) {
            this.name = name;
        }

        public String toString() {
            return name;
        }
    }

    public static enum INeverDoGameTypeEnum {
        GAME_18PLUS("18+"), GAME_STANDARD("Standart"), GAME_MIXED("Mix");
        private final String name;

        private INeverDoGameTypeEnum(String name) {
            this.name = name;
        }

        public String toString() {
            return name;
        }
    }

    private static boolean playMusic  = true;

    private static LanguageEnum language;
    private static INeverDoGameTypeEnum iNeverDoGameType;
    private static int MAX_PLAYERS = 6;
    private static int MAX_TOP_SCORERS = 4;
    private static int MAX_PLAYERS_PRO_CONFIG_PAGE = 4;

    public static LanguageEnum getLanguage() {
        if (language == null)
            setLanguage(LanguageEnum.DE);
        return language;
    }

    public static INeverDoGameTypeEnum getINeverDoGameType() {
        if (iNeverDoGameType == null)
            setINeverDoGameType(INeverDoGameTypeEnum.GAME_MIXED);
        return iNeverDoGameType;
    }

    public static void setLanguage(LanguageEnum _language) {
        language = _language;
    }

    public static void setINeverDoGameType(INeverDoGameTypeEnum _iNeverDoGameType) {
        iNeverDoGameType = _iNeverDoGameType;
    }

    public static int getMaxPlayers() {
        return MAX_PLAYERS;
    }

    public static int getMaxTopScorers() {
        return MAX_TOP_SCORERS;
    }

    public static int getMaxPlayersProConfigPage() {
        return MAX_PLAYERS_PRO_CONFIG_PAGE;
    }

    private Configuration() {
    }

    public static boolean isPlayMusic() {
        return playMusic;
    }

    public static void setPlayMusic(boolean playMusic) {
        Configuration.playMusic = playMusic;
    }

    public static void playMusic() {
        if (playMusic) {
            mp3Music.setLooping(true);
            mp3Music.play();
        } else {
            mp3Music.pause();
        }
    }

}
