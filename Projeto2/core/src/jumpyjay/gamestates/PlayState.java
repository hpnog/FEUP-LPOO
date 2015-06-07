package jumpyjay.gamestates;

import java.io.IOException;
import java.util.ArrayList;

import jumpyjay.handlers.Assets;
import jumpyjay.handlers.MyContactListener;
import jumpyjay.handlers.SaveAndLoad;
import jumpyjay.handlers.SingletonVandC;
import jumpyjay.handlers.SoundHandler;
import jumpyjay.logic.entities.Diamond;
import jumpyjay.logic.entities.ExitDoor;
import jumpyjay.logic.entities.Key;
import jumpyjay.logic.entities.Robot;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * The Class PlayState.
 */
public class PlayState extends GameState {
	
	/** The singleton. */
	private SingletonVandC singleton;
	
	/** The font. */
	private BitmapFont font;
	
	/** The map. */
	private TiledMap map;
	
	/** The renderer. */
	private OrthogonalTiledMapRenderer renderer;
	
	/** The cam. */
	private OrthographicCamera cam;
	
	/** The b2d cam. */
	private OrthographicCamera b2dCam;
	
	/** The hud cam. */
	private OrthographicCamera hudCam;
	
	/** The hud cam text. */
	private OrthographicCamera hudCamText;
	
	/** The screen height. */
	private double screenHeight;
	
	/** The screen width. */
	private double screenWidth;
	
	/** The map width. */
	private float mapWidth;
	
	/** The map height. */
	private float mapHeight;
	
	/** The robot. */
	private Robot robot;
	
	/** The keys. */
	private ArrayList<Key> keys;
	
	/** The diamonds. */
	private ArrayList<Diamond> diamonds;
	
	/** The exit door. */
	private ExitDoor exitDoor;
	
	/** The world. */
	private World world;
	
	/** The b2d renderer. */
	@SuppressWarnings("unused")										//For debugging purposes
	private Box2DDebugRenderer b2dRenderer;
	
	/** The win. */
	private int win = 0;
	
	/** The tile size. */
	private float tileSize;
	
	/** The props. */
	MapProperties props;
	
	/** The sb3. */
	private SpriteBatch sb, sb2, sb3;

	/**
	 * Instantiates a new play state.
	 *
	 * @param gameStateManager the game state manager
	 */
	protected PlayState(GameStateManager gameStateManager) {
		super(gameStateManager);
		init();
	}

	/**
	 * Init initializes the main structure of the game itself
	 * 
	 * initializes the font, singleton, sprite batches map, ant respective renderer, layers, size variables, box2d world, respective renderer and cameras.
	 */
	@Override
	public void init() {
		font = new BitmapFont(Gdx.files.internal("images/font.fnt"), Gdx.files.internal("images/font.png"), false);

		singleton = SingletonVandC.getSingleton();

		sb = new SpriteBatch();
		sb2 = new SpriteBatch();
		sb3 = new SpriteBatch();

		//get tiled map---------------------------------------------------------------------------------------
		map = new TmxMapLoader().load("maps/map" + SingletonVandC.currentLevel + ".tmx");
		renderer = new OrthogonalTiledMapRenderer(map);
		//----------------------------------------------------------------------------------------------------
		//get Layers---------------------------------------------------------------------------------------
		TiledMapTileLayer layerFloor = (TiledMapTileLayer) map.getLayers().get("Floor");
		tileSize = layerFloor.getTileWidth();
		//----------------------------------------------------------------------------------------------------
		//inicializa mapProperties para tirar propriedades do mapa
		props = map.getProperties();
		screenHeight = 200;
		screenWidth = (singleton.SCREEN_WIDTH / (double) singleton.SCREEN_HEIGHT) * screenHeight;
		//----------------------------------------------------------------------------------------------------
		//Cria Mundo do Box2D---------------------------------------------------------------------------------
		world = new World(new Vector2(0, -9.81f), true);
		world.setContactListener(new MyContactListener());
		b2dRenderer = new Box2DDebugRenderer();
		//----------------------------------------------------------------------------------------------------
		startCameras();
		loadToMap();

		mapWidth = props.get("width", Integer.class) * props.get("tilewidth", Integer.class);
		mapHeight = props.get("height", Integer.class) * props.get("tileheight", Integer.class);
	}

