package com.gamefactoryx.cheersapp.model.kongosdrink;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.gamefactoryx.cheersapp.tool.kongosdrink.Configuration;

import java.util.*;

/**
 * Created by bernat on 25.07.2017.
 */
public class CardFactory {
    private Map<Integer, List<com.gamefactoryx.cheersapp.model.kongosdrink.Card>> cards = new HashMap<>();

    private static CardFactory instance;

    public static CardFactory getInstance() {
        if (instance == null)
            return new CardFactory();
        else
            return instance;
    }

    public static CardFactory getNewInstance() {
        return new CardFactory();
    }

    private CardFactory() {
    }

    public Map<Integer, List<com.gamefactoryx.cheersapp.model.kongosdrink.Card>> getCards() {
        int[] level = {1, 2, 3, 5};
        cards.clear();
        String[] files;
        switch (Configuration.getInstance().getGameType()) {
            case DOGFIGHT:
                files = new String[]{"/kongosdrink/cards/single_player/drink/",
                        "/kongosdrink/cards/single_player/drink_continue/"};
                break;
            case TEAMOFTWO_VS_TEAMOFTWO:
                files = new String[]{"/kongosdrink/cards/two_teams/drink/",
                        "/kongosdrink/cards/two_teams/drink_team/"};
                break;
            case TEAM_VS_TEAM:
                files = new String[]{"/kongosdrink/cards/tag_team/drink/",
                        "/kongosdrink/cards/tag_team/drink_team/"};
                break;
            default:
                files = new String[]{"/kongosdrink/cards/single_player/drink/",
                        "/kongosdrink/cards/single_player/drink_continue/"};
        }
        for (int ll : level) {

            cards.put(ll, getCardsFromFile(
                    files, ll));
        }
        return cards;
    }

    private List<com.gamefactoryx.cheersapp.model.kongosdrink.Card> getCardsFromFile(String[] subdirectories, int level) {
        List<com.gamefactoryx.cheersapp.model.kongosdrink.Card> cs = new ArrayList<>();
        for (String subdirectory : subdirectories) {
            FileHandle taskFile = Gdx.files.internal(com.gamefactoryx.cheersapp.tool.Configuration.getLanguage() +
                    subdirectory + "level" + level + ".txt");
            for (String line : taskFile.readString("UTF-8").split("\n")) {
                String[] token = line.split(";");
                if (token.length == 4) {
                    try {
                        com.gamefactoryx.cheersapp.model.kongosdrink.Card card = new com.gamefactoryx.cheersapp.model.kongosdrink.Card(token[0], Integer.parseInt(token[1].trim()),
                                Integer.parseInt(token[2].trim()),
                                Integer.parseInt(token[3].trim()));
                        cs.add(card);
                    } catch (Exception e) {
                    }
                }
            }
        }
        Collections.shuffle(cs);
        return cs;
    }
}
