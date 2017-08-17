package com.gamefactoryx.cheers.tool.kongosdrink;

import com.gamefactoryx.cheers.model.kongosdrink.KongosDrinkMainModel;
import com.gamefactoryx.cheers.model.kongosdrink.Player;

public class CardTextParser {
    public enum SEX_OF_PLAYER {UNI, HOMO, HETERO}
    public enum PARAM_TYPE {POINTS, SIPS, TIMER}

    /*public static void main(String[] args) {
        String line = "--Player-, trinkt 2 mal mit dir!;1;2;0";
        line = replacePlayerNames(line);
        System.out.println(replace(line));
        System.out.println(getValue(PARAM_TYPE.POINTS,line));
        System.out.println(getValue(PARAM_TYPE.SIPS,line));
        System.out.println(getValue(PARAM_TYPE.TIMER,line));
    }*/

    /*public static SEX_OF_PLAYER getSexOfPlayer(String line) {
        if (line.contains("-oPlayer-")) return SEX_OF_PLAYER.UNI;
        else if (line.contains("--Player-")) return SEX_OF_PLAYER.HOMO;
        else if (line.contains("-+Player-")) return SEX_OF_PLAYER.HETERO;
        return SEX_OF_PLAYER.UNI;
    }

    private static String getRandomName(SEX_OF_PLAYER sexOfPlayer) {
        switch (sexOfPlayer) {
            case HOMO:
                return "Dusan";
            case HETERO:
                return "Kajka";
            case UNI:
            default:
                return "Samko";
        }
    }

    public static int getValue(PARAM_TYPE paramType, String line) {
        String[] params = line.split(";");
        if (params.length != 4) return 0;

        try {
            switch (paramType) {
                case POINTS:
                    return Integer.parseInt(params[1]);
                case SIPS:
                    return Integer.parseInt(params[2]);
                case TIMER:
                    return Integer.parseInt(params[3]);
                default:
                    return 0;
            }
        } catch (NumberFormatException e) {
            return 0;
        }

    }*/

    private static Player getActivePlayer(){
        return Configuration.getPlayers()[KongosDrinkMainModel.getInstance().getPlayerIndex()];
    }

    /*public static String replace(String text){
        return text.split(";")[0];
    }*/

    public static String replacePlayerNames(String text){

        text = text.replaceAll("-oPlayer-", Configuration.getRandomPlayer(KongosDrinkMainModel.getInstance().getPlayerIndex()).getName());
        text = text.replaceAll("-\\+Player-", Configuration.getRandomPlayer(KongosDrinkMainModel.getInstance().getPlayerIndex(),
                                                    getActivePlayer().getSex() == Player.SEX.MALE ? Player.SEX.FEMALE: Player.SEX.MALE).getName());
        text = text.replaceAll("--Player-", Configuration.getRandomPlayer(KongosDrinkMainModel.getInstance().getPlayerIndex(),
                                                    getActivePlayer().getSex() == Player.SEX.FEMALE ? Player.SEX.FEMALE: Player.SEX.MALE).getName());
        text = text.replaceAll("-aPlayer-", getActivePlayer().getName());
        return text;
    }
}
