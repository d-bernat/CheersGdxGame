package com.gamefactoryx.cheers.controller;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.List;

/**
 * Created by bernat on 28.04.2017.
 */
@SuppressWarnings("DefaultFileTemplate")
public abstract class TextController extends ButtonController {
    public abstract String[] getText();
    public abstract Sprite getTextBox();

}
