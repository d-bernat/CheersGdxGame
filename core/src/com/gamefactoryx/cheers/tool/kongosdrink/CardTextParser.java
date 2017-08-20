package com.gamefactoryx.cheers.tool.kongosdrink;

import com.gamefactoryx.cheers.model.Subject;
import com.gamefactoryx.cheers.model.kongosdrink.KongosDrinkMainModel;
import com.gamefactoryx.cheers.model.kongosdrink.Player;

public class CardTextParser {
    private static Player getActivePlayer(){
        return Configuration.getPlayers().get(KongosDrinkMainModel.getInstance().getPlayerIndex());
    }

    public static String replacePlayerNames(String text){

        text = text.replaceAll("-oPlayer-", Configuration.getRandomPlayer(KongosDrinkMainModel.getInstance().getPlayerIndex()).getName());
        text = text.replaceAll("-\\+Player-", Configuration.getRandomPlayer(KongosDrinkMainModel.getInstance().getPlayerIndex(),
                                                    getActivePlayer().getSex() == Subject.Sex.MALE ? Subject.Sex.FEMALE: Subject.Sex.MALE).getName());
        text = text.replaceAll("--Player-", Configuration.getRandomPlayer(KongosDrinkMainModel.getInstance().getPlayerIndex(),
                                                    getActivePlayer().getSex() == Subject.Sex.FEMALE ? Subject.Sex.FEMALE: Subject.Sex.MALE).getName());
        text = text.replaceAll("-aPlayer-", getActivePlayer().getName());
        return text;
    }
}
