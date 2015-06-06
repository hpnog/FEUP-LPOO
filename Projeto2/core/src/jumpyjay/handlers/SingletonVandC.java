package jumpyjay.handlers;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.Vector2;

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
	public final int SUCCESS = 3;
	
	public static int paused;
	
	public static boolean testing = false;
	
	//Inicializa o click----------------------------------------------------------------------------------
	public Click click = new Click();
	//----------------------------------------------------------------------------------------------------

	public static Vector2[] initialPositions;
	
	public Assets assetManager;
	
	public static int[] totalScore;
	public static int currentLevel;
	
	public Pixmap screenShot;
	
	private SingletonVandC() {
		assetManager = new Assets();
		initializeInitialPos();
	}
	
	public static SingletonVandC getSingleton(){
		return singleton;
	}
	
	private void initializeInitialPos()
	{
		initialPositions = new Vector2[10];
		initialPositions[0] = new Vector2(2 , 7);
		initialPositions[1] = new Vector2(2 , 6);
		initialPositions[2] = new Vector2(2 , 7);
		initialPositions[3] = new Vector2(2 , 8);
		initialPositions[4] = new Vector2(2 , 9);
		initialPositions[5] = new Vector2(2 , 7);
		initialPositions[6] = new Vector2(2 , 7);
		initialPositions[7] = new Vector2(2 , 2);
		//initialPositions[8] = new Vector2( , );
		initialPositions[9] = new Vector2(2 , 15);
	}
}
