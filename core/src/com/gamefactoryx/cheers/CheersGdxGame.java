package com.gamefactoryx.cheers;

import com.badlogic.gdx.Game;
import com.gamefactoryx.cheers.manager.ScreenEnum;
import com.gamefactoryx.cheers.manager.ScreenManager;
import com.gamefactoryx.cheers.tool.Resolution;

public class CheersGdxGame extends Game {

	@Override
	public void create () {
		ScreenManager.getInstance().initialize(this);
		Resolution.setResolution();
		ScreenManager.getInstance().showScreen(ScreenEnum.MAIN_SCREEN);
	}

}
