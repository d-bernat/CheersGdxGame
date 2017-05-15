package com.gamefactoryx.cheers.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

/**
 * Created by bernat on 05.05.2017.
 */
@SuppressWarnings("DefaultFileTemplate")
public class INeverDoModel {

    private static Configuration.LanguageEnum lastLanguage;
    private String[] tasks;
    private INeverDoModel(){}
    public String getLine() {

        return tasks[(int)((Math.random()) * tasks.length )];
    }

    private void setTasks(String[] tasks){
        this.tasks = tasks;
    }

    private static INeverDoModel instance;

    public static INeverDoModel getInstance(){
        if(instance == null ) {
            instance = new INeverDoModel();
        }
        if(lastLanguage == null || lastLanguage != Configuration.getLanguage()) {
            FileHandle taskFile = Gdx.files.internal(Configuration.getLanguage() + "/iNeverDoScreen/tasks.txt");
            instance.setTasks(taskFile.readString().split("\\n"));
        }
        lastLanguage = Configuration.getLanguage();
        return instance;
    }

}
