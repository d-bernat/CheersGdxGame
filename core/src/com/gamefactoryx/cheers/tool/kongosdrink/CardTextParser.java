package com.gamefactoryx.cheers.tool.kongosdrink;

public class CardTextParser {
    public enum SEX_OF_PLAYER {UNI, HOMO, HETERO}
    public enum PARAM_TYPE {POINTS, SIPS, TIMER}

    public static void main(String[] args) {
        String line = "--Player-, trinkt 2 mal mit dir!;1;2;0";
        line = replacePlayerNames(line);
        System.out.println(getTask(line));
        System.out.println(getValue(PARAM_TYPE.POINTS,line));
        System.out.println(getValue(PARAM_TYPE.SIPS,line));
        System.out.println(getValue(PARAM_TYPE.TIMER,line));
    }

    public static SEX_OF_PLAYER getSexOfPlayer(String line) {
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

    }

    public static String getTask(String line){
        return line.split(";")[0];
    }

    private static String replacePlayerNames(String line){
        line = line.replaceAll("-oPlayer-", getRandomName(getSexOfPlayer(line)));
        line = line.replaceAll("-\\+Player-", getRandomName(getSexOfPlayer(line)));
        line = line.replaceAll("--Player-", getRandomName(getSexOfPlayer(line)));
        return line;
    }
}
