package com.gamefactoryx.cheers.model.kongosdrink;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.gamefactoryx.cheers.tool.kongosdrink.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bernat on 25.07.2017.
 */
public class CardFactory {
    private List<Card> cards = new ArrayList<>();
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
        FileHandle taskFile = Gdx.files.internal(com.gamefactoryx.cheers.tool.Configuration.getLanguage() +
                "/kongosdrink/cards/Einzelspieler.cardtyp1/Kartentyp1.txt");
        taskFile.readString("UTF-8");

        switch (Configuration.getGameType()) {
            case DOGFIGHT:
                for (int i = 1; i <= 1; i++) {
                    Card card = new Card(Card.DepictLabelEnum.DRINK_TOGETHER,
                            "Du muss <<-PLAYER>> einen Kuss auf die\n" +
                                    "Wange geben, ist ja widerlich trink einfach 3 mal.",
                            Configuration.GameType.DOGFIGHT,
                            Card.SubTypeEnum.C,
                            1,
                            3);
                    cards.add(card);
                    card = new Card(Card.DepictLabelEnum.DRINK_TOGETHER,
                            "Gib allen anwesenden Frauen einen Kuss \n" +
                                    "auf die Wange oder trinke 4 Mal.",
                            Configuration.GameType.DOGFIGHT,
                            Card.SubTypeEnum.C,
                            1,
                            3);
                    cards.add(card);
                    card = new Card(Card.DepictLabelEnum.DRINK_TOGETHER,
                            "Gib allen anwesenden Jungs einen \n" +
                                    "Kuss auf die Wange oder trinke 4 Mal.",
                            Configuration.GameType.DOGFIGHT,
                            Card.SubTypeEnum.C,
                            1,
                            4);
                    cards.add(card);
                    card = new Card(Card.DepictLabelEnum.DRINK_TOGETHER,
                            "Gib allen anwesenden Jungs einen \n" +
                                    "Kuss auf die Wange oder trinke 4 Mal.",
                            Configuration.GameType.DOGFIGHT,
                            Card.SubTypeEnum.C,
                            1,
                            4);
                    cards.add(card);
                }
        }
    }

    public List getCards() {
        return cards;
    }

}
