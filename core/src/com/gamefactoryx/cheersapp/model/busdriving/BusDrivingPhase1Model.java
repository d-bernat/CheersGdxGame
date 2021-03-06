package com.gamefactoryx.cheersapp.model.busdriving;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Queue;
import com.gamefactoryx.cheersapp.model.Model;
import com.gamefactoryx.cheersapp.tool.Configuration;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by bernat on 16.05.2017.
 */
public final class BusDrivingPhase1Model extends Model {


    private List<String> enTasks = new ArrayList<>();
    private List<String> deTasks = new ArrayList<>();

    private int activePlayer;
    private int activeTask;
    private com.gamefactoryx.cheersapp.model.busdriving.Croupier croupier;
    private String finalMessage;
    private static BusDrivingPhase1Model instance;


    public static BusDrivingPhase1Model getInstance(){
        if(instance == null) {
            instance = new BusDrivingPhase1Model();
        }

        return instance;
    }

    public static BusDrivingPhase1Model getNewInstance(){
        instance = new BusDrivingPhase1Model();
        return instance;
    }

    private  BusDrivingPhase1Model() {
        deTasks.add("Schwarz oder Rot?");
        enTasks.add("Black or red?");
        deTasks.add("Höher oder Tiefer?");
        enTasks.add("Higher card or lower card?");
        deTasks.add("Dazwischen oder Auserhalb?");
        enTasks.add("Between or Outside?");
        deTasks.add("Welches Muster?");
        enTasks.add("Which template?");
        croupier = com.gamefactoryx.cheersapp.model.busdriving.Croupier.getInstance();

    }

    public Queue<com.gamefactoryx.cheersapp.model.busdriving.VCard> getvCards() {
        return croupier.getInstance().getVCards();
    }

    public List<com.gamefactoryx.cheersapp.model.busdriving.Player> getPlayers() {
        return croupier.getInstance().getPlayers();
    }


    public com.gamefactoryx.cheersapp.model.busdriving.Board getBoard() {
        return croupier.getInstance().getBoard();
    }


    public String getTask() {
        if (deTasks.size() == enTasks.size() && deTasks.size() > activeTask)
            switch (Configuration.getLanguage()) {
                case DE:
                    return deTasks.get(activeTask);
                case EN:
                    return enTasks.get(activeTask);
                default:
                    return deTasks.get(activeTask);
            }

        return null;
    }

    public boolean isPhaseFinished() {
        for (com.gamefactoryx.cheersapp.model.busdriving.Player player : getPlayers()) {
            if (player.isEnable() && player.getVCards().size < 4)
                return false;
        }
        setFinalMessage();
        return true;
    }

    public int getActivePlayer() {
        return activePlayer;
    }

    public void setActivePlayer(int activePlayer) {
        if (activePlayer < getPlayers().size() && getPlayers().get(activePlayer).isEnable())
            this.activePlayer = activePlayer;
        else {
            this.activePlayer = 0;
            ++activeTask;
        }
    }


    public String getFinalMessage() {
        return finalMessage;
    }

    public void setFinalMessage() {
        switch(Configuration.getLanguage()) {
            case DE:
                this.finalMessage = "Weiter ?";
                break;
            case EN:
                this.finalMessage = "Continue ?";
                break;
            default:
                this.finalMessage = "Weiter ?";
        }
    }

    @Override
    protected void initRulesText() {
        rulesText = Gdx.files.internal(Configuration.getLanguage() + "/Busdrivingscreen/rules01.txt").readString();
    }
}
