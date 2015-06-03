package com.mygdx.game;

public class Box2DVariables {
	
	//PIXELS PER METER
	public static final float PPM = 100;
	
	//CATEGORY BITS
	//0000 0000 0000 0010
	public static final short BIT_FLOOR = 2;
	
	//0000 0000 0000 0100
	public static final short BIT_ROBOT = 4;
	
	//0000 0000 0000 0110
	public static final short BIT_FLOOR_ROBOT = 6;
	
	//0000 0000 0000 1000
	public static final short BIT_KEY = 8;
	
	//0000 0000 0001 0000
	public static final short BIT_DIAMOND = 16;
	
	//0000 0000 0010 0100
	public static final short BIT_ROBOT_FOOT = 36;
	
	//0000 0000 0100 0000
	public static final short BIT_DOOR = 64;
	
	//0000 0000 1000 0000
	public static final int BIT_DOOR_CENTER = 128;
}
