package com.amaterasu12.raidenredux.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.amaterasu12.raidenredux.RaidenRedux;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Raiiiiiden Redux";
		config.width = RaidenRedux.W_WIDTH;
		config.height = RaidenRedux.W_HEIGHT;
		new LwjglApplication(new RaidenRedux(), config);
	}
}
