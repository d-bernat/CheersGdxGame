package com.gamefactoryx.cheers.tool;

/**
 * Created by Bernat on 02.06.2017.
 */
public enum CardSize {
    SMALL("SMALL"), BIG("BIG");
    private String value;

    CardSize(String value) {
        this.value = value;

    }

    public String value() {
        return this.value;
    }
}
