package com.gamefactoryx.cheersapp.model.busdriving;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Queue;
import com.gamefactoryx.cheersapp.tool.Configuration;

import java.util.List;


/**
 * Created by bernat on 16.05.2017.
 */
public final class BusDrivingPhase4Model extends com.gamefactoryx.cheersapp.model.Model {


    private com.gamefactoryx.cheersapp.model.busdriving.Croupier croupier;
    private static BusDrivingPhase4Model instance;
    private boolean phaseIsFinished;
    private com.gamefactoryx.cheersapp.model.busdriving.Player busDriver;
    private int drinkPoints;
    private int totalDrunk = 0;
    private int activeCardIndex;


    public static BusDrivingPhase4Model getInstance() {
        if (instance == null) {
            instance = new BusDrivingPhase4Model();
        }

        return instance;
    }

    public static BusDrivingPhase4Model getNewInstance() {
        instance = new BusDrivingPhase4Model();
        return instance;
    }

    private BusDrivingPhase4Model() {
        croupier = com.gamefactoryx.cheersapp.model.busdriving.Croupier.getInstance();
        for (com.gamefactoryx.cheersapp.model.busdriving.Player player : croupier.getPlayers())
            player.getVCards().clear();
        croupier.getBoard().getVCards().clear();
        croupier.getVCards().clear();
        for (int i = 2; i < 53; i++)
            croupier.getVCards().addLast(new VCard(i, com.gamefactoryx.cheersapp.tool.CardOrientation.FACE));
        croupier.shuffle();
        for(int i = 0; i < 7; i++)
            croupier.getBoard().addCard(croupier.getVCards().removeLast());
        for (com.gamefactoryx.cheersapp.model.busdriving.Player player : getPlayers()) {
            if(player.isAlive()) {
                busDriver = player;
            }
        }
    }



    public Queue<VCard> getvCards() {
        return croupier.getInstance().getVCards();
    }

    public List<com.gamefactoryx.cheersapp.model.busdriving.Player> getPlayers() {
        return croupier.getInstance().getPlayers();
    }


    public com.gamefactoryx.cheersapp.model.busdriving.Board getBoard() {
        return croupier.getInstance().getBoard();
    }


    public void setPhaseFinished() {
        phaseIsFinished = true;
    }

    public boolean isPhaseFinished() {
        return phaseIsFinished;
    }

    public com.gamefactoryx.cheersapp.model.busdriving.Player getBusDriver(){
        return busDriver;
    }

    public int getDrinkPoints() {
        return drinkPoints;
    }

    public void setDrinkPoints(int drinkPoints) {
        this.drinkPoints = drinkPoints;
        totalDrunk += drinkPoints;
    }

    public int getActiveCardIndex() {
        return activeCardIndex;
    }

    public void setActiveCardIndex(int activeCardIndex) {
        this.activeCardIndex = activeCardIndex;
    }

    public int getTotalDrunk() {
        return totalDrunk;
    }

    @Override
    protected void initRulesText() {
        rulesText = Gdx.files.internal(Configuration.getLanguage() + "/Busdrivingscreen/rules04.txt").readString();
    }
}