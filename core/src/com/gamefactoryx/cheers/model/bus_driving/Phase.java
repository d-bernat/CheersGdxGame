package com.gamefactoryx.cheers.model.bus_driving;

import com.badlogic.gdx.Gdx;
import com.gamefactoryx.cheers.CheersGdxGame;
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
    private int step;
    private boolean phase_finished;


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


    public boolean isPhaseFinished() {
        return phase_finished;
    }

    public void nextTurn() {
        switch (name) {
            case "PHASE_1":
                VCard vCard;
                switch (step) {
                    case 0:
                        vCard = BusDrivingModel.getInstance().getCroupier().getVCard();
                        vCard.setOrientation(VCard.CardOrientation.FACE);
                        BusDrivingModel.getInstance().getPhase().getBoard().addCard(vCard);
                        ++step;
                        break;
                    case 1:
                        vCard = BusDrivingModel.getInstance().getPhase().getBoard().getVCards().removeLast();
                        vCard.setOrientation(VCard.CardOrientation.FACE);
                        BusDrivingModel.getInstance().getPlayer().addVCard(vCard);
                        ++step;
                        break;
                    case 2:
                        if (BusDrivingModel.getInstance().nextPlayer()) {
                            ++turn;
                        } else if (BusDrivingModel.getInstance().getPlayer().getVCards().size < 4) {
                            BusDrivingModel.getInstance().firstPlayer();
                            turn = 0;
                            ++round;
                        } else {
                            setMessageToContinue();
                            phase_finished = true;
                        }
                        step = 0;

                        break;
                }
                break;
            case "PHASE_2":
                setMessageToContinue();
                phase_finished = true;
                break;
        }

    }
    private void setMessageToContinue(){
        BusDrivingModel.getInstance().setMessage("GO_ON");

    }
}
