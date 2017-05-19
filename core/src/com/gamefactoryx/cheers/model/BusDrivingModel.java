package com.gamefactoryx.cheers.model;

import com.badlogic.gdx.graphics.Texture;
import com.gamefactoryx.cheers.model.bus_driving.*;
import com.gamefactoryx.cheers.tool.Configuration;

import java.util.*;

/**
 * Created by bernat on 16.05.2017.
 */
public final class BusDrivingModel {
    private int phaseIndex;
    private int playerIndex;
    private List<Phase> phases = new ArrayList<>();
    private List<Player> players = new ArrayList<>();
    private Croupier croupier = new Croupier();
    private Map<String, Texture> cardTextures = new HashMap<>();
    private List<Integer> iCards = new ArrayList<>();
    private List<String> deTasks = new ArrayList<>();
    private List<String> enTasks = new ArrayList<>();

    private BusDrivingModel() {
    }

    private static BusDrivingModel instance;

    public static BusDrivingModel getInstance() {
        if (instance == null) {
            instance = new BusDrivingModel();
            instance.reset();
            instance.setCardTextures();
            instance.deTasks.add("Rot oder Schwarz?");
            instance.enTasks.add("Red or Black?");
            instance.deTasks.add("Größer oder Kleiner?");
            instance.enTasks.add("Bigger or Smaller?");
            instance.deTasks.add("In oder Aus?");
            instance.enTasks.add("In or Out?");
            instance.deTasks.add("Welche Farbe?");
            instance.enTasks.add("Which color?");

        }

        return instance;
    }

    private void setICards() {
        for (int i = 2; i < 53; i++)
            iCards.add(i);
    }

    public void reset() {
        instance.getICards().clear();
        instance.setICards();
        instance.croupier.shuffle();
        instance.players.clear();
        instance.setPlayers(instance.createPlayers());
        instance.phases.clear();
        instance.setPhases(instance.createPhases());
        firstPhase();
        firstPlayer();

    }

    public String getTask() {

        switch (phaseIndex) {
            case 0:
                switch (Configuration.getLanguage()) {
                    case DE:
                        return deTasks.get(getPhase().getTurn());
                    case EN:
                        return enTasks.get(getPhase().getTurn());
                    default:
                        return deTasks.get(getPhase().getTurn());
                }
        }
        return "";
    }


    public List<Integer> getICards() {
        return iCards;
    }

    private void setCardTextures() {
        for (Integer icard : iCards) {
            Card card = new Card(icard, Card.CardSize.BIG);
            cardTextures.put(card.getFileName(Card.CardSize.BIG), new Texture(card.getFileName(Card.CardSize.BIG)));
        }

        for (Integer iCard : iCards) {
            Card card = new Card(iCard, Card.CardSize.SMALL);
            cardTextures.put(card.getFileName(Card.CardSize.SMALL), new Texture(card.getFileName(Card.CardSize.SMALL)));
        }

    }

    public Map<String, Texture> getCardTextures() {
        return cardTextures;
    }


    private List<Player> createPlayers() {

        for (int i = 0; i < com.gamefactoryx.cheers.tool.Configuration.getMaxPlayers(); i++) {
            String name = PlayerNameCache.getName(i);
            if (name == null)
                String.format("%d", i + 1);
            players.add(new Player(name, i));
        }
        return players;
    }

    private List<Phase> createPhases() {
        for (int i = 1; i < 4; i++)
            phases.add(new Phase(i));
        return phases;
    }


    public List<Phase> getPhases() {
        return phases;
    }

    public Phase getPhase() {
        return phases.get(phaseIndex);
    }

    public boolean prevPhase() {
        if (phaseIndex == 0) {
            return false;
        } else {
            --phaseIndex;
            return true;
        }
    }

    public boolean nextPhase() {
        if (phaseIndex == phases.size() - 1) {
            return false;
        } else {
            ++phaseIndex;
            return true;
        }
    }

    public void firstPhase() {
        phaseIndex = 0;

    }

    public void lastPhase() {
        phaseIndex = phases.size() - 1;
    }


    public Player getPlayer() {
        return players.get(playerIndex);
    }

    public boolean prevPlayer() {
        if (playerIndex == 0) {
            return false;
        } else {
            --playerIndex;
            return true;
        }

    }

    public boolean nextPlayer() {
        if (playerIndex == players.size() - 1) {
            return false;
        } else {
            ++playerIndex;
            return true;
        }
    }

    public void firstPlayer() {
        playerIndex = 0;

    }

    public void lastPlayer() {
        playerIndex = players.size() - 1;
    }

    protected void setPhases(List<Phase> phases) {
        this.phases = phases;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Croupier getCroupier() {
        return croupier;
    }


}
