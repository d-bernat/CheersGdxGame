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
    private static int MAX_PLAYERS = 2;

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
    public static void setMaxPlayers(int count){
        MAX_PLAYERS = count;
    }

    private Configuration(){}

}
