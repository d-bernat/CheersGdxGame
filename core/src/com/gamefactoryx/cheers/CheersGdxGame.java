package com.gamefactoryx.cheers;

import com.badlogic.gdx.Game;
import com.gamefactoryx.cheers.controller.StageEnum;
import com.gamefactoryx.cheers.controller.StageManager;
import com.gamefactoryx.cheers.controller.kongosdrink.KongosDrinkStageManager;
import com.gamefactoryx.cheers.tool.Resolution;

public class CheersGdxGame extends Game {

	private static ScreenLock screenLock;
	private static LinkHandler linkHandler;

	public CheersGdxGame(ScreenLock screenLock, LinkHandler linkHandler){
		this.screenLock = screenLock;
		this.linkHandler = linkHandler;
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
	public static LinkHandler getLinkHandler(){
		return linkHandler;
	}

}
