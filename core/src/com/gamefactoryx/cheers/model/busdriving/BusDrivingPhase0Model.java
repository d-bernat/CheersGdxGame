package com.gamefactoryx.cheers.model.busdriving;

import com.gamefactoryx.cheers.tool.Configuration;

import java.util.List;


/**
 * Created by bernat on 16.05.2017.
 */
public final class BusDrivingPhase0Model {


    private int activePlayer;
    private Croupier croupier;
    private static BusDrivingPhase0Model instance;
    private int page = 1;
    private int maxPages;
    private boolean last, first;


    public static BusDrivingPhase0Model getInstance(){
        if(instance == null) {
            instance = new BusDrivingPhase0Model();
        }

        return instance;
    }

    public static BusDrivingPhase0Model getNewInstance(){
        instance = new BusDrivingPhase0Model();
        return instance;
    }

    private BusDrivingPhase0Model() {
        croupier = Croupier.getNewInstance();
        int maxPlayersProPage = Configuration.getMaxPlayersProConfigPage();
        maxPages = Configuration.getMaxPlayers() % maxPlayersProPage == 0 ?
                Configuration.getMaxPlayers() / maxPlayersProPage : Configuration.getMaxPlayers() / maxPlayersProPage + 1;
        first = true;

    }

    public List<Player> getPlayers() {
        return croupier.getInstance().getPlayers();
    }


    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
        first = page == 1;
        last = page == maxPages;
    }


    public int getMaxPages() {
        return maxPages;
    }

    public boolean isFirstPage() {
        return first;
    }

    public boolean isLastPage() {
        return last;
    }




    /* public int getActivePlayer() {
        return activePlayer;
    }

    public void setActivePlayer(int activePlayer) {
        if (activePlayer < getPlayers().size())
            this.activePlayer = activePlayer;
    }*/
}
