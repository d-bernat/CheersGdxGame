package com.gamefactoryx.cheers;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.gamefactoryx.cheers.controller.StageEnum;
import com.gamefactoryx.cheers.controller.StageManager;
import com.gamefactoryx.cheers.tool.Resolution;

public class CheersGdxGame extends Game {

	private static ScreenLock screenLock;

	public CheersGdxGame(ScreenLock screenLock){
		this.screenLock = screenLock;
	}
	@Override
	public void create () {
		//screenLock.setOrientationPortrait();
		StageManager.getInstance().initialize(this);
		Resolution.setResolution();
		Music mp3Music = Gdx.audio.newMusic(Gdx.files.internal("common/cheers_musik.mp3"));
		mp3Music.play();
		StageManager.getInstance().showStage(StageEnum.MAIN_STAGE);
	}

	public static ScreenLock getScreenLock(){
		return screenLock;
	}

}
