package com.gamefactoryx.cheersapp.tool;

/**
 * Created by Bernat on 02.06.2017.
 */
public enum CardType {
    CLUB("clubs"), DIAMOND("diamond"), HEART("heart"), SPADE("spades");
    private String value;

    CardType(String value) {
        this.value = value;

    }

    public String value() {
        return this.value;
    }
}
