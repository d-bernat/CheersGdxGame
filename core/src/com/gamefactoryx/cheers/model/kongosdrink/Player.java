package com.gamefactoryx.cheers.model.kongosdrink;

import com.badlogic.gdx.graphics.Texture;
import com.gamefactoryx.cheers.tool.kongosdrink.Configuration;


/**
 * Created by Bernat on 18.07.2017.
 */
public class Player {
    public enum SEX {MALE, FEMALE, DONT_CARE}

    private int position;
    private boolean active;
    private float rotate;
    private AvatarType avatar;
    private String name;
    private SEX sex;
    private boolean finished;

    public Player( String name, SEX sex, AvatarType avatar) {
        this.avatar = avatar;
        this.name = name;
        this.sex = sex;
    }

    public int getPosition() {
        return position;
    }

    public float getNormPosition() {
        return (position - 1) * Configuration.KongosDrink.DISTANCE_BETWEEN_TWO_FIELDS;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public float getRotate() {
        return rotate;
    }

    public void setRotate(float rotate) {
        this.rotate = rotate;
    }

    public AvatarType getAvatar() {
        return avatar;
    }

    public void setAvatar(AvatarType avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SEX getSex() {
        return sex;
    }

    public void setSex(SEX sex) {
        this.sex = sex;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}
