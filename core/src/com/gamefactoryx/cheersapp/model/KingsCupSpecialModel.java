package com.gamefactoryx.cheersapp.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.gamefactoryx.cheersapp.tool.Configuration;

import java.util.HashMap;

/**
 * Created by bernat on 05.05.2017.
 */
@SuppressWarnings("DefaultFileTemplate")
public class KingsCupSpecialModel extends Model {

   // private String text;
    private float rotation;
    private static int radSize = 15;
    private float radPosition;
    private int itemPosition;
    private boolean clicked;
    private boolean showTask;
    private boolean suspended;

    private String[] radValues = {
            "9",
            "8",
            "K",
            "9",
            "B",
            "2",
            "4",
            "7",
            "10",
            "6",
            "3",
            "A",
            "D",
            "5",
            "2"
    };

    private HashMap<String, String> tasks = new HashMap<>();

    private KingsCupSpecialModel() {
    }

   // public String getText() {
   //     return text;
   // }

    //private void setTasks(String text) {
    //    this.text = text;
    //}


    private static KingsCupSpecialModel instance;

    public static KingsCupSpecialModel getInstance() {
        if (instance == null) {
            instance = new KingsCupSpecialModel();

        }
        String[] fileNames = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "B", "D", "K", "A"};
        for(String f: fileNames ){
            FileHandle taskFile = Gdx.files.internal(Configuration.getLanguage() + "/kingsCupSpecial/" + f + ".txt");
            instance.tasks.put(f, taskFile.readString("UTF-8"));
        }

        return instance;
    }

    public static KingsCupSpecialModel getNewInstance() {
        instance = new KingsCupSpecialModel();
        //FileHandle taskFile = Gdx.files.internal(Configuration.getLanguage() + "/kingsCupSpecial/KingsCupSpecialDescription.txt");
        //instance.setTasks(taskFile.readString("UTF-8"));


        instance.rotation = 0;


        return instance;
    }

    @Override
    protected void initRulesText() {
        FileHandle file = Gdx.files.internal(Configuration.getLanguage() + "/kingsCupSpecial/rules.txt");
        rulesText = file.readString("UTF-8");
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public int getRadSize() {
        return radSize;
    }


    public int getItemPosition() {
        return itemPosition;
    }

    public void setRadPosition(float rotation) {
        this.radPosition = ((rotation < 0 ? 360 + (rotation % 360) : 360 - rotation % 360));
        itemPosition = Math.round(radPosition / (360 / radSize)) % radSize;
    }

    public boolean isClicked() {
        return clicked;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

    public String[] getRadValues() {
        return radValues;
    }

    public boolean isShowTask() {
        return showTask;
    }

    public void setShowTask(boolean showTask) {
        this.showTask = showTask;
    }

    public HashMap<String, String> getTasks() {
        return tasks;
    }

    public boolean isSuspended() {
        return suspended;
    }

    public void setSuspended(boolean suspended) {
        this.suspended = suspended;
    }
}
