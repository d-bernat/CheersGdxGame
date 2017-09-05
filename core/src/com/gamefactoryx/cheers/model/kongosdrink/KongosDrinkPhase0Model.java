package com.gamefactoryx.cheers.model.kongosdrink;

import com.gamefactoryx.cheers.tool.kongosdrink.Configuration;

import java.util.List;


/**
 * Created by bernat on 16.05.2017.
 */
public final class KongosDrinkPhase0Model {


    private int activePlayer;
    private int page = 1;
    private int maxPages;
//    private Croupier croupier;
    private static KongosDrinkPhase0Model instance;


    public static KongosDrinkPhase0Model getInstance(){
        if(instance == null) {
            instance = new KongosDrinkPhase0Model();
        }

        return instance;
    }

    public static KongosDrinkPhase0Model getNewInstance(){
        instance = new KongosDrinkPhase0Model();
        return instance;
    }

    private KongosDrinkPhase0Model() {
        for(int i = 0; i < Configuration.getPlayers().size(); i++)
            Configuration.getPlayers().get(i).setEnable(i < 2);
        int maxPlayersProPage = com.gamefactoryx.cheers.tool.Configuration.getMaxPlayersProConfigPage();
        maxPages = Configuration.getMaxPlayers() % maxPlayersProPage == 0 ?
                Configuration.getMaxPlayers() / maxPlayersProPage : Configuration.getMaxPlayers() / maxPlayersProPage + 1;

    }

    public List<Player> getPlayers() {
        return Configuration.getPlayers();
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getMaxPages() {
        return maxPages;
    }

    /* public int getActivePlayer() {
        return activePlayer;
    }

    public void setActivePlayer(int activePlayer) {
        if (activePlayer < getPlayers().size())
            this.activePlayer = activePlayer;
    }*/
}
