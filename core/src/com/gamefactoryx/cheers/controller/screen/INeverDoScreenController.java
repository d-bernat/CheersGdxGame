package com.gamefactoryx.cheers.controller.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.gamefactoryx.cheers.controller.ButtonController;
import com.gamefactoryx.cheers.controller.TextController;
import com.gamefactoryx.cheers.manager.ScreenEnum;
import com.gamefactoryx.cheers.manager.ScreenManager;
import com.gamefactoryx.cheers.tool.Orientation;
import com.gamefactoryx.cheers.tool.Resolution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by bernat on 28.04.2017.
 */
@SuppressWarnings("DefaultFileTemplate")
public class INeverDoScreenController extends TextController {

    private boolean[] clicked;
    private Sprite[][] buttons;
    private Sprite textBox;


    public INeverDoScreenController() {
        initButtons();
        initTextBox();
    }

    @Override
    public Sprite[][] getButtons() {
        return buttons;
    }

    @Override
    public boolean[] getClicked() {
        return clicked;
    }

    @Override
    public int getCountOfButtons() {
        return buttons.length;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        for (int i = 0; i < buttons.length; i++) {
            clicked[i] = (screenX >= buttons[i][0].getX() &&
                    screenX <= buttons[i][0].getX() + buttons[i][0].getWidth() &&
                    Resolution.getGameWorldHeightPortrait() - screenY >= buttons[i][0].getY() &&
                    Resolution.getGameWorldHeightPortrait() - screenY <= buttons[i][0].getY() + buttons[i][0].getHeight() &&
                    Orientation.getOrientation() == Input.Orientation.Portrait
                    ||
                    screenX >= buttons[i][0].getX() &&
                            screenX <= buttons[i][0].getX() + buttons[i][0].getWidth() &&
                            Resolution.getGameWorldHeightLandscape() - screenY >= buttons[i][0].getY() &&
                            Resolution.getGameWorldHeightLandscape() - screenY <= buttons[i][0].getY() + buttons[i][0].getHeight() &&
                            Orientation.getOrientation() == Input.Orientation.Landscape);
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        for (int i = 0; i < buttons.length; i++) {
            if (getClicked()[i])
                switch (i) {
                    case 0:
                        ScreenManager.getInstance().showScreen(ScreenEnum.MAIN_SCREEN);
                }

            getClicked()[i] = false;
        }

        if(screenX >= getTextBox().getX() && screenX <= getTextBox().getX() + getTextBox().getWidth() &&
                screenY >= getTextBox().getY() && screenY <= getTextBox().getY() + getTextBox().getHeight()) {
            ScreenManager.getInstance().showScreen(ScreenEnum.I_NEVER_DO_SCREEN);
        }

        return true;
    }


    private void initButtons() {
        buttons = new Sprite[1][2];

        getButtons()[0][0] = new Sprite(new Texture("base/button_free_back_to_main.png"));
        getButtons()[0][1] = new Sprite(new Texture("base/button_clicked_back_to_main.png"));

        getButtons()[0][0].setSize(Resolution.getGameWorldWidthPortrait() * 0.2f,
                Resolution.getGameWorldHeightPortrait() * 0.2f * Resolution.getAspectRatio());
        getButtons()[0][1].setSize(Resolution.getGameWorldWidthPortrait() * 0.2f,
                Resolution.getGameWorldHeightPortrait() * 0.2f * Resolution.getAspectRatio());

        clicked = new boolean[getCountOfButtons()];
    }

    private void initTextBox(){
        textBox = new Sprite(new Texture("base/TextBoxPortrait.png"));

    }

    @Override
    public String[] getText() {
        FileHandle taskFile = Gdx.files.internal("base/tasks.txt");
        String[] tasks = taskFile.readString().split("\\n");

        int index = (int)((Math.random()) * tasks.length );
        return tasks[index].split(":");
    }

    @Override
    public Sprite getTextBox() {
       return textBox;
    }


}
