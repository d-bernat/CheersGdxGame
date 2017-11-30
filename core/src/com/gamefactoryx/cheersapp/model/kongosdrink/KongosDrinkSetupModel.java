package com.gamefactoryx.cheersapp.model.kongosdrink;

import com.badlogic.gdx.Gdx;
import com.gamefactoryx.cheersapp.tool.Configuration;


/**
 * Created by bernat on 16.05.2017.
 */
public final class KongosDrinkSetupModel extends com.gamefactoryx.cheersapp.model.Model {


    private static KongosDrinkSetupModel instance;

    public static KongosDrinkSetupModel getInstance(){
        if(instance == null) {
            instance = new KongosDrinkSetupModel();
        }

        return instance;
    }

    public static KongosDrinkSetupModel getNewInstance(){
        instance = new KongosDrinkSetupModel();
        return instance;
    }

    private KongosDrinkSetupModel() {

    }


    @Override
    protected void initRulesText() {
        rulesText = Gdx.files.internal(Configuration.getLanguage() + "/kongosdrink/rules.txt").readString();
    }
}
