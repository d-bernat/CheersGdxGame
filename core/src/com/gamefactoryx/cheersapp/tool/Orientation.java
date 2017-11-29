package com.gamefactoryx.cheersapp.tool;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

/**
 * Created by bernat on 28.04.2017.
 */
@SuppressWarnings("DefaultFileTemplate")
public class Orientation {
    public static  Input.Orientation getOrientation() {
        int rotation = Gdx.input.getRotation();
        if ((Gdx.input.getNativeOrientation() == Input.Orientation.Portrait && (rotation == 0 || rotation == 180)) ||
                (Gdx.input.getNativeOrientation() == Input.Orientation.Landscape && (rotation == 90 || rotation == 270))) {
            return Input.Orientation.Portrait;
        } else
            return Input.Orientation.Landscape;

    }

}
