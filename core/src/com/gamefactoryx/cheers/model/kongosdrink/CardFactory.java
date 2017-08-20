package com.gamefactoryx.cheers.model.kongosdrink;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.gamefactoryx.cheers.tool.kongosdrink.Configuration;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by bernat on 25.07.2017.
 */
public class CardFactory {
    private Map<Integer,List<Card>> cards = new HashMap<>();

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

    public Map<Integer, List<Card>> getCards() {
        int[] level = {1,2,3,5};
        cards.clear();
        for(int ll: level) {
            cards.put(ll, getCardsFromFile(
                    new String[]{"/kongosdrink/cards/single_player/drink/",
                            "/kongosdrink/cards/single_player/drink-continue/"}, ll));
        }
        return cards;
    }

    private List<Card> getCardsFromFile(String[] subdirectories, int level){
        List<Card> cs = new ArrayList<>();
        for(String subdirectory: subdirectories) {
            FileHandle taskFile = Gdx.files.internal(com.gamefactoryx.cheers.tool.Configuration.getLanguage() +
                    subdirectory + "level" + level + ".txt");
            for (String line : taskFile.readString("UTF-8").split("\n")) {
                String[] token = line.split(";");
                if (token.length == 4) {
                    try {
                        Card card = new Card(token[0],  Integer.parseInt(token[1].trim()),
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
