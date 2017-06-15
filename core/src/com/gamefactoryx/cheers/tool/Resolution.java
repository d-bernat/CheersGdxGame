package com.gamefactoryx.cheers.tool;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

/**
 * Created by bernat on 28.04.2017.
 */
@SuppressWarnings("DefaultFileTemplate")
public class Resolution {
    private static float gameWorldWidthPortrait;
    private static float gameWorldHeightPortrait;
    private static float gameWorldWidthLandscape;
    private static float gameWorldHeightLandscape;

    public static float getGameWorldWidthPortrait() {
        return gameWorldWidthPortrait;
    }

    public static float getGameWorldHeightPortrait() {
        return gameWorldHeightPortrait;
    }

    public static float getGameWorldWidthLandscape() {
        return gameWorldWidthLandscape;
    }

    public static float getGameWorldHeightLandscape() {
        return gameWorldHeightLandscape;
    }

    public static void setResolution(){
        if ((Gdx.input.getNativeOrientation() == Input.Orientation.Portrait)) {
            gameWorldWidthPortrait = Gdx.graphics.getWidth();
            gameWorldHeightPortrait = Gdx.graphics.getHeight();
            gameWorldWidthLandscape = Gdx.graphics.getHeight();
            gameWorldHeightLandscape = Gdx.graphics.getWidth();
        } else {
            gameWorldWidthPortrait = Gdx.graphics.getHeight();
            gameWorldHeightPortrait = Gdx.graphics.getWidth();
            gameWorldWidthLandscape = Gdx.graphics.getWidth();
            gameWorldHeightLandscape = Gdx.graphics.getHeight();
        }
    }

    public static float getAspectRatio(){
        float aspectRatio;
        if (Gdx.graphics.getWidth() < Gdx.graphics.getHeight())
        //if (Orientation.getOrientation() == Input.Orientation.Portrait)
            aspectRatio = (float) Gdx.graphics.getWidth() / (float) Gdx.graphics.getHeight();
        else
            aspectRatio = (float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth();

        return aspectRatio;
    }



}
