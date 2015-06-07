package jumpyjay.handlers;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;


/**
 * The Class Assets.
 */
public class Assets {
	
	/** The Constant manager. */
	public static final AssetManager manager = new AssetManager();
	
	/** The Constant diamond. */
	public static final AssetDescriptor<Texture> diamond = new AssetDescriptor<Texture>("maps/diamonds/diamond.png", Texture.class);	
	
	/** The Constant robotRight. */
	public static final AssetDescriptor<Texture> robotRight = new AssetDescriptor<Texture>("maps/robots/robotwalkright.png", Texture.class);	
	
	/** The Constant robotLeft. */
	public static final AssetDescriptor<Texture> robotLeft = new AssetDescriptor<Texture>("maps/robots/robotwalkleft.png", Texture.class);	
	
	/** The Constant robotJumpLeft. */
	public static final AssetDescriptor<Texture> robotJumpLeft = new AssetDescriptor<Texture>("maps/robots/robotjumpleft.png", Texture.class);	
	
	/** The Constant robotJumpRight. */
	public static final AssetDescriptor<Texture> robotJumpRight = new AssetDescriptor<Texture>("maps/robots/robotjumpright.png", Texture.class);	
	
	/** The Constant key. */
	public static final AssetDescriptor<Texture> key = new AssetDescriptor<Texture>("maps/keys/key.png", Texture.class);	
	
	/** The Constant openedDoor. */
	public static final AssetDescriptor<Texture> openedDoor = new AssetDescriptor<Texture>("images/openedDoor.png", Texture.class);	
	
	/** The Constant closedDoor. */
	public static final AssetDescriptor<Texture> closedDoor = new AssetDescriptor<Texture>("images/closedDoor.png", Texture.class);	
	
	/** The Constant robotExit. */
	public static final AssetDescriptor<Texture> robotExit = new AssetDescriptor<Texture>("images/robotexit.png", Texture.class);	
	
	/** The Constant zeroLives. */
	public static final AssetDescriptor<Texture> zeroLives = new AssetDescriptor<Texture>("maps/hp/zero_health.png", Texture.class);	
	
	/** The Constant oneLife. */
	public static final AssetDescriptor<Texture> oneLife = new AssetDescriptor<Texture>("maps/hp/one_health.png", Texture.class);	
	
	/** The Constant twoLifes. */
	public static final AssetDescriptor<Texture> twoLifes = new AssetDescriptor<Texture>("maps/hp/two_health.png", Texture.class);	
	
	/** The Constant fullLife. */
	public static final AssetDescriptor<Texture> fullLife = new AssetDescriptor<Texture>("maps/hp/full_health.png", Texture.class);	
	
	/** The Constant backgroundMenu. */
	public static final AssetDescriptor<Texture> backgroundMenu = new AssetDescriptor<Texture>("menu/menu.png", Texture.class);	
	
	/** The Constant tapToStart. */
	public static final AssetDescriptor<Texture> tapToStart = new AssetDescriptor<Texture>("menu/taptostart.png", Texture.class);	
	
	/** The Constant levelMenu. */
	public static final AssetDescriptor<Texture> levelMenu = new AssetDescriptor<Texture>("levelMenu/levelmenu.png", Texture.class);	
	
	/** The Constant levelOne. */
	public static final AssetDescriptor<Texture> levelOne = new AssetDescriptor<Texture>("levelMenu/level1.png", Texture.class);	
	
	/** The Constant levelTwo. */
	public static final AssetDescriptor<Texture> levelTwo = new AssetDescriptor<Texture>("levelMenu/level2.png", Texture.class);	
	
	/** The Constant levelThree. */
	public static final AssetDescriptor<Texture> levelThree = new AssetDescriptor<Texture>("levelMenu/level3.png", Texture.class);	
	
	/** The Constant levelFour. */
	public static final AssetDescriptor<Texture> levelFour = new AssetDescriptor<Texture>("levelMenu/level4.png", Texture.class);	
	
	/** The Constant levelFive. */
	public static final AssetDescriptor<Texture> levelFive = new AssetDescriptor<Texture>("levelMenu/level5.png", Texture.class);	
	
	/** The Constant levelSix. */
	public static final AssetDescriptor<Texture> levelSix = new AssetDescriptor<Texture>("levelMenu/level6.png", Texture.class);	
	
	/** The Constant levelSeven. */
	public static final AssetDescriptor<Texture> levelSeven = new AssetDescriptor<Texture>("levelMenu/level7.png", Texture.class);	
	
	/** The Constant levelEight. */
	public static final AssetDescriptor<Texture> levelEight = new AssetDescriptor<Texture>("levelMenu/level8.png", Texture.class);	
	
