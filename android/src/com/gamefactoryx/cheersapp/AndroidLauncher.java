package com.gamefactoryx.cheersapp;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.gamefactoryx.cheersapp.controller.StageManager;
import com.gamefactoryx.cheersapp.tool.Configuration;
import com.google.android.gms.ads.*;

public class AndroidLauncher extends AndroidApplication implements ScreenLock, ActivityRequestHandler, InterstitialResolver {
    private CheersGdxGame game;
    protected AdView adView;
    protected View gameView;
    private InterstitialAd interstitialAd;

    private final int SHOW_ADS = 1;
    private final int HIDE_ADS = 0;
    private static final String AD_UNIT_ID = "ca-app-pub-4210471520715681/8370424825";

    protected Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SHOW_ADS: {
                    if (!Configuration.isPremium() && !game.isAdMobVisible()) {
                        AdRequest adRequest = new AdRequest.Builder().build();
                        adView.loadAd(adRequest);
                    }
                    break;
                }
                case HIDE_ADS: {
                    if (!Configuration.isPremium()) {
                        adView.setVisibility(View.GONE);
                        game.setAdMobVisible(false);
                    }
                    break;
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MobileAds.initialize(this, "ca-app-pub-4210471520715681~6207295910");
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

        // Create the layout
        RelativeLayout layout = new RelativeLayout(this);
        // Add the AdMob view

        layout.addView(createAdView());
        layout.addView(createGameView(config));

        //===== detect operating system and Configure platform dependent code ==========================

		/*if(game.getAppStore() == CheersGdxGame.APPSTORE_OUYA) {
			CheersGdxGame.setPlatformResolver(new OUYAResolver(game));
		}*/
        if (game.getAppStore() == CheersGdxGame.APPSTORE_GOOGLE) {
            CheersGdxGame.setPlatformResolver(new AndroidResolver(game, this));
        }
		/*else if(game.getAppStore() == CheersGdxGame.APPSTORE_AMAZON) {
			CheersGdxGame.setPlatformResolver(new AmazonFireResolver(game, this));

		}*/

        //=============== create and setup AdMob view =====================

        setContentView(layout);
        adView.setVisibility(View.GONE);
        initInterstitial();
        startAdvertising(adView);

    }

    /*@Override
    public void showAds(boolean show) {
        handler.sendEmptyMessage(show ? SHOW_ADS : HIDE_ADS);
    }*/

    private void initInterstitial() {
        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId("ca-app-pub-4210471520715681/6129196875");
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                //Toast.makeText(getApplicationContext(), "Finished Loading Interstitial", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdClosed() {
                loadInterstitial();
                //Toast.makeText(getApplicationContext(), "Closed Interstitial", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadInterstitial() {
        try {
            AdRequest interstitialRequest = new AdRequest.Builder().build();
            if (!interstitialAd.isLoading()) {
                interstitialAd.loadAd(interstitialRequest);
            }
        } catch (Exception e) {

        }

    }

    private AdView createAdView() {
        adView = new AdView(this);
        adView.setAdSize(AdSize.SMART_BANNER);
        adView.setAdUnitId(AD_UNIT_ID);
        //adView.setId(12345); // this is an arbitrary id, allows for relative positioning in createGameView()
        adView.setId(R.id.adViewId);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
        adView.setLayoutParams(params);
        adView.setBackgroundColor(Color.BLACK);
        adView.setAdListener(new AdListener() {

            @Override
            public void onAdOpened() {
                super.onAdOpened();
                StageManager.getInstance().getGame().setAdMobActivated(false);
                StageManager.getInstance().getGame().getAdMobRequestHandler().showAds(false);
            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                StageManager.getInstance().getGame().setAdMobActivated(false);
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                if (!Configuration.isPremium()) {
                    adView.setVisibility(View.VISIBLE);
                    StageManager.getInstance().getGame().setAdMobHeight(50/*adView.getMeasuredHeight()*/);
                    game.setAdMobVisible(true);
                    StageManager.getInstance().getGame().setAdMobActivated(true);
                }

            }
        });
        return adView;
    }

    private View createGameView(AndroidApplicationConfiguration cfg) {
        game = new CheersGdxGame(this, new AndroidFacebookLinkHandler(this), this, this);
        gameView = initializeForView(game, cfg);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
        params.addRule(RelativeLayout.ABOVE, adView.getId());
        gameView.setLayoutParams(params);
        return gameView;
    }

    private void startAdvertising(AdView adView) {
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        showOrLoadInterstitial();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void showOrLoadInterstitial() {
        if (!Configuration.isPremium()) {
            try {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if (interstitialAd.isLoaded()) {
                                interstitialAd.show();
                            } else {
                                loadInterstitial();
                            }
                        } catch (Exception e) {
                        }
                    }
                });
            } catch (Exception e) {
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //  InApp: dispose payment system(s)
        game.getPlatformResolver().dispose();
        adView.destroy();

    }

    @Override
    protected void onResume() {
        super.onResume();
        adView.resume();
        gameView.requestFocus();
        gameView.requestFocusFromTouch();

    }

    @Override
    protected void onPause() {
        super.onPause();
        adView.pause();
    }


    @Override
    public void lock(int type) {
        this.setRequestedOrientation(type);
    }

    @Override
    public void unlock() {
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
    }

    @Override
    public void showAds(boolean show) {
        handler.sendEmptyMessage(show && StageManager.getInstance().getGame().isAdMobActivated() && !Configuration.isPremium() ? SHOW_ADS : HIDE_ADS);
    }

}
