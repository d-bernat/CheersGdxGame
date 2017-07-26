package com.gamefactoryx.cheers.model.kongosdrink;

import com.gamefactoryx.cheers.tool.kongosdrink.Configuration;

/**
 * Created by bernat on 25.07.2017.
 */

public class Card {

    public enum SubTypeEnum{A, B, C, D, E, F, G;}
    public enum DepictLabelEnum {CHANGES,
        DRINK_TOGETHER, MIRROR, NOTALKING, PIG,
        PRESENT, RULES, STAR, WC, YODA;}
    private final DepictLabelEnum depictLabel;
    private final String text;
    private final Configuration.GameType gameType;
    private final SubTypeEnum subType;
    private final int point;
    private final int sip;


    public Card(DepictLabelEnum depictLabel, String text, Configuration.GameType gameType, SubTypeEnum subType, int point, int sip) {
        this.depictLabel = depictLabel;
        this.text = text;
        this.gameType = gameType;
        this.subType  = subType;
        this.point = point;
        this.sip = sip;
    }

    public DepictLabelEnum getDepictLabel() {
        return depictLabel;
    }

    public String getText() {
        return text;
    }

    public Configuration.GameType getGameType() {
        return gameType;
    }

    public SubTypeEnum getSubType() {
        return subType;
    }

    public int getPoint() {
        return point;
    }

    public int getSip() {
        return sip;
    }
}
