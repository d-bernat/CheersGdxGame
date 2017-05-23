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
    private final Queue<VCard> vCards = new Queue<>();

    public void shuffle() {
        shuffle(2, 53);
    }

    public void shuffle(int min, int max) {
        vCards.clear();
        List<VCard> temps = new ArrayList<>();
        for (VCard vCard: BusDrivingModel.getInstance().getVCards())
            temps.add(vCard);
        Collections.shuffle(temps);

        for (VCard vCard : temps) {
            vCards.addLast(vCard);
        }
    }

    public VCard getVCard() {
        return vCards.removeFirst();
    }

    public Queue<VCard> getVCards() {
        return vCards;
    }

}

