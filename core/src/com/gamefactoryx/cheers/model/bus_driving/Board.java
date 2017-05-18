package com.gamefactoryx.cheers.model.bus_driving;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bernat on 17.05.2017.
 */
public class Board {

    private final List<Integer> iCards;

    public Board(){
        iCards = new ArrayList<>();
    }
    public List<Integer> getCards() {
        return iCards;
    }
    public void addCard(Integer iCard){
        iCards.add(iCard);
    }

}
