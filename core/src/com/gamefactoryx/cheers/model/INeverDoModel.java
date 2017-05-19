package com.gamefactoryx.cheers.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

/**
 * Created by bernat on 05.05.2017.
 */
@SuppressWarnings("DefaultFileTemplate")
public class INeverDoModel {

    private static com.gamefactoryx.cheers.tool.Configuration.LanguageEnum lastLanguage;
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
        if(lastLanguage == null || lastLanguage != com.gamefactoryx.cheers.tool.Configuration.getLanguage()) {
            FileHandle taskFile = Gdx.files.internal(com.gamefactoryx.cheers.tool.Configuration.getLanguage() + "/iNeverDoScreen/tasks.txt");
            instance.setTasks(taskFile.readString().split("\\n"));
        }
        lastLanguage = com.gamefactoryx.cheers.tool.Configuration.getLanguage();
        return instance;
    }

}
