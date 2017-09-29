package com.gamefactoryx.cheers.tool;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bernat on 17.05.2017.
 */
public class Card {
    private static Card instance;
    private Map<String, Texture> cardTextures;

    public static Card getInstance()
    {
           if(instance == null)
                instance = new Card();

            return instance;
    }

    public Texture getCardTexture(int cardIndex, CardSize cardSize, CardOrientation cardOrientation) {

        return cardTextures.get(getFileName(cardIndex, cardSize, cardOrientation));
    }

    private Card() {
        cardTextures = new HashMap<>();
        setCardTextures();
    }

    private String getFileName(int cardIndex, CardSize cardSize, CardOrientation orientation) {
        CardType type;
        if (orientation == CardOrientation.BACK) {
            if (cardSize == CardSize.BIG)
                return "common/busdriving_cards/facedown_big_card.png";
            else
                return "common/busdriving_cards/facedown_small_card.png";
        } else {
            String prefix;

            if (cardIndex >= 2 && cardIndex <= 14) {
                type = CardType.HEART;
                prefix = "heart";
            } else if (cardIndex >= 15 && cardIndex <= 27) {
                type = CardType.SPADE;
                prefix = "spades";
            } else if (cardIndex >= 28 && cardIndex <= 40) {
                type = CardType.DIAMOND;
                prefix = "diamond";
            } else {
                type = CardType.CLUB;
                prefix = "clubs";
            }
            return ("common/busdriving_cards/" + prefix + "_" + cardSize.value() + "/" + type.value() + "_" + getName(cardIndex) + ".png").toLowerCase();
        }
    }

    private void setCardTextures() {
        if (cardTextures.size() == 0) {
            for (int i = 2; i < 53; i++) {
                cardTextures.put(getFileName(i, CardSize.BIG, CardOrientation.FACE), new Texture(getFileName(i, CardSize.BIG, CardOrientation.FACE)));
                cardTextures.put(getFileName(i, CardSize.BIG, CardOrientation.BACK), new Texture(getFileName(i, CardSize.BIG, CardOrientation.BACK)));
                cardTextures.put(getFileName(i, CardSize.SMALL, CardOrientation.FACE), new Texture(getFileName(i, CardSize.SMALL, CardOrientation.FACE)));
                cardTextures.put(getFileName(i, CardSize.SMALL, CardOrientation.BACK), new Texture(getFileName(i, CardSize.SMALL, CardOrientation.BACK)));
            }
        }
    }

    private String getName(int index) {
        switch (index) {
            case 2:
            case 15:
            case 28:
            case 41:
                return "2";
            case 3:
            case 16:
            case 29:
            case 42:
                return "3";
            case 4:
            case 17:
            case 30:
            case 43:
                return "4";
            case 5:
            case 18:
            case 31:
            case 44:
                return "5";
            case 6:
            case 19:
            case 32:
            case 45:
                return "6";
            case 7:
            case 20:
            case 33:
            case 46:
                return "7";
            case 8:
            case 21:
            case 34:
            case 47:
                return "8";
            case 9:
            case 22:
            case 35:
            case 48:
                return "9";
            case 10:
            case 23:
            case 36:
            case 49:
                return "10";
            case 11:
            case 24:
            case 37:
            case 50:
                return "b";
            case 12:
            case 25:
            case 38:
            case 51:
                return "d";
            case 13:
            case 26:
            case 39:
            case 52:
                return "k";
            case 14:
            case 27:
            case 40:
            case 53:
                return "a";
        }
        return "X";
    }

    private void dispose(){

    }
}
