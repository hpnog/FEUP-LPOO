package com.mygdx.gamestates;

import java.util.ArrayList;

import handlers.MyContactListener;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
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
import com.mygdx.entities.Diamond;
import com.mygdx.entities.ExitDoor;
import com.mygdx.entities.Key;
import com.mygdx.entities.Robot;
import com.mygdx.game.Content;
import com.mygdx.game.MyJumpyJay;

import static com.mygdx.game.Box2DVariables.*;

public class PlayState extends GameState {

	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;

	private OrthographicCamera cam;
	private OrthographicCamera b2dCam;

	private double screenHeight;
	private double screenWidth;
	
	private float mapWidth;
	private float mapHeight;
	
	private Robot robot;
	private ArrayList<Key> keys;
	private ArrayList<Diamond> diamonds;
	private ExitDoor exitDoor;
	
	private World world;
	private Box2DDebugRenderer b2dRenderer;

	private float tileSize;
	MapProperties props;

	public static Content res;
	private SpriteBatch sb;

	protected PlayState(GameStateManager gameStateManager) {
		super(gameStateManager);
		init();
	}

	@Override
	public void init() {
		
		sb = new SpriteBatch();
		res = new Content();
		res.loadTexture("maps/robots/robotwalkright.png", "robotRight");
		res.loadTexture("maps/robots/robotwalkleft.png", "robotLeft");
		res.loadTexture("maps/robots/robotjumpleft.png", "robotJumpLeft");
		res.loadTexture("maps/robots/robotjumpright.png", "robotJumpRight");
		res.loadTexture("maps/keys/key.png", "key");
		res.loadTexture("maps/diamonds/diamond.png", "diamond");
		res.loadTexture("images/openedDoor.png", "openedDoor");
		res.loadTexture("images/closedDoor.png", "closedDoor");
		res.loadTexture("images/robotexit.png", "robotExit");
		
		//get tiled map---------------------------------------------------------------------------------------
		map = new TmxMapLoader().load("maps/mapa2.tmx");
		renderer = new OrthogonalTiledMapRenderer(map);
		//----------------------------------------------------------------------------------------------------

		//get Layers---------------------------------------------------------------------------------------
		TiledMapTileLayer layerFloor = (TiledMapTileLayer) map.getLayers().get("Floor");
		tileSize = layerFloor.getTileWidth();
		//----------------------------------------------------------------------------------------------------

		//inicializa mapProperties para tirar propriedades do mapa
		props = map.getProperties();
		screenHeight = 200;
		screenWidth = (MyJumpyJay.WIDTH / (double) MyJumpyJay.HEIGHT) * screenHeight;
		//----------------------------------------------------------------------------------------------------

		//Cria Mundo do Box2D---------------------------------------------------------------------------------
		world = new World(new Vector2(0, -9.81f), true);
		world.setContactListener(new MyContactListener());
		b2dRenderer = new Box2DDebugRenderer();
		//----------------------------------------------------------------------------------------------------

		//Creates camera to control the users view------------------------------------------------------------
		cam = new OrthographicCamera();			//swap dimensions to see physics
		cam.setToOrtho(false, (int) screenWidth, (int) screenHeight);
		cam.update();
		//----------------------------------------------------------------------------------------------------

		b2dCam = new OrthographicCamera();
		b2dCam.setToOrtho(false, (int) screenWidth / PPM, (int) screenHeight / PPM);
		
		//Loads the robots Texture----------------------------------------------------------------------------
		createRobot(4, 5);
		loadAndCreateKeys();
		loadAndCreateDiamonds();
		loadDoor();
		
		robot.getX();
		robot.getY();

		//Introduzir info na Box2D---------------------------------------------------------------------------
		loadLayerToB2d("Floor", BIT_FLOOR, BIT_ROBOT_FOOT);
		//----------------------------------------------------------------------------------------------------
		mapWidth = props.get("width", Integer.class) * props.get("tilewidth", Integer.class);
		mapHeight = props.get("height", Integer.class) * props.get("tileheight", Integer.class);
	}

	private void createRobot(int xIni, int yIni) {
		//create platform-------------------------------------------------------------------------------------
		BodyDef robotBody = new BodyDef();
		robotBody.position.set((xIni * tileSize) / PPM, (yIni * tileSize) / PPM);
		robotBody.type = BodyType.DynamicBody;
		Body robotB = world.createBody(robotBody);
				
		//create robot
		robot = new Robot(robotB, xIni, yIni, tileSize, world);

	}

	@Override
	public void update(float dt) {
		handleInput();
		//Update world
		world.step(dt, 6, 2);

		for (int i = 0; i < keys.size(); i++)
		{
			keys.get(i).update(dt);
			if (keys.get(i).checkifCaught())
				exitDoor.keyCaught();
		}
		
		for (int i = 0; i < diamonds.size(); i++)
			diamonds.get(i).update(dt);
		
		exitDoor.update(dt);
		
		if (robot.update(dt, world,
				(props.get("width", Integer.class) * props.get("tilewidth", Integer.class)) / PPM,
				props.get("height", Integer.class) * props.get("tileheight", Integer.class) / PPM))
			gameStateManager.setState(GameStateManager.MENU);

	}

	@Override
	public void render() {
		Gdx.gl20.glClearColor(217 / (float) 256, 208 / (float) 256, 179 / (float) 256, 1);		
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		//draw tiled map
		renderer.setView(cam);
		renderer.render();
		
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
		
		b2dRenderer.render(world, b2dCam.combined);
		
		controlCamera();
		
		robot.getX();
		robot.getY();

	}

