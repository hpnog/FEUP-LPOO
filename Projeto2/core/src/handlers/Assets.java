package handlers;

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
		
		manager.finishLoading();
	}
	
	public static void dispose() 
	{

	}
}
