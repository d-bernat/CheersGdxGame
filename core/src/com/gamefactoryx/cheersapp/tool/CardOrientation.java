package com.gamefactoryx.cheersapp.tool;

/**
 * Created by Bernat on 02.06.2017.
 */
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
