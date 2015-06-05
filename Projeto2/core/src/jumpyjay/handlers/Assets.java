package jumpyjay.handlers;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class Assets {
	
	public static final AssetManager manager = new AssetManager();
	
	public static final AssetDescriptor<Texture> diamond = new AssetDescriptor<Texture>("maps/diamonds/diamond.png", Texture.class);	
	public static final AssetDescriptor<Texture> robotRight = new AssetDescriptor<Texture>("maps/robots/robotwalkright.png", Texture.class);	
	public static final AssetDescriptor<Texture> robotLeft = new AssetDescriptor<Texture>("maps/robots/robotwalkleft.png", Texture.class);	
	public static final AssetDescriptor<Texture> robotJumpLeft = new AssetDescriptor<Texture>("maps/robots/robotjumpleft.png", Texture.class);	
	public static final AssetDescriptor<Texture> robotJumpRight = new AssetDescriptor<Texture>("maps/robots/robotjumpright.png", Texture.class);	
	public static final AssetDescriptor<Texture> key = new AssetDescriptor<Texture>("maps/keys/key.png", Texture.class);	
	public static final AssetDescriptor<Texture> openedDoor = new AssetDescriptor<Texture>("images/openedDoor.png", Texture.class);	
	public static final AssetDescriptor<Texture> closedDoor = new AssetDescriptor<Texture>("images/closedDoor.png", Texture.class);	
	public static final AssetDescriptor<Texture> robotExit = new AssetDescriptor<Texture>("images/robotexit.png", Texture.class);	
	public static final AssetDescriptor<Texture> oneLife = new AssetDescriptor<Texture>("maps/hp/one_health.png", Texture.class);	
	public static final AssetDescriptor<Texture> twoLifes = new AssetDescriptor<Texture>("maps/hp/two_health.png", Texture.class);	
	public static final AssetDescriptor<Texture> fullLife = new AssetDescriptor<Texture>("maps/hp/full_health.png", Texture.class);	
	public static final AssetDescriptor<Texture> backgroundMenu = new AssetDescriptor<Texture>("menu/menu.png", Texture.class);	
	public static final AssetDescriptor<Texture> tapToStart = new AssetDescriptor<Texture>("menu/taptostart.png", Texture.class);	
	public static final AssetDescriptor<Texture> levelMenu = new AssetDescriptor<Texture>("levelMenu/levelmenu.png", Texture.class);	
	public static final AssetDescriptor<Texture> levelOne = new AssetDescriptor<Texture>("levelMenu/level1.png", Texture.class);	
	public static final AssetDescriptor<Texture> levelTwo = new AssetDescriptor<Texture>("levelMenu/level2.png", Texture.class);	
	public static final AssetDescriptor<Texture> levelThree = new AssetDescriptor<Texture>("levelMenu/level3.png", Texture.class);	
	public static final AssetDescriptor<Texture> levelFour = new AssetDescriptor<Texture>("levelMenu/level4.png", Texture.class);	
	public static final AssetDescriptor<Texture> levelFive = new AssetDescriptor<Texture>("levelMenu/level5.png", Texture.class);	
	public static final AssetDescriptor<Texture> levelSix = new AssetDescriptor<Texture>("levelMenu/level6.png", Texture.class);	
	public static final AssetDescriptor<Texture> levelSeven = new AssetDescriptor<Texture>("levelMenu/level7.png", Texture.class);	
	public static final AssetDescriptor<Texture> levelEight = new AssetDescriptor<Texture>("levelMenu/level8.png", Texture.class);	
	public static final AssetDescriptor<Texture> levelNine = new AssetDescriptor<Texture>("levelMenu/level9.png", Texture.class);	
	public static final AssetDescriptor<Texture> levelTen = new AssetDescriptor<Texture>("levelMenu/level10.png", Texture.class);	
	public static final AssetDescriptor<Texture> blockedLevel = new AssetDescriptor<Texture>("levelMenu/levellocked.png", Texture.class);	
	public static final AssetDescriptor<Texture> successScreen = new AssetDescriptor<Texture>("maps/successScreen.png", Texture.class);	
	public static final AssetDescriptor<Texture> pausedScreen = new AssetDescriptor<Texture>("maps/pausedScreen.png", Texture.class);	
	public static final AssetDescriptor<Texture> failedScreen = new AssetDescriptor<Texture>("maps/failedScreen.png", Texture.class);	

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

		manager.finishLoading();
	}
	
	public static void dispose() 
	{

	}
}
