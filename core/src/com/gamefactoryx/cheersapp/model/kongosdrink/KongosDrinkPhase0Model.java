package com.gamefactoryx.cheersapp.model.kongosdrink;

import com.badlogic.gdx.Gdx;
import com.gamefactoryx.cheersapp.tool.kongosdrink.Configuration;

import java.util.List;


/**
 * Created by bernat on 16.05.2017.
 */
public final class KongosDrinkPhase0Model extends com.gamefactoryx.cheersapp.model.Model {


    private int activePlayer;
    private int page = 1;
    private int maxPages;
    private boolean last, first;


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
        for(int i = 0; i < Configuration.getInstance().getPlayers().size(); i++)
            Configuration.getInstance().getPlayers().get(i).setEnable(i < 2);
        int maxPlayersProPage = com.gamefactoryx.cheersapp.tool.Configuration.getMaxPlayersProConfigPage();
        maxPages = Configuration.getInstance().getMaxPlayers() % maxPlayersProPage == 0 ?
                Configuration.getInstance().getMaxPlayers() / maxPlayersProPage : Configuration.getInstance().getMaxPlayers() / maxPlayersProPage + 1;
        first = true;


    }

    @Override
    protected void initRulesText() {
        rulesText = Gdx.files.internal(com.gamefactoryx.cheersapp.tool.Configuration.getLanguage() + "/kongosdrink/rules.txt").readString();
    }

    public List<Player> getPlayers() {
        return Configuration.getInstance().getPlayers();
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

}
