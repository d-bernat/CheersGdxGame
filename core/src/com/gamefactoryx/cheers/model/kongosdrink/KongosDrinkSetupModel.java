package com.gamefactoryx.cheers.model.kongosdrink;

import com.badlogic.gdx.Gdx;
import com.gamefactoryx.cheers.model.Model;


/**
 * Created by bernat on 16.05.2017.
 */
public final class KongosDrinkSetupModel extends Model {


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
        rulesText = Gdx.files.internal(com.gamefactoryx.cheers.tool.Configuration.getLanguage() + "/kongosdrink/rules.txt").readString();
    }
}
