package com.mygdx.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.mygdx.game.Robot;

public class PlayState extends GameState {

	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	private OrthographicCamera cam;
	private int height;
	private int width;
	private Robot robot;
	
	protected PlayState(GameStateManager gameStateManager) {
		super(gameStateManager);
		init();
	}

	@Override
	public void init() {
		map = new TmxMapLoader().load("maps/test.tmx");
		renderer = new OrthogonalTiledMapRenderer(map);
		MapProperties props = map.getProperties();
		height = props.get("height", Integer.class) * props.get("tileheight", Integer.class);
		width = props.get("width", Integer.class) * props.get("tilewidth", Integer.class);
		
		//Creates camera to control the users view	
		cam = new OrthographicCamera(width, height);
		cam.translate(width / 2, height / 2);
		cam.update();
		
		//Loads the robots Texture
		Texture tex = new Texture(Gdx.files.internal("maps/robots/robot1.png"));
		robot = new Robot(tex, 2 , 8 , "ROBO", (TiledMapTileLayer) map.getLayers().get("Floor"));
	}

	@Override
	public void update(float dt) {
		robot.checkJump();
		robot.update(dt);
	}

	@Override
	public void draw() {
		Gdx.gl20.glClearColor(217 / (float) 256, 208 / (float) 256, 179 / (float) 256, 1);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		
		renderer.setView(cam);
		renderer.render();
		
		renderer.getBatch().begin();
		robot.draw(renderer.getBatch());
		renderer.getBatch().end();
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

}
