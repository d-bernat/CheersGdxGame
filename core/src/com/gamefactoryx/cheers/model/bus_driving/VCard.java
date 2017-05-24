package com.gamefactoryx.cheers.model.bus_driving;

/**
 * Created by bernat on 23.05.2017.
 */
public class VCard {
    private Integer cardIndex;
    private int credit;

    public enum CardOrientation {
        FACE("face"), BACK("back");
        private String value;

        CardOrientation(String value) {
            this.value = value;

        }

        public String value() {
            return this.value;
        }

    }

    private CardOrientation orientation;


    public VCard(Integer cardIndex, CardOrientation orientation){
        this.cardIndex = cardIndex;
        this.orientation = orientation;
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
}
