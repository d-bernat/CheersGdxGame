package com.gamefactoryx.cheers.model.bus_driving;

import com.badlogic.gdx.utils.Queue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bernat on 17.05.2017.
 */
public class Board {

    private final Queue<VCard> vCards;

    public Board(){
        vCards = new Queue<>();
    }
    public Queue<VCard> getVCards() {
        return vCards;
    }
    public void addCard(VCard vCard){
        vCards.addLast(vCard);
    }

}