	/**
	 * Load to map.
	 * 
	 * loads also the maps info to the box2d world
	 */
	private void loadToMap() {
		createRobot((int) SingletonVandC.initialPositions[SingletonVandC.currentLevel - 1].x, (int) SingletonVandC.initialPositions[SingletonVandC.currentLevel - 1].y);
		loadAndCreateKeys();
		loadAndCreateDiamonds();
		loadDoor();

		robot.getX();
		robot.getY();

		loadLayerToB2d("Floor", singleton.BIT_FLOOR, singleton.BIT_ROBOT_FOOT);
		loadLayerToB2d("Danger", singleton.BIT_DANGER, singleton.BIT_ROBOT_FOOT);
	}

	/**
	 * Start cameras.
	 */
	private void startCameras() {
		//Creates camera to control the users view------------------------------------------------------------
		cam = new OrthographicCamera();			//swap dimensions to see physics
		cam.setToOrtho(false, (int) screenWidth, (int) screenHeight);
		cam.update();
		//----------------------------------------------------------------------------------------------------

		b2dCam = new OrthographicCamera();
		b2dCam.setToOrtho(false, (int) screenWidth / singleton.PPM, (int) screenHeight / singleton.PPM);

		hudCam = new OrthographicCamera();
		hudCam.setToOrtho(false, (int) screenWidth, (int) screenHeight);

		hudCamText = new OrthographicCamera();
		hudCamText.setToOrtho(false, (int) screenWidth * 5, (int) screenHeight * 5);
	}

	/**
	 * Creates the robot.
	 *
	 * @param xIni the x initial coordinate
	 * @param yIni the y initial coordinate
	 */
	public void createRobot(int xIni, int yIni) {
		//create platform-------------------------------------------------------------------------------------
		BodyDef robotBody = new BodyDef();
		robotBody.position.set((xIni * tileSize) / singleton.PPM, (yIni * tileSize) / singleton.PPM);
		robotBody.type = BodyType.DynamicBody;
		Body robotB = world.createBody(robotBody);

		//create robot
		robot = new Robot(robotB, xIni, yIni, tileSize, world);

	}

	/**
	 * update updates the game, handles the input, checks if user has won/lost and takes respective actions, updates all the elements of the game
	 * 
	 * @param dt time since the last update took place
	 */
	@Override
	public void update(float dt) {
		handleInput();
		singleton.click.update();

		if (win == -1)
		{
			SoundHandler.playDead();
			win--;
		}

		if ((win == 1 || SingletonVandC.paused < 0) && !singleton.click.gotClicked())
			return;

		//Update world
		world.step(dt, 6, 2);
		checkAndOpenDoor();
		updateObjects(dt);

		//Check out of bounds
		if (robot.update(dt, world,	(props.get("width", Integer.class) * props.get("tilewidth", Integer.class)) * singleton.PPM,props.get("height", Integer.class) * props.get("tileheight", Integer.class) * singleton.PPM,exitDoor.getPosition()))
			endGame(-1);

		updateExit();

		if (robot.getHp() <= 0)
			endGame(-1);
	}

	/**
	 * Update exit.
	 */
	private void updateExit() {
		if (singleton.exiting >= 1)
			singleton.exiting++;

		if (singleton.exiting == 200)
		{
			win = 1;
			endGame(1);
		}
	}

	/**
	 * Update objects.
	 *
	 * @param dt the time since the last update
	 */
	private void updateObjects(float dt) {
		for (int i = 0; i < keys.size(); i++)
		{
			keys.get(i).update(dt);
			if (keys.get(i).checkifCaught())
				exitDoor.keyCaught();
		}

		for (int i = 0; i < diamonds.size(); i++)
			diamonds.get(i).update(dt);

		exitDoor.update(dt);
	}

	/**
	 * Check checks if door can be opened and opens door.
	 */
	private void checkAndOpenDoor() {
		if (singleton.exiting == 1 && exitDoor.getKeysToCatch() > 0)
			singleton.exiting = 0;
	}

	/**
	 * render renders all the elements in the screen with 3 cameras. Calls functions to render specific parts
	 */
	@Override
	public void render() {

		Gdx.gl20.glClearColor(217 / (float) 256, 208 / (float) 256, 179 / (float) 256, 1);		
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		//draw tiled map
		renderer.setView(cam);
		renderer.render();
		renderObjects();
		renderLifeAndMenus();

		renderText();
		renderSoundRelatedButtons();
		hudCam.update();

		//b2dRenderer.render(world, b2dCam.combined);
		controlCamera();

		robot.getX();
		robot.getY();

	}

