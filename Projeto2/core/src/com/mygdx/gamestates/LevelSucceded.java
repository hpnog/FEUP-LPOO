package com.mygdx.gamestates;

import handlers.Animation;
import handlers.Assets;
import handlers.SingletonVandC;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LevelSucceded extends GameState {

	SpriteBatch batch;	
	SingletonVandC singleton;
	OrthographicCamera cam;

	private Texture screen;
	Animation robot;

	public LevelSucceded(GameStateManager gameStateManager) {
		super(gameStateManager);
		init();
	}

	@Override
	public void init() {
		screen = Assets.manager.get(Assets.successScreen);
		singleton = SingletonVandC.getSingleton();
		batch = new SpriteBatch();
		cam = new OrthographicCamera();
		cam.setToOrtho(false, singleton.SCREEN_WIDTH, singleton.SCREEN_HEIGHT);
		cam.update();
	}

	@Override
	public void update(float dt) {
		handleInput();
	}

	@Override
	public void render() {

		batch.setProjectionMatrix(cam.combined);

		batch.begin();
		batch.draw(screen, singleton.SCREEN_WIDTH / 4, singleton.SCREEN_HEIGHT / 6, (singleton.SCREEN_WIDTH - (singleton.SCREEN_WIDTH / 2)), (singleton.SCREEN_HEIGHT - (singleton.SCREEN_HEIGHT / 3)));
		batch.end();
	}

	@Override
	public void handleInput() {
		
	}

	@Override
	public void dispose() {
		batch.dispose();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

}
