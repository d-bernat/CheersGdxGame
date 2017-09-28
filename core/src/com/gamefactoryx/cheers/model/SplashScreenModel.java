package com.gamefactoryx.cheers.model;

public class SplashScreenModel {
    private static SplashScreenModel instance;
    private boolean logoUp = true;
    private SplashScreenModel(){}

    public static SplashScreenModel getInstance() {
        if (instance == null) {
            instance = new SplashScreenModel();
        }
        return instance;
    }

    public boolean isLogoUp() {
        return logoUp;
    }

    public void setLaterLogoDown(final long timeout) {
        new Thread() {
            @Override
            public void run(){
                long time = System.currentTimeMillis();
                while(System.currentTimeMillis() < time + timeout ){
                }
                logoUp = false;
            }
        }
        .start();
    }
}
