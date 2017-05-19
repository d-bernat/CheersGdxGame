package com.gamefactoryx.cheers.model.bus_driving;

import com.gamefactoryx.cheers.model.BusDrivingModel;

import java.util.Random;

/**
 * Created by bernat on 17.05.2017.
 */
public class Phase {
    private final String name;
    private final Board board;
    private int round;
    private boolean finished;

    public Phase(int index) {
        name = "PHASE_" + index;
        board = new Board();
        round = 0;
    }

    public String getName() {
        return name;
    }

    public Board getBoard() {
        return board;
    }

    public int getRound(){ return round;}

    public boolean isFinished() {
        return finished;
    }

    public void nextRound(){
        switch(name){
            case "PHASE_1":
                if(round < 3){
                    ++round;
                    BusDrivingModel.getInstance().nextPlayer();
                }
                else
                    finished = true;
                break;
        }

    }
}
