package com.gamefactoryx.cheersapp.model.kongosdrink;

/**
 * Created by bernat on 25.07.2017.
 */

public class Card {
    private final String originText;
    private String  text;
    private final int ttl;
    private final int point;
    private final int sip;


    public Card(String originText, int point, int sip, int ttl) {
        this.originText = originText;
        this.ttl = ttl;
        this.point = point;
        this.sip = sip;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getTtl() {
        return ttl;
    }

    public int getPoint() {
        return point;
    }

    public int getSip() {
        return sip;
    }

    public String getOriginText() {
        return originText;
    }
}
