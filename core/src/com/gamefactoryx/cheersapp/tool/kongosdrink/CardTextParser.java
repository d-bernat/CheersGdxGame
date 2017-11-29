package com.gamefactoryx.cheersapp.tool.kongosdrink;

import com.gamefactoryx.cheersapp.model.Subject;
import com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel;

public class CardTextParser {
    private static com.gamefactoryx.cheersapp.model.kongosdrink.Player getActivePlayer(){
        return com.gamefactoryx.cheersapp.tool.kongosdrink.Configuration.getInstance().getPlayers().get(KongosDrinkMainModel.getInstance().getPlayerIndex());
    }

    public static String replacePlayerNames(String text){

        text = text.replaceAll("-oPlayer-", com.gamefactoryx.cheersapp.tool.kongosdrink.Configuration.getInstance().getRandomPlayer(KongosDrinkMainModel.getInstance().getPlayerIndex()).getName());
        text = text.replaceAll("-\\+Player-", com.gamefactoryx.cheersapp.tool.kongosdrink.Configuration.getInstance().getRandomPlayer(KongosDrinkMainModel.getInstance().getPlayerIndex(),
                                                    getActivePlayer().getSex() == Subject.Sex.MALE ? Subject.Sex.FEMALE: Subject.Sex.MALE).getName());
        text = text.replaceAll("--Player-", com.gamefactoryx.cheersapp.tool.kongosdrink.Configuration.getInstance().getRandomPlayer(KongosDrinkMainModel.getInstance().getPlayerIndex(),
                                                    getActivePlayer().getSex() == Subject.Sex.FEMALE ? Subject.Sex.FEMALE: Subject.Sex.MALE).getName());
        text = text.replaceAll("-aPlayer-", getActivePlayer().getName());
        return text;
    }
}
