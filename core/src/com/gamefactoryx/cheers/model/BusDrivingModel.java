package com.gamefactoryx.cheers.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.gamefactoryx.cheers.tool.Configuration;

import java.util.*;

/**
 * Created by bernat on 16.05.2017.
 */
public final class BusDrivingModel {
    private int phaseIndex;
    private int playerIndex;
    private List<com.gamefactoryx.cheers.model.bus_driving_OLD.Phase> phases = new ArrayList<>();
    private List<com.gamefactoryx.cheers.model.bus_driving_OLD.Player> players = new ArrayList<>();
    private List<com.gamefactoryx.cheers.model.bus_driving_OLD.Player> loosers = new ArrayList<>();
    private com.gamefactoryx.cheers.model.bus_driving_OLD.Croupier croupier = new com.gamefactoryx.cheers.model.bus_driving_OLD.Croupier();
    private Map<String, Texture> cardTextures = new HashMap<>();
    private List<com.gamefactoryx.cheers.model.bus_driving_OLD.VCard> vCards = new ArrayList<>();
    private List<String> deTasks = new ArrayList<>();
    private List<String> enTasks = new ArrayList<>();
    private List<String> nameList;
    private boolean scrollPyramide;
    private String message;

    private BusDrivingModel() {
    }

    private static BusDrivingModel instance;

    public static BusDrivingModel getInstance() {
        if (instance == null) {
            instance = new BusDrivingModel();
            instance.reset();
            instance.setCardTextures();
            instance.deTasks.add("Schwarz oder Rot?");
            instance.enTasks.add("Black or red?");
            instance.deTasks.add("Höher oder Tiefer?");
            instance.enTasks.add("Higher card or lower card?");
            instance.deTasks.add("Dazwischen oder Auserhalb?");
            instance.enTasks.add("Between or Outside?");
            instance.deTasks.add("Welches Muster?");
            instance.enTasks.add("Which template?");

        }

        return instance;
    }

    private void setVCards() {
        for (int i = 2; i < 53; i++)
            vCards.add(new com.gamefactoryx.cheers.model.bus_driving_OLD.VCard(i, com.gamefactoryx.cheers.model.bus_driving_OLD.VCard.CardOrientation.BACK));
    }

    public void reset() {
        instance.setScrollPyramide(false);
        instance.getVCards().clear();
        instance.setVCards();
        instance.croupier.shuffle();
        instance.players.clear();
        instance.createFunnyNames();
        instance.setPlayers(instance.createPlayers());
        instance.phases.clear();
        instance.setPhases(instance.createPhases());
        instance.setMessage(null);
        firstPhase();
        firstPlayer();

    }


    public String getTask() {

        switch (phaseIndex) {
            case 0:
                switch (Configuration.getLanguage()) {
                    case DE:
                        return deTasks.get(getPhase().getRound());
                    case EN:
                        return enTasks.get(getPhase().getRound());
                    default:
                        return deTasks.get(getPhase().getRound());
                }
            case 2:
                switch (Configuration.getLanguage()) {
                    case DE:
                        return deTasks.get(1);
                    case EN:
                        return enTasks.get(1);
                    default:
                        return deTasks.get(1);
                }
        }
        return "";
    }


    public List<com.gamefactoryx.cheers.model.bus_driving_OLD.VCard> getVCards() {
        return vCards;
    }

    private void setCardTextures() {
        for (com.gamefactoryx.cheers.model.bus_driving_OLD.VCard vcard : vCards) {
            com.gamefactoryx.cheers.model.bus_driving_OLD.Card card = new com.gamefactoryx.cheers.model.bus_driving_OLD.Card(vcard.getCardIndex(), com.gamefactoryx.cheers.model.bus_driving_OLD.Card.CardSize.BIG);
            cardTextures.put(card.getFileName(com.gamefactoryx.cheers.model.bus_driving_OLD.Card.CardSize.BIG, com.gamefactoryx.cheers.model.bus_driving_OLD.VCard.CardOrientation.FACE), new Texture(card.getFileName(com.gamefactoryx.cheers.model.bus_driving_OLD.Card.CardSize.BIG, com.gamefactoryx.cheers.model.bus_driving_OLD.VCard.CardOrientation.FACE)));
            cardTextures.put(card.getFileName(com.gamefactoryx.cheers.model.bus_driving_OLD.Card.CardSize.BIG, com.gamefactoryx.cheers.model.bus_driving_OLD.VCard.CardOrientation.BACK), new Texture(card.getFileName(com.gamefactoryx.cheers.model.bus_driving_OLD.Card.CardSize.BIG, com.gamefactoryx.cheers.model.bus_driving_OLD.VCard.CardOrientation.BACK)));
        }

        for (com.gamefactoryx.cheers.model.bus_driving_OLD.VCard vCard : vCards) {
            com.gamefactoryx.cheers.model.bus_driving_OLD.Card card = new com.gamefactoryx.cheers.model.bus_driving_OLD.Card(vCard.getCardIndex(), com.gamefactoryx.cheers.model.bus_driving_OLD.Card.CardSize.SMALL);
            cardTextures.put(card.getFileName(com.gamefactoryx.cheers.model.bus_driving_OLD.Card.CardSize.SMALL, com.gamefactoryx.cheers.model.bus_driving_OLD.VCard.CardOrientation.FACE), new Texture(card.getFileName(com.gamefactoryx.cheers.model.bus_driving_OLD.Card.CardSize.SMALL, com.gamefactoryx.cheers.model.bus_driving_OLD.VCard.CardOrientation.FACE)));
            cardTextures.put(card.getFileName(com.gamefactoryx.cheers.model.bus_driving_OLD.Card.CardSize.SMALL, com.gamefactoryx.cheers.model.bus_driving_OLD.VCard.CardOrientation.BACK), new Texture(card.getFileName(com.gamefactoryx.cheers.model.bus_driving_OLD.Card.CardSize.SMALL, com.gamefactoryx.cheers.model.bus_driving_OLD.VCard.CardOrientation.BACK)));
        }


    }

