package com.mygdx.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.SingletonVandC;

public class LevelMenu extends GameState {

	SpriteBatch batch;
	
	SingletonVandC singleton;

	public LevelMenu(GameStateManager gameStateManager) {
		super(gameStateManager);
		init();
	}

	@Override
	public void init() {
		singleton = SingletonVandC.getSingleton();

		batch = new SpriteBatch();
	}

	@Override
	public void update(float dt) {
		handleInput();
	}

	@Override
	public void render() {
		Gdx.gl20.glClearColor(217 / (float) 256, 208 / (float) 256, 179 / (float) 256, 1);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		batch.begin();

		batch.end();
	}

	@Override
	public void handleInput() {
		if (Gdx.input.justTouched())
			gameStateManager.setState(singleton.PLAY);
	}

	@Override
	public void dispose() {
		batch.dispose();
	}

}