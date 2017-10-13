package com.gamefactoryx.cheers.tool.kongosdrink;

import com.gamefactoryx.cheers.model.Subject;
import com.gamefactoryx.cheers.model.kongosdrink.Player;
import com.gamefactoryx.cheers.tool.FunnyNameGenerator;
import com.gamefactoryx.cheers.tool.kongosdrink.Configuration.GameTypeEnum;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class PlayerToTeamsTransformator {

    public static List<Player> toTeams(List<Player> players) {
        switch (Configuration.getInstance().getGameType()) {
            case TEAMOFTWO_VS_TEAMOFTWO:
                return getTeamofTwo(players);
            case TEAM_VS_TEAM:
                return getTeamVsTeam(players);
            default:
                return getSinglePlayers(players);
        }
    }

    private static List<Player> getSinglePlayers(List<Player> players) {

        List<Player> tp = new ArrayList<>();
        for(Player player: players){
            if(player.isEnable()) tp.add(player);
        }
        return tp;

    }

    private static List<Player> getTeamVsTeam(List<Player> players) {
        List<Player> rs = new ArrayList<>();
        Collections.shuffle(players);


        List<Player>[] splitted = splitPlayerList(players);

        Player[] newPlayers = {new Player(new Subject(FunnyNameGenerator.getTeamFunnyName(0), Subject.Sex.DONT_CARE, Subject.Type.TEAM,
                splitted[0].get(0).getAvatar())),
                new Player(new Subject(FunnyNameGenerator.getTeamFunnyName(1), Subject.Sex.DONT_CARE, Subject.Type.TEAM,
                        splitted[1].get(0).getAvatar()))};

        for (Player player : splitted[0]) {
            Subject s = player.getSubjects().get(0);
            newPlayers[0].addSubject(s);
            newPlayers[0].setEnable(true);
        }
        rs.add(newPlayers[0]);

        for (Player player : splitted[1]) {
            Subject s = player.getSubjects().get(0);
            newPlayers[1].addSubject(s);
            newPlayers[1].setEnable(true);
        }
        rs.add(newPlayers[1]);

        return rs;
    }

    private static List<Player> getTeamofTwo(List<Player> players) {
        List<Player> rs = new ArrayList<>();
        Collections.shuffle(players);

        List<Player>[] splitted = splitPlayerList(players);


        if (splitted[0].size() == splitted[1].size()) {
            for (int i = 0; i < splitted[0].size(); i++) {
                Player newPlayer = new Player(new Subject(FunnyNameGenerator.getTeamFunnyName(i), Subject.Sex.DONT_CARE,
                        Subject.Type.TEAM, splitted[0].get(i).getAvatar()));
                newPlayer.addSubject(splitted[0].get(i).getSubjects().get(0));
                newPlayer.addSubject(splitted[1].get(i).getSubjects().get(0));
                newPlayer.setEnable(true);
                rs.add(newPlayer);
            }

        } else if (splitted[0].size() < splitted[1].size()) {
            for (int i = 0; i < splitted[0].size(); i++) {
                Player newPlayer = new Player(new Subject(FunnyNameGenerator.getTeamFunnyName(i), Subject.Sex.DONT_CARE,
                        Subject.Type.TEAM, splitted[0].get(0).getAvatar()));
                newPlayer.addSubject(splitted[0].get(i).getSubjects().get(0));
                newPlayer.addSubject(splitted[1].get(i).getSubjects().get(0));
                newPlayer.setEnable(true);
                rs.add(newPlayer);
            }

            Player newPlayer = new Player(new Subject("Team " + splitted[1].size(), Subject.Sex.DONT_CARE,
                    Subject.Type.TEAM, splitted[0].get(0).getAvatar()));
            newPlayer.addSubject(splitted[1].get(splitted[1].size() - 1).getSubjects().get(0));
            newPlayer.setEnable(true);
            rs.add(newPlayer);

        } else {
            for (int i = 0; i < splitted[1].size(); i++) {
                Subject s = splitted[0].get(i).getSubjects().get(0);
                Player newPlayer = new Player(new Subject("Team " + (i + 1), Subject.Sex.DONT_CARE,
                        Subject.Type.TEAM, splitted[0].get(0).getAvatar()));
                newPlayer.addSubject(splitted[0].get(i).getSubjects().get(0));
                newPlayer.addSubject(splitted[1].get(i).getSubjects().get(0));
                newPlayer.setEnable(true);
                rs.add(newPlayer);
            }
            Player newPlayer = new Player(new Subject("Team " + splitted[0].size(), Subject.Sex.DONT_CARE,
                    Subject.Type.TEAM, splitted[1].get(0).getAvatar()));
            newPlayer.addSubject(splitted[0].get(splitted[0].size() - 1).getSubjects().get(0));
            newPlayer.setEnable(true);
            rs.add(newPlayer);

        }


        return rs;
    }


    private static List<Player>[] splitPlayerList(List<Player> playerList) {
        List<Player> first = new ArrayList<>();
        List<Player> second = new ArrayList<>();

        byte flag = 0;
        for (Player player : playerList) {
            if (player.isEnable())
                if (flag == 0) {
                    flag = 1;
                    first.add(player);
                } else {
                    flag = 0;
                    second.add(player);
                }
        }

        List<Player>[] rs = new ArrayList[2];

        rs[0] = first;
        rs[1] = second;

        return rs;
    }
}
