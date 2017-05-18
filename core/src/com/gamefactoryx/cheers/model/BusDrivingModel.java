package com.gamefactoryx.cheers.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.gamefactoryx.cheers.model.bus_driving.*;

import javax.xml.soap.Text;
import java.util.*;

/**
 * Created by bernat on 16.05.2017.
 */
public final class BusDrivingModel {
    private int phaseIndex;
    private int playerIndex;
    private List<Phase> phases;
    private List<Player> players;
    private Croupier croupier = new Croupier();
    private Map<String, Texture> cardTextures = new HashMap<>();

    private BusDrivingModel(){}

    private static BusDrivingModel instance;

    public static BusDrivingModel getInstance(){
        if(instance == null ) {
            instance = new BusDrivingModel();
            instance.croupier.shuffle();
            instance.setCardTextures();
            instance.setPlayers(instance.createPlayers());
            instance.setPhases(instance.createPhases());

        }
        return instance;
    }

    private void setCardTextures(){
        for(Integer icard: getCroupier().getCards()) {
            Card card = new Card(icard, Card.CardSize.BIG);
            cardTextures.put(card.getFile(Card.CardSize.BIG), new Texture(card.getFile(Card.CardSize.BIG)));
        }

        for(Integer iCard: getCroupier().getCards()) {
            Card card = new Card(iCard, Card.CardSize.SMALL);
            cardTextures.put(card.getFile(Card.CardSize.SMALL), new Texture(card.getFile(Card.CardSize.SMALL)));
        }

    }

    public Map<String, Texture> getCardTextures() {
        return cardTextures;
    }


    private List<Player> createPlayers() {
        players = new ArrayList<>();
        for(int i = 0; i < 4; i++)
            players.add( new Player("P" + i));
        return players;
    }

    private List<Phase> createPhases() {
        phases = new ArrayList<>();
        for(int i = 0; i < 4; i++)
            phases.add( new Phase(i));
        return phases;
    }


    public List<Phase> getPhases() {
        return phases;
    }

    public Phase getPhase(){
        return phases.get(phaseIndex);
    }

    public boolean prevPhase(){
        if(phaseIndex == 0){
            return false;
        }else {
            --phaseIndex;
            return true;
        }
    }

    public boolean nextPhase(){
        if(phaseIndex == phases.size() -1){
            return false;
        }else {
            ++phaseIndex;
            return true;
        }
    }

    public void firstPhase(){
        phaseIndex = 0;
    }

    public void lastPhase(){
        phaseIndex = phases.size() - 1;
    }


    public Player getPlayer() {
        return players.get(playerIndex);
    }

    public boolean prevPlayer() {
        if(playerIndex == 0){
            return false;
        }else {
            --playerIndex;
            return true;
        }

    }

    public boolean nextPlayer() {
        if(playerIndex == players.size() -1){
            return false;
        }else {
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

    public Croupier getCroupier(){
        return croupier;
    }


}
