package jumpyjay.handlers;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.Vector2;

// TODO: Auto-generated Javadoc
/**
 * The Class SingletonVandC.
 */
public class SingletonVandC {

	/** The singleton. */
	private static SingletonVandC singleton = new SingletonVandC();
	
	//PIXELS PER METER
	/** The ppm. */
	public final float PPM = 100;
	//CATEGORY BITS
	//0000 0000 0000 0010
	/** The bit floor. */
	public final short BIT_FLOOR = 2;
	//0000 0000 0000 0100
	/** The bit robot. */
	public final short BIT_ROBOT = 4;
	//0000 0000 0000 0110
	/** The bit floor robot. */
	public final short BIT_FLOOR_ROBOT = 6;
	//0000 0000 0000 1000
	/** The bit key. */
	public final short BIT_KEY = 8;
	//0000 0000 0001 0000
	/** The bit diamond. */
	public final short BIT_DIAMOND = 16;
	//0000 0000 0010 0100
	/** The bit robot foot. */
	public final short BIT_ROBOT_FOOT = 36;
	//0000 0000 0100 0000
	/** The bit door. */
	public final short BIT_DOOR = 64;
	//0000 0000 1000 0000
	/** The bit door center. */
	public final int BIT_DOOR_CENTER = 128;
	//0000 0001 0000 0000
	/** The bit danger. */
	public final short BIT_DANGER = 256;
	
	/** The jump force y. */
	public float JUMP_FORCE_Y = 3.75f;
	
	/** The speed x. */
	public float SPEED_X = 1.3f;
	
	/** The jump ready. */
	public int jumpReady = 0;
	
	/** The screen width. */
	public int SCREEN_WIDTH;
	
	/** The screen height. */
	public int SCREEN_HEIGHT;
	
	/** The exiting. */
	public int exiting;
	
	/** The lose life. */
	public int loseLife = 0;
	
	/** The level score. */
	public int levelScore = 0;
	
	/** The menu. */
	public final int MENU = 0;
	
	/** The play. */
	public final int PLAY = 1;
	
	/** The level. */
	public final int LEVEL = 2;
	
	/** The success. */
	public final int SUCCESS = 3;
	
	/** The sound. */
	public static boolean sound = true;
	
	/** The music. */
	public static boolean music = true;
	
	/** The paused. */
	public static int paused;
	
	/** The music id. */
	public static long musicId;
	
	/** The testing. */
	public static boolean testing = false;
	
	//Inicializa o click----------------------------------------------------------------------------------
	/** The click. */
	public Click click = new Click();
	//----------------------------------------------------------------------------------------------------

	/** The initial positions. */
	public static Vector2[] initialPositions;
	
	/** The asset manager. */
	public Assets assetManager;
	
	/** The total score. */
	public static int[] totalScore;
	
	/** The current level. */
	public static int currentLevel;
	
	/** The screen shot. */
	public Pixmap screenShot;
	
	/**
	 * Instantiates a new singleton vand c.
	 */
	private SingletonVandC() {
		assetManager = new Assets();
		initializeInitialPos();
	}
	
	/**
	 * Gets the singleton.
	 *
	 * @return the singleton
	 */
	public static SingletonVandC getSingleton(){
		return singleton;
	}
	
	/**
	 * Initialize initial pos.
	 */
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
		initialPositions[8] = new Vector2(8 , 2);
		initialPositions[9] = new Vector2(2 , 10);
	}
}
