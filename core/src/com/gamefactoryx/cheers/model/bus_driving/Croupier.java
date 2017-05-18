package com.gamefactoryx.cheers.model.bus_driving;

import com.badlogic.gdx.utils.Queue;
import com.gamefactoryx.cheers.model.BusDrivingModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by bernat on 17.05.2017.
 */
public class Croupier {
    private final Queue<Integer> cards = new Queue<>();

    public void shuffle() {
        shuffle(2, 53);
    }

    public void shuffle(int min, int max) {
        cards.clear();
        List<Integer> temps = new ArrayList<>();
        for (Integer iCard: BusDrivingModel.getInstance().getICards())
            temps.add(iCard);
        Collections.shuffle(temps);

        for (int i : temps) {
            cards.addLast(i);
        }
    }

    public Integer getCard() {
        return cards.removeFirst();
    }

    public Queue<Integer> getCards() {
        return cards;
    }

}

