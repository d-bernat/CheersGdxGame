package com.gamefactoryx.cheers.model.bus_driving;

import com.gamefactoryx.cheers.model.BusDrivingModel;
import com.gamefactoryx.cheers.tool.Configuration;

import java.util.Random;

/**
 * Created by bernat on 17.05.2017.
 */
public class Phase {
    private final String name;
    private final Board board;
    private int round;
    private int turn;
    private boolean phase_finished;
    private boolean round_finished;

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

    public int getRound() {
        return round;
    }

    public int getTurn() {
        return turn;
    }



    public boolean isPhaseFinished() {
        return phase_finished;
    }

    public boolean isRoundFinished() {
        return round_finished;
    }

    public void nextRound() {
        round_finished = false;
        switch (name) {
            case "PHASE_1":
                if (round < Configuration.getMaxPlayers() - 1) {
                    turn = 0;
                    ++round;
                    BusDrivingModel.getInstance().nextPlayer();
                } else {
                    round_finished = true;
                    phase_finished = true;
                }
                break;
        }

    }

    public void nextTurn() {
        switch (name) {
            case "PHASE_1":
                if (turn < 3) {
                    ++turn;
                }
                else
                    round_finished = true;
                break;
        }
    }
}
