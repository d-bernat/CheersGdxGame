package com.gamefactoryx.cheers.model.bus_driving;

import com.badlogic.gdx.utils.Queue;



/**
 * Created by bernat on 17.05.2017.
 */
public class Player implements Comparable<Player>{

    private String name;
    private final Queue<VCard> cards;
    private boolean alive;
    private int position;

    public Player(String name, int position){
        cards = new Queue<>();
        this.name = name;
        this.position = position;
    }
    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public Queue<VCard> getVCards() {
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

    public void addVCard(VCard vCard){
        cards.addLast(vCard);
    }
    public void removeVCard(VCard vCard){
        cards.removeIndex(cards.indexOf(vCard, true));
    }


    @Override
    public int compareTo(Player o) {
        return o.getVCards().size - this.getVCards().size ;
    }
}
