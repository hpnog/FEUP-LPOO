package com.mygdx.game;

import handlers.Assets;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.mygdx.gamestates.GameStateManager;

public class MyJumpyJay extends ApplicationAdapter {

	private SingletonVandC singleton;
	
	public static OrthographicCamera cam;
	
	private GameStateManager gameStateManager;
	
	@Override
	public void create () {
		singleton = SingletonVandC.getSingleton();
		
		singleton.SCREEN_WIDTH = Gdx.graphics.getWidth();
		singleton.SCREEN_HEIGHT = Gdx.graphics.getHeight();
		
		cam = new OrthographicCamera(singleton.SCREEN_WIDTH, singleton.SCREEN_HEIGHT);
		cam.translate(singleton.SCREEN_WIDTH / 2, singleton.SCREEN_HEIGHT / 2);
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
		Assets.manager.finishLoading();
	}

	@Override
	public void resize (int width, int height) {
		
	}
	
	@Override
	public void dispose () {

	}
}
