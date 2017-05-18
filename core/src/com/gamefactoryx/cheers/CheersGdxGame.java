package com.gamefactoryx.cheers;

import com.badlogic.gdx.Game;
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
		StageManager.getInstance().showStage(StageEnum.MAIN_STAGE);
	}

	public static ScreenLock getScreenLock(){
		return screenLock;
	}

}
