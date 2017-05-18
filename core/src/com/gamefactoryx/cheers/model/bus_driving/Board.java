package com.gamefactoryx.cheers.model.bus_driving;

import com.badlogic.gdx.utils.Queue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bernat on 17.05.2017.
 */
public class Board {

    private final Queue<Integer> iCards;

    public Board(){
        iCards = new Queue<>();
    }
    public Queue<Integer> getCards() {
        return iCards;
    }
    public void addCard(Integer iCard){
        iCards.addLast(iCard);
    }

}
