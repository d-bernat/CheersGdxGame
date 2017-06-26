package com.gamefactoryx.cheers.model;

import com.gamefactoryx.cheers.model.bus_driving.Croupier;
import com.gamefactoryx.cheers.model.bus_driving.Player;

import java.util.List;


/**
 * Created by bernat on 16.05.2017.
 */
public final class BusDrivingPhase0Model {


    private int activePlayer;
    private Croupier croupier;
    private static BusDrivingPhase0Model instance;


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

    }

    public List<Player> getPlayers() {
        return croupier.getInstance().getPlayers();
    }

   /* public int getActivePlayer() {
        return activePlayer;
    }

    public void setActivePlayer(int activePlayer) {
        if (activePlayer < getPlayers().size())
            this.activePlayer = activePlayer;
    }*/
}
