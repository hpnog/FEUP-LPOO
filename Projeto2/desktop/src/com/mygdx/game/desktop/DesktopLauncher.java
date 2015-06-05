package com.mygdx.game.desktop;

import jumpyjay.game.MyJumpyJay;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		config.title = "Jumpy Jay";
		config.width = 600;
		config.height = 350;
		config.resizable = false;
		
		new LwjglApplication(new MyJumpyJay(), config);
	}
}
