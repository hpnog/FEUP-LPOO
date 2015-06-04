package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.MyJumpyJay;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		config.title = "Jumpy Jay";
		config.width = 600;
		config.height = 300;
		config.resizable = false;
		
		new LwjglApplication(new MyJumpyJay(), config);
	}
}
