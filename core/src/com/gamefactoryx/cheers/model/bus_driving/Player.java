package com.gamefactoryx.cheers.model.bus_driving;

import com.badlogic.gdx.utils.Queue;


/**
 * Created by bernat on 17.05.2017.
 */
public class Player {

    private String name;
    private final Queue<Integer> cards;
    private boolean alive;

    public Player(String name){
        cards = new Queue<>();
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public Queue<Integer> getCards() {
        return cards;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public void addCard(Integer iCard){
        cards.addLast(iCard);
    }
    public void removeCard(Integer iCard){
        cards.removeIndex(cards.indexOf(iCard, true));
    }
}
