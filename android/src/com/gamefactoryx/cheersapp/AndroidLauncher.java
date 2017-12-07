package com.gamefactoryx.cheersapp;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.gamefactoryx.cheersapp.tool.Configuration;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class AndroidLauncher extends AndroidApplication implements ScreenLock, ActivityRequestHandler {
    private CheersGdxGame game;
    protected AdView adView;
    protected View gameView;

    private final int SHOW_ADS = 1;
    private final int HIDE_ADS = 0;
    private static final String AD_UNIT_ID = "ca-app-pub-4210471520715681/8370424825";
    private static final String GOOGLE_PLAY_URL = "https://play.google.com/store/apps/developer?id=TheInvader360";

    protected Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SHOW_ADS: {
                    if(!Configuration.isPremium())
                        adView.setVisibility(View.VISIBLE);
                    else
                        adView.setVisibility(View.GONE);
                    break;
                }
                case HIDE_ADS: {
                    adView.setVisibility(View.GONE);
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
        startAdvertising(adView);
    }

    /*@Override
    public void showAds(boolean show) {
        handler.sendEmptyMessage(show ? SHOW_ADS : HIDE_ADS);
    }*/


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
        return adView;
    }

    private View createGameView(AndroidApplicationConfiguration cfg) {
        game = new CheersGdxGame(this, new AndroidFacebookLinkHandler(this), this);
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
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //  InApp: dispose payment system(s)
        game.getPlatformResolver().dispose();
        adView.destroy();
    }

    @Override
    protected void onResume(){
        super.onResume();
        adView.resume();
        gameView.requestFocus();
        gameView.requestFocusFromTouch();

    }

    @Override
    protected void onPause(){
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
        handler.sendEmptyMessage(show ? SHOW_ADS : HIDE_ADS);
    }


}
