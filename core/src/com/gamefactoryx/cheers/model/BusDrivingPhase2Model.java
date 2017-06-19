package com.gamefactoryx.cheers.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Queue;
import com.gamefactoryx.cheers.model.bus_driving.Board;
import com.gamefactoryx.cheers.model.bus_driving.Croupier;
import com.gamefactoryx.cheers.model.bus_driving.Player;
import com.gamefactoryx.cheers.model.bus_driving.VCard;
import com.gamefactoryx.cheers.tool.CardOrientation;
import com.gamefactoryx.cheers.tool.Configuration;

import java.util.Collections;
import java.util.List;


/**
 * Created by bernat on 16.05.2017.
 */
public final class BusDrivingPhase2Model {


    private Croupier croupier;
    private String finalMessage;
    private static BusDrivingPhase2Model instance;
    private boolean scrolled;
    private boolean phaseIsFinished;


    public boolean isScrolled() {
        return scrolled;
    }

    public void setScrolled(boolean scrolled) {
        this.scrolled = scrolled;
    }

    public static BusDrivingPhase2Model getInstance() {
        if (instance == null) {
            instance = new BusDrivingPhase2Model();
        }

        return instance;
    }

    public static BusDrivingPhase2Model getNewInstance() {
        instance = new BusDrivingPhase2Model();
        return instance;
    }

    private BusDrivingPhase2Model() {
        croupier = Croupier.getInstance();
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


    public boolean checkAndSetPhaseFinished(VCard activeCard) {

        if (activeCard == null)
            return false;

        for (VCard vCard : getBoard().getVCards()) {
            if (vCard.getOrientation() != CardOrientation.FACE)
                return false;
        }

        for (Player player : getPlayers()) {
            for (VCard vCard : player.getVCards())
                if (activeCard.equals(vCard))
                    return false;
        }

        setFinalMessage();
        setLoosers();
        phaseIsFinished = true;
        return true;
    }

    public boolean canAnyPlayerDropCard(VCard activeCard) {

        if(activeCard == null)
            return false;

        for (Player player : getPlayers()) {
            for (VCard vCard : player.getVCards())
                if (activeCard.equals(vCard))
                    return true;
        }

        return false;
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
                this.finalMessage = "Wieter ?";
        }
    }

    private void setLoosers() {
        Collections.sort(getPlayers());
        int index = 0;
        int count = 0;
        for (Player player : getPlayers()) {
            if (index == 0) {
                player.setAlive(true);
                count = player.getVCards().size;
            } else {
                player.setAlive(count == player.getVCards().size);
            }
            ++index;
        }
    }


}