	/**
	 * Render sound related buttons.
	 */
	private void renderSoundRelatedButtons() {
		sb2.begin();
		if (SingletonVandC.music)
			sb2.draw(Assets.manager.get(Assets.enabledMusic),(float) screenWidth - (float) screenWidth / 15, (float) screenHeight / 30, (float) screenWidth / 20, (float) screenHeight / 12);
		else
			sb2.draw(Assets.manager.get(Assets.disabledMusic), (float) screenWidth - (float) screenWidth / 15, (float) screenHeight / 30, (float) screenWidth / 20, (float) screenHeight / 12);
		if (SingletonVandC.sound)
			sb2.draw(Assets.manager.get(Assets.enabledSound), (float) screenWidth - 2 * (float) screenWidth / 15, (float) screenHeight / 30, (float) screenWidth / 20, (float) screenHeight / 12);
		else
			sb2.draw(Assets.manager.get(Assets.disabledSound), (float) screenWidth - 2 * (float) screenWidth / 15, (float) screenHeight / 30, (float) screenWidth / 20, (float) screenHeight / 12);
		sb2.end();
	}

	/**
	 * Render life and menus.
	 */
	private void renderLifeAndMenus() {
		sb2.setProjectionMatrix(hudCam.combined);
		sb2.begin();

		if (robot.getHp() == 3)
			sb2.draw(Assets.manager.get(Assets.fullLife), (float) (screenWidth - screenWidth / 12), (float) (screenHeight - screenHeight / 12), (float) (screenWidth / 15), (float) (screenHeight / 15));
		else if (robot.getHp() == 2)
			sb2.draw(Assets.manager.get(Assets.twoLifes), (float) (screenWidth - screenWidth / 12), (float) (screenHeight - screenHeight / 12), (float) (screenWidth / 15), (float) (screenHeight / 15));
		else if (robot.getHp() == 1)
			sb2.draw(Assets.manager.get(Assets.oneLife), (float) (screenWidth - screenWidth / 12), (float) (screenHeight - screenHeight / 12), (float) (screenWidth / 15), (float) (screenHeight / 15));
		else
			sb2.draw(Assets.manager.get(Assets.zeroLives), (float) (screenWidth - screenWidth / 12), (float) (screenHeight - screenHeight / 12), (float) (screenWidth / 15), (float) (screenHeight / 15));

		if (SingletonVandC.paused == -2)
			sb2.draw(Assets.manager.get(Assets.pausedScreen), (float) screenWidth / 4,(float) screenHeight / 6,(float) (screenWidth - (screenWidth / 2)), (float)(screenHeight - (screenHeight / 3)));

		if (win == 1)
			sb2.draw(Assets.manager.get(Assets.successScreen), (float) screenWidth / 4,(float) screenHeight / 6,(float) (screenWidth - (screenWidth / 2)), (float)(screenHeight - (screenHeight / 3)));
		else if (win <= -1)
			sb2.draw(Assets.manager.get(Assets.failedScreen), (float) screenWidth / 4,(float) screenHeight / 6,(float) (screenWidth - (screenWidth / 2)), (float)(screenHeight - (screenHeight / 3)));

		sb2.end();
	}

	/**
	 * Render text.
	 */
	private void renderText() {
		sb3.begin();
		sb3.setProjectionMatrix(hudCamText.combined);

		String toPrint = getScoreToPrint();
		font.setColor(0, 0, 0, 1);
		font.draw(sb3, toPrint, 5f * ((float) screenWidth / 12), (float) (screenHeight * 5 - 2.5 * screenHeight / 12), (float) (screenWidth / 50), 1, true);

		if (win == 1)
		{
			String toPrint2 = getHiScoreToPrint(SingletonVandC.currentLevel);
			font.draw(sb3, toPrint, 30f * ((float) screenWidth / 11.75f), (float) (screenHeight * 2.55), (float) (screenWidth / 50), 1, true);
			font.draw(sb3, toPrint2, 30f * ((float) screenWidth / 11.75f), (float) (screenHeight * 1.75), (float) (screenWidth / 50), 1, true);
		}
		else if (win <= -1)
		{
			String toPrint2 = getHiScoreToPrint(SingletonVandC.currentLevel);
			font.draw(sb3, toPrint2, 30f * ((float) screenWidth / 11.75f), (float) (screenHeight * 2.5), (float) (screenWidth / 50), 1, true);
		}

		sb3.end();
	}

