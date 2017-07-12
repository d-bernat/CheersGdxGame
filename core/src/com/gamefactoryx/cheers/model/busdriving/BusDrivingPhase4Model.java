package com.gamefactoryx.cheers.model.busdriving;

import com.badlogic.gdx.utils.Queue;
import com.gamefactoryx.cheers.tool.CardOrientation;

import java.util.List;


/**
 * Created by bernat on 16.05.2017.
 */
public final class BusDrivingPhase4Model {


    private Croupier croupier;
    private static BusDrivingPhase4Model instance;
    private boolean phaseIsFinished;
    private Player busDriver;
    private int drinkPoints;
    private int totalDrunk;
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
        croupier = Croupier.getInstance();
        for (Player player : croupier.getPlayers())
            player.getVCards().clear();
        croupier.getBoard().getVCards().clear();
        croupier.getVCards().clear();
        for (int i = 2; i < 53; i++)
            croupier.getVCards().addLast(new VCard(i, CardOrientation.FACE));
        croupier.shuffle();
        for(int i = 0; i < 7; i++)
            croupier.getBoard().addCard(croupier.getVCards().removeLast());
        for (Player player : getPlayers()) {
            if(player.isAlive()) {
                busDriver = player;
            }
        }
    }



    public Queue<VCard> getvCards() {
        return croupier.getInstance().getVCards();
    }

    public List<Player> getPlayers() {
        return croupier.getInstance().getPlayers();
    }


    public Board getBoard() {
        return croupier.getInstance().getBoard();
    }


    public void setPhaseFinished() {
        phaseIsFinished = true;
    }

    public boolean isPhaseFinished() {
        return phaseIsFinished;
    }

    public Player getBusDriver(){
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
}