package com.gamefactoryx.cheers.model;

public abstract class Model {
    private boolean showRules = true;
    private boolean showRulesText;
    protected String rulesText;

    public Model(){
        initRulesText();
    }

    public boolean isShowRules() {
        return showRules;
    }

    public void setShowRules(boolean showRules) {
        this.showRules = showRules;
    }

    public void setShowRulesText(boolean showRulesText) {
        this.showRulesText = showRulesText;
    }

    public String getRulesText(){
        return rulesText;
    };

    protected abstract void initRulesText();
    public boolean isShowRulesText() {
        return showRulesText;
    }

}
