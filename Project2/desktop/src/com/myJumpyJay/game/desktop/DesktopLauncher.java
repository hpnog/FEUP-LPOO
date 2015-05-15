package com.myJumpyJay.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.myJumpyJay.game.JumpyJay;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Jumpy Jay - desktop";
		config.width = 500;
		config.height = 400;
		config.resizable = false;
		new LwjglApplication(new JumpyJay(), config);
	}
}
