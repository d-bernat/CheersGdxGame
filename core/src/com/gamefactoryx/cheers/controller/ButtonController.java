package com.gamefactoryx.cheers.controller;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by bernat on 28.04.2017.
 */
@SuppressWarnings("DefaultFileTemplate")
public abstract class ButtonController  extends InputAdapter {
    public abstract Sprite[][] getButtons();
    public abstract boolean[] getClicked();
    public abstract int getCountOfButtons();

}