	/**
	 * Render objects.
	 */
	private void renderObjects() {
		sb.setProjectionMatrix(cam.combined);
		sb.begin();

		exitDoor.draw(sb);
		robot.draw(sb);
		for (int i = 0; i < keys.size(); i++)
			keys.get(i).draw(sb);
		for (int i = 0; i < diamonds.size(); i++)
			diamonds.get(i).draw(sb);


		sb.end();
		sb.setProjectionMatrix(cam.combined);
	}

	/**
	 * Gets the score to print.
	 *
	 * @return the score to print
	 */
	private String getScoreToPrint() {
		String temp = "";
		if (singleton.levelScore == 0)
			temp = "000000";
		else if (singleton.levelScore < 10)
			temp = "00000" + singleton.levelScore;
		else if (singleton.levelScore < 100)
			temp = "0000" + singleton.levelScore;
		else if (singleton.levelScore < 1000)
			temp = "000" + singleton.levelScore;
		else if (singleton.levelScore < 10000)
			temp = "00" + singleton.levelScore;
		else if (singleton.levelScore < 100000)
			temp = "0" + singleton.levelScore;
		else if (singleton.levelScore < 1000000)
			temp = "" + singleton.levelScore;
		else
			temp = "999999+";
		return temp;
	}

	/**
	 * Gets the high score to print.
	 *
	 * @param i the index of the level
	 * @return the high score to print
	 */
	private String getHiScoreToPrint(int i) {
		String temp = "";
		if (SingletonVandC.totalScore[i-1] <= 0)
			temp = "000000";
		else if (SingletonVandC.totalScore[i-1] < 10)
			temp = "00000" + SingletonVandC.totalScore[i-1];
		else if (SingletonVandC.totalScore[i-1] < 100)
			temp = "0000" + SingletonVandC.totalScore[i-1];
		else if (SingletonVandC.totalScore[i-1] < 1000)
			temp = "000" + SingletonVandC.totalScore[i-1];
		else if (SingletonVandC.totalScore[i-1] < 10000)
			temp = "00" + SingletonVandC.totalScore[i-1];
		else if (SingletonVandC.totalScore[i-1] < 100000)
			temp = "0" + SingletonVandC.totalScore[i-1];
		else if (SingletonVandC.totalScore[i-1] < 1000000)
			temp = "" + SingletonVandC.totalScore[i-1];
		else
			temp = "999999+";
		return temp;
	}

	/**
	 * Control camera.
	 */
	private void controlCamera() {
		cam.position.set(new Vector2(robot.getX(), robot.getY()), 0);
		b2dCam.position.set(new Vector2(robot.getX() / singleton.PPM, robot.getY() / singleton.PPM), 0);

		if (robot.getX() < (screenWidth / 2))
		{
			cam.position.set(new Vector2((float) screenWidth / 2, cam.position.y), 0);
			b2dCam.position.set(new Vector2((float) screenWidth / 2 / singleton.PPM, b2dCam.position.y), 0);
		}
		else if (robot.getX() > (mapWidth - (screenWidth / 2)))
		{
			cam.position.set(new Vector2(mapWidth - ((float) screenWidth / 2), cam.position.y), 0);
			b2dCam.position.set(new Vector2((mapWidth - ((float) screenWidth / 2)) / singleton.PPM, b2dCam.position.y), 0);
		}

		if (robot.getY() < (screenHeight / 2))
		{
			cam.position.set(new Vector2(cam.position.x, (float) screenHeight / 2), 0);
			b2dCam.position.set(new Vector2(b2dCam.position.x, (float) screenHeight / 2 / singleton.PPM), 0);
		}
		else if (robot.getY() > (mapHeight - (screenHeight - 2)))
		{
			cam.position.set(new Vector2(cam.position.x, mapHeight - ((float) screenHeight / 2)), 0);
			b2dCam.position.set(new Vector2(b2dCam.position.x, (mapHeight - ((float) screenHeight / 2)) / singleton.PPM), 0);
		}


		b2dCam.update();
		cam.update();


	}

