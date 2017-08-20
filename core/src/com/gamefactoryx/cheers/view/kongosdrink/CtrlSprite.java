package com.gamefactoryx.cheers.view.kongosdrink;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class CtrlSprite extends Sprite {
    private int type;
    private  boolean active;
    private boolean clicked;


    public CtrlSprite(Texture texture){
        super(texture);
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isClicked() {
        return clicked;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }
}
