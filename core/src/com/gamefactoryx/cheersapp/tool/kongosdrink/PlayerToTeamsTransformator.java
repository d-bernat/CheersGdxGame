package com.gamefactoryx.cheersapp.tool.kongosdrink;

import com.gamefactoryx.cheersapp.tool.FunnyNameGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class PlayerToTeamsTransformator {

    public static List<com.gamefactoryx.cheersapp.model.kongosdrink.Player> toTeams(List<com.gamefactoryx.cheersapp.model.kongosdrink.Player> players) {
        switch (com.gamefactoryx.cheersapp.tool.kongosdrink.Configuration.getInstance().getGameType()) {
            case TEAMOFTWO_VS_TEAMOFTWO:
                return getTeamofTwo(players);
            case TEAM_VS_TEAM:
                return getTeamVsTeam(players);
            default:
                return getSinglePlayers(players);
        }
    }

    private static List<com.gamefactoryx.cheersapp.model.kongosdrink.Player> getSinglePlayers(List<com.gamefactoryx.cheersapp.model.kongosdrink.Player> players) {

        List<com.gamefactoryx.cheersapp.model.kongosdrink.Player> tp = new ArrayList<>();
        for(com.gamefactoryx.cheersapp.model.kongosdrink.Player player: players){
            if(player.isEnable()) tp.add(player);
        }
        return tp;

    }

    private static List<com.gamefactoryx.cheersapp.model.kongosdrink.Player> getTeamVsTeam(List<com.gamefactoryx.cheersapp.model.kongosdrink.Player> players) {
        List<com.gamefactoryx.cheersapp.model.kongosdrink.Player> rs = new ArrayList<>();
        Collections.shuffle(players);


        List<com.gamefactoryx.cheersapp.model.kongosdrink.Player>[] splitted = splitPlayerList(players);

        com.gamefactoryx.cheersapp.model.kongosdrink.Player[] newPlayers = {new com.gamefactoryx.cheersapp.model.kongosdrink.Player(new com.gamefactoryx.cheersapp.model.Subject(FunnyNameGenerator.getTeamFunnyName(0), com.gamefactoryx.cheersapp.model.Subject.Sex.DONT_CARE, com.gamefactoryx.cheersapp.model.Subject.Type.TEAM,
                splitted[0].get(0).getAvatar())),
                new com.gamefactoryx.cheersapp.model.kongosdrink.Player(new com.gamefactoryx.cheersapp.model.Subject(FunnyNameGenerator.getTeamFunnyName(1), com.gamefactoryx.cheersapp.model.Subject.Sex.DONT_CARE, com.gamefactoryx.cheersapp.model.Subject.Type.TEAM,
                        splitted[1].get(0).getAvatar()))};

        for (com.gamefactoryx.cheersapp.model.kongosdrink.Player player : splitted[0]) {
            com.gamefactoryx.cheersapp.model.Subject s = player.getSubjects().get(0);
            newPlayers[0].addSubject(s);
            newPlayers[0].setEnable(true);
        }
        rs.add(newPlayers[0]);

        for (com.gamefactoryx.cheersapp.model.kongosdrink.Player player : splitted[1]) {
            com.gamefactoryx.cheersapp.model.Subject s = player.getSubjects().get(0);
            newPlayers[1].addSubject(s);
            newPlayers[1].setEnable(true);
        }
        rs.add(newPlayers[1]);

        return rs;
    }

    private static List<com.gamefactoryx.cheersapp.model.kongosdrink.Player> getTeamofTwo(List<com.gamefactoryx.cheersapp.model.kongosdrink.Player> players) {
        List<com.gamefactoryx.cheersapp.model.kongosdrink.Player> rs = new ArrayList<>();
        Collections.shuffle(players);

        List<com.gamefactoryx.cheersapp.model.kongosdrink.Player>[] splitted = splitPlayerList(players);


        if (splitted[0].size() == splitted[1].size()) {
            for (int i = 0; i < splitted[0].size(); i++) {
                com.gamefactoryx.cheersapp.model.kongosdrink.Player newPlayer = new com.gamefactoryx.cheersapp.model.kongosdrink.Player(new com.gamefactoryx.cheersapp.model.Subject(FunnyNameGenerator.getTeamFunnyName(i), com.gamefactoryx.cheersapp.model.Subject.Sex.DONT_CARE,
                        com.gamefactoryx.cheersapp.model.Subject.Type.TEAM, splitted[0].get(i).getAvatar()));
                newPlayer.addSubject(splitted[0].get(i).getSubjects().get(0));
                newPlayer.addSubject(splitted[1].get(i).getSubjects().get(0));
                newPlayer.setEnable(true);
                rs.add(newPlayer);
            }

        } else if (splitted[0].size() < splitted[1].size()) {
            for (int i = 0; i < splitted[0].size(); i++) {
                com.gamefactoryx.cheersapp.model.kongosdrink.Player newPlayer = new com.gamefactoryx.cheersapp.model.kongosdrink.Player(new com.gamefactoryx.cheersapp.model.Subject(FunnyNameGenerator.getTeamFunnyName(i), com.gamefactoryx.cheersapp.model.Subject.Sex.DONT_CARE,
                        com.gamefactoryx.cheersapp.model.Subject.Type.TEAM, splitted[0].get(i).getAvatar()));
                newPlayer.addSubject(splitted[0].get(i).getSubjects().get(0));
                newPlayer.addSubject(splitted[1].get(i).getSubjects().get(0));
                newPlayer.setEnable(true);
                rs.add(newPlayer);
            }

            com.gamefactoryx.cheersapp.model.kongosdrink.Player newPlayer = new com.gamefactoryx.cheersapp.model.kongosdrink.Player(new com.gamefactoryx.cheersapp.model.Subject(FunnyNameGenerator.getTeamFunnyName(splitted[1].size()), com.gamefactoryx.cheersapp.model.Subject.Sex.DONT_CARE,
                    com.gamefactoryx.cheersapp.model.Subject.Type.TEAM, splitted[1].get(splitted[1].size() - 1).getAvatar()));
            newPlayer.addSubject(splitted[1].get(splitted[1].size() - 1).getSubjects().get(0));
            newPlayer.setEnable(true);
            rs.add(newPlayer);

        } else {
            for (int i = 0; i < splitted[1].size(); i++) {
                com.gamefactoryx.cheersapp.model.Subject s = splitted[0].get(i).getSubjects().get(0);
                com.gamefactoryx.cheersapp.model.kongosdrink.Player newPlayer = new com.gamefactoryx.cheersapp.model.kongosdrink.Player(new com.gamefactoryx.cheersapp.model.Subject(FunnyNameGenerator.getTeamFunnyName(i + 1), com.gamefactoryx.cheersapp.model.Subject.Sex.DONT_CARE,
                        com.gamefactoryx.cheersapp.model.Subject.Type.TEAM, splitted[0].get(i).getAvatar()));
                newPlayer.addSubject(splitted[0].get(i).getSubjects().get(0));
                newPlayer.addSubject(splitted[1].get(i).getSubjects().get(0));
                newPlayer.setEnable(true);
                rs.add(newPlayer);
            }
            com.gamefactoryx.cheersapp.model.kongosdrink.Player newPlayer = new com.gamefactoryx.cheersapp.model.kongosdrink.Player(new com.gamefactoryx.cheersapp.model.Subject(FunnyNameGenerator.getTeamFunnyName(splitted[0].size()), com.gamefactoryx.cheersapp.model.Subject.Sex.DONT_CARE,
                    com.gamefactoryx.cheersapp.model.Subject.Type.TEAM, splitted[0].get(splitted[0].size() - 1).getAvatar()));
            newPlayer.addSubject(splitted[0].get(splitted[0].size() - 1).getSubjects().get(0));
            newPlayer.setEnable(true);
            rs.add(newPlayer);

        }


        return rs;
    }


    private static List<com.gamefactoryx.cheersapp.model.kongosdrink.Player>[] splitPlayerList(List<com.gamefactoryx.cheersapp.model.kongosdrink.Player> playerList) {
        List<com.gamefactoryx.cheersapp.model.kongosdrink.Player> first = new ArrayList<>();
        List<com.gamefactoryx.cheersapp.model.kongosdrink.Player> second = new ArrayList<>();

        byte flag = 0;
        for (com.gamefactoryx.cheersapp.model.kongosdrink.Player player : playerList) {
            if (player.isEnable())
                if (flag == 0) {
                    flag = 1;
                    first.add(player);
                } else {
                    flag = 0;
                    second.add(player);
                }
        }

        List<com.gamefactoryx.cheersapp.model.kongosdrink.Player>[] rs = new ArrayList[2];

        rs[0] = first;
        rs[1] = second;

        return rs;
    }
}
