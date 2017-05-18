package com.gamefactoryx.cheers.model.bus_driving;

import java.util.Random;

/**
 * Created by bernat on 17.05.2017.
 */
public class Phase {
    private final String name;
    private final Board board;
    private Random rand = new Random();

    public Phase(int index) {
        name = "PHASE_" + index;
        board = new Board();
    }

    public String getName() {
        return name;
    }

    public Board getBoard() {
        return board;
    }
}
