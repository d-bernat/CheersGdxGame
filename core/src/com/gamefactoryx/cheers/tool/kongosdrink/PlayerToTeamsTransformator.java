package com.gamefactoryx.cheers.tool.kongosdrink;

import com.gamefactoryx.cheers.model.kongosdrink.Player;
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
                return players;
        }
    }

    private static List<Player> getTeamVsTeam(List<Player> players) {
        List<Player> rs = new ArrayList<>();
        Collections.shuffle(players);
        List<Player> first = players.subList(0, players.size() / 2 - 1);
        List<Player> second = players.subList(players.size() / 2, players.size() - 1);

        //Player firstPlayer = new Player(Player.Type.TEAM, null);
        //Player secondPlayer = new Player(Player.Type.TEAM, null);
        return rs;
    }

    private static List<Player> getTeamofTwo(List<Player> players) {
        List<Player> rs = new ArrayList<>();
        return rs;
    }
}
