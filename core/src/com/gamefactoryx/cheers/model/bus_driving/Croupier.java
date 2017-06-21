package com.gamefactoryx.cheers.model.bus_driving;

import com.badlogic.gdx.utils.Queue;
//import com.gamefactoryx.cheers.model.BusDrivingModel;
import com.gamefactoryx.cheers.model.PlayerNameCache;
import com.gamefactoryx.cheers.tool.CardOrientation;
import com.gamefactoryx.cheers.tool.Configuration;
import com.gamefactoryx.cheers.tool.FunnyNameGenerator;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by bernat on 17.05.2017.
 */
public class Croupier {
    private static Croupier instance;

    public static Croupier getInstance(){

        if(instance == null){
            instance = new Croupier();
        }

        return instance;
    }

    public static Croupier getNewInstance(){

        instance = new Croupier();
        return instance;
    }


    private final Queue<VCard> vCards = new Queue<>();
    private final List<com.gamefactoryx.cheers.model.bus_driving.Player> players = new ArrayList<>();
    private final com.gamefactoryx.cheers.model.bus_driving.Board board = new com.gamefactoryx.cheers.model.bus_driving.Board();


    private Croupier(){

        for (int i = 2; i < 53; i++)
            vCards.addLast(new VCard(i, CardOrientation.BACK));

        for (int i = 0; i < 6; i++) {
            PlayerNameCache.clear();
            players.add(new com.gamefactoryx.cheers.model.bus_driving.Player(FunnyNameGenerator.getFunnyName(i), i, i < 2));
        }
        shuffle();
    }

    public void shuffle() {
        shuffle(2, 53);
    }

    public void shuffle(int min, int max) {
        List<VCard> temps = new ArrayList<>();
        for (VCard vCard: vCards)
            temps.add(vCard);
        Collections.shuffle(temps);
        for (VCard vCard : temps) {
            vCards.addLast(vCard);
        }
    }

    public VCard getVCard() {
        return vCards.removeLast();
    }

    public Queue<VCard> getVCards() {
        return vCards;
    }


    //public Queue<VCard> getvCards() {
    //    return vCards;
    //}

    public List<com.gamefactoryx.cheers.model.bus_driving.Player> getPlayers() {
        return players;
    }

    public com.gamefactoryx.cheers.model.bus_driving.Board getBoard() {
        return board;
    }




}

