package com.gamefactoryx.cheersapp.model.busdriving;

import com.badlogic.gdx.utils.Queue;

/**
 * Created by bernat on 17.05.2017.
 */
public class Board {

    private final Queue<VCard> vCards;
    private boolean allCardsOnBoardFace;

    public Board(){
        vCards = new Queue<>();
    }
    public Queue<VCard> getVCards() {
        return vCards;
    }
    public void addCard(VCard vCard){
        vCards.addLast(vCard);
    }

    public boolean isAllCardsOnBoardFace() {
        return allCardsOnBoardFace;
    }

    public void setAllCardsOnBoardFace(boolean allCardsOnBoardFace) {
        this.allCardsOnBoardFace = allCardsOnBoardFace;
    }
}
