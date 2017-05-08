package com.gamefactoryx.cheers.model;

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

    public static LanguageEnum getLanguage(){
        if(language == null)
            setLanguage(LanguageEnum.DE);
        return language;
    }
    public static void setLanguage(LanguageEnum _language){
        language = _language;
    }


    private Configuration(){}

}