    public Map<String, Texture> getCardTextures() {
        return cardTextures;
    }


    private List<com.gamefactoryx.cheers.model.bus_driving_OLD.Player> createPlayers() {

        for (int i = 0; i < com.gamefactoryx.cheers.tool.Configuration.getMaxPlayers(); i++) {
            PlayerNameCache.clear();
            players.add(new com.gamefactoryx.cheers.model.bus_driving_OLD.Player(getFunnyName(i), i));
        }
        return players;
    }

    private void createFunnyNames() {
        FileHandle nameFile = Gdx.files.internal(com.gamefactoryx.cheers.tool.Configuration.getLanguage() + "/Busdrivingscreen/names.txt");
        String fileContent = nameFile.readString();
        String[] names = fileContent.split("\n");
        nameList = Arrays.asList(names);
        Collections.shuffle(nameList);
    }

    private String getFunnyName(int index) {

        return nameList.get(index);
    }

    private List<com.gamefactoryx.cheers.model.bus_driving_OLD.Phase> createPhases() {
        for (int i = 1; i < 4; i++)
            phases.add(new com.gamefactoryx.cheers.model.bus_driving_OLD.Phase(i));
        return phases;
    }


    public List<com.gamefactoryx.cheers.model.bus_driving_OLD.Phase> getPhases() {
        return phases;
    }

    public com.gamefactoryx.cheers.model.bus_driving_OLD.Phase getPhase() {
        return phases.get(phaseIndex);
    }

    public boolean prevPhase() {
        if (phaseIndex == 0) {
            return false;
        } else {
            --phaseIndex;
            return true;
        }
    }

    public boolean nextPhase() {
        if (phaseIndex == phases.size() - 1) {
            return false;
        } else {
            ++phaseIndex;
            if (phaseIndex == 1) {
                for (int i = 0; i < 15; i++)
                    getPhase().getBoard().addCard(croupier.getVCard());
                instance.setMessage(null);
            }else if(phaseIndex == 2){
                instance.getVCards().clear();
                instance.setVCards();
                instance.croupier.shuffle();
                instance.players.clear();
                com.gamefactoryx.cheers.model.bus_driving_OLD.VCard vcard = croupier.getVCard();
                vcard.setOrientation(com.gamefactoryx.cheers.model.bus_driving_OLD.VCard.CardOrientation.FACE);
                for(com.gamefactoryx.cheers.model.bus_driving_OLD.Player player: instance.getLoosers()) {
                    player.getVCards().clear();
                    player.getVCards().addLast(vcard);
                }
                instance.setMessage(null);
                instance.players.addAll(instance.getLoosers());
                Collections.shuffle(instance.players);
                instance.firstPlayer();

                getPhase().getBoard().addCard(croupier.getVCard());



            }
            return true;
        }
    }

    public void firstPhase() {
        phaseIndex = 0;

    }

    public void lastPhase() {
        phaseIndex = phases.size() - 1;
    }


    public com.gamefactoryx.cheers.model.bus_driving_OLD.Player getPlayer() {
        return players.get(playerIndex);
    }

    public boolean prevPlayer() {
        if (playerIndex == 0) {
            return false;
        } else {
            --playerIndex;
            return true;
        }

    }

    public boolean nextPlayer() {
        if (playerIndex == players.size() - 1) {
            return false;
        } else {
            ++playerIndex;
            return true;
        }
    }

    public void firstPlayer() {
        playerIndex = 0;

    }

    public void lastPlayer() {
        playerIndex = players.size() - 1;
    }

    protected void setPhases(List<com.gamefactoryx.cheers.model.bus_driving_OLD.Phase> phases) {
        this.phases = phases;
    }

    public void setPlayers(List<com.gamefactoryx.cheers.model.bus_driving_OLD.Player> players) {
        this.players = players;
    }

    public com.gamefactoryx.cheers.model.bus_driving_OLD.Croupier getCroupier() {
        return croupier;
    }

    public boolean isScrollPyramide() {
        return scrollPyramide;
    }

    public void setScrollPyramide(boolean scrollPyramide) {
        this.scrollPyramide = scrollPyramide;
    }

    public String getMessage() {
        if ("GO_ON".equals(message))
            switch (Configuration.getLanguage()) {
                case DE:
                    return "Weiter?";
                case EN:
                    return "Continue?";
                case SK:
                    return "Pokračovať?";
            }
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public  void result() {
        Collections.sort(players);
        loosers.clear();
        for (com.gamefactoryx.cheers.model.bus_driving_OLD.Player player : players) {
            if (loosers.size() == 0 && player.getVCards().size > 0){
                loosers.add(player);
            } else {
                if(loosers.get(0).getVCards().size == player.getVCards().size)
                    loosers.add(player);
                else
                    break;
            }

        }
    }

    public List<com.gamefactoryx.cheers.model.bus_driving_OLD.Player> getLoosers(){
        return loosers;
}

}
