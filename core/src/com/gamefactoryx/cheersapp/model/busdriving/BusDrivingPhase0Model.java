package com.gamefactoryx.cheersapp.model.busdriving;

import com.badlogic.gdx.Gdx;
import com.gamefactoryx.cheersapp.model.Model;
import com.gamefactoryx.cheersapp.tool.Configuration;

import java.util.List;


/**
 * Created by bernat on 16.05.2017.
 */
public final class BusDrivingPhase0Model extends Model {


    private int activePlayer;
    private com.gamefactoryx.cheersapp.model.busdriving.Croupier croupier;
    private static BusDrivingPhase0Model instance;
    private int page = 1;
    private int maxPages;
    private boolean last, first;
    private boolean loadingNextStage;

    public static BusDrivingPhase0Model getInstance() {
        if (instance == null) {
            instance = new BusDrivingPhase0Model();
        }

        return instance;
    }

    public static BusDrivingPhase0Model getNewInstance() {
        instance = new BusDrivingPhase0Model();
        return instance;
    }

    private BusDrivingPhase0Model() {
        croupier = com.gamefactoryx.cheersapp.model.busdriving.Croupier.getNewInstance();
        int maxPlayersProPage = Configuration.getMaxPlayersProConfigPage();
        maxPages = Configuration.getMaxPlayers() % maxPlayersProPage == 0 ?
                Configuration.getMaxPlayers() / maxPlayersProPage : Configuration.getMaxPlayers() / maxPlayersProPage + 1;
        first = true;

    }

    public List<com.gamefactoryx.cheersapp.model.busdriving.Player> getPlayers() {
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


    public boolean isLoadingNextStage() {
        return loadingNextStage;
    }

    public void setLoadingNextStage(boolean loadingNextStage) {
        this.loadingNextStage = loadingNextStage;
    }

    @Override
    protected void initRulesText() {
        rulesText = Gdx.files.internal(Configuration.getLanguage() + "/Busdrivingscreen/rules.txt").readString();
    }
}
