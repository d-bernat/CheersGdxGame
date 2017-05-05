package com.gamefactoryx.cheers.controller;

import com.badlogic.gdx.InputAdapter;
import com.gamefactoryx.cheers.view.AbstractScreen;

/**
 * Created by bernat on 05.05.2017.
 */
@SuppressWarnings("DefaultFileTemplate")
public abstract class AbstractController extends InputAdapter {

    public abstract AbstractScreen getView();
}