	private void controlCamera() {
		cam.position.set(new Vector2(robot.getX(), robot.getY()), 0);
		b2dCam.position.set(new Vector2(robot.getX() / PPM, robot.getY() / PPM), 0);
		
		if (robot.getX() < (screenWidth / 2))
		{
			cam.position.set(new Vector2((float) screenWidth / 2, cam.position.y), 0);
			b2dCam.position.set(new Vector2((float) screenWidth / 2 / PPM, b2dCam.position.y), 0);
		}
		else if (robot.getX() > (mapWidth - (screenWidth / 2)))
		{
			cam.position.set(new Vector2(mapWidth - ((float) screenWidth / 2), cam.position.y), 0);
			b2dCam.position.set(new Vector2((mapWidth - ((float) screenWidth / 2)) / PPM, b2dCam.position.y), 0);
		}
		
		if (robot.getY() < (screenHeight / 2))
		{
			cam.position.set(new Vector2(cam.position.x, (float) screenHeight / 2), 0);
			b2dCam.position.set(new Vector2(b2dCam.position.x, (float) screenHeight / 2 / PPM), 0);
		}
		else if (robot.getY() > (mapHeight - (screenHeight - 2)))
		{
			cam.position.set(new Vector2(cam.position.x, mapHeight - (float) screenHeight / 2), 0);
			b2dCam.position.set(new Vector2(b2dCam.position.x, (mapHeight - (float) screenHeight / 2) / PPM), 0);
		}
		
		
		b2dCam.update();
		cam.update();
		
		
	}

	@Override
	public void handleInput() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		map.dispose();
		renderer.dispose();
		robot.dispose();
		
	}

	private void loadLayerToB2d(String name, short cBits, short mBits)
	{
		TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(name);

		tileSize = layer.getTileWidth();

		//Introduzir info na Box2D
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
							bdef.position.set((float) (x + 0.5) * tileSize / PPM, (float) (y + 0.5) * tileSize / PPM);

							ChainShape shape = new ChainShape();
							Vector2[] v = new Vector2[5];
							v[0] = new Vector2(- tileSize / 2 / PPM, -tileSize / 2 / PPM);
							v[1] = new Vector2(- tileSize / 2 / PPM, tileSize / 2 / PPM);
							v[2] = new Vector2(tileSize / 2 / PPM, tileSize / 2 / PPM);
							v[3] = new Vector2(tileSize / 2 / PPM, - tileSize / 2 / PPM);
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

	private void loadAndCreateKeys()
	{
		keys = new ArrayList<Key>();
		
		MapLayer layer = map.getLayers().get("Key");
		
		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();
		
		for (MapObject mo : layer.getObjects())
		{
			bdef.type = BodyType.StaticBody;
						
			float x = mo.getProperties().get("x", float.class) / PPM;
			float y = mo.getProperties().get("y", float.class) / PPM;
			
			bdef.position.set(x, y);
			
			PolygonShape kShape = new PolygonShape();
			kShape.setAsBox(8 / PPM, 8 / PPM);
			
			fdef.shape = kShape;
			fdef.isSensor = true;
			fdef.filter.categoryBits = BIT_KEY;
			fdef.filter.maskBits = BIT_ROBOT;
						
			Body body = world.createBody(bdef);
			Fixture fix = body.createFixture(fdef);
			fix.setUserData("key");
			
			Key k = new Key(body, fix, x, y, 13, 12);
			keys.add(k);
			
			
		}
	} 

	private void loadAndCreateDiamonds() {
		
		diamonds = new ArrayList<Diamond>();
		
		MapLayer layer = map.getLayers().get("Diamond");
		
		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();
		
		for (MapObject mo : layer.getObjects())
		{
			bdef.type = BodyType.StaticBody;
						
			float x = (mo.getProperties().get("x", float.class) + 8) / PPM;
			float y = (mo.getProperties().get("y", float.class) + 8) / PPM;
			
			bdef.position.set(x, y);
			
			PolygonShape kShape = new PolygonShape();
			kShape.setAsBox(8 / PPM, 8 / PPM);
			
			fdef.shape = kShape;
			fdef.isSensor = true;
			fdef.filter.categoryBits = BIT_DIAMOND;
			fdef.filter.maskBits = BIT_ROBOT;
						
			Body body = world.createBody(bdef);
			Fixture fix = body.createFixture(fdef);
			fix.setUserData("diamond");
			
			Diamond k = new Diamond(body, fix, x, y, 14, 16);
			diamonds.add(k);
			
			
		}
	}

	private void loadDoor() {
		
		MapLayer layer = map.getLayers().get("Exit");
		
		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();
		
		for (MapObject mo : layer.getObjects())
		{
			bdef.type = BodyType.StaticBody;
						
			float x = (mo.getProperties().get("x", float.class)) / PPM;
			float y = (mo.getProperties().get("y", float.class)) / PPM + 24 / PPM;
			
			bdef.position.set(x, y);
			
			PolygonShape kShape = new PolygonShape();
			kShape.setAsBox(32 / PPM / 2, 48 / PPM / 2);
			
			fdef.shape = kShape;
			fdef.isSensor = true;
			fdef.filter.categoryBits = BIT_DOOR;
			fdef.filter.maskBits = BIT_ROBOT;
						
			Body body = world.createBody(bdef);
			Fixture fix = body.createFixture(fdef);
			fix.setUserData("door");
			
			exitDoor = new ExitDoor(body, x, y, 32, 48, keys.size());	
		}
	}
}
