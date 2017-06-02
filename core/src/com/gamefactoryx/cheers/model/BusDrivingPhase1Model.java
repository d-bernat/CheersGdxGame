package com.gamefactoryx.cheers.model;

import com.badlogic.gdx.utils.Queue;
import com.gamefactoryx.cheers.model.bus_driving2.*;
import com.gamefactoryx.cheers.tool.Configuration;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by bernat on 16.05.2017.
 */
public final class BusDrivingPhase1Model {


    private List<String> enTasks = new ArrayList<>();
    private List<String> deTasks = new ArrayList<>();

    private int activePlayer;
    private int activeTask;
    private Croupier croupier;
    private String finalMessage;
    private static BusDrivingPhase1Model instance;


    public static BusDrivingPhase1Model getInstance(){
        if(instance == null) {
            instance = new BusDrivingPhase1Model();
        }

        return instance;
    }

    public static BusDrivingPhase1Model getNewInstance(){
        instance = new BusDrivingPhase1Model();
        return instance;
    }

    private  BusDrivingPhase1Model() {
        deTasks.add("Schwarz oder Rot?");
        enTasks.add("Black or red?");
        deTasks.add("Höher oder Tiefer?");
        enTasks.add("Higher card or lower card?");
        deTasks.add("Dazwischen oder Auserhalb?");
        enTasks.add("Between or Outside?");
        deTasks.add("Welches Muster?");
        enTasks.add("Which template?");
        croupier = Croupier.getNewInstance();

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


    public String getTask() {
        if (deTasks.size() == enTasks.size() && deTasks.size() > activeTask)
            switch (Configuration.getLanguage()) {
                case DE:
                    return deTasks.get(activeTask);
                case EN:
                    return enTasks.get(activeTask);
                default:
                    return deTasks.get(activeTask);
            }

        return null;
    }

    public boolean isPhaseFinishend() {
        for (Player player : getPlayers()) {
            if (player.getVCards().size < 4)
                return false;
        }
        setFinalMessage();
        return true;
    }

    public int getActivePlayer() {
        return activePlayer;
    }

    public void setActivePlayer(int activePlayer) {
        if (activePlayer < getPlayers().size())
            this.activePlayer = activePlayer;
        else {
            this.activePlayer = 0;
            ++activeTask;
        }
    }


    public String getFinalMessage() {
        return finalMessage;
    }

    public void setFinalMessage() {
        switch(Configuration.getLanguage()) {
            case DE:
                this.finalMessage = "Weiter ?";
                break;
            case EN:
                this.finalMessage = "Continue ?";
                break;
            default:
                this.finalMessage = "Wieter ?";
        }
    }


}
