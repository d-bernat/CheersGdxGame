package com.gamefactoryx.cheers;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.gamefactoryx.cheers.controller.StageEnum;
import com.gamefactoryx.cheers.controller.StageManager;
import com.gamefactoryx.cheers.controller.kongosdrink.KongosDrinkStageManager;
import com.gamefactoryx.cheers.tool.Card;
import com.gamefactoryx.cheers.tool.Resolution;

import java.util.Timer;

public class CheersGdxGame extends Game {

	private static ScreenLock screenLock;

	public CheersGdxGame(ScreenLock screenLock){
		this.screenLock = screenLock;
	}
	@Override
	public void create () {
		//screenLock.setOrientationPortrait();
		StageManager.getInstance().initialize(this);
		KongosDrinkStageManager.getInstance().initialize(this);
		Resolution.setResolution();
		StageManager.getInstance().showStage(StageEnum.SPLASH_STAGE);

	}

	public static ScreenLock getScreenLock(){
		return screenLock;
	}

}
