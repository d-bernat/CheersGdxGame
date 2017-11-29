package com.gamefactoryx.cheersapp.model.busdriving;

import com.badlogic.gdx.utils.Queue;


/**
 * Created by bernat on 17.05.2017.
 */
public class Player implements Comparable<Player>{

    private final Queue<VCard> cards;
    private boolean alive;
    private boolean enable;
    private int position;
    private final com.gamefactoryx.cheersapp.model.Subject subject;

    public Player(com.gamefactoryx.cheersapp.model.Subject subject, int position, boolean enable){
        cards = new Queue<>();
        this.subject = subject;
        this.position = position;
        this.enable = enable;
    }
    public String getName() {
        return subject.getName();
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
        subject.setName(name);
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public void addVCard(VCard vCard){
        cards.addLast(vCard);
    }
    public void removeVCard(VCard vCard){
        cards.removeIndex(cards.indexOf(vCard, true));
    }

    public com.gamefactoryx.cheersapp.model.Subject.Sex getSex() {
        return subject.getSex();
    }
    public void setSex(com.gamefactoryx.cheersapp.model.Subject.Sex sex){
        subject.setSex(sex);
    }
    @Override
    public int compareTo(Player o) {
        return o.getVCards().size - this.getVCards().size ;
    }

    public boolean equals(Player player){
        return player != null && player.getName() != null && player.getName().trim().length() > 0 &&
                this.getName() != null && this.getName().trim().length() > 0 &&
                this.getName().toLowerCase().equals(player.getName().toLowerCase());
    }
}
