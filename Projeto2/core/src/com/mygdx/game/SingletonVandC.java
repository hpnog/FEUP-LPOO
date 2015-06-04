package com.mygdx.game;

import handlers.Assets;

public class SingletonVandC {

	private static SingletonVandC singleton = new SingletonVandC();
	
	//PIXELS PER METER
	public final float PPM = 100;
	//CATEGORY BITS
	//0000 0000 0000 0010
	public final short BIT_FLOOR = 2;
	//0000 0000 0000 0100
	public final short BIT_ROBOT = 4;
	//0000 0000 0000 0110
	public final short BIT_FLOOR_ROBOT = 6;
	//0000 0000 0000 1000
	public final short BIT_KEY = 8;
	//0000 0000 0001 0000
	public final short BIT_DIAMOND = 16;
	//0000 0000 0010 0100
	public final short BIT_ROBOT_FOOT = 36;
	//0000 0000 0100 0000
	public final short BIT_DOOR = 64;
	//0000 0000 1000 0000
	public final int BIT_DOOR_CENTER = 128;
	//0000 0001 0000 0000
	public final short BIT_DANGER = 256;
	
	public float JUMP_FORCE_Y = 3.75f;
	public float SPEED_X = 1.3f;
	public int jumpReady = 0;
	public int SCREEN_WIDTH;
	public int SCREEN_HEIGHT;
	public int exiting;
	public int loseLife = 0;
	public int levelScore = 0;
	
	public final int MENU = 0;
	public final int PLAY = 1;
	public final int LEVEL = 2;
	
	public Assets assetManager;
	
	private SingletonVandC() {
		assetManager = new Assets();
	}
	
	public static SingletonVandC getSingleton(){
		return singleton;
	}
}
