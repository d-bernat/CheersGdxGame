package com.gamefactoryx.cheersapp.model.kongosdrink;

import com.badlogic.gdx.Gdx;


/**
 * Created by bernat on 16.05.2017.
 */
public final class KongosDrinkPhase01Model extends com.gamefactoryx.cheersapp.model.Model {


    private int playerToConfigureIndex;


    private static KongosDrinkPhase01Model instance;


    public static KongosDrinkPhase01Model getInstance(){
        if(instance == null) {
            instance = new KongosDrinkPhase01Model();
        }

        return instance;
    }

    public static KongosDrinkPhase01Model getNewInstance(){
        instance = new KongosDrinkPhase01Model();
        return instance;
    }

    private KongosDrinkPhase01Model() {
    }

    public int getPlayerToConfigureIndex() {
        return playerToConfigureIndex;
    }

    public void setPlayerToConfigureIndex(int playerToConfigureIndex) {
        this.playerToConfigureIndex = playerToConfigureIndex;
    }


    @Override
    protected void initRulesText() {
        rulesText = Gdx.files.internal(com.gamefactoryx.cheersapp.tool.Configuration.getLanguage() + "/kongosdrink/rules.txt").readString();
    }
}