	/**
	 * handle input
	 * 
	 * handles the users input, related to the game or any of the buttons
	 */
	@Override
	public void handleInput() {
		if (Gdx.input.justTouched())
		{
			if ((Gdx.input.getX() > (singleton.SCREEN_WIDTH - singleton.SCREEN_WIDTH / 15)) && (Gdx.input.getX() < (singleton.SCREEN_WIDTH - singleton.SCREEN_WIDTH / 15 + singleton.SCREEN_WIDTH / 20)) &&
					(Gdx.input.getY() < (singleton.SCREEN_HEIGHT - singleton.SCREEN_HEIGHT / 30)) && (Gdx.input.getY() > (singleton.SCREEN_HEIGHT - singleton.SCREEN_HEIGHT / 30 - singleton.SCREEN_HEIGHT / 12)))
			{
				SoundHandler.changeMusicState();
				singleton.click.update();
			}
			else if ((Gdx.input.getX() > (singleton.SCREEN_WIDTH - 2 * (singleton.SCREEN_WIDTH / (float) 15))) && (Gdx.input.getX() < (singleton.SCREEN_WIDTH - 2 * (singleton.SCREEN_WIDTH / (float) 15) + singleton.SCREEN_WIDTH / 20)) &&
					(Gdx.input.getY() < (singleton.SCREEN_HEIGHT - singleton.SCREEN_HEIGHT / 30)) && (Gdx.input.getY() > (singleton.SCREEN_HEIGHT - singleton.SCREEN_HEIGHT / 30 - singleton.SCREEN_HEIGHT / 12)))
			{
				SoundHandler.changeSoundState();
				singleton.click.update();
			}
		}

		if (win == 1 || win <= -1) //se ja ganhou
		{

			if (Gdx.input.justTouched() && (Gdx.input.getY() > (singleton.SCREEN_HEIGHT / 2 + (0.3 * singleton.SCREEN_HEIGHT / 2))) && (Gdx.input.getY() < (singleton.SCREEN_HEIGHT / 2 + (0.7 * singleton.SCREEN_HEIGHT / 2))))
				if ((Gdx.input.getX() > (singleton.SCREEN_WIDTH / 2 - (0.5 * (singleton.SCREEN_WIDTH / 2))) && Gdx.input.getX() < (singleton.SCREEN_WIDTH / 2 - 0.7 * (singleton.SCREEN_WIDTH / 5))))
				{
					SingletonVandC.currentLevel = 0;
					gameStateManager.setState(singleton.LEVEL);
				}

			if (Gdx.input.justTouched() && (Gdx.input.getY() > (singleton.SCREEN_HEIGHT / 2 + (0.3 * singleton.SCREEN_HEIGHT / 2))) && (Gdx.input.getY() < (singleton.SCREEN_HEIGHT / 2 + (0.7 * singleton.SCREEN_HEIGHT / 2))))
				if ((Gdx.input.getX() < (singleton.SCREEN_WIDTH / 2 + (0.5 * (singleton.SCREEN_WIDTH / 2))) && Gdx.input.getX() > (singleton.SCREEN_WIDTH / 2 + 0.7 * (singleton.SCREEN_WIDTH / 5))))
					gameStateManager.setState(singleton.PLAY);

		}
	}

	/**
	 * dispose
	 * 
	 * disposes the assets loaded and created for the game
	 */
	@Override
	public void dispose() {
		map.dispose();
		renderer.dispose();
		robot.dispose();
		sb.dispose();
		sb2.dispose();
		sb3.dispose();
		for (int i = 0; i < keys.size(); i++)	
			keys.get(i).dispose();
		for (int i = 0; i < diamonds.size(); i++)
			diamonds.get(i).dispose();
		exitDoor.dispose();
		font.dispose();

		keys.clear();
		diamonds.clear();
	}

	/**
	 * Load layer to b2d.
	 *
	 * @param name the layer
	 * @param cBits the category bits
	 * @param mBits the mask bits
	 */
	private void loadLayerToB2d(String name, short cBits, short mBits)
	{
		TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(name);

		if (layer != null)
			for(int y = 0; y < layer.getHeight(); y++)
				for(int x = 0; x < layer.getWidth(); x++)
				{
					Cell cell = layer.getCell(x, y);
					if (cell != null) 
						if (cell.getTile() != null)
							if (cell.getTile().getProperties().get("blockable") != null)
							{
								BodyDef bdef = new BodyDef();
								bdef.type = BodyType.StaticBody;
								bdef.position.set((float) (x + 0.5) * tileSize / singleton.PPM, (float) (y + 0.5) * tileSize / singleton.PPM);

								ChainShape shape = new ChainShape();
								Vector2[] v = new Vector2[5];
								v[0] = new Vector2(- tileSize / 2 / singleton.PPM, -tileSize / 2 / singleton.PPM);
								v[1] = new Vector2(- tileSize / 2 / singleton.PPM, tileSize / 2 / singleton.PPM);
								v[2] = new Vector2(tileSize / 2 / singleton.PPM, tileSize / 2 / singleton.PPM);
								v[3] = new Vector2(tileSize / 2 / singleton.PPM, - tileSize / 2 / singleton.PPM);
								v[4] = v[0];
								shape.createChain(v);

								FixtureDef fdef = new FixtureDef();
								fdef.filter.categoryBits = cBits;
								fdef.filter.maskBits = mBits;
								fdef.friction = 0;
								fdef.shape = shape;

								world.createBody(bdef).createFixture(fdef).setUserData(name);
							}

				}
	}

