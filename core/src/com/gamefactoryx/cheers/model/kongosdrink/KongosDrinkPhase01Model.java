package com.gamefactoryx.cheers.model.kongosdrink;

import com.gamefactoryx.cheers.model.Model;
import com.gamefactoryx.cheers.tool.kongosdrink.Configuration;

import java.util.List;


/**
 * Created by bernat on 16.05.2017.
 */
public final class KongosDrinkPhase01Model extends Model {


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
    public String getRulesText() {
        return null;
    }
}
