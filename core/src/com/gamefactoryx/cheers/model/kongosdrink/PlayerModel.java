package com.gamefactoryx.cheers.model.kongosdrink;

import com.badlogic.gdx.graphics.Texture;
import com.gamefactoryx.cheers.tool.Configuration;

/**
 * Created by Bernat on 18.07.2017.
 */
public class PlayerModel {
    private int position;
    private boolean active;
    private float rotate;
    private Texture avatar;

    public int getPosition() {
        return position;
    }

    public float getNormPosition(){
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

    public Texture getAvatar() {
        return avatar;
    }

    public void setAvatar(Texture avatar) {
        this.avatar = avatar;
    }
}
