package com.gamefactoryx.cheersapp.model.busdriving;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Queue;
import com.gamefactoryx.cheersapp.tool.Configuration;

import java.util.List;


/**
 * Created by bernat on 16.05.2017.
 */
public final class BusDrivingPhase3Model extends com.gamefactoryx.cheersapp.model.Model {


    private com.gamefactoryx.cheersapp.model.busdriving.Croupier croupier;
    private String finalMessage;
    private static BusDrivingPhase3Model instance;
    private boolean phaseIsFinished;
    private com.gamefactoryx.cheersapp.model.busdriving.Player activePlayer;


    public static BusDrivingPhase3Model getInstance() {
        if (instance == null) {
            instance = new BusDrivingPhase3Model();
        }

        return instance;
    }

    public static BusDrivingPhase3Model getNewInstance() {
        instance = new BusDrivingPhase3Model();
        return instance;
    }

    private BusDrivingPhase3Model() {
        croupier = com.gamefactoryx.cheersapp.model.busdriving.Croupier.getInstance();
        for (com.gamefactoryx.cheersapp.model.busdriving.Player player : croupier.getPlayers())
            player.getVCards().clear();
        croupier.getBoard().getVCards().clear();
        croupier.getVCards().clear();
        for (int i = 2; i < 53; i++)
            croupier.getVCards().addLast(new VCard(i, com.gamefactoryx.cheersapp.tool.CardOrientation.FACE));
        croupier.shuffle();
        croupier.getBoard().addCard(croupier.getVCards().removeLast());
        firstPlayer();

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


    public void setPhaseFinished(com.gamefactoryx.cheersapp.model.busdriving.Player looser) {

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

    public com.gamefactoryx.cheersapp.model.busdriving.Player getActivePlayer() {
        return activePlayer;
    }


    private void firstPlayer() {
        for (com.gamefactoryx.cheersapp.model.busdriving.Player player : croupier.getPlayers()) {
            if (player.isAlive() && player.isEnable()) {
                activePlayer = player;
                break;
            }
        }
    }

    public void nextPlayer() {
        boolean behind = false;
        boolean newPlayerSet = false;
        if (activePlayer != null) {
            for (com.gamefactoryx.cheersapp.model.busdriving.Player player : croupier.getPlayers()) {
                if (behind && player.isAlive() && player.isEnable()) {
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

    private void setLoosers(com.gamefactoryx.cheersapp.model.busdriving.Player looser) {
        for (com.gamefactoryx.cheersapp.model.busdriving.Player player : getPlayers()) {
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

    @Override
    public void initRulesText() {
        rulesText = Gdx.files.internal(Configuration.getLanguage() + "/Busdrivingscreen/rules03.txt").readString();
    }
}