package com.gamefactoryx.cheers.controller.screen;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.gamefactoryx.cheers.controller.ButtonController;
import com.gamefactoryx.cheers.controller.TextController;
import com.gamefactoryx.cheers.manager.ScreenEnum;
import com.gamefactoryx.cheers.manager.ScreenManager;
import com.gamefactoryx.cheers.tool.Orientation;
import com.gamefactoryx.cheers.tool.Resolution;

import java.util.List;

/**
 * Created by bernat on 28.04.2017.
 */
@SuppressWarnings("DefaultFileTemplate")
public class MainScreenController extends TextController {

    private final int COUNT_OF_BUTTONS = 5;
    private boolean[] clicked;
    private Sprite[][] buttons;


    public MainScreenController(){
        initButtons();
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
    public int getCountOfButtons(){
        return buttons.length;
    }



    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        for (int i = 0; i < COUNT_OF_BUTTONS; i++) {
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
            if (clicked[i])
                switch (i) {
                    case 0:
                        ScreenManager.getInstance().showScreen(ScreenEnum.NEW_GAME_SCREEN);
                        break;
                    default:
                        ScreenManager.getInstance().showScreen(ScreenEnum.MAIN_SCREEN);
                }
            clicked[i] = false;
        }


        return true;
    }

    private void initButtons() {
        buttons = new Sprite[COUNT_OF_BUTTONS][2];

        buttons[0][0] = new Sprite(new Texture("mainScreen/sekt_button_free_new_game.png"));
        buttons[0][1] = new Sprite(new Texture("mainScreen/sekt_button_clicked_new_game.png"));
        buttons[1][0] = new Sprite(new Texture("mainScreen/sekt_button_free_add_task.png"));
        buttons[1][1] = new Sprite(new Texture("mainScreen/sekt_button_clicked_add_task.png"));
        buttons[2][0] = new Sprite(new Texture("mainScreen/sekt_button_free_settings.png"));
        buttons[2][1] = new Sprite(new Texture("mainScreen/sekt_button_clicked_settings.png"));
        buttons[3][0] = new Sprite(new Texture("mainScreen/sekt_button_free_help.png"));
        buttons[3][1] = new Sprite(new Texture("mainScreen/sekt_button_clicked_help.png"));
        buttons[4][0] = new Sprite(new Texture("mainScreen/sekt_button_free_hall_of_fame.png"));
        buttons[4][1] = new Sprite(new Texture("mainScreen/sekt_button_clicked_hall_of_fame.png"));



        for (int i = 0; i < COUNT_OF_BUTTONS; i++)
            for (int j = 0; j < 2; j++) {
                buttons[i][j].setSize(Resolution.getGameWorldWidthPortrait() * 0.3f,
                        Resolution.getGameWorldHeightPortrait() * 0.3f * Resolution.getAspectRatio());
            }
        clicked = new boolean[COUNT_OF_BUTTONS];
    }

    @Override
    public String[] getText() {
        return null;
    }

    @Override
    public Sprite getTextBox() {
        return null;
    }
}
