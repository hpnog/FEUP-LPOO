package com.mygdx.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.entities.Robot;
import com.mygdx.game.Content;
import com.mygdx.game.MyJumpyJay;

import static com.mygdx.game.Box2DVariables.PPM;;

public class PlayState extends GameState {

	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;

	private OrthographicCamera cam;
	private OrthographicCamera b2dCam;

	private double height;
	private double width;
	private Robot robot;

	private World world;
	private Box2DDebugRenderer b2dRenderer;

	private float tileSize;
	MapProperties props;

	private float lastX;
	private float lastY;

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
		height = 150;
		width = (MyJumpyJay.WIDTH / (double) MyJumpyJay.HEIGHT) * height;
		//----------------------------------------------------------------------------------------------------

		//Cria Mundo do Box2D---------------------------------------------------------------------------------
		world = new World(new Vector2(0, -9.81f), true);
		b2dRenderer = new Box2DDebugRenderer();
		//----------------------------------------------------------------------------------------------------

		//Creates camera to control the users view------------------------------------------------------------
		cam = new OrthographicCamera();			//swap dimensions to see physics
		cam.setToOrtho(false, (int) width, (int) height);
		cam.update();
		//----------------------------------------------------------------------------------------------------

		b2dCam = new OrthographicCamera();
		b2dCam.setToOrtho(false, (int) width / PPM, (int) height / PPM);
		
		//Loads the robots Texture----------------------------------------------------------------------------
		createRobot(4, 4);

		lastX = robot.getX();
		lastY = robot.getY();
		//----------------------------------------------------------------------------------------------------

		//Introduzir info na Box2D---------------------------------------------------------------------------
		loadLayerToB2d("Floor");
		//----------------------------------------------------------------------------------------------------

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
		//Update world
		world.step(dt, 6, 2);

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
		robot.draw(sb);
		sb.end();
		
		sb.setProjectionMatrix(cam.combined);
		
		b2dRenderer.render(world, b2dCam.combined);

		
		
		if (robot.getX() > width / 2 && robot.getX() < (props.get("width", Integer.class) * props.get("tilewidth", Integer.class) - (width / 2)))
		{
			cam.translate((float) robot.getX() - lastX, 0);
			b2dCam.translate((float) (robot.getX() - lastX) / PPM, 0);
		}
		if (robot.getY() > height / 2 && robot.getY() < props.get("height", Integer.class) * props.get("tileheight", Integer.class) - (height / 2))
		{
			cam.translate(0, (float) robot.getY() - lastY);
			b2dCam.translate(0, (float) (robot.getY() - lastY) / PPM);
		}
		b2dCam.update();
		cam.update();
		
		
		lastX = robot.getX();
		lastY = robot.getY();

	}

	@Override
	public void handleInput() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		map.dispose();
		renderer.dispose();
	}

	private void loadLayerToB2d(String name)
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
							fdef.friction = 0;
							fdef.shape = shape;

							world.createBody(bdef).createFixture(fdef).setUserData(name);
						}

			}
	}

}
