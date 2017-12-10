package com.gamefactoryx.cheersapp.tool;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

import java.util.Locale;

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
    private static boolean showBackButton = Gdx.app.getType() == Application.ApplicationType.iOS;
    private static boolean showRules = true;
    private static boolean premium;
    private static boolean admin;

    public static LanguageEnum getLanguage() {
        if (language == null) {
            if("de".equals(Locale.getDefault().getLanguage()))
                setLanguage(LanguageEnum.DE);
            else
                setLanguage(LanguageEnum.EN);
        }
        return language;
    }

    public static INeverDoGameTypeEnum getINeverDoGameType() {
        if (iNeverDoGameType == null)
            setINeverDoGameType(INeverDoGameTypeEnum.GAME_STANDARD);
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

    public static boolean isShowBackButton() {
        return showBackButton;
    }

    public static void setShowBackButton(boolean showBackButton) {
        Configuration.showBackButton = showBackButton;
    }

    public static boolean isShowRules() {
        return showRules;
    }

    public static void setShowRules(boolean showRules) {
        Configuration.showRules = showRules;
    }

    public static boolean isPremium() {
        return premium;
    }

    public static void setPremium(boolean premium) {
        Configuration.premium = false;
    }

    public static boolean isAdmin() {
        return admin;
    }

    public static void setAdmin(boolean admin) {
        Configuration.admin = admin;
    }
}
