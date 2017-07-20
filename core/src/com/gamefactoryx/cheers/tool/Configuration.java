package com.gamefactoryx.cheers.tool;

import com.badlogic.gdx.Gdx;

/**
 * Created by Bernat on 08.05.2017.
 */
public class Configuration {


    public static enum LanguageEnum{
        DE("de"), EN("en"), SK("sk");
        private final String name;
        private LanguageEnum(String name){
            this.name = name;
        }
        public String toString(){
            return name;
        }
    }


    private static LanguageEnum language;
    private static int MAX_PLAYERS = 6;
    private static int MAX_TOP_SCORERS = 6;

    public static LanguageEnum getLanguage(){
        if(language == null)
            setLanguage(LanguageEnum.DE);
        return language;
    }
    public static void setLanguage(LanguageEnum _language){
        language = _language;
    }
    public static int getMaxPlayers(){
        return MAX_PLAYERS;
    }
    public static int getMaxTopScorers(){
        return MAX_TOP_SCORERS;
    }

    private Configuration(){}
}
