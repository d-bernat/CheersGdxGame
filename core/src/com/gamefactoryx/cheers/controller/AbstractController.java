package com.gamefactoryx.cheers.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.gamefactoryx.cheers.view.AbstractScreen;

/**
 * Created by bernat on 05.05.2017.
 */
@SuppressWarnings("DefaultFileTemplate")
abstract class AbstractController extends InputAdapter {

    private final AbstractScreen screen;

    AbstractController(final AbstractScreen screen){
        this.screen = screen;
        Gdx.input.setInputProcessor(this);

    }

    AbstractScreen getScreen(){ return screen; }

}
