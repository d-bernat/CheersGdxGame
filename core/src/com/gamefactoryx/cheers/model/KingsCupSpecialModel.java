package com.gamefactoryx.cheers.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.io.UnsupportedEncodingException;

/**
 * Created by bernat on 05.05.2017.
 */
@SuppressWarnings("DefaultFileTemplate")
public class KingsCupSpecialModel extends Model {

    private String text;
    private float rotation;
    private static int radSize = 24;
    private float radPosition;
    private int itemPosition;
    private String[] radValues = {
            "Bankrupt",
            "600",
            "400",
            "300",
            "joker",
            "800",
            "350",
            "450",
            "700",
            "300",
            "600",
            "5000",
            "600",
            "500",
            "300",
            "Turn",
            "800",
            "550",
            "400",
            "300",
            "900",
            "500",
            "300",
            "900"
    };


    private KingsCupSpecialModel() {
    }

    public String getText() {
        return text;
    }

    private void setTasks(String text) {
        this.text = text;
    }


    private static KingsCupSpecialModel instance;

    public static KingsCupSpecialModel getInstance() {
        if (instance == null) {
            instance = new KingsCupSpecialModel();

        }
        FileHandle taskFile = Gdx.files.internal(com.gamefactoryx.cheers.tool.Configuration.getLanguage() + "/kingsCupSpecial/KingsCupSpecialDescription.txt");
        instance.setTasks(taskFile.readString("UTF-8"));


        return instance;
    }

    public static KingsCupSpecialModel getNewInstance() {
        instance = new KingsCupSpecialModel();
        FileHandle taskFile = Gdx.files.internal(com.gamefactoryx.cheers.tool.Configuration.getLanguage() + "/kingsCupSpecial/KingsCupSpecialDescription.txt");
        instance.setTasks(taskFile.readString("UTF-8"));
        instance.rotation = 0;


        return instance;
    }

    @Override
    protected void initRulesText() {
        rulesText = null;
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


    public float getItemPosition() {
        return itemPosition;
    }

    public void setRadPosition(float rotation) {
        this.radPosition = ((rotation < 0 ? 360 + (rotation % 360) : 360 - rotation % 360));
        itemPosition = Math.round(radPosition / (360 / radSize)) % radSize;
        Gdx.app.log("*************", rotation + "");
        Gdx.app.log("*************", radPosition + "");
        Gdx.app.log("*************", itemPosition + "");
        Gdx.app.log("*************", radValues[itemPosition]);

    }


}
