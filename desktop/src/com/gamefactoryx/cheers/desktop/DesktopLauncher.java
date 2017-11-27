package com.gamefactoryx.cheers.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.gamefactoryx.cheers.CheersGdxGame;
import com.gamefactoryx.cheers.ScreenLock;

public class DesktopLauncher{
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new CheersGdxGame(null, null), config);
	}

}
