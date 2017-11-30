package com.gamefactoryx.cheersapp.model.busdriving;

import com.gamefactoryx.cheersapp.tool.CardOrientation;

/**
 * Created by bernat on 23.05.2017.
 */
public class VCard {
    private Integer cardIndex;
    private int credit;
    private int value;


    private CardOrientation orientation;
    public VCard(Integer cardIndex, CardOrientation orientation) {
        this.cardIndex = cardIndex;
        this.orientation = orientation;
        this.value = getValue();
    }

    public int getValue() {
        int value = 0;
        switch (cardIndex) {
            case 2:
            case 15:
            case 28:
            case 41:
                value = 2;
                break;
            case 3:
            case 16:
            case 29:
            case 42:
                value = 3;
                break;
            case 4:
            case 17:
            case 30:
            case 43:
                value = 4;
                break;
            case 5:
            case 18:
            case 31:
            case 44:
                value = 5;
                break;
            case 6:
            case 19:
            case 32:
            case 45:
                value = 6;
                break;
            case 7:
            case 20:
            case 33:
            case 46:
                value = 7;
                break;
            case 8:
            case 21:
            case 34:
            case 47:
                value = 8;
                break;
            case 9:
            case 22:
            case 35:
            case 48:
                value = 9;
                break;
            case 10:
            case 23:
            case 36:
            case 49:
                value = 10;
                break;
            case 11:
            case 24:
            case 37:
            case 50:
                value = 11;
                break;
            case 12:
            case 25:
            case 38:
            case 51:
                value = 12;
                break;
            case 13:
            case 26:
            case 39:
            case 52:
                value = 13;
                break;
            case 14:
            case 27:
            case 40:
            case 53:
                value = 14;
                break;
        }
        return value;
    }

    public CardOrientation getOrientation() {
        return orientation;
    }

    public void setOrientation(CardOrientation orientation) {
        this.orientation = orientation;
    }

    public Integer getCardIndex() {
        return cardIndex;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    @Override
    public boolean equals(Object o) {
        if (o != null && o instanceof VCard) {
            return ((VCard) o).getValue() == this.getValue();
        }
        return false;
    }

}