	/** The Constant levelNine. */
	public static final AssetDescriptor<Texture> levelNine = new AssetDescriptor<Texture>("levelMenu/level9.png", Texture.class);	
	
	/** The Constant levelTen. */
	public static final AssetDescriptor<Texture> levelTen = new AssetDescriptor<Texture>("levelMenu/level10.png", Texture.class);	
	
	/** The Constant blockedLevel. */
	public static final AssetDescriptor<Texture> blockedLevel = new AssetDescriptor<Texture>("levelMenu/levellocked.png", Texture.class);	
	
	/** The Constant successScreen. */
	public static final AssetDescriptor<Texture> successScreen = new AssetDescriptor<Texture>("maps/successScreen.png", Texture.class);	
	
	/** The Constant pausedScreen. */
	public static final AssetDescriptor<Texture> pausedScreen = new AssetDescriptor<Texture>("maps/pausedScreen.png", Texture.class);	
	
	/** The Constant failedScreen. */
	public static final AssetDescriptor<Texture> failedScreen = new AssetDescriptor<Texture>("maps/failedScreen.png", Texture.class);	
	
	/** The Constant enabledSound. */
	public static final AssetDescriptor<Texture> enabledSound = new AssetDescriptor<Texture>("sound/enabledSound.png", Texture.class);	
	
	/** The Constant enabledMusic. */
	public static final AssetDescriptor<Texture> enabledMusic = new AssetDescriptor<Texture>("sound/enabledMusic.png", Texture.class);	
	
	/** The Constant disabledSound. */
	public static final AssetDescriptor<Texture> disabledSound = new AssetDescriptor<Texture>("sound/disabledSound.png", Texture.class);	
	
	/** The Constant disabledMusic. */
	public static final AssetDescriptor<Texture> disabledMusic = new AssetDescriptor<Texture>("sound/disabledMusic.png", Texture.class);	
	
	//-------------------SOUND------------------
	/** The Constant music. */
	public static final AssetDescriptor<Music> music = new AssetDescriptor<Music>("sound/music.mp3", Music.class);	
	
	/** The Constant wonSound. */
	public static final AssetDescriptor<Sound> wonSound = new AssetDescriptor<Sound>("sound/won.mp3", Sound.class);	
	
	/** The Constant diamondSound. */
	public static final AssetDescriptor<Sound> diamondSound = new AssetDescriptor<Sound>("sound/gotDiamond.mp3", Sound.class);	
	
	/** The Constant jumpSound. */
	public static final AssetDescriptor<Sound> jumpSound = new AssetDescriptor<Sound>("sound/jump.mp3", Sound.class);	
	
	/** The Constant diedSound. */
	public static final AssetDescriptor<Sound> diedSound = new AssetDescriptor<Sound>("sound/died.mp3", Sound.class);	
	
	/** The Constant lostLifeSound. */
	public static final AssetDescriptor<Sound> lostLifeSound = new AssetDescriptor<Sound>("sound/lostLife.mp3", Sound.class);	

	
	/**
	 * Loads
	 */
	public static void load()
	{
		manager.clear();
		
		manager.load(diamond);
		manager.load(robotRight);
		manager.load(robotLeft);
		manager.load(robotJumpLeft);
		manager.load(robotJumpRight);
		manager.load(key);
		manager.load(openedDoor);
		manager.load(closedDoor);
		manager.load(robotExit);
		manager.load(zeroLives);
		manager.load(oneLife);
		manager.load(twoLifes);
		manager.load(fullLife);
		manager.load(backgroundMenu);
		manager.load(tapToStart);
		manager.load(levelMenu);
		manager.load(levelOne);
		manager.load(levelTwo);
		manager.load(levelThree);
		manager.load(levelFour);
		manager.load(levelFive);
		manager.load(levelSix);
		manager.load(levelSeven);
		manager.load(levelEight);
		manager.load(levelNine);
		manager.load(levelTen);
		manager.load(blockedLevel);
		manager.load(successScreen);
		manager.load(pausedScreen);
		manager.load(failedScreen);
		manager.load(enabledSound);
		manager.load(enabledMusic);
		manager.load(disabledSound);
		manager.load(disabledMusic);
		
		manager.load(music);
		manager.load(wonSound);
		manager.load(diamondSound);
		manager.load(lostLifeSound);
		manager.load(diedSound);
		manager.load(jumpSound);

		manager.finishLoading();
	}
	
	/**
	 * Disposes
	 */
	public static void dispose() 
	{
		manager.clear();
	}
}
