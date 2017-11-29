package com.gamefactoryx.cheersapp;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import android.view.View;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class AndroidLauncher extends AndroidApplication implements ScreenLock{
	private CheersGdxGame game;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
		game = new CheersGdxGame(this, new AndroidFacebookLinkHandler(this));
		View gameView = initializeForView(game, config);
		setContentView(gameView);
		//===== detect operating system and Configure platform dependent code ==========================

		/*if(game.getAppStore() == CheersGdxGame.APPSTORE_OUYA) {
			CheersGdxGame.setPlatformResolver(new OUYAResolver(game));
		}*/
		if(game.getAppStore() == CheersGdxGame.APPSTORE_GOOGLE) {
			CheersGdxGame.setPlatformResolver(new AndroidResolver(game, this));
		}
		/*else if(game.getAppStore() == CheersGdxGame.APPSTORE_AMAZON) {
			CheersGdxGame.setPlatformResolver(new AmazonFireResolver(game, this));

		}*/

	}

	@Override
	protected void onActivityResult (int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		//  InApp: dispose payment system(s)
		game.getPlatformResolver().dispose();
	}
		@Override
	public void lock(int type) {
		this.setRequestedOrientation(type);
	}

	@Override
	public void unlock() {
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
	}
}
