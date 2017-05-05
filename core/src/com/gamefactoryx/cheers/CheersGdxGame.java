package com.gamefactoryx.cheers;

import com.badlogic.gdx.Game;
import com.gamefactoryx.cheers.controller.StageEnum;
import com.gamefactoryx.cheers.controller.StageManager;
import com.gamefactoryx.cheers.tool.Resolution;

public class CheersGdxGame extends Game {

	@Override
	public void create () {
		StageManager.getInstance().initialize(this);
		Resolution.setResolution();
		StageManager.getInstance().showStage(StageEnum.MAIN_STAGE);
	}

}
