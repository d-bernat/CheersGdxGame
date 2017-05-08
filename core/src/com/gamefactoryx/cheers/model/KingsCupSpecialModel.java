package com.gamefactoryx.cheers.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

/**
 * Created by bernat on 05.05.2017.
 */
@SuppressWarnings("DefaultFileTemplate")
public class KingsCupSpecialModel {

    private String text;
    private KingsCupSpecialModel(){}
    public String getText() {
        return text;
    }

    private void setTasks(String text){
        this.text = text;
    }


    private static KingsCupSpecialModel instance;

    public static KingsCupSpecialModel getInstance(){
        if(instance == null ) {
            instance = new KingsCupSpecialModel();
        }
        FileHandle taskFile = Gdx.files.internal(Configuration.getLanguage() + "/kingsCupSpecial/KingsCupSpecialDescription.txt");
        instance.setTasks( taskFile.readString());
        return instance;
    }

}