	/**
	 * Load and create keys.
	 */
	private void loadAndCreateKeys()
	{
		keys = new ArrayList<Key>();

		MapLayer layer = map.getLayers().get("Key");

		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();

		for (MapObject mo : layer.getObjects())
		{
			bdef.type = BodyType.StaticBody;

			float x = mo.getProperties().get("x", float.class) / singleton.PPM;
			float y = mo.getProperties().get("y", float.class) / singleton.PPM;

			bdef.position.set(x, y);

			PolygonShape kShape = new PolygonShape();
			kShape.setAsBox(8 / singleton.PPM, 8 / singleton.PPM);

			fdef.shape = kShape;
			fdef.isSensor = true;
			fdef.filter.categoryBits = singleton.BIT_KEY;
			fdef.filter.maskBits = singleton.BIT_ROBOT;

			Body body = world.createBody(bdef);
			Fixture fix = body.createFixture(fdef);
			fix.setUserData("key");

			Key k = new Key(body, fix, x, y, 13, 12);
			keys.add(k);


		}
	} 

	/**
	 * Load and create diamonds.
	 */
	private void loadAndCreateDiamonds() {

		diamonds = new ArrayList<Diamond>();

		MapLayer layer = map.getLayers().get("Diamond");

		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();

		for (MapObject mo : layer.getObjects())
		{
			bdef.type = BodyType.StaticBody;

			float x = (mo.getProperties().get("x", float.class) + 8) / singleton.PPM;
			float y = (mo.getProperties().get("y", float.class) + 8) / singleton.PPM;

			bdef.position.set(x, y);

			PolygonShape kShape = new PolygonShape();
			kShape.setAsBox(8 / singleton.PPM, 8 / singleton.PPM);

			fdef.shape = kShape;
			fdef.isSensor = true;
			fdef.filter.categoryBits = singleton.BIT_DIAMOND;
			fdef.filter.maskBits = singleton.BIT_ROBOT;

			Body body = world.createBody(bdef);
			Fixture fix = body.createFixture(fdef);
			fix.setUserData("diamond");

			Diamond k = new Diamond(body, fix, x, y, 14, 16);
			diamonds.add(k);


		}
	}

	/**
	 * Load door.
	 */
	private void loadDoor() {

		MapLayer layer = map.getLayers().get("Exit");

		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();

		for (MapObject mo : layer.getObjects())
		{
			bdef.type = BodyType.StaticBody;

			float x = (mo.getProperties().get("x", float.class)) / singleton.PPM;
			float y = (mo.getProperties().get("y", float.class)) / singleton.PPM + 24 / singleton.PPM;

			bdef.position.set(x, y);

			PolygonShape kShape = new PolygonShape();
			kShape.setAsBox(32 / singleton.PPM / 2, 48 / singleton.PPM / 2);

			fdef.shape = kShape;
			fdef.isSensor = true;
			fdef.filter.categoryBits = singleton.BIT_DOOR;
			fdef.filter.maskBits = singleton.BIT_ROBOT;

			Body body = world.createBody(bdef);
			Fixture fix = body.createFixture(fdef);
			fix.setUserData("door");

			exitDoor = new ExitDoor(body, x, y, 32, 48, keys.size());	
		}
	}

	/**
	 * End game.
	 *
	 * @param i the indicator of the state of the game
	 */
	private void endGame(int i)
	{
		if (i == -1 && win <= -1) {}
		else win = i;		

		if (win == 1)
		{
			if (singleton.levelScore > SingletonVandC.totalScore[SingletonVandC.currentLevel-1])
				SingletonVandC.totalScore[SingletonVandC.currentLevel - 1] = singleton.levelScore;
			SoundHandler.playWon();
		}

		try {
			SaveAndLoad.saveGame();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * pause
	 */
	@Override
	public void pause() {
	}

}
