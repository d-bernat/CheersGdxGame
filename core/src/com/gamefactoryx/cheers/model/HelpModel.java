package com.gamefactoryx.cheers.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

/**
 * Created by bernat on 05.05.2017.
 */
@SuppressWarnings("DefaultFileTemplate")
public class HelpModel {

    private String text;
    private int maxYScrollPos;

    private HelpModel() {
    }

    public String getText() {
        return text;
    }

    private void setTasks(String text) {
        this.text = text;
    }


    private static HelpModel instance;

    public static HelpModel getInstance() {
        if (instance == null) {
            instance = new HelpModel();
        }
        FileHandle taskFile = Gdx.files.internal(com.gamefactoryx.cheers.tool.Configuration.getLanguage() + "/help/questions.txt");
        instance.setTasks(taskFile.readString("UTF-8"));

        return instance;
    }

    public int getMaxYScrollPos() {
        return maxYScrollPos;
    }

    public void setMaxYScrollPos(int maxYScrollPos) {
        this.maxYScrollPos = maxYScrollPos;
    }
}
