package com.gamefactoryx.cheers.model;

import com.badlogic.gdx.utils.Queue;
import com.gamefactoryx.cheers.model.bus_driving.Board;
import com.gamefactoryx.cheers.model.bus_driving.Croupier;
import com.gamefactoryx.cheers.model.bus_driving.Player;
import com.gamefactoryx.cheers.model.bus_driving.VCard;
import com.gamefactoryx.cheers.tool.CardOrientation;
import com.gamefactoryx.cheers.tool.Configuration;

import java.util.List;


/**
 * Created by bernat on 16.05.2017.
 */
public final class BusDrivingPhase4Model {


    private Croupier croupier;
    private String finalMessage;
    private static BusDrivingPhase4Model instance;
    private boolean phaseIsFinished;
    private Player activePlayer;


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
       // croupier.getBoard().addCard(croupier.getVCards().removeLast());
        firstPlayer();

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


    public void setPhaseFinished(Player looser) {

        setFinalMessage();
        setLoosers(looser);
        phaseIsFinished = true;
    }

    public boolean isPhaseFinished() {
        return phaseIsFinished;
    }

    public String getFinalMessage() {
        return finalMessage;
    }

    public void setFinalMessage() {
        switch (Configuration.getLanguage()) {
            case DE:
                this.finalMessage = "Weiter ?";
                break;
            case EN:
                this.finalMessage = "Continue ?";
                break;
            default:
                this.finalMessage = "Weiter ?";
        }
    }

    public Player getActivePlayer() {
        return activePlayer;
    }


    private void firstPlayer() {
        for (Player player : croupier.getPlayers()) {
            if (player.isAlive()) {
                activePlayer = player;
                break;
            }
        }
    }

    public void nextPlayer() {
        boolean behind = false;
        boolean newPlayerSet = false;
        if (activePlayer != null) {
            for (Player player : croupier.getPlayers()) {
                if (behind && player.isAlive()) {
                    activePlayer = player;
                    newPlayerSet = true;
                }

                if (player.equals(activePlayer)) {
                    behind = true;
                }
            }
        }

        if (!newPlayerSet) firstPlayer();
    }

    private void setLoosers(Player looser) {
        for (Player player : getPlayers()) {
            player.setAlive(false);
        }
        looser.setAlive(true);
    }

    public String getTask() {
        switch (Configuration.getLanguage()) {
            case DE:
                return "Höher oder Tiefer?";
            case EN:
                return "Higher card or lower card?";
            default:
                return "Höher oder Tiefer?";

        }

    }
}