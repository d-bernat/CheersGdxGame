package com.gamefactoryx.cheers;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import android.view.View;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class AndroidLauncher extends AndroidApplication implements ScreenLock{
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
		View gameView = initializeForView(new CheersGdxGame(this, new AndroidFacebookLinkHandler(this)), config);
		setContentView(gameView);
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
