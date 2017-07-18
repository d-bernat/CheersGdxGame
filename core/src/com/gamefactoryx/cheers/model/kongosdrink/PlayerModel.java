package com.gamefactoryx.cheers.model.kongosdrink;

import com.gamefactoryx.cheers.tool.Configuration;

/**
 * Created by Bernat on 18.07.2017.
 */
public class PlayerModel {
    private int screenIndex;
    private int screenXCoor;
    private int position;
    private boolean active;
    private float rotate;

    public int getScreenIndex() {
        return screenIndex;
    }

    public void setScreenIndex(int screenIndex) {
        this.screenIndex = screenIndex;
    }

    public int getScreenXCoor() {
        return screenXCoor;
    }

    public void setScreenXCoor(int screenXCoor) {
        this.screenXCoor = screenXCoor;
    }

    public int getPosition() {
        return position;
    }

    public float getNormPosition(){
        return (position - 1) * Configuration.DISTANCE_BETWEEN_TWO_FIELDS;
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
}
