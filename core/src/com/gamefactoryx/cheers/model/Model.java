package com.gamefactoryx.cheers.model;

public abstract class Model {
    private boolean showRules = true;
    private boolean showRulesText;

    public boolean isShowRules() {
        return showRules;
    }

    public void setShowRules(boolean showRules) {
        this.showRules = showRules;
    }

    abstract public String getRulesText();

    public boolean isShowRulesText() {
        return showRulesText;
    }

    public void setShowRulesText(boolean showRulesText) {
        this.showRulesText = showRulesText;
    }
}
