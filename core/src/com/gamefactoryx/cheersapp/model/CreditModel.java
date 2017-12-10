package com.gamefactoryx.cheersapp.model;

public class CreditModel {
    private static CreditModel instance;
    private CreditModel(){}
    private float yOffset;
    private float maxYOffset;
    private boolean animate;


    public static CreditModel getInstance() {
        if (instance == null) {
            instance = new CreditModel();
        }
        return instance;
    }

    public float getyOffset() {
        return yOffset;
    }

    public void setyOffset(float yOffset) {
        this.yOffset = yOffset;
    }

    public float getMaxYOffset() {
        return maxYOffset;
    }

    public void setMaxYOffset(float maxYOffset) {
        this.maxYOffset = maxYOffset;
    }

    public void startAnimation(){
        animate = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (CreditModel.getInstance().getyOffset() > CreditModel.getInstance().getMaxYOffset() && animate) {
                    long time = System.currentTimeMillis();
                    while (System.currentTimeMillis() < time + 5L) {
                    }
                    CreditModel.getInstance().setyOffset(CreditModel.getInstance().getyOffset() - 1);

                }
                animate = false;
            }

        }).start();
    }

    public boolean isAnimate() {
        return animate;
    }

    public void setAnimate(boolean animate) {
        this.animate = animate;
    }
}

