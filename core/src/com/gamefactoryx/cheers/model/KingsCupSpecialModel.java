package com.gamefactoryx.cheers.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;

import java.io.UnsupportedEncodingException;

/**
 * Created by bernat on 05.05.2017.
 */
@SuppressWarnings("DefaultFileTemplate")
public class KingsCupSpecialModel extends Model {

    private String text;
    private float rotation;
    private static int radSize = 15;
    private float radPosition;
    private int itemPosition;
    private boolean clicked;

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
        Gdx.app.log("*************", radValues[itemPosition]);

    }

    public boolean isClicked() {
        return clicked;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }
}
