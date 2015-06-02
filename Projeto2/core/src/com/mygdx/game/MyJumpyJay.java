package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.mygdx.gamestates.GameStateManager;

public class MyJumpyJay extends ApplicationAdapter {

	public static int WIDTH;
	public static int HEIGHT;
	
	public static OrthographicCamera cam;
	
	private GameStateManager gameStateManager;
	public AssetManager manager = new AssetManager();
	
	@Override
	public void create () {
		
		WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight();
		
		cam = new OrthographicCamera(WIDTH, HEIGHT);
		cam.translate(WIDTH / 2, HEIGHT / 2);
		cam.update();
		
		gameStateManager = new GameStateManager();
	}

	@Override
	public void render () {		
		gameStateManager.update(Gdx.graphics.getDeltaTime());
		gameStateManager.draw();
	}
	
	@Override
	public void pause () {
		
	}
	
	@Override
	public void resume () {
		
	}

	@Override
	public void resize (int width, int height) {
		
	}
	
	@Override
	public void dispose () {
		
	}
}
